package repositorio;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.*;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.*;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "repositorio")
@EnableTransactionManagement
public class DatabaseConfig {

        protected static final String PROPERTY_NAME_DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
        protected static final String PROPERTY_NAME_DATABASE_PASSWORD = "Leobola2133@";
        protected static final String PROPERTY_NAME_DATABASE_URL = "jdbc:mysql://localhost:3306/pcs";
        protected static final String PROPERTY_NAME_DATABASE_USERNAME = "root";

        private static final String PROPERTY_PACKAGES_TO_SCAN = "modelo";
        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter){
            LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
            entityManagerFactoryBean.setDataSource(dataSource);
            entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
            entityManagerFactoryBean.setPackagesToScan(PROPERTY_PACKAGES_TO_SCAN);
            return entityManagerFactoryBean;
        }

        @Bean
        public BasicDataSource dataSource(){
            BasicDataSource ds = new BasicDataSource();
            ds.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
            ds.setUrl(PROPERTY_NAME_DATABASE_URL);
            ds.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
            ds.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);
            ds.setInitialSize(5);
            return ds;
        }

        @Bean
        public JpaVendorAdapter jpaVendorAdapter(){
            HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
            adapter.setDatabase(Database.MYSQL);
            adapter.setShowSql(true);
            adapter.setGenerateDdl(true);

//I'm using MySQL5InnoDBDialect to make my tables support foreign keys
adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
            return adapter;
        }

        @Bean
        public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
            return new JpaTransactionManager(entityManagerFactory);
        }


    }
