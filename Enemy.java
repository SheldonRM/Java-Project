import java.util.Random;
import java.util.Scanner;

public class Enemy {
	Scanner enter = new Scanner(System.in);
	Random ra = new Random();
	private static String[] enemyList;
	private static int maxHealthEnemy;
	private static int maxDamageEnemy;
	private int enemyHealth;
	private String enemy;
	
	public Enemy() {
		enemyList = new String[] {"Skeleton", "Zombie", "Vampire", "Werewolf", "Orc", "Poltergeist", "Demon", "Minotaur", "Goblin"};
		maxHealthEnemy= 100;
		maxDamageEnemy= 75;
		enemy = enemyList[ra.nextInt(enemyList.length)];
		enemyHealth = (1 + ra.nextInt(Enemy.getMaxHealthEnemy()));
	}
	
	public int getEnemyHealth() {
		return enemyHealth;
	}
	
	public void setEnemyHealth(int h) {
		enemyHealth = h;
	}
	
	public int rngEnemyHealth() {
		enemyHealth = (1 + ra.nextInt(Enemy.getMaxHealthEnemy()));
		return enemyHealth;
	}
	
	public void setEnemy() {
		enemy = enemyList[ra.nextInt(enemyList.length)];
	}
	
	public String getEnemy() {
		return enemy;
	}

	static public int getMaxHealthEnemy() {
		return maxHealthEnemy;
	}
	
	
	static public int getMaxDamageEnemy() {
		return maxDamageEnemy;
	}
	
	static public void setMaxHealthEnemy(int health) {
		maxHealthEnemy = health;
	}
	
	static public void setMaxDamageEnemy(int dmg) {
		maxDamageEnemy = dmg;
	}
}
