# SteamCharts
Exercício Arquivos
O Arquivo SteamCharts.csv contém informações sobre popularidade dos jogos na plataforma
Steam, estão contempladas as informações e divididas por ano e por mês. São mais de 83 mil
registros de jogos.
São interessantes para a nossa análise o nome do jogo, o ano, o mês e a média de jogadores
ativos (avg).
Fazer uma solução em java que tenha, na classe de controle (SteamController), um método
que receba como parâmetros o ano, o mês e um valor esperado para a média e exiba no
console, no seguinte formato: (Nome do jogo | Média de jogadores ativos) desde que o ano e
o mês estejam de acordo com os parâmetros e a média de jogadores ativos seja maior ou igual
ao parâmetro passado.
A classe de controle deve ter um outro método que receba o ano, o mês, um caminho de
diretório válido e um nome de arquivo válido. O método deve filtrar as linhas de acordo com o
mês e o ano inseridos pelo usuário e deve gerar um arquivo (nome.csv), no diretório válido (A
existência do diretório deve ser validada), com o seguinte formato:
nome do jogo ; média dos jogadores ativos
Uma classe de visão (Principal.java) deve ser criada com um método Main que permita realizar
as operações
