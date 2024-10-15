# SQL-DDL-DML-Locadora-SelectCase-Subquery

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

1) Fazer uma consulta que retorne ID, Ano, nome do Filme (Caso o nome do filme tenha
mais de 10 caracteres, para caber no campo da tela, mostrar os 10 primeiros
caracteres, seguidos de reticências ...) dos filmes cujos DVDs foram fabricados depois
de 01/01/2020
2) Fazer uma consulta que retorne num, data_fabricacao, qtd_meses_desde_fabricacao
(Quantos meses desde que o dvd foi fabricado até hoje) do filme Interestelar
3) Fazer uma consulta que retorne num_dvd, data_locacao, data_devolucao,
dias_alugado(Total de dias que o dvd ficou alugado) e valor das locações da cliente que
tem, no nome, o termo Rosa
4) Nome, endereço_completo (logradouro e número concatenados), cep (formato
XXXXX-XXX) dos clientes que alugaram DVD de num 10002.


