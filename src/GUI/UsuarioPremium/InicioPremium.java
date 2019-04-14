package GUI.UsuarioPremium;

import javax.swing.JLabel;
import javax.swing.SpringLayout;

import GUI.AccesoComun.InicioComun;

public class InicioPremium extends InicioComun {

	public InicioPremium() {
		super();
		JLabel suscripcion = new JLabel(" _ dias para que expire la suscripcion a Premium");

		SpringLayout layout = (SpringLayout) this.getLayout();
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, suscripcion, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, suscripcion, 200, SpringLayout.VERTICAL_CENTER, this);
		
		this.add(suscripcion);
	}

}
