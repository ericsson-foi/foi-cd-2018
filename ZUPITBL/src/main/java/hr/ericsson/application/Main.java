package hr.ericsson.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import hr.ericsson.application.dao.ApplicationDAO;
import hr.ericsson.application.domain.Application;

@SpringBootApplication
public class Main {
    
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args); 

    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	//TODO ilija komentar  izvuci u propertie file                
                registry.addMapping("/application/**").allowedOrigins("*");                
            }
        };
    }

}
