package com.fullcrum.model.sys;

import java.sql.Date;

/*
 * 
 * 
 * author :gavin.hou
 * 
 * 
companyName varchar(45) 
contactsId varchar(45) 
contactsPhone varchar(45) 
contactsEmail varchar(45) 
contactsQQ varchar(45) 
bankAccountName varchar(45) 
bankAccount varchar(45) 
bankName varchar(45) 
picId varchar(45) 
signUpAddr varchar(50) 
updateDate date 
timeStamp timestamp
 *
 *
 */


public class CompanyEntity {

	private int companyId ;
	
	private String companyName;
	
	private String contactsId;
	
	private String contactsName;
	
	private String contactsPhone;
	
	private String  contactsEmail;
	
	private String  contactsQQ;
	
	private String bankAccountName;
	
	private String bankAccount;
	
	private String bankName;
	
	private int picId;
	
	private String  signUpAddr;  //开户地址
	
	private Date updateDate;   //更新时间

	private String role; //公司审核状态

	private Date updateTimeStamp;

	private String bankCode;//开户行行号

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getContactIDCardNo() {
		return contactIDCardNo;
	}

	public void setContactIDCardNo(String contactIDCardNo) {
		this.contactIDCardNo = contactIDCardNo;
	}

	private String contactIDCardNo;//联系人身份证证件号码

	public Date getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	public void setUpdateTimeStamp(Date updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}
	
	public String getContactsName() {
		return contactsName;
	}

	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactsId() {
		return contactsId;
	}

	public void setContactsId(String contactsId) {
		this.contactsId = contactsId;
	}

	public String getContactsPhone() {
		return contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	public String getContactsEmail() {
		return contactsEmail;
	}

	public void setContactsEmail(String contactsEmail) {
		this.contactsEmail = contactsEmail;
	}

	public String getContactsQQ() {
		return contactsQQ;
	}

	public void setContactsQQ(String contactsQQ) {
		this.contactsQQ = contactsQQ;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public int getPicId() {
		return picId;
	}

	public void setPicId(int picId) {
		this.picId = picId;
	}

	public String getSignUpAddr() {
		return signUpAddr;
	}

	public void setSignUpAddr(String signUpAddr) {
		this.signUpAddr = signUpAddr;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}