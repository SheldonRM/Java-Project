import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		//System Objects
		Scanner sc = new Scanner(System.in);
		Player plr = new Player();
		Enemy enm = new Enemy();

		boolean running = true;
		int stage = 0;
		
		System.out.println("Welcome to the world of Pokemon!");
		
		//GAME:
		while(running) {
			if(plr.getmaxPlayerHealth()>0) {
				System.out.println("-----------------------ROUND " + ++stage + "--------------------------");
				
				//POKEMON SELECTOR
				System.out.println("#A wild " + enm.getEnemy() + " has appeared!#\n");		
		
				RUN:
				while(enm.getEnemyHealth() > 0) {
					System.out.println("\t" + enm.getEnemy() + "'s HP: " + enm.getEnemyHealth());		//Enemy health																		
					System.out.println("\tYour HP: " + plr.getmaxPlayerHealth());						//Player health
					
					System.out.println("\n\tWhat will you do?");									    //OPTIONS
					System.out.println("\t1-Attack\n\t2-Drink a health Potion\n\t3-Run!");
					String input = sc.nextLine();
				
					if(input.equals("1")) {					                                            //ATTACK
						enm.setEnemyHealth(plr.attack(enm.getEnemy(), enm.getEnemyHealth()));
						//enm.enemyHealth = plr.Attack(enm.getenemy(), enm.enemyHealth);
						
						if(enm.getEnemyHealth()<=0) {				                                   //if enemy dies
							plr.potionsDrop(enm.getEnemy());
							break;
						}
						
					}
					
					else if (input.equals("2")) {												       //Drink Potion
						plr.drinkPotion(enm.getEnemy());
					}
					
					else if(input.equals("3")) {
						if(plr.getruns()>0) {
							System.out.println("You successfully ran away!");
							plr.setruns(plr.getruns()-1);
							stage--;
							break;
						}
						System.out.println("You are too exhausted to run");
						continue RUN;
						
					}
					if(plr.getmaxPlayerHealth()<1) 				//if dead	
						break;	
				}	
			}
			else {
				System.out.println("\n\tGAME OVER\n\tYOU MADE IT TO STAGE " + stage + "\n\tHowever you are unable to go any further\n\tYou crawled away to the nearest Pokemon Center\n\tTHANK YOU FOR PLAYING");		//final screen
				break;
			}
		enm.setEnemy();					 //new enemy
		enm.rngEnemyHealth();			//Random Number Generator Enemy health     
		}
	sc.close();
	}
}