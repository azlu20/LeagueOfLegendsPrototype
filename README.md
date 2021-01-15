Documentation Updated and Journal can be found here: https://gowustl-my.sharepoint.com/:w:/g/personal/a_z_lu_wustl_edu/EZeWjgCVCvZJjWre-Y8-TuQB2OluoNMxaKgvvLPb01Y0MA?e=Ofn0Dn



Documentation of Created Objects 

Interfaces 

Unit: 

An interface so that everything can exist on the same rift. A unit is a length in a single direction that can hold either an empty traversable space, a minion, a Champion, terrain, or a turret. This unit allows for all these objects to exist on the same rift as they are inheritors from this interface. 

 

Unit Methods: 

 

public String toString(); 

Making sure that the printing of the board can be viewed. 

 

public boolean canCS(); 

This method checks to see if object that has slain a minion is eligible to receive gold and exp. The only objects that should be receive this are Champions. 

 

	public int getXloc(); 

	public int getYloc() 

	Methods used to get the position of the Unit. 

	 

	public boolean canTarget(Unit dealer) 

	If the dealer Unit can target the current object. This should account for being on same team and checks overall if the object should be able to be targeted. For example, a wall should not be able to be targeted. 

 

	public String getTeam(); 

	Returns the team if there is a team for the Unit 

	 

	public int takeDamage(Unit dealer, int damage, String type);  

If it can take damage, calculate the damage. Otherwise it returns a -1. 

 

public int receiveGold(int gold); 

If it can receive gold, receive the amount otherwise return a -1. 

 

Items: (WIP) 

Item Methods: 

	public void giveStats(Unit champ) 

	Checks for the stat increases for the champ. 

 

public void sell(Unit champ) 

Returns the sell value back to the champ and deletes itself. 

 

public void putInventory(Unit champ) 

The champ buys from the shop and puts the item into one of the inventory slots (positions in the array). Then giveStats() is called to check for how much stats is given. 

 

public boolean buildsInto(Items item) 

Reduces the price of the items that it builds into by the buy price of the current item. 

Classes 

 

Rift: 

Rift is public class that League takes place in. It holds a board of 16000x16000 Units, so that everything can exist in this 2D Array. Each Space is instantiated so that they are not null. See more in instantiating space below. In addition, the rift also has a boolean winner that decides if the game is over or not. Finally, the rift also has a timer that is the ingame counter.	 

 

Rift Variables: 

	Unit board [][]; 

	private boolean winner; 

	private final long time = System.currentTimeMillis(); 

	private ArrayList<Unit> spawn1= new ArrayList<Unit>(); //minion waves without cannon 

	private ArrayList<Unit> spawn2 = new ArrayList<Unit>(); //minion waves with cannon 

	private ArrayList<Unit> spawn3 = new ArrayList<Unit>(); //Super Minion waves 

	public ArrayList<Unit> blueteam = new ArrayList<Unit>(); 

 

 

 

Rift Methods: 

Rift() 

The constructor that instantiates the board and the spaces in the board. Also sets the winner to false, so the game will start. Also creates the stacks of the minion waves that will be spawned using spawnBlueTeam() or spawnRedTeam() 

 

public void spawnBlueTeam() (WIP) 

Will alternate between spawning regular waves and cannon and super minion waves when inhibitors are broken. Currently only creates minions. 

public void endgame()  

Sets winner to true so the game will end. 

 

public int getAgeInSeconds() 

Returns the current running time in an integer. 

 

public boolean isWinner() 

public void setWinner(boolean winner) 

Getters Setters for winner variable. 

Space: 

The most basic Unit, it hosts nothing and can be traversed. 

Space Class Variables: 

	boolean exist; 

	private int xloc;  

	private int yloc; 

	Unit board; 

	private boolean canTarget; 

 

Space Methods: 

	public Space(int y, int x) 

		- Sets up the location of the space 

Space Getters/Setters: 

	public int getXloc() { 

	return xloc; 

} 

public void setXloc(int xloc) { 

	this.xloc = xloc; 

} 

public int getYloc() { 

	return yloc; 

} 

public void setYloc(int yloc) { 

	this.yloc = yloc; 

} 

public String toString() { 

	return " | "; 

} 

  

  

@Override 

public boolean canCS() { 

	return false; 

} 

MeleeMinion: 

