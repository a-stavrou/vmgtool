package com.vmgtool.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import flexjson.JSONSerializer;

@Entity
public class Question {

	@Id
	@GeneratedValue
	private Integer id;
	private String title;
	private String description;
	@Lob()
	@Column(name = "QUESTION_IMAGE")

	private byte[] image;

	@Transient
	private String base64Image;

	private Integer type;

	private Integer numberOfOptions;

	private Integer numberOfRules;
	private Integer numberOfPremises;
	private Integer numberOfConclusions;
	private Integer selectedPremises;
	private Integer selectedRules;
	private Integer selectedConclusions;

	@OneToOne(mappedBy = "question", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	private Answer answer;
	
	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ExamAnswer> examAnswers;
	

	public List<ExamAnswer> getExamAnswers() {
		return examAnswers;
	}

	public void setExamAnswers(List<ExamAnswer> examAnswers) {
		this.examAnswers = examAnswers;
	}

	private Integer timeout;

	@ManyToOne
	@JoinColumn(name = "study_id")
	private Studies study;

	@Transient
	private MultipartFile imageUpload;

	@OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<QuestionOptions> questionOptions;

	public MultipartFile getImageUpload() {
		return imageUpload;
	}

	public void setImageUpload(MultipartFile imageUpload) {
		this.imageUpload = imageUpload;
	}

	public Integer getId() {
		return id;
	}

	public List<QuestionOptions> getQuestionOptions() {
		if (this.questionOptions == null)
			this.questionOptions = new ArrayList<QuestionOptions>();
		return questionOptions;
	}

	public void setQuestionOptions(List<QuestionOptions> questionOptions) {
		this.questionOptions = questionOptions;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Studies getStudy() {
		return study;
	}

	public void setStudy(Studies study) {
		this.study = study;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer classic) {
		this.type = classic;
	}

	public String toJson() {
		return new JSONSerializer().include("questionOptions").include("image").exclude("*.class").exclude("study")
				.serialize(this);
	}

	public Integer getNumberOfRules() {
		return numberOfRules;
	}

	public void setNumberOfRules(Integer numberOfRules) {
		this.numberOfRules = numberOfRules;
	}

	public Integer getNumberOfPremises() {
		return numberOfPremises;
	}

	public void setNumberOfPremises(Integer numberOfPremises) {
		this.numberOfPremises = numberOfPremises;
	}

	public Integer getNumberOfConclusions() {
		return numberOfConclusions;
	}

	public void setNumberOfConclusions(Integer numberOfConclusions) {
		this.numberOfConclusions = numberOfConclusions;
	}

	public Integer getNumberOfOptions() {
		return numberOfOptions;
	}

	public void setNumberOfOptions(Integer numberOfOptions) {
		this.numberOfOptions = numberOfOptions;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public String getBase64Image() {
		if (this.image != null){
			this.base64Image = Base64.encodeBase64String(this.image);;
			return base64Image;
		}
		return "";
	}
	
	@Override
	public boolean equals(Object o){
		Question question = (Question)o;
		if(question.getId().equals(this.getId())){
			return true;
		}
		return false;
	}
	@Override
	public int hashCode(){
		return this.getId();
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public Integer getSelectedPremises() {
		return selectedPremises;
	}

	public void setSelectedPremises(Integer selectedPremises) {
		this.selectedPremises = selectedPremises;
	}

	public Integer getSelectedRules() {
		return selectedRules;
	}

	public void setSelectedRules(Integer selectedRules) {
		this.selectedRules = selectedRules;
	}

	public Integer getSelectedConclusions() {
		return selectedConclusions;
	}

	public void setSelectedConclusions(Integer selectedConclusions) {
		this.selectedConclusions = selectedConclusions;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
