package com.vmgtool.repositories;

import java.util.List;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vmgtool.entity.Question;
import com.vmgtool.entity.Studies;


public interface QuestionRepository extends JpaRepository<Question, Integer>{
List<Question> findByStudy(Studies study);



	
}
