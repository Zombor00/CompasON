package GUI.UsuarioRegistrado;

import javax.swing.JButton;
import javax.swing.SpringLayout;
import GUI.AccesoComun.InicioComun;
import aplicacion.Aplicacion;

/**
 * Esta clase tiene toda la informacion relevante a las pestanias
 * del administrador
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class InicioRegistrado extends InicioComun {
	
	/**
	 * Clase que nos permite realizar el pago
	 */
	private Pago pago;
	/**
	 * Boton para mostrar el formulario de pago
	 */
	private JButton hacersePremium;

	public InicioRegistrado() {
		super();
		
		hacersePremium = new JButton("Hacerse premium");
		pago = new Pago();
		pago.setVisible(false);
		
		/*Colocamos los elementos*/
		SpringLayout layout = (SpringLayout) this.getLayout();
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, hacersePremium, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, hacersePremium, 200, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, pago, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, pago, 200, SpringLayout.VERTICAL_CENTER, this);
		
		/*Listener para mostrar el formulario de pago*/
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
	
	/**
     * Metodo que actualiza la informacion del panel del inicio del usuario registrado
     */
	public void actualizarDatos() {
		super.actualizarDatos();
		pago.getInformacionPago().setText("Precio del servicio Premium: "+ Aplicacion.getInstance().getPrecioPremium()+ "€");
	}
	
	/**
     * Oculta y limpia el formulario de pago
     */
	public void noPagar() {
		pago.setVisible(false);
		hacersePremium.setVisible(true);
		pago.pagoLimpiar();
	}
	
	
}
