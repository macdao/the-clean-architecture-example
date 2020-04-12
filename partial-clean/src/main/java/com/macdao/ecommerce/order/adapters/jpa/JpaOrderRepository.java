package com.macdao.ecommerce.order.adapters.jpa;

import com.macdao.ecommerce.order.entities.*;
import com.macdao.ecommerce.order.usecases.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, OrderId> {
}
