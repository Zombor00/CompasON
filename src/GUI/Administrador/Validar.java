package GUI.Administrador;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import aplicacion.Aplicacion;
import gestion.Denuncia;
import media.Cancion;
import usuarios.Administrador;;


public class Validar extends JPanel {
	
	private DefaultTableModel modeloDatos;
	private JTable tabla;

	public Validar() {
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
		modeloDatos = new DefaultTableModel(filas, titulos);
		tabla = new JTable(modeloDatos);
		tabla.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tabla.setPreferredScrollableViewportSize(new Dimension(800, 500));
		JScrollPane scrollTabla = new JScrollPane(tabla);
		
		

		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollTabla, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, scrollTabla, 0, SpringLayout.VERTICAL_CENTER, this);

		
	
		this.add(scrollTabla);
	}

	public void actualizarDatos() {
		int numFilas = modeloDatos.getRowCount();
		for(int i=0; i< numFilas; i++) {
			modeloDatos.removeRow(0);
		}
		Administrador a = Aplicacion.getInstance().getAdministrador();
		Object[] rowData = {0,0,0};
		
		for (Cancion c : a.getCancionesNuevas()) {
			if (!c.esValido() && c.getModificableHasta() == null) {
				rowData[0] = c;
				rowData[1] = c.getAutor();
				rowData[2] = c.getDuracion();
			}
			modeloDatos.addRow(rowData);
		}		
	}

	
}
