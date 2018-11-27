public class Init {
    private BattleShipGame g;
    private int battleshipCount;
    private int cruiserCount;
    private int submarineCount;
    private int destroyerCount;
    private int carrierCount;
    
    public Init(BattleShipGame game) {
        this.g = game;
        formatShipCount();


    }
    
    public BattleShipGame getGame() {
        return this.g;
    }
    
    /**
     * Used to randomly put ships on (cpu) board. 
     * Can be used with player's board also (See Main for more)
     * 
     * @param b the board where ships are randomized.
     * */
    public void setShips(BattleShipBoard b) {
        
        boolean isValidLocation = false;
        int [] rNum = randomize();        

        
        while (!(carrierCount == BattleShipGame.CARRIER_COUNT))  {
            isValidLocation = b.addShip(new Carrier(rNum[0],rNum[1],rNum[2], "Carrier"));
            rNum = randomize();
            
            if(isValidLocation) carrierCount++;
        }
        isValidLocation = false;
        
        while (!(battleshipCount == BattleShipGame.BATTLESHIP_COUNT)) {
            isValidLocation = b.addShip(new Battleship(rNum[0],rNum[1],rNum[2], "Battleship"));
            rNum = randomize();
            
            if(isValidLocation) battleshipCount++;
        } 
        isValidLocation = false;
        
  

        while (!(cruiserCount == BattleShipGame.CRUISER_COUNT)) {
            isValidLocation = b.addShip(new Cruiser(rNum[0],rNum[1],rNum[2], "Cruiser"));
            rNum = randomize();
            
            if(isValidLocation) cruiserCount++;
        } 
        isValidLocation = false;
        
        while (!(submarineCount == BattleShipGame.SUBMARINE_COUNT)) {
            isValidLocation = b.addShip(new Submarine(rNum[0],rNum[1],rNum[2], "Submarine"));
            rNum = randomize();
            
            if(isValidLocation) submarineCount++;
        } 
        isValidLocation = false;
        
        while (!(destroyerCount == BattleShipGame.DESTROYER_COUNT)) {
            
            isValidLocation = b.addShip(new Destroyer(rNum[0],rNum[1],rNum[2], "Destroyer"));
            rNum = randomize();
            
            if(isValidLocation) destroyerCount++;
        } 
        

    }
    /**
     * Randomly selects coordinates and alignment for ship. 
     * 
     * @return array of x,y coordinates with alignment (0 or 1);
     * */
    public int[] randomize() {
        
        int [] nums = new int[3];
        
        nums[0] = ((int)(Math.random() * 10) + 1);
        nums[1] = ((int)(Math.random() * 10) + 1);
        nums[2] = ((int)(Math.random()*2));
        
        return nums;
    }
    public void formatShipCount() {
        battleshipCount = 0;
        cruiserCount = 0;
        destroyerCount = 0;
        carrierCount = 0;
        submarineCount = 0;
    }
    
    public void resetShips(BattleShipBoard b) {
        formatShipCount();
        setShips(b);
    }

}
