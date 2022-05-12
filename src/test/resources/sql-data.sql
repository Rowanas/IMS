INSERT INTO `customers` (`forename`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `item` (item_name, item_price) VALUES ('pliers', 3.99);
INSERT INTO `orders` (customer_id) VALUES (1);
INSERT INTO `order_items` (customer_id, item_id) VALUES (1,1);