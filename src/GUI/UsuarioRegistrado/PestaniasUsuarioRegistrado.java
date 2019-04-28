package GUI.UsuarioRegistrado;

import javax.swing.*;
import GUI.AccesoComun.*;;


public class PestaniasUsuarioRegistrado extends JTabbedPane {
	
	private InicioRegistrado inicio = new InicioRegistrado();
	private Busqueda busqueda = new Busqueda();
	private MisCanciones misCanciones = new MisCanciones();
	
	public PestaniasUsuarioRegistrado() {
		super();
		
		this.addTab("Inicio", inicio);
		this.addTab("Busqueda", busqueda);
		this.addTab("Mis canciones", misCanciones);
		
		this.setSelectedIndex(0);
		
	}
	
	public InicioRegistrado getInicio() {
		return this.inicio;
	}
	
	public Busqueda getBusqueda() {
		return this.busqueda;
	}
	
	public MisCanciones getMisCanciones() {
		return this.misCanciones;
	}
	
	public void actualizarDatos() {
		this.inicio.actualizarDatos();
		this.misCanciones.actualizarDatos();
	}
	
	

}
