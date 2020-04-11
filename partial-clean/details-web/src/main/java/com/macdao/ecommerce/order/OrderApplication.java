package com.macdao.ecommerce.order;

import com.macdao.ecommerce.infrastructure.app.*;
import com.macdao.ecommerce.infrastructure.app.details.*;
import com.macdao.ecommerce.order.details.*;
import com.macdao.ecommerce.order.interfaceadapters.jdbc.*;
import com.macdao.ecommerce.order.interfaceadapters.web.*;
import com.macdao.ecommerce.order.usecases.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;

@SpringBootApplication
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
    public OrderGateway orderGateway(DataAccessInterface dataAccess) {
        return new JdbcOrderGateway(dataAccess);
    }

    @Bean
    public TransactionManager dummyTransactionManager() {
        return new DummyTransactionManager();
    }

    @Bean
    public OrderFactory orderFactory(OrderIdGenerator orderIdGenerator) {
        return new OrderFactory(orderIdGenerator);
    }

    @Bean
    public OrderIdGenerator orderIdGenerator() {
        return new UuidOrderIdGenerator();
    }

    @Bean
    public CreateOrderInteractor createOrderInputBoundary(OrderGateway orderGateway, TransactionManager transactionManager, OrderFactory orderFactory) {
        return new CreateOrderInteractor(orderGateway, transactionManager, orderFactory);
    }

    @Bean
    public CreateOrderController createOrderController(CreateOrderInteractor createOrderInputBoundary) {
        return new CreateOrderController(createOrderInputBoundary);
    }

    @Bean
    public ChangeAddressDetailInteractor changeAddressDetailInputBoundary(OrderGateway orderGateway, TransactionManager transactionManager) {
        return new ChangeAddressDetailInteractor(orderGateway, transactionManager);
    }

    @Bean
    public ChangeAddressDetailController changeAddressDetailController(ChangeAddressDetailInteractor changeAddressDetailInputBoundary) {
        return new ChangeAddressDetailController(changeAddressDetailInputBoundary);
    }
}
