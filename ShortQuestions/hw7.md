## Rest API
### Post Practice
### 1. Practice with below examples (You can find any Open APIs on the internet):
- 5 GET APIs with different response type
```bash 
// inspect the responses type
curl https://jsonplaceholder.typicode.com/posts/1

// JSON response:
GET https://jsonplaceholder.typicode.com/posts/1 

{
  "userId": 1,
  "id": 1,
  "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
  "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
}

// XML response:
GET https://earthquake.usgs.gov/fdsnws/event/1/query?format=xml&starttime=2021-01-01&endtime=2021-01-02

<feed xmlns="http://www.w3.org/2005/Atom" xmlns:geo="http://www.w3.org/2003/01/geo/wgs84_pos#" xmlns:georss="http://www.georss.org/georss" xmlns:gml="http://www.opengis.net/gml">
  <title>USGS Earthquakes</title>
  <entry>
    <id>urn:earthquake-usgs-gov:us2021abcd</id>
    <title>M 4.5 - 10km NW of Kerman, California</title>
    <updated>2021-01-01T12:34:56Z</updated>
    <link href="https://earthquake.usgs.gov/earthquakes/eventpage/us2021abcd"/>
    <summary type="html">
      <![CDATA[
      <strong>Magnitude:</strong> 4.5<br/>
      <strong>Location:</strong> 10km NW of Kerman, California<br/>
      <strong>Time:</strong> 2021-01-01T12:34:56Z
      ]]>
    </summary>
    <georss:point>36.745 119.705</georss:point>
    <georss:elev>-5000</georss:elev>
    <category term="earthquake"/>
  </entry>
</feed>

// Plain Text Response
GET https://baconipsum.com/api/?type=meat-and-filler&paras=1&format=text

// HTML response:
GET https://www.example.com/

// csv repsonse


```
- 5 Post API with json request body, please also paste the response here 
```bash
PUT https://jsonplaceholder.typicode.com/posts
Request type: JSON
Request Body: 
{
  "title": "foo",
  "body": "bar",
  "userId": 1
}
Response: 
{
  "title": "foo",
  "body": "bar",
  "userId": 1,
  "id": 101
}
```
- 3 PUT API with json request body, please also paste the response here
```bash
PUT https://fakestoreapi.com/products/7
Request Body: 
{
  "title": "updated product",
  "price": 15.5,
  "description": "updated description",
  "image": "https://i.pravatar.cc",
  "category": "updated category"
}

response:
{
  "id": 7,
  "title": "updated product",
  "price": 15.5,
  "description": "updated description",
  "image": "https://i.pravatar.cc",
  "category": "updated category"
}

```
- 2 DELETE API
```bash
DELETE https://jsonplaceholder.typicode.com/posts/1
```
- Each example with 404, 401,500 and any http status codes you know
```bash
404: Not Found
401: Unauthorized
500: Internal Server Error
```
