package com.apress.prospring5.ch7.config;

//import com.apress.prospring5.ch6.CleanUp;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages =
        "com.apress.prospring5.ch7")
@EnableTransactionManagement
public class AppConfig {
    private static Logger logger =
            LoggerFactory.getLogger(AppConfig.class);
//    @Bean
//    public DataSource dataSource() {
//        try {
//            EmbeddedDatabaseBuilder dbBuilder =
//                    new EmbeddedDatabaseBuilder();
//            return dbBuilder.setType(EmbeddedDatabaseType.H2)
//                    .addScripts("classpath:sql/schema.sql",
//                            "classpath:sql/test-data.sql").build();
//        } catch (Exception e) {
//            logger.error("Embedded DataSource bean cannot be created!", e);
//            return null;
//        }
//    }

    @Bean
    public DataSource dataSource(){
        Properties props = new Properties();
        MysqlDataSource mysqlDS = null;
        try {
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL("jdbc:mysql://localhost/hibernate");
            mysqlDS.setUser("root");
            mysqlDS.setPassword("");
            return mysqlDS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mysqlDS;
    }


    private Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.use_sql_comments", true);
//        hibernateProp.put("hibernate.show_sql", true);
        hibernateProp.put("hibernate.max_fetch_depth", 3);
        hibernateProp.put("hibernate.jdbc.batch_size", 10);
        hibernateProp.put("hibernate.jdbc.fetch_size", 50);
//        hibernateProp.put("hibernate.enable_lazy_load_no_trans", true);
        return hibernateProp;
    }
    @Bean public SessionFactory sessionFactory()
            throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.apress.prospring5.ch7.entities");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }
    @Bean public PlatformTransactionManager transactionManager()
            throws IOException {
        return new HibernateTransactionManager(sessionFactory());
    }
}