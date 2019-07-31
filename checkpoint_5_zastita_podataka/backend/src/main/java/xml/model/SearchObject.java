package xml.model;

import java.util.Date;

public class SearchObject {

	private Date endDate;
	private Date startDate;
	private String accomodationTypeValue;
	private int accomodationCategoryValue;
	private int numOfPersons;
	private boolean fullBoard;
	private boolean halfBoard;
	private boolean kitchen;
	private boolean breakfast;
	private boolean parking;
	private boolean privateBathroom;
	private boolean tv;
	private boolean wifi;
	
	public SearchObject() {}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getAccomodationTypeValue() {
		return accomodationTypeValue;
	}

	public void setAccomodationTypeValue(String accomodationTypeValue) {
		this.accomodationTypeValue = accomodationTypeValue;
	}

	public int getAccomodationCategoryValue() {
		return accomodationCategoryValue;
	}

	public void setAccomodationCategoryValue(int accomodationCategoryValue) {
		this.accomodationCategoryValue = accomodationCategoryValue;
	}

	public int getNumOfPersons() {
		return numOfPersons;
	}

	public void setNumOfPersons(int numOfPersons) {
		this.numOfPersons = numOfPersons;
	}

	public boolean isFullBoard() {
		return fullBoard;
	}

	public void setFullBoard(boolean fullBoard) {
		this.fullBoard = fullBoard;
	}

	public boolean isHalfBoard() {
		return halfBoard;
	}

	public void setHalfBoard(boolean halfBoard) {
		this.halfBoard = halfBoard;
	}

	public boolean isKitchen() {
		return kitchen;
	}

	public void setKitchen(boolean kitchen) {
		this.kitchen = kitchen;
	}

	public boolean isBreakfast() {
		return breakfast;
	}

	public void setBreakfast(boolean breakfast) {
		this.breakfast = breakfast;
	}

	public boolean isParking() {
		return parking;
	}

	public void setParking(boolean parking) {
		this.parking = parking;
	}


	public boolean isPrivateBathroom() {
		return privateBathroom;
	}

	public void setPrivateBathroom(boolean privateBathroom) {
		this.privateBathroom = privateBathroom;
	}

	public boolean isTv() {
		return tv;
	}

	public void setTv(boolean tv) {
		this.tv = tv;
	}

	public boolean isWifi() {
		return wifi;
	}

	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}
	
}
