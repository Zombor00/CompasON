package GUI.Administrador;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import aplicacion.Aplicacion;
import gestion.Denuncia;
import usuarios.Administrador;


public class Denuncias extends JPanel {
	
	private DefaultTableModel modeloDatos;
	private JTable tabla;

	public Denuncias() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		
		String[] titulos = {"Cancion", "Autor", "Duracion", "Comentario"};
		Object[][] filas = {
		{"Cancion 1", "Autor 1", "00:00","Comentario 1"},
		{"Cancion 2", "Autor 2", "00:00", "Comentario 2"},
		{"Cancion 3", "Autor 3", "00:00", "Comentario 3"},
		{"Cancion 4", "Autor 4", "00:00", "Comentario 4"},
		{"Cancion 5", "Autor 5", "00:00", "Comentario 5"},
		{"Cancion 6", "Autor 6", "00:00", "Comentario 6"},
		{"Cancion 7", "Autor 7", "00:00", "Comentario 7"},
		{"Cancion 8", "Autor 8", "00:00", "Comentario 8"},
		{"Cancion 9", "Autor 9", "00:00", "Comentario 9"},
		{"Cancion 10", "Autor 10", "00:00", "Comentario 10"},
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
		Object[] rowData = {0,0,0,0};
		
		for (Denuncia d : a.getDenuncias()) {
			rowData[0] = d.getDenunciada();
			rowData[1] = d.getDenunciada().getAutor();
			rowData[2] = d.getDenunciada().getDuracion();
			rowData[3] = d.getComentario();
			modeloDatos.addRow(rowData);
		}		
	}

	
}
