![Cabecalho](ReadMe-Anexos/Cabecalho.png)

[Home](ReadMe.md)

## Descrição

**Banco de Dados**: O banco de dados utilizado é o H2.

É necessário que faça o download dele em seu site e o instale.

**Configuração da base de dados**:

Arquivo *project-defaults.yml*

```
swarm:
  datasources:
    jdbc-drivers:
      h2:
        driver-class-name: org.h2.Driver
        xa-datasource-class-name: org.h2.jdbcx.JdbcDataSource
        driver-module-name: com.h2database.h2
    data-sources:
      TesteDS:
        driver-name: h2
        connection-url: {url do banco}
        user-name: {usuario}
        password: {senha}

```

Arquivo *module.xml*
```
<module xmlns="urn:jboss:module:1.1" name="com.h2database.h2">

    <resources>
        <artifact name="com.h2database:h2:1.4.195"/>
    </resources>
    <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
        <module name="javax.servlet.api" optional="true"/>
    </dependencies>
</module>
```

Depois de realizar as configurações, é necessário criar a tabela de despesas para que o sistema funcione corretamente.
```
CREATE TABLE TB_DESPESAS (
    ID INT,
    DESCRICAO varchar(255),
    VALOR varchar(255),
    DATA varchar(255),
    TIPO_LANCAMENTO INT 
);
```
**Comandos para executar o projeto:**

Execução com testes: "mvn wildfly-swarm:run"
Execução sem testes:  "mvn clean install wildfly-swarm:run -DskipTests=true"

**Testes**

Não é necessário realizar configurações para os testes, pois foi criada uma classe auxiliar *ArquillianHelper.java* para que ela limpe a base e insira os dados.



## Módulos do Software

| Módulo                                               | Descrição                          |
| ---------------------------------------------------- | ---------------------------------- |
| [Financeiro](Modulo-Financeiro/Modulo-Financeiro.md) | Módulo Financeiro                  |
| [Segurança](Modulo-Seguranca/Modulo-Seguranca.md)    | Módulo de Segurança e Autenticação |


## Documentação Técnica

| Documento                                          | Descrição                                      |
| -------------------------------------------------- | ---------------------------------------------- |
| [How-To Técnico](How-To-Tecnico/How-To-Tecnico.md) | Páginas com procedimentos técnicos específicos |


_[Sobre o Portal de Documentação](About/About.md)_


![Rodape](ReadMe-Anexos/Rodape.png)
