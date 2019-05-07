package GUI.UsuarioPremium;

import javax.swing.JTabbedPane;

import GUI.AccesoComun.Busqueda;
import GUI.AccesoComun.MisCanciones;

/**
 * Esta clase tiene toda la informacion relevante a las pestanias
 * del usuario premium
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class PestaniasUsuarioPremium extends JTabbedPane{
	
	/**
	 * Pestania de inicio del usuario premium
	 */
	private InicioPremium inicio;
	
	/**
	 * Pestania de busqueda del usuario premium
	 */
	Busqueda busqueda;
	
	/**
	 * Pestania de las canciones del usuario premium
	 */
	private MisCanciones misCanciones;
	
	/**
	 * Pestania de listas del usuario premium
	 */
	private MisListas misListas;
	
	public PestaniasUsuarioPremium() {
		super();
		inicio = new InicioPremium();
		busqueda = new Busqueda();
		misCanciones = new MisCanciones();
		misListas = new MisListas();
		
		this.addTab("Inicio", inicio);
		this.addTab("Busqueda", busqueda);
		this.addTab("Mis canciones", misCanciones);
		this.addTab("Mis listas", misListas);
		
		this.setSelectedIndex(0);
		
	}
	
	public Busqueda getBusqueda() {
		return this.busqueda;
	}
	
	public MisCanciones getMisCanciones() {
		return this.misCanciones;
	}
	
	public MisListas getMisListas() {
		return this.misListas;
	}
	
	/*Actualiza los datos de cada pestania del usuario premium*/
	public void actualizarDatos() {
		inicio.actualizarDatos();
		busqueda.actualizarDatos();
		misCanciones.actualizarDatos();
		misListas.actualizarDatos();
	}

}
