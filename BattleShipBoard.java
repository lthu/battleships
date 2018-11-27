import java.util.ArrayList;

class BattleShipBoard implements IGameSettings  {
    
    private ArrayList<Ship> shipList = new ArrayList<>();
    private Ship [][] ships = new Ship [12][12];
    private String [][] boardSymbols = new String [12][12];
    private BattleShipGame game;
    private int shipCount;
    private Player player;
    private String name;
    
    public BattleShipBoard(String n){
        this.name = n;
        fillBoards();
    }


    /**
    * Adds ships to board. Checks that ship fits the board.
    * @param s the ship that is checked
    * @return boolean true if ship fits
    */
    public boolean addShip(Ship s){
        
    	shipList.add(s);
    
        if(checkBoundaryfit()) {
        
            if(isTaken(s)) {
       			shipList.remove(s); 

       		} else {
       			this.shipCount++;
       			drawShips();
       			return true;
       		}
        }
            return false;
    }

    /**
    * Goes through the list of Ships and checks that they fit. 
    * If not, then removes them from the list.
    * 
    * @return boolean
    */
    private boolean checkBoundaryfit() {
    	
    	
    	
    	for(Ship s : shipList) {
    		
    		int x = s.getCoordinateX();
    		int y = s.getCoordinateY();
    		int length = s.getShiplength();
    		int alignment = s.getAlignment();
    		

    		if( (alignment > 0 && x + length > 11) || 
    		    (alignment < 1 && y + length > 11) ) {
    			shipList.remove(s);
    			return false;
    			
    		} 	
    	}		
    			return true;	
    }

    /**
    * Formats the arrays that contain Ships and specific characters. 
    */
    private void fillBoards(){
    	
        for(int x = 1;x < 12;x++){
        		
            for(int y = 1;y < 12;y++){
            	boardSymbols[x][0] = "z";
            	boardSymbols[0][y] = "z";
            	boardSymbols[x][y] = "~";
                ships[x][y] = null;
            }
        }
    }
    
    /**
    * Goes through the list of Ships and "fills" arrays.
    */
     private void drawShips() {   
        for(Ship s:shipList){
        	
        	int x = s.getCoordinateX();
        	int y = s.getCoordinateY();
        	int length = s.getShiplength();
        	int align = s.getAlignment();
        	

            for(int a = 0; a < length; a++){                
                if(align > 0) { 
                    boardSymbols[x+a][y] = "y";
                    ships[x+a][y] = s;
                }
                else { 
                    boardSymbols[x][y+a] = "y";
                    ships[x][y+a] = s;
                }
            }
        }
     }

    /**
     * Calculates number of valid shooting options at x,y coordinates on 
     * issued board. Used for cpu to choose best possible target.
     * 
     * @param b the board which to check.
     * @param x the coordinate x
     * @param y the coordinate y
     * @return number of possible options
     */ 
    public Integer getOptions(BattleShipBoard b, int x, int y) {
    	
		Integer options = 0;
			
			if(!b.isShot(x+1, y) && x+1 < 11 && !b.nextIsShip(x+1, y) ) {options++;}
			if(!b.isShot(x, y+1) && y+1 < 11 && !b.nextIsShip(x, y+1) ) {options++;}

			if(!b.isShot(x-1, y) && x-1 > 0 && !b.nextIsShip(x-1, y)) {options++;}
			if(!b.isShot(x, y-1) && y-1 > 0 && !b.nextIsShip(x, y-1)) {options++;}
		
			if(b.isShot(x, y) ) options = 0;
			
			return options;
    }
    
    /**
    * Checks wheter another Ship already on board is interfering with this Ship
    * 
    * @param s the Ship to be compared
    * @return true if Ship cannot be inserted to current location on board. 
    */
    public boolean isTaken(Ship s){
    	
    	int x = s.getCoordinateX();
    	int y = s.getCoordinateY();
    	int length = s.getShiplength();
    	
        for(int a = 0; a < length; a++){	
            
            if(s.getAlignment() > 0){ 
                if(boardSymbols[x+a+1][y].equals("y") ||
                   boardSymbols[x-1][y].equals("y")   ||
                   boardSymbols[x+a][y+1].equals("y") || 
                   boardSymbols[x+a][y-1].equals("y")){
                    
                    return true;
                	}
            }else { 
 
                if(boardSymbols[x][y+a+1].equals("y") ||
                   boardSymbols[x][y-1].equals("y")   ||
                   boardSymbols[x+1][y+a].equals("y") ||
                   boardSymbols[x-1][y+a].equals("y")){
                	
                    return true;
                	}
            }                
        }
            return false;
    }    

   
    /**
    * Fires a shot and checks wheter it hit or not.  
    * 
    * @param x the coordinate x
    * @param y the coordinate y
    * @param shooter the Player that fired the shot
    */
    public void fireShot(int x, int y, Player shooter){
    	
        if(boardSymbols[x][y].equals("y")){

            boardSymbols[x][y] = "#";
            ships[x][y].takeHit();
            
            game.setTargetHit(true);
            game.setText(shooter + " hit target. ");
                        
            if(ships[x][y].isBroken()) {
            	
                if(shooter.isCpu()) { 
                    game.setAiState(false);
                    
                }else {
                    game.setCPUShipIsBroken(true);
                }

                this.shipCount--;
                game.setText(shooter + " sank a ship! " + this.shipCount + " ships remaining.");
                                
            } if (this.shipCount == 0){ 
                game.setText("Game over!"); 
                game.endGame(shooter);
            }
        }else {
            
            game.setText(shooter + " missed target.");
            boardSymbols[x][y] = "z";
            game.setTargetHit(false);
            
        }
        
    }

