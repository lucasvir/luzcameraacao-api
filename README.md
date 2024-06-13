# Luz Camera Ação - API

## Diagram de Classes

```mermaid
classDiagram
    class User {
        (D, #00F) id: int
        (D, #00F) firstName: string
        (D, #00F) lastName: string
        (D, #00F) email: string
        (D, #00F) password: string
        (D, #00F) telephone: string
        (D, #00F) cpf: string
        (D, #00F) cnpj: string
        List<Order> orders
    }
    
    class Order {
        (D, #00F) id: int
        (D, #00F) userId: int
        (D, #00F) location: string
        (D, #00F) complement: int
        (D, #00F) uf: string
        (D, #00F) startedAt: datetime
        (D, #00F) expiresAt: datetime
        (D, #00F) List<Product> products
        (D, #00F) totalValue: double
    }
    
    class Product {
        (D, #00F) id: int
        (D, #00F) orderId: int
        (D, #00F) name: string
        (D, #00F) brand: string
        (D, #00F) category: string
        (D, #00F) type: string
        (D, #00F) price: double
    }
    
    User "1" *-- "1..*" Order : has
    Order "1" *-- "*" Product : contains
```