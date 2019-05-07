package GUI.Administrador;

import javax.swing.*;

import GUI.AccesoComun.Busqueda;

/**
 * Esta clase tiene toda la informacion relevante a las pestanias
 * del administrador
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class PestaniasAdministrador extends JTabbedPane {
	
	
	/**
	 * Pestania de inicio del administrador
	 */
	private InicioAdministrador inicio;
	
	/**
	 * Pestania de busqueda del administrador
	 */
	private Busqueda busqueda;
	
	/**
	 * Pestania de validar canciones del administrador
	 */
	private Validar validar;
	
	/**
	 * Pestania de tramitar denuncias del administrador
	 */
	private Denuncias denuncias;
	
	
	/**
	 * Constructor que crea las cuatro pestanias
	 * 
	 */
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
	
	/**
     * Metodo que actualiza la informacion de las pestanias
     * 
     */
	public void actualizarDatos() {
		this.inicio.actualizarDatos();
		this.busqueda.actualizarDatos();
		this.validar.actualizarDatos();
		this.denuncias.actualizarDatos();
	}
	
	
}
