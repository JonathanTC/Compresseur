package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import controller.Controller;
import model.Model;
import model.R201;
import observer.Observer;

public class FenetreHCQ extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller controller;
	
	private JComboBox<R201> comboCompressor;
	private JComboBox<Integer> comboCharge;
	private DefaultComboBoxModel<Integer> comboModel;
	private DefaultComboBoxModel<R201> modelCompressor;
	
	private ImagePanel imagePane;
	private JPanel containerPane;
	
	private JTable calculTable;
	private DefaultTableModel modelTable;
	
	public FenetreHCQ(Model model) {
		this.setTitle("Compresseurs R201");
		this.setSize(500, 370);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1, 2));
		
		controller = new Controller(model);
		
		imagePane = new ImagePanel();
		containerPane = new JPanel();
		containerPane.setLayout(new GridLayout(3, 1));
		
		// Création de la liste des compresseurs
		modelCompressor = new DefaultComboBoxModel<R201>();
		comboCompressor = new JComboBox<R201>();
		comboCompressor.setModel(modelCompressor);	
		comboCompressor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.existCharges((R201) comboCompressor.getSelectedItem());
			}		
		});

		// Création de la liste des charges possible
		comboModel = new DefaultComboBoxModel<Integer>();
		comboCharge = new JComboBox<>();
		comboCharge.setModel(comboModel);
		comboCharge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer charge = (Integer) comboCharge.getSelectedItem();
				controller.controlCharge(charge);
			}
		});
			
		// Création de la table de calcule
		modelTable = new DefaultTableModel();
		modelTable.addColumn("Type");
		modelTable.addColumn("Charge");
		modelTable.addColumn("Débit");
		
		calculTable = new JTable();
		calculTable.setModel(modelTable);
		calculTable.setDefaultRenderer(Object.class, new TableRender());
		
		initialize(model);
		
		containerPane.add(comboCompressor);
		containerPane.add(comboCharge);	
		containerPane.add(new JScrollPane(calculTable));
		
		this.add(imagePane);
		this.add(containerPane);		
		
		this.setVisible(true);
	}
	
	// initialise la vue avec les données du model
	public void initialize(Model model) {		
		modelCompressor.addAll(model.getCompressorList());
		comboModel.addAll(model.getCompressor().getCharges());
		
		comboCompressor.setSelectedItem(model.getCompressor());
		comboCharge.setSelectedItem(model.getCompressor().getCharge());
		
		imagePane.setImage(model.getCompressor().getImage());
		
		int debitTotal = 0;
		for(R201 compresseur : model.getCompressorList()) {
			
			Object[] rowData = { 
					compresseur.toString(), 
					compresseur.getCharge(),
					compresseur.getDebit() 
			};
			
			debitTotal += compresseur.getDebit();
			
			modelTable.addRow(rowData);
		}
		
		modelTable.addRow(new Object[] {"", "", debitTotal});
	}

	// mise à jour de la vue
	@Override
	public void update(R201 compressor) {
		comboModel.removeAllElements();
		comboModel.addAll(compressor.getCharges());
		
		comboCompressor.setSelectedItem(compressor);
		comboCharge.setSelectedItem(compressor.getCharge());
		
		imagePane.setImage(compressor.getImage());
		imagePane.repaint();
		
		int index = comboCompressor.getSelectedIndex();
		
		int debitTotal = (int) modelTable.getValueAt(3, 2) - (int) modelTable.getValueAt(index, 2);
		
		Object[] rowData = { 
				compressor.toString(), 
				compressor.getCharge(),
				compressor.getDebit() 
		};
		
		debitTotal += compressor.getDebit();
		
		modelTable.removeRow(index);
		modelTable.insertRow(index, rowData);
		
		modelTable.removeRow(3);
		modelTable.insertRow(3, new Object[] {"", "", debitTotal});
	}
}	

