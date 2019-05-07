package GUI.UsuarioRegistrado;

import javax.swing.*;
import GUI.AccesoComun.*;

/**
 * Esta clase tiene toda la informacion relevante a las pestanias
 * del usuario registrado
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class PestaniasUsuarioRegistrado extends JTabbedPane {
	
	/**
	 * Pestania de inicio del usuario registrado
	 */
	private InicioRegistrado inicio = new InicioRegistrado();
	
	/**
	 * Pestania de busqueda del usuario registrado
	 */
	private Busqueda busqueda = new Busqueda();
	
	/**
	 * Pestania con las canciones del usuario registrado
	 */
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
	
	/**
     * Metodo que actualiza la informacion de las pestanias del usuario registrado
     */
	public void actualizarDatos() {
		this.inicio.actualizarDatos();
		this.misCanciones.actualizarDatos();
		this.busqueda.actualizarDatos();
	}

}
