
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

    }

    @Test
    public void buscarDespesasDataTest() throws SQLException {
        List<Despesas> lista = despesasBean.buscarDespesasData("");
        Assert.assertNotNull(lista);

    }

    @Test
    public void buscarDespesasDescricaoTest() throws SQLException {
        List<Despesas> lista = despesasBean.buscarDespesasDescricao("");
        Assert.assertNotNull(lista);

    }

    @Test
    public void buscarDespesasTipoLancamentoTest() throws SQLException {
        List<Despesas> lista = despesasBean.buscarDespesasTipoLancamento(1);
        Assert.assertNotNull(lista);

    }
}
