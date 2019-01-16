package com.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.dao.InsuranceQuoteGenerationDao;
import com.dao.InsuranceQuoteGenerationIMPL;
import com.exception.InsuranceQuoteGenerationException;

public class InsuranceQuoteGenerationServiceIMPL implements InsuranceQuoteGenerationService {

	InsuranceQuoteGenerationDao insuranceQuoteGenerationDao = null;;
	InsuranceQuoteGenerationIMPL insuranceQuoteGenerationIMPL = null;

	public String checkUser(String username) throws InsuranceQuoteGenerationException {
		String role = "";
		insuranceQuoteGenerationIMPL = new InsuranceQuoteGenerationIMPL();
		role = insuranceQuoteGenerationIMPL.checkUser(username);
		return role;
	}

	@Override
	public void accountCreation(CreateAccountBean agentBean) throws InsuranceQuoteGenerationException {

		insuranceQuoteGenerationDao = new InsuranceQuoteGenerationIMPL();
		insuranceQuoteGenerationDao.accountCreation(agentBean);
	}

	public void validateAgent(CreateAccountBean agentBean) throws InsuranceQuoteGenerationException {

		List<String> validationErrors = new ArrayList<>();
		if (!(isValidInsuredName(agentBean.getInsuredName()))) {
			validationErrors.add("\nFirst letter of insured name should be capital!!");
		}
		if (!(isValidInsuredStreet(agentBean.getInsuredStreet()))) {
			validationErrors
					.add("\nFirst letter of city name should be capital & should contain min three characters!!");
		}
		if (!(isValidInsuredCity(agentBean.getInsuredCity()))) {
			validationErrors
					.add("\nFirst letter of city name should be capital & should contain min three characters!!");
		}
		if (!(isValidInsuredState(agentBean.getInsuredState()))) {
			validationErrors
					.add("\nFirst letter of state name should be capital & should contain min three characters!!");
		}
		if (!(isValidInsuredZip(agentBean.getInsuredZip()))) {
			validationErrors.add("\nZip code should be 6 character long & it should be numbers!!");
		}
		if (!(isValidAccountNumber(agentBean.getAccountNumber()))) {
			validationErrors.add("\nAccount number should consists 10 digits");
		}

		if (!validationErrors.isEmpty()) {
			try {
				throw new InsuranceQuoteGenerationException(validationErrors + "");
			} catch (InsuranceQuoteGenerationException e) {
				System.err.println(e);
			}
		}
	}

	private boolean isValidAccountNumber(long accountNumber) {

		Pattern accountNumberPattern = Pattern.compile("[1-9][0-9]{9}");
		Matcher accountNumberMatcher = accountNumberPattern.matcher(String.valueOf(accountNumber));
		return accountNumberMatcher.matches();
	}

	private boolean isValidInsuredZip(long insuredZip) {

		Pattern zipPattern = Pattern.compile("[1-9][0-9]{5}");
		Matcher zipMatcher = zipPattern.matcher(String.valueOf(insuredZip));
		return zipMatcher.matches();

	}

	private boolean isValidInsuredState(String insuredState) {

		Pattern statePattern = Pattern.compile("[A-Z][a-z]{3,15}");
		Matcher stateMatcher = statePattern.matcher(insuredState);
		return stateMatcher.matches();
	}

	private boolean isValidInsuredCity(String insuredCity) {

		Pattern cityPattern = Pattern.compile("[A-Z][a-z]{3,15}");
		Matcher cityMatcher = cityPattern.matcher(insuredCity);
		return cityMatcher.matches();
	}

	private boolean isValidInsuredStreet(String insuredStreet) {

		Pattern streetPattern = Pattern.compile("[a-zA-Z0-9]{3,40}");
		Matcher streetMatcher = streetPattern.matcher(insuredStreet);
		return streetMatcher.matches();
	}

	private boolean isValidInsuredName(String insuredName) {

		Pattern namePattern = Pattern.compile("[A-Z][a-z]{1,30}");
		Matcher nameMatcher = namePattern.matcher(insuredName);
		return nameMatcher.matches();
	}

	@Override
	public List<AgentViewPolicyBean> getPolicyDetails(String agentName) throws InsuranceQuoteGenerationException {

		insuranceQuoteGenerationDao = new InsuranceQuoteGenerationIMPL();
		List<AgentViewPolicyBean> policyList = new ArrayList<>();
		policyList = insuranceQuoteGenerationDao.getPolicyDetails(agentName);
		return policyList;
	}

	@Override
	public void policyCreation(PolicyCreationBean agentPolicyCreationBean) throws InsuranceQuoteGenerationException {

		insuranceQuoteGenerationDao = new InsuranceQuoteGenerationIMPL();
		insuranceQuoteGenerationDao.policyCreation(agentPolicyCreationBean);

	}

	@Override
	public void addProfile(ProfileCreation profileCreation) throws InsuranceQuoteGenerationException {

		insuranceQuoteGenerationDao = new InsuranceQuoteGenerationIMPL();

		insuranceQuoteGenerationDao.addProfile(profileCreation);

	}

	@Override
	public List<CustomerDetails> customerDetails() throws InsuranceQuoteGenerationException {
		insuranceQuoteGenerationDao = new InsuranceQuoteGenerationIMPL();
		List<CustomerDetails> array = new ArrayList<>();
		array = insuranceQuoteGenerationDao.customerDetails();
		return array;
	}

