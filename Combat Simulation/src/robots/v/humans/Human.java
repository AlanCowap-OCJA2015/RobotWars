package robots.v.humans;

import java.util.Random;

public class Human extends Lifeform{
	private String name;

	private static String[] firstNameArray = {"Adam", "Bob", "Chuck", "Dave", "Ed", "Fred", "George", "Hansel", "Hank", "Joe", 
			"Kanye", "Liam", "Max", "Noel", "Olly", "Paddy", "Quentin", "Rick", "Sam", "Trev", "Uriah", "Vlad", "Wayne", 
			"Xavier", "Yves", "Zed", "Alice", "Beth", "Claire", "Dorris", "Eliza", "Fiona", "Gretchen", "Hannah", "Isabelle",
			"Juliet", "Kitty", "Lara", "Mary", "Nuala", "Olive", "Penelope", "Quennie", "Ruth", "Siobhan", "Tamlyn",
			"Una", "Violet", "Wanda", "Xena", "Yrsa", "Zara"};

	private static String[] secondNameArray = {"Asimov", "Blair", "Cooper", "Dickens", "Eugene", "Fredrickson", "Hoover", 
			"Grant", "Ivanovski", "Jelly", "Klump", "Lewis", "Murphy", "Nutter", "O'Brien", "Potter", "Ruddock", "Smith",
			"Tomlinson", "Urod", "Vickers", "Winterbottom", "Xandor", "Yellowbottom", "Zoltan"};
	private static String[] rank = {"Private", "Corporal", "Sergant", "Major", "General"};

	Human(){
		this.setName();
	}

	public String getName() {
		return name;
	}

	public void setName() {
		Random rand = new Random();
		this.name = Human.rank[rand.nextInt(Human.rank.length)] + " "
				+ Human.firstNameArray[rand.nextInt(Human.firstNameArray.length)] + " "
				+ Human.secondNameArray[rand.nextInt(Human.secondNameArray.length)];
	}
}