package com.mubasher.ocja.game;


import java.util.Random;



/**
 * @author @author Thomas Mcsherry,Mubasher Mohammede
 *
 */
/*
 * Scanner s = new Scanner(new File("filepath"));
ArrayList<String> list = new ArrayList<String>();
while (s.hasNext()){
    list.add(s.next());
}
s.close();
 */
public class Human {
	private String name;
	private double power;
	private boolean isAlive =true; 

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public Human(String name) {
		super();
		this.name = name;
		
		Random ran = new Random();
		power = ran.nextInt(101);
		
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPower() {
		return power;
	}
	public void setPower(double power) {
		this.power = power;
	}
	

}