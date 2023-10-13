package edu.yacoubi.todobackend.repository;

import edu.yacoubi.todobackend.model.AppUser;
import org.junit.jupiter.api.BeforeEach;
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
class UserRepositoryTest {

    @Autowired
    private AppUserRepository underTest;

    @Test
    void itShouldSaveAppUser() {
        // Given
        Faker faker = new Faker();
        String email = faker.name().firstName() + "." + faker.name().lastName() + "@gmail.com";
        AppUser appUser = new AppUser(
                faker.name().firstName(),
                faker.name().lastName(),
                email,
                "username",
                "12345"
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
                    //assertThat(user).isEqualToComparingFieldByField(appUser); // deprecated
                });
    }

    @Test
    void itShouldNotSaveAppUserWhenFistNameIsNull() {
        // Given
        Faker faker = new Faker();
        AppUser appUser = new AppUser(
                null,
                "Lastname",
                "Lastname@gmx.de",
                "username",
                "12345"
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
        AppUser appUser = new AppUser(
                "firstName",
                null,
                "firstName@gmx.de",
                "username",
                "12345"
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
        AppUser appUser = new AppUser(
                "firstName",
                "lastName",
                null,
                "username",
                "12345"
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
        AppUser appUser = new AppUser(
                "firstName",
                "lastName",
                "firstName.lastName@gmail.com",
                null,
                "12345"
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
        AppUser appUser = new AppUser(
                "firstName",
                "lastName",
                "firstName.lastName@gmail.com",
                "userName",
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
    //@Transactional
    void itShouldNotSaveAppUserWhenEmailAlreadyExists() {
        // Given
        String email = "underTest.email@gmx.de";
        AppUser appUserExist = new AppUser(
                "firstName_1",
                "lastName_1",
                email,
                "userName_1",
                "12345"
        );
        underTest.save(appUserExist);
        AppUser newAppUser = new AppUser(
                "firstName_2",
                "lastName_2",
                email,
                "userName_2",
                "12345789"
        );

        // When
        // Then
        assertThatThrownBy(() -> underTest.save(newAppUser))
                .hasMessageContaining("could not execute statement; SQL [n/a];")
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}