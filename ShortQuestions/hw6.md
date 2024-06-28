### Find the customer's payments
   GET /api/customers/{customerId}/payments

### Find the customer's history orders
   GET /api/customers/{customerId}/orders?startDate=2022-10-10&endDate=2022-10-24

### Find the customer's delivery addresses
   GET /api/customers/{customerId}/addresses

### Get customer's default payment and default delivery address
   GET /api/customers/{customerId}/defaults

### Examples of API collections: Twitter, PayPal

1. Twitter:

Read, write and access Direct Messages
This permission level includes access to all of the above and adds the ability to read, write and delete Direct Messages on behalf of a user.

``` 
POST /2/dm_conversations/:dm_conversation_id/messages
POST /2/dm_conversations/
POST /2/dm_conversations/with/:participant_id/messages
GET /2/dm_conversations/with/:user_id/dm_events
GET /2/dm_conversations/:dm_conversation_id/dm_events
GET /2/dm_events
```
2. Youtube

Comments: A comment resource contains information about a single YouTube comment. A comment resource can represent a comment about either a video or a channel. In addition, the comment could be a top-level comment or a reply to a top-level comment.

**Base URI:** `https://www.googleapis.com/youtube/v3`

| Method                | HTTP Request                              | Description                                                                                                 |
|-----------------------|-------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| list                  | `GET /comments`                           | Returns a list of comments that match the API request parameters.                                           |
| setModerationStatus   | `POST /comments/setModerationStatus`      | Sets the moderation status of one or more comments. The API request must be authorized by the owner of the channel or video associated with the comments. |
| insert                | `POST /comments`                          | Creates a reply to an existing comment. Note: To create a top-level comment, use the commentThreads.insert method. |
| markAsSpam            | `POST /comments/markAsSpam`               | Note: This method has been deprecated and is no longer supported.                                           |
| delete                | `DELETE /comments`                        | Deletes a comment.                                                                                          |
| update                | `PUT /comments`                           | Modifies a comment.                                                                                         |


``` 

```
### Blog Website API collection:
    - Posts
      GET /api/posts
      GET /api/posts/{postId}
      POST /api/posts
      PUT /api/posts/{postId}
      DELETE /api/posts/{postId}

    - Comments
      GET /api/posts/{postId}/comments
      POST /api/posts/{postId}/comments
      PUT /api/comments/{commentId}
      DELETE /api/comments/{commentId}

    - Users
      GET /api/users/{userId}
      PUT /api/users/{userId}
      DELETE /api/users/{userId}
