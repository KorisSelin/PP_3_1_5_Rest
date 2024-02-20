package ru.kata.spring.PP_3_1_5_Rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.PP_3_1_5_Rest.model.User;
import ru.kata.spring.PP_3_1_5_Rest.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User findByUsername(String username) {
    if (userRepository.findByUsername(username).isEmpty()) {
      throw new UsernameNotFoundException("Пользователь с таким именем не найден");
    }
    return userRepository.findByUsername(username).get();
  }

  @Override
  public User findUserById(Long id) {
    if (userRepository.findById(id).isEmpty()) {
      throw new UsernameNotFoundException("Пользователь с таким ID не найден");
    }
    return userRepository.findById(id).get();
  }

  @Transactional
  @Override
  public void update(User updatedUser) {
    Optional<User> existingUserOptional = userRepository.findById(updatedUser.getId());
    if (existingUserOptional.isPresent()) {
      User existingUser = existingUserOptional.get();
      if (!updatedUser.getPassword().equals(existingUser.getPassword())) {
        updatedUser.setPassword(new BCryptPasswordEncoder().encode(updatedUser.getPassword()));
      }
      userRepository.save(updatedUser);
    }
  }

  @Transactional
  @Override
  public void add(User user) {
    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    userRepository.save(user);
  }

  @Transactional
  @Override
  public void delete(Long id) {
    if (userRepository.findById(id).isPresent()) {
      userRepository.deleteById(id);
    }
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("Пользователь с таким именем не найден");
    }
    return user.get();
  }
}