/*
	Criação do Banco de Dados
*/
CREATE DATABASE Exercicio1
GO

/*
	Usar o Banco de Dados
*/

USE Exercicio1
GO

/* 
	Ter o privilegio para fazer alteração
*/

USE master
GO

/*
	Botão de Emergencia
*/

DROP DATABASE Exercicio1
GO

-- Criação de tabelas

CREATE TABLE Aluno (
RA			INT			 NOT NULL,
Nome	    VARCHAR(50)  NOT NULL,
Sobrenome   VARCHAR(50)  NOT NULL,
Rua         VARCHAR(30)  NOT NULL,
Numero      CHAR(15)	 NOT NULL,	
Bairro      VARCHAR(30)  NOT NULL,
CEP         CHAR(8)      NOT NULL,
Telefone    CHAR(11)     NULL
PRIMARY KEY (RA)
)
GO

CREATE TABLE Cursos (
Codigo		   INT         NOT NULL,
Nome		   VARCHAR(30) NOT NULL,
Carga_Horaria  INT         NOT NULL,
Turno		   VARCHAR(7)  NOT NULL
PRIMARY KEY (Codigo)
)
GO

CREATE TABLE Disciplinas (
Codigo			INT			NOT NULL,
Nome			VARCHAR(20) NOT NULL,
Carga_Horaria	INT			NOT NULL,
Turno			VARCHAR(20) NOT NULL,
Semestre		INT			NOT NULL
PRIMARY KEY (Codigo)
)
GO

INSERT INTO Aluno VALUES 
(12345,'José','Silva','Almiranre Noronha','236','Jardim São Paulo','1589000','698775287' ),
(12346,'Ana','Silva','Anhaia','1568','Barra Funda','3569000','25698526'),
(12347,'Mario','Santos','XV de Novembro','1841','Centro','1020030',NULL),
(12348,'Marcia','Neves','Voluntários da Patria','255','Santana','2785090', '78964152')

INSERT INTO Cursos VALUES
(1,	'Informática',	2800,	'Tarde'),
(2,	'Informática',	2800,	'Noite'),
(3,	'Logística',	2650,	'Tarde'),
(4,	'Logística',	2650,	'Noite'),
(5,	'Plásticos',	2500,	'Tarde'),
(6,	'Plásticos',	2500,	'Noite')

INSERT INTO Disciplinas VALUES
(1,	'Informática',		  4,'Tarde',1),
(2,	'Informática',		  4,'Noite',1),
(3,	'Quimica',			  4,'Tarde',1),
(4,	'Quimica',            4,'Noite',1),
(5,	'Banco de Dados I',	  2,'Tarde',3),
(6,	'Banco de Dados I',	  2,'Noite',3),
(7,	'Estrutura de Dados', 4,'Tarde',4),
(8,	'Estrutura de Dados', 4,'Noite',4)


--Consultar:
-- Nome e sobrenome, como nome completo dos Alunos Matriculados
SELECT 
	Nome + ' ' + Sobrenome AS NomeCompleto
FROM
	Aluno

-- Rua, nº , Bairro e CEP como Endereço do aluno que não tem telefone
SELECT
	Rua, Numero, Bairro, CEP
FROM
	Aluno
WHERE Telefone IS NULL

-- Telefone do aluno com RA 12348
SELECT 
	Telefone
FROM 
	Aluno
WHERE RA = 12348

--Nome e Turno dos cursos com 2800 horas
SELECT
	Nome, Turno
FROM
	Cursos
WHERE Carga_Horaria = 2800

-- O semestre do curso de Banco de Dados I noite
SELECT Semestre
FROM Disciplinas
WHERE Nome = 'Banco de Dados I' AND Turno = 'Noite'