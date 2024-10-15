CREATE DATABASE clinica

Use clinica

USE master 
GO
DROP DATABASE clinica 

CREATE DATABASE clinica
go
USE clinica 

CREATE TABLE paciente(
Numero_Beneficiario             INT              NOT NULL,
Nome                            VARCHAR(100)     NOT NULL,
Logradouro                      VARCHAR(100)     NOT NULL,
Numero                          INT              NOT NULL,
CEP                             CHAR(8)          NOT NULL,
Complemento                     VARCHAR(255)     NOT NULL,
Telefone                        CHAR(11)         NOT NULL
PRIMARY KEY (Numero_Beneficiario)
)
GO

CREATE TABLE especialidade(
Id_especialidade				INT				NOT NULL,
Especialidade					VARCHAR(100) 	NOT NULL
PRIMARY KEY (Id_especialidade)
)
GO
 
CREATE TABLE medico(
Codigo						INT				NOT NULL,
Nome						VARCHAR(100)    NOT NULL,
Lorgadouro                  VARCHAR(200)	NOT NULL,
Numero                      INT				NOT NULL,
CEP							CHAR(8)			NOT NULL,
Complemento					VARCHAR(255)    NOT NULL,
Contato                     VARCHAR(11)		NOT NULL,
Id_Especialidade			INT		        NOT NULL
PRIMARY KEY (Codigo)
FOREIGN KEY (Id_Especialidade) REFERENCES especialidade(Id_especialidade)
)
GO

CREATE TABLE consulta (
Numero_Beneficiario				INT				NOT NULL,
Codigo							INT				NOT NULL,
Data_Hora						DATE			NOT NULL,
Observacao						VARCHAR(255)	NOT NULL
PRIMARY KEY (Numero_Beneficiario, Codigo, Data_Hora)
FOREIGN KEY (Numero_Beneficiario) REFERENCES paciente(Numero_Beneficiario),
FOREIGN KEY (Codigo)	REFERENCES medico (Codigo)
)

--BULK INSERT
INSERT INTO paciente VALUES
(99901, 'Washington Silva', 'R. Anhaia', 150, '02345000', 'Casa','922229999'),
(99902, 'Luis Ricardo', 'R. Voluntários da Pátria', 2251, '03254010', 'Bloco B. Apto 25','923450987'),
(99903, 'Maria Elisa', 'Av. Aguia de Haia', 1188, '06987020', 'Apto 1208','912348765'),
(99904, 'José Araujo', 'R. XV de Novembro', 18, '03678000', 'Casa','945674312'),
(99905, 'Joana Paula', 'R. 7 de Abril', 97, '01214000', 'Conjunto 3 - Apto 801','912095674')

INSERT INTO especialidade VALUES
(1, 'Otorrinolaringolopista'),
(2, 'Urologista'),
(3, 'Geriatra'),
(4, 'Pediatra')

INSERT INTO medico VALUES
(100001, 'Ana Paula', 'R. 7 de Abril', 256, '03698000', 'Casa','915689456',1),
(100002, 'Maria Aparecida', 'Av. Brasil', 32, '02145070', 'Casa','923235454',1),
(100003, 'Lucas Borges', 'Av. do Estado', 3210, '05241000', 'Apto 205','915689456',2),
(100004, 'Gabriel Oliveira', 'Av. Dom Helder Camara', 350, '03145000', 'Apto 602','932458745',3)

INSERT INTO consulta VALUES
(99901,100002,'2021-09-04 13:20','Infecção Urinaria'),
(99902,100003,'2021-09-04 13:15','Gripe'),
(99901,100001,'2021-09-04 12:20','Infecção Garganta')

--Adicionar a coluna dia_atendimento para médico
/*
100001 – Passa a atender na 2a feira
100002 – Passa a atender na 4a feira
100003 – Passa a atender na 2a feira
100004 – Passa a atender na 5a feira
Atualizar todos
*/
ALTER TABLE medico ADD dia_atendimento CHAR(10)

UPDATE medico SET dia_atendimento = '2º Feira'
WHERE Codigo = 100001

UPDATE medico SET dia_atendimento = '4º Feira'
WHERE Codigo = 100002

UPDATE medico SET dia_atendimento = '2º Feira'
WHERE Codigo = 100003

UPDATE medico SET dia_atendimento = '5º Feira'
WHERE Codigo = 100004

ALTER TABLE medico
ALTER COLUMN dia_atendimento CHAR(10) NOT NULL;

-- Deletar Especialide Pediatria
DELETE especialidade 
WHERE Id_especialidade = 4

DELETE especialidade 
WHERE Especialidade = 'Pediatria'

-- ALtera o nome da coluna
EXEC sp_rename 'medico.dia_atendimento', 'dia_semana_atendimento', 'COLUMN';

--Atualizar os dados do médico Lucas Borges que passou a residir à Av. Bras Leme, no. 876, apto 504, CEP 02122000
UPDATE medico SET Lorgadouro = 'Av. Bras Leme', Numero =  876, Complemento = 'apto 504', CEP = '02122000'
WHERE Nome = 'Lucas Borges';

-- Mudar o tipo de dado da observação da consulta para VARCHAR(200)
ALTER TABLE consulta ALTER COLUMN Observacao VARCHAR(200)

SELECT * FROM medico
SELECT * FROM especialidade
SELECT * FROM consulta

	