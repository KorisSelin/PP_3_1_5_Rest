package ru.kata.spring.PP_3_1_5_Rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.PP_3_1_5_Rest.model.Role;
import ru.kata.spring.PP_3_1_5_Rest.repositories.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;


  @Autowired
  public RoleServiceImpl(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public List<Role> getAllRole() {
    return roleRepository.findAll();
  }

}