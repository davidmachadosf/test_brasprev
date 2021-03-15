
# Teste Brasprev (OBS.: documento ainda em desenvolvimento!)

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

### tb01_usuarios: 

Login (chave primária) dos usuários cadastrados no sistema, bem como o hash de suas senhas (as senhas não são armazenada como texto legível na base) e uma lista de Autorizações (Roles) de cada usuário, que controla quais os serviços ele pode acessar. A estrutura desta tabela é a seguinte:

campo | tipo | chave 
----- | ---- | ------
login | VARCHAR( 10) | PRIMARY KEY
roles | VARCHAR( 50) |
hash  | VARCHAR(255) |

### tb02_clientes: 

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
* cadastrar usuários novos
* alterar a senha de qualquer usuário (incluindo sua própria)
* alterar nome e autorizações de acess de acesso de usuários
* deletar usuários
* procurar e visualizar usuarios

`EDIT` tem permissão para:
* alterar a própria senha 
* cadastrar clientes novos
* alterar dados clientes
* deletar clientes 

`VIEW` tem permissão para:
* alterar a própria senha 
* procurar e visualizar  clientes 

`OWNER` tem permissão para:
* indica que usuário só pode acessar o serviço se estiver alterando seus próprios dados



## Serviço de login

Para acessar os serviços que requerem autorizações de acesso é necessário, inicialmente, adquirir um token fornecendo login e senha para o serviço:

/auth/{login}/{senha}

Será devolvido um token, que deve ser incluido no Header das requisições.


## Serviços de inclusão 

### usuarios
  * post (login, senha, roles)

### clientes
   * post (cpf,nome, endereço(log, bairro,cidade, estado cep))

## Serviços de alteração

### usuarios
  * senha: post (login, senha)
  
  * autorizações (roles) de acesso: post (login, roles)

### clientes
  * post (cpf,nome, endereço(log, bairro,cidade, estado cep))  


## Serviços de remoção

### usuarios
  * remove(login) CHECAR SE NÂO É O PRÓPRIO ADMIN

### clientes
  * remove(cpf)


## Serviços de busca
Alguns dos métodos de pesquisa comuns à interface JPA foram expostos como serviços rest. Podemos obter uma listagem de todos os métodops de busca expostos pela chamada:

http://localhost:8080/clientes/search

Assim estão disponíveis as seguintes buscas de cliente:

* 1

* 2

* 3


:metal:


