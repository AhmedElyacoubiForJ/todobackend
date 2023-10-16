package edu.yacoubi.todobackend.service;

import com.github.javafaker.Faker;
import edu.yacoubi.todobackend.EntityNotFoundException;
import edu.yacoubi.todobackend.model.AppUser;
import edu.yacoubi.todobackend.repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppUserServiceImplTest {

    // test the class in isolation,
    // using a Mock for dependencies and classes that already tested
    @Mock
    private AppUserRepository appUserRepository;

    private AppUserServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new AppUserServiceImpl(appUserRepository);
    }

    @Test
    public void itShouldFindAppUserById() {
        // Given
        Faker faker = new Faker();
        AppUser expectedAppUser = new AppUser(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.name().username(),
                faker.internet().password()
        );

        // When
        when(appUserRepository.findById(anyLong()))
                .thenReturn(Optional.of(expectedAppUser));

        // Then
        assertThat(underTest.findById(anyLong()))
                .usingRecursiveComparison()
                .isEqualTo(expectedAppUser);

        verify(appUserRepository, times(1))
                .findById(anyLong());

        verifyNoMoreInteractions(appUserRepository);
    }

    @Test
    public void itShouldThrownWhenFindAppUserByIdNotFound() {
        // Given
        Long id = 1000L;


        // When
        // Then
        assertThatThrownBy(
                () -> underTest.findById(id)
        )
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("user with ID :  " + id + " not found");
    }

    @Test
    public void itShouldThrownWhenFindAppUserByIdIsNull() {
        // Given
        Long id = null;

        // When
        // Then
        assertThatThrownBy(
                () -> underTest.findById(id)
        )
                .isInstanceOf(IllegalArgumentException.class);

        // Finally
        then(appUserRepository).should(never()).findById(any());
    }

}