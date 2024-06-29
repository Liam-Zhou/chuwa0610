## HW6

1. find the customer's payments, like credit card 1, credit card 2, paypal, Apple Pay.

   >GET /api/v1/customers/{customer_id}/payments

2. Find the customer's history orders from 10/10/2022 to 10/24/2022

   >GET /api/v1/customers/{customer_id}/orders?start-date=10-10-2022&end-date=10-24-2022

3. find the customer's delievery addresses

   >GET /api/v1/customers/{customer_id}/addresses

4. If I also want to get customer's default payment and default delievery address, what kind of the API (URL) should be?

   >GET /api/v1/customers/{customer_id}/payments/default
   >
   >GET /api/v1/customers/{customer_id}/addresses/default

5. Find 2 collection of APIs example. ie. Twitter, Paypal, Youtube etc. -- 命名规范

   >Twitter:
   >
   >https://developer.x.com/en/docs/api-reference-index
   >
   >Paypal:
   >
   >https://developer.paypal.com/api/rest/

6. Design a collection of APIs for a Blog Website, please specify GET POST PUT DELETE

   >Base URL: https://example.com/api/v1/ 
   >
   >1. Blog Posts
   >
   >   >GET /posts
   >   >
   >   >POST /posts
   >   >
   >   >GET /posts/{postId}
   >   >
   >   >PUT /posts/{postId}
   >   >
   >   >DELETE /posts/{postId}
   >
   >2. Comments
   >
   >   >GET /posts/{postId}/comments
   >   >
   >   >POST /posts/{postId}/comments
   >   >
   >   >PUT /posts/{postId}/comments/{commentId}
   >   >
   >   >DELETE /posts/{postId}/comments/{commentId}
   >
   >3. Users
   >
   >   >GET /users
   >   >
   >   >POST /users
   >   >
   >   >GET /users/{userId}
   >   >
   >   >PUT /users/{userId}
   >   >
   >   >DELETE /users/{userId}