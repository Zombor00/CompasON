package GUI.UsuarioRegistrado;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import GUI.AccesoComun.Informacion;

/**
 * Esta clase tiene toda la informacion relevante a las pestanias
 * del administrador
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class PanelUsuarioRegistrado extends JPanel {
	
	/**
	 * Pestania del administrador
	 */
	private PestaniasUsuarioRegistrado pestaniasUsuarioRegistrado;
	
	/**
	 * Informacion del administrador
	 */
	private Informacion informacion;
	
	public PanelUsuarioRegistrado() {
		super();
		this.pestaniasUsuarioRegistrado = new PestaniasUsuarioRegistrado();
		this.informacion = new Informacion();
		
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
	
	/**
     * Metodo que actualiza la informacion del panel del usuario registrado
     */
	public void actualizarDatos() {
		this.pestaniasUsuarioRegistrado.actualizarDatos();
		this.informacion.actualizarDatos();
	}

}
