package thomasmcsherry.mubashermohammed.game.codechallenge.robotsvshumans;

import java.util.Random;

public class Human{
	
	private String name;
	private double power;
	private boolean isAlive = true;
	
	public Human(String name) {
		super();
		this.name = name;
		
		Random rand = new Random();
		power = rand.nextInt(101);
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	
	public void die(){
		isAlive = false;
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
