package ru.kata.spring.PP_3_1_5_Rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.kata.spring.PP_3_1_5_Rest.model.User;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
  @Query("Select u from User u left join fetch u.roles where u.username=:username")
  Optional<User> findByUsername(String username);
}
