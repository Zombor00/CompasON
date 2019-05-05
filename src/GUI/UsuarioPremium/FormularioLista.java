package GUI.UsuarioPremium;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import GUI.GuiAplicacion;
import GUI.AccesoComun.JCheckBoxScrollableListSelect;

public class FormularioLista extends JPanel{
	
	private JTextField nombre;
	private ArrayList<Integer> reproduciblesSeleccionados = new ArrayList<Integer>();
	private JTextField reproducibles;

	public FormularioLista() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		nombre = new JTextField(30);
		reproducibles = new JTextField(21);
		JButton seleccionar = new JButton("Seleccionar");
		seleccionar.setPreferredSize(new Dimension(105, seleccionar.getPreferredSize().height));
		JLabel nombreLabel = new JLabel("Nombre de la lista");
		JLabel reproduciblesLabel = new JLabel("Elementos:");

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nombre, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, nombre, 0, SpringLayout.VERTICAL_CENTER, this);
		
		layout.putConstraint(SpringLayout.WEST, reproducibles, 0, SpringLayout.WEST, nombre);
		layout.putConstraint(SpringLayout.NORTH, reproducibles, 5, SpringLayout.SOUTH, nombre);
		
		layout.putConstraint(SpringLayout.EAST, seleccionar, 0, SpringLayout.EAST, nombre);
		layout.putConstraint(SpringLayout.NORTH, seleccionar, 5, SpringLayout.SOUTH, nombre);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, nombreLabel,0,SpringLayout.VERTICAL_CENTER, nombre);
		layout.putConstraint(SpringLayout.EAST, nombreLabel,-75,SpringLayout.WEST, nombre);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, reproduciblesLabel,0,SpringLayout.VERTICAL_CENTER, reproducibles);
		layout.putConstraint(SpringLayout.WEST, reproduciblesLabel,0,SpringLayout.WEST, nombreLabel);
		
		

		this.add(nombre);
		this.add(nombreLabel);
		this.add(reproducibles);
		this.add(seleccionar);
		this.add(reproduciblesLabel);
		this.setPreferredSize(new Dimension(1000,300));
		
		/*Configuramos el boton "Buscar" */
		seleccionar.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						ArrayList<String> nombreReproducibles = new ArrayList<String>();
						nombreReproducibles.addAll(GuiAplicacion.getInstance().getPanelesUsuarios().getPanelUsuarioPremium().getPestanias().
								getMisCanciones().getNombreCanciones());
						nombreReproducibles.addAll(GuiAplicacion.getInstance().getPanelesUsuarios().getPanelUsuarioPremium().getPestanias().
								getMisCanciones().getNombreAlbumes());
						nombreReproducibles.addAll(GuiAplicacion.getInstance().getPanelesUsuarios().getPanelUsuarioPremium().getPestanias().
								getMisListas().getNombreListas());
						
						@SuppressWarnings("unused")
						JCheckBoxScrollableListSelect checkBoxScrollableListSelect = new JCheckBoxScrollableListSelect(
								"Seleccione las canciones para el nuevo album",
								nombreReproducibles,
								reproducibles,
								reproduciblesSeleccionados,
								null);
					}
				}
		);
		
	}
	
	public ArrayList<Integer> getReproduciblesSeleccionados(){
		return this.reproduciblesSeleccionados;
	}
	
	public String getNombre() {
		return this.nombre.getText();
	}
	
	public void actualizarDatos() {
		nombre.setText("");
		reproduciblesSeleccionados.clear();
		reproducibles.setText("");
	}
	
}
