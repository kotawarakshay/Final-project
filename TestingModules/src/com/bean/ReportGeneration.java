package com.bean;

public class ReportGeneration {

private String insure_name;
private String insured_street;
private String insured_city;
private String insured_state;
private Long zip;
private String business_segment;
public String getInsure_name() {
	return insure_name;
}
public void setInsure_name(String insure_name) {
	this.insure_name = insure_name;
}
public String getInsured_street() {
	return insured_street;
}
public void setInsured_street(String insured_street) {
	this.insured_street = insured_street;
}
public String getInsured_city() {
	return insured_city;
}
public void setInsured_city(String insured_city) {
	this.insured_city = insured_city;
}
public String getInsured_state() {
	return insured_state;
}
public void setInsured_state(String insured_state) {
	this.insured_state = insured_state;
}
public Long getZip() {
	return zip;
}
public void setZip(Long zip) {
	this.zip = zip;
}
public String getBusiness_segment() {
	return business_segment;
}
public void setBusiness_segment(String business_segment) {
	this.business_segment = business_segment;
}
@Override
public String toString() {
	return "ReportGeneration [insure_name=" + insure_name + ", insured_street=" + insured_street + ", insured_city="
			+ insured_city + ", insured_state=" + insured_state + ", zip=" + zip + ", business_segment="
			+ business_segment + "]";
}
}
