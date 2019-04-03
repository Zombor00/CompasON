package GUI;

import javax.swing.*;

public class Pestanias extends JTabbedPane {
	
	public Pestanias() {
		super();
		JPanel inicioNoRegistrado = new InicioNoRegistrado();
		JPanel busqueda = new Busqueda();
		
		this.addTab("Inicio", inicioNoRegistrado);
		this.addTab("Busqueda", busqueda);
		
		this.setSelectedIndex(0);
		
	}

}
