package GUI;

import java.awt.*;

import javax.swing.*;

import GUI.Administrador.PanelAdministrador;
import GUI.UsuarioPremium.PanelUsuarioPremium;
import GUI.UsuarioRegistrado.PanelUsuarioRegistrado;
import GUI.UsuarioSinCuenta.PanelUsuarioSinCuenta;
import aplicacion.Aplicacion;
import usuarios.UsuarioRegistrado;

/**
 * Esta clase tiene toda la informacion relevante a los paneles de
 * los diferentes tipos de usuarios
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class PanelesUsuarios extends JPanel {
	
	/**
	 * String identificador del panel del usuario sin cuenta
	 */
	public final static String SIN_CUENTA = "Sin cuenta";
	/**
	 * String identificador del panel del usuario registrado
	 */
	public final static String REGISTRADO = "Registrado";
	/**
	 * String identificador del panel del usuario premium
	 */
	public final static String PREMIUM = "Premium";
	/**
	 * String identificador del panel del administrador
	 */
	public final static String ADMINISTRADOR = "Admin";
	
	/**
	 * String que indica el panel actual mostrado por pantalla
	 */
	private String actual = SIN_CUENTA;
	
	/**
	 * Panel que se muestra al usuario sin cuenta
	 */
	private PanelUsuarioSinCuenta panelUsuarioSinCuenta = new PanelUsuarioSinCuenta();
	/**
	 * Panel que se muestra al usuario registrado
	 */
	private PanelUsuarioRegistrado panelUsuarioRegistrado = new PanelUsuarioRegistrado();
	/**
	 * Panel que se muestra al usuario premium
	 */
	private PanelUsuarioPremium panelUsuarioPremium = new PanelUsuarioPremium();
	/**
	 * Panel que se muestra al administrador
	 */
	private PanelAdministrador panelAdministrador = new PanelAdministrador();
	
	/**
	 * Constructor que almacena los paneles junto a su identificador y muestra por
	 * defecto el del usuario sin cuenta
	 * 
	 */
	public PanelesUsuarios() {
		super();
		CardLayout layout = new CardLayout();		
		this.setLayout(layout);
		
		this.add(panelUsuarioSinCuenta, SIN_CUENTA);
		this.add(panelUsuarioRegistrado, REGISTRADO);
		this.add(panelUsuarioPremium, PREMIUM);
		this.add(panelAdministrador, ADMINISTRADOR);
		
		layout.show(this, SIN_CUENTA);
		
	}
	
	/**
     * Metodo que cambia el panel mostrado por pantalla
     * 
     * @param name identificador del panel a mostrar
     */
	public void cambiarPanel(String name) {
		if (name != PanelesUsuarios.SIN_CUENTA && name != PanelesUsuarios.REGISTRADO && name != PanelesUsuarios.PREMIUM
				&& name != PanelesUsuarios.ADMINISTRADOR) {
			return;
		}
		CardLayout layout = (CardLayout) this.getLayout();
		layout.show(this, name);
		this.actual = name;
	}
	
	public PanelUsuarioSinCuenta getPanelUsuarioSinCuenta() {
		return this.panelUsuarioSinCuenta;
	}
	
	public PanelUsuarioRegistrado getPanelUsuarioRegstrado() {
		return this.panelUsuarioRegistrado;
	}
	
	public PanelUsuarioPremium getPanelUsuarioPremium() {
		return this.panelUsuarioPremium;
	}
	
	public PanelAdministrador getPanelAdministrador() {
		return this.panelAdministrador;
	}
	
	/**
     * Metodo que actualiza los datos del panel mostrado en la aplicacion
     * 
     */
	public void actualizarDatos() {
		UsuarioRegistrado u = Aplicacion.getInstance().getUsuarioLogeado();
		if (Aplicacion.getInstance().getAdministradorLogeado()) {
			this.panelAdministrador.actualizarDatos();
		} else if(u == null) {
			panelUsuarioSinCuenta.actualizarDatos();
		} else if(u.getPremiumHasta() == null) {
			this.panelUsuarioRegistrado.actualizarDatos();
		} else {
			this.panelUsuarioPremium.actualizarDatos();
		}
	}
	
	public String getActual(){
		return this.actual;
	}
}
