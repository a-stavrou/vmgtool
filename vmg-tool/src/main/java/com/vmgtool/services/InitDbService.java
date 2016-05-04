/*package com.vmgtool.services;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vmgtool.entity.Question;
import com.vmgtool.entity.QuestionOptions;
import com.vmgtool.entity.Role;
import com.vmgtool.entity.Studies;
import com.vmgtool.entity.User;
import com.vmgtool.repositories.QuestionOptionsRepository;
import com.vmgtool.repositories.QuestionRepository;
import com.vmgtool.repositories.RoleRepository;
import com.vmgtool.repositories.StudiesRepository;
import com.vmgtool.repositories.UserRepository;

@Transactional
@Service
public class InitDbService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StudiesRepository studiesRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private QuestionOptionsRepository questionOptionsRepository;
	
	@PostConstruct
	public void init() {

		Role roleUser = new Role();
	roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);

		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);

		User userAdmin = new User();
		userAdmin.setEnabled(true);
		userAdmin.setName("admin");
		userAdmin.setEmail("A.Stavrou2@uni.brighton.ac.uk");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("E6E40297A4!vMg"));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);

	}

}
*/