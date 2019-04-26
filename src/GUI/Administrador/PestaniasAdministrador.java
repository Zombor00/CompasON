package GUI.Administrador;

import javax.swing.*;

import GUI.AccesoComun.Busqueda;


public class PestaniasAdministrador extends JTabbedPane {

	public PestaniasAdministrador() {
		super();
		JPanel inicio = new InicioAdministrador();
		JPanel busqueda = new Busqueda();
		JPanel validar = new Tramitar();
		JPanel denuncias = new Tramitar();
		
		this.addTab("Inicio", inicio);
		this.addTab("Busqueda", busqueda);
		this.addTab("Validar", validar);
		this.addTab("Denuncias", denuncias);
		
		this.setSelectedIndex(0);
		
	}
}
