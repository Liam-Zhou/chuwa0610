USE ChuwaHomework; 

CREATE TABLE oms_cart_item (
    id BIGINT PRIMARY KEY,
    product_id BIGINT,
    product_sku_id BIGINT,
    member_id BIGINT,
    quantity INT,
    price DECIMAL(10, 2),
    sp1 VARCHAR(200),
    sp2 VARCHAR(200),
    sp3 VARCHAR(200),
    product_pic VARCHAR(1000),
    product_name VARCHAR(500),
    product_brand VARCHAR(200),
    product_sn VARCHAR(200),
    product_sub_title VARCHAR(500),
    product_sku_code VARCHAR(200),
    member_nickname VARCHAR(500),
    create_date DATETIME,
    modify_date DATETIME,
    delete_status INT(1),
    product_category_id BIGINT,
    product_attr VARCHAR(500)
);


INSERT INTO oms_cart_item (id, product_id, product_sku_id, member_id, quantity, price, sp1, sp2, sp3, product_pic, product_name, product_brand, product_sn, product_sub_title, product_sku_code, member_nickname, create_date, modify_date, delete_status, product_category_id, product_attr)
VALUES
(1, 101, 1001, 201, 2, 19.99, 'Color: Red', 'Size: M', 'Material: Cotton', 'http://example.com/pic1.jpg', 'T-Shirt', 'Brand A', 'SN123456', 'Comfortable T-Shirt', 'SKU1234', 'john_doe', '2024-06-27 12:00:00', '2024-06-27 12:00:00', 0, 301, '{"key":"color","value":"red"}'),
(2, 102, 1002, 202, 1, 29.99, 'Color: Blue', 'Size: L', 'Material: Polyester', 'http://example.com/pic2.jpg', 'Jeans', 'Brand B', 'SN654321', 'Stylish Jeans', 'SKU5678', 'jane_smith', '2024-06-27 13:00:00', '2024-06-27 13:00:00', 0, 302, '{"key":"color","value":"blue"}'),
(3, 103, 1003, 203, 3, 9.99, 'Color: Green', 'Size: S', 'Material: Wool', 'http://example.com/pic3.jpg', 'Hat', 'Brand C', 'SN789012', 'Warm Hat', 'SKU9101', 'mike_johnson', '2024-06-27 14:00:00', '2024-06-27 14:00:00', 0, 303, '{"key":"color","value":"green"}'),
(4, 104, 1004, 204, 4, 39.99, 'Color: Yellow', 'Size: XL', 'Material: Silk', 'http://example.com/pic4.jpg', 'Jacket', 'Brand D', 'SN345678', 'Elegant Jacket', 'SKU1121', 'emily_davis', '2024-06-27 15:00:00', '2024-06-27 15:00:00', 0, 304, '{"key":"color","value":"yellow"}'),
(5, 105, 1005, 205, 5, 49.99, 'Color: Black', 'Size: XXL', 'Material: Leather', 'http://example.com/pic5.jpg', 'Coat', 'Brand E', 'SN901234', 'Luxury Coat', 'SKU1314', 'chris_brown', '2024-06-27 16:00:00', '2024-06-27 16:00:00', 0, 305, '{"key":"color","value":"black"}');



 