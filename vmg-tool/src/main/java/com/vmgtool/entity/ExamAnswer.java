package com.vmgtool.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ExamAnswer {

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "exam_id")
	private Exam exam;
	

	@ManyToOne
	@JoinColumn(name = "question")
	private Question question;

	private Integer selectedPremise;
	private Integer selectedConclusion;
	private Integer selectedRule;
	private String optionsDescriptions;
	private Integer elapsedTime;
    private Date createdDate;
	
	private String correct;
	

	private String selectedOptionValue;

	@OneToMany(mappedBy = "examAnswer", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ExamAnswerOptions> examAnswerOptions;
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Integer getSelectedPremise() {
		return selectedPremise;
	}

	public void setSelectedPremise(Integer selectedPremise) {
		this.selectedPremise = selectedPremise;
	}

	public Integer getSelectedConclusion() {
		return selectedConclusion;
	}

	public void setSelectedConclusion(Integer selectedConclusion) {
		this.selectedConclusion = selectedConclusion;
	}

	public Integer getSelectedRule() {
		return selectedRule;
	}

	public void setSelectedRule(Integer selectedRule) {
		this.selectedRule = selectedRule;
	}

	public String getSelectedOptionValue() {
		return selectedOptionValue;
	}

	public void setSelectedOptionValue(String selectedOptionValue) {
		this.selectedOptionValue = selectedOptionValue;
	}
	

	public List<ExamAnswerOptions> getExamAnswerOptions() {
		return examAnswerOptions;
	}

	public void setExamAnswerOptions(List<ExamAnswerOptions> examAnswerOptions) {
		this.examAnswerOptions = examAnswerOptions;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}

	public String getOptionsDescriptions() {
		String resume= "";
		if(this.getExamAnswerOptions() != null){
			for(ExamAnswerOptions opt : this.getExamAnswerOptions()){
				resume+=opt.getName()+" <br/>";
			}
		}
		return resume;
	}

	@Override
	public boolean equals(Object o){
		ExamAnswer examAnswer = (ExamAnswer)o;
		
		if(examAnswer.getId().equals(this.getId())){
			return true;
		}
		return false;
	}
	@Override
	public int hashCode(){
		return this.getId();
	}
	public void setOptionsDescriptions(String optionsDescriptions) {
		this.optionsDescriptions = optionsDescriptions;
	}

	public Integer getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(Integer elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

}
