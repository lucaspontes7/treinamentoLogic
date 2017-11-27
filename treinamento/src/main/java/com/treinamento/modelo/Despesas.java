/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.treinamento.modelo;

import java.sql.Date;

/**
 *
 * @author Lucas
 */
public class Despesas {

    private int id;
    private String descricao;
    private Long valor;
    private Date data;
    private int tipoLancamento;

    public Despesas(int id, String descricao, Long valor, Date data, int tipoLancamento) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.tipoLancamento = tipoLancamento;
    }

    public Despesas() {
    }

    public int getTipoLancamento() {
        return tipoLancamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipoLancamento(int tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

}
