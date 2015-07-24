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
	 * Randomly select from an array of syllables. Each syllable will be accompanied
	 * by a list of letters that go well with it on the left and right hand sides
	 * @return
	 */
	public String makeHumanName() {
		final String vowels = "aeiou";
		final String cons = "bcdfghjklmnpqrstvwxyz";
		
		String[] syllables = {
				cons,	"al",	vowels,
				cons,	"an",	vowels,
				cons,	"ar",	vowels,
				cons,	"as",	vowels,
				cons,	"at",	vowels,
				cons,	"ea",	cons,
				cons,	"ed",	vowels,
				cons,	"en",	vowels,
				cons,	"er",	vowels,
				cons,	"es",	vowels,
				vowels,	"ha",	cons,
				vowels,	"he",	cons,
				vowels,	"hi",	cons,
				cons,	"in",	vowels,
				cons,	"is",	vowels,
				cons,	"it",	vowels,
				vowels,	"le",	cons,
				vowels,	"ke",	cons,
				vowels,	"me",	cons,
				vowels,	"nd",	vowels,
				vowels,	"ne",	cons,
				vowels,	"ng",	vowels,
				vowels,	"nt",	vowels,
				cons,	"on",	vowels,
				cons,	"or",	vowels,
				cons,	"ou",	cons,
				vowels,	"re",	cons,
				vowels,	"se",	cons,
				vowels,	"st",	vowels,
				vowels,	"te",	cons,
				vowels,	"th",	vowels,
				vowels,	"ti",	cons,
				vowels,	"to",	cons,
				vowels,	"ve",	cons,
				vowels,	"wa",	cons,
				vowels,	"he",	cons,
				cons,	"and",	vowels,
				vowels,	"con",	vowels,
				vowels,	"jam",	vowels,
				vowels,	"sam",	vowels,
				vowels,	"mar",	cons,
				vowels,	"pet",	vowels,
				vowels,	"jos",	vowels,
				vowels,	"jen",	vowels,
				cons,	"art",	vowels,
				vowels,	"gre",	cons + vowels,
				vowels,	"cr",	vowels,
				vowels,	"mi",	cons,
				vowels,	"rob",	vowels,
				cons,	"ark",	vowels,

				
		};
		Random rand = new Random();
		
		int lastSyl = -1;
		int syl = rand.nextInt(syllables.length / 3);
		String preMatch = syllables[syl * 3];
		String sound = syllables[syl * 3 + 1];
		String postMatch = syllables[syl * 3 + 2];
		String lastSound = sound;
		String name = sound;
		
		int numSyls = 2 + rand.nextInt(2);
		
		for (int i = 0; i < numSyls; ++i) {
			
			while (true) {
				
				// pick another random syllable
				postMatch = syllables[syl * 3 + 2];
				syl = rand.nextInt(syllables.length / 3);
//				System.out.printf("Chose syllable #%d\n", syl);
				preMatch = syllables[syl * 3];
				sound = syllables[syl * 3 + 1];
//				System.out.printf("Matching new sound '%s' with '%s' using pre-match of %s\n", sound, lastSound, preMatch);
				if (postMatch.indexOf(sound.substring(0, 1)) >= 0) {
//					System.out.println("Match made");
					break;
				}
			}
			name += sound;
			lastSound = sound;
			
		}
		
		
		return name;
	}
	
	
	
}
