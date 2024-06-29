## API Design

### 1. Find the Customers' payments, like credit card 1, credit card 2, paypal and Apple Pay.
- multiples payment， 
- GET /api/v1/customers/{customer_id}/payments

### 2. Find the Customers' history orders from 10/10/2022 to 10/24/2022.
- has a lot or orders
- GET /api/v1/customers/{customer_id}/orders?start-date=2022-10-10&end-date=2022-10-24

### 3. Find the Customers' delivery addresses.
- GET /api/v1/customers/{customer_id}/addresses

### 4. If I also want to get Customers' default payment and default delivery address, what kind of the API(URL) should be?
- After the resource name, specify a specific - default
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
- HTTP Methods to define actions on resources(GET, UPDATE, DELETE, POST)
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