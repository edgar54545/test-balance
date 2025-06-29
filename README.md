Java 17, Maven, Spring boot, H2 in-memory DB is used.
 
`mvn spring-boot:run` command can be executed to run the app. Taking into account that maven is installed in a machine or bundled maven from inteliij is used.


The following endpoint will create a balance
```
curl --location 'localhost:8080/api/v1/balance' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Balance-1"
}'
```

The response looks like
```
{
    "id": "def7e0a4-14e1-49ec-a032-93f29342064a",
    "name": "Balance-1",
    "amount": 0
}
```
Balance id is used to create transactions.


The endpoint below is used to create the transaction
```
curl --location 'localhost:8080/api/v1/balance/{balance_id}/transaction' \
--header 'Content-Type: application/json' \
--data '{
    "type": "DEPOSIT",
    "amount": 100,
    "currency": "EUR"
}'
```


Finally, with the cURL below the balance can be fetched from API
```
curl --location 'localhost:8080/api/v1/balance/{balance-id}/amount'
```

Currency exchange is hardcoded with the approximate values 
   * EUR/USD=1.08,
   * BYN/USD=0.31D,
   * RUB/USD=0.011;


 The balance is recalculated on each new transaction. Negative balances are allowed.
