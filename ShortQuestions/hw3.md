# HW30

## Exercise1 - MYSQL
1. Create oms_company_address table
2. Insert some random data to oms_company_address table
3. Write a SQL query to fetch all data from oms_company_address `table
4. Write a SQL query to fetch top 3 records from oms_company_address table
5. Update oms_company_address table to set all phone to 666-6666-8888
6. Delete one entry from oms_company_address table
7. (Optional)Youcanalsotrytocreateothertablesthatlistedabove

```dockerfile
docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=esther -d mysql:latest
docker exec -it some-mysql mysql -uroot -p
```

```dockerfile
# q1
mysql> CREATE DATABASE test;
Query OK, 1 row affected (0.02 sec)

mysql> use test
    Database changed
mysql> CREATE TABLE oms_company_address(id BIGINT AUTO_INCREMENT PRIMARY KEY, address_name VARCHAR(200), send_status INT(1), receive_status INT(1), name VARCHAR(64), phone VARCHAR(64), province VARCHAR(64), city VARCHAR(64), region VARCHAR(64), detail_address VARCHAR(200));
Query OK, 0 rows affected, 2 warnings (0.02 sec)

# q2
mysql> INSERT INTO oms_company_address (address_name, send_status, receive_status, name, phone, province, city, region, detail_address) VALUES ("addr1","0","0","anchor","123456789","CA","SJ","SJ",""),("addr2","1","0","bob","223252789","CA","SV","SV","addr2"),("addr3","1","1","charlos","333433789","CA","SC","SC","");
Query OK, 3 rows affected (0.03 sec)
Records: 3  Duplicates: 0  Warnings: 0

#q3
mysql> SELECT * FROM oms_company_address
    -> ;
+----+--------------+-------------+----------------+---------+-----------+----------+------+--------+----------------+
| id | address_name | send_status | receive_status | name    | phone     | province | city | region | detail_address |
+----+--------------+-------------+----------------+---------+-----------+----------+------+--------+----------------+
|  1 | addr1        |           0 |              0 | anchor  | 123456789 | CA       | SJ   | SJ     |                |
|  2 | addr2        |           1 |              0 | bob     | 223252789 | CA       | SV   | SV     | addr2          |
|  3 | addr3        |           1 |              1 | charlos | 333433789 | CA       | SC   | SC     |                |
+----+--------------+-------------+----------------+---------+-----------+----------+------+--------+----------------+
3 rows in set (0.01 sec)

#q4
mysql> SELECT * FROM oms_company_address ORDER BY id ASC LIMIT 3;
+----+--------------+-------------+----------------+---------+-----------+----------+------+--------+----------------+
| id | address_name | send_status | receive_status | name    | phone     | province | city | region | detail_address |
+----+--------------+-------------+----------------+---------+-----------+----------+------+--------+----------------+
|  1 | addr1        |           0 |              0 | anchor  | 123456789 | CA       | SJ   | SJ     |                |
|  2 | addr2        |           1 |              0 | bob     | 223252789 | CA       | SV   | SV     | addr2          |
|  3 | addr3        |           1 |              1 | charlos | 333433789 | CA       | SC   | SC     |                |
+----+--------------+-------------+----------------+---------+-----------+----------+------+--------+----------------+
3 rows in set (0.01 sec)

#q5
mysql> UPDATE oms_company_address SET phone = "666-6666-8888";
Query OK, 3 rows affected (0.01 sec)
Rows matched: 3  Changed: 3  Warnings: 0

mysql> SELECT * FROM oms_company_address;
+----+--------------+-------------+----------------+---------+---------------+----------+------+--------+----------------+
| id | address_name | send_status | receive_status | name    | phone         | province | city | region | detail_address |
+----+--------------+-------------+----------------+---------+---------------+----------+------+--------+----------------+
|  1 | addr1        |           0 |              0 | anchor  | 666-6666-8888 | CA       | SJ   | SJ     |                |
|  2 | addr2        |           1 |              0 | bob     | 666-6666-8888 | CA       | SV   | SV     | addr2          |
|  3 | addr3        |           1 |              1 | charlos | 666-6666-8888 | CA       | SC   | SC     |                |
+----+--------------+-------------+----------------+---------+---------------+----------+------+--------+----------------+
3 rows in set (0.00 sec)

#q6mysql> DELETE FROM oms_company_address WHERE id=1;
Query OK, 1 row affected (0.01 sec)

