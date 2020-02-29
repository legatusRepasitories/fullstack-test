package ru.it.pro.fullstacktest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.Record3;
import org.jooq.Record4;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.it.pro.fullstacktest.utill.Record3JsonSerializer;
import ru.it.pro.fullstacktest.utill.Record4JsonSerializer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }


}
