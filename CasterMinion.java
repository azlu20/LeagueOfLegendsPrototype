package League;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class CasterMinion implements Unit {
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
	private String team; //blue is true red is false
	private int detectionrange;
	CasterMinion(Rift rift, String team, int yloc, int xloc){
		this.rift = rift;
		rift.board[yloc][xloc] = this;
		this.setYloc(yloc);
		this.setXloc(xloc);
		this.canTarget = true;
	
		//League has a number that the increased health armor and attack increment by a certain amount. This number is the ingame time/90 seconds.
		int scaling = rift.getAgeInSeconds();
		int upgrade = scaling%90; 
		if(upgrade <= 4) {
		this.health = (int) (290 +6*upgrade);
		}
		if(upgrade > 4) {
		this.health = (int)(455+8.25*upgrade);
		if(this.health >= 485) {
			this.health = 485;
		}
		}
		

			
		
		if(upgrade > 4) {
			this.setAttackvalue((int) (22.5+4.5*upgrade));
			if(this.getAttackvalue() >= 120) {
				this.setAttackvalue(120);
			}
		}
		if(upgrade < 4) {
			this.setAttackvalue((int) (22.5+1.5*upgrade));
		}
		//Below are the constant values 
		this.setArmor(0);
		this.setTeam(team);
		this.setAttackspeed(0.667);
		this.setMr(0);
		this.setMs(325);
		this.setGoldvalue(14);
		this.setAttackrange(550);
	}
	public void onDeath(Unit killer) {
		if(killer.canCS()) {
			/* killer.incrementCS();
			   killer.gainGold(this.getGoldvalue());
			   killer.gainExp(this.getExp());
			 */
		}
		this.rift.board[yloc][xloc] = new Space(yloc, xloc);
	}
	public void attack(MeleeMinion dealt) {
		//Checks if it can do damage to unit after move.
		dealt.calculatePhysicalDamage(this, this.getAttackvalue());
	}
/*	public void pathing(Unit target) {
		int count = 0;
		int xdiff =  target.getXloc() - this.getXloc();
		int ydiff = target.getYloc() - this.getYloc();
		int magnitude = (int) this.distanceTo(target);
		int yunit = ydiff/magnitude;
		int xunit = xdiff/magnitude;
		Unit temp = rift.board[this.getYloc()+yunit][this.getXloc()+xunit];
		Unit traversal = rift.board[this.getYloc()][this.getXloc()];
		
		while(this.distanceTo(target) > this.getAttackrange()) {
			temp = rift.board[this.getYloc()+yunit][this.getXloc()+xunit];
			this.setXloc(this.getXloc()+xunit);
			this.setYloc(this.getYloc()+yunit);
			if(temp.isTraversable()[0][0]!=0 && temp.isTraversable()[0][1]!=0) { //if the first two elements of isTraversable is 0,0, we know that it is not traversable
				break;
			}
		}
		if(temp.isTraversable()[0][0]!=0 && temp.isTraversable()[0][1]!=0) {
			LinkedList <Unit> pathqueue = new LinkedList<Unit>();
			HashMap <Unit, Integer> traveldistance = new HashMap<Unit, Integer>();
			int cur[] = temp.isTraversable()[0];
			Unit mins = rift.board[cur[0]][cur[1]];
			int shortest = this.distanceTo(mins);
			//implement Dijstraks 
			while(pathqueue.isEmpty()!=true) {
				Unit current = pathqueue.pop();
				for(int i =0; i < temp.isTraversable().length; i++) {
					int tempy = temp.isTraversable()[i][0];
					int tempx = temp.isTraversable()[i][1];
					if(current.distanceTo(rift.board[tempy][tempx]) + traveldistance.get(current)< traveldistance.get(rift.board[tempy][tempx])){
						traveldistance.put(rift.board[tempy][tempx], current.distanceTo(rift.board[tempy][tempx]) + traveldistance.get(current));
					}
				}
			}
		}
		else {
		int yvector = (int) (yunit * this.getMs()/60);
		int xvector = (int) (xunit  * this.getMs()/60);
			while(this.distanceTo(target) > this.getAttackrange()) {
				rift.board[this.getYloc()+yvector][this.getXloc()+xvector] = this;
				rift.board[this.getYloc()][this.getXloc()] = new Space(this.getYloc(), this.getXloc());
				this.setYloc(this.getYloc()+yvector);
				this.setXloc(getXloc()+xvector);
				try {
					TimeUnit.MILLISECONDS.sleep((int)(1000* this.getMs() /60 ));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.attack(target);
		}
	}
	*/
	public void testpathing(MeleeMinion target) {
		int xdiff =  target.getXloc() - this.getXloc();
		int ydiff = target.getYloc() - this.getYloc();
		int magnitude = (int) this.distanceTo(target);
		int yunit = ydiff/magnitude;
		int xunit = xdiff/magnitude;	
		int yvector = (int) (yunit * this.getMs()/60);
		int xvector = (int) (xunit  * this.getMs()/60);
		while(this.distanceTo(target) > this.getAttackrange()) {
			System.out.println(this.distanceTo(target));
			rift.board[this.getYloc()+yvector][this.getXloc()+xvector] = this;
			rift.board[this.getYloc()][this.getXloc()] = new Space(this.getYloc(), this.getXloc());
			this.setYloc(this.getYloc()+yvector);
			this.setXloc(getXloc()+xvector);
			try {
				TimeUnit.MILLISECONDS.sleep((int)(1000* this.getMs() /60 ));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.attack(target);
	
	}
	public int distanceTo(Unit target) {
		int xdiff = target.getXloc() - this.getXloc();
		int ydiff = target.getYloc() - this.getYloc();
		return (int) (Math.pow(xdiff*xdiff+ydiff*ydiff, 0.5));
	}
	public Unit detectionRange() {
		int range = this.getDetectionrange();
		for(int i = xloc-range; i <= xloc+range; i++) {
			for(int j = yloc-range; j <= yloc + range; j++) {
				if((i-xloc)*(i-xloc) + (j-yloc)*(j-yloc) <= range*range) {
					if(i >= 0 && j >=0 && i < this.rift.board.length && j < this.rift.board[0].length) {
					Unit searched = rift.board[j][i];
					if(searched.canTarget(this)) {
						return searched;
					}
					}
				}
			}
		}
		return null;
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
	public void changeCanTarget() {
		this.canTarget = !this.canTarget;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String toString() {
		return "" + this.health;
	}
	public int getDetectionrange() {
		return detectionrange;
	}
	public int receiveGold(int gold) {
		return -1;
	}

	public void setDetectionrange(int detectionrange) {
		this.detectionrange = detectionrange;
	} 
}
