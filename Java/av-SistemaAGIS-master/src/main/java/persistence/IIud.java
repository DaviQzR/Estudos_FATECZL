package persistence;

import java.sql.SQLException;
public interface IIud<T> {
	
	/**Operação SQL determinada para executar um procedimento IUD, ou seja, uma operação CRUD sem a possibilidade de mostrar um objeto. O procedimento IUD é executado através
	 * da determinação da ação escolhida (pelo valor dentro da variável ação), cada letra na palavra IUD representa uma operação diferente, "I" representa a operação de inserção,
	 * "U" representa a operação de atualização, e "D" representa a operação de exclusão.
	 * 
	 * @param acao - Variável que determina o tipo de operação IUD a ser executada
	 * @param t - Objeto genérico, que será usado para as operações
	 * @return String - Variável de saída, retornada pelo procedimento em SQL
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String iud(String acao, T t) throws SQLException, ClassNotFoundException;
}
