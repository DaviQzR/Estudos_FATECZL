CREATE DATABASE TarefaCursor
GO

USE TarefaCursor
GO

CREATE TABLE curso (
    codigo INT,
    nome VARCHAR(50),
    duracao INT,
    PRIMARY KEY (codigo)
)

-- Inserindo dados na tabela curso
INSERT INTO curso (Codigo, Nome, Duracao)
VALUES
(48, 'Análise e Desenvolvimento de Sistemas', 2880),
(51, 'Logística', 2880),
(67, 'Polímeros', 2880),
(73, 'Comércio Exterior', 2600),
(94, 'Gestão Empresarial', 2600)

CREATE TABLE disciplinas (
    codigo VARCHAR(10),
    nome VARCHAR(100),
    carga_horaria INT,
    PRIMARY KEY (codigo)
)

-- Inserir dados na tabela Disciplinas
INSERT INTO disciplinas (Codigo, Nome, Carga_Horaria)
VALUES
('ALG001', 'Algoritmos', 80),
('ADM001', 'Administração', 80),
('LHW010', 'Laboratório de Hardware', 40),
('LPO001', 'Pesquisa Operacional', 80),
('FIS003', 'Física I', 80),
('FIS007', 'Físico Química', 80),
('CMX001', 'Comércio Exterior', 80),
('MKT002', 'Fundamentos de Marketing', 80),
('INF001', 'Informática', 40),
('ASI001', 'Sistemas de Informação', 80)

CREATE TABLE disciplina_curso (
    codigo_disciplina VARCHAR(10),
    codigo_curso INT,
    FOREIGN KEY (codigo_disciplina) REFERENCES disciplinas (codigo),
    FOREIGN KEY (codigo_curso) REFERENCES curso (codigo)
)

-- Inserir dados na tabela Disciplina_Curso
INSERT INTO disciplina_curso (codigo_disciplina, codigo_curso)
VALUES
('ALG001', 48),
('ADM001', 48),
('ADM001', 51),
('ADM001', 73),
('ADM001', 94),
('LHW010', 48),
('LPO001', 51),
('FIS003', 67),
('FIS007', 67),
('CMX001', 51),
('CMX001', 73),
('MKT002', 51),
('MKT002', 94),
('INF001', 51),
('INF001', 73),
('ASI001', 48),
('ASI001', 94)

-- Criação da função
CREATE FUNCTION fn_curso_disciplina (@codigo_curso INT)
RETURNS @tabela_saida TABLE (
    codigo_disciplina VARCHAR(10),
    nome_disciplina VARCHAR(100),
    carga_horaria_disciplina INT,
    nome_curso VARCHAR(100)
)
AS
BEGIN
    DECLARE @nome_curso VARCHAR(100)

    -- Pegando o nome do curso
    SELECT @nome_curso = Nome FROM curso WHERE codigo = @codigo_curso

    -- Cursor para percorrer as disciplinas do curso
    DECLARE c CURSOR FOR
        SELECT d.codigo, d.nome, d.carga_horaria
        FROM disciplinas d
        INNER JOIN disciplina_curso dc ON d.codigo = dc.codigo_disciplina
        WHERE dc.codigo_curso = @codigo_curso

    -- Variáveis para armazenar os dados da disciplina
    DECLARE @codigo_disciplina VARCHAR(10)
    DECLARE @nome_disciplina VARCHAR(100)
    DECLARE @carga_horaria_disciplina INT

    OPEN c
    FETCH NEXT FROM c INTO @codigo_disciplina, @nome_disciplina, @carga_horaria_disciplina

    -- Laço para o processamento das disciplinas
    WHILE @@FETCH_STATUS = 0
    BEGIN
        INSERT INTO @tabela_saida (codigo_disciplina, nome_disciplina, carga_horaria_disciplina, nome_curso)
        VALUES (@codigo_disciplina, @nome_disciplina, @carga_horaria_disciplina, @nome_curso)

        FETCH NEXT FROM c INTO @codigo_disciplina, @nome_disciplina, @carga_horaria_disciplina
    END

    CLOSE c
    DEALLOCATE c
    RETURN
END

SELECT * FROM fn_curso_disciplina(48)
