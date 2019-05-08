package GUI.AccesoComun;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Esta clase tiene toda la informacion relevante al panel de
 * informacion comun a todos los usuarios menos al administrador
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class Informacion extends JPanel{
	
	/**
	 * Panel donde aparece a quien sigue el usuario y que permite cerrar sesion
	 */
	private Sesion sesion = new Sesion();
	/**
	 * Panel donde aparecen las notificaciones del usuario
	 */
	private Notificaciones notificaciones = new Notificaciones();
	
	/**
	  * Constructor del panel de informacion 
	  *
	  */
	public Informacion() {
		
		super();
		BoxLayout layout = new BoxLayout(this,BoxLayout.Y_AXIS);
		this.setLayout(layout);
		
		this.add(sesion);
		this.add(notificaciones);
		this.setPreferredSize(new Dimension(300,800));
		this.setBorder(new LineBorder(Color.DARK_GRAY));
	}
	
	public Sesion getSesion() {
		return this.sesion;
	}
	
	public Notificaciones getNotificaciones() {
		return this.notificaciones;
	}
	
	/**
	 * Metodo que actualiza los datos de los paneles que contiene
	 * 
	 */
	public void actualizarDatos() {
		this.sesion.actualizarDatos();
		this.notificaciones.actualizarDatos();
	}

}
