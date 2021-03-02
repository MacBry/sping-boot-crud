package com.mac.bry.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication 
@EnableAutoConfiguration
//@ComponentScan(basePackages={"com.baeldung.crud","com.baeldung.crud.controllers"})
@EnableJpaRepositories(basePackages="com.mac.bry.crud.repositories")
@EnableTransactionManagement
@EntityScan(basePackages="com.mac.bry.crud.entities")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}
