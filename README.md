# Luz Camera Ação - API

## Diagram de Classes

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
        -int complement
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