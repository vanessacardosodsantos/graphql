# GraphQL
Implementação da linguagem de consulta GraphQL

# Tecnologias 
- Gradle 
- Spring Boot 4
- Java 25

# Para execução 
- Run no Application
- Acessar a Url: http://localhost:8080/graphiql
- Query exemplo para consulta: 

```
query bookDetails {
  bookById(id: "book-1") {
    id
    name
    pageCount
    author {
      id
      firstName
      lastName
    }
  }
}
```
