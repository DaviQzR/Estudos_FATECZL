USE master
GO
DROP DATABASE agis
GO

CREATE DATABASE agis
GO
USE agis
GO

/*
 Banco de Dados - Sistema AGIS
 Todas as queries SQL do sistema

 ÍNDICE:
 IND00 - Criação de Tabelas
 IND01 - Stored Procedures
 IND02 - User Defined Functions
 IND03 - Views
 IND04 - Inserções para teste

*/
--  IND00 - Criação de Tabelas
-------------------------------------------------------------------------------

CREATE TABLE aluno(
cpf						CHAR(11)		NOT NULL	UNIQUE,
ra						CHAR(9)			NOT NULL,
nome					VARCHAR(100)	NOT NULL,
nome_social				VARCHAR(100)	NULL,
data_nasc				DATE			NOT NULL,
telefone_celular		CHAR(11)		NOT NULL,
telefone_residencial	CHAR(11)		NULL,
email_pessoal			VARCHAR(200)	NOT NULL,
email_corporativo		VARCHAR(200)	NULL,
data_segundograu		DATE			NOT NULL,
instituicao_segundograu	VARCHAR(100)	NOT NULL,
pontuacao_vestibular	INT				NOT NULL,
posicao_vestibular		INT				NOT NULL,
ano_ingresso			CHAR(4)			NOT NULL,
semestre_ingresso		CHAR(1)			NOT NULL,
semestre_graduacao		CHAR(6)			NOT NULL,
ano_limite				CHAR(6)			NOT NULL,
curso_codigo			INT				NOT NULL,
data_primeiramatricula	DATE			NOT NULL,
turno					VARCHAR(10)		NOT NULL	DEFAULT('Tarde')
PRIMARY KEY(ra)
FOREIGN KEY(curso_codigo) REFERENCES curso(codigo)
)
GO
CREATE TABLE curso(
codigo					INT				NOT NULL,
nome					VARCHAR(100)	NOT NULL,
carga_horaria			INT				NOT NULL,
sigla					VARCHAR(10)		NOT NULL,
nota_enade				INT				NOT NULL
PRIMARY KEY(codigo)
)
GO
CREATE TABLE professor(
codigo					INT				NOT NULL,
nome					VARCHAR(100)	NOT NULL,
titulacao				VARCHAR(100)	NOT NULL
PRIMARY KEY(codigo)
)
GO
CREATE TABLE disciplina(
codigo					INT				NOT NULL,
nome					VARCHAR(100)	NOT NULL,
qtd_aulas				INT				NOT NULL,
horario_inicio			TIME			NOT NULL,
horario_fim				TIME			NOT NULL,
dia						VARCHAR(20)		NOT NULL,
curso_codigo			INT				NOT NULL,
professor_codigo		INT				NOT NULL
PRIMARY KEY(codigo)
FOREIGN KEY(curso_codigo) REFERENCES curso(codigo),
FOREIGN KEY(professor_codigo) REFERENCES professor(codigo)
)
GO
CREATE TABLE conteudo(
codigo					INT				NOT NULL,
descricao				VARCHAR(200)	NOT NULL,
codigo_disciplina		INT				NOT NULL
PRIMARY KEY(codigo)
FOREIGN KEY(codigo_disciplina) REFERENCES disciplina(codigo)
)
GO
CREATE TABLE matricula(
codigo					INT				NOT NULL,
aluno_ra				CHAR(9)			NOT NULL,
data_matricula			DATE			NOT NULL
PRIMARY KEY(codigo)
FOREIGN KEY(aluno_ra) REFERENCES aluno(ra)
)
GO
CREATE TABLE matricula_disciplina(
codigo_matricula		INT				NOT NULL,
codigo_disciplina		INT				NOT NULL,
situacao				VARCHAR(50)		NOT NULL,
qtd_faltas				INT				NOT NULL,
nota_final				CHAR(2)			NOT NULL
PRIMARY KEY(codigo_matricula, codigo_disciplina)
FOREIGN KEY(codigo_disciplina) REFERENCES disciplina(codigo),
FOREIGN KEY(codigo_matricula) REFERENCES matricula(codigo)
)
GO
CREATE TABLE aula(
matricula_codigo		INT				NOT NULL,
disciplina_codigo		INT				NOT NULL,
data_aula				DATE			NOT NULL,
presenca				INT				NOT NULL
PRIMARY KEY(matricula_codigo, disciplina_codigo, data_aula)
FOREIGN KEY(matricula_codigo) REFERENCES matricula(codigo),
FOREIGN KEY(disciplina_codigo) REFERENCES disciplina(codigo)
)
GO
CREATE TABLE dispensa(
aluno_ra				CHAR(9)			NOT NULL,
codigo_disciplina		INT				NOT NULL,
motivo					VARCHAR(200)	NOT NULL,
estado					VARCHAR(200)	NOT NULL
PRIMARY KEY(aluno_ra, codigo_disciplina)
FOREIGN KEY(aluno_ra) REFERENCES aluno(ra),
FOREIGN KEY(codigo_disciplina) REFERENCES disciplina(codigo)
)

-- IND01 - Stored Procedures
---------------------------------------------------------------------------------

-- Procedure de validação do cpf
---------------------------------------------------------------------------------

-- Início da procedure
CREATE PROCEDURE sp_validarcpf(@cpf CHAR(11), @valido BIT OUTPUT)
AS
DECLARE @soma1 INT,
	@soma2 INT,
	@cont INT,
	@digito1 INT,
	@digito2 INT

SET @cont = 1
SET @soma1 = 0
SET @soma2 = 0

IF LEN(@cpf) <> 11
BEGIN
	SET @valido = 0
	RAISERROR('CPF Inválido', 16, 1)
	RETURN
END

WHILE(@cont <= 9)	
BEGIN 
	SET @soma1 = @soma1 + (CAST(SUBSTRING(@cpf, @cont, 1) AS INT) * (11 - @cont))
	SET @cont = @cont + 1
END
SET @cont = 1
WHILE(@cont <= 10)
BEGIN
	SET @soma2 = @soma2 + (CAST(SUBSTRING(@cpf, @cont, 1) AS INT) * (12 - @cont))
	SET @cont = @cont + 1
END
IF((@soma1 % 11) <  2)
BEGIN
	SET @digito1 = 0
END
ELSE
BEGIN
	SET @digito1 = 11 - (@soma1 % 11)
