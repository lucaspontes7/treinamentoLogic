package com.treinamento.treinamento.rest;

import com.treinamento.bean.DespesasBean;
import com.treinamento.modelo.Despesas;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("lancamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LancamentoResource {

    @EJB
    DespesasBean despesasBean;

    @GET
    @Path("listar")
    public Response getLancamentos() throws NamingException, SQLException {
        List<Despesas> lista = despesasBean.buscarDespesas();
        return Response.status(Response.Status.ACCEPTED).entity(lista).build();
    }

    @POST
    @Path("listarPorDescricao")
    public Response getLancamentosDescricao(Despesas despesas) throws NamingException, SQLException {
        List<Despesas> lista = despesasBean.buscarDespesasDescricao(despesas.getDescricao());
        return Response.status(Response.Status.ACCEPTED).entity(lista).build();
    }

    @POST
    @Path("listarPorTipoLancamento")
    public Response getLancamentosTipoLancamento(Despesas despesas) throws NamingException, SQLException {
        List<Despesas> lista = despesasBean.buscarDespesasTipoLancamento(despesas.getTipoLancamento());
        return Response.status(Response.Status.ACCEPTED).entity(lista).build();
    }

    @POST
    @Path("listarPorData")
    public Response getLancamentosData(Despesas despesas) throws NamingException, SQLException {
        List<Despesas> lista = despesasBean.buscarDespesasData(despesas.getData());
        return Response.status(Response.Status.ACCEPTED).entity(lista).build();

    }

    @POST
    @Path("inserir")
    public Response inserir(Despesas despesas) throws NamingException, SQLException {
        despesasBean.inserir(despesas);
        return Response.status(Response.Status.ACCEPTED).entity(despesas.getDescricao()).build();

    }

    @PUT
    @Path("atualizar")
    public Response atualizar(Despesas despesas) throws NamingException, SQLException {
        despesasBean.atualizar(despesas);
        return Response.status(Response.Status.ACCEPTED).entity(despesas).build();

    }

    @DELETE
    @Path("remover/{id}")
    public Response remover(@PathParam("id") int id) throws NamingException, SQLException {
        despesasBean.remover(id);
        return Response.status(Response.Status.ACCEPTED).entity(id).build();
    }
}
