USE master
GO

CREATE  DATABASE exercicio2Trigger
GO

USE  exercicio2Trigger
GO

--Fazer uma TRIGGER AFTER na tabela Venda que, uma vez feito um INSERT, verifique se a quan�dade
--está disponível em estoque. Caso esteja, a venda se concretiza, caso contrário, a venda deverá ser
--cancelada e uma mensagem de erro deverá ser enviada. A mesma TRIGGER deverá validar, caso a
--venda se concretize, se o estoque está abaixo do estoque mínimo determinado ou se após a venda,
--ficará abaixo do estoque considerado mínimo e deverá lançar um print na tela avisando das duas situações.
--Fazer uma UDF (User Defined Function) Multi Statement Table, que apresente, para uma dada nota
--fiscal, a seguinte saída:
--(Nota_Fiscal | Codigo_Produto | Nome_Produto | Descricao_Produto | Valor_Unitario | Quantidade | Valor_Total*)
-- Considere que Valor_Total = Valor_Unitário * Quantidade


CREATE  TABLE produto (
	codigo INT NOT NULL,
	nome VARCHAR(100) NOT NULL,
	descricao VARCHAR(100) NOT NULL,
	valor_unitario DECIMAL(7,2) NOT NULL,
	PRIMARY KEY (codigo)
)
GO

CREATE TABLE venda (
	nota_fiscal INT NOT NULL,
	codigo_produto INT NOT NULL,
	quantidade  INT NOT NULL
	PRIMARY KEY (nota_fiscal)
	FOREIGN KEY (codigo_produto) REFERENCES produto(codigo)
)
GO

CREATE TABLE estoque (
	codigo_produto INT NOT NULL,
	qtd_estoque    INT NOT NULL,
	estoque_minimo INT NOT NULL,
	PRIMARY KEY (codigo_produto),
	FOREIGN KEY (codigo_produto) REFERENCES produto (codigo)
) 

CREATE TRIGGER tr_verificar_estoque
ON venda
AFTER INSERT
AS
BEGIN
    DECLARE @codigo_produto INT, @quantidade INT, @estoque_disponivel INT, @estoque_minimo INT;

    SELECT @codigo_produto = codigo_produto, @quantidade = quantidade
    FROM inserted;

    SELECT @estoque_disponivel = qtd_estoque, @estoque_minimo = estoque_minimo
    FROM estoque
    WHERE codigo_produto = @codigo_produto

    IF (@quantidade > @estoque_disponivel)
    BEGIN
        RAISERROR ('Erro: Quantidade insuficiente em estoque para o produto.', 16, 1)
        ROLLBACK TRANSACTION;
    END
    ELSE
    BEGIN
        IF (@estoque_disponivel <= @estoque_minimo)
        BEGIN
            PRINT 'Aviso: Estoque do produto (' + CAST(@codigo_produto AS VARCHAR(10)) + ') está abaixo do estoque mínimo.';
        END
    END
END

CREATE FUNCTION udf_detalhes_nota_fiscal (@nota_fiscal INT)
RETURNS TABLE
AS
RETURN (
    SELECT v.nota_fiscal, v.codigo_produto, p.nome AS nome_produto, p.descricao AS descricao_produto, p.valor_unitario,
           v.quantidade, p.valor_unitario * v.quantidade AS valor_total
    FROM venda v
    INNER JOIN produto p ON v.codigo_produto = p.codigo
    WHERE v.nota_fiscal = @nota_fiscal
)

INSERT INTO produto (codigo, nome, descricao, valor_unitario)
VALUES 
(1, 'Calça', 'Cintura Alta', 50.10),
(2, 'Camiseta', 'Gola V', 40.50)
 
INSERT INTO estoque (codigo_produto, qtd_estoque, estoque_minimo)
VALUES
(1, 100, 20), 
(2, 50, 10)

SELECT * FROM venda

INSERT INTO venda (nota_fiscal, codigo_produto, quantidade)
VALUES (1002, 1, 15)

INSERT INTO venda (nota_fiscal, codigo_produto, quantidade)
VALUES (1003, 2, 60)

SELECT * FROM udf_detalhes_nota_fiscal(1001)