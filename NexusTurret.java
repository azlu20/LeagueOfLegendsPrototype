package League;

import java.util.concurrent.TimeUnit;

public class NexusTurret implements Unit {
	private int attackrange;
	private int attackdamage;
	public int health;
	private int xloc;
	private int yloc;
	private int armor;
	private int mr;
	private int attackspeed;
	private String team;
	public Rift rift;
	private boolean destroyed;
NexusTurret(Rift rift, int yloc, int xloc, String team){

	this.rift = rift;
	this.setXloc(xloc);
	this.setYloc(yloc);
	this.setTeam(team);
	this.health = 2700;
	this.setAttackspeed(1);
	this.setArmor(70);
	this.setMr(70);
	this.setAttackdamage(150);
	this.updateAttackdamage();
}
public void updateAttackdamage() { 
	if(this.destroyed) {
		return ;
	}
	int scaling = rift.getAgeInSeconds();
	int upgrade = scaling%60; 
	if(upgrade == 3) {
		this.setAttackdamage((int) (150+9));
		try {
			TimeUnit.MILLISECONDS.sleep(1000*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(upgrade > 3) {
		this.setAttackdamage((this.getAttackdamage()+9*upgrade));
		if(upgrade > 17) {
			this.setAttackdamage(285);
		}
		try {
			TimeUnit.MILLISECONDS.sleep(1000*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
public void onDeath(Unit dealer) {
	String teams = dealer.getTeam();
	if(teams.equals("blue")){
		for(int i =0; i < xloc; i++) { //temp until champs are put in
			;
		}
	}

}
public void attack(MeleeMinion dealt) {
	//Checks if it can do damage to unit after move.
	while(dealt.health >0 && this.inRange(dealt) ) {
	dealt.calculatePhysicalDamage(this, this.getAttackdamage());
	try {
		TimeUnit.MILLISECONDS.sleep((int)1000*this.attackspeed/60);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
public boolean inRange(Unit dealt) {
	int range = this.getAttackrange();

	for(int i = xloc-range; i <= xloc+range; i++) {
		for(int j = yloc-range; j <= yloc + range; j++) {
			if((i-xloc)*(i-xloc) + (j-yloc)*(j-yloc) <= range*range) {
				if(i >= 0 && j >=0 && i < this.rift.board.length && j < this.rift.board[0].length) {
					if(dealt.getXloc() == i && dealt.getYloc() == j) {
						return true;
					}
					}
				}
		}
	}
	return false;
}
public void regenerate() {
	int regen = 6;
	boolean thirtythree = false;
	boolean sixtysix = false;
	boolean full = false;
	if(this.health <= 2700/3) {
		thirtythree = true;
	}
	else if(this.health <= 2*2700/3) {
		sixtysix = true;
	}
	else if(this.health <= 2700) {
		full = true;
	}
	if(thirtythree == true) {
		while(this.health < 2700/3) {
			this.health+= regen;
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ;
	}
	if(sixtysix == true) {
		while(this.health < 2700*2/3) {
			this.health+= regen;
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ;
	}
	if(full == true) {
		while(this.health < 2700*2/3) {
			this.health+= regen;
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.health = 2700;
		return ;
	}

}
public Unit target() {
	int range = this.getAttackrange();
	for(int i = xloc-range; i <= xloc+range; i++) {
		for(int j = yloc-range; j <= yloc + range; j++) {
			if((i-xloc)*(i-xloc) + (j-yloc)*(j-yloc) <= range*range) {
				if(i >= 0 && j >=0 && i < this.rift.board.length && j < this.rift.board[0].length) {
				Unit searched = rift.board[j][i];
				if(searched.canTarget(this) ) {
					return searched;
				}
				}
			}
		}
	}
	return null;
}
public void calculatePhysicalDamage(Unit dealer, int flatdmg) {
	if(this.getArmor() >= 0) {
		int newdmg = flatdmg * 100/(100+this.getArmor());
		 this.takeDamage(dealer, newdmg);
	}
	else {
	int newdmg = flatdmg * (2- (100/100-this.getArmor()));
	 this.takeDamage(dealer, newdmg);
	}
}
public void calculateMagicDamage(Unit dealer, int flatdmg) {
	if(this.getMr() >= 0) {
		int newdmg = flatdmg * 100/(100+this.getMr());
		 this.takeDamage(dealer, newdmg);
	}
	else {
	int newdmg = flatdmg * (2- (100/100-this.getMr()));
	 this.takeDamage(dealer, newdmg);
	}
}
public void takeDamage(Unit dealer, int dmg) {
	this.health -= dmg;
	if(this.health <= 0) {
		this.onDeath(dealer);
	}
}
	@Override
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
	public int getAttackrange() {
		return attackrange;
	}
	public void setAttackrange(int attackrange) {
		this.attackrange = attackrange;
	}
	public int getAttackdamage() {
		return attackdamage;
	}
	public void setAttackdamage(int attackdamage) {
		this.attackdamage = attackdamage;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public void setXloc(int xloc) {
		this.xloc = xloc;
	}
	private void setYloc(int yloc) {
		// TODO Auto-generated method stub
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
	@Override
	public boolean canTarget(Unit dealer) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String getTeam() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public int getAttackspeed() {
		return attackspeed;
	}

	public void setAttackspeed(int attackspeed) {
		this.attackspeed = attackspeed;
	}
	public boolean isDestroyed() {
		return destroyed;
	}
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}
}
