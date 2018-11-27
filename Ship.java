import java.awt.Color;

class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getCoordinateX(){    
        return this.x;
    }

    public int getCoordinateY(){    
        return this.y;
    }
}

 class Ship extends Coordinate {
    
    private String shipname;
    private int shields;
    private String type;
    private int orientation;
    private int length;
    private String symbol;
    private Color c;

    public Ship(int x, int y, int or, String shipname, int shields){
       super(x,y);
       this.shipname = shipname;
       this.shields = shields;
       this.orientation = or;
       this.length = 2;
       this.type = "";
       this.symbol = "y";


    } 
    public Ship(int x, int y, int or, int length, String shipname, int shields, String type, String symbol, Color c){
        super(x,y);
        this.shipname = shipname;
        this.shields = shields;
        this.orientation = or;
        this.length = length;
        this.type = type;
        this.symbol = symbol;
        this.c = c;
 
     }

    public Color getColor() {
    	return this.c;
    }
    
    
    public void takeHit(){
        this.shields--;    
    }
    
    public boolean isBroken(){
        if(this.shields == 0){
            return true;
        } 
        return false;
    }

    public void setShipname(String n){
        this.shipname = n;
    }

    public int getShiplength(){
        return this.length;
    }

    public String getSymbol(){
        return this.symbol;
    }
    
    public String getShipname(){
        return this.shipname;
    }
    public String getType(){
        return this.type;
    }
    public int getAlignment(){
        return this.orientation;
    }

    public int getShields() {
    	return this.shields;
    }
    public void setShields() {
    	this.shields = this.length;
    }
    public String toString(){
        return "Name: " + this.shipname + "\nType: " + this.type;
    }
}
 
class Destroyer extends Ship {
    public Destroyer(int x, int y, int or, String shipname){
        super(x, y, or, 2, shipname, 2, "destroyer", "D", new Color(255,0,0));
    }
}

class Cruiser extends Ship {
    public Cruiser(int x, int y, int or, String shipname){
        super(x, y, or, 3, shipname, 3, "destroyer", "C", new Color(255,0,0));
    }
}

class Submarine extends Ship {
    public Submarine(int x, int y, int or, String shipname){
        super(x, y, or, 3, shipname, 3, "destroyer", "S", new Color(255,0,0));
    }
}

class Battleship extends Ship {
    public Battleship(int x, int y, int or, String shipname){
        super(x, y, or, 4, shipname, 4, "destroyer", "B", new Color(255,0,0));
    }
}
class Carrier extends Ship {
    
    public Carrier(int x, int y, int or, String shipname){
        super(x, y, or, 5, shipname, 5, "carrier", "A", new Color(255,0,0));

    }

}