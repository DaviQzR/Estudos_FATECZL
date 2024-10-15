# Triggers Laboratório de Banco de Dados
## Prof. Leandro Colevati, FATEC ZL

### EXERCÍCIO 1
- Uma empresa vende produtos alimentícios
- A empresa dá pontos, para seus clientes, que podem ser revertidos em prêmios
- Para não prejudicar a tabela venda, nenhum produto pode ser deletado, mesmo que não
venha mais a ser vendido
- Para não prejudicar os relatórios e a contabilidade, a tabela venda não pode ser alterada.
- Ao invés de alterar a tabela venda deve-se exibir uma tabela com o nome do último cliente que
comprou e o valor da última compra
- Após a inserção de cada linha na tabela venda, 10% do total deverá ser transformado em
pontos.
- Se o cliente ainda não estiver na tabela de pontos, deve ser inserido automaticamente após
sua primeira compra
- Se o cliente atingir 1 ponto, deve receber uma mensagem (PRINT SQL Server) dizendo que
ganhou e remove esse 1 ponto da tabela de pontos

![image](https://github.com/DaviQzR/LabBD-Triggers-Aula/assets/125469425/f3b67944-a6da-4c0f-b259-02ad919e139c)


### EXERCÍCIO 2

![image](https://github.com/DaviQzR/LabBD-Triggers-Aula/assets/125469425/3232cf0a-c32d-47a0-9ee6-c2f13a1e95a7)

- Fazer uma TRIGGER AFTER na tabela Venda que, uma vez feito um INSERT, verifique se a quantidade
está disponível em estoque. Caso esteja, a venda se concretiza, caso contrário, a venda deverá ser
cancelada e uma mensagem de erro deverá ser enviada. A mesma TRIGGER deverá validar, caso a
venda se concretize, se o estoque está abaixo do estoque mínimo determinado ou se após a venda,
ficará abaixo do estoque considerado mínimo e deverá lançar um print na tela avisando das duas
situações.
- Fazer uma UDF (User Defined Function) Multi Statement Table, que apresente, para uma dada nota
fiscal, a seguinte saída:
(Nota_Fiscal | Codigo_Produto | Nome_Produto | Descricao_Produto | Valor_Unitario | Quantidade
| Valor_Total*)
* Considere que Valor_Total = Valor_Unitário * Quantidade

