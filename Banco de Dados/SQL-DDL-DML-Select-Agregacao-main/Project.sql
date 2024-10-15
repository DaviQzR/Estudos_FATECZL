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
id			INT				NOT NULL, IDENTITY (1,1),
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


--a) Adicionar User
--(6; Joao; Ti_joao; 123mudar; joao@empresa.com)
INSERT INTO users (name, username, password, email)
VALUES (6, 'Joao', 'Ti_joao', '123mudar', 'joao@empresa.com');


--b) Adicionar Project
--(10004; Atualização de Sistemas; Modificação de Sistemas Operacionais nos PC's; 12/09/2014)
INSERT INTO projects (id, name, description, date)
VALUES (10004, 'Atualização de Sistemas', 'Modificação de Sistemas Operacionais nos PC''s', '12/09/2014');

--c) Consultar:

-- 1) Quantos projetos não tem usuários associados a ele. A coluna deve chamar
--qty_projects_no_users

SELECT 
	COUNT(proj.id) AS Qty_projects_no_users
FROM
	projects proj
LEFT JOIN 
	users_has_projects up ON proj.id = up.id_projects
WHERE
	up.id_users IS NULL

--Id do projeto, nome do projeto, qty_users_project (quantidade de usuários por
--projeto) em ordem alfabética crescente pelo nome do projeto
SELECT
	proj.id AS project_id,
	proj.name AS project_name,
	COUNT(up.id_users) AS qty_users_project
FROM
	projects proj
LEFT JOIN
	users_has_projects up ON proj.id = up.id_projects
GROUP BY
	proj.id, proj.name
ORDER BY 
	proj.name ASC
	 
SELECT * from users
SELECT * from projects
SELECT * from users_has_projects

