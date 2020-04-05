package com.macdao.ecommerce.infrastructure.app;

public interface TransactionManager {
    void startTransaction();

    void commit();

    void rollback();
}
