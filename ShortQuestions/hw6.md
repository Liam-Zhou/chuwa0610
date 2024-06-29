# HW6

## Exercise 1 SQL

- CREATE TABLE oms_company_addresss (
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

- INSERT INTO oms_company_address (id, address_name, send_status, receive_status, name, phone, province, city, region, detail_address) VALUES 
(1, 'First', 1, 1, 'Alicy', '111-111-1111, 'A', 'City A', 'a', 'Red Street'),
(2, 'Second', 1, 1, 'Bobby', '222-222-2222', 'B', 'City B', 'b', 'Blue Street'),
(3, 'Third', 1, 1, 'Charley', '333-333-3333', 'C', 'City C', 'c', 'Green Street'),
(4, 'Fourth', 1, 1, 'Doggy', '444-444-4444', 'D', 'City D', 'd', 'Yellow Stret');

- SELECT * FROM oms_company_address;

- SELECT * FROM oms_company_address ORDER BY id DESC LIMIT 3;

- UPDATE oms_company_address SET phone = '666-6666-8888';

- DELETE FROM oms_company_address WHERE id = 4;

## Exercise 2 NoSQL

- use test;

- db.createCollection("oms_company_address");

- db.oms_company_address.insertMany([
    {
        "address_name": "First",
        "send_status": 1,
        "receive_status": 1,
        "name": "Alice",
        "phone": "111-111-1111",
        "province": "A",
        "city": "a",
        "region": "aa",
        "detail_address": "Red Street"
    },
    {
        "address_name": "Second",
        "send_status": 1,
        "receive_status": 1,
        "name": "Bobby",
        "phone": "222-222-2222",
        "province": "B",
        "city": "b",
        "region": "bb",
        "detail_address": "Blue Street"
    },
    {
        "address_name": "Third",
        "send_status": 1,
        "receive_status": 1,
        "name": "Charlcy",
        "phone": "333-333-3333",
        "province": "C",
        "city": "c",
        "region": "cc",
        "detail_address": "Green Street"
    },
    {
        "address_name": "Fourth",
        "send_status": 1,
        "receive_status": 1,
        "name": "Doggy",
        "phone": "444-444-4444",
        "province": "D",
        "city": "d",
        "region": "dd",
        "detail_address": "Yellow Street"
    }
]);

- db.oms_company_address.findOne()

- db.oms_company_address.find().pretty()

- db.oms_company_address.updateOne( { name: "Doggy" }, { $set: { phone: "666-6666-8888" } } )

- db.oms_company_address.remove({ name: "Doggy" })


## API Design

### 1. Find the Customers' payments, like credit card 1, credit card 2, paypal and Apple Pay.
- GET /api/v1/customers/{customer_id}/payments

### 2. Find the Customers' history orders from 10/10/2022 to 10/24/2022.
- GET /api/v1/customers/{customer_id}/orders?start-date=2022-10-10&end-date=2022-10-24

### 3. Find the Customers' delivery addresses.

- GET /api/v1/customers/{customer_id}/addresses

### 4. If I also want to get Customers' default payment and default delivery address, what kind of the API(URL) should be?

- GET /api/v1/customers/{customer_id}/addresses/default
- GET /api/v1/customers/{customer_id}/payments/default

### 5. Find 2 collection of APIs example. ie. Twitter, Paypal, Youtube, etc -- 命名规范

- Twitter:
    - GET /2/tweets/{id}
    - POST /2/tweets
    - DELETE /2/tweets/{id}
    - GET /2/users/by/username/{username}
    - GET /2/users/{id}
    - GET /1.1/trends/available.json

- Paypal
    - POST /v1/payments/payment
    - GET /v1/payments/payment/{payment_id}
    - DELETE v1/invoicing/invoices/{invoice_id}
    - POST /v2/checkout/orders
    - GET /v2/checkout/orders/{order_id}

- Both of them use Nouns to represent resources
- HTTP Methods to define actions on resources
- Path Parameters for identifying specific instances of resources
- Consistent and predictable URL structures that make the API intuitive and easy to use

### 6. Design a collection of APIS for a Blog Website, please specify GET POST PUT DELETE
- Users:
    - Create new users
        - POST /users
    - Get user details
        - GET /users/{userId}
    - Update user details
        - PUT /users/{userId}
    - Delete a user
        - DELETE /users/{userId}

- Posts:
    - Create new post
        - POST /posts
    - Get posts
        - GET /posts/{postId}
    - Update post
        - PUT /posts/{postId}
    - Delete post
        - DELETE /posts/{postId}

- Comments:
    - Create new comment
        - POST /posts/{postId}/comments
    - Get comment
        - GET /comments/{commentId}
    - Update comment
        - PUT /comments/{commentId}
    - Delete comment
        - DELETE /comments/{commentId}