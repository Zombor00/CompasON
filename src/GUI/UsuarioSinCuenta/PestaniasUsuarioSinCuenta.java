package GUI.UsuarioSinCuenta;

import javax.swing.*;

import GUI.AccesoComun.Busqueda;

public class PestaniasUsuarioSinCuenta extends JTabbedPane {
	
	private InicioUsuarioSinCuenta inicioUsuarioSinCuenta = new InicioUsuarioSinCuenta();
	private Busqueda busqueda = new Busqueda();
	
	public PestaniasUsuarioSinCuenta() {
		super();
		
		this.addTab("Inicio", inicioUsuarioSinCuenta);
		this.addTab("Busqueda", busqueda);
		
		this.setSelectedIndex(0);
		
	}
	
	public InicioUsuarioSinCuenta getInicio() {
		return this.inicioUsuarioSinCuenta;
	}
	
	public Busqueda getBusqueda() {
		return this.busqueda;
	}

}
