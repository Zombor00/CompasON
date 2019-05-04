package GUI.UsuarioRegistrado;

import javax.swing.JButton;
import javax.swing.SpringLayout;
import GUI.AccesoComun.InicioComun;
import aplicacion.Aplicacion;

public class InicioRegistrado extends InicioComun {
	
	Pago pago;

	public InicioRegistrado() {
		super();
		JButton hacersePremium = new JButton("Hacerse premium");
		pago = new Pago();
		
		pago.setVisible(false);

		SpringLayout layout = (SpringLayout) this.getLayout();
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, hacersePremium, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, hacersePremium, 200, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, pago, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, pago, 200, SpringLayout.VERTICAL_CENTER, this);
		
		hacersePremium.addActionListener(e -> {
			pago.setVisible(true);
			hacersePremium.setVisible(false);
		});
		
		this.add(hacersePremium);
		this.add(pago);
	}

	public Pago getPago() {
		return pago;
	}
	
	public void actualizarDatos() {
		super.actualizarDatos();
		pago.getInformacionPago().setText("Precio del servicio Premium: "+ Aplicacion.getInstance().getPrecioPremium()+ "€");
	}
	
	
}
