package web.dao;

import web.model.Role;

import java.util.Set;

public interface RoleDao  {
    void addRole(Set<Role> role) ;
    Set<Role> getRole();
}
