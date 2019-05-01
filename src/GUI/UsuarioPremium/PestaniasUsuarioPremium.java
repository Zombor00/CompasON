package GUI.UsuarioPremium;

import javax.swing.JTabbedPane;

import GUI.AccesoComun.Busqueda;
import GUI.AccesoComun.MisCanciones;

public class PestaniasUsuarioPremium extends JTabbedPane{
	
	private InicioPremium inicio;
	Busqueda busqueda;
	private MisCanciones misCanciones;
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
	
	public void actualizarDatos() {
		inicio.actualizarDatos();
		busqueda.actualizarDatos();
		misCanciones.actualizarDatos();
		misListas.actualizarDatos();
	}

}
