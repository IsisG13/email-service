# Email Spring Boot

### Para enviar o email use o comando:
```
curl -X POST http://localhost:8080/api/email \
-H "Content-Type: application/json" \
-d '{"to":"destinatario@example.com", "subject":"Teste", "body":"Olá! Este é um teste com MimeMessage."}'
```

