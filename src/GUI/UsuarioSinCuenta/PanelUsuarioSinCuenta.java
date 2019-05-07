package GUI.UsuarioSinCuenta;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * Esta clase tiene toda la informacion relevante a la pantalla que se
 * muestra al usuario sin cuenta
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class PanelUsuarioSinCuenta extends JPanel {
	
	/**
	 * Pestanias del usuario sin cuenta
	 */
	private PestaniasUsuarioSinCuenta pestaniasUsuarioSinCuenta = new PestaniasUsuarioSinCuenta();
	
	/**
     * Constructor de la clase que crea el panel del usuario sin cuenta
     * con las pestanias
     * 
     */
	public PanelUsuarioSinCuenta() {
		super();
		BorderLayout layout = new BorderLayout();		
		this.setLayout(layout);
		
		this.add(pestaniasUsuarioSinCuenta,BorderLayout.CENTER);
		
	}
	
	public PestaniasUsuarioSinCuenta getPestanias() {
		return this.pestaniasUsuarioSinCuenta;
	}

	/**
     * Metodo que actualiza la informacion del panel del usuario sin cuenta
     * 
     */
	public void actualizarDatos() {
		pestaniasUsuarioSinCuenta.actualizarDatos();
	}

}
