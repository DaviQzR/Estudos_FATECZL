/*
	Criação do Banco de Dados
*/
CREATE DATABASE locadora
GO

/*
	Usar o Banco de Dados
*/

USE locadora
GO

/* 
	Ter o privilegio para fazer alteração
*/

USE master
GO

/*
	Botão de Emergencia
*/

DROP DATABASE locadora
GO

-- Criação de tabelas
CREATE TABLE Filme (
id			INT			NOT NULL IDENTITY (1001,1),
titulo      VARCHAR(40) NULL,
ano			INT			NULL CHECK (ano < = 2021)
PRIMARY KEY (id)
)
GO

CREATE TABLE Estrela(
id		  INT		    NOT NULL IDENTITY (9901,1),
nome      VARCHAR(50)   NOT NULL
PRIMARY KEY(id)
)
GO

CREATE TABLE Filme_Estrela(
id_filme	INT			NOT NULL,
id_estrela  INT			NOT NULL
PRIMARY KEY (id_filme,id_estrela)
FOREIGN KEY (id_filme)   REFERENCES Filme (id),
FOREIGN KEY (id_estrela) REFERENCES Estrela (id)
)
GO

CREATE TABLE DVD (
num			     INT		NOT NULL IDENTITY(10001,1),
data_fabricacao  DATE		NOT NULL CHECK (data_fabricacao < GETDATE()),
id_filme		 INT		NOT NULL	
PRIMARY KEY (num) 
FOREIGN KEY (id_filme) REFERENCES Filme(id)
)
GO


CREATE TABLE Cliente (
num_cadastro		INT		     NOT NULL IDENTITY (5501,1),
nome				VARCHAR(70)  NOT NULL,
logradouro			VARCHAR(150) NOT NULL,
num					INT			 NOT NULL CHECK (num >=0),
cep					CHAR(8)		 NOT NULL CHECK (LEN(cep) = 8)
PRIMARY KEY (num_cadastro)
)
GO

CREATE TABLE Locacao (
num_dvd             INT				NOT NULL,	
num_cadastro        INT				NOT NULL, 
data_locacao		DATE			NOT NULL DEFAULT (GETDATE()),
data_devolucao		DATE			NOT NULL,
valor				DECIMAL(7,2)    NOT NULL CHECK(valor >0)
PRIMARY KEY (num_dvd,num_cadastro,data_locacao)
FOREIGN KEY (num_dvd) REFERENCES DVD (num),
FOREIGN KEY (num_cadastro) REFERENCES Cliente(num_cadastro),
CONSTRAINT chk_data_locacao_data_devolucao CHECK(data_devolucao > data_locacao)
)
GO

ALTER TABLE Estrela
ADD nome_real VARCHAR(50) NULL;

ALTER TABLE Filme
ALTER COLUMN titulo VARCHAR(80) NULL;


INSERT INTO Filme VALUES
('Whiplash', 2015),
('Birdman', 2015),
('Interestelar',2014 ),
('A Culpa é das estrelas',2014),
('Alexandre e o Dia Terrível, Horrível, Espantoso e Horroroso',2014),
('Sing',2016)

INSERT INTO Estrela VALUES
('Michael Keaton', 'Michael John Douglas'),
('Emma Stone', 'Emily Jean Stone'),
('Miles Teller',NULL ),
('Steve Carell','Steven John Carell'),
('Jennifer Garner','Jennifer Anne Garner')

INSERT INTO Filme_Estrela VALUES
(1002,9901),
(1002,9902),
(1001,9903),
(1005,9904),
(1005,9905)

INSERT INTO DVD VALUES
('2020-12-02',1001),
('2019-10-18',1002),
('2020-04-03',1003),
('2020-12-02',1001),
('2019-10-18',1004),
('2020-04-03',1002),
('2020-12-02',1005),
('2019-10-18',1002),
('2020-04-03',1003)

ALTER TABLE Cliente
ALTER COLUMN cep CHAR(08) NULL;

INSERT INTO Cliente VALUES
('Matilde Luz','Rua Síria',150,'03086040'),
('Carlos Carreiro','Rua Bartolomeu Aires',1250,'04419110'),
('Daniel Ramalho','Rua Itajutiba',169,NULL),
('Roberta Bento','Rua Jayme Von Rosenburg',36,NULL),
('Rosa Cerqueira','Rua Arnaldo Simões Pinto', 235,'02917110')

INSERT INTO Locacao VALUES
(10001,5502,'2021-02-18','2021-02-21',3.50),
(10009,5502,'2021-02-18','2021-02-21',3.50),
(10002,5503,'2021-02-18','2021-02-19',3.50),
(10002,5505,'2021-02-20','2021-02-23',3.00),
(10004,5505,'2021-02-20','2021-02-23',3.00),
(10005,5505,'2021-02-20','2021-02-23',3.00),
(10001,5501,'2021-02-24','2021-02-26',3.50),
(10008,5501,'2021-02-24','2021-02-26',3.50)

--Os CEP dos clientes 5503 e 5504 são 08411150 e 02918190 respectivamente
UPDATE Cliente SET cep = '08411150' where num_cadastro = 5503
UPDATE Cliente SET cep = '02918190' where num_cadastro = 5504

--A locação de 2021-02-18 do cliente 5502 teve o valor de 3.25 para cada DVD alugado
UPDATE locacao
SET valor = 3.25
WHERE num_cadastro =5502 
AND data_locacao = '2021-02-18';

--A locação de 2021-02-24 do cliente 5501 teve o valor de 3.10 para cada DVD alugado
UPDATE locacao
SET valor = 3.10
WHERE num_cadastro = 5501
AND data_locacao = '2021-02-24';

--O DVD 10005 foi fabricado em 2019-07-14
UPDATE DVD SET data_fabricacao = '2019-07-14' WHERE num = 10005

--O nome real de Miles Teller é Miles Alexander Teller
UPDATE  Estrela SET nome_real ='Miles Alexander Teller' WHERE nome = 'Miles Teller'

--O filme Sing não tem DVD cadastrado e deve ser excluído
DELETE FROM Filme WHERE titulo ='Sing'

--Consultas

--1) Fazer um select que retorne os nomes dos filmes de 2014
SELECT titulo 
From Filme
WHERE ano = 2014

--2) Fazer um select que retorne o id e o ano do filme Birdman
SELECT id, ano 
FROM Filme
WHERE titulo = 'Birdman'

--3) Fazer um select que retorne o id e o ano do filme que tem o nome terminado por plash
SELECT id,ano
FROM Filme
WHERE titulo LIKE '%plash'

--4) Fazer um select que retorne o id, o nome e o nome_real da estrela cujo nome começa com Steve
SELECT id, nome, nome_real 
FROM Estrela
WHERE nome LIKE 'Steve%'

--5) Fazer um select que retorne FilmeId e a data_fabricação em formato (DD/MM/YYYY) (apelidar de fab) dos filmes fabricados a partir de 01-01-2020
SELECT id_filme,
				CONVERT(CHAR(10),data_fabricacao,103) AS fab
FROM DVD
WHERE data_fabricacao > '2020-01-01'

--6) Fazer um select que retorne DVDnum, data_locacao, data_devolucao, valor e valor com multa de acréscimo de 2.00 da locação do cliente 5505
SELECT num_dvd, 
		CONVERT(CHAR(10), data_locacao, 103) AS data_locação,CONVERT(CHAR(10), data_devolucao, 103) AS data_devolução, valor,
		'R$ ' + CAST(CAST(valor + 2.00 AS DECIMAL(7,2)) AS VARCHAR(8)) AS Valor_Multa
FROM Locacao
WHERE num_cadastro lIKE 5505

--7) Fazer um select que retorne Logradouro, num e CEP de Matilde Luz
SELECT logradouro, num,cep	 FROM Cliente WHERE nome lIKE 'Matilde Luz'


--8) Fazer um select que retorne Nome real de Michael Keaton
SELECT nome_real FROM Estrela WHERE nome LIKE 'Michael Keaton' 

--9) Fazer um select que retorne o num_cadastro, o nome e o endereço completo, concatenando (logradouro, numero e CEP), apelido end_comp, dos clientes cujo ID é maior ou igual 5503
SELECT num_cadastro,
		nome,
		logradouro + ', ' + CAST(num AS VARCHAR(5)) 
			+ ' - CEP: ' + cep AS end_compl		
FROM Cliente
WHERE num_cadastro > 5503


Select * from Filme
Select * from Estrela
Select * from DVD
Select * from Cliente
Select * from Locacao