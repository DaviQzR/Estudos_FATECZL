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
import br.edu.fateczl.SpringAGIS.model.MatriculaDisciplinas;
import br.edu.fateczl.SpringAGIS.model.Professor;
import br.edu.fateczl.SpringAGIS.persistence.AlunoDao;
import br.edu.fateczl.SpringAGIS.persistence.DisciplinaDao;
import br.edu.fateczl.SpringAGIS.persistence.GenericDao;
import br.edu.fateczl.SpringAGIS.persistence.HistoricoDao;
import br.edu.fateczl.SpringAGIS.persistence.MatriculaDisciplinaDao;

@Controller
public class HistoricoController {
	
	@Autowired 
	GenericDao gDao;
	
	@Autowired 
	AlunoDao aDao;
	
	@Autowired
	DisciplinaDao dDao;
	
	@Autowired
	MatriculaDisciplinaDao mdDao;
	
	@RequestMapping(name = "historico", value="/historico", method = RequestMethod.GET)
	public ModelAndView historicoGet (@RequestParam Map<String, String> allRequestParam, ModelMap model) {
		return new ModelAndView("historico");
	}
	
	@RequestMapping(name = "historico", value="/historico", method = RequestMethod.POST)
	public ModelAndView historicoPost (@RequestParam Map<String, String> allRequestParam, ModelMap model) {
		String cmd = allRequestParam.get("botao");
		String ra = allRequestParam.get("ra");
		
		Aluno a = new Aluno();
		List<MatriculaDisciplinas> md = new ArrayList<>();
		String saida = "";
		String erro = "";
		
		try {
			if(cmd.contains("Consultar")) {
				a.setRa(ra);
				a = buscarAluno(a);
				md = visualizarHistorico(a, md);
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("saida", saida);
			model.addAttribute("erro", erro);
			model.addAttribute("aluno", a);
			model.addAttribute("disciplinas", md);
		}
		return new ModelAndView("historico");
	}

	private Aluno buscarAluno(Aluno a) throws ClassNotFoundException, SQLException {
		a = aDao.consultarAlunoRa(a);
		return a;
	}

	private List<MatriculaDisciplinas> visualizarHistorico(Aluno a, List<MatriculaDisciplinas> md) throws ClassNotFoundException, SQLException {
		md = mdDao.listarSituacao(a.getRa());
		return md;
	}
	
}