END

IF((@soma2 % 11) < 2)
BEGIN
	SET @digito2 = 0
END
ELSE
BEGIN
	SET @digito2 = 11 - (@soma2 % 11)
END
IF @digito1 = CAST(SUBSTRING(@cpf, 10, 1) AS INT) AND @digito2 = CAST(SUBSTRING(@cpf, 11, 1) AS INT)
BEGIN
	SET @valido = 1
END
ELSE
BEGIN
	SET @valido = 0
END

CREATE TRIGGER t_validarcpf ON aluno
AFTER INSERT
AS
BEGIN
	DECLARE @cpf CHAR(11)
	SELECT @cpf = cpf FROM INSERTED

	DECLARE @soma1 INT,
	@soma2 INT,
	@cont INT,
	@digito1 INT,
	@digito2 INT

	SET @cont = 1
	SET @soma1 = 0
	SET @soma2 = 0

	IF LEN(@cpf) <> 11
	BEGIN
		ROLLBACK TRANSACTION
		RAISERROR('CPF Inválido - não tem 11 numeros', 16, 1)
		RETURN
	END

	WHILE(@cont <= 9)	
	BEGIN 
		SET @soma1 = @soma1 + (CAST(SUBSTRING(@cpf, @cont, 1) AS INT) * (11 - @cont))
		SET @cont = @cont + 1
	END
	SET @cont = 1
	WHILE(@cont <= 10)
	BEGIN
		SET @soma2 = @soma2 + (CAST(SUBSTRING(@cpf, @cont, 1) AS INT) * (12 - @cont))
		SET @cont = @cont + 1
	END
	IF((@soma1 % 11) <  2)
	BEGIN
		SET @digito1 = 0
	END
	ELSE
	BEGIN
		SET @digito1 = 11 - (@soma1 % 11)
	END

	IF((@soma2 % 11) < 2)
	BEGIN
		SET @digito2 = 0
	END
	ELSE
	BEGIN
		SET @digito2 = 11 - (@soma2 % 11)
	END
	IF @digito1 = CAST(SUBSTRING(@cpf, 10, 1) AS INT) AND @digito2 = CAST(SUBSTRING(@cpf, 11, 1) AS INT)
	BEGIN
		RETURN
	END
	ELSE
	BEGIN
		ROLLBACK TRANSACTION
		RAISERROR('CPF Inválido - valores invalidos', 16, 1)
		RETURN
	END
END

-- Procedure de validação da idade
------------------------------------------------------------------------------------

-- Início da procedure
CREATE PROCEDURE sp_validaridade(@dtnasc DATE, @valido BIT OUTPUT)
AS
IF(DATEDIFF(YEAR,@dtnasc,GETDATE()) < 16)
BEGIN
	SET @valido = 0
END
ELSE
IF(DATEDIFF(YEAR,@dtnasc,GETDATE())> 120)
BEGIN
	SET @valido = 0
END
ELSE
BEGIN
	SET @valido = 1
END
-- Fim da procedure

CREATE TRIGGER t_validaridade ON aluno
AFTER INSERT, UPDATE
AS
BEGIN
	DECLARE @dtnasc DATE
	SELECT @dtnasc = data_nasc FROM INSERTED
	IF((DATEDIFF(YEAR,@dtnasc,GETDATE()) < 16) OR (DATEDIFF(YEAR,@dtnasc,GETDATE()) > 120))
	BEGIN
		ROLLBACK TRANSACTION
		RAISERROR('Data de nascimento inválida', 16, 1)
		RETURN
	END
END

-- Procedure de geração do RA
--------------------------------------------------------------------------------------------

-- Início da procedure
CREATE PROCEDURE sp_gerarra(@ano CHAR(4), @sem CHAR(1), @ra CHAR(9) OUTPUT)
AS

DECLARE @existe BIT = 1
WHILE(@existe = 1)
BEGIN
	DECLARE @n1 CHAR(1) = CAST(RAND()*10+1 AS CHAR)
	DECLARE @n2 CHAR(1) = CAST(RAND()*10+1 AS CHAR)
	DECLARE @n3 CHAR(1) = CAST(RAND()*10+1 AS CHAR)
	DECLARE @n4 CHAR(1) = CAST(RAND()*10+1 AS CHAR)
	
	SET @ra = @ano + @sem + @n1 + @n2 + @n3 + @n4
	
	-- Verifica se o RA gerado já pertence a um aluno, caso contrário, outro RA vai ser gerado
	IF EXISTS(SELECT ra FROM aluno WHERE ra = @ra)
	BEGIN 
		SET @existe = 1
	END
	ELSE 
	BEGIN 
		SET @existe = 0
	END
END

-- Procedure de geração do Ano e Semestre limite
----------------------------------------------------------------------------------------------

-- Início da procedure
CREATE PROCEDURE sp_geraranolimite(@ano CHAR(4), @sem CHAR(1), @anolimite CHAR(6) OUTPUT)
AS
BEGIN
SET @ano = CAST((CAST(@ano AS INT) + 5) AS CHAR)

IF(@sem = '1')
BEGIN
	SET @sem = '2'
END
ELSE
BEGIN
	SET @sem = '1'
END
SET @anolimite = FORMATMESSAGE('%s/%s', @ano, @sem)
END

-- Procedure de geração do ano e semestre de ingresso
---------------------------------------------------------------------------------
CREATE PROCEDURE sp_geraringresso(@ano CHAR(4) OUTPUT, @sem CHAR(1) OUTPUT)
AS
DECLARE @mes INT

SELECT @ano = YEAR(GETDATE())
SELECT @mes = MONTH(GETDATE()) 
IF(@mes <= 6)
BEGIN
	SET @sem = 1
END
ELSE
BEGIN
	SET @sem = 2
END

-- Procedure IUD Aluno
----------------------------------------------------------------------------------------------

-- Início da procedure
CREATE PROCEDURE sp_iudaluno(@acao CHAR(1),
				 		     @cpf CHAR(11),
				 			 @ra CHAR(9) OUTPUT,
				 		     @nome VARCHAR(100),
				 		     @nomesocial VARCHAR(100),
				 		     @datanasc DATE,
							 @telefonecelular CHAR(11),
							 @telefoneresidencial CHAR(11),
				 		     @emailpessoal VARCHAR(200),
				 		     @emailcorporativo VARCHAR(200),
				 		     @datasegundograu DATE,
				 		     @instituicaosegundograu VARCHAR(100),
				 		     @pontuacaovestibular INT,
				 		     @posicaovestibular INT,
				 		     @semestregraduacao CHAR(6),
				 		     @anolimite CHAR(6) OUTPUT,
							 @cursocodigo INT,
							 @turno VARCHAR(10),
				 		     @saida VARCHAR(300) OUTPUT)
