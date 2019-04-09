package GUI;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Notificaciones extends JPanel {
	
	public Notificaciones() {
		super();
		FlowLayout layout = new FlowLayout();
		this.setLayout(layout);
		JLabel mensaje = new JLabel("Notificaciones");

		this.add(mensaje);
	}

}
