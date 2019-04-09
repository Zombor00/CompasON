package GUI.UsuarioNoRegistrado;

import javax.swing.*;

import GUI.*;
import GUI.UsuarioNoRegistrado.Inicio.*;

public class PestaniasUsuarioNoRegistrado extends JTabbedPane {
	
	public PestaniasUsuarioNoRegistrado() {
		super();
		JPanel inicio = new InicioUsuarioNoRegistrado();
		JPanel busqueda = new Busqueda();
		
		this.addTab("Inicio", inicio);
		this.addTab("Busqueda", busqueda);
		
		this.setSelectedIndex(0);
		
	}

}
