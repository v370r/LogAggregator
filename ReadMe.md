# Sonatus Log Aggregator Service (Springboot and In Memory H2 DB)

This project is asked as part of assessment for Sonatus Cloud Backend Role via Codebyte.

## Tech Stack 
- Java 17
- SpringBoot
- h2 In-Memory database
- Lombok

## Features
- POST/logs
- GET/logs

## How to Run Project
```
java -jar sonatus\target\sonatus-0.0.1-SNAPSHOT.jar
```

if port 8080 is already occupied

Use:
```
java -jar sonatus\target\sonatus-0.0.1-SNAPSHOT.jar --server.port=9090
```

## Postman Requests

### POST
```
curl -X POST http://localhost:8080/logs \
  -H "Content-Type: application/json" \
  -d '{
    "serviceName": "auth-service",
    "timestamp": "2025-03-28T10:15:00Z",
    "message": "User login successful"
  }'
```

```serviceName``` — Name of the microservice (must not be blank)

```timestamp``` — ISO-8601 UTC timestamp (Instant)

```message``` — Log message content (must not be blank)


### GET
```
curl --location 'http://localhost:8080/logs?service=auth-service&start=2025-03-28T10%3A00%3A00Z&end=2025-03-28T11%3A59%3A50Z'
```

## Why Schedular not Time to Live TTL?
Initially I thought of using MongoDB with built in TTL indexes but this assignment specifically asked to use inmemory logs only. So I had to scrap off that idea.

Since h2 db dont have inmemory TTL functionality I have written a schedular that automatically deltes logs older than 1 hour.

## why Spring boot and Java ?
I have experience as a software dev at ADP for 3 years developing scalable microservices so I made sure  the application I write is threadsafe for data abse operations. I picked java springboot as it has JPA that manages connects per request and thread safety is internally handled 