package ru.kata.spring.PP_3_1_5_Rest.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @Column(name = "username")
  @NotEmpty(message = "Name is not empty")
  @Size(min = 2, max = 50, message = "Name should be from 2 to 50 characters")
  private String username;


  @Column(name = "first_name")
  @NotEmpty(message = "first_name is not empty")
  @Size(min = 2, max = 50, message = "firstName should be from 2 to 50 characters")
  private String firstName;

  @Column(name = "last_name")
  @NotEmpty(message = "lastname is not empty")
  @Size(min = 2, max = 50, message = "lastname should be from 2 to 50 characters")
  private String lastname;


  @Column(name = "age")
  @Min(value = 1, message = "Возраст не может быть меньше 1 лет!")
  @Max(value = 150, message = "Возраст не может быть больше 150 лет!")
  private Byte age;

  @Email
  @Column(name = "email")
  private String email;

  @Column(name = "password")
  @NotEmpty(message = "Password is not empty")
  private String password;

  @ManyToMany
  @JoinTable(name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private Collection <Role> roles;

  public User() {
  }

  public User(String username, String firstName, String lastname, Byte age, String email, String password, List<Role> roles) {
    this.username = username;
    this.firstName = firstName;
    this.lastname = lastname;
    this.age = age;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }

  public Long getId() {
    return id;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getFirstname() {
    return firstName;
  }
  public void setFirstname(String firstName) {
    this.firstName = firstName;
  }


  public String getLastname() {
    return lastname;
  }
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }


  public Byte getAge() {
    return age;
  }
  public void setAge(Byte age) {
    this.age = age;
  }


  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }


  @Override
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }


  public Collection<Role> getRoles() {
    return roles;
  }

  public void setRoles(Collection<Role> roles) {
    this.roles = roles;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getRoles();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}