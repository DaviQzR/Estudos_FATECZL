package br.edu.fateczl.SpringAGIS.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.fateczl.SpringAGIS.model.Disciplina;
import br.edu.fateczl.SpringAGIS.model.Professor;

@Repository
public class DisciplinaDao implements IDisciplina{
	private GenericDao gDao;
	
	public DisciplinaDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public List<Disciplina> listarDisciplinas() throws SQLException, ClassNotFoundException {
		List<Disciplina> disciplinas = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM disciplina";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Disciplina d = new Disciplina();
			d.setCodigo(rs.getInt("codigo"));
			d.setNome(rs.getString("nome"));
			d.setDiaSemana(rs.getString("dia"));
			d.setHorarioInicio(rs.getString("horario_inicio"));
			d.setHorarioFim(rs.getString("horario_fim"));
			d.setCursoCodigo(rs.getInt("curso_codigo"));
			d.setQtdAulas(rs.getInt("qtd_aulas"));
			Professor p = new Professor();
			p.setCodigo(rs.getInt("professor_codigo"));
			d.setProfessor(p);
			disciplinas.add(d);
		}
		rs.close();
		ps.close();
		c.close();
		return disciplinas; 
	}

	@Override
	public List<Disciplina> listarDisciplinasPorCurso(int curso_codigo) throws SQLException, ClassNotFoundException {
		List<Disciplina> disciplinas = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM disciplina WHERE curso_codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, curso_codigo);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Disciplina d = new Disciplina();
			d.setCodigo(rs.getInt("codigo"));
			d.setNome(rs.getString("nome"));
			d.setDiaSemana(rs.getString("dia"));
			d.setHorarioInicio(rs.getString("horario_inicio"));
			d.setHorarioFim(rs.getString("horario_final"));
			d.setCursoCodigo(rs.getInt("curso_codigo"));
			d.setQtdAulas(rs.getInt("qtd_aulas"));
			Professor p = new Professor();
			p.setCodigo(rs.getInt("codigo_professor"));
			d.setProfessor(p);
			disciplinas.add(d);
		}
		rs.close();
		ps.close();
		c.close();
		return disciplinas; 
	}

	@Override
	public List<Disciplina> listarDisciplinasPorAluno(String ra) throws SQLException, ClassNotFoundException {
		List<Disciplina> disciplinas = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM dbo.fn_listarultimamatricula(?) WHERE situacao = 'Não cursado'";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, ra);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Disciplina d = new Disciplina();
			d.setCodigo(rs.getInt("codigo"));
			d.setNome(rs.getString("nome"));
			d.setDiaSemana(rs.getString("dia"));
			d.setHorarioInicio(rs.getString("horario_inicio"));
			d.setHorarioFim(rs.getString("horario_fim"));
			d.setCursoCodigo(rs.getInt("curso_codigo"));
			d.setQtdAulas(rs.getInt("qtd_aulas"));
			disciplinas.add(d);
		}
		rs.close();
		ps.close();
		c.close();
		return disciplinas;
	}
	
	public Disciplina consultar(Disciplina d) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM disciplina WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, d.getCodigo());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			d.setCodigo(rs.getInt("codigo"));
			d.setNome(rs.getString("nome"));
			d.setDiaSemana(rs.getString("dia"));
			d.setHorarioInicio(rs.getString("horario_inicio"));
			d.setHorarioFim(rs.getString("horario_fim"));
			d.setCursoCodigo(rs.getInt("curso_codigo"));
			d.setQtdAulas(rs.getInt("qtd_aulas"));
			Professor p = new Professor();
			p.setCodigo(rs.getInt("professor_codigo"));
			d.setProfessor(p);
		}
		rs.close();
		ps.close();
		c.close();
		return d;
	}

}