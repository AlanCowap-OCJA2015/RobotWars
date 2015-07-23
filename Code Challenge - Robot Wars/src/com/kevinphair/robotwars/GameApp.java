package com.kevinphair.robotwars;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Kevin Phair, Sam Downes
 * Created 23 Jul 2015
 *
 * Code Challenge - "Robot Wars"
 * 
 * Initial spec:
 * Create 1,000 robots and 1,000 humans with unique model numbers (robots) and
 * unique names (humans). Each has a random power value from 0 to 100 inclusive.
 * One of each should do battle against each other and after 1,000 battles the
 * wins, draws and losses should be tallied up to determine the winner. 
 *
 * 9:00 - Start project
 *	Design program and set up variables
 * 9:10
 *  Create classes
 *  - Contestant - Contains power value (int) and ID (String)
 *  - ScoreBoard - Contains wins, losses and draws for each army
 *  
 *  Create variable
 *  - humanArmy[] = Contestant[1000]
 *  - robotArmy[] = Contestant[1000]
 *    
 *  Create method placeholders
 *  - Contestant[] initialiseArmy() - Create and return the army array 
 *  - int doBattle (Contestant vs1, Contestant vs2)
 *    >	Iterate over army objects applying fight() to each opposing pair
 *    > Update the ScoreBoard object accordingly
 *  - int fight (Contestant vs1, Contestant vs2) - Give the result of fight between two Contestants
 *  
 *  
 * 9:30 - Break
 * 9:45 - Write code for initialiseArmy()
 * 9:50 - Write code for fight()
 * 9:55 - Write code for doBAttle()
 * 10:00 - Test
 * 11:00 - Break
 * 
 * 12:00 - Finished and Demo
 *
 */
public class GameApp {

	ScoreBoard sb;
	
	public static void main(String[] args) {

		while (true) {
			
			switch(new GameApp().mainMenu()) {
				case 1:
					new GameApp().runProgram();
					break;
				case 2:
					new GameApp().lastArmyStanding();
					break;
				case 3:
					System.out.println("Bye...");
					return;
			}
		}
	}

	public int mainMenu() {
		
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		
		System.out.println("");
		System.out.println("~~~~~~~~~~~");
		System.out.println("ROBOT WARS!");
		System.out.println("~~~~~~~~~~~");
		System.out.println("Enter the number of the game type you wish to see and press ENTER");
		System.out.println("");
		System.out.println("1: Single battle between Robot and Human armies");
		System.out.println("2: Last army standing");
		System.out.println("3: Quit");
		System.out.println("");
		
		while (true) {
			if (scan.hasNextInt()) {
				choice = scan.nextInt();
			}
			if (choice >= 1 && choice <= 3) {
				return choice;
			} else {
				System.out.println("Invalid choice. Please try again");
			}
		}
	}
	
	public void runProgram() {
		
		sb = new ScoreBoard(2);
		
		Contestant[] robotArmy = initialiseArmy();
		for (Contestant c : robotArmy) {
			c.setName(c.makeRobotName());
		}
		Contestant[] humanArmy = initialiseArmy();
		for (Contestant c : humanArmy) {
			c.setName(c.makeHumanName());
		}
		int result = doBattle(robotArmy, humanArmy, true);

		if(result < 0){
			System.out.print("The human army won this battle with ");
			System.out.println(sb.getWins(1) + " wins, " + sb.getLosses(1) + " losses and " + sb.getDraws(1) + " draws (" + getTotalPower(humanArmy) + " total power)");
			System.out.println("Robots lost with " + sb.getWins(0) + " wins, " + sb.getLosses(0) + " losses and " + sb.getDraws(0) + " draws (" + getTotalPower(robotArmy) + " total power)");
			
		}else if(result > 0){
			System.out.print("The robot army won this battle with ");
			System.out.println(sb.getWins(0) + " wins, " + sb.getLosses(0) + " losses and " + sb.getDraws(0) + " draws (" + getTotalPower(robotArmy) + " total power)");
			System.out.println("Humans lost with " + sb.getWins(1) + " wins, " + sb.getLosses(1) + " losses and " + sb.getDraws(1) + " draws (" + getTotalPower(humanArmy) + " total power)");
			
		}else{
			System.out.println("Both armies have drawn this battle");
		}
			
	}
	
