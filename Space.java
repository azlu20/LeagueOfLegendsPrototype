package League;

public class Space implements Unit {
	boolean exist;
	private int xloc; 
	private int yloc;
	Unit board;
	private boolean canTarget;
	public Space(int y, int x){
		this.exist = true;
		this.setXloc(x);
		this.setYloc(y);
		this.canTarget = false;
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
public String toString() {
	return " | ";
}


@Override
public boolean canCS() {
	return false;
}


@Override
public boolean canTarget(Unit Dealer) {
	// TODO Auto-generated method stub
	
	return false;
}
public void changeCanTarget() {
	this.canTarget = !this.canTarget;
}


@Override
public String getTeam() {
	// TODO Auto-generated method stub
	return null;
}

public int takeDamage(Unit dealer, int damage, String type) {
	return -1;
}
public int receiveGold(int gold) {
	return -1;
}



}
