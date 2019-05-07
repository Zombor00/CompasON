package GUI.UsuarioPremium;

import javax.swing.JLabel;
import javax.swing.SpringLayout;

import GUI.AccesoComun.InicioComun;
import aplicacion.Aplicacion;

/**
 * Esta clase tiene toda la informacion relevante a la pestania del inicio
 * de premium
 * 
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class InicioPremium extends InicioComun {
	
	/**
	 * Label que muestra la fecha en la que acaba la suscripcion premium
	 */
	private JLabel suscripcion;

	public InicioPremium() {
		super();
		suscripcion = new JLabel("El dia _ expira la suscripcion a Premium");

		SpringLayout layout = (SpringLayout) this.getLayout();
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, suscripcion, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, suscripcion, 200, SpringLayout.VERTICAL_CENTER, this);
		
		this.add(suscripcion);
	}
	
	/**
	 * Actualiza la fecha en la que acaba la suscripcion premium
	 */
	public void actualizarDatos() {
		super.actualizarDatos();
		suscripcion.setText( "El dia " + Aplicacion.getInstance().getUsuarioLogeado().getPremiumHasta() +  " expira la suscripcion a Premium");
	}

}
