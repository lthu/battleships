
class Player {
    private String name;
    private BattleShipBoard board;
    private String type;
    
    
    public Player(String n, BattleShipBoard b){
        this.name = n;
        this.board = b;
        this.type = "player";
    }
    
    public Player(String n, BattleShipBoard b, String t){
        this.name = n;
        this.board = b;
        this.type = t;
    }

	public void fire(BattleShipBoard b, int x, int y){
		b.fireShot(x, y, this);

    }
    public String toString(){
        return this.name;
    }
    public BattleShipBoard getBoard() {
    	return this.board;
    }
    public boolean isCpu() {
        if(this.type.equals("cpu")) {
            return true;
        }
        return false;
    }
}

class CPUPlayer extends Player {
	private CPUFireAI ai;
	
	
	public CPUPlayer(String n, BattleShipBoard b) {
		super(n,b, "cpu");
	
	}
	
	
    /**
    * CPU's firing method. Starts by shooting at coordinates with most 
    * options (free squares). If a hit is made then proceeds with "AI"
    * 
    * @param b the players board to hit.
    */
	public void randomizeFire(BattleShipBoard b) {
	
	    if(!b.getGame().getAiState()) {

		int fire[] = checkOptions(b);
		int x = fire[0];
		int y = fire[1];
		
		//For GUI to know where cpu fired.
		b.getGame().setCPUFire(x, y);
		fire(b, x, y);
		
		
		if(b.getGame().isTargetHit()) {
				createAI(b, x, y);
			}
		
		} else {	
			
		    //Ask coordinates from "AI".
			int [] aiShot = ai.getShot();
			int x = aiShot[0];
			int y = aiShot[1];
			
	
			b.getGame().setCPUFire(x, y);
	
			fire(b, x, y);

			//Tells "AI" that a hit was made.
			if(b.getGame().isTargetHit()) {
				ai.addHit(x, y);
				
			}
			
		}

	}
    /**
    * Method to create firing "AI" that is used to decide best possible
    * location to shoot next. 
    * 
    * New "AI" is always created when new hit is found and it is used
    * until the ship is destroyed. After that is back to 
    * "randomly" shooting the board.
    * 
    * 
    * @param b the board to shoot at
    * @param x the x coordinate of first hit.
    * @param y the y coordinate of first hit.
    * 
    */
	private void createAI(BattleShipBoard b, int x, int y) {
		b.getGame().setAiState(true);
		ai = new CPUFireAI(b);
		ai.newHit(x, y);
		
	}
	
    /**
    * Goes through every coordinate on board and calculates how many 
    * squares are free to shoot. 
    * Used for cpu to fire at best possible locations when "AI" is not used.
    * 
    * @param b the board to check
    * @return array of two coordinates (x,y) 
    *         
    */
	public int [] checkOptions(BattleShipBoard b) {
		
		int options = 0;
		
		int [] fourOptions = new int [2];
		int [] threeOptions = new int [2];
		int [] twoOptions = new int [2];
		int [] oneOption = new int [2];
		
		int [][] biggestStore = new int[2][2];
		int [][] bigStore = new int[2][2];
		int [][] smallStore = new int[2][2];
		int [][] smallestStore = new int[2][2];
		
		for(int x = 1; x < 11; x++) {
			for(int y = 1; y < 11; y++) {
			
			    if(!b.isShot(x+1, y) && x+1 < 11 && !b.isNextToTwoShips(x+1, y) ) {options++;}
			    if(!b.isShot(x, y+1) && y+1 < 11 && !b.isNextToTwoShips(x, y+1) ) {options++;}

			    if(!b.isShot(x-1, y) && x-1 > 0 && !b.isNextToTwoShips(x-1, y)) {options++;}
			    if(!b.isShot(x, y-1) && y-1 > 0 && !b.isNextToTwoShips(x, y-1)) {options++;}
			

			    if(options == 4 && !b.isShot(x,y) && !b.nextIsShip(x, y)) { 
			        fourOptions[0] = x; 
			        fourOptions[1] = y;
			        biggestStore[0] = fourOptions;
			    }
			
			    if(options == 3 && !b.isShot(x,y) && !b.nextIsShip(x, y)) { 
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
			        options = 0;
			}

		}
		

		if(biggestStore[0][0] > 0) return biggestStore[0];
		if(bigStore[0][0] > 0) return bigStore[0];
		if(smallStore[0][0] > 0) return smallStore[0];
		
		return smallestStore[0];
		
	}
	
}
