package com.bean;

public class BsReportGeneration {
private String question;
private  String answer;
private int weightage;
private long policynumber;

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
public long getPolicynumber() {
	return policynumber;
}
public void setPolicynumber(long policynumber) {
	this.policynumber = policynumber;
}
@Override
public String toString() {
	return "BsReportGeneration [question=" + question + ", answer=" + answer + ", weightage=" + weightage
			+ ", policynumber=" + policynumber + "]";
}
}
