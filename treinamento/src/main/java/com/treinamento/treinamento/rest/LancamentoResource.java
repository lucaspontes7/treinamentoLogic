package com.treinamento.treinamento.rest;

import com.treinamento.bean.DespesasBean;
import com.treinamento.modelo.Despesas;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("lancamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LancamentoResource {

    @EJB
    DespesasBean despesasBean;

    /**
     * Metodo responsavel por retornar uma lista de despesas
     *
     * @return List<>
     * @throws NamingException
     * @throws SQLException
     */
    @GET
    @Path("listar")
    public List<Despesas> getLancamentos() throws NamingException, SQLException {
        List<Despesas> lista = despesasBean.buscarDespesas();
        return lista;
    }

    /**
     * Metodo responsavel por inserir os dados
     *
     * @param despesas
     * @return Response
     * @throws NamingException
     * @throws SQLException
     */
    @POST
    @Path("inserir")
    public Response inserir(Despesas despesas) throws NamingException, SQLException {
        despesasBean.inserir(despesas);
        return Response.status(Response.Status.ACCEPTED).entity(despesas).build();
    }
}
