# Projeto Final - PB Open Banking 2022

![compass logo](https://user-images.githubusercontent.com/86629675/185922211-26dad123-50e0-433c-9810-63bb71001637.png)

> Projeto final do Programa de Bolsas Compass.uol - Open Banking de julho de 2022. O projeto consiste na implementação de uma API REST, responsável pelo gerenciamento de um cadastro de produtos.

## ✅ Funcionalidades

* `Cadastro`
* `Listagem`
* `Remoção`
* `Atualização`
* `Busca filtrada por ID, preços máximo, mínimo e/ou nome`

## 📑 Endpoints
Endpoints referentes aos produtos:
```
GET - /products/ : Listagem de todos os produtos cadastrados
GET - /products/{id} : Busca de produto pelo ID fornecido
GET - /products/search : Busca filtrada por preços máximo e mínimo e/ou nome
POST - /products/ : Cadastro de um novo produto
PUT - /products/{id} : Atualização dos dados de um produto existente pelo ID fornecido
DELETE - /products/{id} : Remoção de um produto cadastrado, pelo ID fornecido
```
## 🔨 Ferramentas 
Feramentas de Desenvolvimento:
```
- Java 11;
- Spring Boot 2.7.2;
- H2 Database;
- Postman;
- Swagger;
- Git;
- Eclipse IDE.
```
Ferramentas de Testagem:
```
- JUnit;
- Mockito.
```
