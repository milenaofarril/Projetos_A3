### Trabalho de Web
#### Esse trabalho de fins para mostrar o que foi aprendido nas aulas com JS, HTML e CSS


### Como Testar

 1. Passo um
    - npm install
 2. Passo dois
   Nesse passo é a criação do banco dados e das tabelas, foi utilizado o sequelize para melhor pratica, para inicializar esse comandos precisa navegar pelo CMD até a pasta 'public'
    Depois disso, realize as criação da database e tabelas
    - npx sequelize-cli db:create
    - npx sequelize-cli db:migrate

 3. Passo três
    Agora para rodar todo o projeto
    - npm start

 4. Foi realizado a criação de um Json com todos as rotas que podem ser testadas
    - Para alguns testes são realizado apenas com Tokens gerados no rota de login, são eles
        - Listas de usuarios
        - Lista de usuário com ID 
        - Deletar usuários
        - Cadastrar figurinhas
        - Buscas todas as figurinhas com usuários
        - Atualização de usuários
        - Atualização de figurinhas
 5. Utilização do Json
    - Pode ser usuado em qualquer app de JSON
        - O utlizado foi o Postman...       

