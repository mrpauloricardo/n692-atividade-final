# Mini Sistema de Cadastro e Consulta de Usuários

Este é um mini sistema de cadastro e consulta de usuários, desenvolvido com Java (Back-End) e HTML, CSS e JavaScript (Front-End).

## Funcionalidades

- Cadastro de usuários com nome, CPF, celular e email.
- Consulta de usuários por CPF.

## Como Usar

1. Clone o repositório:

   ```bash
   git clone https://github.com/mrpauloricardo/n692-atividade-final.git

2. Navegue até o diretório do projeto:

    ```bash
    cd n692-atividade-final

3. Compile e execute o servidor Java:

    ```bash
    javac -cp .;http.jar Main.java
    java -cp .;http.jar Main

4. Abra o arquivo index.html no seu navegador para acessar o front-end do sistema.

## Dependências

- Java 8 ou superior.
- Biblioteca http.jar para o servidor HTTP em Java.

## Estrutura do Projeto

- Main.java: Arquivo principal que inicia o servidor HTTP.
- CadastrarHandler.java: Handler para a rota de cadastro de usuários.
- ConsultarHandler.java: Handler para a rota de consulta de usuários.
- index.html, cadastro.html, consulta.html, style.css, cadastro.js, consulta.js: Arquivos do front-end.

## Observações

- Certifique-se de ter o Java e a biblioteca http.jar instalados e configurados corretamente antes de executar o servidor.
- O front-end foi testado nos seguintes navegadores: Chrome, Firefox e Edge.