package br.com.javapress.application.infrastructure.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"br.com.javapress.domain.repository"})
@PropertySource("classpath:persistence.properties")	
@ComponentScan("br.com.javapress.domain.entity")
public class AppConfig {
	
	private static final String PROPERTY_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_DATABASE_URL = "db.url";
    private static final String PROPERTY_DATABASE_USERNAME = "db.username";
         
    private static final String PROPERTY_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_HIBERNATE_SHOW_SQL = "hibernate.show_sql"; 
    private static final String PROPERTY_GENERATE_DDL = "hibernate.generate_ddl";
    private static final String PROPERTY_CREATE_SCHEMA_DDL = "hibernate.hbm2ddl.auto";
	private static final String PROPERTY_HIBERNATE_PACKAGES_TO_SCAN = "hibernate.packagesToScan"; 
	private static final String PROPERTY_HIBERNATE_DEFAULT_SCHEMA = "hibernate.defaultSchema"; 

	@Resource
    private Environment env;
	
	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
         
        dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_DATABASE_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(PROPERTY_DATABASE_URL));
        dataSource.setUsername(env.getRequiredProperty(PROPERTY_DATABASE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROPERTY_DATABASE_PASSWORD));
         
        return dataSource;
    }
     
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setJpaProperties(hibProperties());
        entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(PROPERTY_HIBERNATE_PACKAGES_TO_SCAN));
        entityManagerFactoryBean.setPersistenceUnitName("javapress");
         
        return entityManagerFactoryBean;
    }
     
    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put(PROPERTY_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_HIBERNATE_DIALECT));
        properties.put(PROPERTY_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_HIBERNATE_SHOW_SQL));
        properties.put(PROPERTY_GENERATE_DDL, env.getRequiredProperty(PROPERTY_GENERATE_DDL));
        properties.put(PROPERTY_CREATE_SCHEMA_DDL, env.getRequiredProperty(PROPERTY_CREATE_SCHEMA_DDL));
        properties.put(PROPERTY_HIBERNATE_PACKAGES_TO_SCAN, env.getRequiredProperty(PROPERTY_HIBERNATE_PACKAGES_TO_SCAN));
        properties.put(PROPERTY_HIBERNATE_DEFAULT_SCHEMA, env.getRequiredProperty(PROPERTY_HIBERNATE_DEFAULT_SCHEMA));
        return properties;        
    }
     
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
