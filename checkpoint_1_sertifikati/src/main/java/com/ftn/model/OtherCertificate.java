package com.ftn.model;

import java.io.Serializable;

public class OtherCertificate  implements Serializable {

	private String ksName;
	private String ksPassword;
	private String issuer;
	private String issuerPassword;
	private String commonName;
	private String alias;
	private String orgName;
	private String orgUnit;
	private String country;
	private String email;
	private String serialNum;
	public String validity;
	public String purpose;
	private boolean isCA;
	
	
	public OtherCertificate() {
		
	}

	public OtherCertificate(String ksName, String ksPassword, String issuer, String issuerPassword, String commonName,
			String alias, String orgName, String orgUnit, String country, String email, String serialNum,
			String validity, String purpose, boolean isCA) {
		super();
		this.ksName = ksName;
		this.ksPassword = ksPassword;
		this.issuer = issuer;
		this.issuerPassword = issuerPassword;
		this.commonName = commonName;
		this.alias = alias;
		this.orgName = orgName;
		this.orgUnit = orgUnit;
		this.country = country;
		this.email = email;
		this.serialNum = serialNum;
		this.validity = validity;
		this.purpose = purpose;
		this.isCA = isCA;
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

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getIssuerPassword() {
		return issuerPassword;
	}

	public void setIssuerPassword(String issuerPassword) {
		this.issuerPassword = issuerPassword;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
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

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public boolean isCA() {
		return isCA;
	}

	public void setCA(boolean isCA) {
		this.isCA = isCA;
	}
	
}
