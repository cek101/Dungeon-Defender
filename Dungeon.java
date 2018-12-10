/*Authors:
 * Joe Iscaro
 * Xavier Ruivivar
 * Christopher Kollias
 *  
 *  */

import java.util.Scanner;
import java.util.Random;

public class Dungeon {
	
	private static Scanner kb = new Scanner(System.in);

	public static void main(String[] args) {

		// Create the Boss Monster
		// ----------------------------------------------------------------
		Monster boss = new Monster("Selarom", 100, 20, 30, 25, 35);
		
		//set menus
		textWelcomeMenu( boss.getName() );
		

		// choices are 1 or 2
		System.out.println("\nYou have two heroes to choose from: ");
		System.out.println("1) Wizard: has lower health, but its magic is stronger than the sword. ");    
		System.out.println("2) Warrior: has high health and a mighty blade. \n ");
		int heroPicker = getInputOption("Press 1 to play as a Wizard \nPress 2 to play as a Warrior");
		
		kb.nextLine(); // used this line to clear out data type from previous input
		
		//prompt user for hero name
		System.out.println("Choose a name for your hero? ");
		String heroName = kb.nextLine();
		
		//set greeting/intro
		textYouChose(heroName);
		
		// Create the hero class based on the user's input (1=Wizard, 2=Warrior)
		Hero hero = setHeroActor(heroPicker, heroName);

		
		int numOfBattles = 3;
		
		// Loop through the number of battles, last battle will be BOSS battle
		for (int p=0; p <= numOfBattles; p++) {
			System.out.println( "\n\n\n\n" );
			
			if ( p == (numOfBattles) ) { // This is the last battle (boss)
				// The boss fight
				// ----------------------------------------------------------------
				System.out.println("The Dragon " + boss.getName() + " steps out from the shadows.\nThe Dragon says: Puny mortal, not even death can save you from me!\n");
				monsterFight(hero, boss);
				
				// Did the hero survive?
				if ( hero.isAlive() ) {
					System.out.println("\n\n\nYou have slayed the " + boss.getName()+ "  The Village is saved!  CONGRATULATIONS!!");
				} else {
					System.out.println("   ________                        ________ ");                     
					System.out.println("  /  _____/_____    _____   ____   \\_____  \\___  __ ___________ "); 
					System.out.println(" /   \\  ___\\__  \\  /     \\_/ __ \\   /   |   \\  \\/ // __ \\_  __ \\");
					System.out.println(" \\    \\_\\  \\/ __ \\|  Y Y  \\  ___/  /    |    \\   /\\  ___/|  | \\/");
					System.out.println("  \\______  (____  /__|_|  /\\___  > \\_______  /\\_/  \\___  >__|");   
					System.out.println("         \\/     \\/      \\/     \\/          \\/          \\/");  
				}
			} else {
				// Start a fight
				// ----------------------------------------------------------------
				Monster randomEnemy = setMonsterActor();
				System.out.println("A " + randomEnemy.getName() + " appears\n");
				monsterFight( hero, randomEnemy );
				
				// Terminate this loop if hero dead
				if ( hero.isDead() ) {
					break;
				}
			}
		}
			
		
												
	} //end main 

	
	// Methods begin 
	// ================================================================================
	// ================================================================================
	// ================================================================================
	// ================================================================================
	
	public static void textWelcomeMenu(String bossName) {
		
		System.out.println("________                                             ________          _____                   ___");            
		System.out.println("\\______ \\  __ __  ____    ____   ____  ____   ____   \\______ \\   _____/ ____\\____   ____    __| _/___________"); 
		System.out.println(" |    |  \\|  |  \\/    \\  / ___\\_/ __ \\/  _ \\ /    \\   |    |  \\_/ __ \\   __\\/ __ \\ /    \\  / __ |/ __ \\_  __ \\");
		System.out.println(" |    `   \\  |  /   |  \\/ /_/  >  ___(  <_> )   |  \\  |    `   \\  ___/|  | \\  ___/|   |  \\/ /_/ \\  ___/|  | \\/");
		System.out.println("/_______  /____/|___|  /\\___  / \\___  >____/|___|  / /_______  /\\___  >__|  \\___  >___|  /\\____ |\\___  >__|");   
		System.out.println("        \\/           \\//_____/      \\/           \\/          \\/     \\/          \\/     \\/      \\/    \\/");
		
		
		System.out.println("\nWell hello adventurer! ");
		System.out.println("\nA nearby village has enlisted your help in dealing with a Dragon.");
		System.out.println("Are you brave enough to enter the Dragons dungeon to kill it? ");
		System.out.println("There will be many monsters in your path. ");
		System.out.println("Beware the great evil one called " + bossName + " as she kills all who enter. \n "); 
		
	}// end welcomeMenu method
			
