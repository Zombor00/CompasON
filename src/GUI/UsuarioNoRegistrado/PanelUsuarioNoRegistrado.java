package GUI.UsuarioNoRegistrado;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class PanelUsuarioNoRegistrado extends JPanel {
	
	public PanelUsuarioNoRegistrado() {
		super();
		BorderLayout layout = new BorderLayout();		
		this.setLayout(layout);
		
		this.add(new PestaniasUsuarioNoRegistrado(),BorderLayout.CENTER);
		
	}
	

}
