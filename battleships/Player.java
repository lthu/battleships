import java.util.ArrayList;

class Player {
    private String name;
    private BattleShipBoard board;
    private CPUFireAI cpufireai;

    public Player(String n, BattleShipBoard b){
        this.name = n;
        this.board = b;
    }

	public void fire(BattleShipBoard b, int x, int y){
		
		if(board.getGameState()) {
			b.guessedRight(x, y, this);
			
			}

    }
    public String toString(){
        return this.name;
    }
    public BattleShipBoard getBoard() {
    	return this.board;
    }
}

class CPUPlayer extends Player {
	private CPUFireAI ai;
	private int [][] store = new int [100][100];
	
	public CPUPlayer(String n, BattleShipBoard b) {
		super(n,b);
	
	}
	
	public void randomizeFire(BattleShipBoard b) {
	
		if(!b.getGame().getAiState()) {
			
		
//		int fire[] = random();
		int fire[] = checkOptions(b);
		int x = fire[0];
		int y = fire[1];
		
		//System.out.println("store: "+ store[0][0] + " " + store[0][1] );
		
	/*	while (b.isShot(x, y)) { 
			fire = random(); 
			x=fire[0]; 
			y=fire[1];   
		}*/
		
		b.getGame().setCPUFire(x, y);
		fire(b, x, y);
		
		
		if(b.getGame().HIT) {
				createAI(b, x, y);
			}
		
		} else { //if Ai true	
			
			System.out.println("[randomizeFire] AiState: " + b.getGame().getAiState());
			
			int [] aiShot = ai.getShot();
			
			int x = aiShot[0];
			int y = aiShot[1];
			
			//System.out.println("[randomizeFire] getShot() x " + x + " y: " + y);
			
			b.getGame().setCPUFire(x, y);
			
			fire(b, x, y);
			System.out.println("fire x: " + x + " y: " + y);
			
			if(b.getGame().HIT) {
				ai.addHit(x, y);
				
			}
			
		}
		b.getGame().getPlayer().getBoard().printBoard();
	}
	
	private void createAI(BattleShipBoard b, int x, int y) {
		b.getGame().setAiState(true);
		ai = new CPUFireAI(b);
		ai.newHit(x, y);
		
	}
	public int [] random() {
		
		int [] rand = new int [2];
		rand[0] = ((int)(Math.random() * 10) + 1);
		rand[1] = ((int)(Math.random() * 10) + 1);
		return rand;
	}
	
	public int [] checkOptions(BattleShipBoard b) {
		
		int options = 0;
		
		int [] fourOptions = new int [2];
		int [] threeOptions = new int [2];
		int [] twoOptions = new int [2];
		int [] oneOption = new int [2];
		int [][] biggestStore = new int[100][100];
		int [][] bigStore = new int[100][100];
		int [][] smallStore = new int[100][100];
		int [][] smallestStore = new int[100][100];
		
		
		
		
		
		for(int x = 1; x < 11; x++) {
			for(int y = 1; y < 11; y++) {
			
			if(!b.isShot(x+1, y) && x+1 < 11 && !b.nextNextIsShip(x+1, y) ) {options++;}
			if(!b.isShot(x, y+1) && y+1 < 11 && !b.nextNextIsShip(x, y+1) ) {options++;}

			if(!b.isShot(x-1, y) && x-1 > 0 && !b.nextNextIsShip(x-1, y)) {options++;}
			if(!b.isShot(x, y-1) && y-1 > 0 && !b.nextNextIsShip(x, y-1)) {options++;}
			

			if(options == 4 && !b.isShot(x,y) && !b.nextIsShip(x, y)) { 
				//System.out.println("options > 3 " + x + " " + y);
				fourOptions[0] = x; 
				fourOptions[1] = y;
				biggestStore[0] = fourOptions;
			}
			
			if(options == 3 && !b.isShot(x,y) && !b.nextIsShip(x, y)) { 
				//System.out.println("options > 3 " + x + " " + y);
				threeOptions[0] = x; 
				threeOptions[1] = y;
				bigStore[0] = threeOptions;
			}
			if( ( options == 2 ) && !b.isShot(x, y) && !b.nextIsShip(x, y) ) {
				
				twoOptions[0] = x;
				twoOptions[1] = y;
				smallStore[0] = twoOptions;
				
				
			}
			if(options == 1 && !b.isShot(x, y) && !b.nextIsShip(x, y) ) {
				oneOption[0] = x;
				oneOption[1] = y;
				smallestStore[0] = oneOption;
				
				
			}
						
			//System.out.println("options > 2 " + x + " " + y);}
			//else if(options > 1 && !b.isShot(x, y) && !b.nextIsShip(x, y) ) {threeOptions[0] = x; threeOptions[1] = y;}
			
			
			options = 0;
			}

		}
		
		System.out.println("smallestStore x: "+ smallestStore[0][0] + " y: " + smallestStore[0][1]);
		System.out.println("smallStore x: "+ smallStore[0][0] + " y: " + smallStore[0][1]);
		System.out.println("bigStore: x: "+ bigStore[0][0] +" y: " + bigStore[0][1]);
		System.out.println("biggestStore: x: "+ biggestStore[0][0] +" y: " + biggestStore[0][1]);
		
		if(biggestStore[0][0] > 0) return biggestStore[0];
		if(bigStore[0][0] > 0) return bigStore[0];
		if(smallStore[0][0] > 0) return smallStore[0];
		
		return smallestStore[0];
		
	
	}
	
}
