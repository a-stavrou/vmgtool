package com.vmgtool.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Exam {

	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.PERSIST)
	@JoinColumn(name = "examUser")
	private ExamUser examUser;
	
	@OneToMany(mappedBy = "exam", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ExamAnswer> examAnswers;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "study")
	private Studies study;
	
	private Integer correctAnswers;
	
	private Integer wrongAnswers;
	
	private Integer emptyAnswers;
	
	private Boolean timeout;
	
	private Date createDate;

	public ExamUser getExamUser() {
		return examUser;
	}

	public void setExamUser(ExamUser examUser) {
		this.examUser = examUser;
	}

	public Studies getStudy() {
		return study;
	}

	public void setStudy(Studies study) {
		this.study = study;
	}

	public Integer getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(Integer correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public Integer getWrongAnswers() {
		return wrongAnswers;
	}

	public void setWrongAnswers(Integer wrongAnswers) {
		this.wrongAnswers = wrongAnswers;
	}

	public Integer getEmptyAnswers() {
		return emptyAnswers;
	}

	public void setEmptyAnswers(Integer emptyAnswers) {
		this.emptyAnswers = emptyAnswers;
	}

	public Boolean getTimeout() {
		return timeout;
	}

	public void setTimeout(Boolean timeout) {
		this.timeout = timeout;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ExamAnswer> getExamAnswers() {
		return examAnswers;
	}

	public void setExamAnswers(List<ExamAnswer> examAnswers) {
		this.examAnswers = examAnswers;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