	// This will return an integer. 
	// Returns 1 or 2
	public static int getInputOption(String prompt) {
		int dummy = 0;
		
		do {
			System.out.println(prompt);
			try { 
				// any code inside here that errors will throw an exception,
				// it will only catch it if we specify that specific exception
				
				// Expects the user to enter an integer but will throw exception if it's a string
				dummy = kb.nextInt();
				
			} catch (java.util.InputMismatchException e) {
				dummy = -1;
				kb.next(); // fixes the prompt issue, and keeps us out of an infinite loop
			}
			
			if (dummy < 1 || dummy > 2) {
				System.out.println("Invalid input.  Only numbers 1 or 2 please.");
			} 
			
		} while (dummy < 1 || dummy > 2); // This will continue loop as long as it's true
		

		return dummy;
	}// end textChooseCharMenu method
	
	public static void textYouChose(String heroName) {
		System.out.println("\nWelcome " +heroName);
		System.out.println("You have chosen well " );
		System.out.println("Good Luck \n ");		
	}//end textYouChose method
	
	
	// sets and object with hero values as chosen by user
	// (1=Wizard, 2=Warrior)
	public static Hero setHeroActor(int heroPicker, String heroName) {
		Hero dummy = null;

		switch (heroPicker) {
			case 1:
				dummy = new Hero( heroName + " (Wizard)", 100, 17, 20, 23, 27 );
				break;	
			case 2:
				dummy = new Hero( heroName + " (Warrior)", 200, 10, 15, 20, 25 );
				break;
		}
		
		return dummy;
	}//end setHeroActor method
	
	public static Monster setMonsterActor() {
		Random ran = new Random();
		int monster = ran.nextInt(3)+1;
		Monster dummy = null;

		switch (monster) {
			case 1: //monster, health, normalMinAttack, NormalMaxAttack, SpecialMinAttack, SpecialMaxAttack
				dummy = new Monster("Troll", 50, 10, 14, 15, 19);
				break;
						
			case 2:
				dummy = new Monster("Goblin", 25, 5, 8, 8, 12);
				break;
			
			case 3:
				dummy = new Monster("Ogre", 75, 10, 15, 20, 25);
				break;
					
		}//end switch
		
		return dummy;
	}//end setMonsterActor method
	
	// Declaring the method or defining the method => creating the method
	// whatever is in the parenthesis is called ARGUMENTS (data that the method is suppose to get)
	public static void monsterFight(Hero hero, Monster enemy) {
		System.out.println("What will you do?");
				
		hero.displayNameAndHealth();
		enemy.displayNameAndHealth();
		
		// control loop variable
		boolean keepFighting = true;
		 do {	
			// Hero attacks monster
			int endam;
			String endamType = "";
						
			int attackChoice = getInputOption("\nPress 1 for Normal Attack \nPress 2 for Special Attack");
			
			if (attackChoice == 1 ) {
				// do normal attack
				endam = hero.normalAtk();
			} else {
				// special attack
				endamType = " special";
				endam = hero.specialAtk();
			}
			
			enemy.takeDamage(endam);
			System.out.println( "\n" + hero.getName() + " hits "  + enemy.getName() + " for " + endam + endamType + " damage"  );
			
			
			if (enemy.isDead()) {
				// dead
				System.out.println("Hooray the " + enemy.getName() + " is slain!!! ");
				keepFighting = false;
				break;
				
			} else { // Monster's turn to attack
				// it's alive				
				int herodam = enemy.normalAtk(); 
				hero.takeDamage(herodam); 
				System.out.println("Oof....." + enemy.getName() + " hits " + hero.getName()  + " for " + herodam + " damage\n" );
				
				hero.displayNameAndHealth();
				enemy.displayNameAndHealth();
				
				if (hero.isDead()) {
					System.out.println("\n" + hero.getName() + " has been slain....\n");
					keepFighting = false;
					break;
				}

			}
			
		}  while ( keepFighting ); // continue if hero or enemy still has health > 0
	}
	
}//end Dungeon class