//package com.practice.practice_project.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "postgresDbEntityManager",
//        transactionManagerRef = "postgresDbTransactionManager",
//        basePackages = {"com.practice.practice_project.model"})
//public class PostgreConfig {
//
//    @Bean(name = "postgresDataSource")
//    @Primary
//    @ConfigurationProperties("datasource.postgres")
//    public DataSource dataSource(){
//        return DataSourceBuilder.create()
//                .type(HikariDataSource.class)
//                .build();
//    }
//
//    @Bean
//    @Primary
//    public EntityManagerFactoryBuilder mainEntityManagerFactoryBuilder(){
//        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
//    }
//
//    @Bean(name = "postgresDbEntityManager")
//    @Primary
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
//                                                                           @Qualifier("postgresDataSource")
//                                                                           DataSource dataSource){
//        Map<String, String> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", "update");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//        return builder
//                .dataSource(dataSource)
//                .packages("com.practice.practice_project.model")
//                .properties(properties)
//                .build();
//    }
//    @Primary
//    @Bean(name = "postgresDbTransactionManager")
//    public PlatformTransactionManager transactionManager(
//            @Qualifier("postgresDbEntityManager")EntityManagerFactory managerFactory){
//        return new JpaTransactionManager(managerFactory);
//    }
//}
