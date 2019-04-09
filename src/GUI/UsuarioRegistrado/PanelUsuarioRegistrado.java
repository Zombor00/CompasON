package GUI.UsuarioRegistrado;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import GUI.Informacion;

public class PanelUsuarioRegistrado extends JPanel {
	
	public PanelUsuarioRegistrado() {
		super();
		BorderLayout layout = new BorderLayout();		
		this.setLayout(layout);
		
		this.add(new PestaniasUsuarioRegistrado(),BorderLayout.CENTER);
		this.add(new Informacion(),BorderLayout.EAST);
		
	}
	

}
