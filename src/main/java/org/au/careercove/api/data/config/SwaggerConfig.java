package org.au.careercove.api.data.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.google.common.base.Predicates;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.PathSelectors; // Import the necessary class

@Configuration
@EnableSwagger2
@Import(SpringDataRestConfiguration.class)
public class SwaggerConfig {
    
    @Bean
    public Docket api() { 
            return new Docket(DocumentationType.SWAGGER_2)  
                .select()                                  
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/data-service/api/actuator/**").negate())
                .paths(PathSelectors.ant("/data-service/api/profile/**").negate())
                .paths(PathSelectors.ant("/data-service/api/error/**").negate())
                .build()
                .apiInfo(apiInfo());                                           
    }

    private ApiInfo apiInfo() {
         return new ApiInfo(
                 "CareerCove data APIs",
                 "CareerCove data APIs",
                 "1.0",
                 "Terms of service",
                 new Contact("Amity University - CareerCove", "https://careercove.com", "support@careercove.com"),
                "Apache License Version 2.0",
                 "https://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
    }
}