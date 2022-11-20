package sample.spring.multipledatabases.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// need '--add-modules java.sql' in compiler options starting from java 11
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "h2EntityManagerFactory",
        transactionManagerRef = "h2TransactionManager",
        basePackages = {"sample.spring.multipledatabases.repository.h2"})
public class H2DatasourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.h2")
    public DataSourceProperties h2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource h2DataSource(
            @Qualifier("h2DataSourceProperties") DataSourceProperties properties
    ) {
        return properties.initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("h2DataSource") DataSource dataSource
    ) {
        Map<String, String> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
        jpaProperties.put("spring.jpa.show-sql", "true");


        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("sample.spring.multipledatabases.model.h2")
                .persistenceUnit("h2PersistenceUnit")
                .properties(jpaProperties)
                .build();
    }

    @Bean
    public PlatformTransactionManager h2TransactionManager(
            @Qualifier("h2EntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
