package com.treinamento.treinamento.rest;

import com.treinamento.bean.DespesasBean;
import com.treinamento.dao.DespesasDAO;
import com.treinamento.local.DespesasLocal;
import com.treinamento.modelo.Despesas;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.junit.Before;
import org.wildfly.swarm.undertow.WARArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.arquillian.container.test.api.Deployment;

/**
 *
 * @author Lucas
 */
public abstract class ArquillianHelper {

    @Resource(lookup = "java:jboss/datasources/TesteDS")
    DataSource ds;

    @Before
    public void limparBase() throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            String SQL = "DELETE FROM TB_DESPESAS";
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.execute();
        } finally {
            conn.close();
            stmt.close();
        }

    }

    @Deployment()
    public static Archive createDeployment() {
        File[] files = Maven.resolver()
                .loadPomFromFile("pom.xml")
                .importCompileAndRuntimeDependencies()
                .importTestDependencies()
                .resolve().withTransitivity().asFile();

        return ShrinkWrap.create(WARArchive.class, "test.war")
                .addAsLibrary(files[0])
                .addPackage(Despesas.class.getPackage())
                .addPackage(DespesasLocal.class.getPackage())
                .addPackage(DespesasBean.class.getPackage())
                .addPackage(DespesasDAO.class.getPackage())
                .addPackage(LancamentoResource.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebResource("project-defaults.yml");
    }

    protected void inserirDados(Statement stmt) throws SQLException {
        stmt.execute("INSERT INTO TB_DESPESAS (ID, DESCRICAO, VALOR, DATA, TIPO_LANCAMENTO) "
                + "VALUES (101, 'Descricao101', '10', '11/10/2018', '3')");
        stmt.execute("INSERT INTO TB_DESPESAS (ID, DESCRICAO, VALOR, DATA, TIPO_LANCAMENTO) "
                + "VALUES (102, 'Descricao102', '20', '12/10/2018', '2')");
        stmt.execute("INSERT INTO TB_DESPESAS (ID, DESCRICAO, VALOR, DATA, TIPO_LANCAMENTO) "
                + "VALUES (103, 'Descricao103', '30', '13/10/2018', '1')");
        stmt.execute("INSERT INTO TB_DESPESAS (ID, DESCRICAO, VALOR, DATA, TIPO_LANCAMENTO) "
                + "VALUES (104, 'Descricao104', '40', '14/10/2018', '1')");
        stmt.execute("INSERT INTO TB_DESPESAS (ID, DESCRICAO, VALOR, DATA, TIPO_LANCAMENTO) "
                + "VALUES (105, 'Descricao105', '50', '15/10/2018', '1')");
    }
}
