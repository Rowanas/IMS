DROP SCHEMA IF EXISTS ims; 
SET @@global.time_zone = '+00:00';

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `customers` (
	`customer_id` INT AUTO_INCREMENT PRIMARY KEY,
    `forename` VARCHAR(64) DEFAULT NULL,
    `surname` VARCHAR(64) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `item` (
	`item_id` INT AUTO_INCREMENT PRIMARY KEY,
	`item_name` VARCHAR(64),
	`item_price` DOUBLE
);

CREATE TABLE IF NOT EXISTS `orders` (
	`order_id` INT AUTO_INCREMENT PRIMARY KEY,
	`customer_id` INT,
	FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE IF NOT EXISTS `order_items` (
	`order_item_id` INT AUTO_INCREMENT PRIMARY KEY,
	`item_id` INT,
    `order_id` INT,
    FOREIGN KEY (item_id) REFERENCES item(item_id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id)
);
CREATE DATABASE IF NOT EXISTS `database`;

USE `database` ;

DROP TABLE IF EXISTS order_items ;
DROP TABLE IF EXISTS orders ;
DROP TABLE IF EXISTS item ;
DROP TABLE IF EXISTS customers ;

-- added on delete cascade to each element with FK, as it was failing to cascade deletions.

CREATE TABLE IF NOT EXISTS `customers` (
	`customer_id` INT AUTO_INCREMENT PRIMARY KEY,
    `forename` VARCHAR(64) DEFAULT NULL,
    `surname` VARCHAR(64) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `item` (
	`item_id` INT AUTO_INCREMENT PRIMARY KEY,
	`item_name` VARCHAR(64),
	`item_price` DOUBLE
);

CREATE TABLE IF NOT EXISTS `orders` (
	`order_id` INT AUTO_INCREMENT PRIMARY KEY,
	`customer_id` INT,
	FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `order_items` (
	`order_item_id` INT AUTO_INCREMENT PRIMARY KEY,
	`item_id` INT,
    `order_id` INT,
    FOREIGN KEY (item_id) REFERENCES item(item_id) ON DELETE CASCADE,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE
);


