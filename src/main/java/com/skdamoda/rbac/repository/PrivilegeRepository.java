package com.skdamoda.rbac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skdamoda.rbac.model.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long>{

}
