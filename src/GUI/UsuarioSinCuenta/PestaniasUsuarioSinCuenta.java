package GUI.UsuarioSinCuenta;

import javax.swing.*;

import GUI.AccesoComun.Busqueda;

/**
 * Esta clase tiene toda la informacion relevante a las pestanias
 * de los usuarios sin cuenta
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class PestaniasUsuarioSinCuenta extends JTabbedPane {
	
	/**
	 * Pestania de inicio de un usuario sin cuenta
	 */
	private InicioUsuarioSinCuenta inicioUsuarioSinCuenta = new InicioUsuarioSinCuenta();
	/**
	 * Pestania de busqueda de un usuario sin cuenta
	 */
	private Busqueda busqueda = new Busqueda();
	
	/**
	 * Constructor que crea las dos pestanias
	 * 
	 */
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
	
	/**
     * Metodo que actualiza la informacion de las pestanias
     * 
     */
	public void actualizarDatos() {
		busqueda.actualizarDatos();
		
	}

}
