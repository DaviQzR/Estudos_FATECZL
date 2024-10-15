
/*
	Criação do Banco de Dados
*/
CREATE DATABASE Exercicio2
GO

/*
	Usar o Banco de Dados
*/

USE Exercicio3
GO

/* 
	Ter o privilegio para fazer alteração
*/

USE master
GO

/*
	Botão de Emergencia
*/

DROP DATABASE Exercicio3
GO

-- Criação de tabelas

CREATE TABLE PACIENTES (
cpf						CHAR(11)				NOT NULL,
nome					VARCHAR(100)			NOT NULL,
rua_end					VARCHAR(100)			NOT NULL,
numero_end				INT						NOT NULL,
bairro_end				VARCHAR(100)			NOT NULL,
telefone				CHAR(10)				NULL,
data_nasc				DATE					NOT NULL
PRIMARY KEY (cpf)
)
GO

CREATE TABLE MEDICO (
codigo					INT						NOT NULL,
nome					VARCHAR(100)			NOT NULL,
especialidade			VARCHAR(100)			NOT NULL
PRIMARY KEY (codigo)
)
GO

CREATE TABLE PRONTUARIO (
datinha					DATE					NOT NULL,
cpf_paciente			CHAR(11)				NOT NULL,
codigo_medico			INT						NOT NULL,
diagnostico				VARCHAR(100)			NOT NULL,
medicamento				VARCHAR(100)			NOT NULL
FOREIGN KEY (cpf_paciente) REFERENCES PACIENTES (cpf),
FOREIGN KEY (codigo_medico) REFERENCES MEDICO (codigo),
PRIMARY KEY (datinha, cpf_paciente, codigo_medico)
)
GO

--*********************************************************--
---Insert Pacientes

INSERT INTO PACIENTES (cpf, nome, rua_end, numero_end, bairro_end, telefone, data_nasc)
VALUES
('35454562890',	'José Rubens',	'Campos Salles',	'2750',	'Centro',	'21450998',	'1954-10-18'),
('29865439810',	'Ana Claudia',	'Sete de Setembro',	'178',	'Centro',	'97382764',	'1960-05-29'),
('82176534800',	'Marcos Aurélio',	'Timóteo Penteado',	'236',	'Vila Galvão',	'68172651',	'1980-09-24'),
('12386758770',	'Maria Rita',	'Castello Branco',	'7765',	'Vila Rosália', 'NULL',	'1975-03-30'),
('92173458910',	'Joana de Souza',	'XV de Novembro',	'298',	'Centro',	'21276578',	'1944-04-24')
GO
--*********************************************************--
---Insert Medico

INSERT INTO MEDICO (codigo, nome, especialidade)
VALUES
('1',	'Wilson Cesar',	'Pediatra'),
('2',	'Marcia Matos',	'Geriatra'),
('3',	'Carolina Oliveira',	'Ortopedista'),
('4',	'Vinicius Araujo',	'Clínico Geral')
GO
--*********************************************************--
---Insert Protuario
INSERT INTO PRONTUARIO (datinha, cpf_paciente, codigo_medico, diagnostico, medicamento)
VALUES
('2020-09-10',	'35454562890',	'2',	'Reumatismo',	'Celebra'),
('2020-09-10',	'92173458910',	'2', 'Renite Alérgica',	'Allegra'),
('2020-09-12',	'29865439810',	'1',	'Inflamação de garganta',	'Nimesulida'),
('2020-09-13',	'35454562890',	'2',	'H1N1',	'Tamiflu'),
('2020-09-15',	'82176534800',	'4',	'Gripe',	'Resprin'),
('2020-09-15',	'12386758770',	'3',	'Braço Quebrado',	'Dorflex + Gesso')
GO


--Consultar:
--Nome e Endereço (concatenado) dos pacientes com mais de 50 anos
SELECT 
    nome,
    CONCAT(rua_end, ', ', numero_end, ', ', bairro_end) AS endereco,
    DATEDIFF(YEAR, data_nasc, GETDATE()) AS idade
FROM 
    PACIENTES
WHERE 
    DATEDIFF(YEAR, data_nasc, GETDATE()) > 50;

--Qual a especialidade de Carolina Oliveira
SELECT especialidade
FROM MEDICO
WHERE nome = 'Carolina Oliveira';

-- Consultar o medicamento para o diagnóstico de Reumatismo
SELECT medicamento
FROM PRONTUARIO
WHERE diagnostico = 'Reumatismo';

--Consultar em subqueries:
-- Consultar diagnóstico e medicamento do paciente José Rubens
SELECT datinha AS Data_Consulta, diagnostico, medicamento
FROM PRONTUARIO
WHERE cpf_paciente = '35454562890';

-- Consultar nome e especialidade do(s) médico(s) que atenderam José Rubens
SELECT 
    M.nome AS Nome_Medico,
    CASE 
        WHEN LEN(E.especialidade) <= 3 THEN E.especialidade
        ELSE LEFT(E.especialidade, 3) + '.'
    END AS Especialidade
FROM 
    PRONTUARIO P
JOIN 
    MEDICO M ON P.codigo_medico = M.codigo
JOIN 
    MEDICO E ON P.codigo_medico = E.codigo
WHERE 
    P.cpf_paciente = '35454562890';


-- Consultar informações dos pacientes do médico Vinicius
SELECT 
    REPLACE(P.CPF, SUBSTRING(P.CPF, 4, 1), '.') + REPLACE(SUBSTRING(P.CPF, 4, 1), '-', '') AS CPF,
    P.nome AS Nome,
    CONCAT(P.rua_end, ', ', P.numero_end, ' - ', P.bairro_end) AS Endereco,
    ISNULL(P.telefone, '-') AS Telefone
FROM 
    PRONTUARIO PR
JOIN 
    PACIENTES P ON PR.cpf_paciente = P.cpf
JOIN 
    MEDICO M ON PR.codigo_medico = M.codigo
WHERE 
    M.nome = 'Vinicius Araujo';

-- Consultar quantos dias se passaram desde a consulta de Maria Rita até hoje
SELECT 
    DATEDIFF(DAY, P.datinha, GETDATE()) AS Dias_Passados
FROM 
    PRONTUARIO P
JOIN 
    PACIENTES PA ON P.cpf_paciente = PA.cpf
WHERE 
    PA.nome = 'Maria Rita';

-- Alterar o telefone da paciente Maria Rita
UPDATE PACIENTES
SET telefone = '98345621'
WHERE nome = 'Maria Rita';

-- Alterar o endereço da paciente Joana de Souza
UPDATE PACIENTES
SET 
    rua_end = 'Voluntários da Pátria',
    numero_end = 1980,
    bairro_end = 'Jd. Aeroporto'
WHERE nome = 'Joana de Souza';


SELECT * FROM MEDICO
SELECT * FROM PACIENTES
SELECT * FROM PRONTUARIO