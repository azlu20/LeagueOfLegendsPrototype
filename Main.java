package League;

public class Main {
	public static void main(String args[]) {
		
		Rift rift = new Rift();
		MeleeMinion test = new MeleeMinion(rift, "blue", 0, 0);
		MeleeMinion test2 = new MeleeMinion(rift, "red", 0, 200);
/*		for(int i =0; i < rift.board.length; i++) {
			for(int j=0; j < rift.board[i].length; j++) {
				if(j==rift.board[i].length-1) {
					System.out.print(rift.board[i][j].toString() + "\n");
				}
				else {
				System.out.print(rift.board[i][j].toString());
				}
			}
		}  */
		test.testpathing(test2);
		System.out.println(test.health);
		
	}
	
	}
