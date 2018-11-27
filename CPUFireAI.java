
class CPUFireAI {

	private int alignment;	
	private int numberOfHits;
	private int [] hitsX;
	private int [] hitsY;
	private BattleShipBoard b;
	
	
	public CPUFireAI(BattleShipBoard b){
		this.b = b;
		this.alignment = 0;
		this.numberOfHits = 0;
		
		this.hitsX = new int[5];
		this.hitsY = new int[5];
	}
	
	/**
	 * Called everytime a new hit is made to initiate "AI".
	 * Location of first hit is used for later shots.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * */
	public void newHit(int x, int y) {
		
		hitsX[0] = x;
		hitsY[0] = y;
		
		numberOfHits++;
		
	}
	
	/**
	 * Called when there is one new hit. 
	 * Method goes through all "neighbor" squares and checks wheter they are 
	 * a valid target.
	 * 
	 * @return array of x and y coordinates for next shot; 
	 */
	
	public int[] guessTwoFirst() {
		

		int x = hitsX[0];
		int y = hitsY[0];
		
		     if(!b.isShot(x+1,y) && (x+1 < 11) && !b.isNextToTwoShips(x+1,y)) { x++; }
		else if(!b.isShot(x,y+1) && (y+1 < 11) && !b.isNextToTwoShips(x,y+1)) { y++; }
		else if(!b.isShot(x-1,y) && (x-1 > 0)  && !b.isNextToTwoShips(x-1,y)) { x--; }
		else if(!b.isShot(x,y-1)) { y--; } 

		// checkOptions(x,y); #DEBUGGING

		int [] result = new int [2];
		
		result [0] = x;
		result [1] = y;
		
		return result;
		
	}
	/**
	 * Methods in Player class call this method for next shot. (x,y coordinates)
	 * Different method is used when there are more than one shot.  
	 * 
	 * @return array of x,y coordinates for next shot. 
	 */
	
	public int [] getShot() {
		
		int [] shots = new int [2];
		
		if(numberOfHits < 2) {
		    shots = guessTwoFirst();
		}
		else if (numberOfHits >= 2) {
		    shots = guessMore();			
		}

		return shots;
	}
	/**
	 * Method is called when there are more than one shot.
	 * With two shots the alignment of ship is known so CPU can continue
	 * shooting in the correct axis.
	 *
	 * @return array of x,y coordinates for next shot.
	 */
	public int [] guessMore() {
	    
		int shots [] = new int [2];
		
		//Last shot that hit
		int x = hitsX[numberOfHits-1];
		int y = hitsY[numberOfHits-1];

		/**
         * Used to decide which way to shoot. Includes a check 
         * for boundaries. 
         * 
         * x and y variables are always the last hit that was made.
         * 
         * Method checks wheter to shoot +1 to last hit or -1 to 
         * first hit.
         * 
         * *NOTE*: Not perfect in current state. CPU still fires 
         * some "useless" shots in certain positions.
         */	
		
		 if(alignment == 1) {

					if(!b.isShot(x, y+1) && y+1 < 11 && !b.isNextToTwoShips(x,y+1) ) { 
						y++;
					}
					else if(!b.isShot(x, hitsY[0]-1)) {
						y=hitsY[0]-1;
					}
					else if(!b.isShot(x,y-1)) {
						y--;
					}
					
					
			}else if (alignment == 2) {
				

				if(!b.isShot(x+1, y) && !b.isNextToTwoShips(x+1,y)  && x+1 < 11) { 
					x++;
					}
				else if(!b.isShot(hitsX[0]-1, y)) {
					x=hitsX[0]-1; 
				}
				else if(!b.isShot(x-1,y )) {
					x--;
				}
			}
	
		shots[0] = x;
		shots[1] = y;
		 
		return shots;
		
	}
	
	/**
	 * After two hits this method is used to determine 
	 * which way the ship is aligned.
	 * 
	 */
	public void calcAlign() {
		if(hitsX[0] == hitsX[1]) alignment = 1; 
		if(hitsY[0] == hitsY[1]) alignment = 2; 
	}

	
	/**
	 * Adds a new hit and calls for alignment check.
	 * This is always a second hit. (First hit was made with newHit())
	 * 
	 * @param x the x coordinate of hit
	 * @param y the y coordinate of hit 
	 */
	public void addHit(int x, int y) {
		hitsX[numberOfHits] = x;
		hitsY[numberOfHits] = y;
		
		numberOfHits++;
		
		calcAlign();
		
	}
	/**
	 * Checks each square around x and y coordinates that can be fired at.
	 * Used for debugging.
	 */
	public void checkOptions(int x, int y) {
		
		int result = 0;

		if(!b.isShot(x+1, y) && x+1 < 11) {result++;}
		if(!b.isShot(x, y+1) && y+1 < 11) {result++;}

		
		if(!b.isShot(x-1, y) && x-1 > 0) {result++;}
		if(!b.isShot(x, y-1) && y-1 > 0) {result++;}


		
			
	}	
}
