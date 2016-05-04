package com.vmgtool.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmgtool.entity.Answer;
import com.vmgtool.entity.Question;
import com.vmgtool.entity.Studies;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer>{

	Answer findByQuestion(Question question);
	
}
