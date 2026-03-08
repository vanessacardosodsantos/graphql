# GraphQL
Implementação da linguagem de consulta GraphQL

# Tecnologias 
- Gradle 
- Spring Boot 4
- Java 25

# Para execução 
- Run no Application
- Rodar o comando `docker compose up -d` estando na pasta raiz da aplicação
- Executar o arquivo `insert.sql` no banco para inserir alguns dados de teste

## Consultas via Graphiql
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

## Via Bruno
- Baixar o http client Bruno https://www.usebruno.com/
- Abrir a pasta `requests-graphql` na aplicação
- Executar o request alterando as variáveis