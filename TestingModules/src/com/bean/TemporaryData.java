package com.bean;

public class TemporaryData {
	private String question;
	private String answer;
	private int weightage;
	private String userName;
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "TemporaryData [question=" + question + ", answer=" + answer + ", weightage=" + weightage + ", userName="
				+ userName + "]";
	}
	
	

}
