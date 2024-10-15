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

import br.edu.fateczl.SpringAGIS.model.Conteudo;
import br.edu.fateczl.SpringAGIS.model.Disciplina;

@Repository
public class ConteudoDao implements ICrud<Conteudo>, IIud<Conteudo>, IConteudo{
	private GenericDao gDao;
	
	public ConteudoDao(GenericDao gDao){
		this.gDao = gDao;
	}
	
	@Override
	public String iud(String acao, Conteudo c) throws SQLException, ClassNotFoundException {
		Connection con = gDao.getConnection();
		String sql = "CALL iud_conteudo(?,?,?,?,?)";
		CallableStatement cs = con.prepareCall(sql);
		cs.setString(1, acao);
		cs.setInt(2, c.getCodigo());
		cs.setString(3, c.getDescricao());
		cs.setInt(4, c.getCodigoDisciplina());
		cs.registerOutParameter(5, Types.VARCHAR);
		cs.execute();
		String saida = cs.getString(5);
		cs.close();
		con.close();
		return saida;
	}

	@Override
	public Conteudo consultar(Conteudo c) throws SQLException, ClassNotFoundException {
		Connection con = gDao.getConnection();
		String sql = "SELECT * FROM conteudo WHERE codigo = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, c.getCodigo());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			c.setCodigo(rs.getInt("codigo"));
			c.setCodigoDisciplina(rs.getInt("codigo_disciplina"));
			c.setDescricao(rs.getString("descricao"));
		}
		rs.close();
		ps.close();
		con.close();
		return c;
	}

	@Override
	public List<Conteudo> listar() throws SQLException, ClassNotFoundException {
		List<Conteudo> conteudos = new ArrayList<>();
		Connection con = gDao.getConnection();
		String sql = "SELECT * FROM conteudo";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Conteudo c = new Conteudo();
			c.setCodigo(rs.getInt("codigo"));
			c.setCodigoDisciplina(rs.getInt("codigo_disciplina"));
			c.setDescricao(rs.getString("descricao"));
			conteudos.add(c);
		}
		rs.close();
		ps.close();
		con.close();
		return conteudos;
	}

	@Override
	public List<Conteudo> listarPorDisciplina(Disciplina d) throws SQLException, ClassNotFoundException {
		List<Conteudo> conteudos = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM v_conteudo WHERE codigo_disciplina = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, d.getCodigo());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Conteudo con = new Conteudo();
			con.setCodigo(rs.getInt("codigo"));
			con.setCodigoDisciplina(rs.getInt("codigo_disciplina"));
			con.setDescricao(rs.getString("descricao"));
			con.setDataAula(rs.getString("data_aula"));
			conteudos.add(con);
		}
		rs.close();
		ps.close();
		c.close();
		return conteudos;
	}
}
