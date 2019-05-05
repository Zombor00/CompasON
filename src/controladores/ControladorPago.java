package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import GUI.GuiAplicacion;
import GUI.PanelesUsuarios;
import GUI.UsuarioRegistrado.Pago;
import aplicacion.Aplicacion;
import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;

public class ControladorPago implements ActionListener {

	private Aplicacion aplicacion;
	private Pago vista;
	private GuiAplicacion gui;

	public ControladorPago(Pago pago) {
		this.vista = pago;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (aplicacion == null)
			aplicacion = Aplicacion.getInstance();
		if (gui == null)
			gui = GuiAplicacion.getInstance();

		if (e.getActionCommand().equals("PAGAR")) {
			try {
				aplicacion.pagarPremium(vista.getNumero(), "Pago de servicio premium CompasON");
			} catch (InvalidCardNumberException e1) {
				GuiAplicacion.showMessage("Numero de tarjeta no valido");
			} catch (FailedInternetConnectionException e1) {
				GuiAplicacion.showMessage("Fallo en la conexion");
			} catch (OrderRejectedException e1) {
				GuiAplicacion.showMessage("Operacion rechazada");
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				e1.printStackTrace();
			}
		}
		
		if (aplicacion.getUsuarioLogeado() != null) {
			gui.actualizarDatos();
			PanelesUsuarios panelesUsuarios = gui.getPanelesUsuarios();
			if(aplicacion.getUsuarioLogeado().esPremium()) {
				panelesUsuarios.cambiarPanel(PanelesUsuarios.PREMIUM);
			}
		}

	}

}
