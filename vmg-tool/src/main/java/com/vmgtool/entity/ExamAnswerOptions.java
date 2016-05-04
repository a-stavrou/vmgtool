package com.vmgtool.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ExamAnswerOptions {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "examanswer_id")
	private ExamAnswer examAnswer;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ExamAnswer getExamAnswer() {
		return examAnswer;
	}

	public void setExamAnswer(ExamAnswer examAnswer) {
		this.examAnswer = examAnswer;
	}

}
