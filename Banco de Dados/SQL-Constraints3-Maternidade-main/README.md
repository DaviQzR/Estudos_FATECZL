# SQL-Constraints3-Maternidade
Criar, em SQL Server, considerando o domínio de uma Maternidade, implementado na aula passada, conforme modelado:

![image](https://github.com/DaviQzR/SQL-Constraints3-Maternidade/assets/125469425/cdaad2e5-6d9f-48fa-967a-7fa8b006eb14)

Foram delimitadas as seguintes restrições:
Para a tabela mãe:
- O ID é auto incremental, iniciando em 1001 e indo de 1 em 1
- Número de porta não pode ser negativo
- CEP deve ter 8 dígitos
- Telefone deve ter 10 dígitos
Para a tabela medico:
- Celular deve ter 11 dígitos
- Celular não pode repetir
Para a tabela bebe:
- O ID é auto incremental, iniciando em 1 e indo de 1 em 1
- Se não preenchida, a data de nascimento é o dia de hoje
- Altura não pode ser negativa
- Peso não pode ser negativo
