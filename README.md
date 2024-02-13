# Altair - URL Shortener

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
- [ ] QR generation

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
 
### DB Schema
![ER-Diagram.png](.github%2Fmedia%2FER-Diagram.png)

### References

- https://github.com/vilaca/UrlShortener
- https://www.geeksforgeeks.org/system-design-url-shortening-service/
- https://systemdesign.one/url-shortening-system-design/#url-shortener-api
- https://medium.com/@pratimbhosale/building-a-url-shortener-using-go-and-sqlite-with-gorm-ab08c0bcc99c
- https://medium.com/@sandeep4.verma/system-design-scalable-url-shortener-service-like-tinyurl-106f30f23a82
- https://github.com/search?q=url+shortener++language%3AJava&type=repositories&l=Java
