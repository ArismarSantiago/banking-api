CREATE TABLE `agency_tb` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `city` varchar(20) NOT NULL,
                             `code` varchar(4) NOT NULL,
                             `name` varchar(150) NOT NULL,
                             `state` varchar(20) NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
