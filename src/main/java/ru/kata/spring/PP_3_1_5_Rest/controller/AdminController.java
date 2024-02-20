package ru.kata.spring.PP_3_1_5_Rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.PP_3_1_5_Rest.model.Role;
import ru.kata.spring.PP_3_1_5_Rest.model.User;
import ru.kata.spring.PP_3_1_5_Rest.service.RoleService;
import ru.kata.spring.PP_3_1_5_Rest.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

  private final UserService userService;
  private final RoleService roleService;

  @Autowired
  public AdminController(UserService userService, RoleService roleService) {
    this.userService = userService;
    this.roleService = roleService;
  }


  @GetMapping("/info")
  public ResponseEntity<User> infoAboutUser (Principal principal) {
    return new ResponseEntity<>(userService.findByUsername(principal.getName()), HttpStatus.OK);
  }

  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers() {
    return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
  }

  @GetMapping("/roles")
  public ResponseEntity<Collection<Role>> getAllRoles() {
    return new ResponseEntity<>(roleService.getAllRole(), HttpStatus.OK);
  }

  @GetMapping("/roles/{id}")
  public ResponseEntity<Collection<Role>> getRole(@PathVariable("id") Long id) {
    return new ResponseEntity<>(userService.findUserById(id).getRoles(), HttpStatus.OK);
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
    return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
  }

  @PostMapping("/users")
  public ResponseEntity<User> addUser(@RequestBody @Valid User userNew) {
    userService.add(userNew);
    return new ResponseEntity<>(userNew, HttpStatus.OK);

  }

  @PatchMapping("/users/{id}")
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    userService.update(user);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
    userService.findUserById(id);
    userService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}


