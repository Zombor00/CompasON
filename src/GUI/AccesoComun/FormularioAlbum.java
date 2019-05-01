package GUI.AccesoComun;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class FormularioAlbum extends JPanel {
	
	public FormularioAlbum(ArrayList<String> nombreCanciones) {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		JTextField nombre = new JTextField(30);
		JTextField canciones = new JTextField(21);
		JButton seleccionar = new JButton("Seleccionar");
		seleccionar.setPreferredSize(new Dimension(105, 18));
		JLabel nombreLabel = new JLabel("Nombre del album:");
		JLabel cancionesLabel = new JLabel("Canciones:");
		JButton aceptar   = new JButton("Aceptar");

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

		layout.putConstraint(SpringLayout.NORTH, aceptar,5,SpringLayout.SOUTH, canciones);
		layout.putConstraint(SpringLayout.EAST, aceptar,0,SpringLayout.EAST, nombre);
		
		

		this.add(nombre);
		this.add(nombreLabel);
		this.add(canciones);
		this.add(seleccionar);
		this.add(cancionesLabel);
		this.add(aceptar);
		this.setPreferredSize(new Dimension(1000,300));
		
		/*Configuramos el boton "Buscar" */
		seleccionar.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						JCheckBoxList checkBoxList = new JCheckBoxList(nombreCanciones);
						JCheckBoxScrollableList checkBoxScrollableList = new JCheckBoxScrollableList(checkBoxList);
						checkBoxScrollableList.setPreferredSize(new Dimension(500,250));
						JOptionPane.showMessageDialog(null, checkBoxScrollableList, "Seleccione las canciones para el nuevo album", JOptionPane.PLAIN_MESSAGE);
						
						canciones.setText(checkBoxScrollableList.getSelectedIndices().toString());
					}
				}
		);
		
	}

}
