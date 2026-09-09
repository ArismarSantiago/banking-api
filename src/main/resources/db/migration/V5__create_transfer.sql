CREATE TABLE `transfer_tb` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `amount` decimal(38,2) NOT NULL,
                               `created_at` datetime(6) DEFAULT NULL,
                               `status` enum('CANCELLED','COMPLETED','FAILED','PENDING') DEFAULT NULL,
                               `type` enum('INTERNAL','PIX','TED') DEFAULT NULL,
                               `destination_account_id` bigint DEFAULT NULL,
                               `source_account_id` bigint DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               KEY `FK714ka7o862hoqhfo8v5jb8bfg` (`destination_account_id`),
                               KEY `FK3nb6y1pj1h8j4ogtchaxk6946` (`source_account_id`),
                               CONSTRAINT `FK3nb6y1pj1h8j4ogtchaxk6946` FOREIGN KEY (`source_account_id`) REFERENCES `account_tb` (`id`),
                               CONSTRAINT `FK714ka7o862hoqhfo8v5jb8bfg` FOREIGN KEY (`destination_account_id`) REFERENCES `account_tb` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;