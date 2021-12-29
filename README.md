# Java coding test

A Java coding skill test.

# Requirements

`docker`
`docker-compose`

# How to build
* Close previous running container: -> docker-compose down
* Build jar file: -> mvn clean install
* Build and execute docker image: ->  docker-compose up --build -d

# I/Fs specification:
## 1. {{end-point}}/coin/deposit
## Request
``` 
curl --location --request POST 'localhost:8080/coin/deposit' \
--header 'Content-Type: application/json' \
--data-raw '{
    "datetime": "2019-10-05T15:40:01+05:30",
    "amount": 1001
}'
```

## Response
```
{
    "amount": 1001.0,
    "datetime": "2019-10-05T15:40:01+05:30"
}
```
## 2. {{end-point}}/coin/list
## Request
``` 
curl --location --request POST 'localhost:8080/coin/list' \
--header 'Content-Type: application/json' \
--data-raw '{
   "startDatetime": "2019-10-05T07:40:00+04:00",
   "endDatetime": "2019-10-05T09:40:04+01:00"
}'
```

## Response
```
{
    "content": [
        {
            "balance": 1264.0,
            "datetime": "2019-10-05T08:00:00Z"
        },
        {
            "balance": 4414.0,
            "datetime": "2019-10-05T09:00:00Z"
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "pageNumber": 0,
        "pageSize": 20,
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 2,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "first": true,
    "number": 0,
    "numberOfElements": 2,
    "size": 20,
    "empty": false
}
```