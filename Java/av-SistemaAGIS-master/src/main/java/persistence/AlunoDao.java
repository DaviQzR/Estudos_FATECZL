package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Curso;

public class AlunoDao implements ICrud<Aluno>, IIud<Aluno>{
	private GenericDao gDao;
	
	public AlunoDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public String iud(String acao, Aluno a) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "CALL sp_iudaluno (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, acao);
		cs.setString(2, a.getCpf());
		cs.setString(3, a.getRa());
		cs.setString(4, a.getNome());
		cs.setString(5, a.getNomeSocial());
		cs.setString(6, a.getDataNasc());
		cs.setString(7, a.getTelefoneCelular());
		cs.setString(8, a.getTelefoneResidencial());
		cs.setString(9, a.getEmailPessoal());
		cs.setString(10, a.getEmailCorporativo());
		cs.setString(11, a.getDataSegundoGrau());
		cs.setString(12, a.getInstituicaoSegundoGrau());
		cs.setDouble(13, a.getPontuacaoVestibular());
		cs.setInt(14, a.getPosicaoVestibular());
		cs.setString(15, a.getSemestreGraduacao());
		cs.setString(16, a.getAnoLimite());
		cs.setInt(17, a.getCurso().getCodigo());
		cs.setString(18, a.getTurno());
		cs.registerOutParameter(19, Types.VARCHAR);
		cs.execute();
		String saida = cs.getString(19);
		cs.close();
		c.close();
		return saida;
	}

	@Override
	public Aluno consultar(Aluno a) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM v_alunos WHERE cpf = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, a.getCpf());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			a.setCpf(rs.getString("cpf"));
			a.setRa(rs.getString("ra"));
			a.setNome(rs.getString("nome"));
			a.setNomeSocial(rs.getString("nome_social"));
			a.setDataNasc(rs.getString("data_nasc"));
			a.setTelefoneCelular(rs.getString("telefone_celular"));
			a.setTelefoneResidencial(rs.getString("telefone_residencial"));
			a.setEmailPessoal(rs.getString("email_pessoal"));
			a.setEmailCorporativo(rs.getString("email_corporativo"));
			a.setDataSegundoGrau(rs.getString("data_segundograu"));
			a.setInstituicaoSegundoGrau(rs.getString("instituicao_segundograu"));
			a.setPontuacaoVestibular(rs.getDouble("pontuacao_vestibular"));
			a.setPosicaoVestibular(rs.getInt("posicao_vestibular"));
			a.setAnoIngresso(rs.getString("ano_ingresso"));
			a.setSemestreIngresso(rs.getString("semestre_ingresso"));
			a.setSemestreGraduacao(rs.getString("semestre_graduacao"));
			a.setAnoLimite(rs.getString("ano_limite"));
			Curso cur = new Curso();
			cur.setCodigo(rs.getInt("curso_codigo"));
			a.setCurso(cur);
			a.setTurno(rs.getString("turno"));
		}
		rs.close();
		ps.close();
		c.close();
		return a;
	}

	@Override
	public List<Aluno> listar() throws SQLException, ClassNotFoundException {
		List<Aluno> alunos = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM v_alunos";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Aluno a = new Aluno();
			a.setCpf(rs.getString("cpf"));
			a.setRa(rs.getString("ra"));
			a.setNome(rs.getString("nome"));
			a.setNomeSocial(rs.getString("nome_social"));
			a.setDataNasc(rs.getString("data_nasc"));
			a.setTelefoneCelular(rs.getString("telefone_celular"));
			a.setTelefoneResidencial(rs.getString("telefone_residencial"));
			a.setEmailPessoal(rs.getString("email_pessoal"));
			a.setEmailCorporativo(rs.getString("email_corporativo"));
			a.setDataSegundoGrau(rs.getString("data_segundograu"));
			a.setInstituicaoSegundoGrau(rs.getString("instituicao_segundograu"));
			a.setPontuacaoVestibular(rs.getDouble("pontuacao_vestibular"));
			a.setPosicaoVestibular(rs.getInt("posicao_vestibular"));
			a.setAnoIngresso(rs.getString("ano_ingresso"));
			a.setSemestreIngresso(rs.getString("semestre_ingresso"));
			a.setSemestreGraduacao(rs.getString("semestre_graduacao"));
			a.setAnoLimite(rs.getString("ano_limite"));
			Curso cur = new Curso();
			cur.setCodigo(rs.getInt("curso_codigo"));
			cur.setSigla(rs.getString("curso_sigla"));
			a.setCurso(cur);
			a.setTurno(rs.getString("turno"));
			alunos.add(a);
		}
		rs.close();
		ps.close();
		c.close();
		return alunos;
	}
}
