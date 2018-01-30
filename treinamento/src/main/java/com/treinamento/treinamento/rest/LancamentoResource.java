package com.treinamento.treinamento.rest;

import com.treinamento.local.DespesasLocal;
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
    DespesasLocal despesasBean;

    @GET
    @Path("listar")
    public Response getLancamentos() throws SQLException {
        List<Despesas> lista = despesasBean.buscarDespesas();
        try {
            if (!lista.isEmpty()) {
                return Response.status(Response.Status.ACCEPTED).entity(lista).build();
            } else {
                return Response.noContent().build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((e.getMessage())).build();
        }
    }

    @POST
    @Path("listarPorDescricao")
    public Response getLancamentosDescricao(Despesas despesas) throws SQLException {
        List<Despesas> lista = despesasBean.buscarDespesasDescricao(despesas.getDescricao());
        try {
            if (!lista.isEmpty()) {
                return Response.status(Response.Status.ACCEPTED).entity(lista).build();
            } else {
                return Response.noContent().build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((e.getMessage())).build();
        }
    }

    @POST
    @Path("listarPorTipoLancamento")
    public Response getLancamentosTipoLancamento(Despesas despesas) throws SQLException {
        List<Despesas> lista = despesasBean.buscarDespesasTipoLancamento(despesas.getTipoLancamento());
        try {
            if (!lista.isEmpty()) {
                return Response.status(Response.Status.ACCEPTED).entity(lista).build();
            } else {
                return Response.noContent().build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((e.getMessage())).build();
        }
    }

    @POST
    @Path("listarPorData")
    public Response getLancamentosData(Despesas despesas) throws SQLException {
        List<Despesas> lista = despesasBean.buscarDespesasData(despesas.getData());
        try {
            if (!lista.isEmpty()) {
                return Response.status(Response.Status.ACCEPTED).entity(lista).build();
            } else {
                return Response.noContent().build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((e.getMessage())).build();
        }

    }

    @POST
    @Path("inserir")
    public Response inserir(Despesas despesas) throws SQLException {
        try {
            despesasBean.inserir(despesas);
            return Response.status(Response.Status.ACCEPTED).entity(despesas.getDescricao()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((e.getMessage())).build();
        }

    }

    @PUT
    @Path("atualizar")
    public Response atualizar(Despesas despesas) throws SQLException {
        try {
            despesasBean.atualizar(despesas);
            return Response.status(Response.Status.ACCEPTED).entity(despesas).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((e.getMessage())).build();
        }

    }

    @DELETE
    @Path("remover/{id}")
    public Response remover(@PathParam("id") int id) throws NamingException, SQLException {
        try {
            despesasBean.remover(id);
            return Response.status(Response.Status.ACCEPTED).entity(id).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((e.getMessage())).build();
        }
    }
}
