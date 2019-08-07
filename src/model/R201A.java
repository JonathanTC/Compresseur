package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class R201A extends R201{
	public R201A() {
		super();
		
		debitList.put(0, 0);
		debitList.put(62, 9260);
		debitList.put(75, 12450);
		debitList.put(83, 13778);
		debitList.put(87, 14585);
		debitList.put(93, 15562);
		debitList.put(100, 16600);
		
		try {
			image = ImageIO.read(new File("images/R201A.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return "R201A";
	}
}