mysql> SELECT * FROM oms_company_address;
+----+--------------+-------------+----------------+---------+---------------+----------+------+--------+----------------+
| id | address_name | send_status | receive_status | name    | phone         | province | city | region | detail_address |
+----+--------------+-------------+----------------+---------+---------------+----------+------+--------+----------------+
|  2 | addr2        |           1 |              0 | bob     | 666-6666-8888 | CA       | SV   | SV     | addr2          |
|  3 | addr3        |           1 |              1 | charlos | 666-6666-8888 | CA       | SC   | SC     |                |
+----+--------------+-------------+----------------+---------+---------------+----------+------+--------+----------------+
2 rows in set (0.00 sec)
```

```dockerfile
mysql> exit
Bye
docker stop some-mysql
```

## Exercise2 - MongoDB
1. Create test DB
2. Create oms_company_address collection (method: createCollection() )
3. Insert few random entries to oms_company_address collection (method: insert() )
4. Read one entry from oms_company_address collection (method: find() )
5. Read all entries from oms_company_address collection (method: find() )
6. Update one entry from oms_company_address collection(method:update() or save()) 
7. Remove one entry from oms_company_address collection(method:remove())
8. (Optional)Youcanalsotrytocreateothertablesthatlistedabove

```dockerfile
docker pull mongo
docker run --name some-mongo  -e MONGO_ROOT_PASSWORD=esther -d mongo:latest
docker exec -it some-mongo mongosh
```

```dockerfile
#q1
test> use test
already on db test

#q2
test> db.createCollection("oms_company_address")
{ ok: 1 }

#q3
test> db.oms_company_address.insertMany([{address_name:"addr1",send_status:"0",receive_status:"0",name:"anchor",phone:"123-4567-8901",province:"CA",city:"SJ"},{address_name:"addr2",send_status:"1",receive_status:"0",name:"bill",phone:"222-4267-8221",province:"CA",city:"SV"},{address_name:"addr3",send_status:"1",receive_status:"1",name:"challote",phone:"333-3337-8331",province:"CA",city:"SC"}])
{
  acknowledged: true,
  insertedIds: {
    '0': ObjectId('667f03b88eb17645c18db5fc'),
    '1': ObjectId('667f03b88eb17645c18db5fd'),
    '2': ObjectId('667f03b88eb17645c18db5fe')
  }
}

#q4
test> db.oms_company_address.findOne()
{
  _id: ObjectId('667effe48eb17645c18db5fb'),
  address_name: 'addr1',
  send_status: '0',
  receive_status: '0',
  name: 'anchor',
  phone: '123-4567-8901',
  province: 'CA',
  city: 'SJ'
}

#q5
test> db.oms_company_address.find().pretty()
[
  {
    _id: ObjectId('667effe48eb17645c18db5fb'),
    address_name: 'addr1',
    send_status: '0',
    receive_status: '0',
    name: 'anchor',
    phone: '123-4567-8901',
    province: 'CA',
    city: 'SJ'
  },
  {
    _id: ObjectId('667f03b88eb17645c18db5fc'),
    address_name: 'addr1',
    send_status: '0',
    receive_status: '0',
    name: 'anchor',
    phone: '123-4567-8901',
    province: 'CA',
    city: 'SJ'
  },
  {
    _id: ObjectId('667f03b88eb17645c18db5fd'),
    address_name: 'addr2',
    send_status: '1',
    receive_status: '0',
    name: 'bill',
    phone: '222-4267-8221',
    province: 'CA',
    city: 'SV'
  },
  {
    _id: ObjectId('667f03b88eb17645c18db5fe'),
    address_name: 'addr3',
    send_status: '1',
    receive_status: '1',
    name: 'challote',
    phone: '333-3337-8331',
    province: 'CA',
    city: 'SC'
  }
]

#q6
test> db.oms_company_address.updateOne({address_name:'addr1'},{ $set: { phone: "666-666-8888" }})
{
  acknowledged: true,
  insertedId: null,
  matchedCount: 1,
  modifiedCount: 0,
  upsertedCount: 0
}
test> db.oms_company_address.find().pretty()
[
  {
    _id: ObjectId('667effe48eb17645c18db5fb'),
    address_name: 'addr1',
    send_status: '0',
    receive_status: '0',
    name: 'anchor',
    phone: '666-666-8888',
    province: 'CA',
    city: 'SJ'
  },
  {
    _id: ObjectId('667f03b88eb17645c18db5fc'),
    address_name: 'addr1',
    send_status: '0',
    receive_status: '0',
    name: 'anchor',
    phone: '123-4567-8901',
    province: 'CA',
    city: 'SJ'
  },
  {
    _id: ObjectId('667f03b88eb17645c18db5fd'),
    address_name: 'addr2',
    send_status: '1',
    receive_status: '0',
    name: 'bill',
    phone: '222-4267-8221',
    province: 'CA',
    city: 'SV'
  },
  {
    _id: ObjectId('667f03b88eb17645c18db5fe'),
    address_name: 'addr3',
    send_status: '1',
    receive_status: '1',
    name: 'challote',
    phone: '333-3337-8331',
    province: 'CA',
    city: 'SC'
  }
]

