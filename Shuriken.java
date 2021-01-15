package League;

import java.util.concurrent.TimeUnit;

class Shuriken implements Unit{
	public int xloc;
	public int yloc;
	public int damage;
	public Rift rift;
	public int travelspeed;
	public String team;

	public Shuriken(Rift rift2, String team2, int yloc2, int xloc2, int damage2) {
		// TODO Auto-generated constructor stub
	}
	public void travel(Unit target) {
		int traveldistance = 0;
		int max = 925;
		int xdiff =  target.getXloc() - this.xloc;
		int ydiff = target.getYloc() - this.yloc;
		int magnitude = (int) this.distanceTo(target);
		int yunit = ydiff/magnitude;
		int xunit = xdiff/magnitude;	
		int yvector = (int) (yunit * this.travelspeed/60);
		int xvector = (int) (xunit  * this.travelspeed/60);
		while(traveldistance < max) {

				if(rift.board[this.yloc][this.xloc] instanceof Space) {
				rift.board[this.yloc][this.xloc] = new Space(this.yloc, this.xloc);
				traveldistance += this.distanceTo(rift.board[this.yloc+yvector][this.xloc+xvector]);
				rift.board[this.yloc+yvector][this.xloc+xvector] = this;
			

				try {
					TimeUnit.MILLISECONDS.sleep((int)(1000* this.travelspeed /60 ));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				else {
					if(!rift.board[this.yloc][this.xloc].getTeam().equals(this.getTeam())) {
						
					}
				}
		}
	}
	public int damage(Unit target) {
		
		//TODO 
		return 0;
	}
	public int distanceTo(Unit target) {
		int xdiff = target.getXloc() - this.xloc;
		int ydiff = target.getYloc() - this.yloc;
		return (int) (Math.pow(xdiff*xdiff+ydiff*ydiff, 0.5));
	}
	@Override
	public int getXloc() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getYloc() {
		// TODO Auto-generated method stub
		return 0;
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
		return null;
	}
	public int takeDamage(Unit dealer, int damage, String type) {
		// TODO Auto-generated method stub
		return -1;
	}
	public int receiveGold(int gold) {
		return -1;
	}

}