package edu.yacoubi.todobackend.repository;

import edu.yacoubi.todobackend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository
        extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findAppUserByEmail(String email);

    @Query("SELECT CASE WHEN count(appUser) > 0 " +
            "THEN TRUE ELSE FALSE END " +
            "FROM AppUser appUser " +
            "WHERE appUser.email = ?1")
    Boolean selectExistsEmail(String email);

    Optional<AppUser> findAppUserByEmailAndPassword(String email, String password);
}
