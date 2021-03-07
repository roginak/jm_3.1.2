package com.roginak.springboot.service;

import com.roginak.springboot.dao.RoleDao;
import com.roginak.springboot.entities.Role;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public Role getRoleByRoleName(String rolename) {
        return roleDao.getRoleByRoleName(rolename);
    }
}
