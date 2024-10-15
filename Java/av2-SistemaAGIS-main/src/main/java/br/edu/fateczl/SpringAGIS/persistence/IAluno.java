package br.edu.fateczl.SpringAGIS.persistence;

import java.sql.SQLException;

import br.edu.fateczl.SpringAGIS.model.Aluno;

public interface IAluno {
	public Aluno consultarAlunoRa(Aluno a) throws SQLException, ClassNotFoundException;
}
