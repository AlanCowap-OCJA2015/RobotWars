package robots.v.humans;


import java.util.Random;

public class Lifeform{
	private int powerLevel;
	static int idNumCounter;
	private int idNumber = idNumCounter;
	private boolean isAlive = true;

	Lifeform(){
		++idNumCounter;
		this.setPowerLevel();
	}

	public int getIdNumber() {
		return idNumber;
	}
	public int getPowerLevel() {
		return powerLevel;
	}
	private void setPowerLevel() {
		Random rand = new Random();
		this.powerLevel = rand.nextInt(101);
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void kill() {
		this.isAlive = false;
	}
	public boolean damage(){
		this.powerLevel = this.powerLevel/2;
		if (this.powerLevel <= 0) this.kill();
		return this.isAlive();
	}
}