	public void lastArmyStanding() {

		ScoreBoard overallSb = new ScoreBoard(2);
		
		Contestant[] robotArmy = initialiseArmy();
		for (Contestant c : robotArmy) {
			c.setName(c.makeRobotName());
		}
		Contestant[] humanArmy = initialiseArmy();
		for (Contestant c : humanArmy) {
			c.setName(c.makeHumanName());
		}
		
		while (true) {

			sb = new ScoreBoard(2);

			// Sort the armies by active status
			Arrays.sort(robotArmy);
			Arrays.sort(humanArmy);
			int result = doBattle(robotArmy, humanArmy, false);
			// vs1 loses (vs2 wins)
			if (result < 0) {
				overallSb.addWin(1);
				overallSb.addLoss(0);
			// vs1 wins (vs2 loses)
			} else if (result > 0) {
				overallSb.addWin(0);
				overallSb.addLoss(1);
			// vs1 draws (vs2 draws)
			} else {
				overallSb.addDraw(1);
				overallSb.addDraw(1);
			}
			System.out.println("Battle result : " + sb.getWins(0) + " - " + sb.getWins(1) + ". Robot power remaining:" + getRemainingPower(robotArmy) + " Human power remaining:" + getRemainingPower(humanArmy));
			if (getRemainingPower(robotArmy) == 0 || getRemainingPower(humanArmy) == 0) break;
		}
		
		if(overallSb.getWins(0) < overallSb.getWins(1)){
			System.out.print("The human army won the war with ");
			System.out.println(overallSb.getWins(1) + " battles won, " + overallSb.getLosses(1) + " battles lost and " + overallSb.getDraws(1) + " battles drawn (" + getTotalPower(humanArmy) + " total power)");
			System.out.println("Robots lost with " + overallSb.getWins(0) + " battles won, " + overallSb.getLosses(0) + " battles lost and " + overallSb.getDraws(0) + " battles drawn (" + getTotalPower(robotArmy) + " total power)");
			
		}else if(overallSb.getWins(0) > overallSb.getWins(1)){
			System.out.print("The robot army won the war with ");
			System.out.println(overallSb.getWins(0) + " battles won, " + overallSb.getLosses(0) + " battles lost and " + overallSb.getDraws(0) + " battles drawn (" + getTotalPower(robotArmy) + " total power)");
			System.out.println("Humans lost with " + overallSb.getWins(1) + " battles won, " + overallSb.getLosses(1) + " battles lost and " + overallSb.getDraws(1) + " battles drawn (" + getTotalPower(humanArmy) + " total power)");
			
		}else{
			System.out.println("Both armies have declared a truce in this war");
			System.out.println("Humans have " + overallSb.getWins(1) + " battles won, " + overallSb.getLosses(1) + " battles lost and " + overallSb.getDraws(1) + " battles drawn (" + getTotalPower(humanArmy) + " total power)");
			System.out.println("Robots have " + overallSb.getWins(0) + " battles won, " + overallSb.getLosses(0) + " battles lost and " + overallSb.getDraws(0) + " battles drawn (" + getTotalPower(robotArmy) + " total power)");
		}
		
		System.out.println("There are " + getNumActive(robotArmy) + " robots left and " + getNumActive(humanArmy) + " humans surviving.");
		
	}
	
	/**
	 * @return
	 */
	public Contestant[] initialiseArmy() {
		
		Contestant[] army = new Contestant[500];
		for(int i = 0; i < army.length; ++i){
			army[i] = new Contestant(new Random().nextInt(101),"" + i);
		}
		return army;
		
	}
	
	/**
	 * @param vs1
	 * @param vs2
	 * @return
	 */
	public int doBattle(Contestant[] vs1, Contestant[] vs2, boolean report) {
		for (int i = 0; i < vs1.length; ++i) {
			if (vs1[i].getActive() > 0 && vs2[i].getActive() > 0) {
				int result = fight(vs1[i], vs2[i], report);
				// vs1 loses (vs2 wins)
				if (result < 0) {
					sb.addWin(1);
					sb.addLoss(0);
					
				// vs1 wins (vs2 loses)
				} else if (result > 0) {
					sb.addWin(0);
					sb.addLoss(1);
					
				// vs1 draws (vs2 draws)
				} else {
					sb.addDraw(1);
					sb.addDraw(1);
				}
			}
//			System.out.println(sb.getWins(0) + " - " + sb.getWins(1));
		}
		if(sb.getWins(0) > sb.getWins(1)){
			return 1;
		}else if (sb.getWins(1) > sb.getWins(0)){
			return - 1;
		}else {
			return 0;
		}
	
	}
	
	public int fight(Contestant vs1, Contestant vs2, boolean report) {
		
		int vs1Power = vs1.getPower();
		int vs2Power = vs2.getPower();
		
		String vs1Name = vs1.getName();
		String vs2Name = vs2.getName();
		
		vs1.setPower((vs1Power * 90) / 100);
		vs2.setPower((vs2Power * 90) / 100);
		
		if(vs1Power > vs2Power){
			if (report) System.out.println(vs1Name + " (" + vs1Power + ") > " + vs2Name + " (" + vs2Power + ") ");
			vs2.makeInactive();
			return 1;			
		}else if (vs2Power > vs1Power){
			if (report) System.out.println(vs1Name + " (" + vs1Power + ") < " + vs2Name + " (" + vs2Power + ") ");
			vs1.makeInactive();
			return -1;
		}else{
			if (report) System.out.println(vs1Name + " (" + vs1Power + ") = " + vs2Name + " (" + vs2Power + ") ");
			return 0;
		}
	}

	public int getTotalPower(Contestant[] army) {
		int totalPower = 0;
		for (Contestant c : army) {
			totalPower += c.getPower();
		}
		return totalPower;
	}

	public int getRemainingPower(Contestant[] army) {
		int remainingPower = 0;
		for (Contestant c : army) {
			if (c.getActive() > 0) remainingPower += c.getPower();
		}
		return remainingPower;
	}

	public int getNumActive(Contestant[] army) {
		int remaining = 0;
		for (Contestant c : army) {
			if (c.getActive() > 0) remaining++;
		}
		return remaining;
	}
	
}
