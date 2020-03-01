package ru.it.pro.fullstacktest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
//@EnableTransactionManagement enabled by def https://github.com/jkubrynski/spring-boot/commit/9d219ef7a004c58a88bbbef82a520a22961c9402
@EnableSpringDataWebSupport
public class FullStackTestApplication {


    public static void main(String[] args) {
        SpringApplication.run(FullStackTestApplication.class, args);
    }


}
