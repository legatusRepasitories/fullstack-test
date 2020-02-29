package ru.it.pro.fullstacktest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.jooq.Record3;
import org.jooq.Record4;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import ru.it.pro.fullstacktest.utill.Record3JsonSerializer;
import ru.it.pro.fullstacktest.utill.Record4JsonSerializer;

import java.io.IOException;

@SpringBootApplication
//@EnableTransactionManagement enabled by def https://github.com/jkubrynski/spring-boot/commit/9d219ef7a004c58a88bbbef82a520a22961c9402
@EnableSpringDataWebSupport
public class FullStackTestApplication {


	public static void main(String[] args) {
		SpringApplication.run(FullStackTestApplication.class, args);
	}



}
