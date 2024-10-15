# Loja
Uma determinada loja vende peças no atacado, mas, para comprar no atacado, cada cliente pode comprar apenas um tipo de peça. Criar uma aplicação java, baseada na biblioteca da fila de objetos, que faça:
1) Criar um objeto model com os atributos:

2) Criar uma classe view (Principal.java) que, na main:
a. Inicialize uma nova fila
b. Insira 20 clientes com suas características, sendo:
i. Nome (Cliente1, Cliente2, Cliente3, ...)
ii. Quan�dade de peças (Número entre 20 e 50)
iii. Valor das Peças (Número entre 5.00 e 100.00)

3) Criar uma classe controller chamada OperacaoController, que tenha um método
caixa(Fila: fila): void, que receba a fila populada, como parâmetro, e faça, para cada
Cliente:
a. Calcular o valor total da compra (quantidade * valor)
b. Exibir o nome do cliente, o valor total da compra
4) O método main da classe Principal.java deve chamar o método caixa(Fila: fila): void.
O método caixa(Fila: fila): void deve estar baseado nas operações da fila (insert(), remove(),
list(), size(), isEmpty())
