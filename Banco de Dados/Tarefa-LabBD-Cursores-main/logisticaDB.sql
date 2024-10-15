CREATE DATABASE logisticaDB
GO

USE logisticaDB
GO

CREATE TABLE envio (
    cpf VARCHAR(20),
    nr_linha_arquiv INT,
    cd_filial INT,
    dt_envio DATETIME,
    nr_ddd INT,
    nr_telefone VARCHAR(10),
    nr_ramal VARCHAR(10),
    dt_processament DATETIME,
    nm_endereco VARCHAR(200),
    nr_endereco INT,
    nm_complemento VARCHAR(50),
    nm_bairro VARCHAR(100),
    nr_cep VARCHAR(10),
    nm_cidade VARCHAR(100),
    nm_uf VARCHAR(2)
)

CREATE TABLE endereco (
    cpf VARCHAR(20),
    cep VARCHAR(10),
    porta INT,
    endereco VARCHAR(200),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    uf VARCHAR(2)
)

CREATE PROCEDURE sp_insereenvio
AS
BEGIN
    DECLARE @cpf AS INT
    DECLARE @cont1 AS INT
    DECLARE @cont2 AS INT
    DECLARE @conttotal AS INT
    SET @cpf = 11111
    SET @cont1 = 1
    SET @cont2 = 1
    SET @conttotal = 1
    WHILE @cont1 <= @cont2 AND @cont2 <= 100
    BEGIN
        INSERT INTO envio (cpf, nr_linha_arquiv, dt_envio)
        VALUES (CAST(@cpf AS VARCHAR(20)), @cont1, GETDATE())

        INSERT INTO endereco (cpf, porta, endereco)
        VALUES (@cpf, @conttotal, CAST(@cont2 AS VARCHAR(3)) + 'Rua ' + CAST(@conttotal AS VARCHAR(5)))

        SET @cont1 = @cont1 + 1
        SET @conttotal = @conttotal + 1

        IF @cont1 >= @cont2
        BEGIN
            SET @cont1 = 1
            SET @cont2 = @cont2 + 1
            SET @cpf = @cpf + 1
        END
    END
END

EXEC sp_insereenvio

SELECT * FROM envio ORDER BY cpf, nr_linha_arquiv ASC
SELECT * FROM endereco ORDER BY cpf ASC

CREATE PROCEDURE sp_move_endereco_para_envio
AS
BEGIN
    DECLARE @cpf VARCHAR(20)
    DECLARE @nr_linha_arquiv INT
    DECLARE @nm_endereco VARCHAR(200)
    DECLARE @nm_complemento VARCHAR(50)
    DECLARE @nm_bairro VARCHAR(100)
    DECLARE @nr_cep VARCHAR(10)
    DECLARE @nm_cidade VARCHAR(100)
    DECLARE @nm_uf VARCHAR(2)

    -- Cursor para percorrer os endereços
    DECLARE endereco_cursor CURSOR FOR
    SELECT
        cpf, porta, endereco, complemento, bairro, cep, cidade, uf
    FROM
        endereco
    ORDER BY
        cpf, porta

    OPEN endereco_cursor
    FETCH NEXT FROM endereco_cursor INTO @cpf, @nr_linha_arquiv, @nm_endereco, @nm_complemento, @nm_bairro, @nr_cep, @nm_cidade, @nm_uf

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Obtém o próximo número de linha para endereço
        SELECT @nr_linha_arquiv = ISNULL(MAX(nr_linha_arquiv), 0) + 1 FROM envio WHERE cpf = @cpf

        -- Move os dados para a tabela envio
        INSERT INTO envio (cpf, nr_linha_arquiv, nm_endereco, nr_endereco, nm_complemento, nm_bairro, nr_cep, nm_cidade, nm_uf)
        VALUES (@cpf, @nr_linha_arquiv, @nm_endereco, @nr_cep, @nm_complemento, @nm_bairro, @nr_cep, @nm_cidade, @nm_uf)

        FETCH NEXT FROM endereco_cursor INTO @cpf, @nr_linha_arquiv, @nm_endereco, @nm_complemento, @nm_bairro, @nr_cep, @nm_cidade, @nm_uf
    END

    CLOSE endereco_cursor
    DEALLOCATE endereco_cursor
END

EXEC sp_move_endereco_para_envio

SELECT * FROM envio ORDER BY cpf, nr_linha_arquiv;
