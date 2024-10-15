package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Aluno;
import model.Disciplina;
import model.Matricula;
import model.MatriculaDisciplinas;
import persistence.GenericDao;
import persistence.MatriculaDisciplinaDao;

@WebServlet("/matricula")
public class MatriculaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MatriculaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDate dataAtual = LocalDate.now();
		boolean intervaloSemestre = validarDataSemestral(dataAtual);
		request.setAttribute("intervalo", intervaloSemestre);
		RequestDispatcher rd = request.getRequestDispatcher("matricula.jsp");
		rd.forward(request, response);			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Entrada
		String cmd = request.getParameter("botao");
		String ra = request.getParameter("ra");
		Matricula matricula = new Matricula();
		String[] disciplinasSelecionadas = request.getParameterValues("disciplinasSelecionadas");
		if(disciplinasSelecionadas != null) {
			for(String str : disciplinasSelecionadas) {			
				System.out.println(str);
			}
		}
		
		// Saída
		String saida="";
		String erro="";
		boolean listar = false;
		Aluno a = new Aluno();
		List<MatriculaDisciplinas> matriculaDisciplinas = new ArrayList<>();
		
		try {
			if(cmd.contains("Iniciar Matricula")) {
				matricula = ultimaMatricula(ra);	
				if(validarDataMatricula(matricula.getDataMatricula())) {
					a.setRa(ra);
					matriculaDisciplinas = listarDisciplinas(ra);					
				}else {
					erro = "Matrícula já foi realizada";
				}
			}
			if(cmd.contains("Confirmar Matricula")) {
				inserirMatricula(disciplinasSelecionadas, ra);
				saida = "Matricula finalizada";
			}
			if(cmd.contains("Consultar Matricula")) {
				a.setRa(ra);
				matriculaDisciplinas = listarDisciplinas(ra);
				listar = true;
			}
		} catch (SQLException | ClassNotFoundException e) {		
			erro = e.getMessage();
		} finally {
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("disciplinas", matriculaDisciplinas);
			request.setAttribute("aluno", a);
			request.setAttribute("listar", listar);
			
			RequestDispatcher rd = request.getRequestDispatcher("matricula.jsp");
			rd.forward(request, response);
		}
	}
	
	/**Realiza uma operação SQL para inserir, em uma nova matrícula, N disciplinas que serão cursadas pelo aluno. A função recebe um vetor de disciplinas e o RA do aluno como parâmetro,
	 * o RA  
	 * 
	 * @param disciplinasSelecionadas - Vetor com todas as disciplinas selecionadas no proecsso de matricula
	 * @param cpf - CPF de um aluno
	 * @return String saída, indicando o resultado determinado da query 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private String inserirMatricula(String[] disciplinasSelecionadas, String ra) throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		MatriculaDisciplinaDao mdDao = new MatriculaDisciplinaDao(gDao);
		String codigoMatricula = mdDao.gerarMatricula(ra);
		System.out.println(codigoMatricula);
		String saida = null;
		for(String str : disciplinasSelecionadas) {
			saida = mdDao.inserirMatricula(ra, Integer.parseInt(codigoMatricula), Integer.parseInt(str));
		}
		return saida;
	}
	
	/**Lista todas as disciplinas disponíveis para um aluno matricular. A função assume que um aluno já possui uma matrícula.
	 * 
	 * 
	 * @param alunoCpf - CPF de um aluno
	 * @return Lista com todas as 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see cadastrarAluno
	 */
	private List<MatriculaDisciplinas> listarDisciplinas(String alunoCpf) throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		MatriculaDisciplinaDao mdDao = new MatriculaDisciplinaDao(gDao);
		List<MatriculaDisciplinas> md = new ArrayList<>();
		md = mdDao.listarSituacao(alunoCpf);
		return md;
	}
	
	/** Verifica se o sistema está dentro do período de matrícula (15 a 21 de Janeiro - 15 a 21 de Julho)
	 * 
	 * @param dataAtual - Data de hoje
	 * @return Variavel booleana, para confirmar a validação
	 */
	private boolean validarDataSemestral(LocalDate dataAtual) {
		LocalDate semestre1Inicio = LocalDate.of(LocalDate.now().getYear(), 1, 14);
		LocalDate semestre1Final = LocalDate.of(LocalDate.now().getYear(), 1, 22);
		LocalDate semestre2Inicio = LocalDate.of(LocalDate.now().getYear(), 7, 14);
		LocalDate semestre2Final = LocalDate.of(LocalDate.now().getYear(), 7, 22);
		
		if((dataAtual.isAfter(semestre1Inicio) && dataAtual.isBefore(semestre1Final)) || (dataAtual.isAfter(semestre2Inicio) && dataAtual.isBefore(semestre2Final))) {
			return true;
		}else{
			return false;
		}
	}
	
	/** Consulta a ultima matrícula feita por um aluno, usando o seu RA como parâmetro
	 * 
	 * @param ra - RA de um aluno
	 * @return A ultima matrícula feita por um aluno
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Matricula ultimaMatricula(String ra) throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		MatriculaDisciplinaDao mdDao = new MatriculaDisciplinaDao(gDao);
		return mdDao.consultarUltimaMatricula(ra);
	}
	
	/** Verifica se a matrícula já foi realizada no período atual de matrículas (Para impedir o aluno de realizar mais de uma matrícula por semestre)
	 * 
	 * @param dataMatricula - A data em que a matrícula foi realizada
	 * @return Variavel booleana para confirmação da verificação
	 */
	private boolean validarDataMatricula(String dataMatricula) {
		Date dataSql = Date.valueOf(dataMatricula);
		boolean validacao = false;
		LocalDate data = dataSql.toLocalDate();
		LocalDate semestre1Inicio = LocalDate.of(LocalDate.now().getYear(), 1, 14);
		LocalDate semestre1Final = LocalDate.of(LocalDate.now().getYear(), 1, 22);
		LocalDate semestre2Inicio = LocalDate.of(LocalDate.now().getYear(), 7, 14);
		LocalDate semestre2Final = LocalDate.of(LocalDate.now().getYear(), 7, 22);
		
		if((data.isAfter(semestre1Inicio)) && data.isBefore(semestre1Final)) {
			validacao = false;
		}else{
			if(data.isAfter(semestre2Inicio) && data.isBefore(semestre2Final)) {				
				validacao = false;				
			}else {
				validacao = true;
			}
		}
		return validacao;
	}
}