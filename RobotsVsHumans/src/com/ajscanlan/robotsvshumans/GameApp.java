package com.ajscanlan.robotsvshumans;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;


public class GameApp {

	ArrayList<Robot> robots = new ArrayList<Robot>();
	ArrayList<Human> humans = new ArrayList<Human>();

	ArrayList<String> firstNames = new ArrayList<String>();
	ArrayList<String> lastNames = new ArrayList<String>();
	ArrayList<String> roboNames = new ArrayList<String>();

	private final int LIMIT = 500;
	private final float DECAY = 0.055F;
	private int totalHumanPower, totalRobotPower, winStreak;
	private int sleep = 2500;
	private String champion;
	private boolean humanKillStreak = true, roboKillStreak = true; //to stop too many sound files playing

	public static void main(String[] args) {

		GameApp gl = new GameApp();
		int choice = gl.displayMenu();
		gl.populateLists();

		switch(choice){
		case 1:
			gl.runProgram();
			gl.displayResults();
			break;

		case 2:
			gl.battleRoyale();
			break;
		}

	}

	private void battleRoyale() {

		String tempChamp = "";
		String prevChamp = "";
		int tempWinStreak = 0;

		//checking 
		while(humans.size() > 0 && robots.size() > 0){

			System.out.println(tempChamp);

			if(!tempChamp.equals(prevChamp)){
				tempWinStreak = 0;
			}
			

			System.out.println("Current Kill Streak: " + tempWinStreak);
			System.out.println(robots.get(0) + " vs " + humans.get(0));
			
			//if human has more power than robot
			if(humans.get(0).getPower() > robots.get(0).getPower()){
				
				tempWinStreak++;
				robots.remove(0);
				humans.get(0).setPower(humans.get(0).getPower() - (humans.get(0).getPower() * DECAY));
				System.out.println("HUMAN WINNER");
				prevChamp = tempChamp;
				tempChamp = humans.get(0).getName();
				
				if(tempWinStreak == 10 && humanKillStreak){
					playSound("vehicularmanslaughter.wav");
					humanKillStreak = false;
				}
				
				calculateWinstreak(tempWinStreak, humans.get(0).getName());
			
			//if they have the same power
			} else if(humans.get(0).getPower() == robots.get(0).getPower()){
				robots.remove(0);
				humans.remove(0);
				System.out.println("DRAW");
				playSound("mutualdestruction.wav");
				System.out.println("Current Kill Streak: " + tempWinStreak);
			
			//if robot has more power than human
			} else if(humans.get(0).getPower() < robots.get(0).getPower()) {
				
				tempWinStreak++;
				humans.remove(0);
				robots.get(0).setPower(robots.get(0).getPower() - (robots.get(0).getPower() * DECAY));
				System.out.println("ROBOT WINNER");
				prevChamp = tempChamp;
				tempChamp = robots.get(0).getModel();
				
				if(tempWinStreak == 10 && roboKillStreak){
					playSound("killingmachine.wav");
					roboKillStreak = false;
				}
				
				calculateWinstreak(tempWinStreak, robots.get(0).getModel());
			}

			System.out.println("");
		}

		System.out.println("Humans: " + humans.size());
		System.out.println("Robots: " + robots.size());
		System.out.println("Champion: " + champion + "\nKill Streak: " + winStreak);
		
		System.out.println(Math.abs(humans.size() - robots.size()));
		
		if(Math.abs(humans.size() - robots.size()) == 1){
			//if theres only one left standing
			playSound("lastsecondsave.wav");
		} else if(Math.abs(humans.size() - robots.size()) < 10){
			//if theres less than 10 remaining
			playSound("bloodbath.wav");
		} else if(Math.abs(humans.size() - robots.size()) > 50){
			//if theres more than 50 remaining
			playSound("massacre.wav");
		}
		
		if(humans.size() > robots.size()){
			//if humans win
			playSound("termination.wav");
		} else if (humans.size() < robots.size()){
			//if robots win
			playSound("extermination.wav");
		}

	}

