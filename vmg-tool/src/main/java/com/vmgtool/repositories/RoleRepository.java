package com.vmgtool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vmgtool.entity.Role;



public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByName(String string);

}
