package com.conorbrennan.robotsVhumans;



public class Human extends Lifeform {
	
	Human(String name , int power){
		this.name = name;
		this.power = power;
	}

	@Override
	public String toString() {
		return "Human "+name+" with a power of"+power;
	}
	
	
}
