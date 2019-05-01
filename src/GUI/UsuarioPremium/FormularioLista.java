package GUI.UsuarioPremium;

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

import GUI.GuiAplicacion;
import GUI.AccesoComun.JCheckBoxList;
import GUI.AccesoComun.JCheckBoxScrollableList;

public class FormularioLista extends JPanel{

	public FormularioLista() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		JTextField nombre = new JTextField(30);
		JTextField elementos = new JTextField(21);
		JButton seleccionar = new JButton("Seleccionar");
		seleccionar.setPreferredSize(new Dimension(105, 18));
		JLabel nombreLabel = new JLabel("Nombre de la lista");
		JLabel elementosLabel = new JLabel("Elementos:");
		JButton aceptar   = new JButton("Aceptar");

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nombre, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, nombre, 0, SpringLayout.VERTICAL_CENTER, this);
		
		layout.putConstraint(SpringLayout.WEST, elementos, 0, SpringLayout.WEST, nombre);
		layout.putConstraint(SpringLayout.NORTH, elementos, 5, SpringLayout.SOUTH, nombre);
		
		layout.putConstraint(SpringLayout.EAST, seleccionar, 0, SpringLayout.EAST, nombre);
		layout.putConstraint(SpringLayout.NORTH, seleccionar, 5, SpringLayout.SOUTH, nombre);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, nombreLabel,0,SpringLayout.VERTICAL_CENTER, nombre);
		layout.putConstraint(SpringLayout.EAST, nombreLabel,-75,SpringLayout.WEST, nombre);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, elementosLabel,0,SpringLayout.VERTICAL_CENTER, elementos);
		layout.putConstraint(SpringLayout.WEST, elementosLabel,0,SpringLayout.WEST, nombreLabel);

		layout.putConstraint(SpringLayout.NORTH, aceptar,5,SpringLayout.SOUTH, elementos);
		layout.putConstraint(SpringLayout.EAST, aceptar,0,SpringLayout.EAST, nombre);
		
		

		this.add(nombre);
		this.add(nombreLabel);
		this.add(elementos);
		this.add(seleccionar);
		this.add(elementosLabel);
		this.add(aceptar);
		this.setPreferredSize(new Dimension(1000,300));
		
		/*Configuramos el boton "Buscar" */
		seleccionar.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						System.out.println(GuiAplicacion.getInstance().getPanelesUsuarios().getPanelUsuarioPremium().getPestanias().
						getMisCanciones().getNombreAlbumes());
						
						ArrayList<String> nombreReproducibles = new ArrayList<String>();
						nombreReproducibles.addAll(GuiAplicacion.getInstance().getPanelesUsuarios().getPanelUsuarioPremium().getPestanias().
								getMisCanciones().getNombreAlbumes());
						nombreReproducibles.addAll(GuiAplicacion.getInstance().getPanelesUsuarios().getPanelUsuarioPremium().getPestanias().
								getMisCanciones().getNOmbreCanciones());
						nombreReproducibles.addAll(GuiAplicacion.getInstance().getPanelesUsuarios().getPanelUsuarioPremium().getPestanias().
								getMisListas().getNombreListas());
						JCheckBoxList checkBoxList = new JCheckBoxList(nombreReproducibles);
						JCheckBoxScrollableList checkBoxScrollableList = new JCheckBoxScrollableList(checkBoxList);
						checkBoxScrollableList.setPreferredSize(new Dimension(500,250));
						JOptionPane.showMessageDialog(null, checkBoxScrollableList, "Seleccione los elementos para la nueva lista", JOptionPane.PLAIN_MESSAGE);
						
						elementos.setText(checkBoxScrollableList.getSelectedIndices().toString());
					}
				}
		);
		
	}
	
}