AS
DECLARE @cpfvalido BIT = 0
DECLARE @idadevalida BIT = 0
DECLARE @codigomatricula INT = 0
DECLARE @anoingresso CHAR(4) = 0
DECLARE @semestreingresso CHAR(1) = 0

SET @turno = 'Tarde'

-- Gerar o ano/semestre de ingresso do aluno
EXEC sp_geraringresso @anoingresso OUTPUT, @semestreingresso OUTPUT

-- Gerar o RA do aluno
EXEC sp_gerarra @anoingresso, @semestreingresso, @ra OUTPUT 
PRINT @ra

-- Gerar o semestre e ano limite do aluno
EXEC sp_geraranolimite @anoingresso, @semestreingresso, @anolimite OUTPUT 
PRINT @anolimite

-- PROCEDIMENTO IUD

IF(UPPER(@acao) = 'I')
BEGIN
	IF EXISTS(SELECT cpf FROM aluno WHERE cpf = @cpf)
	BEGIN
		RAISERROR('O CPF inserido já existe dentro do sistema', 16, 1)
		RETURN
	END
	ELSE
	BEGIN
		INSERT INTO aluno (cpf, ra, nome, nome_social, data_nasc, telefone_celular, telefone_residencial, email_pessoal, email_corporativo,
		data_segundograu, instituicao_segundograu, pontuacao_vestibular, posicao_vestibular,
		ano_ingresso, semestre_ingresso, semestre_graduacao, ano_limite, curso_codigo, data_primeiramatricula, turno) VALUES
		(@cpf,
		 @ra,
		 @nome,
		 @nomesocial,
		 @datanasc,
		 @telefonecelular,
		 @telefoneresidencial,
		 @emailpessoal,
		 @emailcorporativo,
		 @datasegundograu,
		 @instituicaosegundograu,
		 @pontuacaovestibular,
		 @posicaovestibular,
		 @anoingresso,
		 @semestreingresso,
		 @semestregraduacao,
		 @anolimite,
		 @cursocodigo,
		 GETDATE(),
		 @turno)
		 EXEC sp_gerarmatricula @ra, @codigomatricula
		 SET @saida = 'Aluno inserido'
	END
END
ELSE 
IF(UPPER(@acao) = 'U')
BEGIN 
	UPDATE aluno
	SET nome = @nome,
		nome_social = @nomesocial,
		data_nasc = @datanasc,
		telefone_celular = @telefonecelular,
		telefone_residencial = @telefoneresidencial,
		email_pessoal = @emailpessoal,
		email_corporativo = @emailcorporativo,
		data_segundograu = @datasegundograu,
		instituicao_segundograu = @instituicaosegundograu,
		pontuacao_vestibular = @pontuacaovestibular,
		posicao_vestibular = @posicaovestibular,
		ano_ingresso = @anoingresso,
		semestre_ingresso = @semestreingresso,
		semestre_graduacao = @semestregraduacao,
		ano_limite = @anolimite,
		curso_codigo = @cursocodigo,
		turno = @turno
	WHERE cpf = @cpf
	SET @saida = 'Aluno atualizado'
END
ELSE
BEGIN 
	RAISERROR('Erro desconhecido', 16, 1)
END

-- Fim da procedure

-- Procedure IUD Matrícula 
------------------------------------------------------------------------

-- Início da procedure
CREATE PROCEDURE sp_inserirmatricula(@ra CHAR(9), @codigomatricula INT, @codigodisciplina INT, @saida VARCHAR(200) OUTPUT)
AS
DECLARE @conflito BIT,
		@qtdaula INT,
		@horarioinicio TIME,
		@horariofim TIME,
		@diasemana VARCHAR(50),
		@codigoconteudo INT

SELECT @qtdaula = d.qtd_aulas, @horarioinicio = d.horario_inicio, @horariofim = d.horario_fim, @diasemana = d.dia, @codigoconteudo = c.codigo
FROM disciplina d, matricula_disciplina md, matricula m, conteudo c
WHERE d.codigo = @codigodisciplina
	ANd md.codigo_disciplina = d.codigo
	AND md.codigo_matricula = m.codigo
	AND m.codigo = @codigomatricula
	AND m.aluno_ra = @ra
	AND c.codigo_disciplina = @codigodisciplina

EXEC sp_verificarconflitohorario @codigomatricula, @qtdaula, @diasemana, @horarioinicio, @horariofim, @conflito OUTPUT

PRINT @conflito
IF(@conflito = 0)
BEGIN
	UPDATE matricula_disciplina 
	SET situacao = 'Em curso'
	WHERE codigo_matricula = @codigomatricula 
		AND codigo_disciplina = @codigodisciplina
	SET @saida = 'Matricula finalizada.'
END
ELSE
BEGIN
	DELETE matricula_disciplina
	WHERE codigo_matricula = @codigomatricula

	DELETE matricula
	WHERE codigo = @codigomatricula

	RAISERROR('Matricula cancelada: Existe conflito de horários', 16, 1)
	RETURN
END
-- Fim da procedure

-- Procedimento Gerar matricula de um aluno
-----------------------------------------------------------------------------------

