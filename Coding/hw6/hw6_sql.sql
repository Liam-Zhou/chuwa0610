-- Create oms_company_address table
CREATE TABLE oms_company_address (
                                     id BIGINT PRIMARY KEY,
                                     address_name VARCHAR(200),
                                     send_status INT(1),
                                     receive_status INT(1),
                                     name VARCHAR(64),
                                     phone VARCHAR(64),
                                     province VARCHAR(64),
                                     city VARCHAR(64),
                                     region VARCHAR(64),
                                     detail_address VARCHAR(200)
);

-- Insert some random data into oms_company_address table
INSERT INTO oms_company_address (id, address_name, send_status, receive_status, name, phone, province, city, region, detail_address)
VALUES
    (1, 'Warehouse A', 1, 1, 'John Doe', '123-456-7890', 'Province A', 'City A', 'Region A', '123 Main St'),
    (2, 'Warehouse B', 0, 1, 'Jane Smith', '234-567-8901', 'Province B', 'City B', 'Region B', '456 Elm St'),
    (3, 'Warehouse C', 1, 0, 'Alice Johnson', '345-678-9012', 'Province C', 'City C', 'Region C', '789 Pine St'),
    (4, 'Warehouse D', 0, 0, 'Bob Brown', '456-789-0123', 'Province D', 'City D', 'Region D', '101 Maple St');

-- Fetch all data from oms_company_address table
SELECT * FROM oms_company_address;

-- Fetch top 3 records from oms_company_address table
SELECT * FROM oms_company_address
ORDER BY id
    LIMIT 3;

-- Update oms_company_address table to set all phone to 666-6666-8888
UPDATE oms_company_address
SET phone = '666-6666-8888';

-- Delete one entry from oms_company_address table
DELETE FROM oms_company_address
WHERE id = 1;