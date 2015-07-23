/*
 * Thomas McSherry, Mubasher 
 */
package thomasmcsherry.mubashermohammed.game.codechallenge.robotsvshumans;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameApp {

	Human[] humans = new Human[500];
	Robot[] robots = new Robot[500];
	
	ArrayList<String> names = new ArrayList<>();

	int humanWins = 0;
	int robotWins = 0;
	int draws = 0;
	int robotTotalPower = 0;
	int humanTotalPower = 0;


	public static void main(String[] args) {
		GameApp game = new GameApp();
		game.runProgram();

	}

	public void runProgram(){
		loadNames();
		int userChoice = 0;

		do{
			Scanner inputScanner = new Scanner(System.in);
			System.out.println("Welcome");
			System.out.println("1) Single Battle Mode");
			System.out.println("2) Extinction Battle Mode");
			System.out.println("3) Exit");
			System.out.print("Please enter a number for your choice:");
			
			try{
				userChoice = inputScanner.nextInt();
				
				if(userChoice != 1 && userChoice != 2 && userChoice != 3){
					throw new Exception();
				}
				
			}catch(Exception e){
				System.out.println("Invalid input, try again.\n");
				continue;
			}
		
			if(userChoice == 1){
				singleBattleMode();
			}else if(userChoice == 2){
				extinctionBattleMode();
			}
			
			
		}while(userChoice != 3);
		System.out.println("Exiting...");

	}
	
	public void loadNames(){
		try {
			Scanner nameLoader = new Scanner(new File("names.txt"));
			while(nameLoader.hasNext()){
				names.add(nameLoader.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void extinctionBattleMode(){
		
		initArrays();
		
		System.out.println("Starting extinction battle...");
		
		for(int i = 0; i < humans.length; i++){
			
			for(int j = 0; j < robots.length; j++){
				if(robots[j].isAlive()){
					
					System.out.println(humans[i].getName() + "(" + humans[i].getPower() + ") vs " + robots[j].getModel() + "(" + robots[j].getPower() + ")");
					
					if(humans[i].getPower() > robots[j].getPower()){
						robots[j].die();
						System.out.println(humans[i].getName() + " wins.");
						humans[i].setPower(humans[i].getPower() - 0.5D);
						
					}else if(robots[j].getPower() > humans[i].getPower()){
						humans[i].die();
						System.out.println(robots[j].getModel() + " wins.");
						robots[j].setPower(robots[j].getPower() - 0.5D);
						break;
					}else{
						System.out.println("Both " + humans[i].getName() 
								+ " and " + robots[j].getModel() + " died.");
						humans[i].die();
						robots[j].die();
						break;
					}
				}
			}
		}
		
		
		int humansAlive = 0;
		int robotsAlive = 0;
		
		for(int i = 0; i < humans.length; i++){
			
			if(humans[i].isAlive()){
				humansAlive++;
			}
			if(robots[i].isAlive()){
				robotsAlive++;
			}
			
		}
		if(humansAlive == 0){
			System.out.println("\nRobots win with " + robotsAlive + " survivors.\n");
		}else{
			System.out.println("\nHumans win with " + humansAlive + " survivors.\n");
		}
		
	}
	public void singleBattleMode(){
		
		initArrays();

		System.out.println("Battle starting...");

		for(int i = 0; i < humans.length; i++){

			robotTotalPower += robots[i].getPower();
			humanTotalPower += humans[i].getPower();

			System.out.println(humans[i].getName() + "(" + humans[i].getPower() + ") vs " + robots[i].getModel() + "(" + robots[i].getPower() + ")");

			if(humans[i].getPower() > robots[i].getPower()){
				System.out.println(humans[i].getName() + " wins.");
				humanWins++;
			}else if(robots[i].getPower() > humans[i].getPower()){
				System.out.println(robots[i].getModel() + " wins");
				robotWins++;
			}else{
				System.out.println("Draw.");
				draws++;
			}

		}

		System.out.println("\n\n\nHuman: Wins " + humanWins + ", Draws " + draws 
				+ ", Losses " + (humans.length - draws - humanWins));

		System.out.println("Robot: Wins " + robotWins + ", Draws " + draws
				+ ", Losses " + (robots.length - draws - robotWins));

		if(robotWins > humanWins){
			System.out.println("The robots have won the battle.");
		}else if(humanWins > robotWins){
			System.out.println("The humans have won the battle.");
		}else{
			System.out.println("The battle was a draw.");
		}

		System.out.println("Robot Total Power: " + robotTotalPower);
		System.out.println("Human Total Power: " + humanTotalPower);

	}

	public void initArrays(){

		for(int i = 0; i < humans.length; i++){
			humans[i] = new Human(names.get(i));
			if(i % 2 == 0){
				robots[i] = new Robot("Type A" + i);
			}else{
				robots[i] = new Robot("Type B" + i);
			}
		}
	}

}
