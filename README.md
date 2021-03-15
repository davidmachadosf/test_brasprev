
# Teste Brasprev (v1.0.0)

![Em construção](emConstrucao.gif)

## Código Fonte (GitHub)

O código fonte da aplicação foi disponibilizado no GitHub pelo link: 

https://github.com/davidmachadosf/test_brasprev.git

## Deploy (Heroku)

Configurou-se o github para que tono novo commit na branch master automaticamente publice a versão recente da aplicação na plataforma Heroku. A homepage web da aplicação, uma página simples para conferência dos dados gravados na base de dados, pode ser acessada pela url:

https://test-brasprev.herokuapp.com/

## Checagem de status (Actuator)

Pde ser feita uma checagem básica do status da aplicação pelos seguintes endpoints gerados pela ferramenta *Actuator*:

https://test-brasprev.herokuapp.com/actuator/health - verifica se a aplicação iniciou sem problemas

https://test-brasprev.herokuapp.com/actuator/env - exibe todas as variáveis do ambiente onde se encontra a aplicação

## Auxílio de desenvolvimento

Durante o desenvolvimento o conteúdo da base de dados pode ser verificado em:

https://test-brasprev.herokuapp.com/showUsuarios - exibe o conteúdo da tabesa de usuários do sistema

https://test-brasprev.herokuapp.com/showClientes - exibe conteúdo da tabela de clientes cadastrados

Optou-se por não se colocar verificações de segurança nesta página por ser apenas uma conferência de dados para a fase de desenvolvimento da aplicvação, Isto pode ser feito posteriormente ou pode-se eliminar completamente tais páginas, que só são úteis nesta fase.


## Base de Dados

Utilizou-se uma base de dados PostGres também hospedada na plataforma Heroku, como a aplicação. Decidiu-se por criar uma base de dados volátil, ou seja, ela é apagada e recriada após cada restart da aplicação, comportamento que pode ser facilmente alterado se necessário.

Neste exemplo simples a base de dados é composta de apenas duas tabelas: 

`tb01_usuarios` 

Login (chave primária) dos usuários cadastrados no sistema, bem como o hash de suas senhas (as senhas não são armazenada como texto legível na base) e uma lista de Autorizações (Roles) de cada usuário, que controla quais os serviços ele pode acessar. A estrutura desta tabela é a seguinte:

campo | tipo | chave 
----- | ---- | ------
login | VARCHAR( 10) | PRIMARY KEY
roles | VARCHAR( 50) |
hash  | VARCHAR(255) |

`tb02_clientes`

Clientes cadastrados no sistema pelos usuários habilitados para isto (com Role=EDIT, vide item seguinte), com número de CPF (chave primária), nome e dados de endereço composto de logradouro, bairro, cidade, estado (sigla) e CEP. A estrutura desta tabela na base é a seguinte:

campo | tipo | chave 
----- | ---- | ------
cpf        | VARCHAR(11) | PRIMARY KEY
nome       | VARCHAR(50) |
logradouro | VARCHAR(50) |
bairro     | VARCHAR(50) |
cidade     | VARCHAR(50) |
estado     | VARCHAR( 2) |
cep        | VARCHAR( 9) |


## Autorizações de acesso aos serviços

As autorizações de acesso que podem ser atribuidas aos usuários são os seguintes:

`ADMIN` tem permissão para:
>* cadastrar usuários novos
>* alterar a senha de qualquer usuário (incluindo sua própria)
>* alterar nome e autorizações de acess de acesso de usuários
>* deletar usuários
>* procurar e visualizar usuarios

`EDIT` tem permissão para:
>* alterar a própria senha 
>* cadastrar clientes novos
>* alterar dados clientes
>* deletar clientes 

`VIEW` tem permissão para:
>* alterar a própria senha 
>* procurar e visualizar  clientes 

`OWNER` tem permissão para:
>* indica que usuário só pode acessar o serviço se estiver alterando seus próprios dados



## Serviço de obtenção de token

Para acessar os serviços que requerem autorizações de acesso é necessário, inicialmente, adquirir um token fornecendo login e senha para o serviço (trocando *login* e *senha* na URL abaixo pelo login e senha do usuário):

https://test-brasprev.herokuapp.com/auth/login/senha

Será devolvido um token, que deve ser incluido no Header das requisições. Isto será explicado posteriormente, nas instruções de como testar a API Rest da aplicação.


## Serviços de alteração e verificação de senhas


## Serviços de criação/substituição

Inclusão ou substituição de registros, baseados nas respectivas chaves primárias de cada entidade, são feitas com uma chamada REST utilizando o método **POST**. Deve ser preenchido no *body* da chamada uma estrutura json com os dados a serem inseridos ou substituidos. Esta operação substitui integralmente todos os campos, e campos não fornecidos na requisição são enviados como *NULL*.   

`usuarios`
* `POST` https://test-brasprev.herokuapp.com/usuarios
Passando o body:
```
{
    "login": "ze001",
    "roles": "EDIT,VIEW",
    "hash": "12121212"
}
```

`clientes`
* `POST` https://test-brasprev.herokuapp.com/clientes
Passando o body:
```
{
    "cpf": "00000000015",
    "nome": "Zé 0555",
    "logradouro": "Rua Comprida, 300000",
    "bairro": "Vila Sézamo",
    "cidade": "Taubaté",
    "estado": "SP",
    "cep": "05000-002"
}    
```

## Serviços de alteração de campos

Alterações em registros que não sejam chaves primárias são feitas com uma chamada REST utilizando o método **PATCH**. Deve ser preenchido no *body* da chamada uma estrutura json com os dados a serem alterados e informado na URI a chave primária do registro. Neste caso não há necessidade de enviar todos os campos: campos que não forem fornecidos na estrutura não serão alterados.   

`usuarios`
* `PATCH` https://test-brasprev.herokuapp.com/usuarios/LOGIN_DO_USUARIO
Passando o body:
```
{
    "roles": "ADMIN,VIEW"
}
```

`clientes`
* `PATCH` https://test-brasprev.herokuapp.com/clientes/CPF_DO_CLIENTE
Passando o body:
```
{
    "bairro": "Morro do Macaco",
    "cidade": "Pindamonhangaba",
    "cep": "04900-002"
} 
```

## Serviços de remoção de registros

Remoção desão feitas com uma chamada REST utilizando o método **DEL**. A chave primária do registro a ser deletado é passada na URI de chamada.  

`usuarios`
* `DEL` https://test-brasprev.herokuapp.com/usuarios/LOGIN_DO_USUARIO

`clientes`
* `DEL` https://test-brasprev.herokuapp.com/clientes/CPF_DO_CLIENTE


## Serviços de busca
Alguns dos métodos de pesquisa comuns à interface JPA foram expostos como serviços rest. Podemos obter uma listagem de todos os métodops de busca expostos pela chamada:

http://localhost:8080/clientes/search

Assim estão disponíveis as seguintes buscas de cliente:

* 1

* 2

* 3


:metal:


