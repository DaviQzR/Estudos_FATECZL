package br.edu.fateczl.SpringAGIS.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import br.edu.fateczl.SpringAGIS.model.Matricula;
import br.edu.fateczl.SpringAGIS.model.MatriculaDisciplinas;
import br.edu.fateczl.SpringAGIS.persistence.GenericDao;
import br.edu.fateczl.SpringAGIS.persistence.MatriculaDisciplinaDao;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MatriculaController{
	
	@Autowired
	GenericDao gDao;
	
	@Autowired
	MatriculaDisciplinaDao mdDao;
	
	@RequestMapping(name = "matricula", value="/matricula", method = RequestMethod.GET)
	public ModelAndView matriculaGet (@RequestParam Map<String, String> allRequestParam, ModelMap model) {
		LocalDate dataAtual = LocalDate.now();
		boolean intervaloSemestre = validarDataSemestral(dataAtual);
		
		model.addAttribute("intervalo", intervaloSemestre);
		return new ModelAndView("matricula");
	}
	

	@RequestMapping(name = "matricula", value="/matricula", method = RequestMethod.POST)
	public ModelAndView matriculaPost (@RequestParam Map<String, String> allRequestParam, HttpServletRequest request, ModelMap model) {
		Map<String, String[]> parametros = request.getParameterMap();
		String cmd = allRequestParam.get("botao");
		String ra = allRequestParam.get("ra") ;
		Matricula matricula = new Matricula();	
		String[] disciplinasSelecionadas = new String[50];
		
		String saida ="";
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
					erro = "Matricula já foi realizada";
				}
			}
			if(cmd.contains("Confirmar Matricula")) {
				for(String key : parametros.keySet()) {
					if(key.startsWith("disciplinasSelecionadas")) {
						disciplinasSelecionadas = parametros.get(key);
						System.out.println(key);
					}
				}
				for(String str : disciplinasSelecionadas) {
					System.out.println(str);
				}
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
			model.addAttribute("saida", saida);
			model.addAttribute("erro", erro);
			model.addAttribute("disciplinas", matriculaDisciplinas);
			model.addAttribute("aluno", a);
			model.addAttribute("listar", listar);
		}
		
		return new ModelAndView("matricula");
	}
	
	private Matricula ultimaMatricula(String ra) throws ClassNotFoundException, SQLException {
		return mdDao.consultarUltimaMatricula(ra);
	}
	
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


	private List<MatriculaDisciplinas> listarDisciplinas(String ra) throws ClassNotFoundException, SQLException {
		List<MatriculaDisciplinas> md = new ArrayList<>();
		md = mdDao.listarSituacao(ra);
		return md;
	}


	private String inserirMatricula(String[] disciplinasSelecionadas, String ra) throws NumberFormatException, ClassNotFoundException, SQLException {
		String codigoMatricula = mdDao.gerarMatricula(ra);
		System.out.println(codigoMatricula);
		String saida = null;
		for(String str : disciplinasSelecionadas) {
			saida = mdDao.inserirMatricula(ra, Integer.parseInt(codigoMatricula), Integer.parseInt(str));
		}
		return saida;
	}


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
}
