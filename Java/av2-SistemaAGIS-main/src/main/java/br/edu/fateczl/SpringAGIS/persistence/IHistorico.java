package br.edu.fateczl.SpringAGIS.persistence;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.SpringAGIS.model.Disciplina;
import br.edu.fateczl.SpringAGIS.model.Historico;

public interface IHistorico {
	public Historico visualizarHistorico(String ra) throws SQLException, ClassNotFoundException;
}
