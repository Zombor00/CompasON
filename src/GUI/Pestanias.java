package GUI;

import java.awt.*;

import javax.swing.*;

public class Pestanias extends JTabbedPane {
	
	public Pestanias() {
		super();
		JPanel pestania1 = new JPanel();
		JPanel pestania2 = new InicioNoRegistrado();
		
		pestania1.setLayout(new FlowLayout());
		pestania1.add(new JLabel("Nombre"));
		pestania1.add(new JTextField(10));
		pestania1.add(new JButton("Aceptar"));
		pestania1.setPreferredSize(new Dimension(300, 100));
		
		this.addTab("Pestaña 1", pestania1);
		this.addTab("Pestaña 2", pestania2);
		
		this.setSelectedIndex(1);
		
	}

}
