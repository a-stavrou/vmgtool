package com.vmgtool.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ExamUser {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String experimentalReference;
	private String participantReference;
	
	private String university;
	private String degree;
	private String yearOfStudy;
	
	private String gender;
	private Integer age;
	private String sighBasedDisability;
	
	public String getExperimentalReference() {
		return experimentalReference;
	}
	public void setExperimentalReference(String experimentalReference) {
		this.experimentalReference = experimentalReference;
	}
	public String getParticipantReference() {
		return participantReference;
	}
	public void setParticipantReference(String participantReference) {
		this.participantReference = participantReference;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getYearOfStudy() {
		return yearOfStudy;
	}
	public void setYearOfStudy(String yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSighBasedDisability() {
		return sighBasedDisability;
	}
	public void setSighBasedDisability(String sighBasedDisability) {
		this.sighBasedDisability = sighBasedDisability;
	}	

}
