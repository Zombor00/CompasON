package GUI.AccesoComun;

import java.awt.*;
import javax.swing.*;

public class Sesion extends JPanel {

	public Sesion() {
		super();
		FlowLayout layout = new FlowLayout();
		this.setLayout(layout);
		JLabel mensaje = new JLabel("Nombre");

		this.add(mensaje);
	}
}
