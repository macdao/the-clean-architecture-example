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

import static org.springframework.web.context.WebApplicationContext.*;

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
    @Scope(SCOPE_REQUEST)
    public CreateOrderPresenter createorderPresenter() {
        return new CreateOrderPresenter();
    }

    @Bean
    @Scope(SCOPE_REQUEST)
    public CreateOrderInputBoundary createOrderInputBoundary(OrderGateway orderGateway, TransactionManager transactionManager, OrderFactory orderFactory, CreateOrderOutputBoundary createOrderOutputBoundary) {
        return new CreateOrderInteractor(orderGateway, transactionManager, orderFactory, createOrderOutputBoundary);
    }

    @Bean
    @Scope(SCOPE_REQUEST)
    public CreateOrderController createOrderController(CreateOrderInputBoundary createOrderInputBoundary) {
        return new CreateOrderController(createOrderInputBoundary);
    }

    @Bean
    @Scope(SCOPE_REQUEST)
    public ChangeAddressDetailPresenter changeAddressDetailPresenter() {
        return new ChangeAddressDetailPresenter();
    }

    @Bean
    @Scope(SCOPE_REQUEST)
    public ChangeAddressDetailInputBoundary changeAddressDetailInputBoundary(OrderGateway orderGateway, TransactionManager transactionManager, ChangeAddressDetailOutputBoundary changeAddressDetailOutputBoundary) {
        return new ChangeAddressDetailInteractor(orderGateway, transactionManager, changeAddressDetailOutputBoundary);
    }

    @Bean
    @Scope(SCOPE_REQUEST)
    public ChangeAddressDetailController changeAddressDetailController(ChangeAddressDetailInputBoundary changeAddressDetailInputBoundary) {
        return new ChangeAddressDetailController(changeAddressDetailInputBoundary);
    }
}
