/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.treinamento.bean;

import com.treinamento.dao.DespesasDAO;
import com.treinamento.modelo.Despesas;
import java.sql.SQLException;

/**
 *
 * @author Lucas
 */
public class DespesasBean {

    DespesasDAO despesasDAO = new DespesasDAO();

    public void inserir(Despesas despesas) throws SQLException {
        despesasDAO.inserir(despesas);
    }

}
