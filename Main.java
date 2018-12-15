import java.util.Scanner;

public class Main {
	static Scanner enter= new Scanner(System.in);		//System Objects
	static Scanner sc = new Scanner(System.in);
	static Player plr = new Player();
	static Enemy enm = new Enemy();
	static Main main = new Main();
	
	void checkpoint(Player plr) {		//checkpoint
		System.out.println("-------------------------------------------------------");
		enter.nextLine();
		System.out.println("You have reached the checkpoint!");
		enter.nextLine();
		System.out.println("You may rest here");
		enter.nextLine();
		plr.setRuns(3);
		System.out.println("Stamina Replenished!");
		enter.nextLine();
		if(plr.getPotions() < 5) {		//if less than initial stock remaining
			plr.setPotions(5);
			System.out.println("Potion Stock Replenished");
			enter.nextLine();
		}
		System.out.println("Potions Remaining: " + plr.getPotions());
		enter.nextLine();
	}
	
	
	int events(int stage, Player plr) {
		if(stage == 5) {		
			checkpoint(plr);
			return 5;
		}
		
		else if(stage == 10) {
			enm.bossBattle();
			return 10;
		}
		
		
		else if(stage == 11) {		//CREDITS
			enter.nextLine();
			System.out.println("CONGRATULATIONS YOU HAVE DEFEATED THE DUNGEON BOSS\n"
					+ "YOU HAVE SUCCESSFULLY ESCAPED THE DUNGEON\n"
					+ "THANK YOU FOR PLAYING");
			enter.nextLine();
			return 11;		//final level
		}
		return 0;
	}
	
	boolean canRun(Player plr, int stage) {
		if(plr.getRuns()>0) {		//return true
			System.out.println("You successfully ran away!");
			enter.nextLine();
			plr.setRuns(plr.getRuns()-1);
			stage--;
			return true;	//break;
		}
		else {		//return false
			System.out.println("You are too exhausted to run");
			enter.nextLine();
			return false;	//continue;
		}
	}
	
	boolean noEscape(boolean noEscape) {
		if(noEscape) {
			System.out.println("You try to run away");
			enter.nextLine();
			System.out.println("It's a dead end!");
			enter.nextLine();
			System.out.println("There is no escape...");
			enter.nextLine();
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {

		boolean running = true;
		boolean noEscape = false;
		int stage = 10;
		int events; 
		
		System.out.println("WELCOME TO THE DUNGEON SURVIVAL GAME!");
		enter.nextLine();
		System.out.println("IN THIS GAME YOU MUST FIGHT HORDES OF ENEMIES UNTIL \n"
				+ "YOU REACH THE END OF THE DUNGEON."
				+ "\nAT THE END YOU WILL FIND THE DUNGEON BOSS."
				+ "\nYOU MUST DEFEAT THE BOSS IN ORDER TO ESCAPE.\n"
				+ "GOOD LUCK!");
		enter.nextLine();
		
		
		while(running) {
			
			events = main.events(stage, plr);
			if(events == 10) {
				noEscape = true;
			}
			else if(events == 11) {
				running = false;
				continue;
			}
			System.out.println("-----------------------STAGE " + stage++ + "--------------------------");
			enter.nextLine();
			System.out.println("#A " + enm.getEnemy() + " has appeared!#");		//ENEMY SELECTOR
			enter.nextLine();
	
			while(enm.getEnemyHealth() > 0) {		//WHILE ENEMY IS ALIVE
				System.out.println("\t" + enm.getEnemy() + "'s HP: " + enm.getEnemyHealth());		//Enemy health																		
				System.out.println("\tYour HP: " + plr.getmaxPlayerHealth());	//Player health
				
				System.out.println("\n\tWhat will you do?");		  //OPTIONS
				System.out.println("\t1-Attack\n\t2-Drink a health Potion\n\t3-Run!");
				System.out.println("-------------------------------------------------------");
				String input = sc.nextLine();
			
				
				if(input.equals("1")) {  //ATTACK
					plr.attack(enm); 		
					if(enm.getEnemyHealth()<=0) {     //NEXT ENEMY
						break;
					}
				}

				else if (input.equals("2")) {		 //DRINK POTION
					plr.drinkPotion(enm);
				}
	
				else if(input.equals("3")) {		//RUN
					if(main.noEscape(noEscape))		//if run is used during a boss fight
						continue;
					if(main.canRun(plr, stage) == true)		//runnning possible, break current loop and go to next outer loop
						break;
					else	//no runs remaining, replay new loop
						continue;
						
				}
				
				if(plr.getmaxPlayerHealth()<1) 				//if dead	
					break;	
			}	
			
			if(plr.getmaxPlayerHealth()<=0){		//GAME OVER
				System.out.println("\nGAME OVER\nYOU MADE IT TO STAGE " + stage + "\nHowever you are unable to go any further\nYour journey ends here\nTHANK YOU FOR PLAYING");		//final screen
				running = false;
			}
			
			enm.setEnemy();					 //new enemy
			enm.rngEnemyHealth();			//Random Number Generator Enemy health     
		}
		sc.close();
		enter.close();
	}
	
}
