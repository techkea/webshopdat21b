DROP DATABASE IF EXISTS webshop;
CREATE DATABASE webshop;
USE webshop;
CREATE TABLE `product` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `name` varchar(255) DEFAULT NULL,
                           `price` int NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO product(name, price) VALUES
                                     ("Kaffe", 40),
                                     ("Mælk", 11),
                                     ("Kiks", 11);
