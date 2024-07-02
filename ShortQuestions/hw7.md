## HW7

1. 5 GET APIs with different response type 

   >```
   >https://dog.ceo/api/breeds/image/random
   >
   >{
   >
   >​    "message": "https://images.dog.ceo/breeds/pointer-germanlonghair/hans3.jpg",
   >
   >​    "status": "success"
   >
   >}
   >```
   >
   >```
   >https://restcountries.com/v3.1/name/france
   >[
   >    {
   >        "name": {
   >            "common": "France",
   >            "official": "French Republic",
   >            "nativeName": {
   >                "fra": {
   >                    "official": "République française",
   >                    "common": "France"
   >                }
   >            }
   >        },
   >        "tld": [
   >            ".fr"
   >        ],
   >        "cca2": "FR",
   >        "ccn3": "250",
   >        "cca3": "FRA",
   >        "cioc": "FRA",
   >        "independent": true,
   >        "status": "officially-assigned",
   >        "unMember": true,
   >        "currencies": {
   >            "EUR": {
   >                "name": "Euro",
   >                "symbol": "€"
   >            }
   >        },
   >        "idd": {
   >            "root": "+3",
   >            "suffixes": [
   >                "3"
   >            ]
   >        },
   >        "capital": [
   >            "Paris"
   >        ],
   >        "altSpellings": [
   >            "FR",
   >            "French Republic",
   >            "République française"
   >        ],
   >        "region": "Europe",
   >        "subregion": "Western Europe",
   >        "languages": {
   >            "fra": "French"
   >        },
   >        "translations": {
   >            "ara": {
   >                "official": "الجمهورية الفرنسية",
   >                "common": "فرنسا"
   >            },
   >            "bre": {
   >                "official": "Republik Frañs",
   >                "common": "Frañs"
   >            },
   >            "ces": {
   >                "official": "Francouzská republika",
   >                "common": "Francie"
   >            },
   >            "cym": {
   >                "official": "French Republic",
   >                "common": "France"
   >            },
   >            "deu": {
   >                "official": "Französische Republik",
   >                "common": "Frankreich"
   >            },
   >            "est": {
   >                "official": "Prantsuse Vabariik",
   >                "common": "Prantsusmaa"
   >            },
   >            "fin": {
   >                "official": "Ranskan tasavalta",
   >                "common": "Ranska"
   >            },
   >            "fra": {
   >                "official": "République française",
   >                "common": "France"
   >            },
   >            "hrv": {
   >                "official": "Francuska Republika",
   >                "common": "Francuska"
   >            },
   >            "hun": {
   >                "official": "Francia Köztársaság",
   >                "common": "Franciaország"
   >            },
   >            "ita": {
   >                "official": "Repubblica francese",
   >                "common": "Francia"
   >            },
   >            "jpn": {
   >                "official": "フランス共和国",
   >                "common": "フランス"
   >            },
   >            "kor": {
   >                "official": "프랑스 공화국",
   >                "common": "프랑스"
   >            },
   >            "nld": {
   >                "official": "Franse Republiek",
   >                "common": "Frankrijk"
   >            },
   >            "per": {
   >                "official": "جمهوری فرانسه",
   >                "common": "فرانسه"
   >            },
   >            "pol": {
   >                "official": "Republika Francuska",
   >                "common": "Francja"
   >            },
   >            "por": {
   >                "official": "República Francesa",
   >                "common": "França"
   >            },
   >            "rus": {
   >                "official": "Французская Республика",
   >                "common": "Франция"
   >            },
   >            "slk": {
   >                "official": "Francúzska republika",
   >                "common": "Francúzsko"
   >            },
   >            "spa": {
   >                "official": "República francés",
   >                "common": "Francia"
   >            },
   >            "srp": {
   >                "official": "Француска Република",
   >                "common": "Француска"
   >            },
   >            "swe": {
   >                "official": "Republiken Frankrike",
   >                "common": "Frankrike"
   >            },
   >            "tur": {
   >                "official": "Fransa Cumhuriyeti",
   >                "common": "Fransa"
   >            },
   >            "urd": {
   >                "official": "جمہوریہ فرانس",
   >                "common": "فرانس"
   >            },
   >            "zho": {
   >                "official": "法兰西共和国",
   >                "common": "法国"
   >            }
   >        },
   >        "latlng": [
   >            46.0,
   >            2.0
   >        ],
   >        "landlocked": false,
   >        "borders": [
   >            "AND",
   >            "BEL",
   >            "DEU",
   >            "ITA",
   >            "LUX",
   >            "MCO",
   >            "ESP",
   >            "CHE"
   >        ],
   >        "area": 551695.0,
   >        "demonyms": {
   >            "eng": {
   >                "f": "French",
   >                "m": "French"
   >            },
   >            "fra": {
   >                "f": "Française",
   >                "m": "Français"
   >            }
   >        },
   >        "flag": "🇫🇷",
   >        "maps": {
   >            "googleMaps": "https://goo.gl/maps/g7QxxSFsWyTPKuzd7",
   >            "openStreetMaps": "https://www.openstreetmap.org/relation/1403916"
   >        },
   >        "population": 67391582,
   >        "gini": {
   >            "2018": 32.4
   >        },
   >        "fifa": "FRA",
   >        "car": {
   >            "signs": [
   >                "F"
   >            ],
   >            "side": "right"
   >        },
   >        "timezones": [
   >            "UTC-10:00",
   >            "UTC-09:30",
   >            "UTC-09:00",
   >            "UTC-08:00",
   >            "UTC-04:00",
   >            "UTC-03:00",
   >            "UTC+01:00",
   >            "UTC+02:00",
   >            "UTC+03:00",
   >            "UTC+04:00",
   >            "UTC+05:00",
   >            "UTC+10:00",
   >            "UTC+11:00",
   >            "UTC+12:00"
   >        ],
   >        "continents": [
   >            "Europe"
   >        ],
   >        "flags": {
   >            "png": "https://flagcdn.com/w320/fr.png",
   >            "svg": "https://flagcdn.com/fr.svg",
   >            "alt": "The flag of France is composed of three equal vertical bands of blue, white and red."
   >        },
   >        "coatOfArms": {
   >            "png": "https://mainfacts.com/media/images/coats_of_arms/fr.png",
   >            "svg": "https://mainfacts.com/media/images/coats_of_arms/fr.svg"
   >        },
   >        "startOfWeek": "monday",
   >        "capitalInfo": {
   >            "latlng": [
   >                48.87,
   >                2.33
   >            ]
   >        },
   >        "postalCode": {
   >            "format": "#####",
   >            "regex": "^(\\d{5})$"
   >        }
   >    }
   >]
   >```

