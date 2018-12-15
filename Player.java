import java.util.Random;
import java.util.Scanner;

public class Player{
	Scanner enter= new Scanner(System.in);
	Random ran = new Random();		//make private
	private int maxPlayerHealth;
	private int maxDamage;
	private int potions;
	private int potionsHealing;
	private int potionsDropRate; //Percentage
	private int runs;
	private int minDamageAllowed;
	
	public Player(){
		maxPlayerHealth=150;
		maxDamage=100;
		potions=3;
		potionsHealing= 120;
		potionsDropRate= 30; //Percentage
		runs=3;
		minDamageAllowed = 20;
	}
	
	//GETTERS
	public int getmaxPlayerHealth() {
		return maxPlayerHealth;
	}
	public int getMaxDamage() {
		return maxDamage;
	}
	public int getPotions() {
		return potions;
	}
	public int getHealthPotionHealing() {
		return potionsHealing;
	}
	public int getPotionDropRate() {
		return potionsDropRate;
	}
	public int getRuns() {
		return runs;
	}
	
	//SETTERS
	public void setMaxPlayerHealth(int health) {
		maxPlayerHealth = health;
	}
	public void setMaxDamage(int dmg) {
		maxDamage = dmg;
	}
	public void setPotions(int pot) {
		potions = pot;
	}
	public void setPotionsHealing(int potheal) {
		potionsHealing = potheal;
	}
	public void setPotionsDropRate(int rate) {
		potionsDropRate = rate;
	}
	public void setRuns(int r ) {
		runs = r;
	}
	
	
	public Enemy attack(Enemy e) {
		System.out.println("You attacked the " + e.getEnemy() + "!");
		enter.nextLine();
		int dmgDealt = ran.nextInt(getMaxDamage());
		int dmgTaken = ran.nextInt(Enemy.getMaxDamageEnemy());
		
		if((dmgDealt <minDamageAllowed) && (dmgTaken < minDamageAllowed)) {			//damage too less to count as a hit
			System.out.println("Both you and the enemy couldn't land an attack");
			enter.nextLine();
			return e;
		}
		else if(dmgDealt < minDamageAllowed) {			//player missed
			System.out.println("You missed");
			enter.nextLine();
			setMaxPlayerHealth(maxPlayerHealth-=dmgTaken);
			System.out.println("You took " + dmgTaken + " in retaliation!\n");
			enter.nextLine();
			return e;
		}
		
		else if(dmgTaken < minDamageAllowed) {			//enemy missed
			e.setEnemyHealth(e.getEnemyHealth()-dmgDealt);
			System.out.println("The enemy " + e.getEnemy() + " took " + dmgDealt + " damage!");
			enter.nextLine();
			System.out.println("The enemy attack missed");
			enter.nextLine();
		}
		
		else {
			System.out.println("The enemy " + e.getEnemy() + " took " + dmgDealt + " damage!");
			enter.nextLine();
			System.out.println("You took " + dmgTaken + " in retaliation!\n");
			enter.nextLine();
			setMaxPlayerHealth(maxPlayerHealth-=dmgTaken);
			e.setEnemyHealth(e.getEnemyHealth()-dmgDealt);
		}
		
		if(getmaxPlayerHealth() <= 0) {
			return e;
		}
		if(e.getEnemyHealth() <= 0) {
			System.out.println("The wild " + e.getEnemy()+ " fainted!");
			potionsDrop(e);
		}
		
		return e;
	}
	
	public void potionsDrop(Enemy e) {
		if(ran.nextInt(100)<=getPotionDropRate()) {
			int dropCount = 1+ran.nextInt(2);
			setPotions(getPotions()+dropCount);
			System.out.println("The enemy " + e.getEnemy() + " dropped something");
			enter.nextLine();
			System.out.println("You picked up " + dropCount + " health potion(s)!!");
			enter.nextLine();
			System.out.println("\tPotions remaining - " + getPotions() + "\n");
			enter.nextLine();
		}
	}
	
	public void drinkPotion(Enemy e) {
		if(potions>0) {
			System.out.println("You used a potion and healed for " + potionsHealing);
			potions--;
			enter.nextLine();
			maxPlayerHealth+=potionsHealing;
			System.out.println("\tPotions remaining - " + potions + "\n");
			enter.nextLine();
			int dmgTaken = ran.nextInt(Enemy.getMaxDamageEnemy());					//attacked while healing
			maxPlayerHealth-=dmgTaken;
			System.out.println(e.getEnemy() + " saw an opening and landed a strike for " + dmgTaken + " damage!");
			enter.nextLine();
		}
		else 
			System.out.println("Oh no...You are out of potions");
	}
		
}
