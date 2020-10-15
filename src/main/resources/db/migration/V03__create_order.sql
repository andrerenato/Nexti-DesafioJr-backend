CREATE TABLE `orders` (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    total_amount DECIMAL(20, 2) NOT NULL,
    purchase_date DATE,
    id_client BIGINT(20) NOT NULL,
    FOREIGN KEY (id_client) REFERENCES client(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `orders_product` (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    id_orders BIGINT(20) NOT NULL,
    id_product BIGINT(20) NOT NULL,
    FOREIGN KEY (id_product) REFERENCES product(id),
    FOREIGN KEY (id_orders) REFERENCES `orders`(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;