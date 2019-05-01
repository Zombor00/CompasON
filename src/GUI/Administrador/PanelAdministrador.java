package GUI.Administrador;

import java.awt.BorderLayout;

import javax.swing.JPanel;


public class PanelAdministrador extends JPanel {
	private PestaniasAdministrador pestaniasAdministrador;
	private InformacionAdministrador informacionAdministrador;
	
	public PanelAdministrador() {
		super();
		pestaniasAdministrador = new PestaniasAdministrador();
		informacionAdministrador = new InformacionAdministrador();
		
		BorderLayout layout = new BorderLayout();		
		this.setLayout(layout);
		
		this.add(pestaniasAdministrador,BorderLayout.CENTER);
		this.add(informacionAdministrador,BorderLayout.EAST);
	}
	
	public InformacionAdministrador getInformacionAdministrador() {
		return this.informacionAdministrador;
	}
	
	public PestaniasAdministrador getPestaniasAdministrador() {
		return this.pestaniasAdministrador;
	}

	public void actualizarDatos() {
		this.pestaniasAdministrador.actualizarDatos();	
		this.informacionAdministrador.actualizarDatos();
	}
}
