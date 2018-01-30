package com.treinamento.local;

import com.treinamento.modelo.Despesas;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Lucas
 */
@Local
public interface DespesasLocal {

    List<Despesas> buscarDespesas() throws SQLException;

    List<Despesas> buscarDespesasData(String data) throws SQLException;

    List<Despesas> buscarDespesasTipoLancamento(int tipoLancamento) throws SQLException;

    List<Despesas> buscarDespesasDescricao(String descricao) throws SQLException;

    void inserir(Despesas despesas) throws SQLException;

    public void atualizar(Despesas despesas) throws SQLException;

    public void remover(int id) throws SQLException;
}
