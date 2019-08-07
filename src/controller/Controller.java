package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import model.Model;
import model.R201;

public class Controller {
	
	private Model model;
	
	public Controller(Model model) {
		this.model = model;
	}

	public void existCharges(R201 compressor) {
		if(compressor.getCharges().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Le compresseur ne supporte aucune charge", "Information", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			model.setCompressor(compressor);
		}
	}
	
	public void controlCharge(Integer charge) {
		R201 compresseur = model.getCompressor();
		
		if(charge != null) {
			if(compresseur.toString().equals("R201A") || compresseur.toString().equals("R201B")) {	
				if(charge == 62 || charge == 75)
					JOptionPane.showMessageDialog(null, 
							"Une charge de " + charge + "% est dangereuse pour un compresseur de type " + compresseur.toString() + ".\nBouchage par température élevée de la réfrigiration des garnitures.", 
							"Attention", 
							JOptionPane.WARNING_MESSAGE);
			}
			model.setCharge(charge);	
		}
	}
}
