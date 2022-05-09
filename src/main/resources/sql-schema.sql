DROP SCHEMA IF EXISTS ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS customers (
	`customer_id` INT AUTO_INCREMENT PRIMARY KEY,
    `forename` VARCHAR(64) DEFAULT NULL,
    `surname` VARCHAR(64) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS item (
	item_id INT AUTO_INCREMENT PRIMARY KEY,
	item_name VARCHAR(64),
	item_price DOUBLE
);

CREATE TABLE IF NOT EXISTS order_items (
	order_item_id INT AUTO_INCREMENT PRIMARY KEY,
	items INT,
    FOREIGN KEY (items) REFERENCES item(item_id)
);

CREATE TABLE IF NOT EXISTS orders (
	order_id INT AUTO_INCREMENT PRIMARY KEY,
	order_item_id INT,
	total_price INT,
    customer_id INT,
    FOREIGN KEY (order_item_id) REFERENCES order_items(order_item_id),
	FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

