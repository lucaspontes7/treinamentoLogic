package com.treinamento.bean;

import com.treinamento.dao.DespesasDAO;
import com.treinamento.modelo.Despesas;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.NamingException;

/**
 *
 * @author Lucas
 */
@Stateless
public class DespesasBean {

    @Inject
    DespesasDAO despesasDAO;

    public List<Despesas> buscarDespesas() throws SQLException, NamingException {
        return despesasDAO.selecionarTodos();
    }

    public void inserir(Despesas despesas) throws SQLException {
        despesasDAO.inserirDespesas(despesas);
    }

}
