class BattleShipGame {
	
	
    
    private Player player;
    private CPUPlayer cpu;
    private Player winner;
    private boolean GAMEOVER;
    private boolean AI_STATE;
    private boolean HIT;
    public int cpuFireX;
    public int cpuFireY;
    public String guiText;
    private boolean isCPUShipBroken;
    
    public static final int SHIPCOUNT = 10;    
    public static final int CARRIER_COUNT = 1;
    public static final int BATTLESHIP_COUNT = 2;
    public static final int CRUISER_COUNT = 2;
    public static final int SUBMARINE_COUNT = 1;
    public static final int DESTROYER_COUNT = 4;
    
	BattleShipGame(Player player, CPUPlayer cpu) { 
        this.player = player;
        this.cpu = cpu;
        this.HIT = false;
        setAiState(false);
        setGameOver(false);
        
	
    }
	
    /**
    * Calls boards to reset and set "AI" state to false
    */
	public void restartGame() {
		player.getBoard().restartBoard();
		cpu.getBoard().restartBoard();
		
		setAiState(false);
		setGameOver(false);
	}
	
	public boolean isCPUShipBroken() {
	    return this.isCPUShipBroken;
	}
	
	public void setCPUShipIsBroken(boolean b) {
	       this.isCPUShipBroken = b;
	   }
	
    /**
    * Used to end the game. Sets text for GUI.
    * 
    * @param p the player that fired the winning shot.
    */
	
	public void setWinner(Player p) {
	    this.winner = p;
	}
	
	public Player getWinner() {
	    return this.winner;
	}
	
	 public void endGame(Player x) {
	    	setText("Game over. The winner is : "+ x);
	    	setWinner(x);
	    	setGameOver(true); 	
	 }
	 
	 public Player getPlayer(){
		 return player;
	 }
	 
	 public CPUPlayer getCPU() {
		 return cpu;
	 }
	 public void setCPUFire(int x, int y) {
		 this.cpuFireX = x;
		 this.cpuFireY = y;
	 }
	 
	 /**
	  * For GUI to know where cpu fired at.
	  * 
	  * @return array containing x,y coordinates
	  */
	 public int[] getCPUFire() {
		 int [] cpuFire = {this.cpuFireX, this.cpuFireY};
		 return cpuFire;
	 }
	 public void setText(String n) {
		 this.guiText = n;
	 }
	 public String getText() {
		 return this.guiText;
	 }
	 
	 public void setAiState(boolean b) {
		 this.AI_STATE = b;
	 }
	 public boolean getAiState() {
		 return this.AI_STATE;
	 }
	 public void setTargetHit(boolean b) {
	     this.HIT = b;
	 }
	 public boolean isTargetHit() {
	     return this.HIT;
	 }
	 public boolean isGameOver() {
	     return this.GAMEOVER;
	 }
	 public void setGameOver(boolean b) {
	     	this.GAMEOVER = b;
	 }

}