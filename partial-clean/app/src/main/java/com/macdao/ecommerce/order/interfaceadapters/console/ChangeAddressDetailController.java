package com.macdao.ecommerce.order.interfaceadapters.console;

import com.macdao.ecommerce.order.usecases.*;
import org.slf4j.*;

public class ChangeAddressDetailController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ChangeAddressDetailInteractor changeAddressDetail;

    public ChangeAddressDetailController(ChangeAddressDetailInteractor changeAddressDetail) {
        this.changeAddressDetail = changeAddressDetail;
    }

    public ChangeAddressDetailViewModel execute(String input) {
        var split = input.split("\\s");
        var orderId = split[1];
        var detail = split[2];

        var inputData = new ChangeAddressDetailInputData(orderId, detail);

        ChangeAddressDetailOutputData outputData;
        try {
            outputData = changeAddressDetail.execute(inputData);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            return new ChangeAddressDetailViewModel(false, null, null, e.getMessage());
        }
        return new ChangeAddressDetailViewModel(true, outputData.getOrderId(), outputData.getDetail(), null);

    }
}
