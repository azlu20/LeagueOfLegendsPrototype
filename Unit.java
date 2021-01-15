package League;

public interface Unit {
	public int getXloc();
	public int getYloc();
	public String toString();
	public boolean canCS();
	public boolean canTarget(Unit dealer);
	//public int[][] isTraversable(); //returns 0,0 if it is not.
	public String getTeam();
	public int takeDamage(Unit dealer, int damage, String type); //If it can take damage, calculate the damage. Otherwise it returns a -1.
	public int receiveGold(int gold);
}