-- Início da procedure
CREATE PROCEDURE sp_gerarmatricula(@ra CHAR(9), @codigomatricula INT OUTPUT)
AS
BEGIN
	DECLARE @cont INT
	DECLARE @novocodigo INT = 0
	DECLARE @ano CHAR(4)
	DECLARE @sem CHAR(1)

	EXEC sp_geraringresso @ano OUTPUT, @sem OUTPUT

	SELECT @cont = COUNT(*)
	FROM matricula
	WHERE aluno_ra = @ra

	IF(@cont >= 1) -- Caso o aluno já seja matriculado
	BEGIN
		SELECT TOP 1 @codigomatricula = codigo 
		FROM matricula WHERE aluno_ra = @ra 
		ORDER BY codigo DESC
		-- Insiro o aluno em uma nova matricula

		SELECT TOP 1 @novocodigo = codigo + 1
		FROM matricula
		ORDER BY codigo DESC

		INSERT INTO matricula VALUES
		(@novocodigo, @ra, GETDATE()) -- o ultimo valor é só um placeholder


		-- Como a lógica para atualização da matricula será realizada por outra procedure,
		-- eu apenas reinsiro a ultima matricula feita pelo aluno
		INSERT INTO matricula_disciplina
		SELECT @novocodigo, codigo_disciplina, situacao, qtd_faltas, nota_final FROM dbo.fn_ultimamatricula(@codigomatricula)

		-- Retorno o novo codigo
		SET @codigomatricula = @novocodigo
	END
	ELSE -- A primeira matricula do aluno
	BEGIN
		IF NOT EXISTS(SELECT * FROM matricula) --Se nenhuma outra matrícula existir (garante que tenha um codigo para ser inserido)
		BEGIN
			SET @codigomatricula = 1000001
		END
		ELSE
		BEGIN
			SELECT TOP 1 @codigomatricula = codigo + 1
			FROM matricula
			ORDER BY codigo DESC
		END

		INSERT INTO matricula VALUES
		(@codigomatricula, @ra, GETDATE())

		INSERT INTO matricula_disciplina (codigo_matricula, codigo_disciplina, situacao, qtd_faltas, nota_final)
		SELECT * FROM dbo.fn_matriculainicial(@codigomatricula)
	END
END
-- Fim da procedure

-- Procedure de verificação de conflito de horarios em uma matricula
------------------------------------------------------------------------

-- Início da procedure
CREATE PROCEDURE sp_verificarconflitohorario(@codigomatricula INT, @qtdaulas INT, @diasemana VARCHAR(50), @horarioinicio TIME, @horariofim TIME, @conflito BIT OUTPUT)
AS
DECLARE @conflitoexiste INT

SELECT @conflitoexiste = COUNT(*)
FROM matricula_disciplina md, disciplina d
WHERE md.codigo_matricula = @codigomatricula
	AND md.codigo_disciplina = d.codigo
	AND d.dia = @diasemana
	AND	(md.situacao = 'Em curso')
	AND ((@horarioinicio BETWEEN d.horario_inicio AND d.horario_fim) 
	OR (@horariofim BETWEEN d.horario_inicio AND d.horario_fim) 
	OR (d.horario_inicio BETWEEN @horarioinicio AND @horariofim) 
	OR (d.horario_fim BETWEEN @horarioinicio AND @horariofim))

																	
IF (@conflitoexiste >= 1)
BEGIN
	SET @conflito = 1
END
ELSE
BEGIN
	SET @conflito = 0
END
-- Fim da procedure

CREATE PROCEDURE sp_iudprofessor(@acao CHAR(1), @codigo INT, @nome VARCHAR(100), @titulacao VARCHAR(100), @saida VARCHAR(200))
AS
BEGIN
	IF(UPPER(@acao) = 'I')
	BEGIN
		INSERT INTO professor (codigo, nome, titulacao) VALUES
		(@codigo, @nome, @titulacao)
		SET @saida = 'Professor inserido'
	END
	ELSE
	IF(UPPER(@acao) = 'U')
	BEGIN
		UPDATE professor
		SET nome = @nome,
			titulacao = @titulacao
		WHERE codigo = @codigo
		SET @saida = 'Professor atualizado'
	END
	ELSE
	IF(UPPER(@acao) = 'D')
	BEGIN
		DELETE professor
		WHERE codigo = @codigo
	END
	ELSE
	BEGIN
		RAISERROR('Operação inválida', 16, 1)
		RETURN
	END
END

CREATE PROCEDURE sp_alunodispensa(@alunora CHAR(9), @codigodisciplina INT, @motivo VARCHAR(200), @saida VARCHAR(200) OUTPUT)
AS
BEGIN
	IF EXISTS(SELECT * FROM dispensa WHERE aluno_ra = @alunora AND codigo_disciplina = @codigodisciplina AND estado = 'Em andamento')
	BEGIN
		RAISERROR('Disciplina já possui dispensa pendente', 16, 1)
		RETURN
	END
	ELSE
	IF EXISTS(SELECT * FROM dispensa WHERE aluno_ra = @alunora AND codigo_disciplina = @codigodisciplina AND estado = 'Pedido de dispensa recusado')
	BEGIN
		RAISERROR('Pedido de dispensa já foi recusado', 16, 1)
		RETURN
	END
	ELSE
	BEGIN
		INSERT INTO dispensa VALUES
		(@alunora, @codigodisciplina, @motivo, 'Em andamento')
		SET @saida = 'Dispensa solicitada'
	END
END

CREATE PROCEDURE sp_concluirdispensa
    @alunora CHAR(9),
    @codigodisciplina INT,
    @aprovacao VARCHAR(100),
    @saida VARCHAR(200) OUTPUT
AS
BEGIN
        DECLARE @codigomatricula INT
        SELECT TOP 1 @codigomatricula = codigo FROM matricula WHERE aluno_ra = @alunora ORDER BY codigo DESC

	IF(@aprovacao = 'Aprovar')
	BEGIN
		UPDATE matricula_disciplina
		SET situacao = 'Dispensado', nota_final = 'D'
		WHERE codigo_matricula = @codigomatricula
			AND codigo_disciplina = @codigodisciplina

		UPDATE dispensa
		SET estado = 'Disciplina dispensada'
		WHERE aluno_ra = @alunora
		AND codigo_disciplina = @codigodisciplina

        SET @saida = 'Pedido de dispensa aprovado com sucesso'
        END
        ELSE IF @aprovacao = 'Recusar' OR @aprovacao = 'Reprovar'
        BEGIN
          
		UPDATE dispensa
		SET estado = 'Pedido de dispensa recusado'
		WHERE aluno_ra = @alunora
		AND codigo_disciplina = @codigodisciplina

            SET @saida = 'Pedido de dispensa recusado com sucesso'
        END
        ELSE
        BEGIN
            SET @saida = 'Aprovação desconhecida'
            RETURN
        END
END

