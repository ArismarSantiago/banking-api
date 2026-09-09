CREATE TABLE `transaction_tb` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `amount` decimal(38,2) NOT NULL,
                                  `balance_after` decimal(38,2) NOT NULL,
                                  `created_at` datetime(6) DEFAULT NULL,
                                  `description` varchar(200) DEFAULT NULL,
                                  `type` enum('DEPOSIT','PIX_RECEIVED','PIX_SENT','TRANSFER_RECEIVED','TRANSFER_SENT','WITHDRAWAL') DEFAULT NULL,
                                  `account_id` bigint DEFAULT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `FKga8eu24jc0uupjwoau402s93i` (`account_id`),
                                  CONSTRAINT `FKga8eu24jc0uupjwoau402s93i` FOREIGN KEY (`account_id`) REFERENCES `account_tb` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;