package com.treinamento.modelo;

/**
 *
 * @author Lucas
 */
public enum TipoLancamento {
    ALIMENTACAO(1),
    MORADIA(2),
    EDUCACAO(3),
    TRANSPORTE(4),
    SAUDE(5),
    LAZER(6),
    OUTROS(7);

    public int valorLancamento;

    TipoLancamento(int valor) {
        valorLancamento = valor;
    }

}
