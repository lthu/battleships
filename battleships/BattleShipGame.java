class BattleShipGame {
	
	
    
	Player player;
    CPUPlayer cpu;
    public boolean GAME_IS_ON = false;
    public boolean GAMERESTART = false;
    public boolean GAME_ENDED = false;
    private boolean AI_STATE = false;
    public boolean HIT = false;
    public int cpuFireX;
    public int cpuFireY;
    public String Text;
    
    public static final int SHIPCOUNT = 10;    
    public static final int CARRIER_COUNT = 1;
    public static final int BATTLESHIP_COUNT = 2;
    public static final int CRUISER_COUNT = 2;
    public static final int SUBMARINE_COUNT = 1;
    public static final int DESTROYER_COUNT = 4;
    
	BattleShipGame(Player player, CPUPlayer cpu) { 
        this.player = player;
        this.cpu = cpu;
	
    }
	public void startGame() {
		GAME_IS_ON = true;
//		GAMERESTART = false;
	}
	public void restartGame() {
		player.getBoard().restartBoard();
		cpu.getBoard().restartBoard();
		
		setAiState(false);
//		GAMERESTART = true;
		GAME_ENDED = false;
		
		
		
		//
	}
	 public void endGame(Player x) {
	    	setText("Peli loppui. Voittaja on: "+ x + " \n Kiitos pelaamisesta!");
	    	GAME_ENDED = true;
	    	//GAME_IS_ON = false;
	    	
	    }
	 public Player getPlayer(){
		 return player;
	 }
	 
	 public CPUPlayer getOpponent() {
		 return cpu;
	 }
	 public void setCPUFire(int x, int y) {
		 this.cpuFireX = x;
		 this.cpuFireY = y;
	 }
	 public int[] getCPUFire() {
		 int [] cpuFire = {this.cpuFireX, this.cpuFireY};
		 return cpuFire;
	 }
	 public void setText(String n) {
		 this.Text = n;
	 }
	 public String getText() {
		 return this.Text;
	 }
	 
	 public void setAiState(boolean b) {
		 this.AI_STATE = b;
	 }
	 public boolean getAiState() {
		 return this.AI_STATE;
	 }
	 
	 

}