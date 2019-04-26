package GUI.Administrador;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Tramitar extends JPanel {

	public Tramitar() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		
		String[] titulos = {"Cancion", "Autor", "Duracion"};
		Object[][] filas = {
		{"Cancion 1", "Autor 1", "00:00"},
		{"Cancion 2", "Autor 2", "00:00"},
		{"Cancion 3", "Autor 3", "00:00"},
		{"Cancion 4", "Autor 4", "00:00"},
		{"Cancion 5", "Autor 5", "00:00"},
		{"Cancion 6", "Autor 6", "00:00"},
		{"Cancion 7", "Autor 7", "00:00"},
		{"Cancion 8", "Autor 8", "00:00"},
		{"Cancion 9", "Autor 9", "00:00"},
		{"Cancion 10", "Autor 10", "00:00"},
		};
		DefaultTableModel modeloDatos = new DefaultTableModel(filas, titulos);
		JTable tabla = new JTable(modeloDatos);
		tabla.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tabla.setPreferredScrollableViewportSize(new Dimension(800, 80));
		JScrollPane scrollTabla = new JScrollPane(tabla);
		
		

		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollTabla, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, scrollTabla, 100, SpringLayout.NORTH, this);

		
	
		this.add(scrollTabla);
	}
}
