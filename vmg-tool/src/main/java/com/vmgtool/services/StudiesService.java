package com.vmgtool.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmgtool.entity.Question;
import com.vmgtool.entity.Studies;
import com.vmgtool.entity.User;
import com.vmgtool.repositories.QuestionRepository;
import com.vmgtool.repositories.StudiesRepository;
import com.vmgtool.repositories.UserRepository;

@Service
public class StudiesService {

	@Autowired
	private StudiesRepository studiesRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private QuestionRepository questionRepository;

	public void save(Studies study, String name) {
		User user = userRepository.findByName(name);
		System.out.println(name);
		study.setUser(user);
		studiesRepository.save(study);
		System.out.println(study);

	}
	
	public void save(Studies study) {
		studiesRepository.save(study);
	}

	public void delete(Studies study) {
		studiesRepository.delete(study);
		
	}

	public void findStudy(int id) {
		studiesRepository.findOne(id);
		System.out.println(id);
		
	}

	public List<Studies> findAll() {
			return studiesRepository.findAll();
		}

	public Studies findOne(int id) {
		return studiesRepository.findOne(id);
	}

	public Studies findOneWithQuestions(int id) {
		Studies study = findOne(id);
		List<Question> questions = questionRepository.findByStudy(study);
		study.setQuestion(questions);
		
		return study;
	}
	

	
}