package br.com.jacson.api_exchange;

import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.jacson.api_exchange.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .ignoredParameterTypes(InputStream.class, Resource.class)
                .securitySchemes(Arrays.asList(new ApiKey("Basic", HttpHeaders.AUTHORIZATION, In.HEADER.name())));

    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Api Exchange", "Cotação de moedas",
                "1.0",
                "", new Contact("Jacson", "https://www.linkedin.com/in/jacsoncanabarro/", "jacsoncanabarro@gmail.com"),
                "",
                "", Collections.emptySet());
    }
}
