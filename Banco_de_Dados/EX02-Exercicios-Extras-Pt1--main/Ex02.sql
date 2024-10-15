/*
	Criação do Banco de Dados
*/
CREATE DATABASE Exercicio2
GO

/*
	Usar o Banco de Dados
*/

USE Exercicio2
GO

/* 
	Ter o privilegio para fazer alteração
*/

USE master
GO

/*
	Botão de Emergencia
*/

DROP DATABASE Exercicio2
GO

-- Criação de tabelas


CREATE TABLE Cliente (
Codigo		INT			  NOT NULL,
Nome		VARCHAR(30)   NOT NULL,
Logradouro  VARCHAR(50)   NOT NULL,
Numero		INT			  NOT NULL,
Bairro		VARCHAR(50)	  NOT NULL,
Telefone    CHAR(11)      NOT NULL,
Carro       CHAR(07)      NOT NULL
PRIMARY KEY (Codigo)
FOREIGN KEY (Carro) REFERENCES Carro (Placa)
)
GO

CREATE TABLE Carro (
Placa		CHAR(07)		NOT NULL,
Marca       VARCHAR(20)		NOT NULL,
Modelo		VARCHAR(20)		NOT NULL,
Cor			VARCHAR(15)     NOT NULL,
Ano			INT				NOT NULL
PRIMARY KEY (Placa)
)
GO

CREATE TABLE Pecas (		
Codigo			INT			 NOT NULL,
Nome			VARCHAR(30)  NOT NULL,
Valor			DECIMAL(7,2) NOT NULL
PRIMARY KEY (Codigo)
)
GO

INSERT INTO Pecas VALUES
(1,	'Vela',				70.0),
(2,	'Correia Dentada',	125.0),
(3,	'Trambulador',		90.0),
(4,	'Filtro de Ar',		30.0)



INSERT INTO Carro VALUES
('AFT9087',	    'VW',		'Gol',		'Preto',	2007),
('DXO9876',		'Ford',		'Ka',		'Azul',		2000),
('EGT4631',		'Renault',	'Clio',		'Verde',	2004),
('LKM7380',		'Fiat',		'Palio',	'Prata',	1997),
('BCD7521',		'Ford',		'Fiesta',	'Preto',	1999)

INSERT INTO Cliente VALUES
(1,'João Alves',		'R. Pereira Barreto',	1258,	'Jd. Oliveiras',	'2154-9658',	'DXO9876'),
(2,'Ana Maria',			'R. 7 de Setembro',		259,	'Centro',			'9658-8541',	'LKM7380'),
(3,'Clara Oliveira',	'Av. Nações Unidas',	10254,	'Pinheiros',		'2458-9658',	'EGT4631'),
(4,'José Simões',		'R. XV de Novembro',	36,		'Água Branca',		'7895-2459',	'BCD7521'),
(5,'Paula Rocha',		'R. Anhaia',			548,	'Barra Funda',		'6958-2548',	'AFT9087')

CREATE TABLE Servico (
Carro			CHAR(07)		NOT NULL,
Peca			INT				NOT NULL,
Quantidade		INT				NOT NULL,
Valor			DECIMAL(7,2)	NOT NULL,
Data_Reparo  	DATE			NOT NULL
PRIMARY KEY (Carro, Peca, Data_Reparo)
FOREIGN KEY (Carro)        REFERENCES Carro(Placa),
FOREIGN KEY (Peca)  REFERENCES Pecas(Codigo)
)
GO

INSERT INTO Servico VALUES
('DXO9876',	1,	4,	280,'2020-08-01'),
('DXO9876',	4,	1,	30,	'2020-08-01'),
('EGT4631',	3,	1,	90,	'2020-08-02'),
('DXO9876',	2,	1,	125,'2020-08-07')

SELECT * FROM Servico

--Consultar em Subqueries
				
--Telefone do dono do carro Ka, Azul
SELECT
    Telefone AS "Telefone Dono"
FROM
    Cliente
WHERE
    Carro = (
        SELECT
            Placa
        FROM
            Carro
        WHERE
            Modelo = 'Ka' AND Cor = 'Azul'
    );

--Endereço concatenado do cliente que fez o serviço do dia 02/08/2009

SELECT
    Logradouro AS "Endereco Completo"
    
FROM Cliente
WHERE Codigo IN
(
    SELECT Placa
    FROM Carro
    WHERE Placa IN
    (
        SELECT Carro
        FROM Servico
        WHERE Data_Reparo = '2009-08-02'
    )
);

SELECT
    Logradouro + ' ' + CAST(Numero AS VARCHAR) + ', ' + Bairro AS "Endereco Completo"
FROM
    Cliente
WHERE
    Carro IN (
        SELECT
            Carro
        FROM
            Servico
        WHERE
            Data_Reparo = '2020-08-02'
 );

 /*				
Consultar:				
Placas dos carros de anos anteriores a 2001				
*/
SELECT 
 Placa
FROM Carro
 WHERE Ano <= 2001

/*
Marca, modelo e cor, concatenado dos carros posteriores a 2005				
*/
SELECT
 Marca + ' ' +  Modelo + ' ' + Cor AS "Informações do Veículo"
FROM Carro
 WHERE Ano >= 2006
/*
Código e nome das peças que custam menos de R$80,00				
*/
SELECT 
 CAST(Codigo AS VARCHAR) + ' - ' + Nome AS "Código e Nome das Peças"
FROM Pecas
 WHERE Valor < 80.00

