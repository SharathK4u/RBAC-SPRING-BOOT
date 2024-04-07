package com.skdamoda.rbac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skdamoda.rbac.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
