package com.treinamento.bean;

import com.treinamento.dao.DespesasDAO;
import com.treinamento.modelo.Despesas;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.ws.rs.PathParam;

/**
 *
 * @author Lucas
 */
@Stateless
public class DespesasBean {

    @Inject
    DespesasDAO despesasDAO;

    public List<Despesas> buscarDespesas() throws SQLException {
        return despesasDAO.selecionarTodos();
    }

    public List<Despesas> buscarDespesasData(String data) throws SQLException {
        return despesasDAO.selecionarPorData(data);
    }

    public List<Despesas> buscarDespesasTipoLancamento(int tipoLancamento) throws SQLException {
        return despesasDAO.selecioarPorTipoLancamento(tipoLancamento);
    }

    public List<Despesas> buscarDespesasDescricao(String descricao) throws SQLException {
        return despesasDAO.selecioarPorDescricao(descricao);
    }

    public void inserir(Despesas despesas) throws SQLException {
        despesasDAO.inserirDespesas(despesas);
    }

    public void atualizar(Despesas despesas) throws SQLException {
        despesasDAO.atualizarDespesas(despesas);
    }

    public void remover(int id) throws SQLException, NamingException {
        despesasDAO.removerDespesas(id);
    }

}
