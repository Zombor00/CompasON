package GUI.UsuarioPremium;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import GUI.AccesoComun.Informacion;

public class PanelUsuarioPremium extends JPanel{
	
	public PanelUsuarioPremium() {
		super();
		BorderLayout layout = new BorderLayout();		
		this.setLayout(layout);
		
		this.add(new PestaniasUsuarioPremium(),BorderLayout.CENTER);
		this.add(new Informacion(),BorderLayout.EAST);
		
	}
	
	public void actualizarDatos() {
		/* TODO Implementar metodo */
	}

}
