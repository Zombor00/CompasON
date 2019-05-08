package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GuiAplicacion;
import GUI.Administrador.Opciones;
import aplicacion.Aplicacion;

/**
 * Esta clase tiene toda la informacion relevante al controlador
 * de opciones: todas las acciones que puede haber en ese panel
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class ControladorOpciones implements ActionListener {
	
	/**
	 * Panel de opciones donde suceden los eventos
	 */
	private Opciones opciones;
	/**
	 * Aplicacion con la informacion
	 */
	private Aplicacion aplicacion;
	/**
	 * Interfaz grafica de la aplicacion
	 */
	private GuiAplicacion gui;
	
	/**
	 * Constructor del controlador con opciones
	 * @param opciones panel donde actua el controlador
	 */
	public ControladorOpciones(Opciones opciones) {
		this.opciones = opciones;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (aplicacion == null) aplicacion = Aplicacion.getInstance();
		if (gui == null) gui = GuiAplicacion.getInstance();
		
		/* Se actualizan los valores indicados en opciones si se pulsa actualizar */
		if (e.getActionCommand().equals("ACTUALIZAR")) {
			aplicacion.setPrecioPremium((Integer) opciones.getPrecio().getValue());
			aplicacion.setLimiteReproducciones((Integer) opciones.getLimite().getValue());
			aplicacion.setReproduccionesPremium((Integer) opciones.getReproducciones().getValue());
		}
	}

}
