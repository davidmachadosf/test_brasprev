DROP TABLE IF EXISTS tb01_usuarios;
CREATE TABLE tb01_usuarios(login VARCHAR(10) PRIMARY KEY, hash VARCHAR(255), nivel integer);

DROP TABLE IF EXISTS tb02_clientes;
CREATE TABLE tb02_clientes(cpf VARCHAR(11) PRIMARY KEY, nome VARCHAR(50), logradouro VARCHAR(50), bairro VARCHAR(50), cidade VARCHAR(50), estado VARCHAR(2), cep VARCHAR(9));