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
    
    public void removerDespesas(int id){
        String SQL = "DELETE FROM TB_DESPESAS WHERE ID = ?";
        
    }

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

    public List<Despesas> selecionarDespesas() throws SQLException, NamingException {
        List<Despesas> despesasList = new ArrayList<Despesas>();
        try {
            conn = ds.getConnection();
            String SQL = "SELECT * FROM TB_DESPESAS";
            stmt = conn.prepareStatement(SQL);
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
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.close();
            stmt.close();
        }
    }
}
