package com.macdao.ecommerce.order.details.web;

import com.macdao.ecommerce.order.interfaceadapters.web.*;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.context.WebApplicationContext.*;

@RestController
@RequestMapping("/orders")
@Scope(SCOPE_REQUEST)
public class OrderRestController {
    private final CreateOrderController createOrderController;
    private final CreateOrderPresenter createOrderPresenter;
    private final ChangeAddressDetailController changeAddressDetailController;
    private final ChangeAddressDetailPresenter changeAddressDetailPresenter;

    public OrderRestController(CreateOrderController createOrderController, CreateOrderPresenter createOrderPresenter, ChangeAddressDetailController changeAddressDetailController, ChangeAddressDetailPresenter changeAddressDetailPresenter) {
        this.createOrderController = createOrderController;
        this.createOrderPresenter = createOrderPresenter;
        this.changeAddressDetailController = changeAddressDetailController;
        this.changeAddressDetailPresenter = changeAddressDetailPresenter;
    }

    @PostMapping
    public CreateOrderViewModel createOrder(@RequestBody CreateOrderRequest request) throws Exception {
        createOrderController.execute(request);

        if (!createOrderPresenter.isSuccessful()) {
            throw createOrderPresenter.getException();
        }
        return createOrderPresenter.toViewModel();
    }

    @PutMapping("/{orderId}/address/detail")
    public void changeAddressDetail(@PathVariable String orderId, @RequestBody String detail) throws Exception {
        changeAddressDetailController.execute(orderId, detail);

        if (!changeAddressDetailPresenter.isSuccessful()) {
            throw changeAddressDetailPresenter.getException();
        }
    }
}
