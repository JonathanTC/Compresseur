package view;

import java.awt.Color;

import javax.swing.JButton;

public class Button extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int debit;
	
	public Button(String title, int value) {
		super(title);
		debit = value;
		this.desactive();
	}
	
	public void active() {
		this.setBackground(new Color(255, 116, 3));
	}
	
	public void desactive() {
		this.setBackground(new Color (2, 136, 18));
	}
	
	public int getDebit() {
		return debit;
	}
}
