package robots.v.humans;

import java.util.Scanner;

public class GameApp {

	int robotWinCount = 0;
	int humanWinCount = 0;
	int totalHumanPower = 0;
	int totalRobotPower = 0;
	int totalDraws = 0;
	int robotUnitCount = 500;
	int humanUnitCount = 500;
	int totalRounds = 0;

	Robot[] robotArray = new Robot[500];
	Human[] humanArray = new Human[500];

	public static void main(String[] args) {	
		GameApp newGame = new GameApp();
		newGame.runProgram();

	}

	public void runProgram(){
		Scanner scan = new Scanner(System.in);

		combatantInit();		

		System.out.println("======== Welcome to Robo Wars Combat Simulation 2015 ========" +
				"\n" + "-------------------------------------------------------------" +
				"\n" + "Please pick your game mode:" +
				"\n" + "1.Skirmish" +
				"\n" + "2.Last team standing");

		int gameChoice = scan.nextInt();

		System.out.println();
		switch(gameChoice){
		case 1: skirmish(); break;
		case 2: lastManStanding(); break;
		}

	}

	public void lastManStanding(){
		for(int i = 0, j = 0, k = 0; i < 500 && j < 500; ++k){
			System.out.println("================ Round " + (k + 1) + " ================");
			if(humanArray[i].getPowerLevel() > robotArray[j].getPowerLevel()){
				System.out.println("---------- Humans won the round! ----------");
			}else if(humanArray[i].getPowerLevel() < robotArray[j].getPowerLevel()){
				System.out.println("---------- Robots won the round! ----------");
			}else{
				System.out.println(" Draw!");
			}
			System.out.println(" " + humanArray[i].getName() + " has a power of " + humanArray[i].getPowerLevel());
			System.out.println(" " + robotArray[j].getModelName() + " has a power of " + robotArray[j].getPowerLevel());

			if(humanArray[i].getPowerLevel() > robotArray[j].getPowerLevel()){
				totalHumanPower = totalHumanPower + humanArray[i].getPowerLevel();
				humanArray[i].damage();
				humanWinCount++;
				robotUnitCount--;
				j++;
			}else if(humanArray[i].getPowerLevel() < robotArray[j].getPowerLevel()){
				totalRobotPower = totalRobotPower + robotArray[j].getPowerLevel();
				robotArray[j].damage();
				robotWinCount++;
				humanUnitCount--;
				i++;
			}else if(humanArray[i].getPowerLevel() == robotArray[j].getPowerLevel()){
				totalHumanPower = totalHumanPower + humanArray[i].getPowerLevel();
				totalRobotPower = totalRobotPower + robotArray[i].getPowerLevel();
				totalDraws++;
				robotUnitCount--;
				humanUnitCount--;
				i++;
				j++;
			}
			totalRounds = k + 1;
			System.out.println(" Robots have " + robotUnitCount + " units left.");
			System.out.println(" Humans have  " + humanUnitCount + " units left.");
			System.out.println("===========================================\n");


		}
		if(humanUnitCount == 0){
			System.out.println("==========================================="
					+ "\n ------------- Robots Won!!! ------------- "
					+ "\n  Total power of Robots: " + totalRobotPower
					+ "\n  Total Robot wins: " + robotWinCount
					+ "\n  Total Human wins: " + humanWinCount
					+ "\n  Total Robots left: " + robotUnitCount
					+ "\n  Total Rounds played: " + totalRounds
					+ "\n  Total draws: " + totalDraws);
			System.out.println("===========================================");
		}else if(robotUnitCount == 0){
			System.out.println("==========================================="
					+ "\n ------------- Humans Won!!! ------------- "
					+ "\n  Total power of Humans: " + totalHumanPower
					+ "\n  Total Human wins: " + humanWinCount
					+ "\n  Total Robot wins: " + robotWinCount
					+ "\n  Total Humans left: " + humanUnitCount
					+ "\n  Total Rounds played: " + totalRounds
					+ "\n  Total draws: " + totalDraws);
			System.out.println("===========================================");
		}else{
			System.out.println("Draw!");	
		}


	}

	public void skirmish(){

		for(int i = 0; i < 500; ++i){

			if(humanArray[i].getPowerLevel() > robotArray[i].getPowerLevel()){
				humanWinCount++;
				
				totalHumanPower = totalHumanPower + humanArray[i].getPowerLevel();
			}else if(humanArray[i].getPowerLevel() == robotArray[i].getPowerLevel()){
				totalDraws++;
				
			}else{
				
				robotWinCount++;
				totalRobotPower = totalRobotPower + robotArray[i].getPowerLevel();
			}
			robotUnitCount--;
			humanUnitCount--;

			System.out.println("================ Round " + (i + 1) + " ================");
			if(humanArray[i].getPowerLevel() > robotArray[i].getPowerLevel()){
				System.out.println("---------- Humans won the round! ----------");
			}else if(humanArray[i].getPowerLevel() < robotArray[i].getPowerLevel()){
				System.out.println("---------- Robots won the round! ----------");
			}else{
				System.out.println(" Draw!");
			}
			System.out.println(" " + humanArray[i].getName() + " has a power of " + humanArray[i].getPowerLevel());
			System.out.println(" " + robotArray[i].getModelName() + " has a power of " + robotArray[i].getPowerLevel());
			System.out.println(" Robots have " + robotUnitCount + " units left.");
			System.out.println(" Humans have  " + humanUnitCount + " units left.");
			System.out.println("===========================================\n");
			totalRounds = i + 1;
		}

		if(robotWinCount > humanWinCount){
			System.out.println("==========================================="
					+ "\n ------------- Robots Won!!! ------------- "
					+ "\n  Total power of Robots: " + totalRobotPower
					+ "\n  Total Robot wins: " + robotWinCount
					+ "\n  Total Human wins: " + humanWinCount
					+ "\n  Total Rounds played: " + totalRounds
					+ "\n  Total draws: " + totalDraws);
			System.out.println("===========================================");
		}else if(robotWinCount == humanWinCount){
			System.out.println("Draw!");
		}else{
			System.out.println("==========================================="
					+ "\n ------------- Humans Won!!! ------------- "
					+ "\n  Total power of Humans: " + totalHumanPower
					+ "\n  Total Human wins: " + humanWinCount
					+ "\n  Total Robot wins: " + robotWinCount
					+ "\n  Total Rounds played: " + totalRounds
					+ "\n  Total draws: " + totalDraws);
			System.out.println("===========================================");
		}
	}

	public void combatantInit(){
		for(int i = 0; i < humanArray.length; ++i){
			robotArray[i] = new Robot();
			humanArray[i] = new Human();
		}
	}
}