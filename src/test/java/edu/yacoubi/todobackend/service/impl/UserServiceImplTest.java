package edu.yacoubi.todobackend.service.impl;

import com.github.javafaker.Faker;
import edu.yacoubi.todobackend.exception.EntityNotFoundException;
import edu.yacoubi.todobackend.exception.InvalidArgumentException;
import edu.yacoubi.todobackend.model.AppUser;
import edu.yacoubi.todobackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    // test the class in isolation,
    // using a Mock for dependencies and classes that already tested
    @Mock
    private UserRepository userRepository;

    private UserServiceImpl underTest;

    @Captor
    private ArgumentCaptor<String> emailArgumentCaptor;

    @Captor
    private ArgumentCaptor<AppUser> appUserArgumentCaptor;

    @BeforeEach
    void setUp() {
        underTest = new UserServiceImpl(userRepository);
    }

    @Test
    public void itShouldNotSaveAppUserIf_AppUser_IsNull() {
        // Given

        // When
        // Then
        assertThatThrownBy(
                () -> underTest.save(null)
        ).isInstanceOf(InvalidArgumentException.class)
                .hasMessageContaining("AppUser must not be null");

        verify(
                userRepository,
                times(0)
        ).save(any());
    }

    @Test
    public void itShouldSaveAppUser() {
        // Given
        Faker faker = new Faker();
        var expectedAppUser = new AppUser(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.name().username(),
                faker.internet().password()
        );

        // when
        // when(userRepository.save(expectedAppUser)).thenReturn(expectedAppUser);
        underTest.save(expectedAppUser);

        // Then
        then(userRepository)
                .should()
                .save(appUserArgumentCaptor.capture());

        assertThat(appUserArgumentCaptor.getValue())
                .isEqualTo(expectedAppUser);

//        assertThat(expectedAppUser)
//                .usingRecursiveComparison()
//                .isEqualTo(expectedAppUser);

        verify(
                userRepository,
                times(1)
        ).save(expectedAppUser);
    }

    @Test
    public void itShouldFindAllAppUsers() {
        // Arrange
        when(userRepository.findAll())
                .thenReturn(
                        List.of(
                                new AppUser(),
                                new AppUser(),
                                new AppUser()
                        )
                );

        // Act & Assert
        assertThat(underTest.findAll()).hasSize(3);

        verify(
                userRepository,
                times(1)
        ).findAll();

        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void itShouldThrowByDeleteUserById_If_Id_IsNull() {
        // Given
        Long id = null;

        // When
        // Then
        assertThatThrownBy(
                () -> underTest.findById(id)
        ).isInstanceOf(InvalidArgumentException.class)
                .hasMessageContaining("id must not be null");
                //.hasMessageContaining("argument cannot be null");

        verify(userRepository, times(0))
                .deleteById(anyLong());
    }

    @Test
    public void itShouldDeleteUserById() {
        // When
        doNothing().when(userRepository).deleteById(anyLong());
        underTest.delete(anyLong());

        // Then
        verify(
                userRepository,
                times(1)
        ).deleteById(anyLong());
        verifyNoMoreInteractions(userRepository);
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
        when(userRepository.findAppUserByEmail(email))
                .thenReturn(Optional.of(expectedAppUser));
        var actualAppUser = underTest.findAppUserByEmail(email);

        // Then
        then(userRepository)
                .should()
                .findAppUserByEmail(emailArgumentCaptor.capture());

        assertThat(emailArgumentCaptor.getValue())
                .isEqualTo(email);

        assertThat(actualAppUser)
                .usingRecursiveComparison()
                .isEqualTo(expectedAppUser);

        verify(
                userRepository,
                times(1)
        ).findAppUserByEmail(email);

        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void itShouldThrownWhenFindAppUserByEmailNotFound() {
        // Given
        String email = new Faker().internet().emailAddress();

        // When
        when(userRepository.findAppUserByEmail(email))
                .thenReturn(Optional.empty());

        // Then
        assertThatThrownBy(
                () -> underTest.findAppUserByEmail(email)
        )
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("user with EMAIL :  " + email + " not found");

        // Then
        then(userRepository)
                .should()
                .findAppUserByEmail(emailArgumentCaptor.capture());

        assertThat(emailArgumentCaptor.getValue())
                .isEqualTo(email);

        verify(
                userRepository,
                times(1)
        ).findAppUserByEmail(email);

        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void itShouldThrownWhenFindAppUserByEmailIsNull() {
        // Given
        //String email = null;

        // When
        // Then
        assertThatThrownBy(
                () -> underTest.findAppUserByEmail(null)
        )
                .isInstanceOf(InvalidArgumentException.class)
                .hasMessageContaining("email must not be null");

        verify(userRepository, times(0))
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
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(expectedAppUser));

        // Then
        assertThat(underTest.findById(anyLong()))
                .usingRecursiveComparison()
                .isEqualTo(expectedAppUser);

        verify(userRepository, times(1))
                .findById(anyLong());

        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void itShouldThrownWhenFindAppUserByIdNotFound() {
        // Given
        Long id = 0L;

        // When
        when(userRepository.findById(id))
                //.thenThrow(new EntityNotFoundException("user with ID :  " + id + " not found"));
                .thenReturn(Optional.empty());

        // Then
        assertThatThrownBy(
                () -> underTest.findById(id)
        )
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("user with ID :  " + id + " not found");

        verify(
                userRepository,
                times(1) // will be called one time
        ).findById(id);
    }

    @Test
    public void itShouldThrownWhenFindAppUserByIdIsNull() {
        // Given
        // Long id = null;

        // When
        // Then
        assertThatThrownBy(
                () -> underTest.findById(null)
        ).isInstanceOf(InvalidArgumentException.class)
                .hasMessageContaining("id must not be null");

        verify(
                userRepository,
                times(0) // will not be called
        ).findById(any());

        //then(appUserRepository).should(never()).findById(any());
    }
}