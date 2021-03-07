package com.roginak.springboot.service;

import com.roginak.springboot.entities.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    Role getRoleByRoleName(String s);
}
