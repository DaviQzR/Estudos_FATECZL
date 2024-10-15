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
import br.edu.fateczl.SpringAGIS.model.Conteudo;
import br.edu.fateczl.SpringAGIS.model.Disciplina;
import br.edu.fateczl.SpringAGIS.model.Matricula;
import br.edu.fateczl.SpringAGIS.persistence.AulaDao;
import br.edu.fateczl.SpringAGIS.persistence.ConteudoDao;
import br.edu.fateczl.SpringAGIS.persistence.DisciplinaDao;
import br.edu.fateczl.SpringAGIS.persistence.GenericDao;
import br.edu.fateczl.SpringAGIS.persistence.MatriculaDisciplinaDao;
import br.edu.fateczl.SpringAGIS.persistence.ProfessorDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AulaController {
	
	@Autowired
	GenericDao gDao;
	
	@Autowired
	ProfessorDao pDao;
	
	@Autowired
	DisciplinaDao dDao;
	
	@Autowired
	AulaDao aDao;
	
	@Autowired
	ConteudoDao cDao;
	
	@Autowired
	MatriculaDisciplinaDao mdDao;
	
	@RequestMapping(name = "aula", value="/aula", method = RequestMethod.GET)
	public ModelAndView aulaGet (@RequestParam Map<String, String> allRequestParam, HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		String erro = "";	
		List<Disciplina> disciplinas = new ArrayList<>();
		
		try {
			disciplinas = listarDisciplinas();
		} catch(SQLException | ClassNotFoundException e){
			erro = e.getMessage();
		} finally {
			session.setAttribute("disciplinas", disciplinas);
			model.addAttribute("erro", erro);
		}
		return new ModelAndView("aula");
	}
	

	private List<Disciplina> listarDisciplinas() throws ClassNotFoundException, SQLException {
		return dDao.listarDisciplinas();
	}


	@RequestMapping(name = "aula", value="/aula", method = RequestMethod.POST)
	public ModelAndView aulaPost (@RequestParam Map<String, String> allRequestParam, HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		Map<String, String[]> parametros = request.getParameterMap();
		String cmd = allRequestParam.get("botao");
		String disciplina = allRequestParam.get("disciplina");
		String dataAula = allRequestParam.get("dataAula");
		
		String[] presenca = new String[50];
		String erro = "";
		String saida = "";
		Disciplina d = new Disciplina();
		int qtdaula = 0;
		boolean finalizou = false;
		List<Integer> presencas = new ArrayList<>();
		List<Aluno> alunos = new ArrayList<>();
		List<Disciplina> disciplinas = new ArrayList<>();
		
		try {
			disciplinas = listarDisciplinas();			
			if(cmd.contains("Iniciar Chamada")) {
				d.setCodigo(Integer.parseInt(disciplina));
				d = dDao.consultar(d);
				qtdaula = d.getQtdAulas();
				if(verificarAula(d.getCodigo(), dataAula)) {
					erro = "Aula para o dia "+dataAula+" já foi realizada";
				}else {					
					alunos = listarAlunos(Integer.parseInt(disciplina));
				}
			}
			if(cmd.contains("Finalizar Chamada")) {
				d = (Disciplina) session.getAttribute("disciplina");
				alunos = (List<Aluno>) session.getAttribute("alunos");
				for(String key : parametros.keySet()) {
					if(key.startsWith("presenca")) {
						presenca = parametros.get(key);
					}
				}
				saida = finalizarChamada(alunos, presenca, d.getCodigo(), dataAula);
				finalizou = true;
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			session.setAttribute("alunos", alunos);
			session.setAttribute("disciplina", d);
			model.addAttribute("disciplinas", disciplinas);
			model.addAttribute("dataAula", dataAula);
			model.addAttribute("saida", saida);
			model.addAttribute("erro", erro);
			if(finalizou) {
				session.removeAttribute("alunos");
			}
		}
		
		return new ModelAndView("aula");
	}
	
	private boolean verificarAula(int disciplina, String dataAula) throws ClassNotFoundException, SQLException {
		return aDao.verificarAulaExistente(disciplina, dataAula);
	}


	private String finalizarChamada(List<Aluno> alunos, String[] presenca, int disciplina, String dataAula) throws ClassNotFoundException, SQLException {
		String saida = "Chamada finalizada";
		for(int i=0;i<=alunos.size()-1;i++) {
			Matricula matricula = mdDao.consultarUltimaMatricula(alunos.get(i).getRa());
			saida = aDao.inserirAula(matricula.getCodigo(), disciplina, Integer.parseInt(presenca[i]), dataAula);
		}
		return saida;
	}

	private List<Aluno> listarAlunos(int conteudo) throws ClassNotFoundException, SQLException {
		return aDao.listarAlunos(conteudo);
	}
}
