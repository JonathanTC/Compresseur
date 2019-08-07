package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Image image;
	
	public ImagePanel() {
		super();
	}
	
	public ImagePanel(Image image) {
		super();
		this.image = image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public Image getImage() {
		return this.image;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
	}
}
