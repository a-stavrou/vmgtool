package com.vmgtool.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class Studies {

	@Override
	public String toString() {
		return "Studies [id=" + id + ", studyName=" + studyName + ", studyDesc=" + studyDesc + ", user=" + user
				+ ", question=" + question + "]";
	}

	@Id
	@GeneratedValue
	private Integer id;
	@Size(min = 1, message="Study Name must be at least 5 characters!")
	private String studyName;
	@Size(min = 1, message="Study Description must be at least 5 characters!")
	private String studyDesc;
	private Date createdDate;
	
	private Integer timeout;
	private Boolean random = false;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "study", cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
	private List<Question> question;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStudyName() {
		return studyName;
	}

	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}

	public String getStudyDesc() {
		return studyDesc;
	}

	public void setStudyDesc(String studyDesc) {
		this.studyDesc = studyDesc;
	}

	public List<Question> getQuestion() {
		return question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public Boolean getRandom() {
		return random;
	}

	public void setRandom(Boolean random) {
		this.random = random;
	}

	



	
}
