import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;  // Using AWT event classes and listener interfaces
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class ShipInsertGUI extends JFrame implements ActionListener {


    private static final long serialVersionUID = 1L;
    private BattleShipGame game;
    private JButton button;
    private JRadioButton btnRadioDestroyer;
    private JRadioButton btnRadioCruiser;
    private JRadioButton btnRadioSubmarine;
    private JRadioButton btnRadioBattleship;
    private JRadioButton btnRadioCarrier;
    private JToggleButton btnAlignment;
    private Init init;
    private JButton [][] jButtonList;  

   
    private int destroyerCount;
    private int cruiserCount;
    private int submarineCount; 
    private int battleshipCount;
    private int carrierCount;
    private Image img, img2;
   
/**
 * Used to set everything up
 * 
 * @param init the product of initialiser to let GUI know about the game.
 */
    public ShipInsertGUI (Init init) {
        this.init = init;
        this.game= init.getGame();

        destroyerCount = BattleShipGame.DESTROYER_COUNT;
        cruiserCount = BattleShipGame.CRUISER_COUNT;
        submarineCount = BattleShipGame.SUBMARINE_COUNT;
        battleshipCount = BattleShipGame.BATTLESHIP_COUNT;
        carrierCount = BattleShipGame.CARRIER_COUNT;
	  
        setLayout(new GridLayout(11,10,0,0));
      
        jButtonList = new JButton[11][11];
      
        btnRadioDestroyer = new JRadioButton("Destroyer (" + destroyerCount + ")");
        btnRadioCruiser = new JRadioButton("Cruiser (" + cruiserCount +")");
        btnRadioSubmarine = new JRadioButton("Submarine (" + submarineCount + ")");
        btnRadioBattleship = new JRadioButton("Battleship (" + battleshipCount + ")");
        btnRadioCarrier = new JRadioButton("Carrier (" + carrierCount + ")");
      
        btnAlignment = new JToggleButton();
        btnAlignment.setBorder(BorderFactory.createEmptyBorder());
        btnAlignment.setContentAreaFilled(false);
     
        ButtonGroup group = new ButtonGroup();
        
        group.add(btnRadioDestroyer);
        group.add(btnRadioCruiser);
        group.add(btnRadioSubmarine);
        group.add(btnRadioBattleship);
        group.add(btnRadioCarrier);
      
        btnRadioDestroyer.setSelected(true);
      
        try {
            img = ImageIO.read(getClass().getResource("resources/pysty.png"));
            img2 = ImageIO.read(getClass().getResource("resources/vaaka.png"));
		
            btnAlignment.setIcon(new ImageIcon(img));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	
        btnAlignment.addActionListener(new ActionListener() {
	
            public void actionPerformed(ActionEvent e) {
                if (btnAlignment.isSelected()) {
                    btnAlignment.setIcon(new ImageIcon(img2));
                } else {
                    btnAlignment.setIcon(new ImageIcon(img));
                }
	    }
	});
        
        String [] key  = {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", 
              "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10",
              "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10",
              "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10",
              "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "E10",
              "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10",
              "G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8", "G9", "G10",
              "H1", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10",
              "I1", "I2", "I3", "I4", "I5", "I6", "I7", "I8", "I9", "I10",
              "J1", "J2", "J3", "J4", "J5", "J6", "J7", "J8", "J9", "J10"};

        int x = 1;
	    int y = 1;
	    
	    
	    /*
	     * Buttons are "marked" with corresponding x and y coordinates.
	     */
	    for(int i = 0 ; i < 100;i++){
	        
	    	button = new JButton();
	    	button = new JButton(key[i]);
	        button.setName(key[i]);
	        button.setBackground(new Color(0, 105, 148));

	        button.putClientProperty( "x", x );
	        button.putClientProperty( "y", y );
	        add(button);
	        button.addActionListener(this);
	        jButtonList[x][y] = button;

	        if(x > 9) {
	            y++; 
	            x=0;
	        }
	        
	        x++;
	
	    }
	    
	    add(btnRadioDestroyer);
	    add(btnRadioCruiser);
	    add(btnRadioSubmarine);
	    add(btnRadioBattleship);
	    add(btnRadioCarrier);
	    add(btnAlignment);

	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setPreferredSize(new Dimension(1200, 900));
	    setVisible(true);        
	    pack();
   }
  
    public JRadioButton freeButton() {
        
        JRadioButton returnButton = new JRadioButton();
        
         
        if (btnRadioDestroyer.isEnabled()) returnButton = btnRadioDestroyer;
        else if (btnRadioCruiser.isEnabled()) returnButton = btnRadioCruiser;
        else if (btnRadioSubmarine.isEnabled()) returnButton = btnRadioSubmarine;
        else if (btnRadioBattleship.isEnabled()) returnButton = btnRadioBattleship;
        else if (btnRadioCarrier.isEnabled()) returnButton = btnRadioCarrier;
        
        
        return returnButton;
    }
 /**
  * Actionlistener for buttons.
  * Uses jButtons clientproperties for x and y coordinates.
  * 
  * Calls BattleShipBoards addShip method and acts accordingly 
  * disabling the buttons when right amount of ships are on the board. 
  * 
  * Fires up BattleShipGameGUI when SHIPCOUNT is reached.
  */
   @Override
   public void actionPerformed(ActionEvent evt) {

       int x = (int) ((JComponent) evt.getSource()).getClientProperty("x");
	   int y = (int) ((JComponent) evt.getSource()).getClientProperty("y");
	   
	   System.out.println(((JComponent) evt.getSource()).getClientProperty("x"));
	   
	   boolean isDestroyerSelected = btnRadioDestroyer.isSelected();
	   boolean isCarrierSelected = btnRadioCarrier.isSelected();
	   boolean isCruiserSelected = btnRadioCruiser.isSelected();
	   boolean isBattleshipSelected = btnRadioBattleship.isSelected();
	   boolean isSubmarineSelected = btnRadioSubmarine.isSelected();

	   int alignment = btnAlignment.isSelected() ? 1 : 0;
	   
	   Ship s;
	   
	   if(isDestroyerSelected && btnRadioDestroyer.isEnabled()) {		   
		   s = new Destroyer(x, y, alignment, "Destroyer");
		   
		   if(game.getPlayer().getBoard().addShip(s)) {
		       destroyerCount--;
		       colorizeButtons(s);
		       btnRadioDestroyer.setText("Destroyer (" + destroyerCount + ")");
		   			
		       if(destroyerCount == 0) {
		           btnRadioDestroyer.setEnabled(false);
		           freeButton().setSelected(true);
		       }
		   }
		   
	   } else if (isCarrierSelected && btnRadioCarrier.isEnabled()) {
	       s = new Carrier(x, y, alignment, "Carrier");
	   		
		   if(game.getPlayer().getBoard().addShip(s)) {
	   			carrierCount--;
	   			colorizeButtons(s);
	   			btnRadioCarrier.setText("Carrier (" + carrierCount + ")");
	   			
	   				if(carrierCount == 0) {
	   					btnRadioCarrier.setEnabled(false);
	   					freeButton().setSelected(true);
	   				}
		   }
		   
	   } else if (isCruiserSelected && btnRadioCruiser.isEnabled()) {
		   s = new Cruiser(x, y, alignment, "Cruiser");
		   
		   if(game.getPlayer().getBoard().addShip(s)) {
	   			cruiserCount--;
	   			colorizeButtons(s);
	   			btnRadioCruiser.setText("Cruiser (" + cruiserCount + ")");
	   			
	   				if(cruiserCount == 0) {
	   					btnRadioCruiser.setEnabled(false);
	   					freeButton().setSelected(true);
	   				}
		   }
	   				
	   } else if (isBattleshipSelected && btnRadioBattleship.isEnabled()) {
		   s = new Battleship(x, y, alignment, "Battleship");
		   
	   		
		   if(game.getPlayer().getBoard().addShip(s)) {
	   			battleshipCount--;
	   			colorizeButtons(s);
	   			btnRadioBattleship.setText("Battleship (" + battleshipCount + ")");
	   			
	   				if(battleshipCount == 0) {
	   					btnRadioBattleship.setEnabled(false);
	   					freeButton().setSelected(true);
	   				}
		   }
	   } else if (isSubmarineSelected && btnRadioSubmarine.isEnabled()) {
		   s = new Submarine(x, y, alignment, "Submarine");
		   
	   		
		   if(game.getPlayer().getBoard().addShip(s)) {
	   			submarineCount--;
	   			colorizeButtons(s);
	   			btnRadioSubmarine.setText("Submarine (" + submarineCount + ")");
	   			
	   				if(submarineCount == 0) {
	   					btnRadioSubmarine.setEnabled(false);
	   					freeButton().setSelected(true);
	   				}
		   }
	   }
	  
	   if(game.getPlayer().getBoard().getShipcount() == BattleShipGame.SHIPCOUNT) {
	       new BattleShipGameGUI(init);
	       this.dispose();
	   }
	   	   
   }
   
   /**
    * Method to color the buttons when ship is added
    * Uses x,y coordinates with ship's lengnth
    * @param s the ship used to colorize right buttons
    * */
   public void colorizeButtons(Ship s) {
   	
   		int x = s.getCoordinateX();
   		int y = s.getCoordinateY();
   		
   		for(int i = 0 ; i < s.getShiplength();i++)
	    {
   			jButtonList[x][y].setBackground(new Color(122,138,153));

   			if(s.getAlignment() > 0) {
   				x++;
   			} else {
   				y++;
   			}
	        
	    }
   	}
   	
}