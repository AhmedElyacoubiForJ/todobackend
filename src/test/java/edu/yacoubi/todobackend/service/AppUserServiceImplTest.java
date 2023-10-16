package edu.yacoubi.todobackend.service;

import com.github.javafaker.Faker;
import edu.yacoubi.todobackend.EntityNotFoundException;
import edu.yacoubi.todobackend.model.AppUser;
import edu.yacoubi.todobackend.repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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

    @Captor
    private ArgumentCaptor<String> emailArgumentCaptor;

    @BeforeEach
    void setUp() {
        underTest = new AppUserServiceImpl(appUserRepository);
    }

    @Test
    public void itShouldFindAppUserByEmail() {
        // Given
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        var expectedAppUser = new AppUser(
                faker.name().firstName(),
                faker.name().lastName(),
                email,
                faker.name().username(),
                faker.internet().password()
        );

        // When
        when(appUserRepository.findAppUserByEmail(email))
                .thenReturn(Optional.of(expectedAppUser));
        var actualAppUser = underTest.findAppUserByEmail(email);

        // Then
        then(appUserRepository)
                .should()
                .findAppUserByEmail(emailArgumentCaptor.capture());

        assertThat(emailArgumentCaptor.getValue())
                .isEqualTo(email);

        assertThat(actualAppUser)
                .usingRecursiveComparison()
                .isEqualTo(expectedAppUser);

        verify(
                appUserRepository,
                times(1)
        ).findAppUserByEmail(email);

        verifyNoMoreInteractions(appUserRepository);
    }

    @Test
    public void itShouldThrownWhenFindAppUserByEmailNotFound() {
        // Given
        String email = new Faker().internet().emailAddress();

        // When
        when(appUserRepository.findAppUserByEmail(email))
                .thenReturn(Optional.empty());

        // Then
        assertThatThrownBy(
                () -> underTest.findAppUserByEmail(email)
        )
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("user with EMAIL :  " + email + " not found");

        // Then
        then(appUserRepository)
                .should()
                .findAppUserByEmail(emailArgumentCaptor.capture());

        assertThat(emailArgumentCaptor.getValue())
                .isEqualTo(email);

        verify(
                appUserRepository,
                times(1)
        ).findAppUserByEmail(email);

        verifyNoMoreInteractions(appUserRepository);
    }

    @Test
    public void itShouldThrownWhenFindAppUserByEmailIsNull() {
        // Given
        String email = null;

        // When
        // Then
        assertThatThrownBy(
                () -> underTest.findAppUserByEmail(email)
        )
                .isInstanceOf(IllegalArgumentException.class);

        verify(appUserRepository, times(0))
                .findAppUserByEmail(anyString());
        //then(appUserRepository).should(never()).findAppUserByEmail(anyString());
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
        Long id = 0L;

        // When
        when(appUserRepository.findById(id))
                //.thenThrow(new EntityNotFoundException("user with ID :  " + id + " not found"));
                .thenReturn(Optional.empty());

        // Then
        assertThatThrownBy(
                () -> underTest.findById(id)
        )
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("user with ID :  " + id + " not found");

        verify(
                appUserRepository,
                times(1) // will be called one time
        ).findById(id);
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

        verify(
                appUserRepository,
                times(0) // will be not called
        ).findById(any());

        //then(appUserRepository).should(never()).findById(any());
    }

}