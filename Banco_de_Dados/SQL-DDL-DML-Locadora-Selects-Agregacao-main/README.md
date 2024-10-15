# SQL-DDL-DML-Locadora-Selects-Agregacao

Considere o diagrama abaixo:

![image](https://github.com/DaviQzR/SQL-DDL-DML-Locadora-SelectCase-Subquery/assets/125469425/fb56d09d-50e6-4ed3-b59e-36f7c3751d43)

N – atributo NULL

Restrições:

--> Ano de filme deve ser menor ou igual a 2021

--> Data de fabricação de DVD deve ser menor do que hoje

--> Número do endereço de Cliente deve ser positivo

--> CEP do endereço de Cliente deve ter, especificamente, 8 caracteres

--> Data de locação de Locação, por padrão, deve ser hoje

--> Data de devolução de Locação, deve ser maior que a data de locação
Valor de Locação deve ser positivo

Esquema:
A entidade estrela deveria ter o nome real da estrela, com 50 caracteres
Verificando um dos nomes de filme, percebeu-se que o nome do filme deveria ser um atributo
com 80 caracteres

Considere os dados:

![image](https://github.com/DaviQzR/SQL-DDL-DML-Locadora-SelectCase-Subquery/assets/125469425/156bbbf1-b9fc-4775-8d5f-ef2907fbe2d7)

![image](https://github.com/DaviQzR/SQL-DDL-DML-Locadora-SelectCase-Subquery/assets/125469425/6ecfa037-5a78-4eec-85c1-9059acb5eb63)

![image](https://github.com/DaviQzR/SQL-DDL-DML-Locadora-SelectCase-Subquery/assets/125469425/57208e16-22ca-4fe1-ae4f-c6445d47af89)

Operações com dados:


--> Os CEP dos clientes 5503 e 5504 são 08411150 e 02918190 respectivamente

--> A locação de 2021-02-18 do cliente 5502 teve o valor de 3.25 para cada DVD alugado

--> A locação de 2021-02-24 do cliente 5501 teve o valor de 3.10 para cada DVD alugado

--> O DVD 10005 foi fabricado em 2019-07-14

--> O nome real de Miles Teller é Miles Alexander Teller

--> O filme Sing não tem DVD cadastrado e deve ser excluído

Consultar:

1) Consultar, num_cadastro do cliente, nome do cliente, titulo do filme, data_fabricação
do dvd, valor da locação, dos dvds que tem a maior data de fabricação dentre todos os
cadastrados.
2) Consultar Consultar, num_cadastro do cliente, nome do cliente, data de locação
(Formato DD/MM/AAAA) e a quantidade de DVD ́s alugados por cliente (Chamar essa
coluna de qtd), por data de locação
3) Consultar Consultar, num_cadastro do cliente, nome do cliente, data de locação
(Formato DD/MM/AAAA) e a valor total de todos os dvd ́s alugados (Chamar essa
coluna de valor_total), por data de locação
4) Consultar Consultar, num_cadastro do cliente, nome do cliente, Endereço
concatenado de logradouro e numero como Endereco, data de locação (Formato
DD/MM/AAAA) dos clientes que alugaram mais de 2 filmes simultaneamente
