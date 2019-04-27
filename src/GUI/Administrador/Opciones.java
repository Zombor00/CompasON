package GUI.Administrador;


import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpringLayout;

public class Opciones extends JPanel {
	
	public Opciones() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		JLabel titulo = new JLabel("Opciones");
		titulo.setFont(new Font(titulo.getFont().getFontName(), titulo.getFont().getStyle(), 30));
		JLabel precioMensaje = new JLabel("Precio");
		JLabel repMensaje = new JLabel("<html><body>Reproducciones<br>para ser premium</body></html>");
		JLabel limiteMensaje = new JLabel("<html><body>Limite de<br>reproducciones</body></html>");
		JSpinner precio = new JSpinner();
		JSpinner reproducciones = new JSpinner();
		JSpinner limite = new JSpinner();
		
		precio.setValue(10);
		reproducciones.setValue(10);
		limite.setValue(10);
		
		layout.putConstraint(SpringLayout.NORTH, titulo, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, precioMensaje, 15, SpringLayout.SOUTH, titulo);
		layout.putConstraint(SpringLayout.NORTH, precio, 5, SpringLayout.SOUTH, precioMensaje);
		layout.putConstraint(SpringLayout.NORTH, repMensaje, 15, SpringLayout.SOUTH, precio);
		layout.putConstraint(SpringLayout.NORTH, reproducciones, 5, SpringLayout.SOUTH, repMensaje);
		layout.putConstraint(SpringLayout.NORTH, limiteMensaje, 15, SpringLayout.SOUTH, reproducciones);
		layout.putConstraint(SpringLayout.NORTH, limite, 5, SpringLayout.SOUTH, limiteMensaje);
		
		layout.putConstraint(SpringLayout.WEST, titulo, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, precioMensaje, 15, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, repMensaje, 15, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, limiteMensaje, 15, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, precio, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, reproducciones, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, limite, 25, SpringLayout.WEST, this);

		this.add(titulo);
		this.add(precioMensaje);
		this.add(repMensaje);
		this.add(limiteMensaje);
		this.add(precio);
		this.add(reproducciones);
		this.add(limite);
	}

}
