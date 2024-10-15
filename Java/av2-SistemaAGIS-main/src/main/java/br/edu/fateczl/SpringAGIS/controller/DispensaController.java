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
import br.edu.fateczl.SpringAGIS.model.Disciplina;
import br.edu.fateczl.SpringAGIS.model.Dispensa;
import br.edu.fateczl.SpringAGIS.persistence.AlunoDao;
import br.edu.fateczl.SpringAGIS.persistence.DisciplinaDao;
import br.edu.fateczl.SpringAGIS.persistence.DispensaDao;
import br.edu.fateczl.SpringAGIS.persistence.GenericDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class DispensaController {
	
	@Autowired
	GenericDao gDao;
	
	@Autowired
	AlunoDao aDao;
	
	@Autowired
	DisciplinaDao dDao;
	
	@Autowired
	DispensaDao disDao;
	
	@RequestMapping(name="dispensa", value="/dispensa", method = RequestMethod.GET)
	public ModelAndView dispensaGet(@RequestParam Map<String, String> allRequestParam, HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		session.invalidate();
		return new ModelAndView("dispensa");
	}
	
	
	@RequestMapping(name="dispensa", value="/dispensa", method = RequestMethod.POST)
	public ModelAndView dispensaPost(@RequestParam Map<String, String> allRequestParam, ModelMap model) {
		String cmd = allRequestParam.get("botao");
		String ra = allRequestParam.get("ra");
		String disciplina = allRequestParam.get("disciplina");
		String motivo = allRequestParam.get("motivo");
		
		String saida = "";
		String erro = "";
		boolean found = false;
		Aluno a = new Aluno();
		Disciplina d = new Disciplina();
		List<Disciplina> disciplinas = new ArrayList<>();
		List<Dispensa> dispensas = new ArrayList<>();
		
		try {
			a.setRa(ra);
			if(cmd.contains("Consultar Aluno")) {
				a = buscarAluno(a);				
				disciplinas = listarDisciplinas(ra);
				dispensas = listarDispensas(ra);
				found = true;
			}
			if(cmd.contains("Solicitar Dispensa")) {
				d.setCodigo(Integer.parseInt(disciplina));
				a = buscarAluno(a);
				d = buscarDisciplina(d);
				saida = solicitarDispensa(a, d, motivo);
			}
		} catch(SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("saida", saida);
			model.addAttribute("erro", erro);
			model.addAttribute("disciplinas", disciplinas);
			model.addAttribute("disciplina", d);
			model.addAttribute("aluno", a);
			model.addAttribute("found", found);
			model.addAttribute("dispensas", dispensas);
		}
		return new ModelAndView("dispensa");
	}

	private List<Dispensa> listarDispensas(String ra) throws ClassNotFoundException, SQLException {
		return disDao.listarPorAluno(ra);
	}

	private String solicitarDispensa(Aluno a, Disciplina d, String motivo) throws ClassNotFoundException, SQLException {
		String saida = disDao.enviarSolicitacaoDispensa(a, d, motivo);
		return saida;
	}

	private Disciplina buscarDisciplina(Disciplina d) throws ClassNotFoundException, SQLException {
		d = dDao.consultar(d);
		return d;
	}

	private Aluno buscarAluno(Aluno a) throws ClassNotFoundException, SQLException {
		a = aDao.consultar(a);
		return a;
	}

	private List<Disciplina> listarDisciplinas(String ra) throws ClassNotFoundException, SQLException {
		return dDao.listarDisciplinasPorAluno(ra);
	}
	
	
}
