import java.util.Scanner;

public class Main {
	static Scanner enter= new Scanner(System.in);
	public static void main(String[] args) {
		
		//System Objects
		Scanner sc = new Scanner(System.in);
		Player plr = new Player();
		Enemy enm = new Enemy();

		boolean running = true;
		int stage = 0;
		
		System.out.println("WELCOME TO THE DUNGEON SURVIVAL GAME!");
		enter.nextLine();
		
		while(running) {
			System.out.println("-----------------------STAGE " + ++stage + "--------------------------");
			System.out.println("#A " + enm.getEnemy() + " has appeared!#");		//ENEMY SELECTOR
			enter.nextLine();
	
			while(enm.getEnemyHealth() > 0) {		//WHILE ENEMY IS ALIVE
				System.out.println("\t" + enm.getEnemy() + "'s HP: " + enm.getEnemyHealth());		//Enemy health																		
				System.out.println("\tYour HP: " + plr.getmaxPlayerHealth());	//Player health
				
				System.out.println("\n\tWhat will you do?");		  //OPTIONS
				System.out.println("\t1-Attack\n\t2-Drink a health Potion\n\t3-Run!");
				System.out.println("-------------------------------------------------------");
				String input = sc.nextLine();
			
				
				if(input.equals("1")) {  
					plr.attack(enm); 		//ATTACK
					if(enm.getEnemyHealth()<=0) {     		//NEXT ENEMY
						break;
					}
				}


				else if (input.equals("2")) {		 //Drink Potion
					plr.drinkPotion(enm);
				}
	
				
				else if(input.equals("3")) {
					if(plr.getRuns()>0) {
						System.out.println("You successfully ran away!");
						enter.nextLine();
						plr.setRuns(plr.getRuns()-1);
						stage--;
						break;
					}
					System.out.println("You are too exhausted to run");
					enter.nextLine();
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
