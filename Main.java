import javax.swing.*;


public class Main {

    public static void main(String[] args) {

        BattleShipBoard p1board = new BattleShipBoard("p1board");
        BattleShipBoard p2board = new BattleShipBoard("cpuboard");

        Player p1 = new Player("Player", p1board);
        CPUPlayer cpu = new CPUPlayer("Computer", p2board);
           
        p1board.setPlayer(p1);
        p2board.setPlayer(cpu);
        
        BattleShipGame game = new BattleShipGame(p1, cpu);
        
        p1board.setGame(game);
        p2board.setGame(game);
  
        Init init = new Init(game);
        init.setShips(p2board);

        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShipInsertGUI(init); 
            }
        });
 		
    }
}