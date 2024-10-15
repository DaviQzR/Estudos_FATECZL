package br.edu.fateczl.SpringAGIS.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.fateczl.SpringAGIS.model.Professor;

@Repository
public class ProfessorDao implements ICrud<Professor>, IIud<Professor>{
	private GenericDao gDao;
	
	public ProfessorDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public String iud(String acao, Professor p) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "CALL iud_professor(?,?,?,?,?)";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, acao);
		cs.setInt(2, p.getCodigo());
		cs.setString(3, p.getNome());
		cs.setString(4, p.getTitulacao());
		cs.registerOutParameter(5, Types.VARCHAR);
		cs.execute();
		String saida = cs.getString(5);
		cs.close();
		c.close();
		return saida;
	}

	@Override
	public Professor consultar(Professor p) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM v_professor WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, p.getCodigo());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			p.setCodigo(rs.getInt("codigo"));
			p.setNome(rs.getString("nome"));
			p.setTitulacao(rs.getString("titulacao"));
		}
		rs.close();
		ps.close();
		c.close();
		return p;
	}

	@Override
	public List<Professor> listar() throws SQLException, ClassNotFoundException {
		List<Professor> professores = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM v_professor";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Professor p = new Professor();
			p.setCodigo(rs.getInt("codigo"));
			p.setNome(rs.getString("nome"));
			p.setTitulacao(rs.getString("titulacao"));
			professores.add(p);
		}
		rs.close();
		ps.close();
		c.close();
		return professores;
	}
}
