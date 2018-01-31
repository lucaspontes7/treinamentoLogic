
import com.treinamento.local.DespesasLocal;
import com.treinamento.modelo.Despesas;
import com.treinamento.treinamento.rest.ArquillianHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.sql.DataSource;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Lucas
 */
@RunWith(Arquillian.class)
public class LancamentoTest extends ArquillianHelper {

    @EJB
    DespesasLocal despesasBean;

    @Resource(lookup = "java:jboss/datasources/TesteDS")
    DataSource ds;

    @Test
    public void buscarDespesasTest() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            inserirDados(stmt);
            List<Despesas> lista = despesasBean.buscarDespesas();
            Assert.assertEquals(101, lista.get(0).getId());
            Assert.assertEquals("Descricao101", lista.get(0).getDescricao());
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }

    }

    @Test
    public void buscarDespesasDataTest() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            inserirDados(stmt);
            List<Despesas> lista = despesasBean.buscarDespesasData("12/10/2018");
            Assert.assertEquals(102, lista.get(0).getId());
            Assert.assertEquals("Descricao102", lista.get(0).getDescricao());
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }

    }

    @Test
    public void buscarDespesasDescricaoTest() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            inserirDados(stmt);
            List<Despesas> lista = despesasBean.buscarDespesasDescricao("Descricao103");
            Assert.assertEquals(103, lista.get(0).getId());
            Assert.assertEquals("Descricao103", lista.get(0).getDescricao());
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }

    }

    @Test
    public void buscarDespesasTipoLancamentoTest() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            inserirDados(stmt);
            List<Despesas> lista = despesasBean.buscarDespesasTipoLancamento(3);
            Assert.assertEquals(101, lista.get(0).getId());
            Assert.assertEquals("Descricao101", lista.get(0).getDescricao());
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }

    }

    @Test
    public void inserirDespesas() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            inserirDados(stmt);

            Despesas despesa = new Despesas();
            despesa.setId(110);
            despesa.setDescricao("Descricao110");
            despesa.setValor("100");
            despesa.setData("10/10/2010");
            despesa.setTipoLancamento(1);

            despesasBean.inserir(despesa);

            List<Despesas> lista = despesasBean.buscarDespesasDescricao("Descricao110");

            Assert.assertNotNull(lista);

        } catch (Exception e) {
            conn.rollback();
            throw e;
        }

    }

    @Test
    public void atualizarDespesas() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            inserirDados(stmt);

            Despesas despesa = new Despesas();
            despesa.setId(101);
            despesa.setDescricao("DescricaoAtualizada");
            despesa.setValor("100");
            despesa.setData("10/10/2010");
            despesa.setTipoLancamento(1);

            despesasBean.atualizar(despesa);

            List<Despesas> lista = despesasBean.buscarDespesasDescricao("DescricaoAtualizada");

            Assert.assertNotNull(lista);
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }

    }

    @Test
    public void removerDespesas() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            inserirDados(stmt);
            despesasBean.remover(101);
            List<Despesas> lista = despesasBean.buscarDespesasDescricao("Descricao101");
            Assert.assertTrue(lista.isEmpty());
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }

    }
}
