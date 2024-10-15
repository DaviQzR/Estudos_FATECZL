package br.edu.fateczl.SpringAGIS.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import br.edu.fateczl.SpringAGIS.model.Historico;

@Repository
public class HistoricoDao implements IHistorico{
	private GenericDao gDao;
	
	public HistoricoDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public Historico visualizarHistorico(String ra) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM dbo.fn_historico(?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, ra);
		ResultSet rs = ps.executeQuery();
		return null;
	}
}
