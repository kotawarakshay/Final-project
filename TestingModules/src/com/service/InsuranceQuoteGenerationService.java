package com.service;



import java.util.List;

import com.bean.CreateAccountBean;
import com.bean.PolicyCreationBean;
import com.bean.QuestionBean;
import com.bean.ReportGeneration;
import com.bean.TemporaryData;
import com.bean.UserViewPolicyBean;
import com.bean.AgentViewPolicyBean;
import com.bean.BsReportGeneration;
import com.bean.Business;
import com.bean.CustomerDetails;
import com.bean.LoginBean;
import com.bean.NewPolicyBean;
import com.bean.PolicyDetails;
import com.bean.ProfileCreation;
import com.exception.InsuranceQuoteGenerationException;

public interface InsuranceQuoteGenerationService {

	public void accountCreation(CreateAccountBean createAccountBean) throws InsuranceQuoteGenerationException;

	public List<AgentViewPolicyBean> getPolicyDetails(String agentName) throws InsuranceQuoteGenerationException;

	public void policyCreation(PolicyCreationBean PolicyCreationBean) throws InsuranceQuoteGenerationException;

	public void addProfile(ProfileCreation profileCreation) throws InsuranceQuoteGenerationException;

	public List<CustomerDetails> customerDetails() throws InsuranceQuoteGenerationException;

	public List<QuestionBean> createPolicy(String segment) throws InsuranceQuoteGenerationException;

	public void createNewScheme(NewPolicyBean newPolicySchemeBean) throws InsuranceQuoteGenerationException;

	public String checkUser(String username) throws InsuranceQuoteGenerationException;

	public boolean CheckAccount(String username, String business_Segment) throws InsuranceQuoteGenerationException;

	public long policy_Details(PolicyDetails policyDetails) throws InsuranceQuoteGenerationException;

	public String validateUser(LoginBean loginBean) throws InsuranceQuoteGenerationException;

	public boolean findAgentName(String agentName) throws InsuranceQuoteGenerationException;

	public void businessReport(Business businessValues) throws InsuranceQuoteGenerationException;

	public void questionStore(TemporaryData temporaryData) throws InsuranceQuoteGenerationException;

	public void deleteQuestionStore() throws InsuranceQuoteGenerationException;

	public List<TemporaryData> getQuestionStore(String username) throws InsuranceQuoteGenerationException;

	public boolean checkAccountNumber(Long accountNumber) throws InsuranceQuoteGenerationException;

	public List<UserViewPolicyBean> getPolicyDetails1(String userName) throws InsuranceQuoteGenerationException;

	public List<PolicyDetails> getAllPolicies() throws InsuranceQuoteGenerationException;

	public List<ReportGeneration> getReportGeneration(long accountNumber, long policyNumber)
			throws InsuranceQuoteGenerationException;

	public List<BsReportGeneration> getQuestionsReport(long policyNumber) throws InsuranceQuoteGenerationException;
	
	public boolean checkUserAccount(String username)throws InsuranceQuoteGenerationException;

}
