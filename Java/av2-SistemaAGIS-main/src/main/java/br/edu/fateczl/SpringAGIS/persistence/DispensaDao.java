package br.edu.fateczl.SpringAGIS.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.edu.fateczl.SpringAGIS.model.Aluno;
import br.edu.fateczl.SpringAGIS.model.Disciplina;
import br.edu.fateczl.SpringAGIS.model.Dispensa;

@Repository
public class DispensaDao {
    private GenericDao gDao;

    @Autowired
    public DispensaDao(GenericDao gDao) {
        this.gDao = gDao;
    }

    public String enviarSolicitacaoDispensa(Aluno a, Disciplina d, String motivo) throws SQLException, ClassNotFoundException {
        Connection c = gDao.getConnection();
        String sql = "CALL sp_alunodispensa(?,?,?,?)";
        CallableStatement cs = c.prepareCall(sql);
        cs.setString(1, a.getRa());
        cs.setInt(2, d.getCodigo());
        cs.setString(3, motivo);
        cs.registerOutParameter(4, Types.VARCHAR);
        cs.execute();
        String saida = cs.getString(4);
        cs.close();
        c.close();
        return saida;
    }

    public List<Dispensa> atualizarLista() throws SQLException, ClassNotFoundException {
        List<Dispensa> dispensas = new ArrayList<>();
        Connection c = gDao.getConnection();
        String sql = "SELECT * FROM v_dispensas";
        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Dispensa d = new Dispensa();
            Aluno a = new Aluno();
            a.setRa(rs.getString("aluno_ra"));
            a.setNome(rs.getString("aluno_nome"));
            Disciplina di = new Disciplina();
            di.setCodigo(rs.getInt("codigo_disciplina"));
            di.setNome(rs.getString("disciplina_nome"));
            d.setNomeCurso(rs.getString("curso_nome"));
            d.setAluno(a);
            d.setDisciplina(di);
            d.setMotivo(rs.getString("motivo"));
            dispensas.add(d);
        }
        rs.close();
        ps.close();
        c.close();
        return dispensas;
    }

    public String concluirDispensa(String ra, String disciplina, String aprovacao) throws SQLException, ClassNotFoundException {
        Connection c = gDao.getConnection();
        String sql = "CALL sp_concluirdispensa(?,?,?,?)";
        CallableStatement cs = c.prepareCall(sql);
        cs.setString(1, ra);
        cs.setInt(2, Integer.parseInt(disciplina));
        cs.setString(3, aprovacao);
        cs.registerOutParameter(4, Types.VARCHAR);
        cs.execute();
        String saida = cs.getString(4);
        cs.close();
        c.close();
        return saida;
    }

    public List<Dispensa> listar() throws SQLException, ClassNotFoundException {
        List<Dispensa> dispensas = new ArrayList<>();
        Connection c = gDao.getConnection();
        String sql = "SELECT * FROM v_dispensas WHERE estado = 'Em andamento'";
        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Dispensa d = new Dispensa();
            Aluno a = new Aluno();
            a.setRa(rs.getString("aluno_ra"));
            a.setNome(rs.getString("aluno_nome"));
            Disciplina di = new Disciplina();
            di.setCodigo(rs.getInt("codigo_disciplina"));
            di.setNome(rs.getString("disciplina_nome"));
            d.setNomeCurso(rs.getString("curso_nome"));
            d.setAluno(a);
            d.setDisciplina(di);
            d.setMotivo(rs.getString("motivo"));
            dispensas.add(d);
        }
        rs.close();
        ps.close();
        c.close();
        return dispensas;
    }

	public List<Dispensa> listarPorAluno(String ra) throws SQLException, ClassNotFoundException{
        List<Dispensa> dispensas = new ArrayList<>();
        Connection c = gDao.getConnection();
        String sql = "SELECT * FROM v_dispensas WHERE aluno_ra = ?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, ra);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Dispensa d = new Dispensa();
            Aluno a = new Aluno();
            a.setRa(rs.getString("aluno_ra"));
            a.setNome(rs.getString("aluno_nome"));
            Disciplina di = new Disciplina();
            di.setCodigo(rs.getInt("codigo_disciplina"));
            di.setNome(rs.getString("disciplina_nome"));
            d.setNomeCurso(rs.getString("curso_nome"));
            d.setAluno(a);
            d.setDisciplina(di);
            d.setMotivo(rs.getString("motivo"));
            d.setEstado(rs.getString("estado"));
            dispensas.add(d);
        }
        rs.close();
        ps.close();
        c.close();
        return dispensas;
	}
}
