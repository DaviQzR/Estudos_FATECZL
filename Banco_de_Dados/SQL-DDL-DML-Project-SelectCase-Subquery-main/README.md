# SQL-DDL-DML-Project-SelectCase-Subquery
Considere o diagrama abaixo: 

![image](https://github.com/DaviQzR/SQL-DDL-DML-Project-SelectCase-Subquery/assets/125469425/f75d5a24-4e6f-4c40-8c6d-3aa3f4c4c600)

Criar as tabelas da forma que foram diagramadas, obedecendo as seguintes restrições:

1--> A coluna users_id da tabela associativa é FK da coluna id, tabela users

2--> A coluna projects_id da tabela associativa é FK da coluna id, tabela projects

3--> A coluna date da tabela projects deve verificar se a data é posterior que 01/09/2014.
Caso contrário, o registro não deve ser inserido

4--> A PK de projects deve ser de auto incremento, iniciando em 10001, com incremento de
1 

5--> A PK de users deve ser de auto incremento, iniciando em 1, com incremento de 1

6--> O valor padrão da coluna password da tabela users, deverá ser 123mudar

7--> A coluna username da tabela users deve ter restrição de unicidade.

--> Modificar a coluna username da tabela Users para varchar(10)

--> Modificar a coluna password da tabela Users para varchar(8)

Inserir os dados abaixo:

![image](https://github.com/DaviQzR/SQL-DDL-DML-Project-SelectCase-Subquery/assets/125469425/0c3b3305-2564-4569-84a2-3c295c3b4bea)

Considerar as situações:
- O projeto de Manutenção atrasou, mudar a data para 12/09/2014
- O username de aparecido (usar o nome como condição de mudança) está feio, mudar para
Rh_cido
- Mudar o password do username Rh_maria (usar o username como condição de mudança)
para 888@*, mas a condição deve verificar se o password dela ainda é 123mudar
- O user de id 2 não participa mais do projeto 10002, removê-lo da associativa

  Consultar:
  
- Fazer uma consulta que retorne id, nome, email, username e caso a senha seja diferente de
123mudar, mostrar ******** (8 asteriscos), caso contrário, mostrar a própria senha.
- Considerando que o projeto 10001 durou 15 dias, fazer uma consulta que mostre o nome do
projeto, descrição, data, data_final do projeto realizado por usuário de e-mail
aparecido@empresa.com
- Fazer uma consulta que retorne o nome e o email dos usuários que estão envolvidos no
projeto de nome Auditoria
- Considerando que o custo diário do projeto, cujo nome tem o termo Manutenção, é de 79.85
e ele deve finalizar 16/09/2014, consultar, nome, descrição, data, data_final e custo_total do
projeto


