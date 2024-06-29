-- choose database
use Chuwa_Project;

-- 1.Create oms_company_address table
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
-- 2.Insert some random data to oms_company_address table
INSERT INTO `oms_company_address` (`address_name`, `send_status`, `receive_status`, `name`, `phone`, `province`, `city`, `region`, `detail_address`)
VALUES
('Company A Address', 1, 0, 'John Doe', '123-456-7890', 'Province A', 'City A', 'Region A', '123 Main St'),
('Company B Address', 1, 1, 'Jane Smith', '987-654-3210', 'Province B', 'City B', 'Region B', '456 Elm St'),
('Company C Address', 0, 1, 'Michael Johnson', '555-123-4567', 'Province C', 'City C', 'Region C', '789 Oak Ave'),
('Company D Address', 1, 0, 'Emily Brown', '333-888-9999', 'Province D', 'City D', 'Region D', '567 Pine Rd'),
('Company E Address', 0, 0, 'David Wilson', '222-444-6666', 'Province E', 'City E', 'Region E', '890 Maple Blvd'),
('Company F Address', 1, 1, 'Sarah Lee', '111-222-3333', 'Province F', 'City F', 'Region F', '234 Cedar Ln');
-- 3.Write a SQL query to fetch all data from oms_company_address `table
select * from oms_company_address;
-- 4. Write a SQL query to fetch top 3 records from oms_company_address table
select * from oms_company_address
order by id
limit 3;
-- 5. Update oms_company_address table to set all phone to 666-6666-8888
UPDATE `oms_company_address`
SET `phone` = '666-6666-8888'
WHERE `id` > 0;

-- 6. Delete one entry from oms_company_address table
DELETE  FROM  oms_company_address WHERE id = 2;
-- 7. (Optional) You can also try to create other tables that listed above
select  * from oms_company_address;