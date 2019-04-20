package GUI;

import java.awt.*;

import javax.swing.*;

import GUI.UsuarioPremium.PanelUsuarioPremium;
import GUI.UsuarioRegistrado.PanelUsuarioRegistrado;
import GUI.UsuarioSinCuenta.PanelUsuarioSinCuenta;

public class PanelesUsuarios extends JPanel {
	
	public final static String SIN_CUENTA = "Sin cuenta";
	public final static String REGISTRADO = "Registrado";
	public final static String PREMIUM = "Premium";
	
	public PanelesUsuarios() {
		super();
		CardLayout layout = new CardLayout();		
		this.setLayout(layout);
		
		PanelUsuarioSinCuenta panelUsuarioSinCuenta = new PanelUsuarioSinCuenta();
		PanelUsuarioRegistrado panelUsuarioRegistrado = new PanelUsuarioRegistrado();
		PanelUsuarioPremium panelUsuarioPremium = new PanelUsuarioPremium();
		
		this.add(panelUsuarioSinCuenta, SIN_CUENTA);
		this.add(panelUsuarioRegistrado, REGISTRADO);
		this.add(panelUsuarioPremium, PREMIUM);
		
		layout.show(this, SIN_CUENTA);
		
	}
	
	public void cambiarPanel(String name) {
		if (name != PanelesUsuarios.SIN_CUENTA && name != PanelesUsuarios.REGISTRADO && name != PanelesUsuarios.PREMIUM) {
			return;
		}
		CardLayout layout = (CardLayout) this.getLayout();
		layout.show(this, name);
	}
}
