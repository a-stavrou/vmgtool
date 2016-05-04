package com.vmgtool.entity;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class QuestionOptions {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "question_id")
	private Question question;

	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
	}


	private String name;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}



	

}
