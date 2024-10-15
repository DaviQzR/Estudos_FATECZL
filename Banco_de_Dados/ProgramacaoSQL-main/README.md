# Programação SQL Laboratório de Banco de Dados
## Prof. Leandro Colevati - FATEC ZL

Este repositório contém soluções para os exercícios propostos no Laboratório de Banco de Dados ministrado pelo Prof. Leandro Colevati na FATEC ZL.

## Exercícios:

### 1) Fazer em SQL Server os seguintes algoritmos:
a) **Identificar múltiplos de 2, 3, 5:**
   Fazer um algoritmo que leia um número e mostre se é múltiplo de 2, 3, 5 ou nenhum deles.

b) **Maior e menor entre três números:**
   Fazer um algoritmo que leia três números e mostre o maior e o menor.

c) **Série de Fibonacci:**
   Fazer um algoritmo que calcule os 15 primeiros termos da série de Fibonacci (1, 1, 2, 3, 5, 8, 13, 21, ...) e a soma desses termos.

d) **Manipulação de texto:**
   Fazer um algoritmo que separe uma frase, colocando todas as letras em maiúsculo e minúsculo, usando as funções UPPER e LOWER.

e) **Inversão de palavra:**
   Fazer um algoritmo que inverta uma palavra, utilizando a função SUBSTRING.

f) **Geração de massa de dados para tabela Computador:**
   Considerando a tabela abaixo, gerar uma massa de dados com 100 registros para fins de teste, seguindo as regras estabelecidas:
   
   Tabela `Computador`:

   
![image](https://github.com/DaviQzR/ProgramacaoSQL/assets/125469425/61006592-2df8-4843-9bcb-d8880335267b)


- ID inicia em 10001 e incrementa de 1 em 1
- Marca segue o padrão: Marca 1, Marca 2, Marca 3, etc.
- QtdRAM é um número aleatório entre os valores permitidos (2, 4, 8, 16)
- TipoHD segue o padrão:
  - Se o ID dividido por 3 der resto 0, é HDD
  - Se o ID dividido por 3 der resto 1, é SSD
  - Se o ID dividido por 3 der resto 2, é M2 NVME
- QtdHD segue o padrão:
  - Se o TipoHD for HDD, um valor aleatório entre os valores permitidos (500, 1000 ou 2000)
  - Se o TipoHD for SSD, um valor aleatório entre os valores permitidos (128, 256, 512)
- FreqCPU é um número aleatório entre 1.70 e 3.20

## Como Utilizar
1. Clone este repositório.
2. Execute os scripts SQL para cada exercício no SQL Server.
3. Analise os resultados e adapte conforme necessário para suas aplicações.

## Contribuições
Contribuições são bem-vindas! Se você encontrou uma maneira de melhorar estas soluções ou corrigir possíveis problemas, sinta-se à vontade para abrir um problema ou enviar um pull request.

