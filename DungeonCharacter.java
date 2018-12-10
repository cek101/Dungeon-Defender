public class DungeonCharacter {
	//declares variables
	private String name;
	private int health;
	private int healthMax;
	//variable for normAtk and specialAtk
	
	// these variables didn't need to be set for setters/getters because they do not interact with Dungeon.java; used inside the class only
	private int nMinAttack;
	private int nMaxAttack;
	private int sMinAttack;
	private int sMaxAttack;
	
	//create constructor
	public DungeonCharacter(String argName, int argHealth, int argNormalMinAtk, int argNormalMaxAtk, int argSpecialMinAtk, int argSpecialMaxAtk) {
		this.name = argName;
		this.health = argHealth;
		this.healthMax = argHealth;
		this.nMinAttack = argNormalMinAtk;
		this.nMaxAttack = argNormalMaxAtk;
		this.sMinAttack = argSpecialMinAtk;
		this.sMaxAttack = argSpecialMaxAtk;
	}//end constructor

	
	//getters and setters for variables
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	

	//att damage to other actor
	public void takeDamage(int damage) {
		health = health - damage;
	}

	public boolean isDead() {
		return this.health <= 0;
	}
	public boolean isAlive() {
		return this.health > 0;
	}
	
	//method to show current name and health
	public void displayNameAndHealth() {
		System.out.println(name +" health is " + this.health + "/" + this.healthMax);
	}//end displayNameAndHealth

	public int normalAtk() {
		/*
		ran.nextInt(20); // 0 - 20
		ran.nextInt(20)+1; // 1 - 20
		*/
		// Cast the return double data type to int
		return (int) Math.round( (Math.random() * ( this.nMaxAttack - this.nMinAttack)) + this.nMinAttack );
	}
	public int specialAtk() {
		// Cast the return double data type to int
		return (int) Math.round( (Math.random() * ( this.sMaxAttack - this.sMinAttack )) + this.sMinAttack );
	}
	
	
	
}//end class