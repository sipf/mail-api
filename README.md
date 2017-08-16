# mail-api
Just send email with a REST API

```
curl -H "Content-Type: application/json" -X POST -d '{"from": "leonard.tavae@informatique.gov.pf", "to": ["leonard.tavae@informatique.gov.pf"], "subject": "the subject", "message": "the message", "html": true }' http://localhost:8080/mail
```
