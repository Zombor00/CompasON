package GUI.UsuarioRegistrado;

import java.util.ArrayList;

import javax.swing.*;
import GUI.AccesoComun.*;
import media.Buscable;;


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
	
	public void actualizarBusqueda(ArrayList<Buscable> buscables) {
		busqueda.actualizarBusqueda(buscables);
	}
	
	

}
