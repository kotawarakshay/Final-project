package com.bean;

public class QuestionBean {

	 private String businessSegment;
	 private String question;
	 private String answer1;
	 private String answer2;
	 private String answer3;
	 private int weightage1;
	 private int weightage2;
	 private int weightage3;
	 
	public String getBusinessSegment() {
		return businessSegment;
	}
	public void setBusinessSegment(String businessSegment) {
		this.businessSegment = businessSegment;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	public int getWeightage1() {
		return weightage1;
	}
	public void setWeightage1(int weightage1) {
		this.weightage1 = weightage1;
	}
	public int getWeightage2() {
		return weightage2;
	}
	public void setWeightage2(int weightage2) {
		this.weightage2 = weightage2;
	}
	public int getWeightage3() {
		return weightage3;
	}
	public void setWeightage3(int weightage3) {
		this.weightage3 = weightage3;
	}
	@Override
	public String toString() {
		return "AgentPolicyCreationBean [businessSegment=" + businessSegment + ", question=" + question + ", answer1="
				+ answer1 + ", answer2=" + answer2 + ", answer3=" + answer3 + ", weightage1=" + weightage1
				+ ", weightage2=" + weightage2 + ", weightage3=" + weightage3 + "]";
	}
	 
	 
}
