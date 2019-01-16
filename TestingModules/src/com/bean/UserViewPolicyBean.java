package com.bean;

public class UserViewPolicyBean {

	private long policyNumber;
	private double policyPremium;
	private long accountNumber;
	private String business_Segement;
	public String getBusiness_Segement() {
		return business_Segement;
	}
	public void setBusiness_Segement(String business_Segement) {
		this.business_Segement = business_Segement;
	}

	public long getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(long policyNumber) {
		this.policyNumber = policyNumber;
	}
	public double getPolicyPremium() {
		return policyPremium;
	}
	public void setPolicyPremium(double policyPremium) {
		this.policyPremium = policyPremium;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Override
	public String toString() {
		return "Your Policy details:  policyNumber="
				+ policyNumber + ", policyPremium=" + policyPremium + ", accountNumber=" + accountNumber + ", busniess segment="+business_Segement+"";
	}
	
	
}
