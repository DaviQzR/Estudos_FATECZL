USE master 
GO
DROP DATABASE Mecanica

CREATE DATABASE mecanica
GO
USE Mecanica

CREATE TABLE cliente(
Id			INT				NOT NULL IDENTITY (3401,15),
Nome		VARCHAR(100)	NOT NULL, 
Logradouro  VARCHAR(200)	NOT NULL,
Numero		INT			    NOT NULL CHECK (Numero > 0),
Cep			CHAR(8)			NOT NULL CHECK (LEN(Cep) = 8),
Complemento VARCHAR(255)    NOT NULL
PRIMARY KEY (Id)
)
GO

CREATE TABLE Telefone (
Telefone	 CHAR(11)		NOT NULL CHECK (LEN(Telefone) = 11),
Id			 INT			NOT NULL
PRIMARY KEY (Telefone, Id)
FOREIGN KEY (Id) REFERENCES cliente (Id)
)
GO

CREATE TABLE Veiculo (
Placa			CHAR(7)			NOT NULL CHECK(LEN(Placa) = 7),
Marca			VARCHAR(30)		NOT NULL,
Modelo			VARCHAR(30)		NOT NULL,
Cor				VARCHAR(15)		NOT NULL,
Ano_Fabricacao  INT			NOT NULL CHECK(Ano_Fabricacao > 1997),
Ano_Modelo		INT				NOT NULL CHECK(Ano_Modelo > 1997),
Data_Aquisicao	DATE			NOT NULL,
Id				INT				NOT NULL
PRIMARY KEY (Placa)
FOREIGN KEY (Id) REFERENCES cliente (Id),
CONSTRAINT chk_AnoModelo_AnoFabricacao CHECK(Ano_Modelo >= Ano_Fabricacao AND Ano_Modelo <= Ano_Fabricacao + 1)
)
GO

CREATE TABLE Categoria (
Id				INT				NOT NULL IDENTITY(1,1),
Categoria		VARCHAR(10)		NOT NULL,
Valor_Hora		DECIMAL(4,2)	NOT NULL CHECK (Valor_Hora > 0)
PRIMARY KEY (ID),
CONSTRAINT chk_Categoria_Valor_Hora 
CHECK   ((UPPER(Categoria) = 'Estagiário' AND Valor_Hora > 15.00) OR
        ((UPPER(Categoria) = 'Nível 1'    AND Valor_Hora > 25.00) OR
        ((UPPER(Categoria) = 'Nível 2'    AND Valor_Hora > 35.00) OR
        ((UPPER(Categoria) = 'Nível 3'    AND Valor_Hora > 50.00)))))
)
GO

CREATE TABLE Funcionario (
Id					  INT			NOT NULL IDENTITY (101,1),
Nome				  VARCHAR(100)	NOT NULL,
Logradouro			  VARCHAR(200)	NOT NULL,
Numero				  INT			NOT NULL CHECK (Numero > 0),
Telefone			  CHAR(11)		NOT NULL CHECK (LEN(Telefone) = 11),
Categoria_Habilitacao CHAR(02)		NOT NULL CHECK ((UPPER(Categoria_Habilitacao) = 'A') OR (UPPER(Categoria_Habilitacao) = 'B')
 OR (UPPER(Categoria_Habilitacao) = 'C') OR (UPPER(Categoria_Habilitacao) = 'D') OR (UPPER(Categoria_Habilitacao) = 'E')),
Id_Categoria		  INT			NOT NULL	
PRIMARY KEY (Id)
FOREIGN KEY (Id_Categoria) REFERENCES Categoria(Id)
)
GO

CREATE TABLE Peca (
Id					INT			 NOT NULL IDENTITY(3411,7),
Nome				VARCHAR(30)  NOT NULL UNIQUE,
Preco				DECIMAL(4,2) NOT NULL CHECK(Preco >= 0),
Estoque				INT			 NOT NULL CHECK(Estoque>=10)
PRIMARY KEY (Id)
)
GO

CREATE TABLE Reparo (
Placa				CHAR(07)			NOT NULL,
Funcionario			INT					NOT NULL,
Peca				INT					NOT NULL,
Data_Reparo			DATE				NOT NULL DEFAULT(GETDATE()),
Custo_Total			DECIMAL(4,2)		NOT NULL CHECK(Custo_Total >= 0), 
Tempo				INT					NOT NULL
PRIMARY KEY (Placa, Funcionario, Peca, Data_Reparo)
FOREIGN KEY (Placa) REFERENCES Veiculo (Placa),
FOREIGN KEY (Funcionario) REFERENCES Funcionario (Id),
FOREIGN KEY (Peca) REFERENCES Peca (ID)
)
GO
