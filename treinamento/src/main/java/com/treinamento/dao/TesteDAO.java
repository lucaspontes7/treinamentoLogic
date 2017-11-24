/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.treinamento.dao;

import com.treinamento.modelo.Teste;
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
public class TesteDAO {

    @Resource(lookup = "jboss/datasources/ExampleDS")
    private DataSource ds;

    public List<Teste> BuscarTodosDados() throws SQLException {
        List<Teste> testeList = new ArrayList<>();

        Connection conn;
        ResultSet rs;
        PreparedStatement stmt;
        conn = ds.getConnection();

        try {

            stmt = conn.prepareStatement("SELECT ID, NAME FROM TEST");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Teste teste = new Teste();
                teste.setId(rs.getInt("ID"));
                teste.setName(rs.getString("NAME"));
                testeList.add(teste);
                rs.close();
                stmt.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();

        }

        return testeList;
    }

}
