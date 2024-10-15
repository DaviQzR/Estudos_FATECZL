package br.edu.fateczl.SpringAGIS.persistence;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.SpringAGIS.model.Aluno;
import br.edu.fateczl.SpringAGIS.model.Disciplina;

public interface IAula {	
	public List<Aluno> listarAlunos(int conteudo) throws SQLException, ClassNotFoundException;
}
