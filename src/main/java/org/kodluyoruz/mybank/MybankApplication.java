package org.kodluyoruz.mybank;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

@EnableSwagger2
@EnableJpaRepositories(basePackages = "org.kodluyoruz.mybank.repository")
@SpringBootApplication
public class MybankApplication {


    public static void main(String[] args) throws IOException {


        SpringApplication.run(MybankApplication.class, args);
    }

}
