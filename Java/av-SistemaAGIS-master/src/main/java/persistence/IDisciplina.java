package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Aluno;
import model.Matricula;
import model.MatriculaDisciplinas;

public interface IDisciplina {
	/** Gera uma nova matrícula para um aluno, usando seu RA como parâmetro. Alunos novos recebem uma matrícula a ser feita.
	 * 
	 * @param alunoRa
	 * @return Variável de saída
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String gerarMatricula(String alunoRa) throws SQLException, ClassNotFoundException;
	
	/** Insere uma nova matrícula de um aluno no banco de dados. O método usa a matrícula gerada pelo método gerarMatricula para inserir essa nova matricula,
	 *  um aluno com uma matrícula prévia recebe uma nova matrícula, com dados da ultima matricula. 
	 * <p>
	 * O método faz uma alteração/inserção dentro de uma estrutura de repetição dentro da classe de controle.
	 * @param ra - RA de um aluno
	 * @param codigoMatricula - Codigo da matrícula gerada pelo método gerarMatricula
	 * @param codigoDisciplina - Codigo da disciplina que será inserida
	 * @return Variável de saída
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String inserirMatricula(String ra, int codigoMatricula, int codigoDisciplina) throws SQLException, ClassNotFoundException;
	
	/** Lista as disciplinas de um aluno. Um aluno está em determinado curso, portanto, só as disciplinas pertencentes ao curso que o aluno está cursando serão listadas.
	 * 
	 * @param alunoRa - RA de um aluno
	 * @return Lista com todas as disciplinas presentes no curso em que o aluno está cursando
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<MatriculaDisciplinas> listarSituacao(String alunoRa) throws SQLException, ClassNotFoundException;
	
	/** Consulta a ultima matrícula feita por um aluno
	 * 
	 * @param alunoRa - RA de um aluno
	 * @return Ultima matrícula feita por um aluno
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Matricula consultarUltimaMatricula(String alunoRa) throws SQLException, ClassNotFoundException;
}
