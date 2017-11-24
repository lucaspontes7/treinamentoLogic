package com.treinamento.treinamento.rest;

import com.treinamento.dao.TesteDAO;
import com.treinamento.modelo.Teste;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.inject.Inject;
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

    @GET
    @Path("hello")
    public Response doGet() {
        return Response.ok("Hello from WildFly Swarm!").build();
    }

    @Resource(lookup = "jboss/datasources/ExampleDS")
    private DataSource ds;

    @GET
    @Path("buscar")
    public Connection dados() throws SQLException {
       
        Connection conn;
       
        conn = ds.getConnection();

        
        return conn;
    }

}
