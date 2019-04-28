package GUI.AccesoComun;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Busqueda extends JPanel {

	public Busqueda() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		JTextField busqueda = new JTextField(20);
		JButton buscar   = new JButton("Buscar");
		JPanel buscador = new JPanel();
		buscador.setLayout(new FlowLayout());
		buscador.add(busqueda);
		buscador.add(buscar);
		
		String[] titulos = {"Cancion", "Autor", "Fecha"};
		Object[][] filas = {
		{"Cancion 1", "Autor 1", LocalDate.now()},
		{"Cancion 2", "Autor 2", LocalDate.now()},
		{"Cancion 3", "Autor 3", LocalDate.now()},
		{"Cancion 4", "Autor 4", LocalDate.now()},
		{"Cancion 5", "Autor 5", LocalDate.now()},
		{"Cancion 6", "Autor 6", LocalDate.now()},
		{"Cancion 7", "Autor 7", LocalDate.now()},
		{"Cancion 8", "Autor 8", LocalDate.now()},
		{"Cancion 9", "Autor 9", LocalDate.now()},
		{"Cancion 10", "Autor 10", LocalDate.now()},
		};
		DefaultTableModel modeloDatos = new DefaultTableModel(filas, titulos);
		JTable tabla = new JTable(modeloDatos);
		tabla.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tabla.setPreferredScrollableViewportSize(new Dimension(800, 500));
		JScrollPane scrollTabla = new JScrollPane(tabla);
		
		

		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollTabla, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, scrollTabla, 0, SpringLayout.VERTICAL_CENTER, this);
		
		layout.putConstraint(SpringLayout.SOUTH, buscador, -50, SpringLayout.NORTH, scrollTabla);
		layout.putConstraint(SpringLayout.EAST, buscador, 0, SpringLayout.EAST, scrollTabla);

		
		this.add(buscador);
		this.add(scrollTabla);
	}
}
