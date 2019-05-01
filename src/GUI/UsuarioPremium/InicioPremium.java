package GUI.UsuarioPremium;

import javax.swing.JLabel;
import javax.swing.SpringLayout;

import GUI.AccesoComun.InicioComun;
import aplicacion.Aplicacion;

public class InicioPremium extends InicioComun {
	
	private JLabel suscripcion;

	public InicioPremium() {
		super();
		suscripcion = new JLabel("El dia _ expira la suscripcion a Premium");

		SpringLayout layout = (SpringLayout) this.getLayout();
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, suscripcion, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, suscripcion, 200, SpringLayout.VERTICAL_CENTER, this);
		
		this.add(suscripcion);
	}
	
	public void actualizarDatos() {
		super.actualizarDatos();
		suscripcion.setText( "El dia " + Aplicacion.getInstance().getUsuarioLogeado().getPremiumHasta() +  " expira la suscripcion a Premium");
	}

}
