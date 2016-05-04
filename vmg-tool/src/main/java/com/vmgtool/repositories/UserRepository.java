package com.vmgtool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vmgtool.entity.User;


public interface UserRepository extends JpaRepository<User,Integer>{

	User findByName(String name);

}
