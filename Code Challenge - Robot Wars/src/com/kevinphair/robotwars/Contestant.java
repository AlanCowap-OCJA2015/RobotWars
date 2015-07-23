package com.kevinphair.robotwars;

import java.util.Random;

/**
 * @author Kevin Phair, Sam Downes
 * Created 23 Jul 2015
 *
 */
public class Contestant implements Comparable<Contestant> {

	
	private int power = 0;
	private static int id = 0;
	private int objectId = 0;
	private String name = "";
	private int active = 0;
	
	public Contestant (int p, String s) {
		this.power = p;
		this.objectId = getId();
		this.active = 1;
		this.name = makeName();
		Contestant.id++;
	}
	
	public int compareTo(Contestant c) {
		int compareActive = c.getActive(); 
 
		//ascending order
		return this.active - compareActive;
 
		//descending order
		//return compareQuantity - this.quantity;
 
	}
	
	/**
	 * Gets the active status of the object
	 * @return
	 */
	public int getActive() {
		return this.active;
	}

	public void makeInactive() {
		this.active = 0;
	}

	/**
	 * Get the current power level of object
	 * @return power as int
	 */
	public int getPower() {
		return this.power;
	}
	/**
	 * Set the current power level of object
	 * @param power as int from 0 to 100 inclusive
	 */
	public void setPower(int p) {
		this.power = p;
	}

	/**
	 * Get the id (name) of the object
	 * @return id as string
	 */
	public static int getId() {
		return Contestant.id;
	}
	
	public String makeName() {
		return  "" + Contestant.id;	
	}
	
	public String getName() {
		return this.name;
		
	}
	
	public void setName(String s) {
		this.name = s;	
	}

	/**
	 * Generate a name that sounds like an industrial robot
	 * @return
	 */
	public String makeRobotName() {
		String[] sounds = {
				"wa", "ra", "ya", "ma", "ha", "na", "ta", "sa", "ka",
				                  "mi", "hi", "ni", "chi", "shi", "ki",
				      "ru", "yu", "mu", "hu", "nu", "tsu", "su", "ku",
				      "re", "me", "he", "ne", "te", "sr", "ke", 
				"wo", "ro", "mo", "ho", "no", "to", "so", "ko" 
		};
		Random rand = new Random();
		
		int numSounds = 3 + rand.nextInt(3);
		String name = "";
		for (;numSounds > 0; --numSounds) {
			name += sounds[rand.nextInt(sounds.length)];
		}
		return name+ "-"+rand.nextInt(20) * 100;
	}
	
	/**
	 * Generate a human sounding name
	 * @return
	 */
	public String makeHumanName() {
		String vowels = "aeiou";
		String consonants = "ebcdfghjklmnprstvwyxqz";			// Yes, there is an 'e' in there
		Random rand = new Random();
		String name = "";
		int numSounds = 3 + rand.nextInt(8);
		
		for (;numSounds > 0; --numSounds) {
			int vpos = rand.nextInt(vowels.length());
			int cpos = rand.nextInt(consonants.length());
			// Bias the consonant selection to the lower end of the string
			cpos += rand.nextInt(consonants.length());
			cpos = consonants.length() - 1 - (cpos/2);
			
			if (rand.nextInt(10) > 5) {
				name += vowels.substring(vpos, vpos+1) + consonants.substring(cpos, cpos+1);
			} else {
				name += consonants.substring(cpos, cpos+1) + vowels.substring(vpos, vpos+1);
			}
			if (rand.nextInt(10) > 7) {
				name += vowels.substring(vpos, vpos+1);
			}
			if (rand.nextInt(10) > 8) {
				name += " ";
			}
		}
		return name;
	}
	
	
	
}
