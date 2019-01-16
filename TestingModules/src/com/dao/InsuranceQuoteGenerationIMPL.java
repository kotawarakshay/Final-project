package com.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

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
import com.ui.Client;
import com.util.DBConnection;

public class InsuranceQuoteGenerationIMPL implements InsuranceQuoteGenerationDao {
	static Logger logger = Logger.getLogger(Client.class); 
	static Connection connection = null;
	static PreparedStatement preparedStatement = null;
	static ResultSet resultset = null;
	static CreateAccountBean agentBean = null;

	

	@Override
	public void accountCreation(CreateAccountBean createAccountBean) throws InsuranceQuoteGenerationException {
		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(QueryMapper.INSERT_QUERY);

			preparedStatement.setString(1, createAccountBean.getUsername());
			preparedStatement.setString(2, createAccountBean.getInsuredName());
			preparedStatement.setString(3, createAccountBean.getInsuredStreet());
			preparedStatement.setString(4, createAccountBean.getInsuredCity());
			preparedStatement.setString(5, createAccountBean.getInsuredState());
			preparedStatement.setLong(6, createAccountBean.getInsuredZip());
			preparedStatement.setString(7, createAccountBean.getBusinessSegment());
			preparedStatement.setLong(8, createAccountBean.getAccountNumber());
			preparedStatement.setString(9, createAccountBean.getAgentName());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public boolean CheckAccount(String username, String business) throws InsuranceQuoteGenerationException {
		ResultSet rs = null;
		boolean result = false;
		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(
					"select business_Segment from businessreport where username=? and business_Segment=?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, business);
			rs = preparedStatement.executeQuery();
			result = rs.next();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;

	}

	public boolean findAgentName(String agentName) throws InsuranceQuoteGenerationException {
		boolean validation = false;
		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(QueryMapper.FIND_AGENT_NAME);
			preparedStatement.setString(1, agentName);
			resultset = preparedStatement.executeQuery();

			validation = resultset.next();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return validation;

	}

	@Override
	public List<AgentViewPolicyBean> getPolicyDetails(String agentName) throws InsuranceQuoteGenerationException {
		List<AgentViewPolicyBean> policyList = new ArrayList<>();
		try {
			connection = DBConnection.getConnection();
			AgentViewPolicyBean agentViewPolicyBean = null;

			preparedStatement = connection.prepareStatement(QueryMapper.VIEW_POLICY_FOR_ADMIN);

			preparedStatement.setString(1, agentName);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {

				agentViewPolicyBean = new AgentViewPolicyBean();

				agentViewPolicyBean.setInsuredName(resultset.getString(1));
				agentViewPolicyBean.setAccountNumber(resultset.getLong(2));
				agentViewPolicyBean.setBusinessSegment(resultset.getString(3));

				policyList.add(agentViewPolicyBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return policyList;

	}

	@Override
	public void policyCreation(PolicyCreationBean policyCreationBean) throws InsuranceQuoteGenerationException {
		// System.out.println(policyCreationBean);
		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(QueryMapper.CREATE_POLICY);
			preparedStatement.setString(1, policyCreationBean.getQuestion());
			preparedStatement.setString(2, policyCreationBean.getAnswer());
			preparedStatement.setInt(3, policyCreationBean.getWeightage());
			preparedStatement.setLong(4, policyCreationBean.getPolicyNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String checkUser(String username) throws InsuranceQuoteGenerationException {
		String role = null;
		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(QueryMapper.FIND_ROLE);
			preparedStatement.setString(1, username);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				role = resultset.getString(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return role;
	}

	@Override
	public void addProfile(ProfileCreation profileCreation) throws InsuranceQuoteGenerationException {

		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(QueryMapper.CREATE_PROFILE);

			preparedStatement.setString(1, profileCreation.getUserName());
			preparedStatement.setString(2, profileCreation.getPassword());
			preparedStatement.setString(3, profileCreation.getRoleCode());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<CustomerDetails> customerDetails() throws InsuranceQuoteGenerationException {
		Connection con = null;
		ResultSet resultset = null;
		Statement st = null;
		List<CustomerDetails> a3 = new ArrayList<CustomerDetails>();
		try {
			con = DBConnection.getConnection();
			st = con.createStatement();
			resultset = st.executeQuery(
					"select c.agent_name,c.insured_name,a.policy_number,b.business_segment,a.account_number,a.premium from policyDetails a,businessReport b, accountdetails c where c.account_number=a.account_number and  a.policy_number=b.policy_number ");

			while (resultset.next()) {
				CustomerDetails customerdetails = new CustomerDetails();

				customerdetails.setAgentName(resultset.getString(1));

				customerdetails.setInsuredName(resultset.getString(2));
				customerdetails.setPolicyNumber(resultset.getLong(3));
				customerdetails.setBusinessSegment(resultset.getString(4));
				customerdetails.setAccountNumber(resultset.getLong(5));

				customerdetails.setPremiumAmount(resultset.getLong(6));

				a3.add(customerdetails);

				a3.add(customerdetails);

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// PreparedStatement ps=null;

		return a3;
	}

	@Override
	public List<QuestionBean> createPolicy(String businessSegment) throws InsuranceQuoteGenerationException {
		ArrayList<QuestionBean> al = new ArrayList<>();
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = null;
			ResultSet resultset = null;
			ps = con.prepareStatement("select * from policycreation where business_segment=?");
			ps.setString(1, businessSegment);
			resultset = ps.executeQuery();

			while (resultset.next()) {
				QuestionBean policyBean = new QuestionBean();
				policyBean.setBusinessSegment(resultset.getString(1));
				policyBean.setQuestion(resultset.getString(2));
				policyBean.setAnswer1(resultset.getString(3));
				policyBean.setWeightage1(resultset.getInt(4));
				policyBean.setAnswer2(resultset.getString(5));
				policyBean.setWeightage2(resultset.getInt(6));
				policyBean.setAnswer3(resultset.getString(7));
				policyBean.setWeightage3(resultset.getInt(8));
				al.add(policyBean);
			}

		} catch (Exception e) {
			System.out.println("here");
		}

		return al;
	}

	@Override
	public void createNewScheme(NewPolicyBean newPolicySchemeBean) throws InsuranceQuoteGenerationException {
		Connection con;
		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = null;
			ps = con.prepareStatement("insert into policycreation values(?,?,?,?,?,?,?,?)");
			ps.setString(1, newPolicySchemeBean.getBus_seg_name());
			ps.setString(2, newPolicySchemeBean.getPol_ques_desc());
			ps.setString(3, newPolicySchemeBean.getPol_ques_ans1());
			ps.setInt(4, newPolicySchemeBean.getPol_ques_ans1_weightage());

			ps.setString(5, newPolicySchemeBean.getPol_ques_ans2());
			ps.setInt(6, newPolicySchemeBean.getPol_ques_ans2_weightage());
			ps.setString(7, newPolicySchemeBean.getPol_ques_ans3());
			ps.setInt(8, newPolicySchemeBean.getPol_ques_ans3_weightage());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Long policy_Details(PolicyDetails policyDetails) throws InsuranceQuoteGenerationException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultset = null;
		Statement st = null;
		long policyNumber = 0;
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement("insert into policyDetails values(?,?,?)");
			ps.setInt(1, policyDetails.getPremium());
			ps.setLong(2, policyDetails.getPolicyNumber());

			ps.setLong(3, policyDetails.getAccountNumber());
			ps.executeUpdate();
			st = con.createStatement();
			resultset = st.executeQuery("select policy_number from policyDetails where account_number=' "
					+ policyDetails.getAccountNumber() + "'");
			if (resultset.next())
				policyNumber = resultset.getLong(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return policyNumber;
	}

	@Override
	public String validateUser(LoginBean loginBean) throws InsuranceQuoteGenerationException {
		String role = null;
		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(QueryMapper.VALIDATE_USER);
			preparedStatement.setString(1, loginBean.getUserName());
			preparedStatement.setString(2, loginBean.getPassword());
			resultset = preparedStatement.executeQuery();

			if (resultset.next())
				role = resultset.getString(1);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return role;
	}

	@Override
	public void businessReport(Business businessValues) throws InsuranceQuoteGenerationException {
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = null;

			ps = con.prepareStatement("insert into businessReport values(?,?,?)");
			ps.setLong(1, businessValues.getPolicyNumber());
			ps.setString(2, businessValues.getUsername());
			ps.setString(3, businessValues.getBusiness_Segment());

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void questionStore(TemporaryData temporaryData) throws InsuranceQuoteGenerationException {

		Connection con;
		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = null;
			ps = con.prepareStatement("insert into temporarydata values(?,?,?,?)");
			ps.setString(1, temporaryData.getQuestion());
			ps.setString(2, temporaryData.getAnswer());
			ps.setInt(3, temporaryData.getWeightage());
			ps.setString(4, temporaryData.getUserName());

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<TemporaryData> getQuestionStore(String username) throws InsuranceQuoteGenerationException {
		Connection con = null;
		List<TemporaryData> forList = new ArrayList<>();
		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;

			ps = con.prepareStatement("Select question,answer,weightage from temporarydata where username=?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				TemporaryData tempdata = new TemporaryData();
				tempdata.setQuestion((rs.getString(1)));
				tempdata.setAnswer(rs.getString(2));
				tempdata.setWeightage(rs.getInt(3));

				forList.add(tempdata);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return forList;
	}

	@Override
	public void deleteQuestionStore() throws InsuranceQuoteGenerationException {
		Connection con;
		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("truncate table temporarydata");
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean checkAccountNumber(Long accountNumber) throws InsuranceQuoteGenerationException {
		Connection con = null;
		boolean result = false;
		try {
			con = DBConnection.getConnection();
			ResultSet resultSet = null;

			PreparedStatement preparedStatement = con
					.prepareStatement("select account_number from accountdetails where account_number=?");
			preparedStatement.setLong(1, accountNumber);
			preparedStatement.executeQuery();
			resultSet = preparedStatement.executeQuery();
			result = resultSet.next();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<UserViewPolicyBean> getPolicyDetails1(String userName) throws InsuranceQuoteGenerationException {
		List<UserViewPolicyBean> policyList = new ArrayList<>();
		try {
			connection = DBConnection.getConnection();
			UserViewPolicyBean agentViewPolicyBean = null;
			preparedStatement = connection.prepareStatement(QueryMapper.VIEW_POLICY_DETAILS_QUERY);
			preparedStatement.setString(1, userName);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				agentViewPolicyBean = new UserViewPolicyBean();

				agentViewPolicyBean.setPolicyNumber(resultset.getLong(1));
				agentViewPolicyBean.setBusiness_Segement(resultset.getString(2));
				agentViewPolicyBean.setAccountNumber(resultset.getLong(3));
				agentViewPolicyBean.setPolicyPremium(resultset.getDouble(4));

				policyList.add(agentViewPolicyBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return policyList;

	}

	@Override
	public List<PolicyDetails> getAllPolicies() throws InsuranceQuoteGenerationException {
		List<PolicyDetails> policyList = new ArrayList<>();
		try {
			connection = DBConnection.getConnection();
			PolicyDetails policyDetails = null;

			preparedStatement = connection.prepareStatement("select * from policyDetails");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				policyDetails = new PolicyDetails();
				policyDetails.setAccountNumber(resultSet.getLong(1));
				policyDetails.setPolicyNumber(resultSet.getLong(2));
				policyDetails.setPremium(resultSet.getInt(3));
				policyList.add(policyDetails);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return policyList;
	}

	@Override
	public List<ReportGeneration> getReportGeneration(long accountNumber, long policyNumber)
			throws InsuranceQuoteGenerationException {
		List<ReportGeneration> reportList = new ArrayList<>();
		try {
			connection = DBConnection.getConnection();
			ReportGeneration reportGeneration = null;
			preparedStatement = connection.prepareStatement(
					"select a.insured_name, a.insured_street,a.insured_city,a.insured_state,a.insured_zip,b.business_segment from accountDetails a, businessReport b, policyDetails c where a.account_number=? and b.policy_number=? and c.policy_number=b.policy_number ");
			preparedStatement.setLong(1, accountNumber);
			preparedStatement.setLong(2, policyNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				reportGeneration = new ReportGeneration();
				reportGeneration.setInsure_name(resultSet.getString(1));
				reportGeneration.setInsured_street(resultSet.getString(2));
				reportGeneration.setInsured_city(resultSet.getString(3));
				reportGeneration.setInsured_state(resultSet.getString(4));
				reportGeneration.setZip(resultSet.getLong(5));
				reportGeneration.setBusiness_segment(resultSet.getString(6));
				reportList.add(reportGeneration);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reportList;
	}

	@Override
	public List<BsReportGeneration> getQuestionsReport(long policyNumber) throws InsuranceQuoteGenerationException {
		List<BsReportGeneration> reportList = new ArrayList<>();
		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(
					"select question,answer,weightage,policynumber from reportgeneration where policynumber=? ");
			preparedStatement.setLong(1, policyNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			BsReportGeneration bsreportGeneration = null;

			while (resultSet.next()) {
				bsreportGeneration = new BsReportGeneration();
				bsreportGeneration.setQuestion(resultSet.getString(1));
				bsreportGeneration.setAnswer(resultSet.getString(2));
				bsreportGeneration.setWeightage(resultSet.getInt(3));
				bsreportGeneration.setPolicynumber(resultSet.getLong(4));
				reportList.add(bsreportGeneration);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reportList;

	}
	
	public boolean checkUserAccount(String username)throws InsuranceQuoteGenerationException {
		boolean check=false;
		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(
					"select username from accountdetails where username=? ");
			preparedStatement.setString(1,username);
			ResultSet resultSet = preparedStatement.executeQuery();
			check=resultSet.next();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

}