	private void calculateWinstreak(int tempWinStreak, String name) {
		if(tempWinStreak > winStreak){
			winStreak = tempWinStreak;
			champion = name;
		}

		switch(tempWinStreak){
		//		case 2:
		//			System.out.println("Double Kill");
		//			playSound("doublekill.wav");
		//			break;
		//		case 3:
		//			System.out.println("Multi Kill");
		//			playSound("multikill.wav");
		//			break;
		//		case 4:
		//			System.out.println("Ultra Kill");
		//			playSound("ultrakill.wav");
		//			break;
		case 11:
			System.out.println("R-R-R-R-RAMPAGE");
			playSound("rampage.wav");
			break;
		case 12:
			System.out.println("GODLIKE");
			playSound("godlike.wav");
			break;
		case 13:
			System.out.println("HOLY SHIT");
			playSound("holyshit.wav");
			break;
		}
	}

	private void playSound(String string) {
		try {
			File yourFile = new File(string);
			AudioInputStream stream;
			AudioFormat format;
			DataLine.Info info;
			Clip clip;

			stream = AudioSystem.getAudioInputStream(yourFile);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
			clip.start();
			Thread.sleep(sleep);
		}
		catch (Exception e) {
			//whatevers
		}
	}

	private int displayMenu() {
		System.out.println("1) 1 versus 1");
		System.out.println("2) Battle Royale");

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		return scan.nextInt();
	}

	private void displayResults() {
		System.out.println("Humans\n-----------");
		System.out.println("Games won: " + Human.getGamesWon());
		System.out.println("Games lost: " + Human.getGamesLost());
		System.out.println("Games drew: " + Human.getGamesDrew() + "\n\n");

		System.out.println("Robots\n-----------");
		System.out.println("Games won: " + Robot.getGamesWon());
		System.out.println("Games lost: " + Robot.getGamesLost());
		System.out.println("Games drew: " + Robot.getGamesDrew() + "\n\n");

		if(Human.getGamesWon() > Robot.gamesWon){
			System.out.println("HUMANS WIN, IT'S A FINE DAY FOR HUMANITY");
		} else if(Human.getGamesWon() == Robot.getGamesWon()){
			System.out.println("It was a draw, EVERYONE IS A LOSER");
		} else {
			System.out.println("ROBOTS WIN, ALL HAIL OUR ROBOT OVERLORDS");
		}

		System.out.println("Total Human power: " + totalHumanPower);
		System.out.println("Total Robot power: " + totalRobotPower);

	}

	private void populateLists() {
		Random randy = new Random();

		try {
			Scanner hL = new Scanner(new File("lastNames.txt"));
			Scanner hF = new Scanner(new File("firstNames.txt"));
			Scanner r = new Scanner(new File("robots.txt"));

			while (hL.hasNext()){
				lastNames.add(hL.next());
			}

			while (hF.hasNext()){
				firstNames.add(hF.next());
			}

			while(r.hasNext()){
				roboNames.add(r.next());
			}

			r.close();
			hL.close();
			hF.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for(int i = 0; i < LIMIT/2; ++i){
			String firstName = firstNames.get(randy.nextInt(firstNames.size()));
			String temp = firstName.substring(1, firstName.length()).toLowerCase();
			firstName = firstName.charAt(0) + temp;

			String lastName = lastNames.get(randy.nextInt(lastNames.size()));
			temp = lastName.substring(1, lastName.length()).toLowerCase();
			lastName = lastName.charAt(0) + temp;

			String name = firstName + " " + lastName;

			humans.add(new Human(name, randy.nextInt(101), i));
			robots.add(new Robot(roboNames.get(randy.nextInt(roboNames.size())) + " " + Robot.getModelType(), randy.nextInt(101), i + 500));
		}



	}

	private void runProgram() {

		for(int i = 0; i < LIMIT/2; ++i){
			float roboPower = robots.get(i).getPower();
			float humanPower = humans.get(i).getPower();

			System.out.println(humans.get(i) +" vs " + robots.get(i) + "\n");

			totalHumanPower += humanPower;
			totalRobotPower += roboPower;

			if(humanPower == roboPower){
				Human.incGames(Human.DRAW);
				Robot.incGames(Robot.DRAW);
			}else if(humanPower > roboPower){
				Human.incGames(Human.WIN);
				Robot.incGames(Robot.LOSS);
			}else {
				Human.incGames(Human.LOSS);
				Robot.incGames(Robot.WIN);
			}
		}

	}

}
