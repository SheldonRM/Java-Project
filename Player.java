import java.util.Random;

public class Player extends Enemy{
	Random ran = new Random();		//make private
	private int maxPlayerHealth;
	private int maxDamage;
	private int potions;
	private int potionsHealing;
	private int potionsDropRate; //Percentage
	private int runs;
	
	public Player(){
		maxPlayerHealth=120;
		maxDamage=100;
		potions=3;
		potionsHealing= 75;
		potionsDropRate= 20; //Percentage
		runs=3;
	}
	
	//GETTERS
	public int getmaxPlayerHealth() {
		return maxPlayerHealth;
	}
	public int getmaxDamage() {
		return maxDamage;
	}
	public int getpotions() {
		return potions;
	}
	public int gethealthPotionHealing() {
		return potionsHealing;
	}
	public int getpotionDropRate() {
		return potionsDropRate;
	}
	public int getruns() {
		return runs;
	}
	
	//SETTERS
	public void setmaxPlayerHealth(int health) {
		maxPlayerHealth = health;
	}
	public void setmaxDamage(int dmg) {
		maxDamage = dmg;
	}
	public void setpotions(int pot) {
		potions = pot;
	}
	public void setPotionsHealing(int potheal) {
		potionsHealing = potheal;
	}
	public void setpotionsDropRate(int rate) {
		potionsDropRate = rate;
	}
	public void setruns(int r ) {
		runs = r;
	}
	
	
	public int attack(String enemy, int health) {
		System.out.println("You attacked " + enemy + "!");
		int dmgDealt = ran.nextInt(maxDamage);
		int dmgTaken = ran.nextInt(getMaxDamageEnemy());
		System.out.println("The enemy " + enemy + " took " + dmgDealt + " damage!");	//Use String array thunderbolt etc
		System.out.println("You took " + dmgTaken + " in retaliation!\n");
		
		health-=dmgDealt;
		maxPlayerHealth-=dmgTaken;
		return health;
	}
	
	public void potionsDrop(String enemy) {
		System.out.println("The wild " + enemy + " fainted!");
		if(ran.nextInt(100)<=getpotionDropRate()) {
			int dropCount = 1+ran.nextInt(2);
			setpotions(getpotions()+dropCount);
			System.out.println("The enemy " + getEnemy() + " dropped something");
			System.out.println("You picked up " + dropCount + " health potion(s)!!");
			System.out.println("\tPotions remaining - " + getpotions() + "\n");
		}
	}
	
	public void drinkPotion(String enemy) {
		if(potions>0) {
			System.out.println("You used a potion and healed for " + potionsHealing);
			potions--;
			maxPlayerHealth+=potionsHealing;
			System.out.println("\tPotions remaining - " + potions + "\n");
			int dmgTaken = ran.nextInt(getMaxDamageEnemy());					//attacked while healing
			maxPlayerHealth-=dmgTaken;
			System.out.println(enemy + " saw an opening and landed a strike for " + dmgTaken + " damage!");
		}
		else 
			System.out.println("Oh no...You are out of potions");
	}
		
}
