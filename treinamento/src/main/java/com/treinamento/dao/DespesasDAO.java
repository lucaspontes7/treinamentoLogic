package com.treinamento.dao;

import com.treinamento.modelo.Despesas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Lucas
 */
@Stateless
public class DespesasDAO {

    @Resource(lookup = "java:jboss/datasources/TesteDS")
    DataSource ds;
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public void inserirDespesas(Despesas despesas) throws SQLException {
        String SQL = "INSERT INTO TB_DESPESAS (descricao, valor, data, tipolancamento)"
                + "values (?, ?, ?, ?) ";
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, despesas.getDescricao());
            stmt.setString(2, despesas.getValor());
            stmt.setString(3, despesas.getData());
            stmt.setInt(4, despesas.getTipoLancamento());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmt.close();
        }
    }

    public List<Despesas> selecionarTodos() throws SQLException, NamingException {
        List<Despesas> despesasList = new ArrayList<Despesas>();
        Despesas despesas = new Despesas();
        try {
            conn = ds.getConnection();
            String SQL = "SELECT * FROM TB_DESPESAS";
            stmt = conn.prepareStatement(SQL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                despesas = objectFactory(rs);
                despesasList.add(despesas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            stmt.close();

        }
        return despesasList;
    }

    public Despesas objectFactory(ResultSet rs) {
        Despesas despesas = null;
        try {
            despesas = new Despesas(rs.getInt("id"), rs.getString("descricao"),
                    rs.getString("valor"), rs.getString("data"), rs.getInt("tipolancamento"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return despesas;
        }
    }
}
