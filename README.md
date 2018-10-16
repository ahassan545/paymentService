# paymentService

To run the application:
1. Open a cmd console
2. go to the targets folder
3. run java -jar payment.service.jar


Payments are displayed on
http://localhost:8080/payments/
http://localhost:8080/payments/{id}

New Payments are added via Http Post requests to
http://localhost:8080/payments/

Sample Http Post body
{
"notes":"Rent payment",
"amount":"100",
"fromAccount":{"number":"130130130", "ownerName":"John Smith","name":"Current Account","balance":"1500","sortCode":"10-20-30"},
"toAccount":{"sortCode":"40-50-60", "number":"120120120"}
}