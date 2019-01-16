package com.ui;

import java.io.BufferedReader;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.bean.CreateAccountBean;
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
import com.bean.PolicyCreationBean;
import com.exception.InsuranceQuoteGenerationException;
import com.service.InsuranceQuoteGenerationService;
import com.service.InsuranceQuoteGenerationServiceIMPL;

public class Client {

	static InsuranceQuoteGenerationService insuranceQuoteGenerationService = null;
	static Scanner scanner = new Scanner(System.in);
	static Logger logger = Logger.getLogger(Client.class); 
	public static void main(String[] args) {
		PropertyConfigurator.configure("resources/log4j.properties");
		logger.info("log4j file loaded...");
		logger.info("inside main method"); 
		LoginBean loginBean = new LoginBean();
		System.out.println("Welcome to Insurance Quote Generation Application");
		System.out.println("___________________________________________________");
		System.out.println("Enter the Username:");
		String username = scanner.next();
		loginBean.setUserName(username);
		System.out.println("Enter the Password");
		loginBean.setPassword(scanner.next());
		String role = null;
		insuranceQuoteGenerationService = new InsuranceQuoteGenerationServiceIMPL();
		try {

			role = insuranceQuoteGenerationService.validateUser(loginBean);
		} catch (InsuranceQuoteGenerationException insuranceQuoteGenerationException) {
			System.out.println(insuranceQuoteGenerationException);
		}

		if (role.equals("Admin") || role.equals("admin")) {
			while (true) {
				System.out.println("______________________");
				System.out.println("Welcome Underwriter");
				System.out.println("_______________________");
				System.out.println("1. New Profile Creation");
				System.out.println("2. Account Creation");
				System.out.println("3. Policy Creation");
				System.out.println("4. View Policy");
				System.out.println("5. Report Generation");
				System.out.println("6. Exit");
				System.out.println("Enter your choice");
				int adminChoice = scanner.nextInt();
				switch (adminChoice) {
				case 1:
					System.out.println("___________________________________");
					System.out.println("Enter Details for Creating profile");
					System.out.println("____________________________________");

					try {
						System.out.println(populateProfileCreation());
					} catch (InsuranceQuoteGenerationException insuranceQuoteGenerationException) {
						System.out.println(insuranceQuoteGenerationException);
					}

					break;
				case 2:

					try {
						populateAgentBean(username);
					} catch (InsuranceQuoteGenerationException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} 

					break;

				case 3:
					System.out.println("Welcome Admin");
					System.out.println("New Scheme Creation Page");
					System.out.println("how many policy question your scheme has");
					int questionNumber = scanner.nextInt();
					NewPolicyBean newPolicySchemeBean = new NewPolicyBean();
					insuranceQuoteGenerationService = new InsuranceQuoteGenerationServiceIMPL();

					while (questionNumber == 0) {
						newPolicySchemeBean = policyCreation();
						questionNumber--;
						try {
							insuranceQuoteGenerationService.createNewScheme(newPolicySchemeBean);
						} catch (InsuranceQuoteGenerationException e) {
							e.printStackTrace();
						}
					}

					System.out.println("New Policy Scheme Created Successfully");

					break;

				case 4:
					System.out.println("In view Policy");
					System.out.println("1.Check Your Customers");
					System.out.println("2. Check customer details");

					int option = 0;
					option = scanner.nextInt();

					switch (option) {
					case 1:
						List<AgentViewPolicyBean> policyDetails = new ArrayList<>();
						insuranceQuoteGenerationService = new InsuranceQuoteGenerationServiceIMPL();
						try {
							policyDetails = insuranceQuoteGenerationService.getPolicyDetails(username);
							for (AgentViewPolicyBean agentViewPolicyBean : policyDetails) {
								System.out.println(agentViewPolicyBean);
							}

						} catch (InsuranceQuoteGenerationException insuranceQuoteGenerationException) {
							// TODO Auto-generated catch block
							System.out.println(insuranceQuoteGenerationException);
						}
						break;

					case 2:
						insuranceQuoteGenerationService = new InsuranceQuoteGenerationServiceIMPL();

						List<CustomerDetails> arrayList1 = new ArrayList<CustomerDetails>();

						try {
							arrayList1 = insuranceQuoteGenerationService.customerDetails();
						} catch (InsuranceQuoteGenerationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						for (CustomerDetails customerdetails : arrayList1) {

							System.out.println(" " + customerdetails);
						}

					}
					break;
				case 5:
					System.out.println("Report Generation");

					List<PolicyDetails> policyList = new ArrayList<>();
					try {
						policyList = insuranceQuoteGenerationService.getAllPolicies();
					} catch (InsuranceQuoteGenerationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int number = 0;

					for (PolicyDetails policy : policyList) {

						number++;
						System.out.println(number + " " + policy);
					}
					PolicyDetails policy = new PolicyDetails();
					System.out.println("enter the choice");
					int value = scanner.nextInt();
					for (int i = 0; i < policyList.size(); i++) {
						if (i == (value - 1)) {
							policy = policyList.get(i);
							System.out.println(policyList.get(i).getAccountNumber());
							System.out.println(policyList.get(i).getPolicyNumber());
							List<ReportGeneration> reportList = new ArrayList<>();
							List<BsReportGeneration> questionList = new ArrayList<>();
							try {
								reportList = insuranceQuoteGenerationService
										.getReportGeneration(policy.getAccountNumber(), policy.getPolicyNumber());
								for (ReportGeneration report : reportList) {

									System.out.println(" " + report);
								}
								questionList = insuranceQuoteGenerationService
										.getQuestionsReport(policy.getPolicyNumber());
								for (BsReportGeneration report : questionList) {

									System.out.println(" " + report);
								}
							} catch (InsuranceQuoteGenerationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}

					break;
				case 6:
					System.exit(0);
					break;
				default:
					System.out.println("Enter correct Choice!!");
				}
			}
		} else if (role.equals("Agent") || role.equals("agent")) {
			while (true) {
				System.out.println("______________________");
				System.out.println("Welcome Agent");
				System.out.println("______________________");
				System.out.println("1. Account Creation.");
				System.out.println("2. Policy Creation.");
				System.out.println("3. View policy.");
				System.out.println("4. Exit.");
				System.out.println("Enter your choice:");
				try {
					int agentChoice = scanner.nextInt();
					switch (agentChoice) {
					case 1:
						
							try {

								populateAgentBean(username);

							
						} catch (InsuranceQuoteGenerationException insuranceQuoteGenerationException) {
							// TODO Auto-generated catch block
							System.out.println(insuranceQuoteGenerationException);
						}

						break;
					case 2:
						System.out.println("Welcome Agent");
						System.out.println("New Scheme Creation Page");
						System.out.println("how many policy question your scheme has");
						int questionNumber = scanner.nextInt();
						NewPolicyBean newPolicySchemeBean = new NewPolicyBean();
						insuranceQuoteGenerationService = new InsuranceQuoteGenerationServiceIMPL();

						while (questionNumber == 0) {
							newPolicySchemeBean = policyCreation();
							questionNumber--;
							try {
								insuranceQuoteGenerationService.createNewScheme(newPolicySchemeBean);
							} catch (InsuranceQuoteGenerationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						System.out.println("New Policy Scheme Created Successfully");

						break;
					case 3:

						List<AgentViewPolicyBean> policyDetails = new ArrayList<>();
						insuranceQuoteGenerationService = new InsuranceQuoteGenerationServiceIMPL();
						try {
							policyDetails = insuranceQuoteGenerationService.getPolicyDetails(username);
							for (AgentViewPolicyBean agentViewPolicyBean : policyDetails) {
								System.out.println(agentViewPolicyBean);
							}

						} catch (InsuranceQuoteGenerationException insuranceQuoteGenerationException) {
							// TODO Auto-generated catch block
							System.out.println(insuranceQuoteGenerationException);
						}

						break;
					case 4:
						System.out.println("Have a good Day!!");
						System.exit(0);
						break;
					default:
						System.out.println("____________________________________");
						System.out.println("You have entered a wrong choice!!");
						System.out.println("Try Again!!");
						System.out.println("_____________________________________");
						break;
					}

				} catch (InputMismatchException e) {
					System.out.println("___________________________________________");
					System.out.println("Please enter a numeric value, Try Again!!");
					System.out.println("____________________________________________");
					break;
				}
			}
		} else if (role.equals("User") || role.equals("user")) {
			insuranceQuoteGenerationService = new InsuranceQuoteGenerationServiceIMPL();
			try {
				if(insuranceQuoteGenerationService.checkUserAccount(username)) {
					System.out.println("User Choice");
					System.out.println("1.View Policy");
					System.out.println("Enter the chocie ");
					int choice = scanner.nextInt();
				if(choice==1) {
					List<UserViewPolicyBean> policyDetails = new ArrayList<>();
					insuranceQuoteGenerationService = new InsuranceQuoteGenerationServiceIMPL();

					try {
						policyDetails = insuranceQuoteGenerationService.getPolicyDetails1(username);
					} catch (InsuranceQuoteGenerationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for (UserViewPolicyBean userViewPolicyBean : policyDetails) {
						System.out.println(userViewPolicyBean);
					}

				
				}
				else {
					System.out.println("eneter the valid chocie");
				}
			}
				else {
					System.out.println("User Choice");
					System.out.println("1.Account Creation");
					System.out.println("2.View Policy");
					System.out.println("Enter the chocie ");
					int choice = scanner.nextInt();
					switch (choice) {
					case 1:

						try {
							populateAgentBean(username);
						} catch (InsuranceQuoteGenerationException insuranceQuoteGenerationException) {
							// TODO Auto-generated catch block
							System.out.println(insuranceQuoteGenerationException);
						}
						break;
					case 2:
						List<UserViewPolicyBean> policyDetails = new ArrayList<>();
						insuranceQuoteGenerationService = new InsuranceQuoteGenerationServiceIMPL();

						try {
							policyDetails = insuranceQuoteGenerationService.getPolicyDetails1(username);
						} catch (InsuranceQuoteGenerationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						for (UserViewPolicyBean userViewPolicyBean : policyDetails) {
							System.out.println(userViewPolicyBean);
						}

						break;
					}

				} 
				
		
			} catch (InsuranceQuoteGenerationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
		else {
			System.out.println("enter the username and password correctly");
		}
}

	private static String populateProfileCreation() throws InsuranceQuoteGenerationException {
		insuranceQuoteGenerationService = new InsuranceQuoteGenerationServiceIMPL();
		ProfileCreation profileCreation = new ProfileCreation();
		String result = null;
		System.out.println("____________________________________");
		System.out.println("Create a new profile");
		System.out.println("_____________________________________");
		System.out.println("Create username: ");
		String validName = scanner.next();
		profileCreation.setUserName(validName);

		try {
			boolean validation = insuranceQuoteGenerationService.findAgentName(validName);
			if (validation == false) {
				System.out.println("Create password: ");
				profileCreation.setPassword(scanner.next());

				System.out.println("Enter RoleCode: ");
				profileCreation.setRoleCode(scanner.next());
				// insuranceQuoteGenerationService = new InsuranceQuoteGenerationServiceIMPL();
				insuranceQuoteGenerationService.addProfile(profileCreation);
				result = "sucessfully entered!!!";
			} else {
				result = ("entered user is already exist");
			}
		} catch (InsuranceQuoteGenerationException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static void populateAgentBean(String username)
			throws InsuranceQuoteGenerationException{

		insuranceQuoteGenerationService = new InsuranceQuoteGenerationServiceIMPL();

		Business business = new Business();

		System.out.println("Choose Your Business Segment:");
		System.out.println("1. Business Auto.");
		System.out.println("2. Restaurant.");
		System.out.println("3. Apartment.");
		System.out.println("4. General Merchant.");
		System.out.println("Enter your choice:");
		int businessSegmentChoice = scanner.nextInt();
		List<QuestionBean> al = new ArrayList<>();
		switch (businessSegmentChoice) {
		case 1:
			business.setBusiness_Segment("Business Auto");
			al = insuranceQuoteGenerationService.createPolicy("Business_auto");

			getDetails(al, username);
			break;
		case 2:
			business.setBusiness_Segment("Restaurant");
			al = insuranceQuoteGenerationService.createPolicy("Restaurant");
			getDetails(al, username);
			break;
		case 3:
			business.setBusiness_Segment("Apartment");
			al = insuranceQuoteGenerationService.createPolicy("Apartment");
			getDetails(al, username);
			break;
		case 4:
			business.setBusiness_Segment("General Merchant");
			al = insuranceQuoteGenerationService.createPolicy("general_merchant");
			getDetails(al, username);
			break;
		default:
			System.out.println("Please enter correct number");

		}

	}

	public static void getDetails(List<QuestionBean> al, String username)
			throws InsuranceQuoteGenerationException {
		Business businessVal = new Business();
		insuranceQuoteGenerationService = new InsuranceQuoteGenerationServiceIMPL();
  
		boolean value = insuranceQuoteGenerationService.CheckAccount(username, al.get(1).getBusinessSegment());
		if (value == true) {
			System.out.println("You have already taken policy on the particular business Segemnt");
			populateAgentBean(username);
		}
 
		else {

			PolicyCreationBean policyCreation = new PolicyCreationBean();
			TemporaryData temporarydata = new TemporaryData();
			int premiumCal = 0;

			for (QuestionBean questionBean : al) {
				System.out.println(questionBean.getQuestion());
				System.out.println("1." + questionBean.getAnswer1() + "\t" + "2." + questionBean.getAnswer2() + "\t"
						+ "3." + questionBean.getAnswer3());
				System.out.println("enter the option");
				int option = scanner.nextInt();

				switch (option) {
				case 1:
					// agentBean.setAnswer(questionBean.getAnswer1());
					// agentBean.setWeightage(questionBean.getWeightage1());
					premiumCal += questionBean.getWeightage1();
					temporarydata.setAnswer(questionBean.getAnswer1());
					temporarydata.setWeightage(questionBean.getWeightage1());

					break;
				case 2:
					// agentBean.setAnswer(questionBean.getAnswer2());
					// agentBean.setWeightage(questionBean.getWeightage2());
					premiumCal += questionBean.getWeightage2();
					temporarydata.setAnswer(questionBean.getAnswer2());
					temporarydata.setWeightage(questionBean.getWeightage2());

					break;
				case 3:
					// agentBean.setAnswer(questionBean.getAnswer3());
					// agentBean.setWeightage(questionBean.getWeightage3());
					premiumCal += questionBean.getWeightage3();
					temporarydata.setAnswer(questionBean.getAnswer3());
					temporarydata.setWeightage(questionBean.getWeightage3());

				}

				// agentBean.setQuestion(questionBean.getQuestion());
				policyCreation.setBusinessSegment(questionBean.getBusinessSegment());
				temporarydata.setQuestion(questionBean.getQuestion());
				temporarydata.setUserName(username);

			}

			System.out.println("Your premium is:" + premiumCal);
			System.out.println("choose option to continue if premium is okay ");
			System.out.println("1.To continue");
			System.out.println("0.Return Back");
			int chocie = scanner.nextInt();

			switch (chocie) {
			case 1:

				CreateAccountBean createAccount = new CreateAccountBean();
				System.out.println("Enter the Account number");
				Long accountNumber = scanner.nextLong();
				createAccount.setAccountNumber(accountNumber);
				boolean checkNumber = insuranceQuoteGenerationService.checkAccountNumber(accountNumber);
				if (checkNumber == false) {

					System.out.println("Enter the insured Name");
					String insuredName = scanner.next();
					createAccount.setInsuredName(insuredName);
					System.out.println("Enter the insured street");
					String insuredStreet = scanner.next();
					createAccount.setInsuredStreet(insuredStreet);
					System.out.println("Enter the insured city");
					String insuredCity = scanner.next();
					createAccount.setInsuredCity(insuredCity);
					System.out.println("Enter the insured state");
					String insuredState = scanner.next();
					createAccount.setInsuredState(insuredState);
					System.out.println("Enter the insured Zip");
					Long insuredZip = scanner.nextLong();
					createAccount.setInsuredZip(insuredZip);
					System.out.println("Enter the Business Segment");
					createAccount.setBusinessSegment(al.get(1).getBusinessSegment());

					insuranceQuoteGenerationService = new InsuranceQuoteGenerationServiceIMPL();
					String roleCode = insuranceQuoteGenerationService.checkUser(username);

					if (roleCode.equals("agent") || roleCode.equals("Admin")) {
						System.out.println(" Enter the unique username ");
						String name = scanner.next();
						createAccount.setUsername(name);
						createAccount.setAgentName(username);
						businessVal.setUsername(name);

					} else if (roleCode.equals("user")) {

						System.out.println(" enter the unique agentname/adminname is");
						String agentName = scanner.next();
						createAccount.setAgentName(agentName);
						createAccount.setUsername(username);
					}
					insuranceQuoteGenerationService.accountCreation(createAccount);
				} else {
					insuranceQuoteGenerationService = new InsuranceQuoteGenerationServiceIMPL();
					String roleCode = insuranceQuoteGenerationService.checkUser(username);

					if (roleCode.equals("agent") || roleCode.equals("Admin")) {
						System.out.println(" Enter the unique username ");
						String name = scanner.next();
						createAccount.setUsername(name);
						createAccount.setAgentName(username);
						businessVal.setUsername(name);

					} else if (roleCode.equals("user")) {

						System.out.println(" enter the unique agentname/adminname is");
						String agentName = scanner.next();
						createAccount.setAgentName(agentName);
						createAccount.setUsername(username);
					}

				}
				PolicyDetails policyDetails = new PolicyDetails();

				try {
					insuranceQuoteGenerationService.questionStore(temporarydata);

					policyDetails.setAccountNumber(accountNumber);
					policyDetails.setPremium(premiumCal);
					Long policyValue;
					try {
						policyValue = generatePolicy();
						policyDetails.setPolicyNumber(policyValue);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Long number = insuranceQuoteGenerationService.policy_Details(policyDetails);
					System.out.println("Your Policy Number is" + number);
					policyCreation.setPolicyNumber(number);
					List<TemporaryData> temp = new ArrayList<>();
					temp = insuranceQuoteGenerationService.getQuestionStore(username);
					// System.out.println(temp);
					for (TemporaryData temporaryData : temp) {
						policyCreation.setQuestion(temporaryData.getQuestion());
						policyCreation.setAnswer(temporaryData.getAnswer());
						policyCreation.setWeightage(temporaryData.getWeightage());
						// agentBean.setPolicyNumber(number);
						// System.out.println(agentBean);
						insuranceQuoteGenerationService.policyCreation(policyCreation);
					}
					insuranceQuoteGenerationService.deleteQuestionStore();
					businessVal.setBusiness_Segment(al.get(1).getBusinessSegment());
					businessVal.setPolicyNumber(number);
					// businessVal.setUsername(username);
					insuranceQuoteGenerationService.businessReport(businessVal);
				} catch (InsuranceQuoteGenerationException e1) {

					e1.printStackTrace();
				}

				break;
			case 2:
				try {
					populateAgentBean(username);
				} catch (InsuranceQuoteGenerationException insuranceQuoteGenerationException) {
					// TODO Auto-generated catch block
					System.out.println(insuranceQuoteGenerationException);
				} 
				break;

			}
		}
	}

	public static Long generatePolicy() throws IOException {
		FileReader fileReader = new FileReader("Policy_Number.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		int readLine = Integer.parseInt(bufferedReader.readLine());
		int increaseCount = readLine + 1;
		FileOutputStream fileWriter = new FileOutputStream("Policy_Number.txt");
		fileWriter.write(Integer.toString(increaseCount).getBytes());
		fileWriter.close();
		String date = "" + java.time.LocalDate.now();
		String Year = date.substring(2, 4);
		String Month = date.substring(5, 7);
		FileReader contentReader = new FileReader("Policy_Number.txt");
		BufferedReader readLines = new BufferedReader(contentReader);
		int countValue = Integer.parseInt(readLines.readLine());

		String remainingDigits = null;

		if (countValue < 10)
			remainingDigits = "000" + countValue;
		if (countValue >= 10 && countValue < 100)
			remainingDigits = "00" + countValue;
		if (countValue >= 100)
			remainingDigits = "0" + countValue;
		String policyNumber = Year + Month + remainingDigits;
		readLines.close();
		contentReader.close();
		bufferedReader.close();
		fileReader.close();
		return Long.parseLong(policyNumber);

	}

	public static NewPolicyBean policyCreation() {
		NewPolicyBean newPolicySchemeBean = new NewPolicyBean();
		System.out.println("Enter New Policy Business Segment Name");
		newPolicySchemeBean.setBus_seg_name(scanner.next());
		System.out.println("Enter New Question ");
		newPolicySchemeBean.setPol_ques_desc(scanner.next());
		System.out.println("Enter Answer-1 For Entered Question:");
		newPolicySchemeBean.setPol_ques_ans1(scanner.next());
		System.out.println("Enter Weightage for Answer-1 :");
		newPolicySchemeBean.setPol_ques_ans1_weightage(scanner.nextInt());
		System.out.println("Enter Answer-2 For Entered Question:");
		newPolicySchemeBean.setPol_ques_ans2(scanner.next());
		System.out.println("Enter Weightage for Answer-2 :");
		newPolicySchemeBean.setPol_ques_ans2_weightage(scanner.nextInt());
		System.out.println("Enter Answer-3 For Entered Question:");
		newPolicySchemeBean.setPol_ques_ans3(scanner.next());
		System.out.println("Enter Weightage for Answer-3 :");
		newPolicySchemeBean.setPol_ques_ans3_weightage(scanner.nextInt());
		return newPolicySchemeBean;
	}

}
