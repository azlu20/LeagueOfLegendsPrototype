package League;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Zed implements Unit {
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
	
	Zed(Rift rift, String team, int yloc, int xloc){
		goldvalues = new HashMap<Integer, Integer>(){{
			put(300, 274);
			put(274, 220);
			put(220, 176);
			put(176, 140);
			put(140, 112);
			put(112, 100);
			put(100, 100);
			
		}};
		this.inventory = new Items[6];
		for(int i =0; i < this.inventory.length; i++) {
			OpenSpace open = new OpenSpace();
			this.inventory[i] = open;
			
		}
		this.rift = rift;
		this.setCurrentgoldvalue(300);
	}
	public void qAbility(int yloc, int xloc) {
		if(this.getShadowout()) {
			Shuriken shadowshuriken = new Shuriken(this.rift, this.wShadow.getTeam(), this.wShadow.getYloc(), this.getXloc(), this.qDamage.get(this.qmax));
			shadowshuriken.travel(rift.board[yloc][xloc]);
		}
		Shuriken shuriken = new Shuriken(this.rift, this.getTeam(), this.getYloc(), this.getXloc(), this.qDamage.get(this.qmax));
		shuriken.travel(rift.board[yloc][xloc]);
	}
	public void wAbility(int yloc, int xloc) {

		this.wShadow = new Shadow(this.rift, this.getTeam(), this.getYloc(), this.getXloc(), 500);
		this.wShadow.travel(rift.board[yloc][xloc]);
		this.setShadowout(true);
		try { //cooldown of shadow
			TimeUnit.MILLISECONDS.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setShadowout(false);
		
	}
	public void qLevel() {
		if(this.getQmax() <5) {
			this.setQmax(this.getQmax()+1);
		}
		
	}

	public int distanceTo(Unit target) {
		int xdiff = target.getXloc() - this.getXloc();
		int ydiff = target.getYloc() - this.getYloc();
		return (int) (Math.pow(xdiff*xdiff+ydiff*ydiff, 0.5));
	}
	
	public int calculatePhysicalDamage(Unit dealer, int flatdmg) {
		if(this.getArmor() >= 0) {
			int newdmg = flatdmg * 100/(100+this.getArmor());
			return newdmg;
		}
		else {
		int newdmg = flatdmg * (2- (100/100-this.getArmor()));
		return newdmg;
		}

	}
	public int calculateMagicDamage(Unit dealer, int flatdmg) {
		if(this.getMr() >= 0) {
			int newdmg = flatdmg * 100/(100+this.getMr());
			return newdmg;
		}
		else {
		int newdmg = flatdmg * (2- (100/100-this.getMr()));
		return newdmg;
		}

	}
	public void onDeath(Unit dealer) {
		if(dealer.canCS()) {
			dealer.receiveGold(this.getCurrentgoldvalue());
			this.setCurrentgoldvalue(goldvalues.get(this.getCurrentgoldvalue()));
		}
		try {
			TimeUnit.MILLISECONDS.sleep(this.getDeathTimer()*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.respawn();
	}
	public int getDeathTimer(){
		int ans = 0;
		if(this.getCurlevel() <= 6) {
			ans = this.getCurlevel()*2+4;
		}
		if(this.getCurlevel() == 7) {
			ans = 21;
		}
		if(this.getCurlevel() >7) {
			ans = (int) (this.getCurlevel() * 2.5 + 7.5);
		}
		return ans;
	}
	public void respawn() {


			int i =0;
			while(!(this.rift.board[i][0] instanceof Space)) {
				i++;
			}
			if(this.rift.board[0][0] instanceof Space) {
				this.rift.board[0][0] = this;
			}
		
			
	}
	public int takeDamage(Unit dealer, int dmg, String type) {
		int total =0;
		switch(type) {
		case "physical":
			total = calculatePhysicalDamage(dealer, dmg);
			break;
		case "magic":
			total = calculateMagicDamage(dealer, dmg);
			break;
		case "true":
			total = dmg;
			break;
		}
		this.health -= total;
		if(this.health <= 0) {
			this.onDeath(dealer);
		}
		return total;
	}
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
	
}
