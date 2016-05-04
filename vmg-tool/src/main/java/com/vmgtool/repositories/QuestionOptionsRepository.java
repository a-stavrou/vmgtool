package com.vmgtool.repositories;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vmgtool.entity.Question;
import com.vmgtool.entity.QuestionOptions;

public interface QuestionOptionsRepository extends JpaRepository<QuestionOptions, Integer>{

	List<QuestionOptions> findByQuestion(Question question, Pageable pageable);

}
