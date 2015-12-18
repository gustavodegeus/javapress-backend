package br.com.javapress.infrastructure.config;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.validation.Validator;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"br.com.javapress.domain.repository"})
@PropertySource("classpath:persistence.properties")	
@ComponentScan(basePackages = {"br.com.javapress.domain","br.com.javapress.infrastructure.security",
							"br.com.javapress.infrastructure.s3"})
public class AppConfig {
	
	private static final String PROPERTY_DATABASE_DRIVER = "db.driver";
         
    private static final String PROPERTY_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_HIBERNATE_SHOW_SQL = "hibernate.show_sql"; 
    private static final String PROPERTY_GENERATE_DDL = "hibernate.generate_ddl";
    private static final String PROPERTY_CREATE_SCHEMA_DDL = "hibernate.hbm2ddl.auto";
	private static final String PROPERTY_HIBERNATE_PACKAGES_TO_SCAN = "hibernate.packagesToScan"; 
	private static final String PROPERTY_HIBERNATE_DEFAULT_SCHEMA = "hibernate.defaultSchema"; 
    private static final String PROPERTY_ACCESS_KEY = "aws.accessKeyId";
    private static final String PROPERTY_SECRET_KEY = "aws.secretKey";
	
	@Resource
    private Environment env;
	
	@Bean
    public DataSource dataSource() throws URISyntaxException {
         
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":").length == 1 ? "" : dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_DATABASE_DRIVER));
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);         
        return dataSource;
    }
     
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws URISyntaxException {
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
    public JpaTransactionManager transactionManager() throws URISyntaxException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
    
    @Bean(name="validator")
    public Validator validator() {
       return new LocalValidatorFactoryBean();
    }
    
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
    	return new MethodValidationPostProcessor();
    }
    
    @Bean
    public AmazonS3 amazonS3Client() throws IOException{
    	 AmazonS3 s3 = new AmazonS3Client(this.getCredentials());
    	 Region usWest2 = Region.getRegion(Regions.SA_EAST_1);
         s3.setRegion(usWest2);
         return s3;
    }
    
    public AWSCredentials getCredentials() throws IOException{
		ResourcePropertySource awsProperties = new ResourcePropertySource("classpath:aws.properties");
    	
    	if (awsProperties.getProperty(PROPERTY_ACCESS_KEY) != null &&
    			awsProperties.getProperty(PROPERTY_SECRET_KEY) != null) {
        
    		return new BasicAWSCredentials(
    				awsProperties.getProperty(PROPERTY_ACCESS_KEY).toString(),
    				awsProperties.getProperty(PROPERTY_SECRET_KEY).toString());
        }
    	
    	throw new AmazonClientException(
                "Unable to load AWS credentials from Java system properties " +
                "(" + PROPERTY_ACCESS_KEY + " and " + PROPERTY_SECRET_KEY + ")");	
    }
}
