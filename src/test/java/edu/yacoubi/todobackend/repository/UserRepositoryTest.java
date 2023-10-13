package edu.yacoubi.todobackend.repository;

import edu.yacoubi.todobackend.model.AppUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional
class UserRepositoryTest {

    @Autowired
    private AppUserRepository underTest;

    @Test
    @Transactional
    void itShouldSaveAppUser() {
        // Given
        Long id = 1L;
        AppUser appUser = new AppUser(
                "Firstname",
                "Lastname",
                "Firstname.Lastname@gmx.de",
                "username",
                "12345"
        );

        // When
        underTest.save(appUser);

        // Then
        Optional<AppUser> optionalAppUser = underTest.findById(id);
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
        Long id = 1L;
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

        // Then
        assertThatThrownBy(() -> underTest.save(newAppUser))
                .hasMessageContaining("could not execute statement; SQL [n/a];")
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}