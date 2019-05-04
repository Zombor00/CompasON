package GUI.AccesoComun;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class FormularioAlbum extends JPanel {
	
	private ArrayList<Integer> cancionesSeleccionadas = new ArrayList<Integer>();
	private JTextField nombre;
	private JTextField canciones;
	
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

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nombre, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, nombre, 0, SpringLayout.VERTICAL_CENTER, this);
		
		layout.putConstraint(SpringLayout.WEST, canciones, 0, SpringLayout.WEST, nombre);
		layout.putConstraint(SpringLayout.NORTH, canciones, 5, SpringLayout.SOUTH, nombre);
		
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
								cancionesSeleccionadas);
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
	}

}
