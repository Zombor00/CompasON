package GUI;

import javax.swing.*;

import GUI.Inicio.Inicio;

public class Pestanias extends JTabbedPane {
	
	public Pestanias() {
		super();
		JPanel inicio = new Inicio();
		JPanel busqueda = new Busqueda();
		
		this.addTab("Inicio", inicio);
		this.addTab("Busqueda", busqueda);
		
		this.setSelectedIndex(0);
		
	}

}
