package com.vmgtool.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vmgtool.entity.Question;
import com.vmgtool.entity.Studies;
import com.vmgtool.entity.User;

public interface StudiesRepository extends JpaRepository<Studies,Integer> {

	List<Studies> findByUser(User user);

	

	
	


}
