/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.treinamento.dao;

import com.treinamento.modelo.Despesas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 *
 * @author Lucas
 */

public class DespesasDAO {

    @Resource(lookup = "jboss/datasources/ExampleDS")
    private DataSource ds;
    private ResultSet rs;
    private PreparedStatement stmt;
    private Connection conn;

    public boolean inserir(Despesas despesas) throws SQLException {
        String SQL = "INSERT INTO TB_DESPESAS (descricao, valor, data, tipolancamento)"
                + "values (?, ?, ?, ?) ";
        boolean retorno = false;
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, despesas.getDescricao());
            stmt.setDouble(2, despesas.getValor());
            stmt.setDate(3, despesas.getData());
            stmt.setInt(4, despesas.getTipoLancamento());
            stmt.execute();
            retorno = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return retorno;
    }

    public List<Despesas> selecionarTodos() throws SQLException {
        String SQL = "SELECT * FROM TB_DESPESAS";
        List<Despesas> despesasList = new ArrayList<Despesas>();
        Despesas despesas;
        try {
            stmt = conn.prepareStatement(SQL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                despesas = objectFactory(rs);
                despesasList.add(despesas);
                despesas = null;
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        } finally {
            conn.close();
        }
        return despesasList;
    }

    public Despesas objectFactory(ResultSet rs) {
        Despesas despesas = null;
        try {
            despesas = new Despesas(rs.getInt("id"), rs.getString("descricao"),
                    rs.getLong("valor"), rs.getDate("data"), rs.getInt("tipolancamento"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return despesas;
        }
    }
}
