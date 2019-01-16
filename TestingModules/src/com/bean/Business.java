package com.bean;

public class Business {
private String business_Segment;
private String username;
private Long PolicyNumber;

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public Long getPolicyNumber() {
	return PolicyNumber;
}

public void setPolicyNumber(Long policyNumber) {
	this.PolicyNumber = policyNumber;
}

public String getBusiness_Segment() {
	return business_Segment;
}

public void setBusiness_Segment(String business_Segment) {
	this.business_Segment = business_Segment;
}

}
