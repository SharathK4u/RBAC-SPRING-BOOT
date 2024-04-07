package com.skdamoda.rbac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skdamoda.rbac.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
