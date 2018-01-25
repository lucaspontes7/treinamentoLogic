
import com.treinamento.bean.DespesasBean;
import com.treinamento.modelo.Despesas;
import java.sql.SQLException;
import javax.ejb.EJB;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lucas
 */
@RunWith(Arquillian.class)
public class LancamentoTest {

    @EJB
    DespesasBean despesasBean;

    @Test
    public void inserirTest() throws SQLException {
       

    }

}
