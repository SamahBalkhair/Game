/**
 * CButton.java
 * 
 * @version: 1.0
 *   
 * $ jmc4676 $
 * $ smb7739 $
 * 
 * Revisions: 2013-01-28 09:00:00
 * $Log$
 */

import javax.swing.JButton;

/**
 * Represents the Buttons to be use in
 * the Concentration class.
 * 
 * @author: Johanna Calderon
 * @author: Samah Balkhair
 */
public class CButton extends JButton {

	private boolean faceUp;
	private int value;  

	/**
	 * construct a new button face down
	 */
	public CButton() {
		faceUp = false;
	}

	/**
	 * @return the face of the button
	 */
	public boolean isFaceUp() {
		return faceUp;
	}

	/**
	 * set the face of the button
	 * 
	 * @param f     in what state is the button up or down
	 */
	public void setFaceUp(boolean f) {
		faceUp = f;
	}

	/**
	 * set the name of the button
	 * 
	 * @param v      the name is a number, integer value
	 */
	public void setValue(int v) {
		value = v;
	}

	/**
	 * @return the name of the button
	 */
	public int getValue() {
		return value;
	}

}
