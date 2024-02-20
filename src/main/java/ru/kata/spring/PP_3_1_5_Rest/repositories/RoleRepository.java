package ru.kata.spring.PP_3_1_5_Rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.kata.spring.PP_3_1_5_Rest.model.Role;


@Repository
@EnableJpaRepositories
public interface RoleRepository extends JpaRepository<Role, Long> {
}
