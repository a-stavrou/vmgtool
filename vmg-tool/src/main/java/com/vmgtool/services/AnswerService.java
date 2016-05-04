package com.vmgtool.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmgtool.entity.Answer;
import com.vmgtool.entity.Question;
import com.vmgtool.repositories.AnswerRepository;

@Service
public class AnswerService {

	@Autowired
	private AnswerRepository answerRepository;
	
	public void delete(Answer answer){
		answerRepository.delete(answer);
	}
	
	public Answer save(Answer answer){
		return answerRepository.save(answer);
	}
	
	public Answer findOne(Integer id){
		return answerRepository.findOne(id);
	}
	
	public Answer findByQuestion(Question question){
		return answerRepository.findByQuestion(question);

	}
}
