package com.macdao.ecommerce.order.details.console;

import com.macdao.ecommerce.infrastructure.app.*;
import com.macdao.ecommerce.infrastructure.app.details.*;
import com.macdao.ecommerce.order.details.*;
import com.macdao.ecommerce.order.details.database.*;
import com.macdao.ecommerce.order.interfaceadapters.console.*;
import com.macdao.ecommerce.order.usecases.*;

import java.util.*;

import static java.lang.String.*;

public class Main {
    private final CreateOrderPresenter createOrderPresenter;
    private final CreateOrderController createOrderController;
    private final ChangeAddressDetailPresenter changeAddressDetailPresenter;
    private final ChangeAddressDetailController changeAddressDetailController;

    public static void main(String[] args) {
        var main = new Main();
        main.start();
    }

    public Main() {
        TransactionManager transactionManager = new DummyTransactionManager();
        OrderGateway orderGateway = new InMemOrderGateway();

        createOrderPresenter = new CreateOrderPresenter();
        var createOrder = new CreateOrderInteractor(orderGateway, transactionManager, new OrderFactory(new UuidOrderIdGenerator()), createOrderPresenter);
        createOrderController = new CreateOrderController(createOrder);

        changeAddressDetailPresenter = new ChangeAddressDetailPresenter();
        var changeAddressDetail = new ChangeAddressDetailInteractor(orderGateway, transactionManager, changeAddressDetailPresenter);
        changeAddressDetailController = new ChangeAddressDetailController(changeAddressDetail);

    }

    private void start() {
        printHelp();
        Scanner in = new Scanner(System.in);
        while (true) {
            handleInput(in.nextLine());
        }
    }

    private void handleInput(String input) {
        if (input == null) {
            System.exit(0);
        } else if (input.startsWith("createOrder")) {
            createOrder(input);
        } else if (input.startsWith("changeAddressDetail")) {
            changeAddressDetail(input);
        } else {
            System.err.println("Unknown command: " + input);
        }
    }

    private void printHelp() {
        System.out.println("createOrder <productId> <count> <itemPrice> <addressProvince> <addressCity> <addressDetail>");
        System.out.println("changeAddressDetail <orderId> <newAddressDetail>");
    }

    private void createOrder(String input) {
        createOrderController.execute(input);

        var viewModel = createOrderPresenter.toViewModel();

        if (viewModel.isSuccessful()) {
            System.out.println(format("Order created: orderId: %s, items: %s, address: %s", viewModel.getOrderId(), viewModel.getItems(), viewModel.getAddress()));
        }
    }

    private void changeAddressDetail(String input) {
        changeAddressDetailController.execute(input);

        var viewModel = changeAddressDetailPresenter.toViewModel();
        System.out.println("succeed: " + viewModel.isSuccessful());
        if (viewModel.isSuccessful()) {
            System.out.println(format("orderId: %s, detail: %s", viewModel.getOrderId(), viewModel.getDetail()));
        } else {
            System.err.println("reason: " + viewModel.getReason());
        }
    }
}
