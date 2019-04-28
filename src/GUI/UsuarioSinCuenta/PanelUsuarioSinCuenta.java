package GUI.UsuarioSinCuenta;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class PanelUsuarioSinCuenta extends JPanel {
	
	private PestaniasUsuarioSinCuenta pestaniasUsuarioSinCuenta = new PestaniasUsuarioSinCuenta();
	
	public PanelUsuarioSinCuenta() {
		super();
		BorderLayout layout = new BorderLayout();		
		this.setLayout(layout);
		
		this.add(pestaniasUsuarioSinCuenta,BorderLayout.CENTER);
		
	}
	
	public PestaniasUsuarioSinCuenta getPestanias() {
		return this.pestaniasUsuarioSinCuenta;
	}
	

}
