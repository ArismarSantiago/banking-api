CREATE TABLE `customer_tb` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `birth_date` date NOT NULL,
                               `cpf` varchar(14) NOT NULL,
                               `created_at` datetime(6) DEFAULT NULL,
                               `email` varchar(200) NOT NULL,
                               `name` varchar(150) NOT NULL,
                               `phone_number` varchar(20) NOT NULL,
                               `updated_at` datetime(6) DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `UKm4tvx3qq3knrg4th6lkrbht9m` (`cpf`),
                               UNIQUE KEY `UKdh8j49uagmqf7t0699diw2m8m` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
