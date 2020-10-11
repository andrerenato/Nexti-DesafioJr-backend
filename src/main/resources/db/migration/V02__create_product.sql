CREATE TABLE product (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    sku VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(50),
    value DOUBLE NOT NULL,
    quantity BIGINT(20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;