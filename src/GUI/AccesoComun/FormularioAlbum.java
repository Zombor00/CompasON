package GUI.AccesoComun;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class FormularioAlbum extends JPanel {
	
	private ArrayList<Integer> cancionesSeleccionadas = new ArrayList<Integer>();
	private JTextField nombre;
	private JTextField canciones;
	private JComboBox<Integer> comboAnios;
	
	public FormularioAlbum(ArrayList<String> nombreCanciones) {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		nombre = new JTextField(30);
		canciones = new JTextField(21);
		JButton seleccionar = new JButton("Seleccionar");
		seleccionar.setPreferredSize(new Dimension(105, seleccionar.getPreferredSize().height));
		JLabel nombreLabel = new JLabel("Nombre del album:");
		JLabel cancionesLabel = new JLabel("Canciones:");
		Integer[] anios = {2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009,2008,2007,2006,2005,2004,2003,2002,2001,2000,1999,1998,1997,1996,1995,1994,1993,1992,1991,1990,1989,1988,1987,1986,1985,1984,1983,1982,1981,1980,1979,1978,1977,1976,1975,1974,1973,1972,1971,1970,1969,1968,1967,1966,1965,1964,1963,1962,1961,1960,1959,1958,1957,1956,1955,1954,1953,1952,1951,1950};
		comboAnios = new JComboBox<>(anios);	

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nombre, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, nombre, 0, SpringLayout.VERTICAL_CENTER, this);
		
		layout.putConstraint(SpringLayout.WEST, canciones, 0, SpringLayout.WEST, nombre);
		layout.putConstraint(SpringLayout.NORTH, canciones, 5, SpringLayout.SOUTH, nombre);
		
		layout.putConstraint(SpringLayout.WEST, comboAnios, 0, SpringLayout.WEST, canciones);
		layout.putConstraint(SpringLayout.NORTH, comboAnios, 5, SpringLayout.SOUTH, canciones);
		
		layout.putConstraint(SpringLayout.EAST, seleccionar, 0, SpringLayout.EAST, nombre);
		layout.putConstraint(SpringLayout.NORTH, seleccionar, 5, SpringLayout.SOUTH, nombre);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, nombreLabel,0,SpringLayout.VERTICAL_CENTER, nombre);
		layout.putConstraint(SpringLayout.EAST, nombreLabel,-75,SpringLayout.WEST, nombre);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, cancionesLabel,0,SpringLayout.VERTICAL_CENTER, canciones);
		layout.putConstraint(SpringLayout.WEST, cancionesLabel,0,SpringLayout.WEST, nombreLabel);
		

		
		

		this.add(nombre);
		this.add(nombreLabel);
		this.add(canciones);
		this.add(seleccionar);
		this.add(cancionesLabel);
		this.add(comboAnios);
		this.setPreferredSize(new Dimension(1000,300));
		
		
		/*Configuramos el boton "Buscar" */
		seleccionar.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						@SuppressWarnings("unused")
						JCheckBoxScrollableListSelect checkBoxScrollableListSelect = new JCheckBoxScrollableListSelect(
								"Seleccione las canciones para el nuevo album",
								nombreCanciones,
								canciones,
								cancionesSeleccionadas,
								null);
					}
				}
		);
		
	}
	
	public ArrayList<Integer> getCancionesSeleccionadas() {
		return this.cancionesSeleccionadas;
	}
	
	public String getNombre() {
		return this.nombre.getText();
	}
	
	public void actualizarDatos() {
		canciones.setText("");
		nombre.setText("");
		cancionesSeleccionadas.clear();
	}
	
	public Integer getAnio() {
		return (Integer)this.comboAnios.getSelectedItem();
	}

}
