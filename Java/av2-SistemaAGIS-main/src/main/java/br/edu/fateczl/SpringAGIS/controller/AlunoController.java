package br.edu.fateczl.SpringAGIS.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fateczl.SpringAGIS.model.Aluno;
import br.edu.fateczl.SpringAGIS.model.Curso;
import br.edu.fateczl.SpringAGIS.persistence.AlunoDao;
import br.edu.fateczl.SpringAGIS.persistence.CursoDao;
import br.edu.fateczl.SpringAGIS.persistence.GenericDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AlunoController {
	
	@Autowired
	GenericDao gDao;
	
	@Autowired
	AlunoDao aDao;
	
	@Autowired
	CursoDao cDao;
	
	@RequestMapping(name="aluno", value="/aluno", method = RequestMethod.GET)
	public ModelAndView alunoGet(@RequestParam Map<String, String> allRequestParam, HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		session.invalidate();
		String erro = "";
		List<Curso> cursos = new ArrayList<>();
		
		try {
			cursos = listarCursos();
		} catch (ClassNotFoundException | SQLException e){
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("cursos", cursos);
		}
		
		return new ModelAndView("aluno");
	}
	
	@RequestMapping(name="aluno", value="/aluno", method = RequestMethod.POST)
	public ModelAndView alunoPost(@RequestParam Map<String, String> allRequestParam, ModelMap model) {
		String cmd = allRequestParam.get("botao");
		String ra = allRequestParam.get("ra");
		String cpf = allRequestParam.get("cpf");
		String nome = allRequestParam.get("nome");
		String nomeSocial = allRequestParam.get("nomeSocial");
		String dataNasc = allRequestParam.get("dataNasc");
		String telefoneCelular = allRequestParam.get("telefoneCelular");
		String telefoneResidencial = allRequestParam.get("telefoneResidencial");
		String emailPessoal = allRequestParam.get("emailPessoal");
		String emailCorporativo = allRequestParam.get("emailCorporativo");
		String dataSegundoGrau = allRequestParam.get("dataSegundoGrau");
		String instituicaoSegundoGrau = allRequestParam.get("instituicaoSegundoGrau");
		String pontuacaoVestibular = allRequestParam.get("pontuacaoVestibular");
		String posicaoVestibular = allRequestParam.get("posicaoVestibular");
		String anoIngresso = allRequestParam.get("anoIngresso");
		String semestreIngresso = allRequestParam.get("semestreIngresso");
		String semestreGraduacao = allRequestParam.get("semestreGraduacao");
		String curso = allRequestParam.get("curso");
		String turno = allRequestParam.get("turno");
		
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
				model.addAttribute("saida", saida);
				model.addAttribute("erro", erro);
				model.addAttribute("aluno", a);
				model.addAttribute("alunos", alunos);
				model.addAttribute("curso", cr);
				model.addAttribute("cursos", cursos);
			}
		
		return new ModelAndView("aluno");
	}
	
	/**Realiza um procedimento SQL usando parâmetros para realizar uma consulta na tabela Curso. A função 
	 * 
	 * @param cur
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Curso chamarCurso(Curso cur) throws ClassNotFoundException, SQLException {
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
		List<Aluno> alunos = new ArrayList<>();
		alunos = aDao.listar();
		return alunos;
	}
	
}
