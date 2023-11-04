package edu.yacoubi.todobackend;

import com.github.javafaker.Faker;
import edu.yacoubi.todobackend.dto.CategoryDTO;
import edu.yacoubi.todobackend.dto.TodoDTO;
import edu.yacoubi.todobackend.dto.UserDTO;
import edu.yacoubi.todobackend.model.AppUser;
import edu.yacoubi.todobackend.model.Todo;
import edu.yacoubi.todobackend.service.api.CategoryServiceAPI;
import edu.yacoubi.todobackend.service.api.TodoServiceAPI;
import edu.yacoubi.todobackend.service.api.UserServiceAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@SpringBootApplication
@Slf4j
@EnableFeignClients
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			UserServiceAPI userServiceAPI,
			CategoryServiceAPI categoryServiceAPI,
			TodoServiceAPI todoServiceAPI) {

		return args -> {
			// scenario user create category and some todos to the last one
			log.info("Main:commandLineRunner execution started.");

			AppUser appUser = generateAppUser();

			// from API client simulation
			UserDTO userDTO = UserDTO.fromEntity(appUser);

			UserDTO userDTOResult = userServiceAPI.createNewUser(userDTO);

			CategoryDTO categoryDTO = new CategoryDTO(
					"sport",
					"FitX",
					userDTOResult
			);

			CategoryDTO categoryDTOResult = categoryServiceAPI.creatNewCategory(categoryDTO);

			Todo todo = new Todo(
					"lafen",
					"auf band",
					ZonedDateTime.now().plusDays(3)
			);

			TodoDTO todoDTO = TodoDTO.fromEntity(todo);
			todoDTO.setCategoryDTO(categoryDTOResult);

            TodoDTO newTodo = todoServiceAPI.createNewTodo(
                    todoDTO
            );

            System.out.println(newTodo);

            log.info("Main:commandLineRunner execution end.");
		};
	}

	private static AppUser generateAppUser() {
		Faker faker = new Faker();
		AppUser appUser = new AppUser(
				faker.name().firstName(),
				faker.name().lastName(),
				faker.internet().emailAddress(),
				faker.name().username(),
				faker.internet().password()
		);
		return appUser;
	}
}