CREATE PROCEDURE sp_inseriraula(@codigomatricula INT, @codigodisciplina INT, @presenca INT, @dataAula DATE, @saida VARCHAR(200) OUTPUT)
AS
BEGIN
	IF EXISTS(SELECT TOP 1 * FROM aula WHERE matricula_codigo = @codigomatricula AND disciplina_codigo = @codigodisciplina AND data_aula = @dataAula)
	BEGIN
		RAISERROR('Chamada para o dia selecionado já foi realizada', 16, 1)
		RETURN
	END
	ELSE
	BEGIN
		INSERT INTO aula (matricula_codigo, disciplina_codigo, data_aula, presenca)VALUES
		(@codigomatricula, @codigodisciplina, @dataAula, @presenca)
		 
		--definir o total de faltas pro aluno
		UPDATE matricula_disciplina
		SET qtd_faltas = qtd_faltas + (4 - @presenca)
		WHERE codigo_matricula = @codigomatricula
			AND codigo_disciplina = @codigodisciplina

		SET @saida = 'Chamada finalizada'
	END
END

CREATE PROCEDURE sp_verificaraula(@codigodisciplina INT, @dataaula DATE, @saida BIT OUTPUT)
AS
BEGIN
	IF EXISTS(SELECT * FROM aula WHERE disciplina_codigo = @codigodisciplina AND @dataaula = data_aula)
	BEGIN
		SET @saida = 1
	END
	ELSE
	BEGIN
		SET @saida = 0
	END
END

-- IND02 - User Defined Functions
---------------------------------------------------------------------------

-- Função Matrícula Inicial: Retorna uma tabela com todas as disciplinas determinadas como não cursadas
--------------------------------------------------------------------------
CREATE FUNCTION fn_matriculainicial(@codigomatricula INT)
RETURNS @tabela TABLE(
codigo_matricula INT,
codigo_disciplina INT,
situacao VARCHAR(50),
qtd_faltas INT,
nota_final FLOAT
)
AS
BEGIN
	INSERT INTO @tabela (codigo_matricula, codigo_disciplina, situacao, qtd_faltas, nota_final)
	SELECT @codigomatricula, d.codigo, 'Não cursado' AS situacao, 0 AS qtd_faltas, 0.0 AS nota_final
	FROM matricula m, curso c, disciplina d, aluno a
	WHERE d.curso_codigo = c.codigo
		AND a.curso_codigo = c.codigo
		AND m.codigo = @codigomatricula
		AND m.aluno_ra = a.ra
	RETURN
END

-- Função Ultima Matrícula: Retorna uma tabela com a ultima matricula feita por um aluno
------------------------------------------------------------------------
CREATE FUNCTION fn_ultimamatricula(@codigomatricula INT)
RETURNS @tabela TABLE(
codigo_matricula INT,
codigo_disciplina INT,
situacao VARCHAR(50),
qtd_faltas INT,
nota_final FLOAT
)
AS
BEGIN
	INSERT INTO @tabela (codigo_matricula, codigo_disciplina, situacao, qtd_faltas, nota_final)
	SELECT @codigomatricula AS codigo_matricula, md.codigo_disciplina, md.situacao, md.qtd_faltas, md.nota_final
	FROM matricula_disciplina md, matricula m, aluno a, curso c
	WHERE md.codigo_matricula = @codigomatricula	
		AND m.codigo = @codigomatricula
		AND a.curso_codigo = c.codigo
		AND m.aluno_ra = a.ra
	RETURN
END

-- Função Listar Ultima Matrícula
------------------------------------------------------------------------
CREATE FUNCTION fn_listarultimamatricula(@ra char(9))
RETURNS @tabela TABLE(
codigo_matricula INT,
codigo INT,
nome VARCHAR(100),
codigo_professor INT,
nome_professor VARCHAR(100),
qtd_aulas INT,
horario_inicio TIME(0),
horario_fim TIME(0),
dia VARCHAR(20),
curso_codigo INT,
data_matricula CHAR(6),
situacao VARCHAR(50),
qtd_faltas INT,
nota_final CHAR(2)
)
AS
BEGIN
	DECLARE @codigomatricula INT
	DECLARE @ano CHAR(4)
	DECLARE @sem CHAR(1)
	DECLARE @data CHAR(6)

	SELECT @ano = YEAR(data_matricula) FROM matricula WHERE aluno_ra = @ra
	SELECT @sem = MONTH(data_matricula) FROM matricula WHERE aluno_ra = @ra
	IF(@sem <= 6)
	BEGIN
		SET @sem = 1
	END
	ELSE
	BEGIN
		SET @sem = 2
	END
	SET @data = FORMATMESSAGE('%s/%s', @ano, @sem)

	SELECT TOP 1 @codigomatricula = codigo 
	FROM matricula WHERE aluno_ra = @ra
	ORDER BY codigo DESC

	INSERT INTO @tabela (codigo_matricula, codigo, nome, codigo_professor, nome_professor, qtd_aulas, horario_inicio, horario_fim, dia, curso_codigo, data_matricula, situacao, qtd_faltas, nota_final)	
	SELECT CAST(md.codigo_matricula AS VARCHAR), CAST(d.codigo AS VARCHAR),
		   d.nome, CAST(p.codigo AS VARCHAR), p.nome, CAST(d.qtd_aulas AS VARCHAR),
		   d.horario_inicio, d.horario_fim, d.dia AS dia, 
		   CAST(d.curso_codigo AS VARCHAR), @data, md.situacao,
		   CAST(md.qtd_faltas AS VARCHAR), CAST(md.nota_final AS VARCHAR)
	FROM matricula_disciplina md, disciplina d, aluno a, matricula m, curso c, professor p
	WHERE m.codigo = @codigomatricula
		AND m.aluno_ra = a.ra
		AND md.codigo_matricula = @codigomatricula
		AND md.codigo_disciplina = d.codigo
		AND a.curso_codigo = c.codigo
		AND d.curso_codigo = c.codigo
		AND p.codigo = d.professor_codigo
	ORDER BY situacao ASC
	RETURN