2. 5 Post API with json request body, please also paste the response here 

   >```
   >{{subjectUrl}}/subject/category/queryCategoryByPrimary
   >{
   >	"parentId": 1,
   >	"categoryType":2
   >}
   >```
   >
   >```
   >{{subjectUrl}}/subject/category/queryPrimaryCategory
   >{
   >    "categoryType": 1
   >}
   >```

3. 3 PUT API with json request body, please also paste the response here 

   >```
   >https://jsonplaceholder.typicode.com/posts/1
   >{
   >    "id": 1
   >}
   >```
   >
   >```
   >https://reqres.in/api/users/2
   >{
   >    "updatedAt": "2024-07-02T00:29:22.857Z"
   >}
   >```

4. 2 DELETE API 

   >```
   >https://jsonplaceholder.typicode.com/posts/1
   >{}
   >```
   >
   >```
   >https://reqres.in/api/users/2
   >```
   >
   >

5. Each example with 404, 401,500 and any http status codes you know

   >```
   >https://jsonplaceholder.typicode.com/posts/1001
   >404 not found
   >```
   >
   >```
   >http://api.openweathermap.org/data/2.5/weather?q=UnknownCity&appid=
   >401 Unauthorized
   >```

2. explain how the below annotaitons specify the table in database?

   >```java
   >@Column(columnDefinition = "varchar(255) default 'John Snow'")
   >private String name;
   >@Column(name="STUDENT_NAME", length=50, nullable=false, unique=false)
   >private String studentName;
   >```
   >
   >@Column defines how these variables are presented as columns in the database.
   >
   >columnDefinition uses DDL to give the restriction of the column. 

3. What is the default column names of the table in database for @Column ?

   >```java
   >@Column
   >private String firstName;
   >@Column
   >private String operatingSystem;
   >```
   >
   >first_name, operating_system

4. What are the layers in springboot application? what is the role of each layer? 

   >1. Presentation layer
   >   + Authentication
   >   + Json Translation
   >2. Business layer
   >   + Business logic
   >   + Validation
   >   + Authorization
   >3. Persistence layer
   >   + Storage Logic
   >4. Database
   >   + Actual database

5. Describe the flow in all of the layers if an API is called by Postman. 

   >Client sends HTTP request to Controller, then interacts with the service layer and further communicates databases and model.

6. What is the application.properties? do you know application.yml? 

   >application.properties are used to keep the properties in a single file to run the application in a different environment. 
   >
   >application.yml has the same functionality but in a different format and has lower priority than application.properties

7. Create a Project, name it with mongo-blog, write a POST API for mongo-blog, change database to MongoDB; 1. https://www.mongodb.com/compatibility/spring-boot