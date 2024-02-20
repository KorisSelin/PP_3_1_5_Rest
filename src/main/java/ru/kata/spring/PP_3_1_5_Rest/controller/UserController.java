package ru.kata.spring.PP_3_1_5_Rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.PP_3_1_5_Rest.model.User;
import ru.kata.spring.PP_3_1_5_Rest.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/info")
  public ResponseEntity<User> infoAboutUser (Principal principal) {
    return new ResponseEntity<>(userService.findByUsername(principal.getName()), HttpStatus.OK);
  }
}






