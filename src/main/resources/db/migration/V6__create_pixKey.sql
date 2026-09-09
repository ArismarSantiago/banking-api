CREATE TABLE `pix_kays_tb` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `active` bit(1) NOT NULL,
                               `created_at` datetime(6) DEFAULT NULL,
                               `key_type` enum('CPF','EMAIL','PHONE','RANDOM') DEFAULT NULL,
                               `key_value` varchar(255) NOT NULL,
                               `account_id` bigint DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               KEY `FK97pl6203mwgerqe70rjm8fwiw` (`account_id`),
                               CONSTRAINT `FK97pl6203mwgerqe70rjm8fwiw` FOREIGN KEY (`account_id`) REFERENCES `account_tb` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
