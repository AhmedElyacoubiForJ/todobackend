package edu.yacoubi.todobackend;

import com.github.javafaker.Faker;
import edu.yacoubi.todobackend.model.AppUser;
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
			CategoryService categoryService) {

		return args -> {
			//
			log.info("commandLineRunner start call...");
			AppUser newAppUser = generateAppUser();
			userService.save(newAppUser);

			AppUser userByEmail = userService.findAppUserByEmail(newAppUser.getEmail());
			System.out.println(userByEmail);
			log.info("commandLineRunner end call...");

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
