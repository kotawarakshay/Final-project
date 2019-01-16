package com.bean;

public class AgentViewPolicyBean {
	private long accountNumber;
	private String businessSegment;
	private String insuredName;
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBusinessSegment() {
		return businessSegment;
	}
	public void setBusinessSegment(String businessSegment) {
		this.businessSegment = businessSegment;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	
	@Override
	public String toString() {
		return "Your Customer's Policy details:  accountNumber=" + accountNumber + ",insured name="+insuredName+", busniess segment="+businessSegment+"";
	}
	
	
}
