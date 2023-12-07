# User API Spec

## Register User API

Endpoint : POST /api/users

Request Body :

```json
{
  "username": "redha",
  "password": "rahasia",
  "name": "tata redha al fath"
}
```

Response Body Success :

```json
{
  "data": {
    "username": "redha",
    "name": "tata redha al fath"
  }
}
```

Response Body Error :

```json
{
  "errors": "Username alredy registered"
}
```

## Login User API

Endpoint : POST /api/users/login

Request Body :

```json
{
  "username": "redha",
  "password": "rahasia"
}
```

Response Body Success :

```json
{
  "data": {
    "token": "uniq-token",
    "expiredAt" : 2342342423423 // milliseconds
  }
}
```

Response Body Error :

```json
{
  "errors": "Username or Password wrong"
}
```

## Update User API

Endpoint : PATCH /api/users/current

Headers :

- Authorization : token

Request Body :

```json
{
  "name": "redha aja", // optional
  "password": "new password" // optional
}
```

Response Body Success :

```json
{
  "data": {
    "username": "redha",
    "name": "redha aja"
  }
}
```

Response Body Error :

```json
{
  "errors": "Name length max 100"
}
```

## Get User API

Endpoint : PATCH /api/users/current

Headers :

- Authorization : token

Response Body Success :

```json
{
  "data": {
    "username": "redha",
    "name": "redha aja"
  }
}
```

Response Body Error :

```json
{
  "errors": "Unauthorize"
}
```

## Logout User API

Endpoint : DELETE /api/users/logout

Headers :

- Authorization : token

Response Body Success :

```json
{
    "data": "ok"
}
```

Response Body Error :

```json
{
  "errors": "Unauthorize"
}
```