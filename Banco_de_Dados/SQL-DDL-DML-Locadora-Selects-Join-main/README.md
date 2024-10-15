# SQL-DDL-DML-Locadora-Selects-Join
Exercício Selects e Join :

![image](https://github.com/DaviQzR/SQL-DDL-DML-Locadora/assets/125469425/d8150fe4-5162-4827-b551-6ad9dff56712)

N – atributo NULL

Restrições:
Ano de filme deve ser menor ou igual a 2021
Data de fabricação de DVD deve ser menor do que hoje
Número do endereço de Cliente deve ser positivo
CEP do endereço de Cliente deve ter, especificamente, 8 caracteres
Data de locação de Locação, por padrão, deve ser hoje
Data de devolução de Locação, deve ser maior que a data de locação
Valor de Locação deve ser positivo

Esquema:
A entidade estrela deveria ter o nome real da estrela, com 50 caracteres
Verificando um dos nomes de filme, percebeu-se que o nome do filme deveria ser um atributo
com 80 caracteres

Considere os dados:


![image](https://github.com/DaviQzR/SQL-DDL-DML-Locadora/assets/125469425/03dfd0d1-e3a5-4594-8a93-6d536099ff95)


![image](https://github.com/DaviQzR/SQL-DDL-DML-Locadora/assets/125469425/264a8d29-58e9-47fc-a465-de7d9b1171f8)

![image](https://github.com/DaviQzR/SQL-DDL-DML-Locadora/assets/125469425/68f6f541-74f8-46ce-90e8-e90aac9026f2)

Operações com dados:

--> Os CEP dos clientes 5503 e 5504 são 08411150 e 02918190 respectivamente

--> A locação de 2021-02-18 do cliente 5502 teve o valor de 3.25 para cada DVD alugado

--> A locação de 2021-02-24 do cliente 5501 teve o valor de 3.10 para cada DVD alugado

--> O DVD 10005 foi fabricado em 2019-07-14

--> O nome real de Miles Teller é Miles Alexander Teller

--> O filme Sing não tem DVD cadastrado e deve ser excluído

Consultar:


• Não esquecer de rever as restrições de datas
1) Consultar num_cadastro do cliente, nome do cliente, data_locacao (Formato
dd/mm/aaaa), Qtd_dias_alugado (total de dias que o filme ficou alugado), titulo do
filme, ano do filme da locação do cliente cujo nome inicia com Matilde
2) Consultar nome da estrela, nome_real da estrela, título do filme dos filmes
cadastrados do ano de 2015
3) Consultar título do filme, data_fabricação do dvd (formato dd/mm/aaaa), caso a
diferença do ano do filme com o ano atual seja maior que 6, deve aparecer a diferença
do ano com o ano atual concatenado com a palavra anos (Exemplo: 7 anos), caso
contrário só a diferença (Exemplo: 4).
