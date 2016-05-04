package com.vmgtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmgtool.entity.QuestionOptions;
import com.vmgtool.repositories.QuestionOptionsRepository;

@Service
public class QuestionOptionService {

	@Autowired
	private QuestionOptionsRepository repository;
	
	public void save(QuestionOptions option){
		repository.save(option);
	}
	
	public void remove(QuestionOptions option){
		repository.delete(option);
	}
	
}