    /**
    * Checks if current x,y location has been shot 
    * 
    * @param x the x coordinate
    * @param y the y coordinate
    * @return boolean true if the location has been shot. 
    */
    public boolean isShot(int x, int y) {
    	if(boardSymbols[x][y].equals("z") || boardSymbols[x][y].equals("#")) return true;
    	
    	return false;    	
    }

    /**
    * Checks if there is a Ship (that has been fired at) in speficied location 
    * If not, then removes them from the list.
    * 
    * @param x the x coordinate
    * @param y the y coordinate
    * @return boolean true if there is a shot Ship in x,y location.
    */
    public boolean isShip(int x, int y) {
    	if(boardSymbols[x][y].equals("#")) return true;
    	
    	return false;
    }

    /**
    * Checks if coordinate is between two different ships.
    * Used for cpu to decide if it's reasonable to shoot here.
    * 
    * @param x the x coordinate
    * @param y the y coordinate
    * @return boolean true if there is more than one ship next to 
    *         x,y coordinates
    */
    
    public boolean isNextToTwoShips(int x, int y) {
    	
    	int count = 0;
    	
    	if(x+1 < 11 && x >= 0 && y+1 < 11 && y >= 0) {
    	
	    	if(boardSymbols[x+1][y].equals("#")) count++;
	    	if(boardSymbols[x-1][y].equals("#")) count++;
	    	if(boardSymbols[x][y+1].equals("#")) count++;
	    	if(boardSymbols[x][y-1].equals("#")) count++;

    	}
    	
    	if(count > 1) {
    	    return true;
    	}
    	
    	return false;
    }

    /**
    * Checks if there is a ship next to coordinates x,y
    * Used for cpu to decide if it's reasonable to shoot here.
    * 
    * @param x the x coordinate
    * @param y the y coordinate
    * @return boolean true if there is a ship next to these 
    *         x,y coordinates
    */    
    
    public boolean nextIsShip(int x, int y) {
    	if(boardSymbols[x+1][y].equals("#") ||
    	   boardSymbols[x][y+1].equals("#") ||
    	   boardSymbols[x-1][y+1].equals("#") ||
    	   boardSymbols[x][y-1].equals("#")) {
    		
    		return true;
    	}
    		
    		return false;
    }
    
    /**
    * Clears the arrays and Ship list for next game.
    */
    public void restartBoard() {
    	
    	fillBoards();
    	shipList.removeAll(shipList);
    	shipCount = shipList.size();
    	
    	
    	
    }

    public void setPlayer(Player p){
        this.player = p;
    }
    public void setGame(BattleShipGame g) {
        this.game = g;
    }
    public BattleShipGame getGame() {
        return this.game;
    }
    
    public String toString() {
    	return this.name;
    }
    
    public Ship getShip(int x, int y) {
        return ships[x][y];
                
    } 
    
    public BattleShipBoard getBoard(){
        return this;
    }
    
    public int getShipcount(){
        return shipCount;
    }
    
    /**
    * Old function before GUI times. Not used other than in debugging.
    */
    public void printBoard(){
    	
    	System.out.println("");
    	System.out.println("               Player: " + this.player + "     " );
    	System.out.println("");
        System.out.println("   +-A----B----C----D----E----F----G----H----I----J--+");
        
        for(int y = 1;y < 11;y++){
            System.out.printf("%-3d",y);
        
            for(int x = 1;x < 11;x++){
                System.out.print("| "+ boardSymbols[x][y] +"  ");
            }

                System.out.println("|");
                System.out.println("   +-------------------------------------------------+");

        }
    } 
}