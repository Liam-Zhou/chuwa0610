CREATE TABLE `oms_company_address` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `address_name` varchar(200),
    `send_status` int(1),
    `receive_status` int(1),
    `name` varchar(64),
    `phone` varchar(64),
    `province` varchar(64),
    `city` varchar(64),
    `region` varchar(64),
    `detail_address` varchar(200),
    PRIMARY KEY (`id`)
);

INSERT INTO `oms_company_address` (`address_name`, `send_status`, `receive_status`, `name`, `phone`, `province`, `city`, `region`, `detail_address`)
VALUES
('address1', 1, 0, 'li', '1234567890', 'Guangdong', 'Guangzhou', 'haizhu', '123z'),
('address2', 0, 1, 'si', '0987654321', 'Beijing', 'Beijing', 'chaoyang', '456z');

SELECT * FROM `oms_company_address`;

SELECT * FROM `oms_company_address` LIMIT 3;

UPDATE `oms_company_address` SET `phone` = '666-6666-8888';

DELETE FROM `oms_company_address` WHERE `id` = 1;
