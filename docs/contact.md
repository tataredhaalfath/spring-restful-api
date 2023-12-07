# Contact API Spec

## Create Contact API

Endpoint : POST /api/contacts

Headers :

- Authorization : token

Request Body :

```json
{
  "firstName": "tata",
  "lastName": "al fath",
  "email": "tataredha@gmail.com",
  "phone": "08531412512"
}
```

Response Body Success :

```json
{
  "data": {
    "id": "random-string",
    "firstName": "tata",
    "lastName": "al fath",
    "email": "tataredha@gmail.com",
    "phone": "08531412512"
  }
}
```

Response Body Error :

```json
{
  "errors": "Email is not valid format"
}
```

## Update Contact API

Endpoint : PUT /api/contacts/:id

Headers :

- Authorization : token

Request Body :

```json
{
  "firstName": "tata",
  "lastName": "al fath",
  "email": "tataredha@gmail.com",
  "phone": "08531412512"
}
```

Response Body Success :

```json
{
  "data": {
    "id": "random-string",
    "firstName": "tata",
    "lastName": "al fath",
    "email": "tataredha@gmail.com",
    "phone": "08531412512"
  }
}
```

Response Body Error :

```json
{
  "errors": "Email is not valid format"
}
```

## Get Contact API

Endpoint : GET /api/contacts/:id

Headers :

- Authorization : token

Response Body Success :

```json
{
  "data": {
    "id": "random-string",
    "firstName": "tata",
    "lastName": "al fath",
    "email": "tataredha@gmail.com",
    "phone": "08531412512"
  }
}
```

Response Body Error :

```json
{
  "errors": "Contact is not found"
}
```

## Search Contact API

Endpoint : GET /api/contacts

Headers :

- Authorization : token

Query params :

- name : String, contact first name or last name, using like query, optional
- phone : String, contact phone, using like query, optional
- email : String, contact email, using like query, optional
- page : Integer, start from 0, default 0
- size : Integer, default 10

Response Body Success :

```json
{
  "data": [
    {
      "id": "random-string",
      "firstName": "tata",
      "lastName": "al fath",
      "email": "tataredha@gmail.com",
      "phone": "08531412512"
    },
    {
      "id": "random-string",
      "firstName": "tata",
      "lastName": "al fath",
      "email": "tataredha@gmail.com",
      "phone": "08531412512"
    }
  ],
  "paging" : {
    "currentPage" : 0,
    "totalPage" : 10,
    "size" : 10
  }
}
```

Response Body Error :

```json
{
  "errors": "Unauthorize"
}
```

## Remove Contact API

Endpoint : DELETE /api/contacts/:id

Headers :

- Authorization : token

Response Body Error :

```json
{
  "data": "ok"
}
```

Response Body Error :

```json
{
  "errors": "Contact is not found"
}
```
