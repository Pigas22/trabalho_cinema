# 🎥 Projeto de Banco de Dados - Gerenciador de Cinema
## 📒 Sobre o Projeto:
A idealização do projeto foi proposta pelo professor [Howard Roatti](https://www.linkedin.com/in/howardroatti/) como uma avaliação durante a matéria, Banco de Dados no 4° período do curso Sistemas de Informação no Centro Universitário, FAESA.

Para esse projeto, foi escolhido como tema pela equipe, o Sistema de Gerenciamento de Seções de Diversos Cinemas.

#### 📸 Link do vídeo demonstrativo no youtube: **<u>https://youtu.be/GwyLAv1S0tE?feature=shared</u>**

#### 🛠️ Tecnologias e Ferramentas utilizadas no Projeto:
<div align="center">

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Vscode](https://img.shields.io/badge/Vscode-007ACC?style=for-the-badge&logo=visual-studio-code&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-white?style=for-the-badge&logo=postgresql)
![Git](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white) 

</div>

### ✅ Requisitos:
Para o funcionamento correto do projeto, é necessário que tenha as seguintes tecnologias instaladas:
- **`JAVA`** (17 ou mais recente);
- **`PostgreSQL`** (16 ou mais recente).

### ❓  Como Utilizar:
Após a instalação dos Requisitos acima, é necessário configurar a conexão do Banco de Dados com a Aplicação, para isso, navegue até a Classe [<u>__Database.java__</u>](https://github.com/Pigas22/trabalho_cinema/blob/c8aaba6bc29fb496b014c2d475c2566c4b5adddf/src/conexion/Database.java), segue abaixo as váriaveis à serem configuradas na classe, sendo assim:

#### 📘 Váriaveis referentes à conexão:
- **_`NOME_DATABASE`_** : Refere-se ao nome do Banco de Dados que será criado para a utilização da aplicação.
- **_`PORTA_SERVICO`_** : Refere-se à porta da máquina utilizada pelo serviço do PostgreSQL (configurável durante a instalação)
- **_`URL_POSTGRES`_** : Refere-se à URL utilizada pelo [**`JDBC`**](https://napoleon.com.br/glossario/o-que-e-jdbc-java-database-connectivity/) para encontrar e conectar no banco, nesse caso, está configurado para encontrar o banco padrão `"postgres"`, pois a aplicação constrói o banco a partir dessa URL, caso o mesmo não exista.
```Java
private static final String NOME_DATABASE = "ger_cinema_db";
private static final String PORTA_SERVICO = "5432";
private static final String URL_POSTGRES = "jdbc:postgresql://localhost:" + PORTA_SERVICO + "/postgres";
```

#### 📕 Váriaveis referentes aos usuários do PostgreSQL:
- **_`USER_POSTGRES`_** : Usuário Master do PostgreSQL (configurável durante a instalação).
- **_`PASSWORD_POSTGRES`_** : Senha do Usuário Master do PostgreSQL (configurável durante a instalação).
```Java
// 1 - Usuário padrão PostgreSQL
private static final String USER_POSTGRES = "postgres";
private static final String PASSWORD_POSTGRES = "postgres";

// 2 - Usuário VM
private static final String USER_POSTGRES = "labdatabase";
private static final String PASSWORD_POSTGRES = "labDatabase2022";
``` 
Acima estão citados dois exemplos de usuários utilizados no projeto, onde:
- O Primeiro é o usuário padrão, caso você não o altere durante a instalação do PostgreSQL.
- O Segundo é o usuário utilizado pela VM apresentada aos alunos durante as aulas.

Obs.: Lembre-se deixar apenas um ativo, para evitar conflito. 

Após a configuração das váriaveis da classe _Database.java_, basta executar a Classe Principal, [**App.java**](https://github.com/Pigas22/trabalho_cinema/blob/24e1ebcb8a76c8cbb0014b1f08c2aaaf2fddff07/src/App.java). 
Assim que iniciada, apresentará uma pergunta referente à reiniciar o Banco, caso seja a 1° execução do programa, é **EXTREMAMENTE** recomendado que responda à esta pergunta com `"Sim"`. Dessa forma o Banco de Dados será criado e inicializado com suas tabelas e seus respectivos dados. 

### 🥳 Pronto!!! Agora, a aplicação está configurada para ser utilizada.

### 📚 Estrutura e Organização:
- [<u>__Diagrama__</u>](https://github.com/Pigas22/trabalho_cinema/blob/7de34f80d0dfecd6e82d4931a69262986e2eaa1c/Diagrama) : Pasta referente ao Diagrama Relacional de Banco de Dados desenvolvido.
    - A aplicação possue 5 tabelas/entidades, sendo elas: ENDERECO, CINEMA, VENDA, SECAO e FILME.
- [<u>__lib__</u>](https://github.com/Pigas22/trabalho_cinema/blob/7de34f80d0dfecd6e82d4931a69262986e2eaa1c/lib) : Nesta pasta se encontra as bibliotecas externas utilizadas no desenvolvimento.
    - No caso deste projeto, apenas [<u>a biblioteca</u>](https://github.com/Pigas22/trabalho_cinema/blob/7de34f80d0dfecd6e82d4931a69262986e2eaa1c/lib/postgresql-42.7.4.jar) que possibilita a comunicação entre a Linguagem de Programação (Java) e o Banco de Dados (PostgreSQL), por meio do [**`JDBC`**](https://napoleon.com.br/glossario/o-que-e-jdbc-java-database-connectivity/).
- [<u>__SQL__</u>](https://github.com/Pigas22/trabalho_cinema/blob/2ee1da0ac8e987bb843b5cd08df91a2388eee71c/SQL) : Nesta pasta estão todos os arquivos SQL desenvolvidos.
    - [create_tabelas.sql](https://github.com/Pigas22/trabalho_cinema/blob/7de34f80d0dfecd6e82d4931a69262986e2eaa1c/SQL/create_tabelas.sql) : Script onde possue o comando de Criação de cada Tabela.
    - [drop_tabelas.sql](https://github.com/Pigas22/trabalho_cinema/blob/7de34f80d0dfecd6e82d4931a69262986e2eaa1c/SQL/drop_tabelas.sql) : Script onde possue o comando de Exclusão de cada Tabela, caso existam. 
    - [insert_tables.sql](https://github.com/Pigas22/trabalho_cinema/blob/aa5a71b27cc5fc0f7dfacb4685ebec24762749c5/SQL/insert_tables.sql) : Script onde possue o comando de Inserção dos dados "padrões" de cada Tabela.
    - [re_endereco_cinema.sql](https://github.com/Pigas22/trabalho_cinema/blob/2ee1da0ac8e987bb843b5cd08df91a2388eee71c/SQL/re_endereco_cinema.sql) : Script de Relatório onde possue o comando de Consulta, unindo duas tabelas. Retorna o nome do Cinema e sua respectiva rua da Tabela Endereco.
    - [re_informacao.sql](https://github.com/Pigas22/trabalho_cinema/blob/2ee1da0ac8e987bb843b5cd08df91a2388eee71c/SQL/re_informacao.sql) : Script de Relatório onde possue o comando de Consulta, unindo duas tabelas. Retorna detalhes sobre as Seções e seus respectivos filmes da Tabela Filme, cadastrados na aplicação.
    - [re_soma_ingresso.sql](https://github.com/Pigas22/trabalho_cinema/blob/2ee1da0ac8e987bb843b5cd08df91a2388eee71c/SQL/re_soma_ingressos.sql) : Script de Relatório onde possue o comando de Consulta, unindo três tabelas. Retorna uma somatória do valor total arrecadado em cada filme.
- [<u>__src__</u>](https://github.com/Pigas22/trabalho_cinema/blob/0646d7543cfb091b2cae8e47de8e71f5365e9747/src) : Nesta pasta estão todos os códigos-fonte na linguagem Java.
    - [conexion](https://github.com/Pigas22/trabalho_cinema/blob/c8aaba6bc29fb496b014c2d475c2566c4b5adddf/src/conexion) : Diretório designado à comunicação direta do Banco de Dados, com métodos gerais utilizados sob o banco.
    - [controllers](https://github.com/Pigas22/trabalho_cinema/blob/0646d7543cfb091b2cae8e47de8e71f5365e9747/src/controllers) : Diretório designado às classes Controladoras, onde fazem a gestão entre as classes Moldes e o Banco.
    - [models](https://github.com/Pigas22/trabalho_cinema/blob/c48fa1143cad9debc10ad68a168abdf870e933e9/src/models) : Diretório designado às classes Moldes, representação dos registros das Tabelas.
    - [reports](https://github.com/Pigas22/trabalho_cinema/blob/2ee1da0ac8e987bb843b5cd08df91a2388eee71c/src/reports) : Diretório designado à leitura dos relatórios (arquivos SQL com o prefixo "re_").
    - [utils](https://github.com/Pigas22/trabalho_cinema/blob/0646d7543cfb091b2cae8e47de8e71f5365e9747/src/utils) : Diretório designado às classes Auxiliares na aplicação, contendo formatações, menus, gerencimento de arquivos e etc... 
    - [**App.java**](https://github.com/Pigas22/trabalho_cinema/blob/24e1ebcb8a76c8cbb0014b1f08c2aaaf2fddff07/src/App.java) : Classe principal do programa, classe onde possue o método Main e o loop principal da aplicação. 

## 🫂 Participantes no Projeto:
- Davi Tambara Rodrigues;
- Samuel Eduardo Rocha de Souza;
- Thiago Holz Coutinho.
