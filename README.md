# Altair - URL Shortener

Altair is a URL shortener service that allows users to shorten long URLs into short and unique aliases. The service
also provides a QR code for the short URL.

### How does a URL shortener work?

At a high level, the URL shortener executes the following operations:

the server generates a unique short URL for each long URL
the server encodes the short URL for readability
the server persists the short URL in the data store
the server redirects the client to the original long URL against the short URL

### What is a URL shortening service

A URL shortening service is a website that substantially shortens a Uniform Resource Locator (URL). The short URL
redirects the client to the URL of the original website. Some popular public-facing URL shortening services are
tinyurl.com and bitly.com.

The reasons to shorten a URL are the following:

- Track clicks for analytics
- Beautify a URL
- Disguise the underlying URL for affiliates
- Some instant messaging services limit the count of characters on the URL

### Functional requirements

- [x] Given a long URL, the service should generate a shorter and unique alias for it.
- [x] When the user hits a short link, the service should redirect to the original link.
- [ ] The short URL should support analytics (not real-time) such as the number of redirections from the shortened URL
- [x] The short URL should be non-predictable
- [x] Multiple users entering the same long URL must receive the same short URL (1-to-1 mapping)
- [ ] URL expires after 30 days of not being use
- [ ] Optional custom alias
- [x] QR generation

### Things to do

- [x] Basic shortener algorithm
- [x] Front end integration
- [ ] Redis for cache
- [ ] Authentication for statistics
- [ ] Dockerized and deployment configuration
- [ ] Testing :)
- [ ] Cors configuration
- [ ] Documentation
- [ ] Testing again
- [ ] Cloud

### API

#### Shorten URL

```http
POST /?longUrl={:url}
```

| Parameter | Type     | Description |
| :-------- | :------- | :---------- |
| `url`     | `string` | **Required**. The long URL to be shortened |

Response

```json
{
  "id": 10000,
  "shortUrl": "http://localhost:8080/jO5Wi3",
  "longUrl": "https://github.com/",
  "createdAt": "2/13/24, 1:35 PM",
  "expiresAt": "2024-03-13",
  "accessCount": 0,
  "qrCode": "iVBORw0KGgoAAAANSUhEUgAAAPoAAAD6AQAAAACgl2eQAAABU0lEQVR4Xu2XMRLCMAwExVBQ8gSekqeFp/kpeQJlCibm7uTMEBeUzHD4GsXKprAky0rUzyrRezoNIDWA1ABSA0h9DViDOtPMWF0Xra92AJ+3s9YBs0y70wzAzgE8bmU+cTXdERVboMJEXOofAFzbAlVVzXQLn3anF8Asc+cIwEajtR3QtMatiHvzOQF6o3Rj8eThpfMQKAegPnCjnvgmggZutio7gNrgxOGlIX6MgwWAZzSnDEB94tQWQoYA6jg4DaJH8WLNodAQCMVhYXEzAHdFxQ3Icr6wR+UZnrt0ewC8UVHOkDiadvF4ATi8bUxScWM2PLRiD6BJWc8e3FW1B6Cd808U4WjJ724cD4DPG39hprr3KEtg4Z9acopJTku2QOFsj/u1hcMS0OyLGOAMo0c5ApVVvXJakkwBbj4EsDkp3fmVFfBBA0gNIDWA1ABSvwG8AHdpNb7LjVzhAAAAAElFTkSuQmCC"
}
```

#### Redirect URL

```http
GET /{:shortUrl}
```

| Parameter | Type     | Description |
| :-------- | :------- | :---------- |
| `shortUrl`     | `string` | **Required**. The short URL to be redirected |

Response: Redirects to the original URL

### Tech Stack

#### Backend
- Java 17
- Spring Boot
- H2 Database
- JPA
- Lombok
- QRGen
- Swagger
- JUnit
- Mockito
- Docker
- Docker Compose

#### Frontend
- React
- Tailwind CSS
- SWR
- Next.js

### How to run

```bash
docker-compose up
```

### How to use

- Access the Swagger UI at http://localhost:8080/swagger-ui.html
- Use the API to shorten a URL
- Access the shortened URL to be redirected


### DB Schema

![ER-Diagram.png](.github%2Fmedia%2FER-Diagram.png)

### References

- https://github.com/vilaca/UrlShortener
- https://www.geeksforgeeks.org/system-design-url-shortening-service/
- https://systemdesign.one/url-shortening-system-design/#url-shortener-api
- https://medium.com/@pratimbhosale/building-a-url-shortener-using-go-and-sqlite-with-gorm-ab08c0bcc99c
- https://medium.com/@sandeep4.verma/system-design-scalable-url-shortener-service-like-tinyurl-106f30f23a82
- https://github.com/search?q=url+shortener++language%3AJava&type=repositories&l=Java
