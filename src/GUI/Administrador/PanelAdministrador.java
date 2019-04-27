package GUI.Administrador;

import java.awt.BorderLayout;

import javax.swing.JPanel;


public class PanelAdministrador extends JPanel {

	public PanelAdministrador() {
		super();
		BorderLayout layout = new BorderLayout();		
		this.setLayout(layout);
		
		this.add(new PestaniasAdministrador(),BorderLayout.CENTER);
		this.add(new InformacionAdministrador(),BorderLayout.EAST);
		
	}
}