END

 
CREATE FUNCTION fn_historico(@ra CHAR(9))
RETURNS @tabela TABLE(
ra CHAR(9),
nome_aluno VARCHAR(100),
nome_curso VARCHAR(100),
data_primeiramatricula DATE,
pontuacao_vestibular INT,
posicao_vestibular INT,
codigo_disciplina INT,
nome_discilplina VARCHAR(100),
nome_professor VARCHAR(100),
nota_final CHAR(2),
qtd_faltas INT
)
AS
BEGIN
	DECLARE @ultimamatricula TABLE(
		codigo_matricula INT,
		codigo_disciplina INT,	
		situacao VARCHAR(50),
		qtd_faltas INT,
		nota_final FLOAT
	)
	
	INSERT INTO @ultimamatricula (codigo_matricula, codigo_disciplina, situacao, qtd_faltas, nota_final) 
	(SELECT * FROM dbo.fn_ultimamatricula(@ra))

	INSERT INTO @tabela (ra, nome_aluno, nome_curso, nome_discilplina, nome_professor, pontuacao_vestibular, posicao_vestibular, data_primeiramatricula, codigo_disciplina, nota_final, qtd_faltas)
	SELECT @ra, a.nome, c.nome, d.nome, p.nome, a.pontuacao_vestibular, a.posicao_vestibular, a.data_primeiramatricula, u.codigo_disciplina, u.nota_final, u.qtd_faltas
	FROM @ultimamatricula u, aluno a, professor p, disciplina d, matricula_disciplina md, curso c
	WHERE a.ra = @ra
		AND a.curso_codigo = c.codigo
		AND u.codigo_disciplina = d.codigo
		AND d.professor_codigo = p.codigo
	RETURN
END 

-- View Alunos
--------------------------------------------------------------------------

CREATE VIEW v_alunos
AS
SELECT a.cpf AS cpf, a.ra AS ra, a.nome AS nome, a.nome_social AS nome_social, a.data_nasc AS data_nasc, a.telefone_celular AS telefone_celular, a.telefone_residencial AS telefone_residencial, a.email_pessoal AS email_pessoal, a.email_corporativo AS email_corporativo, a.data_segundograu AS data_segundograu, 
	   a.instituicao_segundograu AS instituicao_segundograu, a.pontuacao_vestibular AS pontuacao_vestibular, a.posicao_vestibular AS posicao_vestibular, a.ano_ingresso AS ano_ingresso, a.semestre_ingresso AS semestre_ingresso,
	   a.semestre_graduacao AS semestre_graduacao, a.ano_limite AS ano_limite, c.sigla AS curso_sigla, c.nome AS curso_nome, a.curso_codigo AS curso_codigo, a.data_primeiramatricula AS data_primeiramatricula, a.turno AS turno
FROM aluno a, curso c
WHERE a.curso_codigo = c.codigo

SELECT * FROM v_alunos

-- View Disciplinas
------------------------------------------------------------------------------

CREATE VIEW v_disciplinas
AS
SELECT d.codigo AS codigo, d.nome AS nome, d.qtd_aulas AS qtd_aulas, SUBSTRING(CAST(d.horario_inicio AS VARCHAR), 1, 5) AS horario_inicio, d.dia AS dia, d.curso_codigo AS curso_codigo
FROM disciplina d, curso c
WHERE d.curso_codigo = c.codigo

SELECT * FROM v_disciplinas

-- View Conteudo
-------------------------------------------------------------------------------------

CREATE VIEW v_conteudos
AS
SELECT c.codigo AS codigo, c.descricao AS descricao, c.codigo_disciplina AS codigo_disciplina
FROM conteudo c, disciplina d
WHERE c.codigo_disciplina = d.codigo

SELECT * FROM v_conteudos WHERE codigo_disciplina = 1003

-- View Cursos
--------------------------------------------------------------------------------------

CREATE VIEW v_cursos
AS
SELECT c.codigo AS codigo, c.nome AS nome, c.carga_horaria AS carga_horaria, c.sigla AS sigla, c.nota_enade AS nota_enade
FROM curso c

SELECT * FROM v_cursos

-- View Professor
----------------------------------------------------------------------------------------

CREATE VIEW v_professor
AS
SELECT p.codigo AS codigo, p.nome AS nome, p.titulacao AS titulacao
FROM professor p

-- View Conteudo
----------------------------------------------------------------------------------------

CREATE VIEW v_conteudo
AS
SELECT c.codigo AS codigo, c.codigo_disciplina AS codigo_disciplina, c.descricao AS descricao
FROM conteudo c, disciplina d
WHERE c.codigo_disciplina = d.codigo

-- View Chamada
----------------------------------------------------------------------------------------

CREATE VIEW v_aluno_chamada
AS
SELECT DISTINCT a.nome AS nome, a.ra AS ra, d.codigo AS codigo_disciplina, m.aluno_ra AS aluno_ra
FROM aluno a, matricula m, matricula_disciplina md, disciplina d
WHERE m.aluno_ra = a.ra
	AND md.codigo_matricula = m.codigo
	AND md.codigo_disciplina = d.codigo
	AND md.situacao = 'Em curso'

-- View Disciplinas por aluno
----------------------------------------------------------------------------------------

CREATE VIEW v_disciplinas_aluno
AS
SELECT d.codigo AS codigo, d.nome AS nome, d.qtd_aulas AS qtd_aulas, SUBSTRING(CAST(d.horario_inicio AS VARCHAR), 1, 5) AS horario_inicio, SUBSTRING(CAST(d.horario_fim AS VARCHAR), 1, 5) AS horario_fim, d.dia AS dia, d.curso_codigo AS curso_codigo, a.ra AS ra
FROM disciplina d, curso c, aluno a, matricula m, matricula_disciplina md
WHERE d.curso_codigo = c.codigo
	AND a.curso_codigo = c.codigo
	AND a.ra = m.aluno_ra
	AND m.codigo = md.codigo_matricula
	AND d.codigo = md.codigo_disciplina
	AND md.situacao NOT LIKE 'Dispensado'
	AND md.situacao NOT LIKE 'Em curso'
	AND md.situacao = 'Não Cursado'

-- View Dispensa
----------------------------------------------------------------------------------------

CREATE VIEW v_dispensas
AS
SELECT d.aluno_ra AS aluno_ra, d.codigo_disciplina AS codigo_disciplina, d.motivo AS motivo, a.nome AS aluno_nome, c.nome AS curso_nome, di.nome AS disciplina_nome, d.estado AS estado
FROM dispensa d, aluno a, disciplina di, curso c
WHERE a.ra = d.aluno_ra
	AND di.codigo = d.codigo_disciplina
	AND c.codigo = a.curso_codigo

-- View Aula
----------------------------------------------------------------------------------------

CREATE VIEW v_aula
AS
SELECT a.disciplina_codigo AS disciplina_codigo, a.matricula_codigo AS matricula_codigo, a.data_aula AS data_aula
FROM aula a, disciplina d, matricula_disciplina md, matricula m 
WHERE a.disciplina_codigo = d.codigo
	AND a.matricula_codigo = md.codigo_matricula
	AND md.codigo_disciplina = d.codigo		

