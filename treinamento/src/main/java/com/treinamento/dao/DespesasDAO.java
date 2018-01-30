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
        String SQL = "INSERT INTO TB_DESPESAS (descricao, valor, data, tipo_lancamento)"
                + "values (?, ?, ?, ?) ";
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, despesas.getDescricao());
            stmt.setString(2, despesas.getValor());
            stmt.setString(3, despesas.getData());
            stmt.setInt(4, despesas.getTipoLancamento());
            stmt.execute();
        } finally {
            conn.close();
            stmt.close();
        }
    }

    public void removerDespesas(int id) throws SQLException {
        try {
            String SQL = "DELETE FROM TB_DESPESAS WHERE ID = ?";
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, id);
            stmt.execute();
        } finally {
            conn.close();
            stmt.close();
        }
    }

    public void atualizarDespesas(Despesas despesas) throws SQLException {
        try {
            conn = ds.getConnection();
            String SQL = "UPDATE TB_DESPESAS SET DESCRICAO = ?, VALOR = ?, "
                    + "DATA = ?, TIPO_LANCAMENTO = ? WHERE ID = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, despesas.getDescricao());
            stmt.setString(2, despesas.getValor());
            stmt.setString(3, despesas.getData());
            stmt.setInt(4, despesas.getTipoLancamento());
            stmt.setInt(5, despesas.getId());
            stmt.execute();
        } finally {
            conn.close();
            stmt.close();
        }
    }

    public List<Despesas> selecioarPorDescricao(String descricao) throws SQLException {
        return selecionarDespesas(0, null, descricao);
    }

    public List<Despesas> selecioarPorTipoLancamento(int tipoLancamento) throws SQLException {
        return selecionarDespesas(tipoLancamento, null, null);
    }

    public List<Despesas> selecionarPorData(String data) throws SQLException {
        return selecionarDespesas(0, data, null);
    }

    public List<Despesas> selecionarTodos() throws SQLException {
        return selecionarDespesas(0, null, null);
    }

    private List<Despesas> selecionarDespesas(int tipoLancamento, String data, String descricao) throws SQLException {
        List<Despesas> despesasList = new ArrayList<>();
        try {
            conn = ds.getConnection();
            String SQL = "SELECT * FROM TB_DESPESAS ";
            if (tipoLancamento != 0) {
                SQL += "WHERE TIPO_LANCAMENTO = ?";
                stmt = conn.prepareStatement(SQL);
                stmt.setInt(1, tipoLancamento);
            } else if (data != null) {
                SQL += " WHERE DATA = ?";
                stmt = conn.prepareStatement(SQL);
                stmt.setString(1, data);
            } else if (descricao != null) {
                SQL += " WHERE DESCRICAO LIKE ?";
                stmt = conn.prepareStatement(SQL);
                stmt.setString(1, "%" + descricao + "%");
            } else {
                stmt = conn.prepareStatement(SQL);
            }

            rs = stmt.executeQuery();
            while (rs.next()) {
                Despesas despesas = new Despesas();
                despesas.setId(rs.getInt("id"));
                despesas.setDescricao(rs.getString("descricao"));
                despesas.setValor(rs.getString("valor"));
                despesas.setData(rs.getString("data"));
                despesas.setTipoLancamento(rs.getInt("tipo_lancamento"));
                despesasList.add(despesas);
            }
            return despesasList;
        } finally {
            conn.close();
            stmt.close();
        }
    }

}
