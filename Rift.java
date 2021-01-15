package League;

import java.util.ArrayList;

public class Rift {
	Unit board [][];
	private boolean winner;
	private final long time = System.currentTimeMillis();
	private ArrayList<Unit> spawn1= new ArrayList<Unit>(); //minion waves without cannon
	private ArrayList<Unit> spawn2 = new ArrayList<Unit>(); //minion waves with cannon
	private ArrayList<Unit> spawn3 = new ArrayList<Unit>(); //Super Minion waves
	public ArrayList<Unit> blueteam = new ArrayList<Unit>();
	Rift(){


		this.setWinner(false);
		this.board = new Unit[1600][1600];
		for(int i =0; i < this.board.length; i++) {
			for(int j =0; j < this.board.length; j++) {
				this.board[i][j] = new Space(i, j);
			}
		}

			
		  spawn2.add(new MeleeMinion(this, "blue", 0,0));
		  spawn2.add(new MeleeMinion(this, "blue", 0,0));
		  spawn2.add(new MeleeMinion(this, "blue", 0,0));

		 // spawn2.add(CannonMinion);
		  spawn2.add(new CasterMinion(this, "blue", 0, 0));
		  spawn2.add(new CasterMinion(this, "blue", 0, 0));
		  spawn2.add(new CasterMinion(this, "blue", 0, 0));
	}
	public ArrayList<Unit> getBlueTeam() {
		return this.blueteam;
	}
	public void addChampBlue(Unit champ) {
		this.blueteam.add(champ);
	}
	public void spawnBlueTeam() {
		//TODO Travel down lanes
		//Riot Implementation had specific diretions they have to move
		  MeleeMinion m1 = new MeleeMinion(this, "blue", 0,0);
		  MeleeMinion m2 = new MeleeMinion(this, "blue", 0,0);
		  MeleeMinion m3 = (new MeleeMinion(this, "blue", 0,0));
		  CasterMinion m4 = (new CasterMinion(this, "blue", 0, 0));
		  CasterMinion m5 = (new CasterMinion(this, "blue", 0, 0));
		  CasterMinion m6 = (new CasterMinion(this, "blue", 0, 0));
	}
	public void endgame() {
		winner = true;
	}
	public int getAgeInSeconds() {
	    long nowMillis = System.currentTimeMillis();
	    return (int)((nowMillis - this.time) / 1000);
	}
	public boolean isWinner() {
		return winner;
	}
	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	}

