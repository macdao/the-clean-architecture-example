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
    private final ChangeAddressDetailController changeAddressDetailController;

    public OrderRestController(CreateOrderController createOrderController, ChangeAddressDetailController changeAddressDetailController) {
        this.createOrderController = createOrderController;
        this.changeAddressDetailController = changeAddressDetailController;
    }

    @PostMapping
    public CreateOrderViewModel createOrder(@RequestBody CreateOrderRequest request) {
        return createOrderController.execute(request);
    }

    @PutMapping("/{orderId}/address/detail")
    public void changeAddressDetail(@PathVariable String orderId, @RequestBody String detail) {
        changeAddressDetailController.execute(orderId, detail);
    }
}
