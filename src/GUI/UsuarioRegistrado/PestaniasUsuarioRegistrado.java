package GUI.UsuarioRegistrado;

import javax.swing.*;
import GUI.AccesoComun.*;;


public class PestaniasUsuarioRegistrado extends JTabbedPane {
	
	public PestaniasUsuarioRegistrado() {
		super();
		JPanel inicio = new InicioRegistrado();
		JPanel busqueda = new Busqueda();
		JPanel misCanciones = new MisCanciones();
		
		this.addTab("Inicio", inicio);
		this.addTab("Busqueda", busqueda);
		this.addTab("Mis canciones", misCanciones);
		
		this.setSelectedIndex(0);
		
	}

}
