package GUI.UsuarioRegistrado;

import javax.swing.JButton;
import javax.swing.SpringLayout;
import GUI.AccesoComun.InicioComun;

public class InicioRegistrado extends InicioComun {

	public InicioRegistrado() {
		super();
		JButton hacersePremium = new JButton("Hacerse premium");

		SpringLayout layout = (SpringLayout) this.getLayout();
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, hacersePremium, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, hacersePremium, 200, SpringLayout.VERTICAL_CENTER, this);
		
		this.add(hacersePremium);
	}
}
