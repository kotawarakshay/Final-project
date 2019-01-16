package com.bean;

public class ProfileCreation {

	private String userName;
	private String password;
	private String roleCode;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	@Override
	public String toString() {
		return "ProfileCreation [userName=" + userName + ", password=" + password + ", roleCode=" + roleCode + "]";
	}
	
	
}
