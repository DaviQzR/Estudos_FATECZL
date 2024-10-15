package br.edu.fateczl.SpringAGIS.persistence;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.SpringAGIS.model.Conteudo;
import br.edu.fateczl.SpringAGIS.model.Disciplina;

public interface IConteudo {
	public List<Conteudo> listarPorDisciplina(Disciplina d) throws SQLException, ClassNotFoundException;
}
