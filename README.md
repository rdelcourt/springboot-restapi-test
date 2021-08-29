# springboot-restapi-test
Test de création d'une api REST avec springboot


# commandes scénario d'API
Etape 1:
curl -d '{"author":"Jérémie Durand","channel":"FACEBOOK","content":"Bonjour, j’ai un problème avec mon nouveau téléphone"}' -H 'Content-Type: application/json' -X POST http://localhost:8080/message

Etape 2 :
curl -d '{"customerName":"Jérémie Durand", "messages":[{"id":1}]}' -H 'Content-Type: application/json' -X POST http://localhost:8080/customer-folder


Etape 3 : 
curl -d '{"author":"Sonia Valentin","channel":"MAIL","content":"Je suis Sonia, et je vais mettre tout en œuvre pour vous aider. Quel est le modèle de votre téléphone ?", "customerFolder":{"id":1}}' -H 'Content-Type: application/json' -X POST http://localhost:8080/message


Etape 4 : 
curl -d '{"reference":"KA-18B6"}' -H 'Content-Type: application/json' -X PUT http://localhost:8080/customer-folder/1

Etape 5 : 
curl http://localhost:8080/customer-folders/


# URL de la doc d'API
http://localhost:8080/swagger-ui.html
http://localhost:8080/v2/api-docs
