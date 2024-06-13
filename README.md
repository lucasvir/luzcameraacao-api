# Luz Camera Ação - API

## Diagram de Classes

```mermaid
classDiagram
    class User {
        User : String firstName
        +String lastName
        -email: string
        -password: string
        -telephone: string
        -cpf: string
        -cnpj: string
        -List<Order> orders
    }
    
    class Order {
        -userId: int
        -location: string
        -complement: int
        -uf: string
        -startedAt: datetime
        -expiresAt: datetime
        -List<Product> products
        -totalValue: double
    }
    
    class Product {
        -orderId: int
        -name: string
        -brand: string
        -category: string
        -type: string
        -price: double
    }
    
    User "1" *-- "1..*" Order : has
    Order "1" *-- "*" Product : contains
```