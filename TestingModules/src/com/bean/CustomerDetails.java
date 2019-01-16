package com.bean;

public class CustomerDetails {
	
		private String insuredName;
		private String agentName;
		private long accountNumber;
		private String businessSegment;
		private long policyNumber;
		private long premiumAmount;
		public String getInsuredName() {
			return insuredName;
		}
		public void setInsuredName(String insuredName) {
			this.insuredName = insuredName;
		}
		public String getAgentName() {
			return agentName;
		}
		public void setAgentName(String agentName) {
			this.agentName = agentName;
		}
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
		public long getPolicyNumber() {
			return policyNumber;
		}
		public void setPolicyNumber(long policyNumber) {
			this.policyNumber = policyNumber;
		}
		public long getPremiumAmount() {
			return premiumAmount;
		}
		public void setPremiumAmount(long premiumAmount) {
			this.premiumAmount = premiumAmount;
		}
		@Override
		public String toString() {
			return "Customers [insuredName=" + insuredName + ", agentName=" + agentName + ", accountNumber=" + accountNumber
					+ ", businessSegment=" + businessSegment + ", policyNumber=" + policyNumber + ", premiumAmount="
					+ premiumAmount + "]";
		}

	}
