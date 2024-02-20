package ru.kata.spring.PP_3_1_5_Rest.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.PP_3_1_5_Rest.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

  List<User> getAllUsers();

  User findByUsername(String username);

  User findUserById(Long id);

  void update(User user);

  void add(User user);

  void delete(Long id);

}