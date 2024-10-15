CREATE  DATABASE ex_triggers_07
GO

USE ex_triggers_07
GO

CREATE TABLE cliente (
codigo INT NOT NULL,
nome VARCHAR(70) NOT NULL
PRIMARY KEY(codigo)
)
GO

CREATE TABLE produto (
codigo_produto INT NOT NULL,
nome_produto VARCHAR(100) NOT NULL,
preco DECIMAL(7,2) NOT NULL,
PRIMARY KEY (codigo_produto)
)
GO

CREATE TABLE pontos (
codigo_cliente INT NOT NULL,
total_pontos DECIMAL(4,1) NOT NULL
PRIMARY KEY (codigo_cliente)
FOREIGN KEY (codigo_cliente) REFERENCES cliente(codigo)
)
GO

CREATE TABLE venda (
codigo_venda INT NOT NULL,
codigo_cliente INT NOT NULL,
valor_total DECIMAL(7,2) NOT NULL,
codigo_produto INT NOT NULL
PRIMARY KEY (codigo_venda)
FOREIGN KEY (codigo_cliente) REFERENCES cliente(codigo),
FOREIGN KEY (codigo_produto) REFERENCES produto(codigo_produto)
)
GO

INSERT INTO cliente (codigo, nome)
VALUES (5, 'João da Silva')


--Exercícios:
--1) - Uma empresa vende produtos alimentícios
-- A empresa dá pontos, para seus clientes, que podem ser revertidos em prêmios

-- Para não prejudicar a tabela venda, nenhum produto pode ser deletado, mesmo que não venha mais a ser vendido
INSERT INTO produto VALUES
(2,'BLUSA',20.99)

SELECT * FROM produto

CREATE TRIGGER t_delprod ON produto
FOR DELETE
AS 
BEGIN
	ROLLBACK TRANSACTION
	RAISERROR('NÃO É POSSÍVEL EXCLUIR PRODUTO', 16, 1)
END

DELETE produto
WHERE codigo_produto = 2

-- Para não prejudicar os relatórios e a contabilidade, a tabela venda não pode ser alterada.
INSERT INTO venda (codigo_venda, codigo_cliente, valor_total, codigo_produto)
VALUES (4, 2, 50.50, 2)

CREATE TRIGGER t_updatevenda ON venda
AFTER UPDATE
AS
BEGIN
	RAISERROR('NÃO É PERMITIDO REALIZAR ALTERAÇÕES NA TABELA "VENDA".', 16,1)
	ROLLBACK TRANSACTION
END

UPDATE venda 
SET valor_total = 99.99
WHERE codigo_venda = 3

-- Ao invés de alterar a tabela venda deve-se exibir uma tabela com o nome do último cliente que comprou e o valor da última compra
CREATE TRIGGER t_updtdeldepto ON venda
INSTEAD OF UPDATE
AS
BEGIN
   SELECT TOP 1  c.nome AS nome_cliente, v.valor_total AS ultima_compra
   FROM cliente c
   INNER JOIN venda v ON c.codigo = v.codigo_cliente
   ORDER BY v.codigo_venda DESC  
END


-- Após a inserção de cada linha na tabela venda, 10% do total deverá ser transformado em pontos.
CREATE TRIGGER t_insert_venda ON venda
AFTER  INSERT
AS
BEGIN
	DECLARE @total_venda DECIMAL(7, 2)
	DECLARE @codigo_cliente  INT
	DECLARE @pontos DECIMAL(7,2)

	SELECT @total_venda = valor_total, @codigo_cliente = codigo_cliente
    FROM INSERTED
    SET @pontos = @total_venda * 0.1
    INSERT INTO pontos (codigo_cliente, total_pontos)
    VALUES (@codigo_cliente, @pontos)
END

INSERT INTO venda (codigo_venda, codigo_cliente, valor_total, codigo_produto)
VALUES (6, 2, 50.50, 2)

SELECT * FROM venda
SELECT * FROM pontos

-- Se o cliente ainda não estiver na tabela de pontos, deve ser inserido automaticamente após sua primeira compra
CREATE TRIGGER t_insert_venda_cliente ON venda
AFTER INSERT
AS
BEGIN
	DECLARE @codigo_cliente INT

	SELECT @codigo_cliente = @codigo_cliente
	FROM inserted

	IF NOT EXISTS (SELECT 1 FROM pontos WHERE codigo_cliente = @codigo_cliente)
    BEGIN
        INSERT INTO pontos (codigo_cliente, total_pontos)
        VALUES (@codigo_cliente, 0)
    END
END

INSERT INTO venda (codigo_venda, codigo_cliente, valor_total, codigo_produto)
VALUES (7, 5, 10.50, 2)

SELECT * FROM venda
SELECT * FROM pontos
SELECT * FROM cliente

-- Se o cliente atingir 1 ponto, deve receber uma mensagem (PRINT SQL Server) dizendo que ganhou e remove esse 1 ponto da tabela de pontos
CREATE TRIGGER t_insert_venda_pontos ON venda
AFTER INSERT
AS
BEGIN
    DECLARE @codigo_cliente INT;
    DECLARE @total_pontos DECIMAL(4, 1);
    
    SELECT @codigo_cliente = codigo_cliente,
           @total_pontos = total_pontos
    FROM pontos
    WHERE codigo_cliente = (SELECT codigo_cliente FROM inserted);
    
    UPDATE pontos
    SET total_pontos = @total_pontos + 0.1
    WHERE codigo_cliente = @codigo_cliente

	--Verificar se o cliente atingiu 1 ponto
	IF @total_pontos + 0.1 >= 1
	BEGIN
		PRINT 'PARABÉNS! VOCÊ GANHOU 1 PONTO!'

	 -- Remover 1 ponto da tabela de pontos
	 UPDATE pontos 
	 SET total_pontos = total_pontos - 1
	 WHERE codigo_cliente = @codigo_cliente
	END
END

INSERT INTO venda (codigo_venda, codigo_cliente, valor_total, codigo_produto)
VALUES (8, 3, 20.50, 2)

SELECT * FROM venda
SELECT * FROM pontos
SELECT * FROM cliente