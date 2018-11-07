
import java.awt.*;       // Using layouts
import java.awt.event.*; // Using AWT event classes and listener interfaces
import javax.swing.*;    // Using Swing components and containers

import javax.swing.text.DefaultCaret;

import java.util.ArrayList;


// A Swing GUI application inherits the top-level container javax.swing.JFrame
class BattleShipGameGUI extends JFrame implements ActionListener{
        
    JButton button;
    BattleShipGame game;
    Init instance;
    int fireX;
    int fireY;
    
    private	JSplitPane splitPane;
    private	JSplitPane splitPane2; 
    private	JPanel leftPanel;       // container panel for the top
    private	JPanel rightPanel;
    private	JPanel bottomPanel;
    private	JScrollPane scrollPane; // makes the text scrollable
    private JTextField textField;
    private JTextArea textArea; 	
    private JTextPane textPane;
    private JTextPane [][] tPaneList = new JTextPane[15][15];
    private JButton [][] buttonList = new JButton[15][15];
    private String [][] key;
    private int temp;
    private boolean isCpuHit;
    

   // Constructor to setup the GUI components and event handlers
   public BattleShipGameGUI(Init i) {
	   this.instance = i;
	   this.game = i.getGame();
	   initComponents();
	   CPUPlayer cpu = (CPUPlayer)game.getOpponent();
	   
  
   }
   

private void initComponents() {
	 	setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\OT\\php\\php-oo\\huuskonen-lauri\\battleship\\resources\\battleship.png"));


	 		getContentPane().setLayout(new GridLayout(1,2));

	        leftPanel = new JPanel();       
	        rightPanel = new JPanel();
	        bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));      


	        textField = new JTextField();
	        textField.setColumns(100);
		
	        textArea = new JTextArea();
	       
	        JScrollPane scroll = new JScrollPane(new JScrollPane (textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
	        DefaultCaret caret = (DefaultCaret)textArea.getCaret();
	        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	        
	        setPreferredSize(new Dimension(1200, 900));
	            
	        leftPanel.setLayout(new GridLayout(10, 10, 0, 0));
	        rightPanel.setLayout(new GridLayout(10, 10, 0,0));
	      

	        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
	        JSplitPane sp2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sp, bottomPanel);

	        getContentPane().add(sp2);
	        sp2.setDividerLocation(700);       

	        String [] key = {"A1", "B1", "C1", "D1", "E1", "F1", "G1", "H1", "I1", "J1", 
			        		"A2", "B2", "C2", "D2", "E2", "F2", "G2", "H2", "I2", "J2",
			        		"A3", "B3", "C3", "D3", "E3", "F3", "G3", "H3", "I3", "J3",
			        		"A4", "B4", "C4", "D4", "E4", "F4", "G4", "H4", "I4", "J4",
			        		"A5", "B5", "C5", "D5", "E5", "F5", "G5", "H5", "I5", "J5",
			        		"A6", "B6", "C6", "D6", "E6", "F6", "G6", "H6", "I6", "J6",
			        		"A7", "B7", "C7", "D7", "E7", "F7", "G7", "H7", "I7", "J7",
			        		"A8", "B8", "C8", "D8", "E8", "F8", "G8", "H8", "I8", "J8",
			        		"A9", "B9", "C9", "D9", "E9", "F9", "G9", "H9", "I9", "J9",
			        		"A10", "B10", "C10", "D10", "E10", "F10", "G10", "H10", "I10", "J10" };
 
	    int x = 1;
	    int y = 1;
	    Font f = new Font(Font.SANS_SERIF, 3, 15);
	    
	    for(int i = 0 ; i < key.length;i++)
	    {
	      
	    	button = new JButton(key[i]);
	    	
	    	button.setFont(f);
	        button.setName(key[i]);
	        button.setBorderPainted(false);
	        button.setBackground(new Color(0, 105, 148));
	        

	        button.putClientProperty( "x", x );
	        button.putClientProperty( "y", y );
	        button.addActionListener(this);
	        buttonList[x][y] = button;
	        
	        leftPanel.add(button);
	        
	        if(x > 9) { y++; x=0;}
	        	x++;    
	
	    }
	  
	    x = 1;
	    y = 1;
	    
	    
	    for(int i = 0 ; i < key.length;i++)
	    {
	    	//String n = key[i]+" ";
	    	
	    	textPane = new JTextPane();
	    	textPane.setBackground(new Color(0, 105, 148));
	    	textPane.setText("X");
	    	textPane.setName("cpu " + i);
	    	textPane.setFont(f);
	    	
	    	
	    	//textPane.putClientProperty(isCpuHit, false);
	    	
	    	textPane.setEditable(false);
	    	
	    	tPaneList[x][y] = textPane;
	    	
	    	rightPanel.add(textPane);
	    	
	    	//System.out.println("x " + x + " y: " + y);
	    	
	
	    	   if(x > 9) {y++; x=0;}
	            x++;    
	
	    }
	    scroll.setPreferredSize(new Dimension(300,100));
	    
	    bottomPanel.add(scroll);
	    

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
			setTitle("Battleships"); // "super" Frame sets title
	      //setSize(600, 600);  // "super" Frame sets initial size
	      
	      JMenuBar menuBar = new JMenuBar();
	      setJMenuBar(menuBar);
	      
	      JMenu mnNewMenu = new JMenu("Game");
	      menuBar.add(mnNewMenu);
	      
	      JMenuItem mntmNewMenuItem_1 = new JMenuItem("New game");
	      mnNewMenu.add(mntmNewMenuItem_1);
	      
	      mntmNewMenuItem_1.addActionListener( new ActionListener() {
	    	  public void actionPerformed(ActionEvent e) {
	    		  game.restartGame(); 
	    		  restartGUI();
	    	  }
	      });
	      mntmNewMenuItem_1.putClientProperty("x", 100);
	      
	   
	      setVisible(true);   // "super" Frame shows
	      pack();
   }
   
	public void setOptions() {
	    int x = 1;
	    int y = 1;
	    BattleShipBoard cpuboard = game.getPlayer().getBoard();
		for(int i = 0 ; i < 100;i++)
	    {
			Integer options = game.getOpponent().getBoard().getOptions(cpuboard, x, y);
			
			String op = options.toString();

	    	tPaneList[x][y].setText(op);

	
	    	   if(x > 9) {y++; x=0;}
	            x++;    
	
	    }
	}
   
	public void actionPerformed(ActionEvent e){
	   	

	   

    		((JComponent) e.getSource()).setEnabled(false);
		  fireX = (Integer)((JComponent) e.getSource()).getClientProperty("x");
		  fireY = (Integer)((JComponent) e.getSource()).getClientProperty("y");
		  
		  game.getPlayer().fire(game.getOpponent().getBoard(), fireX, fireY);
		  
		  

		  //System.out.println(fireX +" "+ fireY);
		  //System.out.println(game.getOpponent().getBoard().getShip(fireX, fireY));
		  String n = ((JComponent) e.getSource()).getName();
        
       // System.out.println("fireX: " + fireX + "fireY: "+ fireY);
        
		  JButton btn = ((JButton) e.getSource());
		  
        if( game.HIT ) {
        	Color c = game.getOpponent().getBoard().getShip(fireX, fireY).getColor();
        	
        	btn.setText("X");

            ((JComponent) e.getSource()).setBackground(c);
            
        }else {
        	btn.setText("O");
        }
        
        Font f_large = new Font(Font.SANS_SERIF, 3, 35);
        btn.setFont(f_large);
     
        textArea.setText(textArea.getText()+"\n" + game.getText() );
     
        game.getOpponent().randomizeFire(game.getPlayer().getBoard());
        setOptions();
        
        
        int [] cpuFire = game.getCPUFire();
        int cpuFireX = cpuFire[0];
        int cpuFireY = cpuFire[1];
        
        

        if( game.HIT ) {

        	tPaneList[cpuFireX][cpuFireY].setBackground(new Color(255,0,0));
       
        }else {
        	
        	tPaneList[cpuFireX][cpuFireY].setBackground(new Color(255,255,255));
        	

        	
        }
        	textArea.setText(textArea.getText()+"\n"+ game.getText());
        	checkEndGame();
   }

 

  public void checkEndGame() {
	  //System.out.println("game ended: " + game.GAME_ENDED);
	  if(game.GAME_ENDED) {
		  String message = "Game over. The winner is player1 \n Play again?";
	      int answer = JOptionPane.showConfirmDialog(getContentPane(), message);
      
      if(answer < 1) {
    	  game.restartGame();
    	  restartGUI();
    	  
    	  //setShips(game.getOpponent().getBoard());
      }else {
    	  System.exit(1);
      }
	  
	  }
  }
  public void restartGUI() {
	       
	  new ShipInsertGUI(instance);
	  instance.setShips(game.getOpponent().getBoard());
	  instance.setShips(game.getPlayer().getBoard());
	  
		textPane.setBackground(new Color(0, 105, 148));

		int x = 1;
		int y = 1;
		   
		   for(int i = 0 ; i < 100;i++)
		    {
		    	
			   tPaneList[x][y].setBackground(new Color(0, 105, 148));
			   buttonList[x][y].setBackground(new Color(0, 105, 148));
			   buttonList[x][y].setEnabled(true);
			   

		    	   if(x > 9) {y++; x=0;}
		            x++;    
		
		    }
		 
		   this.dispose();
	
	

}




   
   
	  
  }
 