CREATE TRIGGER t_reprovarfalta ON matricula_disciplina
AFTER UPDATE
AS
BEGIN
	DECLARE @codigomatricula INT
	DECLARE @codigodisciplina INT
	DECLARE @qtdaulas INT
	DECLARE @qtdfaltas INT
	
	SELECT @codigomatricula = codigo_matricula, @codigodisciplina = codigo_disciplina, @qtdfaltas = qtd_faltas FROM INSERTED
	SELECT @qtdaulas = qtd_aulas FROM disciplina WHERE codigo = @codigodisciplina

	IF(@qtdfaltas > @qtdaulas * 5)
	BEGIN
		UPDATE matricula_disciplina
		SET situacao = 'Reprovado por Falta'
		WHERE codigo_matricula = @codigomatricula
			AND codigo_disciplina = @codigodisciplina
	END
END

-- IND04 - Inserções para teste
--------------------------------------------------------------------------------------

-- Valores de teste para tabela Curso
INSERT INTO curso VALUES
(101, 'Análise e Desenvolvimento de Sistemas', 2800, 'ADS', 5),
(102, 'Desenvolvimento de Software Multiplataforma', 1400, 'DSM', 5)

-- Valores de teste para tabela Professor
INSERT INTO professor VALUES
(1001, 'Marcelo Silva', 'Mestre'),
(1002, 'Rafael Medeiros', 'Mestre'),
(1003, 'Adriana Bastos', 'Doutora'),
(1004, 'Henrique Galvão', 'Mestre'),
(1005, 'Ulisses Santos Barbosa', 'Doutor'),
(1006, 'Pedro Guimarães', 'Mestre'),
(1007, 'Reinaldo Santos', 'Doutor'),
(1008, 'Pedro Lima', 'Mestre'),
(1009, 'Marcelo Soares', 'Doutor'),
(1010, 'Costa Lima de Souza', 'Mestre'),
(1011, 'Gabriela Gonçalves', 'Doutora'),
(1012, 'Yasmin Ribeiro', 'Mestre')


-- Valores de teste para tabela Disciplina
-- Curso 101
INSERT INTO disciplina VALUES
(1001, 'Laboratório de Banco de Dados', 4, '14:50', '18:20', 'Segunda', 101, 1001),
(1002, 'Banco de Dados', 4, '14:50', '18:20', 'Terça', 101, 1001),
(1003, 'Algorítmos e Lógica de Programação', 4, '14:50', '18:20', 'Segunda', 101, 1001),
(1004, 'Matemática Discreta', 4, '13:00', '16:30','Quinta', 101, 1001),
(1005, 'Linguagem de Programação', 4, '14:50', '18:20', 'Terça', 101, 1001),
(1006, 'Estruturas de Dados', 2, '13:00', '14:40', 'Terça', 101, 1001),
(1007, 'Programação Mobile', 4, '13:00', '16:30', 'Sexta', 101, 1001),
(1008, 'Empreendedorismo', 2, '13:00', '14:40', 'Quarta', 101, 1002),
(1009, 'Ética e Responsabilidade', 2, '16:50', '18:20', 'Segunda', 101, 1002),
(1010, 'Administração Geral', 4, '14:50', '18:20', 'Terça', 101, 1002),
(1011, 'Sistemas de Informação', 4, '13:00', '16:30', 'Terça', 101, 1002),
(1012, 'Gestão e Governança de TI', 4, '14:50', '18:20', 'Sexta', 101, 1002),
(1013, 'Redes de Computadores', 4, '14:50', '18:20', 'Quinta', 101, 1004),
(1014, 'Contabilidade', 2, '13:00', '14:40', 'Quarta', 101, 1001),
(1015, 'Economia e Finanças', 4, '13:00', '16:30', 'Quarta', 101, 1004),
(1016, 'Arquitetura e Organização de Computadores', 4, '13:00', '16:30', 'Segunda', 101, 1001),
(1017, 'Laboratório de Hardware', 4, '13:00', '16:30', 'Segunda', 101, 1001),
(1018, 'Sistemas Operacionais', 4, '14:50', '18:20', 'Quinta', 101, 1001),
(1019, 'Sistemas Operacionais 2', 4, '14:50', '18:20', 'Sexta', 101, 1001),
(1020, 'Programação Web', 4, '13:00', '16:30', 'Terça', 101, 1001),
(1021, 'Programação em Microinformática', 2, '13:00', '14:40', 'Sexta', 101, 1004),
(1022, 'Programação Linear', 2, '13:00', '14:40', 'Segunda', 101, 1004),
(1023, 'Cálculo', 4, '13:00', '16:30', 'Segunda', 101, 1003),
(1024, 'Teste de Software', 2, '13:00', '14:40', 'Quinta', 101, 1002),
(1025, 'Engenharia de Software 1', 4, '13:00', '16:30', 'Segunda', 101, 1001),
(1026, 'Engenharia de Software 2', 4, '13:00', '16:30', 'Terça', 101, 1002),
(1027, 'Engenharia de Software 3', 4, '14:50', '18:20', 'Segunda', 101, 1005),
(1028, 'Laboratório de Engenharia de Software', 4, '14:50', '18:20', 'Quarta', 101, 1004),
(1029, 'Inglês 1', 4, '14:50', '18:20', 'Sexta', 101, 1005),
(1030, 'Inglês 2', 2, '14:50', '16:30', 'Terça', 101, 1005),
(1031, 'Inglês 3', 2, '13:00', '14:40', 'Sexta', 101, 1005),
(1032, 'Inglês 4', 2, '13:00', '14:40', 'Segunda', 101, 1005),
(1033, 'Inglês 5', 2, '13:00', '14:40', 'Terça', 101, 1005),
(1034, 'Inglês 6', 2, '13:00', '14:40', 'Quinta', 101, 1005),
(1035, 'Sociedade e Tecnologia', 2, '14:50', '16:30', 'Terça', 101, 1002),
(1036, 'Interação Humano Computador', 4, '14:50', '18:20', 'Terça', 101, 1002),
(1037, 'Estatística Aplicada', 4, '14:50', '18:20', 'Quarta', 101, 1004),
(1038, 'Laboratório de Redes de Computadores', 4, '14:50', '18:20', 'Sexta', 101, 1004),
(1039, 'Inteligência Artificial', 4, '13:00', '16:30', 'Quarta', 101, 1004),
(1040, 'Programação para Mainframes', 4, '14:50', '18:20', 'Quarta', 101, 1004)

