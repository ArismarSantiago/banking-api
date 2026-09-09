CREATE TABLE `account_tb` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `account_number` varchar(20) NOT NULL,
                              `balance` decimal(38,2) DEFAULT NULL,
                              `created_at` datetime(6) DEFAULT NULL,
                              `status` enum('ACTIVE','BLOCKED','CLOSED') DEFAULT NULL,
                              `type` enum('CHECKING','SAVINGS') DEFAULT NULL,
                              `updated_at` datetime(6) DEFAULT NULL,
                              `agency_id` bigint DEFAULT NULL,
                              `customer_id` bigint DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `UK4tgqwglqa8k1ftk9obyknotd5` (`account_number`),
                              KEY `FK5qjssm90m24awsh2dn8bvekss` (`agency_id`),
                              KEY `FK6xlg3082tu3n7bu32h5sdampj` (`customer_id`),
                              CONSTRAINT `FK5qjssm90m24awsh2dn8bvekss` FOREIGN KEY (`agency_id`) REFERENCES `agency_tb` (`id`),
                              CONSTRAINT `FK6xlg3082tu3n7bu32h5sdampj` FOREIGN KEY (`customer_id`) REFERENCES `customer_tb` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;