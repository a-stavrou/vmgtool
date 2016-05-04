package com.vmgtool.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

@Service
@Transactional
public class UserService {

		@Autowired
		private UserRepository userRepository;
		
		@Autowired
		private RoleRepository roleRepository;
		
		@Autowired
		private QuestionRepository questionRepository;
		
		@Autowired
		private QuestionOptionsRepository questionOptionsRepository;
		
		@Autowired
		private StudiesRepository studiesRepository;
		
		public List<User> findAll() {
			return userRepository.findAll();
		}
		
		public User findOne(int id) {
			return userRepository.findOne(id);
		}

		public void save(User user) {
			user.setEnabled(true);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleRepository.findByName("ROLE_USER"));
			user.setRoles(roles);

			userRepository.save(user);
	}

		@Transactional
		public User findOneWithStudies(int id) {
			User user = findOne(id);
			List<Studies> studies = studiesRepository.findByUser(user);
			
			user.setStudies(studies);
			
			return user;
		}

		@Transactional
		public User findOneWithStudies(String name) {
			User user = userRepository.findByName(name);
			return findOneWithStudies(user.getId());
		}

		public void delete(int id) {
			userRepository.delete(id);
			
		}

		public User findOneByName(String name) {
			User user = userRepository.findByName(name);
			return null;
		}

	

		
	}
