package GUI.Administrador;


import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.LineBorder;

import GUI.AccesoComun.Sesion;

/**
 * Esta clase tiene toda la informacion relevante a la parte de la 
 * pantalla que muestra informacion del administrador
 * 
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class InformacionAdministrador extends JPanel {
	/**
	 * Zona relacionada con el estado de la sesion del usuario
	 */
	private Sesion sesion;
	/**
	 * Zona relacionada con el ajuste de las opciones de la aplicacion
	 */
	private Opciones opciones;
	
	/**
	 * Constructor de la parte de la informacion del administrador
	 *
	 */
	public InformacionAdministrador() {
		super();
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		
		sesion = new Sesion();
		opciones = new Opciones();
		
		this.add(sesion);
		this.add(opciones);
		this.setPreferredSize(new Dimension(300,800));
		this.setBorder(new LineBorder(Color.DARK_GRAY));
	}
	
	public Sesion getSesion() {
		return this.sesion;
	}
	
	public Opciones getOpciones() {
		return this.opciones;
	}
	
	/**
	 * Metodo que actualiza los datos de la parte de informacion del administrador
	 * 
	 */
	public void actualizarDatos() {
		this.sesion.actualizarDatos();
		this.opciones.actualizarDatos();		
	}

}
