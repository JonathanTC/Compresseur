package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import view.Button;

public class R201C extends R201{
	public R201C() {
		super();
		
		debitList.put(0, 0);
		debitList.put(61, 12200);
		debitList.put(82, 16400);
		debitList.put(100, 20000);
		
		try {
			image = ImageIO.read(new File("images/R201C.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return "R201C";
	}
}
