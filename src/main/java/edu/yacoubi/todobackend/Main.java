package edu.yacoubi.todobackend;

import com.github.javafaker.Faker;
import edu.yacoubi.todobackend.dto.CategoryDTO;
import edu.yacoubi.todobackend.dto.UserDTO;
import edu.yacoubi.todobackend.model.AppUser;
import edu.yacoubi.todobackend.model.Category;
import edu.yacoubi.todobackend.service.api.CategoryServiceAPI;
import edu.yacoubi.todobackend.service.decktop.TodoService;
import edu.yacoubi.todobackend.service.decktop.UserService;
import edu.yacoubi.todobackend.service.decktop.CategoryService;
import edu.yacoubi.todobackend.service.api.UserServiceAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			UserServiceAPI userServiceAPI,
			CategoryServiceAPI categoryServiceAPI) {

		return args -> {
			//
			log.info("Main:commandLineRunner execution started.");

			//AppUser newAppUser = generateAppUser();
			UserDTO userDTO = generateUserDTO();


			UserDTO userDTOResult = userServiceAPI.createNewUser(userDTO);

			CategoryDTO categoryDTO = new CategoryDTO(
					"sport",
					"FitX",
					userDTOResult
			);


			//Category categoryOne = categoryService.save(category);

			// create some todos & connect them to category

			System.out.println();


			//	AppUser userByEmail = userService.getUserByEmail(newAppUser.getEmail());
//				System.out.println(userByEmail);


			log.info("Main:commandLineRunner execution end.");
		};
	}

	private UserDTO generateUserDTO() {
		Faker faker = new Faker();
		UserDTO userDTO = new UserDTO(
				faker.name().firstName(),
				faker.name().lastName(),
				faker.internet().emailAddress(),
				faker.name().username(),
				faker.internet().password()
		);
		return userDTO;
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
