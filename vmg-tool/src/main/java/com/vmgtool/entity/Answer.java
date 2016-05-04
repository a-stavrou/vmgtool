package com.vmgtool.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Answer {

	@Id
	@GeneratedValue
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question")
	private Question question;

	private Integer selectedPremise;
	private Integer selectedConclusion;
	private Integer selectedRule;
	private String selectedOptionNumber;
	private String selectedOptionValue;
	private String selectedCheckBoxes;
	private String optionsDescriptions;

	@Transient
	private Integer elapsedTime;

	@OneToMany(mappedBy = "answer", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AnswerOptions> answerOptions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<AnswerOptions> getAnswerOptions() {
		return answerOptions;
	}

	public void setAnswerOptions(List<AnswerOptions> answerOptions) {
		this.answerOptions = answerOptions;
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

	public String getSelectedOptionNumber() {
		return selectedOptionNumber;
	}

	public void setSelectedOptionNumber(String selectedOptionNumber) {
		this.selectedOptionNumber = selectedOptionNumber;
	}

	public String getSelectedCheckBoxes() {
		return selectedCheckBoxes;
	}

	public void setSelectedCheckBoxes(String selectedCheckBoxes) {
		this.selectedCheckBoxes = selectedCheckBoxes;
	}

	public Integer getElapsedTime() {
		if(elapsedTime == null)
			return 0;
		return elapsedTime;
	}

	public void setElapsedTime(Integer elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public String getOptionsDescriptions() {
		return optionsDescriptions;
	}
	public void setOptionsDescriptions(String optionsDescriptions) {
		this.optionsDescriptions = optionsDescriptions;
	}

}
