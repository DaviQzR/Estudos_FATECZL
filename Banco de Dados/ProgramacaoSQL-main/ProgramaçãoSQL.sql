CREATE DATABASE programacaosql
GO
USE programacaosql
GO

--Exercícios:
--1) Fazer em SQL Server os seguintes algoritmos:

--a) Fazer um algoritmo que leia 1 número e mostre se são múltiplos de 2,3,5 ou nenhum deles
DECLARE  @numero		INT = 7
IF	@numero    % 2 = 0
	PRINT 'O número é multiplo de 2'
IF	@numero		% 3 = 0
	PRINT  'O número é multiplo de 3'
IF  @numero		% 5 = 0
	PRINT 'O número é multiplo de 5'
IF	@numero	 %2 != 0 AND	@numero %  3   != 0		AND		@numero   %  5 !=  0
	PRINT 'O número não é multiplo de 2, 3 ou 5'

--b) Fazer um algoritmo que leia 3 números e mostre o maior e o menor
DECLARE		@numero1	INT	= 80
DECLARE		@numero2	INT = 10
DECLARE		@numero3	INT = 7
DECLARE		@maior		INT
DECLARE		@menor		INT
SET		@maior	=	@numero1
IF		@numero2 >	@maior
		SET		@maior	  =	  @numero2
IF		@numero3	>	@maior
		SET		@maior	  =	  @numero3
SET		@menor	= @numero1
IF		@numero2	<	@menor
		SET		@menor	=		@numero2
IF		@numero3	<	@menor
		SET		@menor	=		@numero3

PRINT	'O maior número é: ' + CAST(@maior AS	VARCHAR(10))
PRINT	'O menor número é: ' + CAST(@menor AS	VARCHAR(10))

/*c) Fazer um algoritmo que calcule os 15 primeiros termos da série
1,1,2,3,5,8,13,21,...
E calcule a soma dos 15 termos*/
DECLARE		@termo1		INT		=	1
DECLARE		@termo2		INT		=	1
DECLARE		@SOMA		INT		=	@termo1	+	@termo2

PRINT 'Termos da Série'
PRINT	@termo1
PRINT	@termo2

DECLARE		@contador		INT		= 3

WHILE	@contador  <=	15
BEGIN
	DECLARE		@novoTermo		INT	  =		@termo1		+	@termo2
	SET		@soma  =  @soma  +  @novoTermo
	PRINT	@novoTermo
	SET		@termo1		=	@termo2
	SET		@termo2		=	@novoTermo
	SET		@contador	=	@contador	+  1
END

PRINT 'A soma dos 15 termos é: ' + CAST(@soma AS VARCHAR(10));
	

/*d) Fazer um algoritmo que separa uma frase, colocando todas as letras em maiúsculo e em
minúsculo (Usar funções UPPER e LOWER)*/
DECLARE		@frase		VARCHAR(100)	=	'Banco de Dados sem duvidas'

PRINT	'Colevati é o melhor professor de ' + @frase

DECLARE @fraseMaiuscula VARCHAR(100) = UPPER(@frase);
DECLARE @fraseMinuscula VARCHAR(100) = LOWER(@frase);

PRINT 'Frase em maiúsculas: ' + @fraseMaiuscula;
PRINT 'Frase em minúsculas: ' + @fraseMinuscula;

--e) Fazer um algoritmo que inverta uma palavra (Usar a função SUBSTRING)
DECLARE		@palavra	VARCHAR(100)	=	'SQL Server'
DECLARE		@tamanho	INT			=	LEN(@palavra)
DECLARE		 @palavraInvertida		VARCHAR(100) = '';
DECLARE		@posicao	INT		=	@tamanho

WHILE	@posicao	>	0
BEGIN
		  SET @palavraInvertida = @palavraInvertida + SUBSTRING(@palavra, @posicao, 1);
		  SET @posicao = @posicao - 1;
END

PRINT 'Palavra original: ' + @palavra;
PRINT 'Palavra invertida: ' + @palavraInvertida;

/*f) Considerando a tabela abaixo, gere uma massa de dados, com 100 registros, para fins de teste
com as regras estabelecidas (Não usar constraints na criação da tabela)
Computador

ID                  Marca                 QtdRAM             TipoHD                QtdHD               FreqCPU
INT (PK)           VARCHAR(40)             INT              VARCHAR(10)            INT               DECIMAL(7,2)

• ID incremental a iniciar de 10001
• Marca segue o padrão simples, Marca 1, Marca 2, Marca 3, etc.
• QtdRAM é um número aleatório* dentre os valores permitidos (2, 4, 8, 16)
• TipoHD segue o padrão:
o Se o ID dividido por 3 der resto 0, é HDD
o Se o ID dividido por 3 der resto 1, é SSD
o Se o ID dividido por 3 der resto 2, é M2 NVME
• QtdHD segue o padrão:
o Se o TipoHD for HDD, um valor aleatório* dentre os valores permitidos (500, 1000 ou 2000)
o Se o TipoHD for SSD, um valor aleatório* dentre os valores permitidos (128, 256, 512)
• FreqHD é um número aleatório* entre 1.70 e 3.20

* Função RAND() gera números aleatórios entre 0 e 0,9999...*/

CREATE TABLE	Computador (
	id		INT	,
	marca	VARCHAR(40),
	qtdRam	INT,
	tipoHd	VARCHAR(10),
	qtdHd	INT,
	freqCpu	DECIMAL	(7,2),
	PRIMARY KEY	(id)
)

DECLARE		@cont	INT	 = 1
DECLARE		@id		INT	 = 10001


WHILE @cont <= 100
BEGIN
    DECLARE @marca VARCHAR(40) = 'Marca' + CAST(@cont AS VARCHAR(10));
    DECLARE @qtdRam INT = CAST(RAND() * 4 + 1 AS INT) * 2;
    DECLARE @tipoHd VARCHAR(10);
    DECLARE @qtdHd INT;
    DECLARE @freqCpu DECIMAL(7,2);

    SET @tipoHd = CASE WHEN @id % 3 = 0 THEN 'HDD'
                       WHEN @id % 3 = 1 THEN 'SSD'
                       ELSE 'M2 NVME'
                  END;
                  
    SET @qtdHd = CASE WHEN  @tipoHd = 'HDD' THEN (CAST(RAND() * 3 AS INT) + 1) * 500
                      WHEN @tipoHd = 'SSD' THEN (CAST(RAND() * 3 AS INT) + 1) * 128
                      ELSE 0
                 END;

    SET @freqCpu = RAND() * (3.20 - 1.70) + 1.70;

    INSERT INTO Computador (id, marca, qtdRam, tipoHd, qtdHd, freqCpu)
    VALUES (@id, @marca, @qtdRam, @tipoHd, @qtdHd, @freqCpu);

    SET @id = @id + 1;
    SET @cont = @cont + 1;
END


SELECT * FROM Computador