#q7
test> db.oms_company_address.deleteOne({ address_name: "addr2" })
{ acknowledged: true, deletedCount: 1 }
test> db.oms_company_address.find().pretty()
[
  {
    _id: ObjectId('667effe48eb17645c18db5fb'),
    address_name: 'addr1',
    send_status: '0',
    receive_status: '0',
    name: 'anchor',
    phone: '666-666-8888',
    province: 'CA',
    city: 'SJ'
  },
  {
    _id: ObjectId('667f03b88eb17645c18db5fc'),
    address_name: 'addr1',
    send_status: '0',
    receive_status: '0',
    name: 'anchor',
    phone: '123-4567-8901',
    province: 'CA',
    city: 'SJ'
  },
  {
    _id: ObjectId('667f03b88eb17645c18db5fe'),
    address_name: 'addr3',
    send_status: '1',
    receive_status: '1',
    name: 'challote',
    phone: '333-3337-8331',
    province: 'CA',
    city: 'SC'
  }
]
```
```dockerfile
test> exit

What's next:
    Try Docker Debug for seamless, persistent debugging tools in any container or image → docker debug some-mongo
    Learn more at https://docs.docker.com/go/debug-cli/
docker stop some-mongo
```

## API Design
1. find the customer's payments, like credit card 1, credit card 2, PayPal, Apple Pay.
   `GET /api/v1/customers/{customer-id}/payment-methods`
2. Find the customer's history orders from 10/10/2022 to 10/24/2022
   `GET /api/v1/customers/{customer-id}/orders?startDate=2022-10-10&endDate=2022-10-24`
3. find the customer's delivery addresses
   `GET /api/v1/customers/{customer-id}/delivery-addresses`
4. If I also want to get customer's default payment and default delivery address, what kind of the API (URL)
   should be?
   `GET /api/v1/customers/{customer-id}/payment-methods/defaults`
   `GET /api/v1/customers/{customer-id}/delivery-addresses/defaults`
5. Find two collections of APIs examples. i.e. Twitter, PayPal, YouTube etc. - m 2#,
   
   Twitter

   | Description                       | Endpoint                    | Data collected                                                                                |
   |-----------------------------------|-----------------------------|-----------------------------------------------------------------------------------------------|
   | Real-time stream of tweets        | GET /2/tweets/sample/stream | 1% random sample of publicly available tweets in real time.                                   |
   |                                   | GET /2/tweets/search/stream | Filtered real-time stream of publicly available tweets.                                       |
   | Historical tweets                 | GET /2/tweets/search/recent | Tweets posted in the last 7 days.                                                             |
   |                                   | GET /2/tweets/search/all    | Historical tweets posted anytime since 2006 (currently only available for Academic Research). |
   | Volume of tweets                  | GET /2/tweets/counts/recent | Volume of tweet data for a search query.                                                      |
   | Tweets lookup                     | GET /2/tweets/:id           | Up-to-date information for tweets with specific identifiers.                                  |
   | Tweets created by a specific user | GET /2/users/:id/tweets     | Tweets posted by a specific user.                                                             |
   | Tweets mentioning a specific user | GET /2/users/:id/mentions   | Tweets mentioning a specific user.                                                            |
   | Users lookup                      | GET /2/users                | Up-to-date information about specific users.                                                  |
6. Design a collection of APis for a Blog Website, please specify GET POST PUT DELETE. Design APIs for the following features（思考：path variable 怎么用？有sub resources，哪些地方该用复数）
   
   | Description         | Endpoint                   |
   |---------------------|----------------------------|
   | List all blogs      | GET /v1/blogs              |
   | Create a new blog   | POST /v1/blogs             |
   | Get a specific blog | GET /v1/blogs/{blog-id}    |
   | Update a blog       | PUT /v1/blogs/{blog-id}    |
   | Delete a blog       | DELETE /v1/blogs/{blog-id} |

   | Description                 | Endpoint                                   |
   |-----------------------------|--------------------------------------------|
   | List all posts in a blog    | GET /v1/blogs/{blog-id}/posts              |
   | Create a new post in a blog | POST /v1/blogs/{blog-id}/posts             |
   | Get a specific post         | GET /v1/blogs/{blog-id}/posts/{post-id}    |
   | Update a post               | PUT /v1/blogs/{blog-id}/posts/{post-id}    |
   | Delete a post               | DELETE /v1/blogs/{blog-id}/posts/{post-id} |


   | Description                 | Endpoint                                                         |
   |-----------------------------|------------------------------------------------------------------|
   | List all comments on a post | GET /v1/blogs/{blog-id}/posts/{post-id}/comments                 |
   | Add a comment to a post     | POST /v1/blogs/{blog-id}/posts/{post-id}/comments                |
   | Delete a comment            | DELETE /v1/blogs/{blog-id}/posts/{post-id}/comments/{comment-id} |