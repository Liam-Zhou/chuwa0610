### 1. create oms_company_address
```bash
    create table oms_company_address (
        id bigint primary key,
        address_name varchar(200),
        send_status int(1),
        receive_status int(1),
        name varchar(64),
        phone varchar(64),
        province varchar(64),
        city varchar(64),
        region varchar(64),
        detail_address varchar(200)
    )
```

### 2. Insert some random data to oms_company_address table
```bash
insert into oms_company_address(id, address_name, send_status, receive_status, name, phone, province, city, region, detail_address) values 
(1, "Oak Hollow", "1", "0", "kay", "111", "TX", "Frisco", "Collin", "Non"),
(2, "Oak Hollow", "1", "0", "sherry", "112", "TX", "Frisco", "Collin", "Non"),
(3, "Oak Hollow", "1", "0", "grace", "113", "TX", "Frisco", "Collin", "Non"),
(4, "Oak Hollow", "1", "0", "lucy", "140", "TX", "Frisco", "Collin", "Non")
```
### 3. Write a SQL query to fetch all data from oms_company_address `table
```bash
    select * 
    from oms_company_address
```
### 4. Write a SQL query to fetch top 3 records from oms_company_address table
```bash
    select * 
    from oms_company_address 
    limit 3;
```
### 5. Update oms_company_address table to set all phone to 666-6666-8888
```bash
    set sql_safe_updates = 0;
    update oms_company_address set phone = "666-6666-8888";
    set sql_safe_updates = 1;
```
### 6. Delete one entry from oms_company_address table
```bash
    delete from oms_company_address
    where id = 1;
```
### 7. (Optional)You can also try to create other tables that listed above

## MongoDB Non-SQL 

### 1. Create test DB
```bash
use test
```
### 2. Create oms_company_address collection (method: createCollection() )
```bash
db.createCollection("oms_company_address")
```
### 3. Insert few random entries to oms_company_address collection (method: insert() )
```bash
// insert one entry
db.oms_company_address.insertOne({address_name: "Stanhope Ln", send_status: "1", receive_status: "0", name: "raphle", phone: "123",province: "CA", city: "Riverside",region: "LA", detail_address: "leave at the door"})

// insert many
db.oms_comany_address.insertMany([{address_name: "Stanhope Ln", send_status: "1", receive_status: "0", name: "kitten", phone: "123",province: "CA", city: "Riverside",region: "LA", detail_address: "leave at the door"}, 
{address_name: "Stanhope Ln", send_status: "1", receive_status: "0", name: "Bob", phone: "123",province: "CA", city: "Riverside",region: "LA", detail_address: "leave at the door"},
{address_name: "Stanhope Ln", send_status: "1", receive_status: "0", name: "steve", phone: "123",province: "CA", city: "Riverside",region: "LA", detail_address: "leave at the door"}
])
```
### 4. Read one entry from oms_company_address collection (method: find() )
```bash
db.oms_company_address.findOne()
```
### 5. Read all entries from oms_company_address collection (method: find() )
```bash
db.oms_company_address.find().pretty()
```
### 6. Update one entry from oms_company_address collection (method:update() or save()) 
```bash
db.oms_company_address.update({name: "shelby"}, {$set: {phone: "71454334", detail_address: "the little dark cold room" }})
```
### 7. Remove one entry from oms_company_address collection (method:remove())
```bash
// delete one
db.oms_company_address.deleteOne({name: "shelby"})

//delete all matches
db.oms_company_address.deleteMany({ name: "kitten" })
```
### 8. (Optional)You can also try to create other tables that listed above
