package GUI.UsuarioRegistrado;

import javax.swing.*;
import GUI.*;


public class PestaniasUsuarioRegistrado extends JTabbedPane {
	
	public PestaniasUsuarioRegistrado() {
		super();
		JPanel inicio = new InicioRegistrado();
		JPanel busqueda = new Busqueda();
		
		this.addTab("Inicio", inicio);
		this.addTab("Busqueda", busqueda);
		
		this.setSelectedIndex(0);
		
	}

}
