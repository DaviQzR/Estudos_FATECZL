package br.edu.fateczl.SpringAGIS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexSecretariaController {
	@RequestMapping(name = "indexsecretaria", value="/indexsecretaria", method = RequestMethod.GET)
	public ModelAndView indexSecretariaGet (ModelMap model) {
		return new ModelAndView("indexsecretaria");
	}
}
