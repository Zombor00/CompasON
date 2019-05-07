package GUI.AccesoComun;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class FormularioCancion extends JPanel{
	
	private JTextField nombre;
	private JTextField fichero;
	
	public FormularioCancion() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		nombre = new JTextField(30);
		fichero = new JTextField(21);
		JButton buscar = new JButton("Buscar");
		buscar.setPreferredSize(new Dimension(105, buscar.getPreferredSize().height));
		JLabel nombreLabel = new JLabel("Nombre de la cancion:");
		JLabel ficheroLabel = new JLabel("Fichero de audio:");

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nombre, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, nombre, 0, SpringLayout.VERTICAL_CENTER, this);
		
		layout.putConstraint(SpringLayout.WEST, fichero, 0, SpringLayout.WEST, nombre);
		layout.putConstraint(SpringLayout.NORTH, fichero, 5, SpringLayout.SOUTH, nombre);
		
		layout.putConstraint(SpringLayout.EAST, buscar, 0, SpringLayout.EAST, nombre);
		layout.putConstraint(SpringLayout.NORTH, buscar, 5, SpringLayout.SOUTH, nombre);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, nombreLabel,0,SpringLayout.VERTICAL_CENTER, nombre);
		layout.putConstraint(SpringLayout.EAST, nombreLabel,-75,SpringLayout.WEST, nombre);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, ficheroLabel,0,SpringLayout.VERTICAL_CENTER, fichero);
		layout.putConstraint(SpringLayout.WEST, ficheroLabel,0,SpringLayout.WEST, nombreLabel);
		
		

		this.add(nombre);
		this.add(nombreLabel);
		this.add(fichero);
		this.add(buscar);
		this.add(ficheroLabel);
		this.setPreferredSize(new Dimension(1000,300));
		
		
		
		
		
		
		/*Configuramos el boton "Buscar" */
		buscar.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JFileChooser fileChooser = new JFileChooser();
						//buscador.setCurrentDirectory(new File(System.getProperty("user.home")));
						int result = fileChooser.showOpenDialog(buscar.getParent());
						if (result == JFileChooser.APPROVE_OPTION) {
						    File selectedFile = fileChooser.getSelectedFile();
						    fichero.setText(selectedFile.toString());
						}
					}
				}
		);
	}
	
	public String getNombre() {
		return this.nombre.getText();
	}
	
	public void setNombre(String nombre) {
		this.nombre.setText(nombre);
	}
	
	public String getFichero() {
		return this.fichero.getText();
	}
	
	public void setFichero(String fichero) {
		this.fichero.setText(fichero);
	}
	
	public void actualizarDatos() {
		nombre.setText("");
		fichero.setText("");
	}

}
