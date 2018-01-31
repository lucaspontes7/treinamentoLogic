
import com.treinamento.bean.DespesasBean;
import com.treinamento.dao.DespesasDAO;
import com.treinamento.local.DespesasLocal;
import com.treinamento.modelo.Despesas;
import com.treinamento.treinamento.rest.LancamentoResource;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.undertow.WARArchive;

/**
 *
 * @author Lucas
 */
@RunWith(Arquillian.class)
public class LancamentoTest {

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
    @EJB
    DespesasLocal despesasBean;

    @Test
    public void buscarDespesasTest() throws SQLException {
        List<Despesas> lista = despesasBean.buscarDespesas();
        Assert.assertNotNull(lista);
        Assert.assertEquals(2, lista.get(0).getId());
        Assert.assertEquals("TESTE", lista.get(0).getDescricao());
        Assert.assertEquals("45", lista.get(0).getValor());
        Assert.assertEquals("26/10/1994", lista.get(0).getData());
        Assert.assertEquals(2, lista.get(0).getTipoLancamento());

    }

    @Test
    public void buscarDespesasDataTest() throws SQLException {
        List<Despesas> lista = despesasBean.buscarDespesasData("26/10/1994");
        Assert.assertNotNull(lista);
        Assert.assertEquals(2, lista.get(0).getId());
        Assert.assertEquals("Teste", lista.get(0).getDescricao());
        Assert.assertEquals("45", lista.get(0).getValor());
        Assert.assertEquals("26/10/1994", lista.get(0).getData());
        Assert.assertEquals(1, lista.get(0).getTipoLancamento());

    }

    @Test
    public void buscarDespesasDescricaoTest() throws SQLException {
        List<Despesas> lista = despesasBean.buscarDespesasDescricao("Teste3");
        Assert.assertNotNull(lista);
        Assert.assertEquals("Teste3", lista.get(0).getDescricao());
        Assert.assertEquals("45", lista.get(0).getValor());
        Assert.assertEquals("26/10/2015", lista.get(0).getData());
        Assert.assertEquals(2, lista.get(0).getTipoLancamento());
    }

    @Test
    public void buscarDespesasTipoLancamentoTest() throws SQLException {
        List<Despesas> lista = despesasBean.buscarDespesasTipoLancamento(1);
        Assert.assertNotNull(lista);
        Assert.assertEquals("112Teste2", lista.get(0).getDescricao());
        Assert.assertEquals("45", lista.get(0).getValor());
        Assert.assertEquals("26/10/2018", lista.get(0).getData());
        Assert.assertEquals(1, lista.get(0).getTipoLancamento());

    }

    @Test
    public void inserirDespesas() throws SQLException {
        Despesas despesa = new Despesas();
        despesa.setDescricao("DespesaTeste");
        despesa.setData("20/01/2014");
        despesa.setValor("90");
        despesa.setTipoLancamento(3);
        despesasBean.inserir(despesa);
        List<Despesas> lista = despesasBean.buscarDespesasDescricao("DespesaTeste");
        Assert.assertNotNull(lista);
    }

    @Test
    public void atualizarDespesas() throws SQLException {
        Despesas despesa = new Despesas();
        despesa.setId(1);
        despesa.setDescricao("DespesaLucas");
        despesa.setData("20/01/2014");
        despesa.setValor("90");
        despesa.setTipoLancamento(3);
        despesasBean.inserir(despesa);
        List<Despesas> lista = despesasBean.buscarDespesasDescricao("DespesaLucas");
        Assert.assertNotNull(lista);
    }

    @Test
    public void removerDespesas() throws SQLException {
        Despesas despesa = new Despesas();
        despesa.setId(10);
        despesa.setDescricao("DespesaTesteExcluir");
        despesa.setData("20/01/2014");
        despesa.setValor("998");
        despesa.setTipoLancamento(3);
        despesasBean.inserir(despesa);
        despesasBean.remover(10);
        List<Despesas> lista = despesasBean.buscarDespesasDescricao("DespesaTesteExcluir");
        Assert.assertTrue(lista.isEmpty());
    }
}
