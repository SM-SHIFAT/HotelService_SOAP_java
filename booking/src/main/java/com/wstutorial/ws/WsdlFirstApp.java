package com.wstutorial.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ws.client.core.WebServiceTemplate;

@SpringBootApplication
public class WsdlFirstApp {
    public static void main(String[] args) {
        SpringApplication.run(WsdlFirstApp.class, args);
    }

    @Bean
    public WebServiceTemplate webServiceTemplate() {
        WebServiceTemplate template = new WebServiceTemplate();
        template.setDefaultUri("http://localhost:8082/wsdlfirst/bookingService"); // Endpoint URL of RoomService
        return template;
    }
}