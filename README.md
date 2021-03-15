
# test_brasprev

## Código Fonte
O código fonte da aplicação está disponibilizada no GitHub pelo link: 


https://github.com/davidmachadosf/test_brasprev.git


## Critérios de Mudança de Versões
As versões da aplicação são marcadas com X.Y.Z, sendo X, Y e Z inteiros sequenciais começando em zero (exceto por Z, que sempre começa em 1).

Os incrementos em Z indicam alterações que não afetam os testes anteriores nas versões X.Y.(Z-n), onde n>=1. Podem ser inclusões de documentação, comentários em código, ou melhorias e verificações em tempo de execução que, mesmo que importantes, não afetam os testes nas versões anteriores, que continuam funcionando.

Os incrementos em Y indicam alterações importantes na aplicação, principalmente correções de bugs. Aplicações de versões X.Y.Z e X.(Y-1).* onde n>=1 se comportarão diferentes se os casos de teste utilizados estiverem relacionados ao bug corrigido. Mas não representam mudanças estruturais muito profundas na arquitetura da aplicação.

Os incrementos em X indicam alterações MUITO importantes na aplicação, inclusive com incompatibilidade de testes em relação às versões (x-n).*.* onde onde n>=1... São incrementos importantes no projeto, como inclusão de recursos de criptografia, autenticação, incremento de domínios (tabelas a mais a serem administradas), etc... 

Mas vale notar que no início do desenvolvimento, antes de ser apresentada a versão inicial 1.0.0, estas evoluções podem ocorrer ligeiramente mais lentas que esta proposta acima. Ou seja, embora alterações importantes possam acontecer entre as versões 0.2.* e 0.3.*, na versão X=0 elas ainda podem ser consideradas como desenvolvimento incompleto, e provavelmente são alterações mais importantes que aquelas sugeridas acima.


## Deploy
Foi configurada para que commit novo na branch master seja automaticamente publicada na plataforma Heroku, na qual a aplicação pode ser acessada pela url:

https://test-brasprev.herokuapp.com/

## Checagem de status
Uma verificação inicial no status da aplicação pode ser checada consultando os seguintes links gerados automaticamente pela ferramenta actuator:


https://test-brasprev.herokuapp.com/actuator/health

https://test-brasprev.herokuapp.com/environment...

## Auxílio de desenvolvimento
Durante o desenvolvimento o conteúdo da base de dados pode ser verificado em:

https://test-brasprev.herokuapp.com/showUsuarios

https://test-brasprev.herokuapp.com/showClientes

Optou-se por não se colocar verificações de segurança nesta págida, o que pode ser feito posteriormente ou pode-se eliminar completamente tais páginas, que só são úteis na fase de desenvolvimento da aplicação.


## Base de Dados

Decidiu-se por criar uma base de dados volátil, ou seja, ela é apagada e recriada após cada restart da aplicação, porém este comportamento pode ser facilmente alterado de necessário.

Neste exemplo simples a base de dados é composta de apenas duas tabelas: 

### tb01_usuarios: 
usuários cadastrados no sistema, bem como o hash de suas senhas de acesso (a senha nunca é armazenada como texto legível na base) e um indicador dos papéis (roles) e permissões atribuidos ao usuário

a estrutura desta tabela na base é a seguinte:

### tb02_clientes: 
clientes cadastrados no sistema pelos usuários habilitados para isto, con numero de cpf, nome e dados de endereço.

a estrutura desta tabela na base é a seguinte:


## Niveis de acesso aos serviçosa
Os niveis de acesso que podem ser atribuitos aos usuários são os seguintes:

ADMINISTRADOR: tem permissão para:
* cadastrar usuários novos
* alterar a senha de qualquer usuário (incluindo sua própria)
* alterar nome e niveis de acesso de usuários
* deletar usuários
OBS:como a senha nunca é gravada na base, o reset de senhas pode ser feito fornecendo uma senha nova ou deixando o sistema gerar uma aleatoria: recuperar uma senha esquecida é impossível, ou computacionalmente inviável num tempo razoável

CADASTRADOR: tem permissão para:
* alterar a própria senha 
* cadastrar clientes novos
* anterar clientes
* deletar clientes 
* procurar e visualizar  clientes 

VISUALIZADOR:
* alterar a própria senha 
* procurar e visualizar  clientes 


## Serviço de login


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


