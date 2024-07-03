package com.vedant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vedant.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
