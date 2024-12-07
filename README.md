# CRUD Projeto - Spring Boot

Este projeto é um sistema de gerenciamento de clientes, contas e cartões bancários. Ele permite criar, visualizar, editar e excluir registros, com um backend desenvolvido em Java utilizando o framework Spring Boot.

## Funcionalidades

- **Lista de Contas/Cliente/Cartão**
- **Cadastro de Conta/Cliente/Cartão**
- **Edição de Conta/Cliente/Cartão**
- **Exclusão de Conta/Cliente/Cartão**
- **Busca por ID**


## Tecnologias Utilizadas

- **Spring Boot**: Framework para construção de aplicações Java baseadas em Spring.
- **Thymeleaf**: Template Engine para renderização de páginas HTML dinâmicas.
- **Spring Web**: Para construção de APIs RESTful e controle de fluxo da aplicação web.
- **Java 17**: Linguagem de programação utilizada para desenvolver o backend.
- **Spring DevTools**: Para melhorar a experiência de desenvolvimento com recarregamento automático da aplicação.


## Estrutura de Diretórios

- `src/main/java/` - Contém o código Java da aplicação.
- `src/main/resources/templates/` - Contém os templates Thymeleaf para renderização das páginas HTML.
- `src/main/resources/static/` - Contém arquivos estáticos como CSS.
  
## Como Rodar o Projeto

### Pré-requisitos

- JDK 17 ou superior
- Maven (ou Gradle)
- IDE de sua preferência (como IntelliJ IDEA, Eclipse ou VSCode)

### Passos para Rodar

1. Clone o repositório:

```bash
   git clone https://github.com/lauragabs/Projeto-CRUD
   cd Projeto-CRUD
``` 

2. Compile o projeto utilizando Maven:

```bash
mvn clean install
```

3. Execute a aplicação:

```bash
mvn spring-boot:run
```

4. Acesse o sistema no navegador em:

```bash
http://localhost:8080
```

Licença
Este projeto está licenciado sob os termos da Licença MIT. Consulte o arquivo [LICENSE](LICENSE) para obter mais detalhes.