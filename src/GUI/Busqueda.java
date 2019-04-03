package GUI;

import java.awt.FlowLayout;

import javax.swing.*;

public class Busqueda extends JPanel {

	public Busqueda() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		JTextField busqueda = new JTextField(20);
		JButton buscar   = new JButton("Buscar");
		
		JPanel buscador = new JPanel();
		buscador.setLayout(new FlowLayout());
		buscador.add(busqueda);
		buscador.add(buscar);
		

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, buscador, 0, SpringLayout.HORIZONTAL_CENTER, this);

		layout.putConstraint(SpringLayout.NORTH, buscador, 25, SpringLayout.NORTH, this);

		
		this.add(buscador);
	}
}
