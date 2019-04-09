package GUI.UsuarioRegistrado;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class InicioRegistrado extends JPanel {

	public InicioRegistrado() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon(new ImageIcon("aux/logo-compason.png").getImage().getScaledInstance(200, 75, Image.SCALE_DEFAULT)));
		
		this.add(logo);
	}
}
