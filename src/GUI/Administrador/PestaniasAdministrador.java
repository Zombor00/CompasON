package GUI.Administrador;

import javax.swing.*;

import GUI.AccesoComun.Busqueda;


public class PestaniasAdministrador extends JTabbedPane {
	
	private InicioAdministrador inicio;
	private Busqueda busqueda;
	private Validar validar;
	private Denuncias denuncias;
	
	public PestaniasAdministrador() {
		super();
		inicio = new InicioAdministrador();
		busqueda = new Busqueda();
		validar = new Validar();
		denuncias = new Denuncias();
		
		this.addTab("Inicio", inicio);
		this.addTab("Busqueda", busqueda);
		this.addTab("Validar", validar);
		this.addTab("Denuncias", denuncias);
		
		this.setSelectedIndex(0);
		
	}
	
	public Busqueda getBusqueda() {
		return busqueda;
	}

	public InicioAdministrador getInicio() {
		return inicio;
	}

	public Validar getValidar() {
		return validar;
	}

	public Denuncias getDenuncias() {
		return denuncias;
	}
	
	public void actualizarDatos() {
		this.inicio.actualizarDatos();
		this.validar.actualizarDatos();
		this.denuncias.actualizarDatos();
	}
	
	
}
