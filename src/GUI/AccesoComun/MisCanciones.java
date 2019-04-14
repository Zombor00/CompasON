package GUI.AccesoComun;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MisCanciones extends JPanel{
	
	public MisCanciones() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		/* Canciones */
		JButton canciones = new JButton("Canciones");		
		String[] titulos = {"Cancion", "Duracion"};
		Object[][] filas = {
		{"Cancion 1", "00:00"},
		{"Cancion 2", "00:00"},
		{"Cancion 3", "00:00"},
		{"Cancion 4", "00:00"},
		{"Cancion 5", "00:00"},
		{"Cancion 6", "00:00"},
		{"Cancion 7", "00:00"},
		{"Cancion 8", "00:00"},
		{"Cancion 9", "00:00"},
		{"Cancion 10", "00:00"},
		};
		DefaultTableModel datosCanciones = new DefaultTableModel(filas, titulos);
		JTable tablaCanciones = new JTable(datosCanciones);
		tablaCanciones.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tablaCanciones.setPreferredScrollableViewportSize(new Dimension(500, 80));
		JScrollPane scrollTablaCanciones = new JScrollPane(tablaCanciones);
		
		/* Albumes */
		JButton albumes = new JButton("Albumes");
		String[] titulos2 = {"Album", "Duracion"};
		Object[][] filas2 = {
		{"Album 1", "00:00"},
		{"Album 2", "00:00"},
		{"Album 3", "00:00"},
		{"Album 4", "00:00"},
		{"Album 5", "00:00"},
		{"Album 6", "00:00"},
		{"Album 7", "00:00"},
		{"Album 8", "00:00"},
		{"Album 9", "00:00"},
		{"Album 10", "00:00"},
		};
		DefaultTableModel datosAlbumes = new DefaultTableModel(filas2, titulos2);
		JTable tablaAlbumes = new JTable(datosAlbumes);
		tablaAlbumes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tablaAlbumes.setPreferredScrollableViewportSize(new Dimension(500, 80));
		JScrollPane scrollTablaAlbumes = new JScrollPane(tablaAlbumes);
		
		/* Subir cancion */
		JButton subirCancion = new JButton("Subir cancion");
		FormularioCancion formularioCancion = new FormularioCancion();
		
		

		/* Colocamos los elementos */
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollTablaCanciones, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollTablaAlbumes, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, formularioCancion, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, albumes, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.WEST, canciones, 0, SpringLayout.WEST, scrollTablaCanciones);
		layout.putConstraint(SpringLayout.EAST, subirCancion, 0, SpringLayout.EAST, scrollTablaCanciones);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, scrollTablaCanciones, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, scrollTablaAlbumes, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, formularioCancion, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, canciones, -50, SpringLayout.NORTH, scrollTablaCanciones);
		layout.putConstraint(SpringLayout.SOUTH, albumes, -50, SpringLayout.NORTH, scrollTablaCanciones);
		layout.putConstraint(SpringLayout.SOUTH, subirCancion, -50, SpringLayout.NORTH, scrollTablaCanciones);
		
		this.add(canciones);
		this.add(scrollTablaCanciones);
		this.add(albumes);
		this.add(scrollTablaAlbumes);
		scrollTablaAlbumes.setVisible(false);
		this.add(subirCancion);
		this.add(formularioCancion);
		formularioCancion.setVisible(false);;
		
		/* Configuramos el boton "albumes" */
		albumes.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						scrollTablaCanciones.setVisible(false);
						formularioCancion.setVisible(false);
						scrollTablaAlbumes.setVisible(true);
					}
				});
		
		/* Configuramos el boton "canciones" */
		canciones.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						scrollTablaCanciones.setVisible(true);
						formularioCancion.setVisible(false);
						scrollTablaAlbumes.setVisible(false);
					}
				});
		
		/* Configuramos el boton "subirCancion" */
		subirCancion.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						scrollTablaCanciones.setVisible(false);
						formularioCancion.setVisible(true);
						scrollTablaAlbumes.setVisible(false);
					}
				});
	}

}
