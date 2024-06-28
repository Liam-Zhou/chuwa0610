USE ChuwaHomework;
INSERT INTO oms_company_address (id, address_name, send_status, receive_status, name, phone, province, city, region, detail_address)
VALUES
(1, 'Office', 1, 0, 'John Doe', '1234567890', 'California', 'Los Angeles', 'West', '1234 West Street'),
(2, 'Warehouse', 0, 1, 'Jane Smith', '0987654321', 'Texas', 'Houston', 'Central', '5678 Central Avenue'),
(3, 'Home', 1, 1, 'Mike Johnson', '5551234567', 'New York', 'New York City', 'East', '9101 East Road'),
(4, 'Branch Office', 0, 0, 'Emily Davis', '4445556666', 'Florida', 'Miami', 'South', '1121 South Boulevard'),
(5, 'Main Office', 1, 1, 'Chris Brown', '3334445555', 'Nevada', 'Las Vegas', 'North', '1314 North Drive');