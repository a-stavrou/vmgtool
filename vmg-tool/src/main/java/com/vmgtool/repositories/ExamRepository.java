package com.vmgtool.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmgtool.entity.Exam;
import com.vmgtool.entity.Studies;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer>{
	List<Exam> findByStudy(Studies study);
}
