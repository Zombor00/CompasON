package GUI.Administrador;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * Esta clase tiene toda la informacion relevante a la pantalla que se
 * muestra al administrador
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class PanelAdministrador extends JPanel {
	
	
	/**
	 * Pestanias del administrador
	 */
	private PestaniasAdministrador pestaniasAdministrador;
	
	/**
	 * Informacion del administrador
	 */
	private InformacionAdministrador informacionAdministrador;
	
	/**
     * Constructor de la clase que crea el panel del administrador
     * con la parte de las pestanias y de la informacion
     * 
     */
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

	/**
     * Metodo que actualiza la informacion del panel del administrador
     * 
     */
	public void actualizarDatos() {
		this.pestaniasAdministrador.actualizarDatos();	
		this.informacionAdministrador.actualizarDatos();
	}
}
