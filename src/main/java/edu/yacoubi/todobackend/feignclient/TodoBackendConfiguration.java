package edu.yacoubi.todobackend.feignclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class TodoBackendConfiguration {

    @Bean("todobackend")
    CommandLineRunner runner(TodoBackendClient todoBackendClient) {
        return args -> {
            //System.out.println(todoBackendClient.getUsers());
        };
    }
}
