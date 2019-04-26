package GUI;

import java.awt.*;

import javax.swing.*;

import GUI.Administrador.PanelAdministrador;
import GUI.UsuarioPremium.PanelUsuarioPremium;
import GUI.UsuarioRegistrado.PanelUsuarioRegistrado;
import GUI.UsuarioSinCuenta.PanelUsuarioSinCuenta;

public class PanelesUsuarios extends JPanel {
	
	public final static String SIN_CUENTA = "Sin cuenta";
	public final static String REGISTRADO = "Registrado";
	public final static String PREMIUM = "Premium";
	public final static String ADMINISTRADOR = "Admin";
	
	public PanelesUsuarios() {
		super();
		CardLayout layout = new CardLayout();		
		this.setLayout(layout);
		
		PanelUsuarioSinCuenta panelUsuarioSinCuenta = new PanelUsuarioSinCuenta();
		PanelUsuarioRegistrado panelUsuarioRegistrado = new PanelUsuarioRegistrado();
		PanelUsuarioPremium panelUsuarioPremium = new PanelUsuarioPremium();
		PanelAdministrador panelAdministrador = new PanelAdministrador();
		
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
	}
}
