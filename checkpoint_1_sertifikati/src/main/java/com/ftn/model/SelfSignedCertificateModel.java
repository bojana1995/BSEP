package com.ftn.model;

public class SelfSignedCertificateModel {
	
	private String ksName;
	private String ksPassword;
	private String commonName;
	private String alias;
	private String orgName;
	private String orgUnit;
	private String country;
	private String email;
	private String serialNum;
	public String validity;
	public String aia;
	public String cdp;
	public String purpose;
	
	
	public SelfSignedCertificateModel() {
		
	}

	public SelfSignedCertificateModel(String commonName,String alias, String orgName, String orgUnit, String country, String email,
		    String serialNum, String ksName, String ksPassword, String validity,
			String aia, String cdp, String purpose) {
		super();
		this.commonName = commonName;
		this.alias = alias;
		this.orgName = orgName;
		this.orgUnit = orgUnit;
		this.country = country;
		this.email = email;
		this.serialNum = serialNum;
		this.ksName = ksName;
		this.ksPassword = ksPassword;
		this.validity = validity;
		this.aia = aia;
		this.cdp = cdp;
		this.purpose = purpose;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgUnit() {
		return orgUnit;
	}

	public void setOrgUnit(String orgUnit) {
		this.orgUnit = orgUnit;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getKsName() {
		return ksName;
	}

	public void setKsName(String ksName) {
		this.ksName = ksName;
	}

	public String getKsPassword() {
		return ksPassword;
	}

	public void setKsPassword(String ksPassword) {
		this.ksPassword = ksPassword;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getAia() {
		return aia;
	}

	public void setAia(String aia) {
		this.aia = aia;
	}

	public String getCdp() {
		return cdp;
	}

	public void setCdp(String cdp) {
		this.cdp = cdp;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
