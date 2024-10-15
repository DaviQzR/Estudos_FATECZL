package br.edu.fateczl.SpringAGIS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexProfessorController {
	@RequestMapping(name = "indexprofessor", value="/indexprofessor", method = RequestMethod.GET)
	public ModelAndView indexProfessorGet (ModelMap model) {
		return new ModelAndView("indexprofessor");
	}
}
