
import java.util.ArrayList;
class BattleShipBoard implements IGameSettings  {
    
    private ArrayList<Ship> shipList = new ArrayList<>();
    private Ship [][] testi = new Ship [12][12];
    private String [][] boardHidden = new String [12][12];
    
    
    private BattleShipGame game;
    private int shipCount;
    private Player player;

    
    private String name;
    
    public BattleShipBoard(String n){
        this.name =n;
     
        fillBoards();
      
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

   
    public boolean addShipBeta(Ship tmp){


    	shipList.add(tmp);
    
        
        if(checkBoundaryfit()) {
        
       		if(isTaken(tmp)) {
       			
       			//System.out.println("[beta] isTaken true: " + tmp.getShipname() + " x: " + tmp.getCoordinateX() + " y: " + tmp.getCoordinateY());
       			shipList.remove(tmp); 
       			//return false;
       			} else {
       			this.shipCount++;
       		//	System.out.println("[beta] add: " +tmp.getShipname()+" x: " + tmp.getCoordinateX() + " y: " + tmp.getCoordinateY());
       			drawShips();
       			return true;
       			
       		}
        }
        	
        	return false;
    }

    private boolean checkBoundaryfit() {
    	
    	int length;
    	
    	for(Ship laiva : shipList) {

    		//System.out.println(laiva.getShipname() + " b: " + this.x);
    		int x = laiva.getCoordinateX();
    		int y = laiva.getCoordinateY();
    		length = laiva.getShiplength();
    		
    		int alignment = laiva.getAlignment();
    		

    		//if(laiva.getCoordinateX() + length > 11 || laiva.getCoordinateY() + length > 11 ) {
    		
    		if( (alignment > 0 && x + length > 11) || 
    		    (alignment < 1 && y + length > 11) ) {
    			//System.out.println("OOB: " + laiva.getShipname() + " x: " +laiva.getCoordinateX() + " y: " + laiva.getCoordinateY() + " l: " + length);
    			shipList.remove(laiva);
    			return false;
    			
    		} 	
    	}		
    			return true;	
    }

    public void fillBoards(){
    	
        for(int x = 1;x < 12;x++){
        		
            for(int y = 1;y < 12;y++){
            	boardHidden[x][0] = "z";
            	boardHidden[0][y] = "z";
                boardHidden[x][y] = "~";
                testi[x][y] = null;
            }
        }
    }
     public void drawShips() {   
        for(Ship laiva:shipList){
        	
        	int x = laiva.getCoordinateX();
        	int y = laiva.getCoordinateY();
        
        	///
        	//DEBUG
        	///
        	laiva.setShields();
        	String s = laiva.getSymbol();
        	
        	//System.out.println("drawShips():shields " +laiva.getShields());
            for(int a = 0; a < laiva.getShiplength(); a++){                
                   if(laiva.getAlignment() > 0) { 
                        boardHidden[x+a][y] = "y";
                         
                        testi[x+a][y] = laiva;
                        //System.out.println("[beta]Align 0 x: " + laiva.getCoordinateX()+" "+a + " y: " + laiva.getCoordinateY()+" " + laiva.getShipname() );
                  }
                  else { 
                      	boardHidden[x][y+a] = "y";
                        testi[x][y+a] = laiva;
                       // System.out.println("[beta]Align 1 x: " + laiva.getCoordinateX() + " y: " + laiva.getCoordinateY()+" "+a + laiva.getShipname() );
                  }
              }
                  //this.shipCount++;  
        }
     }

     
    public Integer getOptions(BattleShipBoard b, int x, int y) {
    	
		Integer options = 0;
			
			if(!b.isShot(x+1, y) && x+1 < 11 && !b.nextIsShip(x+1, y) ) {options++;}
			if(!b.isShot(x, y+1) && y+1 < 11 && !b.nextIsShip(x, y+1) ) {options++;}

			if(!b.isShot(x-1, y) && x-1 > 0 && !b.nextIsShip(x-1, y)) {options++;}
			if(!b.isShot(x, y-1) && y-1 > 0 && !b.nextIsShip(x, y-1)) {options++;}
		
			if(b.isShot(x, y) ) options = 0;
			
			return options;
    }
     
    public boolean isTaken(Ship s){
    	
    	int x = s.getCoordinateX();
    	int y = s.getCoordinateY();
    	int length = s.getShiplength();
    	
    	//System.out.println(" " + s.getShipname() + " x: " + s.getCoordinateX() + " y: " + s.getCoordinateY());
    	
        for(int a = 0; a < length; a++){
        	
            if(s.getAlignment() > 0){ 
            	//System.out.println(s.getShipname() + "vaaka");
            	//System.out.println("a: " +  a + " x: " + x + " y: " + y); 
                if(
                		boardHidden[x+a+1][y].equals("y") ||
                		boardHidden[x-1][y].equals("y") ||

                		boardHidden[x+a][y+1].equals("y") || 
                		boardHidden[x+a][y-1].equals("y"))   {

                	return true;
                	}
            }else { 
//            	System.out.println(s.getShipname() + "pysty");
//            	System.out.println("a: " +  a + " x: " + x + " y: " + y); 
                if(
                		boardHidden[x][y+a+1].equals("y") ||
                		boardHidden[x][y-1].equals("y") ||
                		
                		boardHidden[x+1][y+a].equals("y") ||
                		boardHidden[x-1][y+a].equals("y") ) {
                	//System.out.println("isTaken: " + s.getShipname() + " x: " + s.getCoordinateX() + " y: " + s.getCoordinateY());
                	return true;
                	}
            }                
            
        }
            return false;
    }    

   
    
    public void guessedRight(int x, int y, Player shooter){
    	
        if(boardHidden[x][y].equals("y")){

            boardHidden[x][y] = "#";
            testi[x][y].takeHit();
            game.HIT = true;
            game.setText(shooter + ": osui kohteeseen. ");
            
//            System.out.println("guessedRight():shields " +testi[x][y].getShields());
//            System.out.println("guessedRight():shipCount " +this.shipCount);            
            if(testi[x][y].isBroken()) {
            	
            	if(this.player.toString().equals("lauri")) {System.out.println("AiState -> false"); game.setAiState(false);}
                this.shipCount--;
                game.setText(shooter + " upotti aluksen! (Jäljellä on " + this.shipCount + " laivaa)");
                System.out.println("Upposi!\n Jaljella on: " + this.shipCount + " laivaa");
            } if (this.shipCount == 0){ game.setText("Peli loppui!"); game.endGame(shooter);}
            
        }else {
            
        	game.setText(shooter + ": ohi meni.");
            //boardVisible[x][y] = " ";
            boardHidden[x][y] = "z";
            game.HIT = false;
            
        }
    
    
    }
    
    public boolean getGameState() {
    	return game.GAME_IS_ON;
    }
    
    public boolean isShot(int x, int y) {
    	if(boardHidden[x][y].equals("z") || boardHidden[x][y].equals("#")) {
    	//System.out.println("[isShot] true x: " + x + " y: " + y + " " + this.getBoard().toString());
    	return true;
    	} else {
    	//System.out.println("[isShot] false x: " + x + " y: " + y + " " + this.getBoard().toString());
    	return false;
    	}
    }
    
    public boolean isShip(int x, int y) {
    	if(boardHidden[x][y].equals("#")) return true;
    	
    	return false;
    	
    }
    
    public boolean nextNextIsShip(int x, int y) {
    	
    	int count = 0;
    	
    	if(x+1 < 11 && x >= 0 && y+1 < 11 && y >= 0) {
    	
	    	if(boardHidden[x+1][y].equals("#")) count++;
	    	if(boardHidden[x-1][y].equals("#")) count++;
	    	if(boardHidden[x][y+1].equals("#")) count++;
	    	if(boardHidden[x][y-1].equals("#")) count++;
	    	
	    		//System.out.println("count: " + count + "(x: " + x  + " y: " + y + ")");
    	}
    	
    	if(count > 1) {
    	return true;
    	}
    	
    	return false;
    }
    
    public boolean nextIsShip(int x, int y) {
    	if(boardHidden[x+1][y].equals("#") ||
    	   boardHidden[x][y+1].equals("#") ||
    	   boardHidden[x-1][y+1].equals("#") ||
    	   boardHidden[x][y-1].equals("#")) {
    		
    		return true;
    	}
    		
    		return false;
    }
    
    public void restartBoard() {
    	
    	fillBoards();
    	
    	System.out.println("ship count restartissa2: "+shipCount + this.player);
    	shipList.removeAll(shipList);
    	shipCount = shipList.size(); 
    }
    
    public Ship getShip(int x, int y){
        return testi[x][y];
    }
    
    public String toString() {
    	return this.name;
    }
    
    public BattleShipBoard getBoard(){
        return this;
    }
    public int getShipcount(){
        return shipCount;
    }
    
      
    public void printBoard(){
    	
    	System.out.println("");
    	System.out.println("               Player: " + this.player + "     " );
    	System.out.println("");
        System.out.println("   +-A----B----C----D----E----F----G----H----I----J--+");
        
        for(int y = 1;y < 11;y++){
        
            System.out.printf("%-3d",y);
        
            for(int x = 1;x < 11;x++){
        
                System.out.print("| "+ boardHidden[x][y] +"  ");
    }

        System.out.println("|");
        System.out.println("   +-------------------------------------------------+");

       }
    } 
}
