package co.com.roman.microservice.resolveEnigmaApi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "co.com.roman.microservice.resolveEnigmaApi", "co.com.roman.microservice.resolveEnigmaApi.api" , "co.com.roman.microservice.resolveEnigmaApi.config"})
public class resolveEnigmaApi  {

   

    public static void main(String[] args) throws Exception {
        new SpringApplication(resolveEnigmaApi.class).run(args);
    }

}
