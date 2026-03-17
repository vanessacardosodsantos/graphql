# GraphQL
Implementação da linguagem de consulta GraphQL com Spring Boot, cobrindo os principais conceitos da tecnologia.

# Tecnologias
- Gradle
- Spring Boot 4
- Java 25
- PostgreSQL (via Docker)
- Spring for GraphQL
- GraphQL Java DataLoader

# Conceitos cobertos

| Conceito                 | Status | Onde encontrar                                                  |
|--------------------------|--------|-----------------------------------------------------------------|
| Schema                   | ✅      | `src/main/resources/graphql/schema.graphqls`                    |
| Queries                  | ✅      | `BookController`, `AuthorController`                            |
| Mutations                | ✅      | `BookController`, `AuthorController`                            |
| N+1 Problem + DataLoader | ✅      | `BookController#author` com `@BatchMapping`, `AuthorDataLoader` |
| Scalar Types             | ✅      | `GraphQLConfig` configurando tipo `Long`                        |

---

# Para execução
- Run no `Application`
- Rodar o comando `docker compose up -d` estando na pasta raiz da aplicação
- Executar o arquivo `script/insert.sql` no banco para inserir dados de teste

---

# Queries

### Buscar livro por ID
```graphql
query bookDetails {
  bookById(id: "1") {
    id
    name
    pageCount
    genres {
      name
    }
    author {
      id
      firstName
      lastName
    }
  }
}
```

### Buscar autor por ID (com livros publicados)
```graphql
query authorDetails {
  authorById(id: "1") {
    id
    firstName
    lastName
    publishedBooks {
      id
      name
      pageCount
    }
  }
}
```

### Listar livros com filtros
```graphql
query filteredBooks {
  getBooks(filter: { maxPages: 300, genres: [1, 2] }) {
    id
    name
    pageCount
    author {
      firstName
      lastName
    }
  }
}
```

---

# Mutations

### Criar autor
```graphql
mutation {
  createAuthor(author: { firstName: "Machado", lastName: "de Assis" }) {
    id
    firstName
    lastName
  }
}
```

### Atualizar autor
```graphql
mutation {
  updateAuthor(id: "1", author: { firstName: "José", lastName: "Saramago" }) {
    id
    firstName
    lastName
  }
}
```

### Deletar autor
```graphql
mutation {
  deleteAuthor(id: "1")
}
```

### Criar livro
```graphql
mutation {
  createBook(book: { name: "Dom Casmurro", pageCount: 256, genres: [1], authorId: 1 }) {
    id
    name
    pageCount
    author {
      firstName
      lastName
    }
  }
}
```

### Atualizar livro
```graphql
mutation {
  updateBook(id: "1", book: { name: "Memórias Póstumas", pageCount: 312, genres: [1, 2], authorId: 1 }) {
    id
    name
    pageCount
  }
}
```

### Deletar livro
```graphql
mutation {
  deleteBook(id: "1")
}
```

---

---

# Consultas via GraphiQL
- Acessar: http://localhost:8080/graphiql

# Via Bruno
- Baixar o HTTP client Bruno: https://www.usebruno.com/
- Abrir a pasta `requests-graphql` na aplicação
- Executar os requests alterando as variáveis conforme necessário
