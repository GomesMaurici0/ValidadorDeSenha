# 🔐 Validador de Senha

Uma API REST robusta e extensível para validação de senhas, desenvolvida com Spring Boot seguindo princípios de **Design de API**, **SOLID**, **Clean Code** e boas práticas de desenvolvimento.

## 📋 Índice

- [Características](#características)
- [Requisitos de Senha](#requisitos-de-senha)
- [Arquitetura](#arquitetura)
- [Instalação](#instalação)
- [API REST](#api-rest)
  - [Endpoints](#endpoints)
  - [Exemplos de Requisição](#exemplos-de-requisição)
  - [Exemplos de Resposta](#exemplos-de-resposta)
  - [Códigos de Status HTTP](#códigos-de-status-http)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Padrões e Princípios Aplicados](#padrões-e-princípios-aplicados)
- [Tratamento de Erros](#tratamento-de-erros)
- [Testes](#testes)
- [Tecnologias](#tecnologias)

## ✨ Características

✅ **Validação Robusta** - Múltiplas regras de validação de senha  
✅ **Exceções Personalizadas** - Cada tipo de erro tem sua própria exceção  
✅ **API RESTful** - Endpoint claro e bem documentado  
✅ **Tratamento Centralizado** - GlobalExceptionHandler para respostas consistentes  
✅ **Mensagens Personalizadas** - Feedback específico para cada validação falhada  
✅ **Arquitetura Extensível** - Fácil adicionar novas regras de validação  
✅ **SOLID Principles** - Código bem estruturado e desacoplado  
✅ **Clean Code** - Nomenclatura clara, responsabilidade única  
✅ **Testes Abrangentes** - Cobertura completa com testes parametrizados  
✅ **Java 17+** - Utiliza features modernas como records

## 🔑 Requisitos de Senha

Uma senha válida deve atender a **TODOS** os seguintes critérios:

| Critério | Descrição | Exemplo | Exceção Lançada |
|----------|-----------|---------|-----------------|
| ✓ Comprimento | Mínimo de **8 caracteres** | `Senha@123` | `ComprimentoInsuficienteException` |
| ✓ Letra Maiúscula | Pelo menos 1 letra maiúscula (A-Z) | `S`enha@123 | `LetraMaiusculaAusenteException` |
| ✓ Letra Minúscula | Pelo menos 1 letra minúscula (a-z) | `S`enha@123 | `LetraMinusculaAusenteException` |
| ✓ Dígito | Pelo menos 1 número (0-9) | Senha@`123` | `DigitoAusenteException` |
| ✓ Caractere Especial | Pelo menos 1 caractere de `!@#$%^&*()-+` | Senha`@`123 | `CaractereEspecialAusenteException` |
| ✓ Sem Repetição | Nenhum caractere pode repetir em qualquer posição | ✓ Senha@123 ✗ Senha@112 | `CaracteresRepetidosException` |

## 🏗️ Arquitetura

### Fluxo de Requisição com Exceções

```
┌─────────────────────────────────┐
│   VerificadorController         │
│   POST /v1/verificador-de-senha │
└────────────┬────────────────────┘
             │ SenhaInput
             ▼
┌─────────────────────────────────┐
│   VerificadorServiceImpl         │
│   - Injeta List<ValidadorRegra> │
└────────────┬────────────────────┘
             │
    ┌─────────────────────────────┐
    │ Para cada ValidadorRegra:   │
    │ validador.valida(senha)     │
    └─────────────────────────────┘
             │
      ┌──────┴──────────────────────────┐
      │                                  │
      ▼ Válido                           ▼ Inválido
   Continue              Lança SenhaInvalidaException
      │                                  │
      ▼                                  ▼
   return SenhaOutPut      GlobalExceptionHandler
   (true, "Válido")       Transforma em ResponseEntity
                          (400, SenhaOutPut(false, msg))
```

### Estrutura do Projeto

```
src/main/java/com/example/validadordesenha/
├── controller/
│   └── VerificadorController.java          # Camada de apresentação
├── dto/
│   ├── input/
│   │   └── SenhaInput.java                 # DTO de entrada
│   └── output/
│       └── SenhaOutPut.java                # DTO de resposta
├── exception/
│   ├── SenhaInvalidaException.java         # Exceção base
│   ├── ComprimentoInsuficienteException.java
│   ├── LetraMaiusculaAusenteException.java
│   ├── LetraMinusculaAusenteException.java
│   ├── DigitoAusenteException.java
│   ├── CaractereEspecialAusenteException.java
│   ├── CaracteresRepetidosException.java
│   └── handler/
│       └── GlobalExceptionHandler.java     # Tratador centralizado
├── service/
│   ├── VerificadorService.java             # Interface do serviço
│   ├── impl/
│   │   └── VerificadorServiceImpl.java      # Implementação do serviço
│   └── validator/
│       ├── ValidadorRegra.java             # Interface para validadores
│       └── impl/                            # Implementações dos validadores
│           ├── ValidadorComprimento.java
│           ├── ValidadorLetraMaiuscula.java
│           ├── ValidadorLetraMinuscula.java
│           ├── ValidadorDigito.java
│           ├── ValidadorCaractereEspecial.java
│           └── ValidadorCaracteresRepetidos.java
└── ValidadorDeSenhaApplication.java        # Classe principal
```

## 📦 Instalação

### Pré-requisitos

- **Java 17** ou superior
- **Maven 3.8+**
- **Spring Boot 3.x**

### Passos

1. **Clone o repositório**
   ```bash
   git clone <seu-repositorio>
   cd ValidadorDeSenha
   ```

2. **Compile o projeto**
   ```bash
   mvn clean install
   ```

3. **Execute a aplicação**
   ```bash
   mvn spring-boot:run
   ```

A API estará disponível em `http://localhost:8080`

## 🚀 API REST

### Endpoints

#### Validar Senha

```http
POST /v1/verificador-de-senha/valida
Content-Type: application/json

{
  "senha": "string"
}
```

**Status Code:** `200 OK`

### Exemplos de Requisição

#### ✅ Senha Válida

```bash
curl -X POST http://localhost:8080/v1/verificador-de-senha/valida \
  -H "Content-Type: application/json" \
  -d '{"senha":"Senha@123"}'
```

#### ❌ Senha Inválida

```bash
curl -X POST http://localhost:8080/v1/verificador-de-senha/valida \
  -H "Content-Type: application/json" \
  -d '{"senha":"abc"}'
```

### Exemplos de Resposta

#### ✅ Resposta de Sucesso

```json
{
  "valido": true,
  "mensagem": "Senha válida."
}
```

#### ❌ Resposta de Erro - Múltiplas Violações

```json
{
  "valido": false,
  "mensagem": "A senha deve ter pelo menos 8 caracteres. A senha deve conter pelo menos uma letra maiúscula. A senha deve conter pelo menos um dígito. A senha deve conter pelo menos um caractere especial (!@#$%^&*()-+)."
}
```

#### ❌ Resposta de Erro - Caracteres Repetidos

```json
{
  "valido": false,
  "mensagem": "A senha não pode conter caracteres repetidos."
}
```

#### ❌ Resposta de Erro - Request Inválido

```json
{
  "valido": false,
  "mensagem": "A senha não pode ser vazia ou nula."
}
```

### Códigos de Status HTTP

| Código | Descrição |
|--------|-----------|
| 200    | OK - Senha válida |
| 400    | Bad Request - Senha inválida (detalhes na resposta) |

## 📊 Estrutura do Projeto

### Camadas da Aplicação

#### 1. **Controller** (Camada de Apresentação)
- Recebe requisições HTTP
- Valida entrada com `@Valid`
- Delega ao serviço
- Retorna resposta estruturada

#### 2. **DTO (Data Transfer Object)**
- **Entrada:** `SenhaInput` - Encapsula a senha
- **Saída:** `SenhaOutPut` - Retorna resultado (válido/mensagem)

#### 3. **Service** (Lógica de Negócio)
- Interface `VerificadorService` - Define contrato
- Implementação `VerificadorServiceImpl` - Orquestra validadores

#### 4. **Validator** (Estratégia de Validação)
- Interface `ValidadorRegra` - Contrato para validadores
- Implementações específicas - Cada uma valida uma regra

#### 5. **Exception** (Tratamento de Erros)
- Exceções específicas para cada tipo de erro de validação
- `GlobalExceptionHandler` para tratamento centralizado

## 🎯 Padrões e Princípios Aplicados

### ✅ SOLID Principles

#### 1. **Single Responsibility Principle (SRP)**
Cada classe tem uma única responsabilidade:
- `ValidadorComprimento` → Valida comprimento
- `ValidadorLetraMaiuscula` → Valida maiúscula
- `VerificadorController` → Recebe requisições

#### 2. **Open/Closed Principle (OCP)**
Aberto para extensão, fechado para modificação:
```java
// Para adicionar nova regra, apenas crie uma classe:
@Component
public class ValidadorNovaRegra implements ValidadorRegra {
    public boolean valida(String senha) { /* lógica */ }
    public String getMensagemErro() { return "..."; }
}
// Pronto! Será automaticamente injetada e usada.
```

#### 3. **Liskov Substitution Principle (LSP)**
Validadores são intercambiáveis:
```java
List<ValidadorRegra> validadores; // Qualquer implementação funciona
```

#### 4. **Interface Segregation Principle (ISP)**
Interface focada:
```java
public interface ValidadorRegra {
    boolean valida(String senha);      // Apenas o necessário
    String getMensagemErro();
}
```

#### 5. **Dependency Inversion Principle (DIP)**
Depende de abstrações, não de implementações:
```java
private final List<ValidadorRegra> validadores; // Interface, não impl
```

### ✅ Clean Code

- **Nomenclatura Clara:** `validaSenha()`, `temCaracteresRepetidos()`, `ValidadorComprimento`
- **Métodos Pequenos:** Cada método faz uma coisa bem
- **Sem "Magic Numbers":** Constantes nomeadas (`COMPRIMENTO_MINIMO`)
- **Comentários Úteis:** JavaDoc para classes e métodos públicos
- **Tratamento de Erros:** Mensagens personalizadas e claras

### ✅ API Design

- **RESTful:** Usa HTTP POST corretamente
- **Versionamento:** Endpoint começa com `/v1/`
- **Nomes Descritivos:** `/verificador-de-senha/valida`
- **Respostas Estruturadas:** JSON com campos `valido` e `mensagem`
- **Status Codes:** 200 OK sempre (validação retorna false, não erro)

### ✅ Design Patterns

- **Strategy:** Validadores intercambiáveis
- **Dependency Injection:** Injeção via Spring
- **Template Method:** Fluxo padronizado de validação
- **Factory:** Spring cria instâncias automaticamente

## 🧪 Testes

### Executar Testes

```bash
mvn test
```

### Cobertura de Testes

A suite de testes inclui:

✅ **Testes de Contexto** - Aplicação carrega corretamente  
✅ **Testes de Validação Positiva** - Senhas válidas aceitam  
✅ **Testes Parametrizados** - Múltiplos cenários por regra  
✅ **Testes de Comprimento** - Senhas curtas rejeitadas  
✅ **Testes de Composição** - Falta de maiúscula, minúscula, etc.  
✅ **Testes de Repetição** - Caracteres duplicados rejeitados  
✅ **Testes de Múltiplos Erros** - Mensagens concatenadas

### Exemplo de Teste Parametrizado

```java
@ParameterizedTest
@DisplayName("Deve rejeitar senhas sem letra maiúscula")
@ValueSource(strings = {"senha@1234", "abc123@d", "test1234!"})
void testSenhaSemMaiuscula(String senha) {
    SenhaInput input = new SenhaInput(senha);
    SenhaOutPut output = service.validaSenha(input);
    
    assertFalse(output.valido());
    assertTrue(output.mensagem().contains("maiúscula"));
}
```

## 🛠️ Tecnologias

| Tecnologia | Versão | Propósito |
|-----------|--------|----------|
| Java | 17+ | Linguagem de programação |
| Spring Boot | 3.x | Framework web |
| Spring Data | 3.x | Injeção de dependência |
| Lombok | Latest | Redução de boilerplate |
| JUnit 5 | Latest | Testes unitários |
| Maven | 3.8+ | Gerenciador de dependências |
| Jakarta Validation | Latest | Validação de entrada |

## 📝 Exemplo Completo

### 1. Fazer Requisição

```bash
curl -X POST http://localhost:8080/v1/verificador-de-senha/valida \
  -H "Content-Type: application/json" \
  -d '{"senha":"MyPassword123!"}'
```

### 2. Fluxo Interno

```
1. VerificadorController recebe SenhaInput
2. @Valid valida notBlank
3. Service.validaSenha() é chamado
4. Service injeta 6 ValidadorRegra
5. Cada validador é executado
6. Erros são coletados
7. Response é construído
8. JSON é retornado
```

### 3. Resposta

```json
{
  "valido": true,
  "mensagem": "Senha válida."
}
```

## 🔄 Como Adicionar Nova Regra

### Passo 1: Criar Implementação

```java
@Component
public class ValidadorLengthMaximo implements ValidadorRegra {
    @Override
    public boolean valida(String senha) {
        return senha.length() <= 20;
    }
    
    @Override
    public String getMensagemErro() {
        return "A senha não pode ter mais de 20 caracteres.";
    }
}
```

### Passo 2: Spring Injeta Automaticamente

A classe será injetada na lista de validadores e usada automaticamente!

## 📄 Licença

Este projeto é fornecido como está para fins educacionais.

## ✉️ Contato

Para dúvidas ou sugestões, abra uma issue ou pull request.

---

**Desenvolvido com ❤️ seguindo as melhores práticas de engenharia de software.**