INSERT INTO disciplina VALUES
(1041, 'Desenvolvimento de Aplicações Distribuídas', 4, '13:00', '16:30', 'Segunda', 102, 1006),
(1042, 'Segurança de Aplicações Web', 4, '13:00', '16:30', 'Segunda', 102, 1006),
(1043, 'Banco de Dados NoSQL', 4, '13:00', '16:30', 'Terça', 102, 1006),
(1044, 'Gerenciamento de Projetos de Software Ágil', 4, '13:00', '16:30', 'Terça', 102, 1007),
(1045, 'Desenvolvimento de Aplicações Móveis', 4, '13:00', '16:30', 'Quarta', 102, 1012),
(1046, 'Desenvolvimento de APIs', 2, '13:00', '14:40', 'Quarta', 102, 1011),
(1047, 'Modelagem de Dados', 4, '13:00', '16:30', 'Quinta', 102, 1011),
(1048, 'Arquitetura de Software Distribuído', 4, '13:00', '16:30', 'Quinta', 102, 1011),
(1049, 'Engenharia de Requisitos Avançada', 4, '13:00', '16:30', 'Sexta', 102, 1010),
(1050, 'Metodologias Ágeis', 2, '13:00', '14:40', 'Sexta', 102, 1010),
(1051, 'Desenvolvimento de Interfaces Gráficas', 4, '14:50', '18:20', 'Segunda', 102, 1010),
(1052, 'Auditoria de Sistemas', 4, '14:50', '18:20', 'Segunda', 102, 1010),
(1053, 'Administração de Bancos de Dados', 4, '14:50', '18:20', 'Terça', 102, 1009),
(1054, 'Gestão de Projetos de TI', 4, '14:50', '18:20', 'Terça', 102, 1009),
(1055, 'Desenvolvimento de Jogos Digitais', 4, '14:50', '18:20', 'Quarta', 102, 1009),
(1056, 'Segurança de Redes', 2, '14:50', '16:30', 'Quarta', 102, 1008),
(1057, 'Mineração de Dados', 4, '14:50', '18:20', 'Quinta', 102, 1008),
(1058, 'Arquitetura de Software Orientada a Serviços', 4, '14:50', '18:20', 'Quinta', 102, 1006),
(1059, 'Análise de Negócios em TI', 4, '14:50', '18:20', 'Sexta', 102, 1007),
(1060, 'DevOps', 2, '14:50', '16:30', 'Sexta', 102, 1007),
(1061, 'Desenvolvimento de Sistemas Embarcados', 2, '16:40', '18:20', 'Segunda', 102, 1007),
(1062, 'Criptografia e Segurança de Dados', 2, '16:40', '18:20', 'Segunda', 102, 1007),
(1063, 'Big Data Analytics', 2, '16:40', '18:20', 'Terça', 102, 1007),
(1064, 'Gerenciamento Ágil de Projetos', 2, '16:40', '18:20', 'Terça', 102, 1008),
(1065, 'Desenvolvimento de Aplicações Desktop', 2, '16:40', '18:20', 'Quarta', 102, 1008),
(1066, 'Segurança em IoT', 2, '16:40', '18:20', 'Quarta', 102, 1008),
(1067, 'Banco de Dados Geoespaciais', 2, '16:40', '18:20', 'Quinta', 102, 1008),
(1068, 'Arquitetura de Microserviços', 2, '16:40', '18:20', 'Quinta', 102, 1009),
(1069, 'Engenharia de Requisitos Elicitação e Análise', 2, '16:40', '18:20', 'Sexta', 102, 1009),
(1070, 'Scrum e Métodos Ágeis', 2, '16:40', '18:20', 'Sexta', 102, 1009),
(1071, 'Desenvolvimento de Aplicações Híbridas', 4, '13:00', '16:30', 'Segunda', 102, 1007),
(1072, 'Análise de Riscos em Segurança da Informação', 4, '13:00', '16:30', 'Segunda', 102, 1007),
(1073, 'Banco de Dados Distribuídos', 4, '13:00', '16:30', 'Terça', 102, 1008),
(1074, 'Gestão de Projetos de Desenvolvimento de Software', 4, '13:00', '16:30', 'Terça', 102, 1009),
(1075, 'Desenvolvimento de Aplicações para Dispositivos Móveis', 4, '13:00', '16:30', 'Quarta', 102, 1009),
(1076, 'Segurança da Informação em Cloud Computing', 2, '13:00', '14:40', 'Quarta', 102, 1010),
(1077, 'Data Science Aplicado', 4, '13:00', '16:30', 'Quinta', 102, 1011),
(1078, 'Arquitetura de Microsserviços Distribuídos', 4, '13:00', '16:30', 'Quinta', 102, 1012),
(1079, 'Engenharia de Requisitos para Sistemas Distribuídos', 4, '13:00', '16:30', 'Sexta', 102, 1012),
(1080, 'Kanban e Lean para Desenvolvimento de Software', 2, '13:00', '14:40', 'Sexta', 102, 1012)

INSERT INTO conteudo VALUES
(1001001, 'Aula Introdutoria', 1001),
(1001002, 'Projeto Spring', 1001)

-- Inserts para a tabela aluno
INSERT INTO aluno (cpf, ra, nome, nome_social, data_nasc, telefone_celular, telefone_residencial, email_pessoal, email_corporativo, data_segundograu, instituicao_segundograu, pontuacao_vestibular, posicao_vestibular, ano_ingresso, semestre_ingresso, semestre_graduacao, ano_limite, curso_codigo, data_primeiramatricula)
VALUES
('12345678901', '20211001', 'João Oliveira', NULL, '2001-01-10', '999999999', NULL, 'joao@email.com', NULL, '2019-12-20', 'Colégio Alpha', 820, 150, '2021', '1', '2021/1', '2025/2', 101, '2021-01-05'),
('23456789012', '20211002', 'Maria Santos', NULL, '2002-05-20', '888888888', NULL, 'maria@email.com', NULL, '2020-01-10', 'Colégio Beta', 850, 120, '2021', '1', '2021/1', '2025/2', 102, '2021-01-06');
