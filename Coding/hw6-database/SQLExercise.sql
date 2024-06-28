--Create a new DB Schema
CREATE SCHEMA
`HW6`;

--Create oms_company_address table
CREATE TABLE `HW6`.`oms_company_address`
(
   `id` INT NOT NULL,
   `address_name` VARCHAR
(200) NULL,
   `send_status` INT NULL,
   `receive_status` INT NULL,
   `name` VARCHAR
(64) NULL,
   `phone` VARCHAR
(64) NULL,
   `province` VARCHAR
(64) NULL,
   `city` VARCHAR
(64) NULL,
   `region` VARCHAR
(64) NULL,
   `detail_address` VARCHAR
(200) NULL,
   PRIMARY KEY
(`id`)
);

--Insert data to oms_company_address table
INSERT INTO `HW6`.`oms_company_address` (`id`, `address_name`, `send_status`, `receive_status`, `name`, `phone`, `province`, `city`, `region`, `detail_address`)
VALUE (1, '123 Main St', 1, 0, 'Mike', '123456789', 'CA', 'San Jose', 'East', 'Floor 1');
INSERT INTO `HW6`.`oms_company_address` (`id`, `address_name`, `send_status`, `receive_status`, `name`, `phone`, `province`, `city`, `region`, `detail_address`)
VALUE (2, '572 Baine St', 0, 0, 'James', '132624322', 'CA', 'Fremont', 'West', 'Floor 0');
INSERT INTO `HW6`.`oms_company_address` (`id`, `address_name`, `send_status`, `receive_status`, `name`, `phone`, `province`, `city`, `region`)
VALUE (3, '9021 Jackson St', 1, 1, 'Tom', '3409860341', 'CA', 'Cupertino', 'Central');
INSERT INTO `HW6`.`oms_company_address` (`id`, `address_name`, `send_status`, `receive_status`, `name`, `phone`, `province`, `city`, `region`, `detail_address`)
VALUE (4, '245 Second St', 1, 0, 'Alan', '345572543', 'CA', 'Foster City', 'East', 'Floor 3');

--SQL query to fetch all data from the table
SELECT * FROM `HW6`.`oms_company_address`;

--SQL query to fetch top 3 records from the table
SELECT * FROM HW6.oms_company_address LIMIT 3;

--Update table to set all phone to 666-6666-8888
UPDATE HW6.oms_company_address SET phone = '666-6666-8888';

--Delete one entry from the table
DELETE from HW6.oms_company_address WHERE id = 4;
