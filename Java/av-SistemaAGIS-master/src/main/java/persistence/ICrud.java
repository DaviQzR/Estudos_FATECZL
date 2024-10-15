package persistence;

import java.sql.SQLException;
import java.util.List;

public interface ICrud<T> {
	/** Consulta um Objeto no banco de dados do sistema, onde o parâmetro é substituivel ao realizar o override em uma classe de controle/servlet
	 * 
	 * @param t - Atributo genérico
	 * @return Objeto com todos os atributos já construidos
	 * @throws SQLException Caso haja algum erro nos parâmetros inseridos ao executar a query SQL
	 * @throws ClassNotFoundException
	 */
	public T consultar(T t) throws SQLException, ClassNotFoundException;
	/** Consulta uma lista de objetos no banco de dados do sistema, onde o parâmetro é substituivel ao realizar o override em uma classe de controle/servlet
	 * 
	 * @return List<> - Uma lista de objetos, onde os objetos possuem todos os atributos já construidos 
	 * @throws SQLException Caso haja algum erro nos parâmetros inseridos ao executar a query SQL
	 * @throws ClassNotFoundException
	 */
	public List<T> listar() throws SQLException, ClassNotFoundException;
}