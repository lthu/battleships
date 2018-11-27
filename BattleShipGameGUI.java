
import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;    

import javax.swing.text.DefaultCaret;


class BattleShipGameGUI extends JFrame implements ActionListener{
        

    private static final long serialVersionUID = 1L;
    private JButton button;
    private BattleShipGame game;
    private BattleShipBoard playerboard;
    private BattleShipBoard cpuboard;
    private Player player1;
    private CPUPlayer cpu;
    private Init instance;
    private int fireX;
    private int fireY;
    
    private	JPanel leftPanel;      
    private	JPanel rightPanel;
    private	JPanel bottomPanel;
    private JSplitPane sp;
    private JSplitPane sp2;
    private JTextField textField;
    private JTextArea textArea; 	
    private JTextPane textPane;
    private JTextPane [][] tPaneList = new JTextPane[11][11];
    private JButton [][] buttonList = new JButton[11][11];

    
    
    

    /**
     * Used to set everything up.
     * @param i the initialiser is passed from ShipInsertGUI to also let this GUI know about the game. 
     */
   public BattleShipGameGUI(Init i) {
	   this.instance = i;
	   this.game = i.getGame();

       this.player1 = game.getPlayer();
       this.cpu = game.getCPU();
       this.cpuboard = cpu.getBoard();
       this.playerboard = player1.getBoard();
       
	   initComponents();
	   
	   
  
   }
   

   private void initComponents() {
	 	


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
	      

	        sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
	        sp2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sp, bottomPanel);

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
	    
	    
	    /*
	     * Like in previous gui these buttons also contain the 
	     * x, y coordinates.
	     * 
	     * */
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
	    
	    
	    /*
	     *Textpanes are used for CPU's hits as we only can observe them. 
	     */
	    
	    for(int i = 0 ; i < key.length;i++)
	    {

	    	
	    	textPane = new JTextPane();
	    	textPane.setBackground(new Color(0, 105, 148));
	    	textPane.setText(" ~ " );
	    	textPane.setName("cpu " + i);
	    	textPane.setFont(f);
	    	
	    	textPane.setEditable(false);
	    	tPaneList[x][y] = textPane;
	    	rightPanel.add(textPane);
	
	    	if(x > 9) {
	    	    y++; 
	    	    x=0;
	    	}
	         x++;    
	
	    }
	    scroll.setPreferredSize(new Dimension(300,100));
	    
	    bottomPanel.add(scroll);
	    

	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setTitle("Battleships");
 
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
	      
	   
	      setVisible(true);
	      pack();
   }
    
   
    /*
    * Used for debugging. 
    * 
    * */
	public void setOptions() {
	    int x = 1;
	    int y = 1;
	    
		for(int i = 0 ; i < 100;i++)
	    {
			Integer options = game.getCPU().getBoard().getOptions(cpuboard, x, y);
			
			String op = options.toString();

	    	tPaneList[x][y].setText(op);

	
	    	   if(x > 9) {y++; x=0;}
	            x++;    
	
	    }
	}
   /**
    * Actionlistener for buttons.
    * Every button is disable right after click. 
    * After each click gui calls for check if it hit and colors the button
    * with right kind of color.
    * 
    * Color for hit comes from Ship but for default they all are the 
    * same color.
    * 
    * After hitting a button. GUI calls for CPU's hit and 
    * acts accordingly.
    * 
    * */
	public void actionPerformed(ActionEvent e){
	   	

	    ((JComponent) e.getSource()).setEnabled(false);
		fireX = (Integer)((JComponent) e.getSource()).getClientProperty("x");
		fireY = (Integer)((JComponent) e.getSource()).getClientProperty("y");
		  

		player1.fire(cpuboard, fireX, fireY);
		        
		JButton btn = ((JButton) e.getSource());
		  
		if( game.isTargetHit() ) {
		    Color c = cpuboard.getShip(fireX, fireY).getColor();
		    btn.setBackground(c);
		    btn.setText("X");
		      
		    if(game.isCPUShipBroken() ) {
		        colorizeButtons(cpuboard.getShip(fireX, fireY));
		        game.setCPUShipIsBroken(false);
		    }
            
		}else {
		    btn.setText("O");
		  }
        
		Font f_large = new Font(Font.SANS_SERIF, 3, 35);
        btn.setFont(f_large);
     
        textArea.setText(textArea.getText()+"\n" + game.getText() );
     

        cpu.randomizeFire(playerboard);

        int [] cpuFire = game.getCPUFire();
        int cpuFireX = cpuFire[0];
        int cpuFireY = cpuFire[1];
        

        if( game.isTargetHit() ) {
        	tPaneList[cpuFireX][cpuFireY].setBackground(new Color(255,0,0));
        	tPaneList[cpuFireX][cpuFireY].setText(" X ");
        	tPaneList[cpuFireX][cpuFireY].setFont(f_large);
        	
        }else {
        	tPaneList[cpuFireX][cpuFireY].setBackground(new Color(0,110, 135));
            tPaneList[cpuFireX][cpuFireY].setText(" O ");
            tPaneList[cpuFireX][cpuFireY].setFont(f_large);
            
        }
        	textArea.setText(textArea.getText()+"\n"+ game.getText());
        	checkGameOver();
   }

 
	/**
	 * After each firing checks if game is over. 
	 * User is asked if they want to continue playing.
	 */
	public void checkGameOver() {

	  if(game.isGameOver()) {
		  String message = "Game over. The winner is " + game.getWinner() + "\n Play again?";
	      int answer = JOptionPane.showConfirmDialog(getContentPane(), message);
	      
	      if(answer < 1) {
	          
	          game.restartGame();
	          instance.resetShips(cpuboard);
	          restartGUI();

	      }else {
	          System.exit(1);
	      }
	  
	  }
  }
	public void colorizeButtons(Ship s) {
	       
	    int x = s.getCoordinateX();
	    int y = s.getCoordinateY();
	        
	    for(int i = 0 ; i < s.getShiplength();i++){
	        
	        buttonList[x][y].setBackground(new Color(130,0,0));

	        if(s.getAlignment() > 0) {
	            x++;
	        } else {
	            y++;
	        }
	            
	    }
	}
	   
	public void restartGUI() {
	       
	    new ShipInsertGUI(instance);
	    
	    textPane.setBackground(new Color(0, 105, 148));

		int x = 1;
		int y = 1;
		   
		for(int i = 0 ; i < 100;i++){
		    	
		    tPaneList[x][y].setBackground(new Color(0, 105, 148));
			buttonList[x][y].setBackground(new Color(0, 105, 148));
			buttonList[x][y].setEnabled(true);
			   

		    if(x > 9) {
		        y++; 
		        x=0;
		    }
		    x++;    
		}
		 
		this.dispose();
	
	

	}  
}
 