# Validador de Senha

## Informação
- Java 17
- Spring Boot 3
- JUnit 5 + Mockito

## Como executar
```bash
./mvnw test
./mvnw spring-boot:run
```

## API
- Endpoint: `POST /v1/verificador-senha/validar`
- Request: `{"senha": "AbTp9!fok"}`
- Response: `{"valido": true, "mensagem": "Senha válida."}`

## Decisões de implementação
Optei por implementar as regras de validação como classes independentes seguindo o princípio da responsabilidade única, facilitando a manutenção e extensão. Cada regra é responsável por uma específica validação de senha.

Para validação de caracteres repetidos, utilizei um conjunto (HashSet) para verificar duplicatas de forma eficiente, aproveitando a propriedade de que conjuntos não permitem elementos duplicados.

A validação de espaços é feita verificando diretamente se a string contém espaço em branco, mantendo a lógica simples e direta.

A arquitetura utiliza injeção de dependência para facilitar os testes unitários, permitindo mockar facilmente as dependências quando necessário.