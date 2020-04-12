package com.macdao.ecommerce.order.usecases;

import com.macdao.ecommerce.order.entities.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
public class ChangeAddressDetailInteractor {
    private final OrderRepository orderRepository;

    public ChangeAddressDetailInteractor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public ChangeAddressDetailOutputData execute(ChangeAddressDetailInputData inputData) {
        var orderId = inputData.getOrderId();

        return orderRepository.findById(new OrderId(orderId))
                .map(order -> {
                    order.changeAddressDetail(inputData.getDetail());
                    orderRepository.save(order);
                    return new ChangeAddressDetailOutputData(orderId, inputData.getDetail());
                })
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }
}
