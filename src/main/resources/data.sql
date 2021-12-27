use coin_bank;
CREATE TABLE IF NOT EXISTS `coin_info` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `transaction_type` VARCHAR(255) NOT NULL,
    `amount` VARCHAR(255) NOT NULL,
    `balance` VARCHAR(255) NOT NULL,
    `date_time` DATETIME NOT NULL,
    `zone_id` TIMESTAMP WITH TIME ZONE DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
    )
    COLLATE='utf8mb4_0900_ai_ci'
    ENGINE=InnoDB
    AUTO_INCREMENT=5
;