package GUI.Administrador;


import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;

import aplicacion.Aplicacion;

public class Opciones extends JPanel {
	
	private JSpinner precio;
	private JSpinner reproducciones;
	private JSpinner limite;
	private JButton actualizar;
	
	public Opciones() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		JLabel titulo = new JLabel("Opciones");
		titulo.setFont(new Font(titulo.getFont().getFontName(), titulo.getFont().getStyle(), 30));
		JLabel precioMensaje = new JLabel("Precio");
		JLabel repMensaje = new JLabel("Reproducciones para ser premium:");
		JLabel limiteMensaje = new JLabel("Limite de reproducciones:");
		precio = new JSpinner(new SpinnerNumberModel(Aplicacion.getInstance().getPrecioPremium(), 0, 9999, 1));
		reproducciones = new JSpinner(new SpinnerNumberModel(Aplicacion.getInstance().getReproduccionesPremium(), 0, 9999, 1));
		limite = new JSpinner(new SpinnerNumberModel(Aplicacion.getInstance().getLimiteReproducciones(), 0, 9999, 1));
		actualizar = new JButton("Actualizar");
		
		
		((JSpinner.DefaultEditor)precio.getEditor()).getTextField().setColumns(3);
		((JSpinner.DefaultEditor)reproducciones.getEditor()).getTextField().setColumns(3);
		((JSpinner.DefaultEditor)limite.getEditor()).getTextField().setColumns(3);
		
		layout.putConstraint(SpringLayout.NORTH, titulo, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, precioMensaje, 15, SpringLayout.SOUTH, titulo);
		layout.putConstraint(SpringLayout.NORTH, precio, 5, SpringLayout.SOUTH, precioMensaje);
		layout.putConstraint(SpringLayout.NORTH, repMensaje, 15, SpringLayout.SOUTH, precio);
		layout.putConstraint(SpringLayout.NORTH, reproducciones, 5, SpringLayout.SOUTH, repMensaje);
		layout.putConstraint(SpringLayout.NORTH, limiteMensaje, 15, SpringLayout.SOUTH, reproducciones);
		layout.putConstraint(SpringLayout.NORTH, limite, 5, SpringLayout.SOUTH, limiteMensaje);
		layout.putConstraint(SpringLayout.NORTH, actualizar, 15, SpringLayout.SOUTH, limite);
		
		layout.putConstraint(SpringLayout.WEST, titulo, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, precioMensaje, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, repMensaje, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, limiteMensaje, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, precio, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, reproducciones, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, limite, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, actualizar, 25, SpringLayout.WEST, this);

		this.add(titulo);
		this.add(precioMensaje);
		this.add(repMensaje);
		this.add(limiteMensaje);
		this.add(precio);
		this.add(reproducciones);
		this.add(limite);
		this.add(actualizar);
	}
	
	

	public JSpinner getPrecio() {
		return precio;
	}



	public JSpinner getReproducciones() {
		return reproducciones;
	}



	public JSpinner getLimite() {
		return limite;
	}


	public void actualizarDatos() {
		precio.setValue(Aplicacion.getInstance().getPrecioPremium());
		reproducciones.setValue(Aplicacion.getInstance().getReproduccionesPremium());
		limite.setValue(Aplicacion.getInstance().getLimiteReproducciones());
	}
	
	public void setControlador(ActionListener controlador) {
		actualizar.setActionCommand("ACTUALIZAR");
		actualizar.addActionListener(controlador);
	}
	

}
