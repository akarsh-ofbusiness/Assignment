CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    productId VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    description TEXT,
    price DOUBLE,
    categoryId VARCHAR(255),
    UNIQUE KEY productId (productId),
    FOREIGN KEY (categoryId) REFERENCES category(categoryId)
);




CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    categoryId VARCHAR(255) NOT NULL UNIQUE,
    categoryName VARCHAR(255)
);