

import javax.swing.*;
import java.util.Scanner;

interface IAlignment {
    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;
}
interface IGameSettings {

    public static final int SHIPCOUNT = 10;    
    public static final int CARRIER_COUNT = 1;
    public static final int BATTLESHIP_COUNT = 2;
    public static final int CRUISER_COUNT = 2;
    public static final int SUBMARINE_COUNT = 1;
    public static final int DESTROYER_COUNT = 4;
    
}


public class Main implements IAlignment, IGameSettings  {

	public static void main(String[] args) {
		
		
        BattleShipBoard p1board = new BattleShipBoard("p1board");
        BattleShipBoard p2board = new BattleShipBoard("cpuboard");

        Player p1 = new Player("lauri", p1board);

        CPUPlayer cpu = new CPUPlayer("cpu", p2board);
        p1board.setPlayer(p1);
        p2board.setPlayer(cpu);
        BattleShipGame peli = new BattleShipGame(p1, cpu);
        
        
        p1board.setGame(peli);
        p2board.setGame(peli);
  
        Init init = new Init(peli);
        init.setShips(p2board);
       // p1board.addShipBeta(new Destroyer(5,1,1,"Submarine"));
       // init.setShips(p1board);
     

        

//p1board.addShipBeta(new Submarine(5,1,1,"Submarine"));
//p1board.addShipBeta(new Destroyer(9,1,1,"Destroyer"));
//p1board.addShipBeta(new Battleship(10,3,0,"Battleship"));
//p1board.addShipBeta(new Submarine(10,8,0,"Submarine"));
//p1board.addShipBeta(new Battleship(1,3,0,"Battleship"));
//p1board.addShipBeta(new Submarine(1,8,0,"Submarine"));
//p1board.addShipBeta(new Battleship(3,10,1,"Battleship"));

/*
p1board.addShipBeta(new Cruiser(1,1,1,"Cruiser"));
p1board.addShipBeta(new Cruiser(1,5,1,"Cruiser"));
p1board.addShipBeta(new Cruiser(1,9,1,"Cruiser"));
p1board.addShipBeta(new Cruiser(1,7,1,"Cruiser"));
p1board.addShipBeta(new Cruiser(1,10,1,"Cruiser"));

p1board.addShipBeta(new Cruiser(4,2,0,"Cruiser"));
p1board.addShipBeta(new Cruiser(6,1,1,"Cruiser"));
p1board.addShipBeta(new Cruiser(9,2,0,"Cruiser"));
p1board.addShipBeta(new Cruiser(6,7,1,"Cruiser"));
p1board.addShipBeta(new Cruiser(6,10,1,"Cruiser"));
*/
        

p1board.printBoard();
	



   peli.startGame();
   
    	
    	   SwingUtilities.invokeLater(new Runnable() {
 	          @Override
 	          public void run() {
 	        	new ShipInsertGUI(init); 
 	          }
 	       });
 		
   
   

    }
	
	
}