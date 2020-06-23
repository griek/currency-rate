# currency-rate
Service for currency conversion based on rates from  `https://exchangeratesapi.io/`.

`POST` request input parameters:

1) Base currency (optional field, default value - Ruble (RUB));
2) **Target currency** (mandatory field);
3) **Amount** (mandatory field);
4) Date in YYYY-MM-DD format (optional field, default value - actual conversion rate for today).

Names of parameters:
- baseCurrency: string (Base currency)
- targetCurrency: string (Target currency)
- amount: number (Amount)
- date: string (Conversion date)

Example of request `POST` `http://localhost:8080/api/currency-conversion`:
```
{
  "baseCurrency": "EUR",
  "targetCurrency": "RUB",
  "date": "2020-05-01", 
  "amount": 1300
}
```
# Curl example
```
curl -d '{"baseCurrency": "EUR", "targetCurrency": "RUB", "date": "2020-05-01", "amount": 1300}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/currency-conversion
```

Once the application is launched, you can use Swagger: `http://localhost:8080/swagger-ui.html`.
Conversion method: `/api/currency-conversion`

Output data in JSON format:

1) Course 
  - Base Currency 
  - Conversion currency 
  - Value of base currency
  - Conversion currency value
2) Date in YYYY-MM-DD format;
3) Amount after conversion.
