# Luz Camera Ação - API

:seedling: API - https://luzcameraacao-api.up.railway.app/

<br>

:notebook_with_decorative_cover: [Doc] (https://luzcameraacao-api.up.railway.app/swagger-ui.html)

# :scroll: Descrição

API REST em Java 17 para uma aplicação web de aluguel de equipamentos para produções audio visuais.

Conta com gerenciamento e ciração de usuarios, produtos e pedidos - autenticação via JWT e persistência em banco relacional na nuvem.

## :wrench: Funcionalidades

- *Login*
- *Autenticação JWT Token*
- *Crud de usuário*
- *Crud de produtos*
- *Crud de pedidos*

## :toolbox: Tecnologias

- Java
- Spring Boot
- JPA / Hibernate
- Postgres
- JSON Web Token
- Swagger
- Gradle
- JUnit

## Diagrama de Classes

```mermaid
classDiagram
    class User {
        -String firstName
        -String lastName
        -String email
        -String password
        -String telephone
        -String cpf
        -String cnpj
        -List~Order~ orders
    }
    
    class Order {
        -int userId
        -String location
        -String complement
        -String city
        -String uf
        -datetime startedAt
        -datetime expiresAt
        -List~Product~ products
        -BigDecimal totalValue
    }
    
    class Product {
        -int oderId
        -String name
        -String brand        
        -String category
        -String type
        -BigDecimal price    
    }
    
    User "1" *-- "1..*" Order : has
    Order "1" *-- "*" Product : contains
```