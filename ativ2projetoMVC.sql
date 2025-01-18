create database ativ2projetoMVC;

use ativ2projetoMVC;

CREATE TABLE Cliente (
    id_cliente INT PRIMARY KEY NOT NULL,
    nome VARCHAR(100),
    sexo VARCHAR(4),
    cpf VARCHAR(11),
    endereco VARCHAR(100),
    email VARCHAR(100),
    telefone VARCHAR(15),
    dataNascimento DATE,
    login VARCHAR(20),
    senha VARCHAR(20)
);

CREATE TABLE Conta (
    id_conta INT PRIMARY KEY NOT NULL,
    saldo NUMERIC(7, 2),
    tipo VARCHAR(10),
    dataCriacao DATETIME,
    id_cliente INT,
    FOREIGN KEY(id_cliente) REFERENCES Cliente(id_cliente)
);

CREATE TABLE Cartao (
    id_cartao INT PRIMARY KEY NOT NULL,
    tipo VARCHAR(20),
    limite NUMERIC(7, 2),
    dataVencimento DATE,
    numero BIGINT,
    cvv INT,
    id_conta INT,
    FOREIGN KEY(id_conta) REFERENCES Conta(id_conta)
);

