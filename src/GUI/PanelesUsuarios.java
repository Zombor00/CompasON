package GUI;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import GUI.Administrador.PanelAdministrador;
import GUI.UsuarioPremium.PanelUsuarioPremium;
import GUI.UsuarioRegistrado.PanelUsuarioRegistrado;
import GUI.UsuarioSinCuenta.PanelUsuarioSinCuenta;
import aplicacion.Aplicacion;
import media.Buscable;
import usuarios.UsuarioRegistrado;

public class PanelesUsuarios extends JPanel {
	
	public final static String SIN_CUENTA = "Sin cuenta";
	public final static String REGISTRADO = "Registrado";
	public final static String PREMIUM = "Premium";
	public final static String ADMINISTRADOR = "Admin";
	
	private String actual = SIN_CUENTA;
	
	private PanelUsuarioSinCuenta panelUsuarioSinCuenta = new PanelUsuarioSinCuenta();
	private PanelUsuarioRegistrado panelUsuarioRegistrado = new PanelUsuarioRegistrado();
	private PanelUsuarioPremium panelUsuarioPremium = new PanelUsuarioPremium();
	private PanelAdministrador panelAdministrador = new PanelAdministrador();
	
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
	
	public void actualizarDatos() {
		/* TODO Ojo con el login del admin */
		UsuarioRegistrado u = Aplicacion.getInstance().getUsuarioLogeado();
		if(u == null) return;
		if(u.getPremiumHasta() == null) {
			this.panelUsuarioRegistrado.actualizarDatos();
		} else {
			this.panelUsuarioPremium.actualizarDatos();
		}
	}
	
	public String getActual(){
		return this.actual;
	}
}
