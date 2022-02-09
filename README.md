# Java coding test

A Java coding skill test.

# Local deployment using docker-compose:
* Change mongo db url in application.yml
* Close previous running container: -> docker-compose down
* Build jar file: -> mvn clean install
* Build and execute docker image: ->  docker-compose up --build -d

# Deployment using k8s:
## Requirements:
### Set up
* Download and install `Docker Desktop`
* Download and run `Minikube` using command: minikube start --vm-driver=docker --memory='4000mb'

## Deployment Process:
### Build docker image
* docker build --tag coinbank:0.0.1-SNAPSHOT .
### Tag Image
* docker tag coinbank:0.0.1-SNAPSHOT samirjha123/coinbank:0.0.1-SNAPSHOT
### Push image to docker hub
* docker push samirjha123/coinbank:0.0.1-SNAPSHOT
### Deploy application in DEV profile
* kubectl apply -k .pipeline/k8s-manifest/overlays/dev
### Verify pod and service status
* kubectl get all
### forward 8080 port for local access
* kubectl port-forward svc/coinbank-service 8080:8080

### Application log:
* kubectl exec -it coinbank-cb445759-s527w --container coinbank -- /bin/sh
* cat /var/log/api/Application/Application.log

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
## 2. {{end-point}}/coin/get
## Request
``` 
curl --location --request GET 'http://localhost:8080/coin/get?id=62039d835d36102e24892fbe' \
--data-raw ''
```

## Response
```
{
    "id": "62039d835d36102e24892fbe",
    "amount": 19.0,
    "datetime": "2019-10-05T17:40:01+05:30"
}
```
## 3. {{end-point}}/coin/list
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