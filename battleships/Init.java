
public class Init {
	BattleShipGame g;
	BattleShipBoard b;
	
	public Init(BattleShipGame game) {
	this.g = game;
	this.b = g.getOpponent().getBoard();
	
	}
	
	public BattleShipGame getGame() {
		return this.g;
	}
	
	public void setShips(BattleShipBoard p2board) {

		int [] rNum = randomize();
		boolean test = false;
		
		
		
		while (!test) {
		test = p2board.addShipBeta(new Destroyer(rNum[0],rNum[1],rNum[2], "Destroyer"));
		
			rNum = randomize();
		} test=false;
		while(!test) {
		test = 	p2board.addShipBeta(new Cruiser(rNum[0],rNum[1],rNum[2], "Cruiser"));
		
		rNum = randomize();
		}test=false;
		while(!test) {
		test = 	p2board.addShipBeta(new Submarine(rNum[0],rNum[1],rNum[2], "Submarine")); 
		
		rNum = randomize();
		}test=false;
		while(!test) {
			test = 	p2board.addShipBeta(new Battleship(rNum[0],rNum[1],rNum[2], "Battleship")); 
			
			rNum = randomize();
			}test=false;
		while(!test) {
				test = 	p2board.addShipBeta(new Carrier(rNum[0],rNum[1],rNum[2], "Carrier")); 
				
				rNum = randomize();
				}
		test=false;		while(!test) {			test = 	p2board.addShipBeta(new Cruiser(rNum[0],rNum[1],rNum[2], "Cruiserx"));rNum = randomize();	}
		test=false;		while(!test) {			test = 	p2board.addShipBeta(new Cruiser(rNum[0],rNum[1],rNum[2], "Cruiserx"));rNum = randomize();	}
		test=false;		while(!test) {			test = 	p2board.addShipBeta(new Cruiser(rNum[0],rNum[1],rNum[2], "Cruiserx"));rNum = randomize();	}
		test=false;		while(!test) {			test = 	p2board.addShipBeta(new Cruiser(rNum[0],rNum[1],rNum[2], "Cruiserx"));rNum = randomize();	}
//		test=false;		while(!test) {			test = 	p2board.addShipBeta(new Cruiser(rNum[0],rNum[1],rNum[2], "Cruiserx"));rNum = randomize();	}
//		test=false;		while(!test) {			test = 	p2board.addShipBeta(new Cruiser(rNum[0],rNum[1],rNum[2], "Cruiserx"));rNum = randomize();	}

		
		

		
			}
	public static int[] randomize() {
		int [] nums = new int[3];
		
		 nums[0] = ((int)(Math.random() * 10) + 1);
		 nums[1] = ((int)(Math.random() * 10) + 1);
		 nums[2] = ((int)(Math.random()*2));
		 
		 //System.out.println("x: " + nums[0] + " y: " + nums[1] +  " orient. " + nums[2]  );
		
		return nums;
	}

}
