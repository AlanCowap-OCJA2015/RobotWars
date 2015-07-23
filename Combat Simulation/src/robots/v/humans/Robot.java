package robots.v.humans;

import java.util.Random;

public class Robot extends Lifeform{
	
	static String[] modelType = {"T-X", "T-1", "T-100", "T-200", "T-400", "T-800", "T-1000"};
	
	private String modelName;
	
	Robot(){
		this.setModelName();
	}
	public void setModelName(){
		Random rand = new Random();
		
		this.modelName = Robot.modelType[rand.nextInt(Robot.modelType.length)];
	}
		
	public String getModelName(){
		return modelName;
	}
	
}