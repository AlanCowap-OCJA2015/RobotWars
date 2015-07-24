package com.conorbrennan.robotsVhumans;

public class Lifeform {
	protected int power;
	protected String name ;
	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public String getUniqueID() {
		return uniqueID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	protected String uniqueID;

	@Override
	public String toString() {
		return "Lifeform [power=" + power + ", uniqueID=" + uniqueID + "]";
	}
}
