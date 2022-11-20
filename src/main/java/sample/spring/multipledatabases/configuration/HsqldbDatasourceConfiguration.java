package sample.spring.multipledatabases.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
// need '--add-modules java.sql' in compiler options starting from java 11
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "hsqldbEntityManagerFactory",
        transactionManagerRef = "hsqldbTransactionManager",
        basePackages = {"sample.spring.multipledatabases.repository.hsqldb"})
public class HsqldbDatasourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.hsqldb")
    public DataSourceProperties hsqldbDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource hsqldbDataSource(
            @Qualifier("hsqldbDataSourceProperties") DataSourceProperties properties
    ) {
        return properties.initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean
    @ConfigurationProperties("jpa.properties.hsqldb")
    public Map<String, String> hsqlJpaProperties() {
        return new HashMap<>();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean hsqldbEntityManagerFactory(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("hsqldbDataSource") DataSource dataSource,
            @Qualifier("hsqlJpaProperties") Map<String, String> jpaProperties
    ) {
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("sample.spring.multipledatabases.model.hsqldb")
                .persistenceUnit("hsqldbPersistenceUnit")
                .properties(jpaProperties)
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager hsqldbTransactionManager(
            @Qualifier("hsqldbEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
