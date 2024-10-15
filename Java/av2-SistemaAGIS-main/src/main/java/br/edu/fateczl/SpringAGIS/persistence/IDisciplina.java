package br.edu.fateczl.SpringAGIS.persistence;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.SpringAGIS.model.Disciplina;
import br.edu.fateczl.SpringAGIS.model.Professor;

public interface IDisciplina {
	public List<Disciplina> listarDisciplinas() throws SQLException, ClassNotFoundException;
	public List<Disciplina> listarDisciplinasPorCurso(int curso_codigo) throws SQLException, ClassNotFoundException;
	public List<Disciplina> listarDisciplinasPorAluno(String ra) throws SQLException, ClassNotFoundException;

}
