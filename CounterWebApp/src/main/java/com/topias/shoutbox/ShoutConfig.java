package com.topias.shoutbox;

import static org.hibernate.cfg.AvailableSettings.C3P0_ACQUIRE_INCREMENT;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_SIZE;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_STATEMENTS;
import static org.hibernate.cfg.AvailableSettings.C3P0_MIN_SIZE;
import static org.hibernate.cfg.AvailableSettings.C3P0_TIMEOUT;
import static org.hibernate.cfg.AvailableSettings.DRIVER;
import static org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO;
import static org.hibernate.cfg.AvailableSettings.PASS;
import static org.hibernate.cfg.AvailableSettings.SHOW_SQL;
import static org.hibernate.cfg.AvailableSettings.URL;
import static org.hibernate.cfg.AvailableSettings.USER;

import java.util.Properties;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.ext.RuntimeDelegate;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.topias.shoutbox.rest.ShoutBoxRestService;

@Configuration
@PropertySource( "classpath:db.properties" )
//@EnableSpringConfigured
@ComponentScan( basePackageClasses = { ShoutConfig.class } )
public class ShoutConfig {
    
    @ApplicationPath( "/shout" )
    public class JaxRsApiApplication extends Application {

    }

    @Bean( destroyMethod = "shutdown" )
    public SpringBus cxf() {
        System.out.println( "CXFbean created" );
        return new SpringBus();
    }
    
    @Bean
    @DependsOn( "cxf" )
    public Server jaxRsServer( ApplicationContext context ) {
        JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance().createEndpoint( jaxRsApiApplication(),
                JAXRSServerFactoryBean.class );
        factory.setServiceBean( shoutBoxRestService() );
        System.out.println( "jaxRsServer created" );
        return factory.create();
    }
    
    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        String[] annotatedPackages = { "com.topias.shoutbox.model" };
        factoryBean.setAnnotatedPackages( annotatedPackages );

        Properties props = new Properties();
        // Setting JDBC properties
        props.put( DRIVER, env.getProperty( "mysql.driver" ) );
        props.put( URL, env.getProperty( "mysql.url" ) );
        props.put( USER, env.getProperty( "mysql.user" ) );
        props.put( PASS, env.getProperty( "mysql.password" ) );

        // Setting Hibernate properties
        props.put( SHOW_SQL, env.getProperty( "hibernate.show_sql" ) );
        props.put( HBM2DDL_AUTO, env.getProperty( "hibernate.hbm2ddl.auto" ) );

        // Setting C3P0 properties
        props.put( C3P0_MIN_SIZE, env.getProperty( "hibernate.c3p0.min_size" ) );
        props.put( C3P0_MAX_SIZE, env.getProperty( "hibernate.c3p0.max_size" ) );
        props.put( C3P0_ACQUIRE_INCREMENT, env.getProperty( "hibernate.c3p0.acquire_increment" ) );
        props.put( C3P0_TIMEOUT, env.getProperty( "hibernate.c3p0.timeout" ) );
        props.put( C3P0_MAX_STATEMENTS, env.getProperty( "hibernate.c3p0.max_statements" ) );

        factoryBean.setHibernateProperties( props );
        factoryBean.setPackagesToScan( "com.topias.shoutbox" );

        return factoryBean;
    }
    

    @Bean
    public JaxRsApiApplication jaxRsApiApplication() {
        return new JaxRsApiApplication();
    }

    @Bean
    public ShoutBoxRestService shoutBoxRestService() {
        return new ShoutBoxRestService();
    }

}
