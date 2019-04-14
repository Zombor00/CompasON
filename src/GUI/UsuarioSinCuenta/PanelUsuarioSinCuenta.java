package GUI.UsuarioSinCuenta;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class PanelUsuarioSinCuenta extends JPanel {
	
	public PanelUsuarioSinCuenta() {
		super();
		BorderLayout layout = new BorderLayout();		
		this.setLayout(layout);
		
		this.add(new PestaniasUsuarioSinCuenta(),BorderLayout.CENTER);
		
	}
	

}
