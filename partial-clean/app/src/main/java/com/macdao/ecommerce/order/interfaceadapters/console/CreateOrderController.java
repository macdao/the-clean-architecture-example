package com.macdao.ecommerce.order.interfaceadapters.console;

import com.macdao.ecommerce.order.usecases.*;
import org.slf4j.*;

import java.math.*;
import java.util.*;

import static java.lang.String.*;
import static java.util.List.*;

public class CreateOrderController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final CreateOrderInteractor createOrder;

    public CreateOrderController(CreateOrderInteractor createOrder) {
        this.createOrder = createOrder;
    }

    public CreateOrderViewModel execute(String input) {
        var split = input.split("\\s");
        var productId = split[1];
        var count = Integer.parseInt(split[2]);
        var itemPrice = new BigDecimal(split[3]);
        var addressProvince = split[4];
        var addressCity = split[5];
        var addressDetail = split[6];
        CreateOrderInputData inputData = new CreateOrderInputData(of(new OrderItemData(productId, count, itemPrice)), addressProvince, addressCity, addressDetail);

        CreateOrderOutputData outputData;
        try {
            outputData = createOrder.execute(inputData);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            return CreateOrderViewModel.fail(e.getMessage());
        }

        var items = Arrays.toString(outputData.getOrderItemDataList().stream()
                .map(orderItem -> format("%s %s %s", orderItem.getProductId(), orderItem.getCount(), orderItem.getItemPrice()))
                .toArray());
        var address = format("%s %s %s", outputData.getAddressProvince(), outputData.getAddressCity(), outputData.getAddressDetail());
        return CreateOrderViewModel.succeed(outputData.getOrderId(), items, address);
    }
}
