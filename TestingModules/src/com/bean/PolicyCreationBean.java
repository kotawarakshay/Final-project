package com.bean;

public class PolicyCreationBean {

	private String businessSegment;
	private String question;
	private String answer;
	private int   weightage;
	private Long policyNumber;
	
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
	public Long getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(Long policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getWeightage() {
		return weightage;
	}
	public void setWeightage(int weightage) {
		this.weightage = weightage;
	}

	@Override
	public String toString() {
		return "PolicyCreationBean [businessSegment=" + businessSegment + ", question=" + question + ", answer="
				+ answer + ", weightage=" + weightage + ", policyNumber=" + policyNumber + "]";
	}
	 
	 
}
