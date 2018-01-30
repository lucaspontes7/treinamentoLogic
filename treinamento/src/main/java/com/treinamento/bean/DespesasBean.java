package com.treinamento.bean;

import com.treinamento.dao.DespesasDAO;
import com.treinamento.local.DespesasLocal;
import com.treinamento.modelo.Despesas;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Lucas
 */
@Stateless
public class DespesasBean implements DespesasLocal {

    @Inject
    DespesasDAO despesasDAO;

    @Override
    public List<Despesas> buscarDespesas() throws SQLException {
        return despesasDAO.selecionarTodos();

    }

    @Override
    public List<Despesas> buscarDespesasData(String data) throws SQLException {
        return despesasDAO.selecionarPorData(data);
    }

    @Override
    public List<Despesas> buscarDespesasTipoLancamento(int tipoLancamento) throws SQLException {
        return despesasDAO.selecioarPorTipoLancamento(tipoLancamento);
    }

    @Override
    public List<Despesas> buscarDespesasDescricao(String descricao) throws SQLException {
        return despesasDAO.selecioarPorDescricao(descricao);
    }

    @Override
    public void inserir(Despesas despesas) throws SQLException {
        despesasDAO.inserirDespesas(despesas);
    }

    @Override
    public void atualizar(Despesas despesas) throws SQLException {
        despesasDAO.atualizarDespesas(despesas);
    }

    @Override
    public void remover(int id) throws SQLException {
        despesasDAO.removerDespesas(id);
    }

}
