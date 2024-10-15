# Laboratório de Banco de Dados

Atividade desenvolvida para o Laboratório de Banco de Dados ministrado pelo Prof. Leandro Colevati na FATEC ZL.

## Exercício:

- Exercício tirado de situação real.
A empresa tinha duas tabelas: Envio e Endereço, como listada abaixo. No atributo NR_LINHA_ARQUIV, há
um número que faz referência à linha de incidência do endereço na tabela endereço.
Por exemplo:

![image](https://github.com/DaviQzR/Tarefa-LabBD-Cursores/assets/125469425/3c0931c8-8022-48b8-b8a5-63df46e70191)

- Portanto, o NR_LINHA_ARQUIV (1) referencia o registro do endereço da Rua A e o NR_LINHA_ARQUIV (2)
referencia o endereço da Rua B.
- Como se trata de uma estrutura completamente mal feita, o DBA solicitou que se colcoasse as colunas
NM_ENDERECO, NR_ENDERECO, NM_COMPLEMENTO, NM_BAIRRO, NR_CEP, NM_CIDADE, NM_UF
varchar(2) e movesse os dados da tabela endereço para a tabela envio.

Fazer uma PROCEDURE, com um cursor, que resolva esse problema
