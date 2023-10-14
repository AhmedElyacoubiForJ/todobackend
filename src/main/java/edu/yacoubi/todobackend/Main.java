package edu.yacoubi.todobackend;

import com.github.javafaker.Faker;
import edu.yacoubi.todobackend.model.AppUser;
import edu.yacoubi.todobackend.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			AppUserRepository appUserRepository) {

		return args -> {
			//
			AppUser newAppUser = generateAppUser();
			appUserRepository.save(newAppUser);

			Optional<AppUser> userByEmail = appUserRepository.findUserByEmail(newAppUser.getEmail());

			userByEmail.ifPresent(user -> {
				System.out.println(user.getId());
				System.out.println(user.getFirstName());
				System.out.println(user.getLastName());
				System.out.println(user.getEmail());
			});

		};
	}

	private static AppUser generateAppUser() {
		Faker faker = new Faker();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String email = String.format("%s.%s@yacoubi.edu", firstName, lastName);

		AppUser appUser = new AppUser(
				firstName,
				lastName,
				email,
				"asasas",
				"wewe"
		);
		return appUser;
	}
}
