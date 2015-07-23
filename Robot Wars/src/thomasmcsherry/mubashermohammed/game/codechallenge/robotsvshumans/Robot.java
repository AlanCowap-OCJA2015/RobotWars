package thomasmcsherry.mubashermohammed.game.codechallenge.robotsvshumans;

import java.util.Random;

public class Robot{

	private String model;
	private double power;
	private boolean isAlive = true;
	
	public Robot(String model) {
		super();
		this.model = model;
		
		Random rand = new Random();
		power = rand.nextInt(101);
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	
	public void die(){
		isAlive = false;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}
	
	
	

}
