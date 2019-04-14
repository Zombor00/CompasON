package GUI;

import java.awt.*;

import javax.swing.*;

import GUI.UsuarioPremium.PanelUsuarioPremium;
import GUI.UsuarioRegistrado.PanelUsuarioRegistrado;
import GUI.UsuarioSinCuenta.PanelUsuarioSinCuenta;

public class PanelesUsuarios extends JPanel {
	
	final static String NOMBRE_SIN_CUENTA = "Sin cuenta";
	final static String NOMBRE_REGISTRADO = "Registrado";
	final static String NOMBRE_PREMIUM = "Premium";
	
	public PanelesUsuarios() {
		super();
		CardLayout layout = new CardLayout();		
		this.setLayout(layout);
		
		PanelUsuarioSinCuenta panelUsuarioSinCuenta = new PanelUsuarioSinCuenta();
		PanelUsuarioRegistrado panelUsuarioRegistrado = new PanelUsuarioRegistrado();
		PanelUsuarioPremium panelUsuarioPremium = new PanelUsuarioPremium();
		
		this.add(panelUsuarioSinCuenta, NOMBRE_SIN_CUENTA);
		this.add(panelUsuarioRegistrado, NOMBRE_REGISTRADO);
		this.add(panelUsuarioPremium, NOMBRE_PREMIUM);
		
		layout.show(this, NOMBRE_PREMIUM);
		
		
		
	}
}
