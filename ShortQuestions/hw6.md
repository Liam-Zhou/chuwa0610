## 1. Find the customer's payments, like credit card 1, credit 2, paypal, Apple Pay.
`GET api.example.com/v1/customer/{id}/payments`

## 2. Find the customer's history orders from 10/10/2022 to 10/24/2022
`GET api.example.com/v1/customer/{id}/order?from=10/10/2022&to=10/24/2022`

## 3. Find the customer's delievery addresses
`GET api.example.com/v1/customer/{id}/addresses`

## 4. If I also want to get customer's default payment and default delievery address, what kind of the API(URL) should be?
`GET api.example.com/v1/cutomer/{id}/patment`
`GET api.example.com/v1/cutomer/{id}/address`

## 5. Find 2 collection of APIs example. ie. Twitter, Paypal, Youtube etc.
### X API:
Searching tweets :`https://api.twitter.com/2/tweets/search/recent?query=from:XDevelopers&tweet.fields=created_at&expansions=author_id&user.fields=created_at`

Likes: `GET /2/users/:id/liked_tweets`

### Youtube API:
Activities:
`GET https://www.googleapis.com/youtube/v3/activities`
Captions:
`GET https://www.googleapis.com/youtube/v3/captions`
comments:
`POST https://www.googleapis.com/youtube/v3/comments`

## 6. Design a collection of APIs for a Blog Website
### Blogs
`GET api.example.com/v1/blogs`

`POST api.example.com/v1/blogs`

`GET api.example.com/v1/blogs/{blog_id}`

`PUT api.example.com/v1/blogs/{blog_id}`

### User
`GET api.example.com/v1/users`

`POST api.example.com/v1/users`

`GET api.example.com/v1/users/{user_id}`

`PUT api.example.com/v1/users/{user_id}`

### Search 
`GET api.example.com/v1/search/blogs?q={query}`

`GET api.example.com/v1/search/user?q={query}`

### Auth
`POST api.example.com/v1/auth/login`

`POST api.example.com/v1/auth/logout`

`POST api.example.com/v1/auth/register`


