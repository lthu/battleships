
class CPUFireAI {

	private int alignment; // 0= horiz, 1=vert	
	private int numberOfHits;
	private int numberOfTries;
	private int [] hitsX;
	private int [] hitsY;
	private BattleShipBoard b;
	
	
	public CPUFireAI(BattleShipBoard b){
		this.b = b;
		this.alignment = 0;
	
		this.numberOfHits = 0;
		this.numberOfTries = 0;
		this.hitsX = new int[5];
		this.hitsY = new int[5];
	}
	
	public void newHit(int x, int y) {
		
		hitsX[numberOfHits] = x;
		hitsY[numberOfHits] = y;
		
		numberOfHits++;
		
		System.out.println("[New hit] x: " + x + " y: " + y + " hits: " + numberOfHits);
	}
	
	public int[] guessTwoFirst(int x, int y) {
		
		System.out.println("guessTwoFirst x: " +x);
		//numberOfHits == 1 && 
		//!b.nextNextIsShip(x-1,y)
		     if(!b.isShot(x+1,y) && (x+1 < 11) && !b.nextNextIsShip(x+1,y)    ) { x++; } 
		else if(!b.isShot(x,y+1) && (y+1 < 11) && !b.nextNextIsShip(x,y+1)  ) { y++; }
		else if(!b.isShot(x-1,y) && (x-1 > 0)  && !b.nextNextIsShip(x-1,y))  { x--; }
		else if(!b.isShot(x,y-1)) { y--; } 

		numberOfTries++;
		
		checkOptions(x,y);
		System.out.println("[guessTwoFirst] numberOfHits: " + numberOfHits + " tries: " + numberOfTries + " align: " + alignment);

		int [] result = new int [2];
		
		result [0] = x;
		result [1] = y;

		
		
	//	if(b.isShot(x,y)) {System.out.println("[guess] isShot x: " + x + " y: " + y);}
		
		return result;
		
		
	}
	public int [] getShot() {
		
		int [] shots = new int [2];
		
		if(numberOfHits < 2) {
			System.out.println("[CPUFireAI]: numberOfHits: < 2 " + numberOfHits);
		shots = guessTwoFirst(hitsX[0], hitsY[0]);
		}
		else if (numberOfHits >= 2) {
			System.out.println("[CPUFireAI]: numberOfHits >= 2: " + numberOfHits);
		shots = guessMore();
			
		}
		
		
			
		return shots;
	}
	public int [] guessMore() {
		int shots [] = new int [2];
		System.out.println("guessMore number: " + numberOfHits);
		int x = hitsX[numberOfHits-1];
		int y = hitsY[numberOfHits-1];

			if(alignment == 1) {


					System.out.println("Alignment 1");
					
//&&!b.isShip(x,y+1)
					
					if(!b.isShot(x, y+1) && y+1 < 11 && !b.nextNextIsShip(x,y+1) ) { 
						System.out.println("viimesin y+1 on OK"); 
						y++;
						}
					else if(!b.isShot(x, hitsY[0]-1)) {
						System.out.println("ensimmäinen y-1 on OK");
						y=hitsY[0]-1;
					}
					else if(!b.isShot(x,y-1 )) {
						System.out.println("viimesin y-1 on OK");
						y--;
					}
					
					
			}else if (alignment == 2) {
				

				if(!b.isShot(x+1, y) && !b.nextNextIsShip(x+1,y)  && x+1 < 11) { 
					System.out.println("viimesin x+1 on OK"); 
					x++;
					}
				else if(!b.isShot(hitsX[0]-1, y)) {
					System.out.println("ensimmäinen x-1 on OK");
					x=hitsX[0]-1; 
				}
				else if(!b.isShot(x-1,y )) {
					System.out.println("viimesin x-1 on OK");
					x--;
				}
			}
		//}
		shots[0] = x;
		shots[1] = y;
		if(b.isShot(x, y)) { System.out.println("Ei voida palauttaa oikeesti x: " + x + " y: " + y ) ;} 
		return shots;
		
	}
	public void calcAlign() {
		if(hitsX[0] == hitsX[1]) alignment = 1; 
		if(hitsY[0] == hitsY[1]) alignment = 2; 
	}

	
	
	public void addHit(int x, int y) {
		hitsX[numberOfHits] = x;
		hitsY[numberOfHits] = y;
		
		numberOfHits++;
		System.out.println("[CPUFireAI] addHit: " + numberOfHits );
		calcAlign();
		
	}
	
	public void checkOptions(int x, int y) {
		
		int result = 0;
		//if(!b.isShot(x, y)) { result++;}
		if(!b.isShot(x+1, y) && x+1 < 11) {result++;}
		if(!b.isShot(x, y+1) && y+1 < 11) {result++;}
		//if(!b.isShot(x+1, y+1) && y+1 < 11 && x+1 < 11) {result++;}
		
		if(!b.isShot(x-1, y) && x-1 > 0) {result++;}
		if(!b.isShot(x, y-1) && y-1 > 0) {result++;}
		//if(!b.isShot(x-1, y-1) && y-1 > 0 && x-1 > 0) {result++;}
		
		System.out.println("x: " + x + " y: " + y + " options: " + result);
			
	}
	
	
	public String toString() {
		return "asd"; 
	}
	
	
}
