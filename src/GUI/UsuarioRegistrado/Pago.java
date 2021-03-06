package GUI.UsuarioRegistrado;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

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
public class Pago extends JPanel {
	/**
	 * TextField para meter el numero de cuenta
	 */
	private JTextField numero;
	
	/**
	 * Boton para realizar el pago
	 */
	private JButton pagar;
	
	/**
	 * Boton para no realizar el pago
	 */
	private JButton noPagar;
	
	/**
	 * Muestra el precio del servicio premium
	 */
	private JLabel informacionPago = new JLabel("Precio del servicio Premium: "+ Aplicacion.getInstance().getPrecioPremium()+ "€");
	
	public Pago() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		this.numero = new JTextField(20);
		JLabel numeroLabel = new JLabel("Numero de tarjeta (sin espacios):");
		this.pagar = new JButton("Pagar Premium");
		this.noPagar = new JButton("Cancelar");
		
		/*Colocamos los elementos*/
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, numeroLabel, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.WEST, informacionPago, 0, SpringLayout.WEST, numeroLabel);
		layout.putConstraint(SpringLayout.NORTH, informacionPago, 5, SpringLayout.SOUTH, numeroLabel);
		layout.putConstraint(SpringLayout.WEST, numero, 50, SpringLayout.EAST, numeroLabel);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, numero, 0, SpringLayout.VERTICAL_CENTER, numeroLabel);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, numero, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, pagar, 0, SpringLayout.VERTICAL_CENTER, numero);
		layout.putConstraint(SpringLayout.WEST, pagar, 50, SpringLayout.EAST, numero);
		layout.putConstraint(SpringLayout.NORTH, noPagar, 5, SpringLayout.SOUTH, pagar);
		layout.putConstraint(SpringLayout.EAST, noPagar, 0, SpringLayout.EAST, pagar);
		
		this.add(numero);
		this.add(pagar);
		this.add(noPagar);
		this.add(numeroLabel);
		this.add(informacionPago);
		this.setPreferredSize(new Dimension(800,150));
		
	}
	
	public void setControlador(ActionListener controlador) {
		pagar.setActionCommand("PAGAR");
		pagar.addActionListener(controlador);
		noPagar.setActionCommand("NO_PAGAR");
		noPagar.addActionListener(controlador);
	}

	public String getNumero() {
		return numero.getText();
	}

	public JLabel getInformacionPago() {
		return informacionPago;
	}
	
	/**
     * Limpia el texto de pago
     */
	public void pagoLimpiar() {
		numero.setText("");
	}
}
