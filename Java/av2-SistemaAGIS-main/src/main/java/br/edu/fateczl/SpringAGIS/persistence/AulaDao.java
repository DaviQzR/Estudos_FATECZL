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

import br.edu.fateczl.SpringAGIS.model.Aluno;

@Repository
public class AulaDao implements IAula{
	private GenericDao gDao;
	
	public AulaDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public List<Aluno> listarAlunos(int disciplina) throws SQLException, ClassNotFoundException {
		List<Aluno> alunos = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM v_aluno_chamada WHERE codigo_disciplina = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, disciplina);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Aluno a = new Aluno();
			a.setRa(rs.getString("ra"));
			a.setNome(rs.getString("nome"));
			alunos.add(a);
		}
		rs.close();
		ps.close();
		c.close();
		return alunos;
	}
	
	public String inserirAula(int matricula, int disciplina, int presenca, String dataAula) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "CALL sp_inseriraula(?,?,?,?,?)";
		CallableStatement cs = c.prepareCall(sql);
		cs.setInt(1, matricula);
		cs.setInt(2, disciplina);
		cs.setInt(3, presenca);
		cs.setString(4, dataAula);
		cs.registerOutParameter(5, Types.VARCHAR);
		cs.execute();
		String saida = cs.getString(5);
		cs.close();
		c.close();
		return saida;
	}
	
	
	public boolean verificarAulaExistente(int disciplina, String dataAula) throws SQLException, ClassNotFoundException{
		Connection c = gDao.getConnection();
		String sql = "CALL sp_verificaraula(?,?,?)";
		CallableStatement cs = c.prepareCall(sql);
		cs.setInt(1, disciplina);
		cs.setString(2, dataAula);
		cs.registerOutParameter(3, Types.BIT);
		cs.execute();
		boolean saida = cs.getBoolean(3);
		cs.close();
		c.close();
		return saida;
	}
}
