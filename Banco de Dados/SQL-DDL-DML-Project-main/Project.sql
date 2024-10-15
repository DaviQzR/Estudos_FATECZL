/*
	Ter o privilegio para fazer alteração
*/
USE master
 GO

 /*
 Botão de Emergência
 */
 DROP DATABASE project
 GO

 /*
	Criação do Banco de Dados
 */
 CREATE DATABASE project
 GO
 
  /*
   Usar o Banco de Dados
 */
 USE project
 GO

 --Criação das Tabelas
 CREATE TABLE projects (
id			INT         NOT NULL IDENTITY (10001,1),
name        VARCHAR(45) NOT NULL,
description VARCHAR(45) NOT NULL UNIQUE,
date        DATE        NOT NULL CHECK (date > '2014-09-01')
PRIMARY KEY (id)
)
GO

CREATE TABLE users (
id			INT				NOT NULL IDENTITY (1,1),
name		VARCHAR(45)		NOT NULL,
username	VARCHAR(45)		NOT NULL,
password	VARCHAR(45)		NOT NULL DEFAULT ('123mudar'),
email		VARCHAR(45)		NOT NULL 
PRIMARY kEY	(id)
)
GO

CREATE TABLE users_has_projects(
id_users			INT				NOT NULL,
id_projects         INT				NOT NULL
PRIMARY KEY (id_users, id_projects)
FOREIGN KEY (id_users)    REFERENCES users(id),
FOREIGN KEY (id_projects) REFERENCES projects(id)
)
GO

--Altera os campos da Tabela
ALTER TABLE users ALTER COLUMN username VARCHAR(10)
ALTER TABLE users ALTER COLUMN password VARCHAR(08)

--Inserindo os dados da tabela users
INSERT INTO users (name, username, email)
VALUES ('Maria', 'Rh_maria', 'maria@empresa.com')

INSERT INTO users (name, username, password,email)
VALUES ('Paulo', 'Ti_paulo', '123@456', 'paulo@empresa.com')

INSERT INTO users (name, username, email)
VALUES ('Ana', 'Rh_ana', 'ana@empresa.com')

INSERT INTO users (name, username, email)
VALUES ('Clara', 'Ti_clara', 'clara@empresa.com')

INSERT INTO users (name, username, password,email)
VALUES ('Aparecido', 'Rh_apareci', '55@!cido', 'aparecido@empresa.com')

--Inserindo dados na tabela projects
INSERT INTO projects (name, description, date)
VALUES ('Re-folha', 'Refatoração das Folhas', '2014-09-05')

INSERT INTO projects (name, description, date)
VALUES ('Manutenção PC´s', 'Manutenção PC´s', '2014-09-06')

--Alterando a definição da coluna description na tabela projects
ALTER TABLE projects
ALTER COLUMN description VARCHAR(45) NULL;

INSERT INTO projects (name, description, date)
VALUES ('Auditoria', Null, '2014-09-07')

INSERT INTO users_has_projects VALUES
(1,10001),
(5,10001),
(3,10003),
(4,10002),
(2,10002)

-- O projeto de Manutenção atrasou, mudar a data para 12/09/2014
UPDATE projects
SET date = '2014-09-12'
WHERE id = 10002;

-- O username de aparecido (usar o nome como condição de mudança) está feio, mudar para Rh_cido
UPDATE users
SET username = 'Rh_cido'
WHERE id = 5;

-- Mudar o password do username Rh_maria (usar o username como condição de mudança) para 888@*, mas a condição deve verificar se o password dela ainda é 123mudar
UPDATE users
SET password = '888@*'
WHERE username = 'Rh_maria' AND password = '123mudar' ;

-- O user de id 2 não participa mais do projeto 10002, removê-lo da associativa
DELETE FROM users_has_projects
WHERE id_users =2;

SELECT * from users
SELECT * from projects
SELECT * from users_has_projects

