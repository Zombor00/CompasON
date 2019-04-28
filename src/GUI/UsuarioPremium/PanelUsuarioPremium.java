package GUI.UsuarioPremium;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import GUI.AccesoComun.Informacion;

public class PanelUsuarioPremium extends JPanel{
	
	public PestaniasUsuarioPremium pestaniasUsuarioPremium;
	public Informacion informacion;
	
	public PanelUsuarioPremium() {
		super();
		informacion = new Informacion();
		pestaniasUsuarioPremium = new PestaniasUsuarioPremium();
		
		BorderLayout layout = new BorderLayout();		
		this.setLayout(layout);
		
		this.add(pestaniasUsuarioPremium,BorderLayout.CENTER);
		this.add(informacion,BorderLayout.EAST);
		
	}
	
	public void actualizarDatos() {
		/* TODO Implementar metodo */
	}
	
	public Informacion getInformacion() {
		return this.informacion;
	}

	public PestaniasUsuarioPremium getPestaniasUsuarioPremium() {
		return this.pestaniasUsuarioPremium;
	}

}
