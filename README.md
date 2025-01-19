# CRUD Projeto - Spring Boot

Este projeto é um sistema de gerenciamento de clientes, contas e cartões bancários. Ele permite criar, visualizar, editar e excluir registros, com um backend desenvolvido em Java utilizando o framework Spring Boot.

## Funcionalidades

- **Lista de Contas/Cliente/Cartão**: Visualize todas as contas, clientes e cartões cadastrados.
- **Cadastro de Conta/Cliente/Cartão**: Adicione novas contas, clientes e cartões ao sistema.
- **Edição de Conta/Cliente/Cartão**: Edite informações de contas, clientes e cartões existentes.
- **Exclusão de Conta/Cliente/Cartão**: Remova contas, clientes e cartões do sistema.
- **Busca por ID**: Encontre contas, clientes e cartões específicos pelo ID.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para construção de aplicações Java baseadas em Spring.
- **Thymeleaf**: Template Engine para renderização de páginas HTML dinâmicas.
- **Spring Web**: Para construção de APIs RESTful e controle de fluxo da aplicação web.
- **Java 17**: Linguagem de programação utilizada para desenvolver o backend.
- **Spring DevTools**: Para melhorar a experiência de desenvolvimento com recarregamento automático da aplicação.
- **MySQL**: Banco de dados relacional utilizado para armazenar os dados.

## Estrutura de Diretórios

- `src/main/java/` - Contém o código Java da aplicação.
- `src/main/resources/templates/` - Contém os templates Thymeleaf para renderização das páginas HTML.
- `src/main/resources/static/` - Contém arquivos estáticos como CSS.
- `src/main/resources/application.properties` - Configurações da aplicação, como detalhes do banco de dados.

## Como Rodar o Projeto

### Pré-requisitos

- JDK 17 ou superior
- Maven (ou Gradle)
- IDE de sua preferência (como IntelliJ IDEA, Eclipse ou VSCode)
- MySQL

### Passos para Rodar

1. Clone o repositório:

```bash
   git clone https://github.com/lauragabs/Projeto-CRUD
   cd Projeto-CRUD
``` 

2. Configure o banco de dados MySQL

3. Configure o arquivo application.properties para o seu banco de dados

4. Compile o projeto utilizando Maven:

```bash
mvn clean install
```

5. Execute a aplicação:

```bash
mvn spring-boot:run
```

6. Acesse o sistema no navegador em:

```bash
http://localhost:8080
```

## Licença
Este projeto está licenciado sob os termos da Licença MIT. Consulte o arquivo [LICENSE](LICENSE) para obter mais detalhes.