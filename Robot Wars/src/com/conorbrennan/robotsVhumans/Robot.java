package com.conorbrennan.robotsVhumans;



public class Robot extends Lifeform { 
	Robot(String robotName , String modelName , int power){
		this.name = robotName;
		this.uniqueID = modelName;
		this.power = power;
	}

	@Override
	public String toString() {
		return name+" "+uniqueID+" with a power of "+power;
	}


}
