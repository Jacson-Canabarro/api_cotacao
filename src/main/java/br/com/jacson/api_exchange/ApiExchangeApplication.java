package br.com.jacson.api_exchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ApiExchangeApplication {

    @Autowired
    private ObjectMapper objectMapper;

    @Value("https://api.exchangeratesapi.io/")
    private String apiBasePath;

    public static void main(String[] args) {
        SpringApplication.run(ApiExchangeApplication.class, args);
    }


    @Bean
    @Scope(scopeName = "prototype")
    public UriComponentsBuilder uriComponentsBuilder() {
        return UriComponentsBuilder.fromHttpUrl(apiBasePath);
    }


    @PostConstruct
    public void setUp() {
        // Configura oo ObjectMapper do Spring para lidar com LocaDate, LocalTime e LocalDateTime
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }


}
