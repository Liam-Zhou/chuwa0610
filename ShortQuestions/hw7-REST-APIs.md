## 5 GET APIs with different response type
1. `GET https://catfact.ninja/fact`  
Response:  
status: 200  
{
    "fact": "Phoenician cargo ships are thought to have brought the first domesticated cats to Europe in about 900 BC.",
    "length": 105
}
2. `GET https://api.coindesk.com/v1/bpi/currentprice.json`
Response:  
status: 403  
Bad request.
We can't connect to the server for this app or website at this time. There might be too much traffic or a configuration error. Try again later, or contact the app or website owner.

3. `GET https://catfact.ninja/fact1`  
Response:  
status: 404  
{"message":"Not Found","code":404}
4. `https://api.github.com/user`
Response:
status: 401
{"message":"Requires authentication","documentation_url":"https://docs.github.com/rest/users/users#get-the-authenticated-user","status":"401"}
5. `GET https://httpstat.us/500`  
Response:  
status: 500  
500 Internal Server Error

## 5 Post API with json request body, please also paste the response here
1. `POST https://httpbin.org/status/418`
Response:  
status: 418  

    -=[ teapot ]=-

       _...._
     .'  _ _ `.
    | ."` ^ `". _,
    \_;`"---"`|//
      |       ;/
      \_     _/
        `"""`
2. `POST httpbin.org/`  
Response:  
status: 405
```<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<title>405 Method Not Allowed</title>
<h1>Method Not Allowed</h1>
<p>The method is not allowed for the requested URL.</p>
```

3. `POST httpbin.org/post?key=1`
Response:  
RequestBody: key=1  
status: 200  
{
    "args": {
        "key": "1"
    },
    "data": "{\n    \"note_id\": \"3\",\n    \"new_content\": \"lots of notes\"\n}",
    "files": {},
    "form": {},
    "headers": {
        "Accept": "*/*",
        "Accept-Encoding": "gzip, deflate, br",
        "Authorization": "Basic Og==",
        "Content-Length": "58",
        "Content-Type": "application/json",
        "Host": "httpbin.org",
        "Postman-Token": "6bf19885-a67a-47a7-aa50-3cf720a196c8",
        "User-Agent": "PostmanRuntime/7.39.0",
        "X-Amzn-Trace-Id": "Root=1-66831fb2-2601448338e7c1342cf4d03f"
    },
    "json": {
        "new_content": "lots of notes",
        "note_id": "3"
    },
    "origin": "76.14.113.130",
    "url": "http://httpbin.org/post?key=1"
}

4. `POST httpbin.org/status/404?name=tom`
Response:  
status: 404  

5. `POST httpbin.org/status/401`
Response:  
status: 401  
{"message":"Requires authentication","status":"401"}

## 3 PUT API with json request body, please also paste the response here
1. `PUT httpbin.org/put?name=Tom`  
Response:  
status: 200  
{
    "args": {
        "name": "Tom"
    },
    "data": "{\n    \"note_id\": \"3\",\n    \"new_content\": \"lots of notes\"\n}",
    "files": {},
    "form": {},
    "headers": {
        "Accept": "*/*",
        "Accept-Encoding": "gzip, deflate, br",
        "Authorization": "Basic Og==",
        "Content-Length": "58",
        "Content-Type": "application/json",
        "Host": "httpbin.org",
        "Postman-Token": "7bd2922f-506c-407c-b992-c1c32a91cc8e",
        "User-Agent": "PostmanRuntime/7.39.0",
        "X-Amzn-Trace-Id": "Root=1-66832246-396e195d7a06c2e11149495f"
    },
    "json": {
        "new_content": "lots of notes",
        "note_id": "3"
    },
    "origin": "76.14.113.130",
    "url": "http://httpbin.org/put?name=Tom"
}
2. `PUT httpbin.org/status`
Response:  
status: 404  
```
<title>404 Not Found</title>
<h1>Not Found</h1>
<p>The requested URL was not found on the server. If you entered the URL manually please check your spelling and try
    again.</p>
```
3. `PUT httpbin.org/anything`
Request body:  
{
    "note_id": "3",
    "new_content": "lots of notes"
}
status code: 200  
{
    "args": {},
    "data": "{\n    \"note_id\": \"3\",\n    \"new_content\": \"lots of notes\"\n}",
    "files": {},
    "form": {},
    "headers": {
        "Accept": "*/*",
        "Accept-Encoding": "gzip, deflate, br",
        "Authorization": "Basic Og==",
        "Content-Length": "58",
        "Content-Type": "application/json",
        "Host": "httpbin.org",
        "Postman-Token": "4efb3808-8d07-4348-be96-651dc23bf864",
        "User-Agent": "PostmanRuntime/7.39.0",
        "X-Amzn-Trace-Id": "Root=1-66832374-1f51df6178792f7c75c2d994"
    },
    "json": {
        "new_content": "lots of notes",
        "note_id": "3"
    },
    "method": "PUT",
    "origin": "76.14.113.130",
    "url": "http://httpbin.org/anything"
}

## 2 DELETE API
1. `DELETE httpbin.org/delete`
Request Body:  
{
   "id": "3"
}
status: 200  
{
    "args": {},
    "data": "{\n    \"id\": \"3\"\n}",
    "files": {},
    "form": {},
    "headers": {
        "Accept": "*/*",
        "Accept-Encoding": "gzip, deflate, br",
        "Authorization": "Basic Og==",
        "Content-Length": "17",
        "Content-Type": "application/json",
        "Host": "httpbin.org",
        "Postman-Token": "04b5852d-3ac0-4ad0-8dc7-c586239ba06c",
        "User-Agent": "PostmanRuntime/7.39.0",
        "X-Amzn-Trace-Id": "Root=1-668323da-10dda8a9766e43122228f91c"
    },
    "json": {
        "id": "3"
    },
    "origin": "76.14.113.130",
    "url": "http://httpbin.org/delete"
}
2. `DELETE httpbin.org/status`  
Request body:  
{
    "name": "Jack"
}
Response:  
status: 404  
```
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<title>404 Not Found</title>
<h1>Not Found</h1>
<p>The requested URL was not found on the server. If you entered the URL manually please check your spelling and try
    again.</p>
```