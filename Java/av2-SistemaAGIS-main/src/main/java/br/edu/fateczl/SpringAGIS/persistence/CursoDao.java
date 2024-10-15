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

import br.edu.fateczl.SpringAGIS.model.Curso;

@Repository
public class CursoDao implements IIud<Curso>, ICrud<Curso>{
	private GenericDao gDao;
	
	public CursoDao(GenericDao gDao) {
		this.gDao = gDao;
	}
	
	@Override
	public String iud(String acao, Curso cr) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "CALL sp_iudcurso (?,?,?,?,?,?,?)";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, acao);
		cs.setInt(2, cr.getCodigo());
		cs.setString(3, cr.getNome());
		cs.setInt(4, cr.getCargaHoraria());
		cs.setString(5, cr.getSigla());
		cs.setInt(6, cr.getNotaEnade());
		cs.registerOutParameter(7, Types.VARCHAR);
		cs.execute();
		String saida = cs.getString(7);
		cs.close();
		c.close();
		return saida;
	}

	@Override
	public Curso consultar(Curso cr) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM curso WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, cr.getCodigo());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			cr.setCodigo(rs.getInt("codigo"));
			cr.setNome(rs.getString("nome"));
			cr.setCargaHoraria(rs.getInt("carga_horaria"));
			cr.setSigla(rs.getString("sigla"));
			cr.setNotaEnade(rs.getInt("nota_enade"));
		}
		rs.close();
		ps.close();
		c.close();
		return cr;
	}

	@Override
	public List<Curso> listar() throws SQLException, ClassNotFoundException {
		List<Curso> cursos = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM curso";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Curso cr = new Curso();
			cr.setCodigo(rs.getInt("codigo"));
			cr.setNome(rs.getString("nome"));
			cr.setCargaHoraria(rs.getInt("carga_horaria"));
			cr.setSigla(rs.getString("sigla"));
			cr.setNotaEnade(rs.getInt("nota_enade"));
			cursos.add(cr);
		}
		rs.close();
		ps.close();
		c.close();
		return cursos;
	}
}
