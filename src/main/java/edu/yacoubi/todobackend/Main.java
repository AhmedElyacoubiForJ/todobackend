package edu.yacoubi.todobackend;

import com.github.javafaker.Faker;
import edu.yacoubi.todobackend.exception.InvalidArgumentException;
import edu.yacoubi.todobackend.model.AppUser;
import edu.yacoubi.todobackend.model.Category;
import edu.yacoubi.todobackend.service.TodoService;
import edu.yacoubi.todobackend.service.UserService;
import edu.yacoubi.todobackend.service.CategoryService;
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
			UserService userService,
			CategoryService categoryService,
			TodoService todoService) {

		return args -> {
			//
			log.info("Main:commandLineRunner execution started.");

			AppUser newAppUser = generateAppUser();
			// create user
			AppUser userOne = userService.save(newAppUser);

			// create category
			//Category category = new Category("sport", "FitX", userOne);
			//Category categoryOne = categoryService.save(category);

			// create some todos & connect them to category

			System.out.println();


//				AppUser userByEmail = userService.findAppUserByEmail(newAppUser.getEmail());
//				System.out.println(userByEmail);


			log.info("Main:commandLineRunner execution end.");
		};
	}

	private static AppUser generateAppUser() {
		Faker faker = new Faker();
		//String email = String.format("%s.%s@yacoubi.edu", firstName, lastName);

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
