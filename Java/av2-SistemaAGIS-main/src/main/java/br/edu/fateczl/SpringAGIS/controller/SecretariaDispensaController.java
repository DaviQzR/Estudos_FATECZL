package br.edu.fateczl.SpringAGIS.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
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

@Controller
public class SecretariaDispensaController {
    
    @Autowired
    GenericDao gDao; 
    
    @Autowired
    AlunoDao aDao;
    
    @Autowired
    DisciplinaDao dDao; 
    
    @Autowired
    DispensaDao dispDao;
    
    @RequestMapping(name="secretariadispensa", value="/secretariadispensa", method = RequestMethod.GET)
    public ModelAndView secretariaDispensaGet(@RequestParam Map<String, String> allRequestParam, ModelMap model) {
        String erro = "";
        List<Dispensa> dispensas = new ArrayList<>();
        try {
            dispensas = listarDispensas(dispensas); // Obtém a lista de dispensas
        } catch (SQLException | ClassNotFoundException e) {
            erro = e.getMessage(); 
        } finally {
            model.addAttribute("erro", erro); // Adiciona a mensagem de erro ao modelo
            model.addAttribute("dispensas", dispensas); // Adiciona a lista de dispensas ao modelo
        }
        return new ModelAndView("secretariadispensa"); // Retorna a view "secretariadispensa"
    }
    
   
    private List<Dispensa> listarDispensas(List<Dispensa> dispensas) throws ClassNotFoundException, SQLException {
        dispensas = dispDao.listar(); 
        return dispensas;
    }

  
    @RequestMapping(name="secretariadispensa", value="/secretariadispensa", method = RequestMethod.POST)
    public ModelAndView secretariaDispensaPost(@RequestBody Map<String, String> requestBody, ModelMap model) {
        String ra = requestBody.get("ra"); 
        String disciplina = requestBody.get("disciplina"); 
        String aprovacao = requestBody.get("aprovacao"); 
        System.out.println(ra);
        System.out.println(disciplina);
        System.out.println(aprovacao);
        List<Dispensa> dispensas = new ArrayList<>();
        Aluno a = new Aluno(); 
        Disciplina d = new Disciplina(); 
        String saida = "";
        String erro = "";
        
        try {
            saida = concluirDispensa(ra, disciplina, aprovacao); 
            dispensas = listarDispensas(dispensas); 
        } catch (SQLException | ClassNotFoundException e) {
            erro = e.getMessage(); 
        } finally {
            model.addAttribute("saida", saida); 
            model.addAttribute("erro", erro); 
            model.addAttribute("dispensas", dispensas); 
        }
        
        return new ModelAndView("secretariadispensa"); 
    }

    // Método para concluir a dispensa
    public String concluirDispensa(String ra, String disciplina, String aprovacao) throws SQLException, ClassNotFoundException{
        System.out.println("Concluindo dispensa para RA: " + ra + ", Disciplina: " + disciplina + ", Aprovação: " + aprovacao);
        return dispDao.concluirDispensa(ra, disciplina, aprovacao); // Chama o método no DispensaDao para concluir a dispensa
    }
}