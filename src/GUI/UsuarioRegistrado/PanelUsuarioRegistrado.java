package GUI.UsuarioRegistrado;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import GUI.AccesoComun.Informacion;

public class PanelUsuarioRegistrado extends JPanel {
	
	private PestaniasUsuarioRegistrado pestaniasUsuarioRegistrado = new PestaniasUsuarioRegistrado();
	private Informacion informacion = new Informacion();
	
	public PanelUsuarioRegistrado() {
		super();
		BorderLayout layout = new BorderLayout();		
		this.setLayout(layout);
		
		this.add(pestaniasUsuarioRegistrado,BorderLayout.CENTER);
		this.add(informacion,BorderLayout.EAST);
		
	}
	
	public PestaniasUsuarioRegistrado getPestanias() {
		return this.pestaniasUsuarioRegistrado;
	}
	
	public Informacion getInformacion() {
		return this.informacion;
	}
	
	public void actualizarDatos() {
		this.pestaniasUsuarioRegistrado.actualizarDatos();
		this.informacion.actualizarDatos();
	}

}
