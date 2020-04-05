package com.macdao.ecommerce.order.interfaceadapters.jdbc;

import com.macdao.ecommerce.order.entities.*;
import com.macdao.ecommerce.order.usecases.*;

import java.math.*;
import java.util.*;
import java.util.stream.*;

import static java.util.Map.*;

public class JdbcOrderGateway implements OrderGateway {
    private final DataAccessInterface dataAccess;

    public JdbcOrderGateway(DataAccessInterface dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public Optional<Order> getOrder(OrderId id) {
        var orderData = dataAccess.query("select * from \"order\" where id = :id", of("id", id.toString()));
        if (orderData == null) {
            return Optional.empty();
        }

        var address = new Address((String) orderData.get("address_province"), (String) orderData.get("address_city"), (String) orderData.get("address_detail"));

        var orderItemDataList = dataAccess.queryForList("select * from order_item where order_id = :order_id", of("order_id", id.toString()));
        var items = orderItemDataList.stream()
                .map(orderItemData -> new OrderItem(new ProductId((String) orderItemData.get("product_id")), (Integer) orderItemData.get("count"), (BigDecimal) orderItemData.get("item_price")))
                .collect(Collectors.toList());

        return Optional.of(Order.reconstitute(new OrderId((String) orderData.get("id")), items, Order.OrderStatus.valueOf((String) orderData.get("status")), address));
    }

    @Override
    public void insert(Order order) {
        dataAccess.update("insert into \"order\" (id, status, address_province, address_city, address_detail) values (:id, :status, :address_province, :address_city, :address_detail)",
                of("id", order.getId().toString(),
                        "status", order.getStatus().name(),
                        "address_province", order.getAddress().getProvince(),
                        "address_city", order.getAddress().getCity(),
                        "address_detail", order.getAddress().getDetail()));

        dataAccess.update("insert into order_item (order_id, product_id, count, item_price) values (:order_id, :product_id, :count, :item_price)",
                order.getItems().stream()
                        .map(orderItem -> of("order_id", order.getId().toString(),
                                "product_id", orderItem.getProductId().toString(),
                                "count", orderItem.getCount(),
                                "item_price", orderItem.getItemPrice()
                        )).collect(Collectors.toList())
        );
    }

    @Override
    public void update(Order order) {
        int orderUpdated = dataAccess.update("update \"order\" set status = :status, address_province = :address_province, address_city =:address_city, address_detail = :address_detail where id = :id",
                of("status", order.getStatus().name(),
                        "address_province", order.getAddress().getProvince(),
                        "address_city", order.getAddress().getCity(),
                        "address_detail", order.getAddress().getDetail(),
                        "id", order.getId().toString()));

        if (orderUpdated != 1) {
            throw new IllegalStateException();
        }

        dataAccess.update("delete from order_item where order_id = :order_id", of("order_id", order.getId().toString()));

        int orderItemUpdated = dataAccess.update("insert into order_item (order_id, product_id, count, item_price) values (:order_id, :product_id, :count, :item_price)",
                order.getItems().stream()
                        .map(orderItem -> of("order_id", order.getId().toString(),
                                "product_id", orderItem.getProductId().toString(),
                                "count", orderItem.getCount(),
                                "item_price", orderItem.getItemPrice()
                        )).collect(Collectors.toList()));
    }
}
