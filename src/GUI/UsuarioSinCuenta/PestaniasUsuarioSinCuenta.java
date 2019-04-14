package GUI.UsuarioSinCuenta;

import javax.swing.*;

import GUI.AccesoComun.Busqueda;

public class PestaniasUsuarioSinCuenta extends JTabbedPane {
	
	public PestaniasUsuarioSinCuenta() {
		super();
		JPanel inicio = new InicioUsuarioSinCuenta();
		JPanel busqueda = new Busqueda();
		
		this.addTab("Inicio", inicio);
		this.addTab("Busqueda", busqueda);
		
		this.setSelectedIndex(0);
		
	}

}
