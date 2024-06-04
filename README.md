# Proyecto integrador

Backend C4

## Postman

{
"info": {
"\_postman_id": "e6682d1a-fe0e-4230-b672-c764884fd5a4",
"name": "proyecto-integrador",
"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
"\_exporter_id": "29640503"
},
"item": [
{
"name": "pacientes",
"item": [
{
"name": "all",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseUrl}}/paciente",
"host": [
"{{baseUrl}}"
],
"path": [
"paciente"
]
}
},
"response": []
},
{
"name": "by id",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseUrl}}/paciente/3",
"host": [
"{{baseUrl}}"
],
"path": [
"paciente",
"3"
]
}
},
"response": []
},
{
"name": "new paciente",
"request": {
"method": "POST",
"header": [],
"body": {
"mode": "raw",
"raw": " {\n \"apellido\": \"cosme\",\n \"nombre\": \"FULANITO\",\n \"email\": \"email@example.com\",\n \"dni\": \"1545646\",\n \"fechaIngreso\": \"2024-02-15\",\n \"domicilio\": {\n \"id\": 1,\n \"calle\": \"SIEMPRE VIVA\",\n \"numero\": 123,\n \"localidad\": \"SAN PEDRO\",\n \"provincia\": \"JUJUY\"\n }\n}",
"options": {
"raw": {
"language": "json"
}
}
},
"url": {
"raw": "{{baseUrl}}/paciente",
"host": [
"{{baseUrl}}"
],
"path": [
"paciente"
]
}
},
"response": []
},
{
"name": "update paciente",
"request": {
"method": "PUT",
"header": [],
"body": {
"mode": "raw",
"raw": "{\n \"id\": 3,\n \"nombre\": \"nuevo nombre\",\n \"apellido\": \"nuevo apellido\",\n \"email\": \"email@example.com\",\n \"dni\": \"123456\",\n \"fechaIngreso\": \"2024-02-15\",\n \"domicilio\": {\n \"id\": 3,\n \"calle\": \"calle\",\n \"numero\": 123,\n \"localidad\": \"SAN PEDRO\",\n \"provincia\": \"JUJUY\"\n }\n}",
"options": {
"raw": {
"language": "json"
}
}
},
"url": {
"raw": "{{baseUrl}}/paciente",
"host": [
"{{baseUrl}}"
],
"path": [
"paciente"
]
}
},
"response": []
},
{
"name": "by id",
"request": {
"method": "DELETE",
"header": [],
"url": {
"raw": "{{baseUrl}}/paciente/3",
"host": [
"{{baseUrl}}"
],
"path": [
"paciente",
"3"
]
}
},
"response": []
}
]
}
],
"event": [
{
"listen": "prerequest",
"script": {
"type": "text/javascript",
"packages": {},
"exec": [
""
]
}
},
{
"listen": "test",
"script": {
"type": "text/javascript",
"packages": {},
"exec": [
""
]
}
}
],
"variable": [
{
"key": "baseUrl",
"value": "localhost:8080",
"type": "string"
}
]
}