	@Override
	public List<QuestionBean> createPolicy(String segment) throws InsuranceQuoteGenerationException {
		insuranceQuoteGenerationDao = new InsuranceQuoteGenerationIMPL();
		List<QuestionBean> list = new ArrayList<>();
		list = insuranceQuoteGenerationDao.createPolicy(segment);

		return list;
	}

	@Override
	public void createNewScheme(NewPolicyBean newPolicySchemeBean) throws InsuranceQuoteGenerationException {
		insuranceQuoteGenerationDao = new InsuranceQuoteGenerationIMPL();
		insuranceQuoteGenerationDao.createNewScheme(newPolicySchemeBean);

	}

	@Override
	public boolean CheckAccount(String username, String business) throws InsuranceQuoteGenerationException {
		insuranceQuoteGenerationDao = new InsuranceQuoteGenerationIMPL();
		boolean businesslist = insuranceQuoteGenerationDao.CheckAccount(username, business);
		return businesslist;
	}

	@Override
	public long policy_Details(PolicyDetails policyDetails) throws InsuranceQuoteGenerationException {
		insuranceQuoteGenerationDao = new InsuranceQuoteGenerationIMPL();
		Long policy_Number = insuranceQuoteGenerationDao.policy_Details(policyDetails);
		return policy_Number;

	}

	@Override
	public String validateUser(LoginBean loginBean) throws InsuranceQuoteGenerationException {
		insuranceQuoteGenerationDao = new InsuranceQuoteGenerationIMPL();
		String role = insuranceQuoteGenerationDao.validateUser(loginBean);
		return role;
	}

	@Override
	public boolean findAgentName(String agentName) throws InsuranceQuoteGenerationException {
		insuranceQuoteGenerationDao = new InsuranceQuoteGenerationIMPL();
		boolean result = insuranceQuoteGenerationDao.findAgentName(agentName);
		return result;
	}

	@Override
	public void businessReport(Business businessValues) throws InsuranceQuoteGenerationException {
		insuranceQuoteGenerationDao = new InsuranceQuoteGenerationIMPL();
		insuranceQuoteGenerationDao.businessReport(businessValues);
	}

	@Override
	public void questionStore(TemporaryData temporaryData) throws InsuranceQuoteGenerationException {
		// TODO Auto-generated method stub
		insuranceQuoteGenerationDao = new InsuranceQuoteGenerationIMPL();
		insuranceQuoteGenerationDao.questionStore(temporaryData);
	}

	@Override
	public void deleteQuestionStore() throws InsuranceQuoteGenerationException {
		insuranceQuoteGenerationDao = new InsuranceQuoteGenerationIMPL();
		insuranceQuoteGenerationDao.deleteQuestionStore();

	}

	@Override
	public List<TemporaryData> getQuestionStore(String username) throws InsuranceQuoteGenerationException {
		List<TemporaryData> temp = new ArrayList<>();
		insuranceQuoteGenerationDao = new InsuranceQuoteGenerationIMPL();
		temp = insuranceQuoteGenerationDao.getQuestionStore(username);
		return temp;
	}

	@Override
	public boolean checkAccountNumber(Long accountNumber) throws InsuranceQuoteGenerationException {
		insuranceQuoteGenerationDao = new InsuranceQuoteGenerationIMPL();
		boolean value = insuranceQuoteGenerationDao.checkAccountNumber(accountNumber);
		return value;
	}

	@Override
	public List<UserViewPolicyBean> getPolicyDetails1(String userName) throws InsuranceQuoteGenerationException {
		insuranceQuoteGenerationDao = new InsuranceQuoteGenerationIMPL();
		List<UserViewPolicyBean> policyList = new ArrayList<>();
		policyList = insuranceQuoteGenerationDao.getPolicyDetails1(userName);
		return policyList;
	}

	@Override
	public List<PolicyDetails> getAllPolicies() throws InsuranceQuoteGenerationException {
		insuranceQuoteGenerationDao = new InsuranceQuoteGenerationIMPL();
		List<PolicyDetails> policyList = new ArrayList<>();
		policyList = insuranceQuoteGenerationDao.getAllPolicies();
		return policyList;
	}

	@Override
	public List<ReportGeneration> getReportGeneration(long accountNumber, long policyNumber)
			throws InsuranceQuoteGenerationException {
		insuranceQuoteGenerationDao = new InsuranceQuoteGenerationIMPL();
		List<ReportGeneration> policyList = new ArrayList<>();
		policyList = insuranceQuoteGenerationDao.getReportGeneration(accountNumber, policyNumber);
		return policyList;
	}

	@Override
	public List<BsReportGeneration> getQuestionsReport(long policyNumber) throws InsuranceQuoteGenerationException {
		insuranceQuoteGenerationDao = new InsuranceQuoteGenerationIMPL();
		List<BsReportGeneration> policyList = new ArrayList<>();
		policyList = insuranceQuoteGenerationDao.getQuestionsReport(policyNumber);
		return policyList;
	}

	@Override
	public boolean checkUserAccount(String username) throws InsuranceQuoteGenerationException {
		insuranceQuoteGenerationDao = new InsuranceQuoteGenerationIMPL();
		boolean result=insuranceQuoteGenerationDao.checkUserAccount(username);
		return result;
	}

}
