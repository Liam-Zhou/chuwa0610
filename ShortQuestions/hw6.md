# Homework 6

## Yirun Wang

### 1. Create oms_company_address table
```
CREATE DATABASE oms;
USE oms;
create table oms_company_address(
	id BIGINT,
    address_name VARCHAR(200),
    send_status TINYINT,
    receive_status TINYINT,
    name VARCHAR(64),
    phone VARCHAR(64),
    province VARCHAR(64),
    city varchar(64),
    region varchar(64),
    detial_address varchar(200),
    PRIMARY KEY (id)
);
```

### 2. Insert some random data to oms_company_address table
```
INSERT INTO oms_company_address (id, address_name, send_status, receive_status, name, phone, province, city, region, detail_address) VALUES
(1, 'Main Office', 1, 0, 'Alice', '1234567890', 'PA', 'City', 'Region1', '5th Ave'),
(2, 'East Office', 0, 1, 'Debbie', '2345678901', 'NJ', 'City2', 'Region2', '2nd Ave'),
(3, 'West Office', 1, 1, 'Cathy', '3456789012', 'CA', 'City3', 'Region3', 'E 14th St');
```

### 3. Write a SQL query to fetch all data from oms_company_address `table
```
SELECT * FROM oms_company_address;
```

### 4. Write a SQL query to fetch top 3 records from oms_company_address table 
```
SELECT * FROM oms_company_address LIMIT 3;
```

### 5. Update oms_company_address table to set all phone to 666-6666-8888
```
UPDATE oms_company_address SET phone = '666-6666-8888';
```

### 6. Delete one entry from oms_company_address table
```
DELETE FROM oms_company_address WHERE id = 1;
```

### 1. Create test DB
```
use test
```

### 2. Create oms_company_address collection (method: createCollection() )
```
db.createCollection("oms_company_address")
```

### 3. Insert few random entries to oms_company_address collection (method: insert() )
```
db.oms_company_address.insertMany([
    { id: 1, address_name: "Main Office", send_status: 1, receive_status: 0, name: "Alice", phone: "1234567890", province: "PA", city: "City1", region: "Region1", detail_address: "5th Ave" },
    { id: 2, address_name: "East Office", send_status: 0, receive_status: 1, name: "Debbie", phone: "2345678901", province: "NJ", city: "City2", region: "Region2", detail_address: "2nd Ave" },
    { id: 3, address_name: "West Office", send_status: 1, receive_status: 1, name: "Cathy", phone: "3456789012", province: "CA", city: "City3", region: "Region3", detail_address: "E 14th St" }
])
```
### 4. Read one entry from oms_company_address collection (method: find() )
```
db.oms_company_address.findOne({ id: 1 })
```

### 5. Read all entries from oms_company_address collection (method: find() )
```
db.oms_company_address.find()
```

### 6. Updateoneentryfromoms_company_addresscollection(method:update()orsave()) 
```
db.oms_company_address.updateOne(
    { id: 1 },
    { $set: { phone: "666-6666-8888" } }
)
```

### 7. Removeoneentryfromoms_company_addresscollection(method:remove())
```
db.oms_company_address.deleteOne({ id: 1 })
```

## API Design

### 1. Find the customer's payments, like credit card 1, credit card 2, paypal, Apple Pay
GET /api/v1/customers/{customer_id}/payments

### 2. Find the customer's history orders from 10/10/2022 to 10/24/2022
GET /api/v1/custoerms/{customer_id}/orders?startDate=10-10-2022&endDate=10-24-2022

### 3. Find the customer's delievery address
GET /api/v1/customers/{customer_id}/address

### 4. Also get customer's default payment and default delievery address
GET /api/v1/customers/{customer_id}/defaults

### 5. Find 2 collection of APIs example. ie, Twitter, Paypal, Youtube etc.
- Get User details
`GET https://api.twitter.com/2/users/{id}`
- Get User's Tweets
`GET https://api.twitter.com/2/users/{id}/tweets`
- Post a Tweet
`POST https://api.twitter.com/2/tweets`

- Create a payment
`POST https://api.paypal.com/v1/payments/payment`
- Get Payment details
`GET https://api.paypal.com/v1/payments/payment/{paymentId}`
- Create a Subscription
`POST https://api.paypal.com/v1/billing/subscriptions`

### 6. Design a collection of APIs for a Blog Website, specify GET POST PUT DELETE
- Get user's details
`GET /api/users/{user_id}`
- Create a new user
`POST /api/users`
put other info in the request body
- Update user
`PUT /api/users/{user_id}`
put the update info in request body
- Delete user
`DELETE /api/users/{user_id}`
- Get a post
`GET /api/posts/{post_id}`
- Get all posts
`GET /api/posts`
- Create a post
`POST /api/posts/`

- Get comments for a post
`GET /api/posts/{post_id}/comments`
- Create a comment
`POST /api/posts/{post_id}/comments`
new comment in the request body
- Update a comment
`PUT /api/comments/{comment_id}`
- Delete a comment
`DELETE /api/comments/{comment_id}`