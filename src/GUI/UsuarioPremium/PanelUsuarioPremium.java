package GUI.UsuarioPremium;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import GUI.AccesoComun.Informacion;

/**
 * Esta clase tiene toda la informacion relevante a la pantalla
 * que se le muestra al usuario premium
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class PanelUsuarioPremium extends JPanel{
	
	/**
	 * Pestanias del usuario premium
	 */
	public PestaniasUsuarioPremium pestaniasUsuarioPremium;
	
	/**
	 * Informacion del usuario premium
	 */
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
	
	/**
	 * Actualiza la informacion y las pestanias del usuario premium
	 */
	public void actualizarDatos() {
		informacion.actualizarDatos();
		pestaniasUsuarioPremium.actualizarDatos();
	}
	
	public Informacion getInformacion() {
		return this.informacion;
	}

	public PestaniasUsuarioPremium getPestanias() {
		return this.pestaniasUsuarioPremium;
	}

}
