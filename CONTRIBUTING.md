##  Contribuindo no Projeto"
Esse guia tem como o objetivo auxiliar novos contribuidores a realizarem suas primeiras contribuições no projeto. Com o intuito de manter a organização e padronização do projeto, é importante seguir as instruções abaixo.
### 1) Faça um **Fork** deste Repositório
Acesse a página principal do repositório e clique no botão "Fork" no canto superior direito da página.
> [!NOTE]  
> Um "fork" no GitHub é uma cópia de um repositório que pode ser criada por qualquer usuário. <br>
> Para mais detalhes, acesse a documentação do GitHub: [Criar fork de um repositório](https://docs.github.com/pt/pull-requests/collaborating-with-pull-requests/working-with-forks/fork-a-repo).

### 2) Clone localmente
Abra o seu Git Bash e digite o comando `git clone` seguido da URL do seu fork para clonar o seu repositório localmente. Por exemplo:
```bash
git clone https://github.com/SEU_USERNAME/sgi-back.git
```
Pressione enter, e uma cópia do seu fork no GitHub será criada localmente.

### 3) Crie uma nova **branch**
Utilize o comando `git checkout -b` para criar e alternar para a nova branch e nomeie-a no formato `tipoCommit/funcionalidade`
> Exemplo: `git checkout -b feat/adicionarClasseX`

### 4) Adicione suas alterações à "staging area"
Utilize o comando `git add` para adicionar sua alteração (nesse caso o arquivo markdown criado)  à "staging area" no Git.
>Exemplo: `git add UserServce.java`
### 5) Crie um Commit
Crie um commit e adicione a mensagem indicando a adição do seu perfil:
```bash
git commit -m"feat: add SEU_USERNAME profile"
```
>[!IMPORTANTE]
> Verifique a [`Convenção de Commits`](https://github.com/lmtsufape/sgi-back/main/CONTRIBUTING.md#conven%C3%A7%C3%A3o-de-commits) para escrever a mensagem do seu commit de forma clara e padronizada.

### 6) Envie as Alterações para o seu Repositório Remoto
Envie as alterações realizadas no seu repositório local para a branch `feat/community/SEU_USERNAME` no seu repositório remoto com o comando:
```bash
git push origin feat/community/SEU_USERNAME
```

### 7) Crie um **Pull Request**.
Acesse a página do seu repositório no GitHub e clique no botão "Compare & pull request" para criar um novo Pull Request. Adicione um título e uma descrição para o seu Pull Request e clique em "Create pull request".
É recomendado que você adicione uma descrição detalhada sobre as alterações realizadas e um reviewer para revisar o seu Pull Request.

## Convenção de Commits

| Tipo de Commit | Descrição                                                          | Exemplo                               |
|----------------|--------------------------------------------------------------------|---------------------------------------|
| `feat`         | Adiciona uma nova funcionalidade ao projeto.                       | `feat: add USENAME.md profile`        |
| `fix`          | Corrige um bug ou problema no projeto.                             | `fix: fixed issue fix#IssueNumber`    |
| `docs`         | Altera a documentação do projeto.                                  | `docs: update README.md`              |
| `style`        | Realiza mudanças na aparência, sem alterar a funcionalidade.       | `style: add EFFECTNAME to COMPONENT`  |
| `refactor`     | Realiza mudanças no código que não alteram a funcionalidade.       | `refactor: refactor at CLASSNAME`     |
| `test`         | Adiciona ou modifica testes no projeto.                            | `test: add unit test for UserService` |
| `chore`        | Realiza alterações no processo de build ou ferramentas auxiliares. | `chore: add .gitignore file`          |