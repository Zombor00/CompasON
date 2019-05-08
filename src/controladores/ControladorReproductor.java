package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GuiAplicacion;
import GUI.Reproductor;
import aplicacion.Aplicacion;
import pads.musicPlayer.exceptions.Mp3PlayerException;

/**
 * Esta clase tiene toda la informacion relevante al controlador
 * del reproductor: todas las acciones que puede haber en ese panel
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class ControladorReproductor implements ActionListener{

	/**
	 * Aplicacion con la informacion
	 */
	private Aplicacion aplicacion;
	/**
	 * Interfaz grafica de la aplicacion
	 */
	private GuiAplicacion gui;
	
	public void actionPerformed(ActionEvent e) {
		if (gui == null) gui = GuiAplicacion.getInstance();
		if (aplicacion == null) aplicacion = Aplicacion.getInstance();
		
		/* Se comienza a reproducir la cola o se pausa y se suman las reproducciones correctamente */
		if (e.getActionCommand().equals("PULSADO")) {
			Reproductor r = gui.getReproductor();
			boolean play = r.getPlay();
			if(aplicacion.getCola().getQueueSize() != 0) {
				if(play) {
					try {
						aplicacion.getCola().play();
					} catch (Mp3PlayerException e1) {
						e1.printStackTrace();
					}
				}else {
					boolean firstRep = gui.getReproductor().getFirstRep();
					if(firstRep) {
						gui.getReproductor().setFirstRep(false);
					}else {
						int reproducidas = aplicacion.getCola().getSongsPlayed();
						aplicacion.getUsuarioLogeado().setReproducidas(aplicacion.getUsuarioLogeado().getReproducidas() + reproducidas);
						aplicacion.getCola().resetSongsPlayed();
						gui.actualizarDatos();
					}
					aplicacion.getCola().stop();
					
				}
				r.changeIcon(!play);
			}
		}
	}
}
