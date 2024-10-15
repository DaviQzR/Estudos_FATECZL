package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Aluno;
import model.Curso;
import persistence.AlunoDao;
import persistence.CursoDao;
import persistence.GenericDao;

@WebServlet("/aluno")
public class AlunoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AlunoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String erro = "";
		List<Curso> cursos = new ArrayList<>();
		
		try {
			cursos = listarCursos();
		} catch (ClassNotFoundException | SQLException e){
			erro = e.getMessage();
		} finally {
			request.setAttribute("erro", erro);
			request.setAttribute("cursos", cursos);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("aluno.jsp");
		rd.forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//entrada
			Map<String,String[]> parameterMap = request.getParameterMap();
		/* Para recebermos o curso com sucesso, foi utilizado o CursoDao para recuperar todos os cursos no sistema
		 * 
		 * E a construção dele dentro do AlunoServlet foi necessária para determinar o curso do aluno, observável entre as linhas 89-99
		 */
				String cmd = request.getParameter("botao");
				String ra = request.getParameter("ra");
				String cpf = request.getParameter("cpf");
				String nome = request.getParameter("nome");
				String nomeSocial = request.getParameter("nomeSocial");
				String dataNasc = request.getParameter("dataNasc");
				String telefoneCelular = request.getParameter("telefoneCelular");
				String telefoneResidencial = request.getParameter("telefoneResidencial");
				String emailPessoal = request.getParameter("emailPessoal");
				String emailCorporativo = request.getParameter("emailCorporativo");
				String dataSegundoGrau = request.getParameter("dataSegundoGrau");
				String instituicaoSegundoGrau = request.getParameter("instituicaoSegundoGrau");
				String pontuacaoVestibular = request.getParameter("pontuacaoVestibular");
				String posicaoVestibular = request.getParameter("posicaoVestibular");
				String anoIngresso = request.getParameter("anoIngresso");
				String semestreIngresso = request.getParameter("semestreIngresso");
				String semestreGraduacao = request.getParameter("semestreGraduacao");
				String curso = request.getParameter("curso");
				String turno = request.getParameter("turno");
				
				//saida
				String saida="";
				String erro="";
				Aluno a = new Aluno();
				List<Aluno> alunos = new ArrayList<>();
				List<Curso> cursos = new ArrayList<>();
				Curso cr = new Curso();
				
			try {
				// Curso sendo construído no aluno ao clicar em qualquer botão que não seja o botão de listar
				cursos = listarCursos();
				if(!cmd.contains("Listar")) {
					cr.setCodigo(Integer.parseInt(curso));
					try {
						cr = chamarCurso(cr);
					} catch (SQLException | ClassNotFoundException e) {
						erro = e.getMessage();
					}
					a.setCurso(cr);
					
					a.setCpf(cpf);
				}
				if(cmd.contains("Cadastrar") || cmd.contains("Alterar")) {
					a.setCpf(cpf);
					a.setRa(ra);
					a.setNome(nome);
					a.setNomeSocial(nomeSocial);
					a.setDataNasc(dataNasc);
					a.setTelefoneCelular(telefoneCelular);
					a.setTelefoneResidencial(telefoneResidencial);
					a.setEmailPessoal(emailPessoal);
					a.setEmailCorporativo(emailCorporativo);
					a.setDataSegundoGrau(dataSegundoGrau);
					a.setInstituicaoSegundoGrau(instituicaoSegundoGrau);
					a.setPontuacaoVestibular(Double.parseDouble(pontuacaoVestibular));
					a.setPosicaoVestibular(Integer.parseInt(posicaoVestibular));
					a.setAnoIngresso(anoIngresso);
					a.setSemestreIngresso(semestreIngresso);
					a.setSemestreGraduacao(semestreGraduacao);
					cr.setCodigo(Integer.parseInt(curso));
					try {
						cr = chamarCurso(cr);
					} catch (SQLException | ClassNotFoundException e) {
						erro = e.getMessage();
					}
					a.setCurso(cr);
					a.setTurno(turno);
				}
				
					if(cmd.contains("Cadastrar")) {
						saida = cadastrarAluno(a);
						a = null;
					}
					if(cmd.contains("Alterar")) {
						saida = atualizarAluno(a);
						a = null;
					}
					if(cmd.contains("Excluir")) {
						saida = excluirAluno(a);
						a = null;
					}
					if(cmd.contains("Buscar")) {
						a = buscarAluno(a);
						cr = a.getCurso();
					}
					if(cmd.contains("Listar")) {
						alunos = listarAlunos();
					}
					
				} catch(SQLException | ClassNotFoundException e) {
					erro = e.getMessage();
				} finally {
					request.setAttribute("saida", saida);
					request.setAttribute("erro", erro);
					request.setAttribute("aluno", a);
					request.setAttribute("alunos", alunos);
					request.setAttribute("curso", cr);
					request.setAttribute("cursos", cursos);
					
					RequestDispatcher rd = request.getRequestDispatcher("aluno.jsp");
					rd.forward(request, response);
				}
	}
	
	/**Realiza um procedimento SQL usando parâmetros para realizar uma consulta na tabela Curso. A função 
	 * 
	 * @param cur
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Curso chamarCurso(Curso cur) throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		CursoDao cDao = new CursoDao(gDao);
		cur = cDao.consultar(cur);
		return cur;
	}
	/**Realiza um procedimento SQL para realizar uma chamada na tabela Curso. A função faz a chamada do objeto DAO relacionado ao curso, e executa um procedimento
	 * para listar todos os valores encontrados na tabela Curso, retornando uma lista de cursos
	 * 
	 * @return List< > - Objeto {@link Curso}
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private List<Curso> listarCursos() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		CursoDao cDao = new CursoDao(gDao);
		return cDao.listar();
	}

	/**Realiza um procedimento SQL usando parâmetros para inserção na tabela Aluno. A função recebe um objeto de tipo Aluno como parâmetro
	 * , faz a chamada do objeto DAO relacionado ao aluno, o AlunoDao, e executa o procedimento IUD com parâmetro "I", indicando que
	 * uma inserção será realizada com o parâmetro recebido pela função. 
	 * 
	 * @param a - Objeto Aluno
	 * @return String - Variável de saída, retornada pelo procedimento em SQL
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private String cadastrarAluno(Aluno a) throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		AlunoDao aDao = new AlunoDao(gDao);
		String saida = aDao.iud("I", a);
		return saida;
	}
	/**Realiza um procedimento SQL usando parâmetros para atualização na tabela Aluno. A função recebe um objeto de tipo Aluno como parâmetro,
	 * faz a chamada do objeto DAO relacionado ao aluno, o AlunoDao, e executa o procedimento IUd com parâmetro "U", indicando que
	 * uma atualização será realizada com o parâmetro recebido pela função.
	 * 
	 * @param a - Objeto Aluno
	 * @return String - Variável de saída, retornada pelo procedimento em SQL
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private String atualizarAluno(Aluno a) throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		AlunoDao aDao = new AlunoDao(gDao);
		String saida = aDao.iud("U", a);
		return saida;
	}
	/**Realiza um procedimento SQL usando parâmetros para exclusão na tabela Aluno. A função recebe um objeto de tipo Aluno como parâmetro,
	 * faz a chamada do objeto DAO relacionado ao aluno, o AlunoDao, e executa o procedimento IUD com parâmetro "D", indicando que
	 * uma exclusão será realizada com o parâmetro recebido pela função. Normalmente, com operações de exclusão em SQL, precisamos apenas
	 * do atributo chave para realizar a exclusão com sucesso, mas para manter a normalização, o objeto como todo é passado como parâmetro
	 * 
	 * @param a - Objeto Aluno
	 * @return String - Variável de saída, retornada pelo procedimento em SQL
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private String excluirAluno(Aluno a) throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		AlunoDao aDao = new AlunoDao(gDao);
		String saida = aDao.iud("D", a);
		return saida;
	}
	
	/**Realizar um procedimento SQL usando parâmetros para realizar uma consuta na tabela Aluno.
	 * 
	 * @param a
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Aluno buscarAluno(Aluno a) throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		AlunoDao aDao = new AlunoDao(gDao);
		a = aDao.consultar(a);
		return a;
	}
	/**Realiza um procedimento SQL para realizar uma chamada na tabela Aluno. A função faz a chamada do objeto DAO relacionado ao aluno, e executa um procedimento
	 * para listar todos os valores encontrados na tabela Aluno, retornando uma lista de alunos
	 * 
	 * @return List<> - Lista de objetos do tipo Aluno
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private List<Aluno> listarAlunos() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		List<Aluno> alunos = new ArrayList<>();
		AlunoDao aDao = new AlunoDao(gDao);
		alunos = aDao.listar();
		return alunos;
	}
}
