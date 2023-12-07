# Address API Spec

## Create Address API

Endpoint : POST /api/contacts/:contactId/addresses

Headers :

- Authorization : token

Request Body :

```json
{
  "street": "Jl. Ronggokusumo",
  "city": "Pati",
  "province": "Central Java",
  "country": "Indonesia",
  "postalCode": "59154"
}
```

Response Body Success :

```json
{
  "data": {
    "id": "random-string",
    "street": "Jl. Ronggokusumo",
    "city": "Pati",
    "province": "Central Java",
    "country": "Indonesia",
    "postalCode": "59154"
  }
}
```

Response Body Error :

```json
{
  "errors": "Country is required"
}
```

## Update Address API

Endpoint : PUT /api/contacts/:contactId/addresses/:addressId

Headers :

- Authorization : token

Request Body :

```json
{
  "street": "Jl. Ronggokusumo",
  "city": "Pati",
  "province": "Central Java",
  "country": "Indonesia",
  "postalCode": "59154"
}
```

Response Body Success :

```json
{
  "data": {
    "id": "random-string",
    "street": "Jl. Ronggokusumo",
    "city": "Pati",
    "province": "Central Java",
    "country": "Indonesia",
    "postalCode": "59154"
  }
}
```

Response Body Error :

```json
{
  "errors": "Country is required"
}
```

## Get Address API

Endpoint : GET /api/contacts/:contactId/addresses/:addressId

Headers :

- Authorization : token

Response Body Success :

```json
{
  "data": {
    "id": "random-string",
    "street": "Jl. Ronggokusumo",
    "city": "Pati",
    "province": "Central Java",
    "country": "Indonesia",
    "postalCode": "59154"
  }
}
```

Response Body Error :

```json
{
  "errors": "Contact is not found"
}
```

## List Addresses API

Endpoint : GET /api/contacts/:contactId/addresses

Headers :

- Authorization : token

Query params :

Response Body Success :

```json
{
  "data": [
    {
      "id": "random-string",
      "street": "Jl. Ronggokusumo",
      "city": "Pati",
      "province": "Central Java",
      "country": "Indonesia",
      "postalCode": "59154"
    },
    {
      "id": "random-string",
      "street": "Jl. Ronggokusumo",
      "city": "Pati",
      "province": "Central Java",
      "country": "Indonesia",
      "postalCode": "59154"
    }
  ]
}
```

Response Body Error :

```json
{
  "errors": "Contact is not found"
}
```

## Remove Address API

Endpoint : DELETE /api/contacts/:contactId/addresses/:addressId

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
  "errors": "Address is not found"
}
```
