package com.vmgtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmgtool.entity.Question;
import com.vmgtool.entity.Studies;
import com.vmgtool.entity.User;
import com.vmgtool.repositories.QuestionRepository;
import com.vmgtool.repositories.StudiesRepository;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private StudiesRepository studiesRepository;

	public Question findOne(int id) {
		return questionRepository.findOne(id);
	}
	public Question save(Question question) {
	
	    Studies study = question.getStudy();
	    question.setStudy(study);
		return questionRepository.save(question);

	}
	
	public void delete(Question question) {
		questionRepository.delete(question);
		
	}

}
