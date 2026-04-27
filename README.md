# 🔐 Validador de Senha API

API REST desenvolvida em Java com Spring Boot para validação de senhas com base em regras pré-definidas.

---

## 📌 Objetivo

Validar se uma senha atende aos critérios de segurança estabelecidos e retornar uma resposta indicando se é válida ou não, juntamente com a mensagem correspondente.

---

## 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot 3.x
- Maven
- JUnit 5
- Mockito

---

## 📋 Regras de validação

A senha deve:

- Ter no mínimo 9 caracteres
- Conter pelo menos 1 letra maiúscula (A-Z)
- Conter pelo menos 1 letra minúscula (a-z)
- Conter pelo menos 1 dígito (0-9)
- Conter pelo menos 1 caractere especial (!@#$%^&*()-+)
- Não conter caracteres repetidos
- Não conter espaços em branco

---

### ▶️ Como executar o projeto
#### Pré-requisitos

* Java 21
* Maven instalado

### Passos
#### Clonar repositório
```
git clone https://github.com/GomesMaurici0/ValidadorDeSenha.git

# Entrar no projeto
cd nome-do-projeto

# Rodar aplicação
mvn spring-boot:run
```

### A aplicação estará disponível em:

```
http://localhost:8080
```

--- 

### 🧠 Decisões Técnicas 

* Estrutura do projeto: organizei o projeto em camadas (controller, service, model) pra ter melhor manutenção e escalabilidade em caso de evolução. Trouxe tambem a validação pra camada de serviço, deixando o controller mais limpo e focado em lidar com as requisições e respostas.
---
* Validação personalizada: criei uma classe de validação customizada pra encapsular as regras de validação da senha, facilitando a manutenção e a adição de novas regras no futuro.
---
* Tratamento de erros: implementei um tratamento global de exceções usando @RestControllerAdvice pra garantir que qualquer erro seja capturado e uma resposta consistente seja retornada pro cliente, com mensagens claras sobre o motivo da falha.
---
* Testes: criei testes unitários pra validar as regras de negócio e testes de integração
---
* Implementação de validador separado: criei uma classe de validador específica pra lidar com as regras de validação da senha, mantendo a lógica de validação isolada e facilitando a manutenção e evolução futura. Isso também ajuda a manter o código mais limpo e organizado, e mantendo o service mais focado em orquestrar as camadas de validação e lidar com a lógica de negócio, enquanto o validador se concentra exclusivamente nas regras de validação.
## 📡 Endpoint

### 🔹 Validar senha

**POST** `/v1/verificador-senha/validar`

### 📥 Request

```json
{
  "senha": "AbTp9!fok"
}
```


### 📥 Response (sucesso - 200)

```json 
{
  "valido": true,
  "mensagem": "Senha válida."
}
```

### 📤 Response (erro - 400)
```json
{
  "valido": false,
  "mensagem": "Senha inválida: [motivo específico]."
}
```

### 🧪 Testes
#### O projeto possui testes unitários e de camada web:

* ✔️ Testes de regras de validação (service)
* ✔️ Testes de controller com MockMvc
* ✔️ Simulação de cenários de sucesso e erro

--- 
