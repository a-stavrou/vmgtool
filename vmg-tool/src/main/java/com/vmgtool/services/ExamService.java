package com.vmgtool.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmgtool.entity.Exam;
import com.vmgtool.entity.Studies;
import com.vmgtool.repositories.ExamAnswerRepository;
import com.vmgtool.repositories.ExamRepository;

@Service
public class ExamService {

	@Autowired
	private ExamRepository examRepository;
	
	@Autowired
	private ExamAnswerRepository examAnswerRepository;
	
	public void save(Exam exam){
		examRepository.save(exam);
	}
	
	public Exam findOne(Integer id){
		Exam exam = examRepository.findOne(id);
		return exam;
	}

	public List<Exam> findAll(){
		return examRepository.findAll();
	}
	
	public List<Exam> findByStudy(Studies study){
		return examRepository.findByStudy(study);
	}

}
