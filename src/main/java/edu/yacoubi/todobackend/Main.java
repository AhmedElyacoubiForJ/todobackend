package edu.yacoubi.todobackend;

import com.github.javafaker.Faker;
import edu.yacoubi.todobackend.model.AppUser;
import edu.yacoubi.todobackend.repository.AppUserRepository;
import edu.yacoubi.todobackend.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			AppUserService appUserService) {

		return args -> {
			//
			AppUser newAppUser = generateAppUser();
			appUserService.save(newAppUser);

			AppUser userByEmail = appUserService.findAppUserByEmail(newAppUser.getEmail());
			System.out.println(userByEmail);

		};
	}

	private static AppUser generateAppUser() {
		Faker faker = new Faker();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		//String email = String.format("%s.%s@yacoubi.edu", firstName, lastName);
		String email = faker.internet().emailAddress();
		String username = faker.name().username();
		String password = faker.internet().password();

		AppUser appUser = new AppUser(
				firstName,
				lastName,
				email,
				username,
				password
		);
		return appUser;
	}
}
