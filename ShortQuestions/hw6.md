# SQL
## 1. Create oms_company_address table
```
CREATE TABLE oms_company_addresss (
    `id`    bigint,
    `address_name`  varchar(200),
    `send_status`   int(1),
    `receive_status`    int(1),
    `name`  varchar(64),
    `phone`	    varchar(64),
    `province`   varchar(64),
    `city`  varchar(64),
    `region`    varchar(64),
    `detail_address`    varchar(64),
    PRIMARY KEY(`id`)
);
```

## 2. Insert some random data to oms_company_address table
```
insert oms_company_addresss (id, address_name, send_status,receive_status,name,phone,province,city,region,detail_address) VALUES
    (1, "Centre0", 0, 0, "M", "5555555555", "PA","Pittsburgh","Pittsburgh", "apt 1234"),
    (2, "Centre1", 0, 0, "M", "5555555555", "PA","Pittsburgh","Pittsburgh", "apt 1234"),
    (3, "Centre2", 0, 0, "M", "5555555555", "PA","Pittsburgh","Pittsburgh", "apt 1234"),
    (4, "Centre3", 1, 0, "M", "5555555555", "PA","Pittsburgh","Pittsburgh", "apt 1234"),
    (5, "Centre4", 1, 1, "M", "5555555555", "PA","Pittsburgh","Pittsburgh", "apt 1234")

```

## 3. Write a SQL query to fetch all data from oms_company_address `table
```
Select * from oms_company_addresss;
```

## 4. Write a SQL query to fetch top 3 records from oms_company_address table
``` 
   Select * from oms_company_addresss order by  id desc LIMIT 3
```

## 5. Update oms_company_address table to set all phone to 666-6666-8888
```
UPDATE oms_company_addresss
SET phone = "666-6666-8888"
```
## 6. Delete one entry from oms_company_address table
```
DELETE  FROM oms_company_addresss WHERE id = 1;
```

# NoSQL - MongoDB
