package com.treinamento.treinamento.rest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/teste")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HelloWorldEndpoint {

    private Connection conn;
    private PreparedStatement stmt;

    @Resource(lookup = "java:jboss/datasources/ExampleDS")
    private DataSource ds;

    @GET
    @Path("hello")
    public Response doGet() {
        return Response.ok("Hello from WildFly Swarm!").build();
    }

    @GET
    @Path("teste")
    public String get() throws NamingException, SQLException {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("jboss/datasources/ExampleDS"); // ExampleDS was been created automatically using the auto-detected h2 driver
        Connection conn = ds.getConnection();
        try {
            return "Howdy using connection: " + conn;
        } finally {
            conn.close();
        }
    }

    @GET
    @Path("criarTabela")
    public boolean criarTabela() throws Exception {
        boolean tabelaCriada = false;
        String SQL = "CREATE TB_TESTE (NOME VARCHAR(15), SOBRENOME VARCHAR(15))";
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.execute();
            tabelaCriada = true;
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.close();
        }

        return tabelaCriada;
    }

}
