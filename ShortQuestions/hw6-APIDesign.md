## 1. Find the customer's payments, like credit card 1, credit 2, paypal, Apple Pay.  
`GET api/v1/customer-id/{id}/payments`

## 2. Find the customer's history orders from 10/10/2022 to 10/24/2022  
`GET api/v1/customer-id/{id}/orders?start-date=10/10/2022&end-date=10/24/2022`

## 3. Find the customer's delievery addresses  
`GET api/v1/customer-id/{id}/addresses`

## 4. If I also want to get customer's default payment and default delievery address, what kind of the API(URL) should be?  
`GET api/v1/customer-id/{id}/payments/default`  
`GET api/v1/customer-id/{id}/addresses/default`

## 5. Find 2 collection of APIs example. ie. Twitter, Paypal, Youtube etc.  
### **Twitter**
- Posts  
`GET statuses/show`  
`POST statuses/update`  
`GET statuses/retweeters/:ids`
- Users  
`GET statuses/retweets_of_me`  
`GET users/search`  
`GET friendships/show`  
### **Paypal**  
- Payment experience: `/v1/payment-experience/web-profiles`
- Create partner referral: `/v2/customer/partner-referrals`
- Show user profile details: `/v1/identity/openidconnect/userinfo`
- Show payout batch details: `/v1/payments/payouts/{id}`

## 6. Design a collection of APIs for a Blog Website
- Create user: `POST /api/v1/user/{id}`
- Update user info: `PUT /api/v1/user/{id}?name={new-name}`
- Get user profile: `GET /api/v1/user/{id}`
- Delete user: `DELETE /api/v1/user/{id}`
- Create a blog: `POST /api/v1/user/{id}/blog`
- Edit a blog `PATCH /api/v1/user/{id}/blog/{blog-id}`
- Read a blog `GET /api/v1/user/{id}/blog/{blog-id}`
- Delete a blog `DELETE /api/v1/user/{id}/blog/{blog-id}`
