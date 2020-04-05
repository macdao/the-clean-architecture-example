CREATE TABLE "order" (
    id VARCHAR(50) PRIMARY KEY,
    status VARCHAR(50) NOT NULL,
    address_province VARCHAR(50) NOT NULL,
    address_city VARCHAR(50) NOT NULL,
    address_detail VARCHAR(50) NOT NULL
);

CREATE TABLE order_item (
    order_id VARCHAR(50),
    product_id  VARCHAR(50) NOT NULL,
    count INTEGER NOT NULL,
    item_price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES "order"(id)
);
