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

import br.edu.fateczl.SpringAGIS.model.Disciplina;
import br.edu.fateczl.SpringAGIS.model.Matricula;
import br.edu.fateczl.SpringAGIS.model.MatriculaDisciplinas;
import br.edu.fateczl.SpringAGIS.model.Professor;

@Repository
public class MatriculaDisciplinaDao implements IMatricula{
	private GenericDao gDao;
	
	public MatriculaDisciplinaDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public String inserirMatricula(String ra, int codigoMatricula, int codigoDisciplina) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "CALL sp_inserirmatricula(?,?,?,?)";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, ra);
		cs.setInt(2, codigoMatricula);
		cs.setInt(3, codigoDisciplina);
		cs.registerOutParameter(4, Types.VARCHAR);
		cs.execute();
		String saida = cs.getString(4);
		cs.close();
		c.close();
		return saida;
	}

	@Override
	public List<MatriculaDisciplinas> listarSituacao(String alunoRa)
			throws SQLException, ClassNotFoundException {
		List<MatriculaDisciplinas> ms = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM dbo.fn_listarultimamatricula(?) ORDER BY situacao, nome ASC";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, alunoRa);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt("codigo_matricula"));
			MatriculaDisciplinas m = new MatriculaDisciplinas();
			Disciplina d = new Disciplina();
			d.setCodigo(rs.getInt("codigo"));
			d.setNome(rs.getString("nome"));
			d.setQtdAulas(rs.getInt("qtd_aulas"));
			d.setHorarioInicio(rs.getString("horario_inicio"));
			d.setHorarioFim(rs.getString("horario_fim"));
			d.setDiaSemana(rs.getString("dia"));
			d.setCursoCodigo(rs.getInt("curso_codigo"));
			Professor p = new Professor();
			p.setCodigo(rs.getInt("codigo_professor"));
			p.setNome(rs.getString("nome_professor"));
			d.setProfessor(p);
			m.setDisciplina(d);
			m.setCodigoMatricula(rs.getInt("codigo_matricula"));
			m.setSituacao(rs.getString("situacao"));
			m.setQtdFaltas(rs.getInt("qtd_faltas"));
			m.setNotaFinal(rs.getString("nota_final"));
			ms.add(m);
		}
		
		ps.close();
		c.close();
		return ms;
	}

	@Override
	public String gerarMatricula(String alunoRa) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "CALL sp_gerarmatricula (?,?)";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, alunoRa);
		cs.registerOutParameter(2, Types.INTEGER);
		cs.execute();
		String saida = Integer.toString(cs.getInt(2));
		cs.close();
		c.close();
		return saida;
	}

	@Override
	public Matricula consultarUltimaMatricula(String alunoRa) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT TOP 1 codigo, aluno_ra, data_matricula FROM matricula WHERE aluno_ra = ? ORDER BY codigo DESC";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, alunoRa);
		ResultSet rs = ps.executeQuery();
		Matricula m = new Matricula();
		if(rs.next()) {
			m.setCodigo(rs.getInt("codigo"));
			m.setDataMatricula(rs.getString("data_matricula"));
			m.setAlunoRa(rs.getString("aluno_ra"));
		}
		return m;
	}
}
