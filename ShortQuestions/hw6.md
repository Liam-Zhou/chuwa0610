# HW6 MingHaoLee

# DB Practice SQL(MySql)/NoDSQL(Mongodb)
 - Refer to the coding folder: dbPractice for specific code

# API Design
## 1.Find the customer's payments, like credit card 1, credit card 2, paypal, Apple Pay.
 - `GET /api/v1/customer/{id}/payment`

## 2.Find the customer's history orders from 10/10/2022 to 10/24/2022
 - `GET /api/v1/customer/{id}/orders?start-date=10-10-2022&end-date=10-24-2022`

## 3.find the customer's delivery addresses
 - `GET /api/v1/customer/{id}/delivery-addresses`

## 4.If I also want to get customer's default payment and default delivery address, what kind of the API (URL) should be?
 - `GET /api/v1/customers/{Id}/defaults`
 - we could have a default info for user the endpoint retrieves the default payment method and delivery address for a customer.

## 5. Find 2 collection of APIs example. ie. Twitter, Paypal, Youtube etc. --
### Example 1: Twitter API

- **GET /statuses/user_timeline**: Retrieves a collection of the most recent tweets posted by the authenticated user.
- **POST /statuses/update**: Posts a new tweet.

### Example 2: PayPal API

- **POST /v1/payments/payment**: Creates a new payment.
- **GET /v1/payments/payment/{paymentId}**: Retrieves details of a specific payment.

## 6.Design a collection of APIs for a Blog Website, please specify GET POST PUT DELETE
 # Blog Website API

## Resources
- Users
- Posts
- Comments

### User

#### Create User
- **Method:** POST
- **Endpoint:** `/api/v1/user/{userId}`
- **Description:** Creates a new user.

#### Get All Users
- **Method:** GET
- **Endpoint:** `/api/v1/user`
- **Description:** Retrieves a list of all users.

#### Get a Single User
- **Method:** GET
- **Endpoint:** `/api/v1/user/{userId}`
- **Description:** Retrieves details of a single user.

#### Update a User
- **Method:** PUT
- **Endpoint:** `/api/v1/user/{userId}`
- **Description:** Updates the details of a specific user.

#### Delete a User
- **Method:** DELETE
- **Endpoint:** `/api/v1/user/{userId}`
- **Description:** Deletes a specific user.

### Post

#### Create a Post
- **Method:** POST
- **Endpoint:** `/api/v1/user/{userId}/posts`
- **Description:** Creates a new post for a specific user.

#### Get All Posts
- **Method:** GET
- **Endpoint:** `/api/v1/posts`
- **Description:** Retrieves a list of all posts.

#### Get All Posts by a User
- **Method:** GET
- **Endpoint:** `/api/v1/users/{userId}/posts`
- **Description:** Retrieves all posts created by a specific user.

#### Get a Single Post
- **Method:** GET
- **Endpoint:** `/api/v1/user/{userId}/posts/{postId}`
- **Description:** Retrieves details of a single post by a specific user.

#### Update a Single Post
- **Method:** PUT
- **Endpoint:** `/api/v1/user/{userId}/post/{postId}`
- **Description:** Updates the details of a specific post by a specific user.

#### Delete a Single Post
- **Method:** DELETE
- **Endpoint:** `/api/v1/user/{userId}/post/{postId}`
- **Description:** Deletes a specific post by a specific user.

### Comment

#### Create a Comment
- **Method:** POST
- **Endpoint:** `/api/v1/posts/{postId}/comments`
- **Description:** Creates a new comment on a specific post.

#### Get All Comments from a Post
- **Method:** GET
- **Endpoint:** `/api/v1/posts/{postId}/comments`
- **Description:** Retrieves all comments from a specific post.

#### Get a Single Comment from a Post
- **Method:** GET
- **Endpoint:** `/api/v1/posts/{postId}/comments/{commentId}`
- **Description:** Retrieves details of a single comment from a specific post.

#### Update a Comment
- **Method:** PUT
- **Endpoint:** `/api/v1/posts/{postId}/comments/{commentId}`
- **Description:** Updates the details of a specific comment on a specific post.

#### Delete a Comment
- **Method:** DELETE
- **Endpoint:** `/api/v1/posts/{postId}/comments/{commentId}`
- **Description:** Deletes a specific comment on a specific post.
