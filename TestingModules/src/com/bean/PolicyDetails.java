package com.bean;

public class PolicyDetails {
private long policyNumber;
private long accountNumber;
private int premium;
public long getPolicyNumber() {
	return policyNumber;
}
public void setPolicyNumber(long policyNumber) {
	this.policyNumber = policyNumber;
}
public long getAccountNumber() {
	return accountNumber;
}
public void setAccountNumber(long accountNumber) {
	this.accountNumber = accountNumber;
}
public int getPremium() {
	return premium;
}

public void setPremium(int premium) {
	this.premium = premium;
}
@Override
public String toString() {
	return "PolicyDetails [policyNumber=" + policyNumber + ", accountNumber=" + accountNumber + ", premium=" + premium
			+ "]";
}
}
