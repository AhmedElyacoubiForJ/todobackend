package edu.yacoubi.todobackend.repository;

import edu.yacoubi.todobackend.model.AppUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.javafaker.Faker;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository underTest;

    @Test
    @Transactional
    void itShouldSaveAppUser() {
        // Given
        Faker faker = new Faker();
        AppUser appUser = new AppUser(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.name().username(),
                faker.internet().password()
        );

        // When
        AppUser savedAppUser = underTest.save(appUser);
        Long id = savedAppUser.getId();
        Optional<AppUser> optionalAppUser = underTest.findById(id);


        // Then
        assertThat(optionalAppUser)
                .isPresent()
                .hasValueSatisfying(user -> {
                    assertThat(user.getId()).isEqualTo(id);
                    assertThat(user.getFirstName()).isEqualTo(appUser.getFirstName());
                    assertThat(user.getLastName()).isEqualTo(appUser.getLastName());
                    assertThat(user.getEmail()).isEqualTo(appUser.getEmail());
                    assertThat(user.getUserName()).isEqualTo(appUser.getUserName());
                    assertThat(user.getPassword()).isEqualTo(appUser.getPassword());
                    // lazy initialize problem if the test method not annotated as Transactional
                    assertThat(user).isEqualToComparingFieldByField(appUser); // deprecated
                });
    }

    @Test
    void itShouldNotSaveAppUserWhenFistNameIsNull() {
        // Given
        Faker faker = new Faker();
        AppUser appUser = new AppUser(
                null,
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.name().username(),
                faker.internet().password()
        );

        // When
        // Then
        String message = "not-null property references a null or transient value : " +
                "edu.yacoubi.todobackend.model.AppUser.firstName";
        assertThatThrownBy(
                () -> underTest.save(appUser)
        )
                .hasMessageContaining(message)
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void itShouldNotSaveAppUserWhenLastNameIsNull() {
        // Given
        Faker faker = new Faker();
        AppUser appUser = new AppUser(
                faker.name().firstName(),
                null,
                faker.internet().emailAddress(),
                faker.name().username(),
                faker.internet().password()
        );

        // When
        // Then
        String message = "not-null property references a null or transient value : " +
                "edu.yacoubi.todobackend.model.AppUser.lastName";
        assertThatThrownBy(
                () -> underTest.save(appUser)
        )
                .hasMessageContaining(message)
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void itShouldNotSaveAppUserWhenEmailIsNull() {
        // Given
        Faker faker = new Faker();
        AppUser appUser = new AppUser(
                faker.name().firstName(),
                faker.name().lastName(),
                null,
                faker.name().username(),
                faker.internet().password()
        );

        // When
        // Then
        String message = "not-null property references a null or transient value : " +
                "edu.yacoubi.todobackend.model.AppUser.email";
        assertThatThrownBy(
                () -> underTest.save(appUser)
        )
                .hasMessageContaining(message)
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void itShouldNotSaveAppUserWhenUserNameIsNull() {
        // Given
        Faker faker = new Faker();
        AppUser appUser = new AppUser(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                null,
                faker.internet().password()
        );

        // When
        // Then
        String message = "not-null property references a null or transient value : " +
                "edu.yacoubi.todobackend.model.AppUser.userName";
        assertThatThrownBy(
                () -> underTest.save(appUser)
        )
                .hasMessageContaining(message)
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void itShouldNotSaveAppUserWhenPasswordIsNull() {
        // Given
        Faker faker = new Faker();
        AppUser appUser = new AppUser(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.name().username(),
                null
        );

        // When
        // Then
        String message = "not-null property references a null or transient value : " +
                "edu.yacoubi.todobackend.model.AppUser.password";
        assertThatThrownBy(
                () -> underTest.save(appUser)
        )
                .hasMessageContaining(message)
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void itShouldNotSaveAppUserWhenEmailAlreadyExists() {
        // Given
        Faker faker = new Faker();
        String underTestUniqueEmail = faker.internet().emailAddress();
        AppUser appUserExist = new AppUser(
                faker.name().firstName(),
                faker.name().lastName(),
                underTestUniqueEmail,
                faker.name().username(),
                faker.internet().password()
        );
        underTest.save(appUserExist);

        faker = new Faker();
        AppUser newAppUser = new AppUser(
                faker.name().firstName(),
                faker.name().lastName(),
                underTestUniqueEmail,
                faker.name().username(),
                faker.internet().password()
        );

        // When
        // Then
        assertThatThrownBy(
                () -> underTest.save(newAppUser)
        )
                .hasMessageContaining("could not execute statement; SQL [n/a];")
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void itShouldFindAppUserByEmail() {
        // Given
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        AppUser appUser = new AppUser(
                faker.name().firstName(),
                faker.name().lastName(),
                email,
                faker.name().username(),
                faker.internet().password()
        );
        AppUser savedAppUser = underTest.save(appUser);

        // When
        Optional<AppUser> userByEmailOptional = underTest.findAppUserByEmail(email);

        // Then
        assertThat(userByEmailOptional)
                .isPresent()
                .hasValueSatisfying(
                        user -> {
                            user.getEmail().equals(email);
                        }
                );

    }

    @Test
    void itShouldSelectIfEmailExists() {
        // Given
        String dontExistsEmail = new Faker().internet().emailAddress();

        Faker faker = new Faker();
        String existsEmail = faker.internet().emailAddress();
        AppUser appUser = new AppUser(
                faker.name().firstName(),
                faker.name().lastName(),
                existsEmail,
                faker.name().username(),
                faker.internet().password()
        );
        AppUser savedAppUser = underTest.save(appUser);

        // When
        // Then
        assertThat(
                underTest.selectExistsEmail(existsEmail)
        ).isTrue();

        assertThat(
                underTest.selectExistsEmail(dontExistsEmail)
        ).isFalse();
    }
}