package League;

import java.util.concurrent.TimeUnit;

class Shadow implements Unit{
	public int xloc;
	public int yloc;
	public int damage;
	public Rift rift;
	public int travelspeed;
	public String team;
	Shadow(Rift rift, String team, int yloc, int xloc, int damage){
		this.xloc = xloc;
		this.yloc = yloc;
		this.rift = rift;
		this.damage = damage;
		this.travelspeed = 1700;
		this.team = team;
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
	}

	public int distanceTo(Unit target) {
		int xdiff = target.getXloc() - this.xloc;
		int ydiff = target.getYloc() - this.yloc;
		return (int) (Math.pow(xdiff*xdiff+ydiff*ydiff, 0.5));
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

}
