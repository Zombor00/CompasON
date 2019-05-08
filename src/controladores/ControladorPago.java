package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GuiAplicacion;
import GUI.PanelesUsuarios;
import GUI.UsuarioRegistrado.InicioRegistrado;
import GUI.UsuarioRegistrado.Pago;
import aplicacion.Aplicacion;
import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;

/**
 * Esta clase tiene toda la informacion relevante al controlador
 * de pago: todas las acciones que puede haber en ese panel
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class ControladorPago implements ActionListener {
	
	/**
	 * Aplicacion con la informacion
	 */
	private Aplicacion aplicacion;
	/**
	 * Panel de pago donde suceden los eventos
	 */
	private Pago vista;
	/**
	 * Interfaz grafica de la aplicacion
	 */
	private GuiAplicacion gui;
	
	/**
	 * Constructor del controlador con pago
	 * @param pago panel donde actua el controlador
	 */
	public ControladorPago(Pago pago) {
		this.vista = pago;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (aplicacion == null)
			aplicacion = Aplicacion.getInstance();
		if (gui == null)
			gui = GuiAplicacion.getInstance();
		
		/* Se realiza o cancela el pago */
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
			
			if (aplicacion.getUsuarioLogeado() != null) {
				gui.actualizarDatos();
				PanelesUsuarios panelesUsuarios = gui.getPanelesUsuarios();
				if(aplicacion.getUsuarioLogeado().esPremium()) {
					panelesUsuarios.cambiarPanel(PanelesUsuarios.PREMIUM);
				}
			}
		
		}else if(e.getActionCommand().equals("NO_PAGAR")) {
			InicioRegistrado iR = gui.getPanelesUsuarios().getPanelUsuarioRegstrado().getPestanias().getInicio();
			iR.noPagar();
		}
	}

}
