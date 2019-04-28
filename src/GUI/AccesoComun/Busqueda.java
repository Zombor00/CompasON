package GUI.AccesoComun;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import media.Buscable;

public class Busqueda extends JPanel {
	
	private DefaultTableModel modeloDatos;
	private JButton buscar;
	private JTextField busqueda;

	public Busqueda() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		busqueda = new JTextField(20);
		buscar   = new JButton("Buscar");
		JPanel buscador = new JPanel();
		buscador.setLayout(new FlowLayout());
		buscador.add(busqueda);
		buscador.add(buscar);
		
		String[] titulos = {"Cancion", "Autor", "Fecha"};
		Object[][] filas = {
		};
		
	    modeloDatos = new DefaultTableModel(filas, titulos);
		JTable tabla = new JTable(modeloDatos);
		tabla.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tabla.setPreferredScrollableViewportSize(new Dimension(800, 500));
		JScrollPane scrollTabla = new JScrollPane(tabla);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollTabla, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, scrollTabla, 0, SpringLayout.VERTICAL_CENTER, this);
		
		layout.putConstraint(SpringLayout.SOUTH, buscador, 0, SpringLayout.NORTH, scrollTabla);
		layout.putConstraint(SpringLayout.EAST, buscador, 0, SpringLayout.EAST, scrollTabla);

		
		this.add(buscador);
		this.add(scrollTabla);
	}
	
	public void actualizarBusqueda(ArrayList<Buscable> buscables) {
		
		int numFilas = modeloDatos.getRowCount();
		for(int i=0; i< numFilas; i++) {
			modeloDatos.removeRow(0);
		}
		
		Object[] rowData = {0,0,0};
		for (Buscable b : buscables) {
			rowData[0] = b.getTitulo();
			rowData[1] = b.getAutor();
			rowData[2] = b.getDuracion();
			modeloDatos.addRow(rowData);
		}
	}
	
	public void setControlador(ActionListener controlador) {
		buscar.setActionCommand("BUSCAR");
		buscar.addActionListener(controlador);
	}
	
	public String getBusqueda() {
		return busqueda.getText();
	}
}