Melees are the first minion coded. They have variable HP, armor, and Attack through game time otherwise their variables are constant.  

 

	public Rift rift; 

	public int health; 

	private int ms; 

	private int armor; 

	private int mr; 

	private int xloc; 

	private int yloc; 

	private int exp; 

	private int goldvalue; 

	private double attackspeed; 

	private int attackvalue; 

	private int attackrange; 

	private boolean canTarget; 

	private String team;  

Just standard stats of a minion. Rift is the rift that it exists on. 

 

MeleeMinion Methods: 

MeleeMinion(Rift rift) 

-	Constructor to set up the upgrades that it will receive throughout the game time. Also sets the stats for the other variables. 

 

public int takeDamage(Unit dealer, int dmg, String type) { 

- Uses a switch statement for the type of damage taken. These then point to helper methods to calculate the specific damage 

-Decrements the HP by the damage taken 

- Checks to see if this unit has perished (below 0 hp), if so goes to the onDeath() helper method. 

public void onDeath(Unit killer) 

	- Method that sets the space that the unit is on back to a regular space Unit. (WIP) will give the killer gold and increment CS counter if killer canCS (or i.e receive gold). 

	 

	public void pathing(Unit target)  

		- (WIP) Current usable version is testpathing().  

		- Finds the unit vector to the target and scales it up to the movement speed. 

		- Travels to that location until this object can attack() the target 

		- TODO: still need to code in the walls and how pathing works for walls. 

		- Side note: I believe the in game version of minions have specific positions during the spawning and walking to base phase, but I will likely not be using that method. 

 

	public void attack(Unit dealt)  

		- This method deals damage to the targeted unit, and triggers for that unit to run a damage calculation on itself 

		- Waits after a little bit using the attack speed calculation between hits 

 

	public int distanceTo(Unit target)  

		- Simple helper method that calculates distance to a target 

	public Unit detectionRange()  

		- Searches in a circle if there is an unit on that is targetable (on the opposite team and other conditions). 

		- If there is such unit, return that Unit so that it can be targeted using pathing and then attack. 

		- The way this searches should locate the closest enemy 

 

public int calculatePhysicalDamage(Unit dealer, int flatdmg)  

public int calculateMagicDamage(Unit dealer, int flatdmg) { 

		- helpers for takeDamage() 

		- uses armor and mr respectively to calculate how much damage scaled should be taken 

	Getter/Setter and Methods for All Units: 

	public int getMs() { 

		return this.ms; 

	} 

	public void setMs(int ms) { 

		this.ms = ms; 

	} 

	public int getXloc() { 

		return xloc; 

	} 

	public void setXloc(int xloc) { 

		this.xloc = xloc; 

	} 

	public int getYloc() { 

		return yloc; 

	} 

	public void setYloc(int yloc) { 

		this.yloc = yloc; 

	} 

	public int getArmor() { 

		return armor; 

	} 

	public void setArmor(int armor) { 

		this.armor = armor; 

	} 

	public int getMr() { 

		return mr; 

	} 

	public void setMr(int mr) { 

		this.mr = mr; 

	} 

	public int getExp() { 

		return exp; 

	} 

	public void setExp(int exp) { 

		this.exp = exp; 

	} 

	public int getGoldvalue() { 

		return goldvalue; 

	} 

	public void setGoldvalue(int goldvalue) { 

		this.goldvalue = goldvalue; 

	} 

	@Override 

	public boolean canCS() { 

		// TODO Auto-generated method stub 

		return false; 

	} 

	public double getAttackspeed() { 

		return attackspeed; 

	} 

	public void setAttackspeed(double d) { 

		this.attackspeed = d; 

	} 

	public int getAttackvalue() { 

		return attackvalue; 

	} 

	public void setAttackvalue(int attackvalue) { 

		this.attackvalue = attackvalue; 

	} 

	public int getAttackrange() { 

		return attackrange; 

	} 

	public void setAttackrange(int attackrange) { 

		this.attackrange = attackrange; 

	} 

	@Override 

	public boolean canTarget(Unit dealer) { 

		// TODO Auto-generated method stub 

		if(dealer.getTeam().equals(this.getTeam())) 

		return false; 

		if(this.canTarget == false) 

			return false; 

		return true; 

	} 

		- This method checks if the targeter of this minion is on its team and other conditions. 

	public void changeCanTarget() { 

		this.canTarget = !this.canTarget; 

	} 

  

	public void setTeam(String team) { 

		this.team = team; 

	} 

	public String toString() { 

		return "" + this.health; 

	} 

	@Override 

	public String getTeam() { 

		// TODO Auto-generated method stub 

		return this.team; 

	} 

	public int receiveGold(int gold) { 

		return -1; 

	} 

		- This method returns -1, as the melee minion cannot receive gold 

 

Ranged Minions: 

	- Much of the same methods and logic used in Melee minion however with different base stats and different scaling conditions changed in the constructor. To spare the repetition, this part was omitted. 

 

Zed (Champion): (WIP) 

	- The first champion I decided to code as it has some unique mechanics with its shadow that I wanted to try out making.  

	- As it has such  a heavy dependence of Shadow, I will cover the shadow and other projectile Zed uses in this same section. 

	Class Variables: 

	public Rift rift; 

	public int health; 

	private int xloc; 

	private int yloc; 

	private int attackspeed; 

	private int ms; 

	private int armor; 

	private int mr; 

	private int armorpen; 

	private int lethality; 

	private Items[] items; 

	private int energy; 

	private int energyregen; 

	private int attackrange; 

	private String team; 

	private int critrate; 

	private boolean canTarget = true; 

	private int qmax; 

	private int wmax; 

	private int emax; 

	private int rmax; 

	private int summoner1; 

	private int summoner2; 

	private boolean shadowout =false; 

	private Shadow wShadow; 

	private Shadow rShadow; 

	private int currentgoldvalue; 

	private int curlevel; 

	private int gold; 

	private Items[] inventory; 

	public HashMap<Integer, Integer> goldvalues; 

	public HashMap<Integer, Integer> qDamage; 

	- Not much to note about these except for that these are just basic stats and attributes the champ has. Qmax, wmax .. etc. Are used to see what abilities are leveld up and are used to calculate the cooldown,  damage, and etc. About the abilities. 

	- The HashMap are used to access the how much gold value Zed is worth after getting kills/or being killed (with my Zed, likely the former). 

	- The inventory array is used to store items and when bought would add value to Zed's stats (TODO) 

Zed's Methods: 

Zed(Rift rift, String team, int yloc, int xloc) (WIP) 

		- The constructor for the champion that sets up the stats, the level, and points to the rift that it exists on. 

		- Also sets up the the HashMap for the gold values and other values such as qlevel up etc. 

		- Also sets up the inventory space to be blanks so that items can be bought 

	public void wAbility(int yloc, int xloc)  

		- As a large portion of Zed's kit revolves around having the shadows out, the w ability will be covered first followed by the shadow class.  

		- This method is the w ability that sends a shadow out at the designated location. It then sets the boolean for wShadowOut to be True so that if another ability such as qAbility or eAbility is used the shadow will mimic it. 

		- The shadow stays on the board for 5 seconds. 

	 

	Shadow Class: 

	- This class is the shadow that mimics Zed's abilities. Rather then having the shadow cast the abilities, the abilities will be cast from Zed's class and placed upon the shadow locations. 

	public int xloc; 

	public int yloc; 

	public int damage; 

	public Rift rift; 

	public int travelspeed; 

	public String team; 

		- Basic information about the shadow.  

	Shadow(Rift rift, String team, int yloc, int xloc, int damage) 

		- The constructor that sets up the basic information 

	public void travel(Unit target) 

		- Travels up to a certain speed and as far as possible to the target unless the target is within the targetable range. 

	public int distanceTo(Unit target) 

		- Uses distanceTo to calculate travel distance, on going distance, etc. 

	Shadow Getter/Setters/Unit Interface Methods: 

	public int getXloc() { 

		// TODO Auto-generated method stub 

		return this.xloc; 

	} 

	@Override 

	public int getYloc() { 

		// TODO Auto-generated method stub 

		return this.yloc; 

	} 

	@Override 

	public boolean canCS() { 

		// TODO Auto-generated method stub 

		return false; 

	} 

	@Override 

	public boolean canTarget(Unit dealer) { 

		// TODO Auto-generated method stub 

		return false; 

	} 

	@Override 

	public String getTeam() { 

		// TODO Auto-generated method stub 

		return this.team; 

	} 

	public int takeDamage(Unit dealer, int damage, String type) { 

		// TODO Auto-generated method stub 

		return 0; 

	} 

	public int receiveGold(int gold) { 

		return -1; 

	} 

	Back to Zed's Methods 

	public void qAbility(int yloc, int xloc) 

		- This the Zed's Q or the shuriken 

		- One of the shurikens originates from Zed's body while the other originates the shadow if the shadow is out (dictated by the boolean changed from the wAbility and rAbility. 

		- The shurikens are created and the damage is calculated by accessing the HashMap for qlevelUps. The shurikens are then shot out from the location of the base body and the wAbility if it is out 

		- After travelling, the shurikens become Space Units once again 

public void qLevel() 

	- Level up the q ability if it is not at max 

public int distanceTo(Unit target) 

	- Standard distanceTo method 

Public takeDamage() 

	- Same as Melee Minions 

public void onDeath(Unit dealer) 

	-As dying as a champion is a bit different then a minion dying, there are some parts of this method that is changed. 

	- Most notably, is the gold value given to the dealer. This value is obtained by taking the current gold value and getting the next gold value in the HashMap for gold value. Then setting the current gold value to the next gold value.  

	- Then there is the death timer, this is obtained using the helper method getDeathTimer(). 

	- Finally after the death timer, the champion is respawned() 

Zed's Getters/Setters/Unit Methods: 

public boolean getShadowout() { 

		return this.shadowout; 

	} 

	public void setShadowout(boolean shadowout) { 

		this.shadowout = shadowout; 

	} 

  

	public int getXloc() { 

		return xloc; 

	} 

  

	public void setXloc(int xloc) { 

		this.xloc = xloc; 

	} 

  

	public int getYloc() { 

		return yloc; 

	} 

  

	public void setYloc(int yloc) { 

		this.yloc = yloc; 

	} 

  

	public int getAttackspeed() { 

		return attackspeed; 

	} 

  

	public void setAttackspeed(int attackspeed) { 

		this.attackspeed = attackspeed; 

	} 

  

	public int getMs() { 

		return ms; 

	} 

  

	public void setMs(int ms) { 

		this.ms = ms; 

	} 

  

	public int getArmor() { 

		return armor; 

	} 

  

	public void setArmor(int armor) { 

		this.armor = armor; 

	} 

  

	public int getMr() { 

		return mr; 

	} 

  

	public void setMr(int mr) { 

		this.mr = mr; 

	} 

  

	public int getArmorpen() { 

		return armorpen; 

	} 

  

	public void setArmorpen(int armorpen) { 

		this.armorpen = armorpen; 

	} 

  

	public int getLethality() { 

		return lethality; 

	} 

  

	public void setLethality(int lethality) { 

		this.lethality = lethality; 

	} 

  

	public Items[] getItems() { 

		return items; 

	} 

  

	public void setItems(Items[] items) { 

		this.items = items; 

	} 

  

	public int getEnergy() { 

		return energy; 

	} 

  

	public void setEnergy(int energy) { 

		this.energy = energy; 

	} 

  

	public int getAttackrange() { 

		return attackrange; 

	} 

  

	public void setAttackrange(int attackrange) { 

		this.attackrange = attackrange; 

	} 

  

	public int getEnergyregen() { 

		return energyregen; 

	} 

  

	public void setEnergyregen(int energyregen) { 

		this.energyregen = energyregen; 

	} 

  

	@Override 

	public boolean canCS() { 

		// TODO Auto-generated method stub 

		return true; 

	} 

  

	@Override 

	public boolean canTarget(Unit dealer) { 

		// TODO Auto-generated method stub 

		if(dealer.getTeam().equals(this.getTeam())) 

		return false; 

		if(this.canTarget == false) 

			return false; 

		return true; 

	} 

  

	@Override 

	public String getTeam() { 

		// TODO Auto-generated method stub 

		return this.team; 

	} 

  

	public void setTeam(String team) { 

		this.team = team; 

	} 

  

	public int getCritrate() { 

		return critrate; 

	} 

  

	public void setCritrate(int critrate) { 

		this.critrate = critrate; 

	} 

	public int getCurrentgoldvalue() { 

		return currentgoldvalue; 

	} 

	public void setCurrentgoldvalue(int currentgoldvalue) { 

		this.currentgoldvalue = currentgoldvalue; 

	} 

	public int getCurlevel() { 

		return curlevel; 

	} 

	public void setCurlevel(int curlevel) { 

		this.curlevel = curlevel; 

	} 

	public int getQmax() { 

		return qmax; 

	} 

	public void setQmax(int qmax) { 

		this.qmax = qmax; 

	} 

	@Override 

	public int receiveGold(int gold) { 

		// TODO Auto-generated method stub 

		this.setGold(gold+this.getGold()); 

		return 1; 

	} 

	public int getGold() { 

		return gold; 

	} 

	public void setGold(int gold) { 

		this.gold = gold; 

	} 

 

NexusTurrets: 

	Coded basically like a minion except for different stats and does not traverse. 

 

Longsword (IN PROGRESS): 
