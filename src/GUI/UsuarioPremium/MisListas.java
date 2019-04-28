package GUI.UsuarioPremium;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

public class MisListas extends JPanel{
	
	public MisListas() {
		
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		/* Canciones */	
		String[] titulos = {"Lista", "Duracion"};
		Object[][] filas = {
		{"Lista 1", "00:00"},
		{"Lista 2", "00:00"},
		{"Lista 3", "00:00"},
		{"Lista 4", "00:00"},
		{"Lista 5", "00:00"},
		{"Lista 6", "00:00"},
		{"Lista 7", "00:00"},
		{"Lista 8", "00:00"},
		{"Lista 9", "00:00"},
		{"Lista 10", "00:00"},
		};
		DefaultTableModel datosListas = new DefaultTableModel(filas, titulos);
		JTable tablaListas = new JTable(datosListas);
		tablaListas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tablaListas.setPreferredScrollableViewportSize(new Dimension(800, 500));
		JScrollPane scrollTablaListas = new JScrollPane(tablaListas);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollTablaListas, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, scrollTablaListas, 0, SpringLayout.VERTICAL_CENTER, this);
		
		this.add(scrollTablaListas);
		
	}

}
