/**
 * Concentration.java
 * 
 * @version: 1.0
 *   
 * $ jmc4676 $
 * $ smb7739 $
 * 
 * Revisions: 2013-01-26 09:00:00
 * Revisions: 2013-01-27 10:30:00
 * Revisions: 2013-01-28 04:10:00
 * Revisions: 2013-01-29 05:00:00
 * $Log$
*/
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Represents the Concentration memory game.
 * 
 * @author: Johanna Calderon
 * @author: Samah Balkhair
 */
public class Concentration extends JFrame {
	
	private final int R = 4;   //rows of the board (constant)
	private final int C = 4;   //columns of the board (constant)
	private int numMatch = 0;  // keep tracking for number button that is matching 
	//create a label for the message
	final JLabel msg = new JLabel("Select the first location.");
	//create a panel for the board with a GridLayout
	final JPanel pBoard = new JPanel(new GridLayout(R, C));
	//create a list to store the cards numbers and shuffle them
	ArrayList<Integer> l = new ArrayList<Integer>();
	//create a list to store color
	// here btn 3
	//private Color color[] = new Color[(C*R)/2];
	private CButton firstcard;
	//to use as a track if a card is already face up
	private boolean scndLoc = false;  
	
	// here
	//menu declaration 

	
	/**
	 * Constructor to initialize the JFrame
	 * with all components inside.
	 */
	public Concentration(){
		//here version #
		this.setTitle("Game GUI. V 1.0");
		this.getContentPane().setLayout(new BorderLayout());

		//window listener when closing the frame
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
					System.exit(0);
			}
		});
		
		//fill the color array with random color
		// here btn 4
		//generateRandomColor();
		
		//variables to manage the numbers of cards
		int t = R * C;  //total of cards
		final int p = t / 2;  //half of cards
		
		//Store number in the Array list and shuffle them.
		for (int x = 0; x < p; x++) {
			for (int y = 0; y < 2; y++) {
				l.add(x);			
			}
		}
		Collections.shuffle(l);
		
		//insert cards in the grid
		for (int i = 0; i < t; i++) {
			int n = l.get(i);  //get the number from the ArrayList
			
            //panels for the background cards
			final JPanel cards = new JPanel(new BorderLayout());
			cards.setName(String.valueOf(n));
			pBoard.add(cards);
			
			//buttons to cover the cards
			final CButton button = new 	CButton();
            //buttons should be empty
			button.setValue(n);
			button.setFont(new Font("Arial", Font.PLAIN, 60)); 
			button.setContentAreaFilled(false);
			button.setOpaque(true);
			//here font color
			button.setForeground(Color.WHITE);
			
			
			//action listener for the buttons: anonymous class
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					
					if(!button.isFaceUp()){
						
						button.setFaceUp(true);  
						changeButtonStyle(button);
	
						if(!scndLoc){ //1st card
							firstcard = button;
							scndLoc = true;
							msg.setText("select 2nd location");
					
						}else {//2nd card
							
							if(button.getValue() == firstcard.getValue()){//match
								//set enable false
								button.setEnabled(false);
								firstcard.setEnabled(false);
								msg.setText("MATCH: Select a location");
								numMatch++;
								
							}else{//not match
								
								msg.setText("NOT MATCH: try again");
								lockButtons(true);
								//anonymous class for the time delay in displaying the card
								new java.util.Timer().schedule( 
								        new java.util.TimerTask() {
								            //@override: hide the buttons again
								            public void run() {
								            	lockButtons(false);
												firstcard.setFaceUp(false);
												button.setFaceUp(false);
												changeButtonStyle(button);
												changeButtonStyle(firstcard);
								            }
								        }, 
								        500  //time to delay
								);//end of time delay
							}//end no match
							scndLoc = false;
							if (numMatch == p){
							     JOptionPane.showMessageDialog( pBoard, "congratulation, you win.",
							    		 "congratulation", JOptionPane.NO_OPTION);
							     newGame();
							}
						}//end of 2nd card
					}//end if button is face down
				} //end of action perform event
			}); //anonymous class: action listener end
			
			cards.add(button, BorderLayout.CENTER);
		}
			
		
		//create buttons to clear and exit the game
		JPanel pB = new JPanel(new FlowLayout());
		JButton bQuit = new JButton("Quit");
		    //action listener for Quit button
		    bQuit.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent event) {
			    	if((JOptionPane.showConfirmDialog(pBoard,  "Do you want quit the game?", 
			    			"Quit", JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION){
			    				System.exit(0);
			    	}
			    }
		    }); //quit action listener ends
		JButton bClear = new JButton("Clear");
			//action listener for Clear button
		    bClear.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent event) {
			    	if((JOptionPane.showConfirmDialog(pBoard,  "Do you want start new game?", 
			    			"New Game", JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION){
			    					    	newGame();
			    	}
     		    }
		    }); //clear action listener ends
		pB.add(bClear);
		pB.add(bQuit);
		
		//add the buttons to the frame in the south
		this.getContentPane().add(pB, BorderLayout.SOUTH);
		
		//add the board panel to the frame in the center
		this.getContentPane().add(pBoard, BorderLayout.CENTER);
		
		//add the label to the frame in the north
		this.getContentPane().add(msg, BorderLayout.NORTH);
		
		//set the frame properties
		Dimension d = new Dimension(600, 600); //size of the frame
		this.pack();
		this.setVisible(true);
		this.setSize(d);
		this.setMinimumSize(d);
		//this.setResizable(false);
		this.setLocationRelativeTo(null);  //centralize the window in the screen 
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//here menu
		// menu setting
		
				
	}
	
	/**
	 * Clear the board for a new game
	 */
	public void newGame(){
		msg.setText("Select the first location.");
		int btnNum = 0;
	    numMatch=0;
		Collections.shuffle(l);
		for(int i=0; i<pBoard.getComponentCount(); i++){
			if(pBoard.getComponent(i)instanceof JPanel){
				JPanel p = (JPanel) pBoard.getComponent(i);
				for(int j =0; j < p.getComponentCount(); j++){
					if(p.getComponent(j)instanceof CButton){
						int n = l.get(btnNum);  //get the number from the ArrayList
						CButton btn = (CButton) p.getComponent(0);
						btn.setFaceUp(false);
						btn.setValue(n);
						// here btn 1
						//btn.setBackground(color[n]);
						btn.setEnabled(true);
						changeButtonStyle(btn);
						btnNum++;
					}
				}
			}
		}
	}
	
	/**
	 * Changes the style of the button depending the event
	 * 
	 * @param btn     button pressed
	 */
	public void changeButtonStyle(CButton btn){
		if(btn.isFaceUp()){
			btn.setText(String.valueOf(btn.getValue()));
			// here btn 2
			//btn.setBackground(color[btn.getValue()]);
			btn.setOpaque(true);
			btn.validate();
		}else{	
			btn.setText("");
			btn.setOpaque(false);
			btn.validate();
		}
	}
	
	
	// here btn generator func
	// code # 1
	


	/**
	 * This method to lock all buttons to avoid user
	 * form click any button when there are non matching
	 * and buttons are face up.
	 * 
	 * @param lock    lock/unlock button
	 */
	public void lockButtons(boolean lock){
		int btnNum = 0;
		for(int i=0; i<pBoard.getComponentCount(); i++){
			if(pBoard.getComponent(i)instanceof JPanel){
				JPanel p = (JPanel) pBoard.getComponent(i);
				for(int j =0; j < p.getComponentCount(); j++){
					if(p.getComponent(j)instanceof CButton){
						CButton btn = (CButton) p.getComponent(0);
						if(!btn.isFaceUp()){
							btn.setEnabled(!lock);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Main method to start the Frame
	 * @param args   ignored
	 */
	public static void main(String[] args) {
		Concentration b = new Concentration();   
	}

}
