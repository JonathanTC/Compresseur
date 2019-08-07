package hcq;

import model.Model;
import view.FenetreHCQ;

public class Main {
	public static void main(String[] args) {
		
		Model model = new Model();
		FenetreHCQ fenHCQ = new FenetreHCQ(model);
		model.addObserver(fenHCQ);
	}
}
