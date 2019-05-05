package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GuiAplicacion;
import GUI.Reproductor;
import aplicacion.Aplicacion;
import pads.musicPlayer.exceptions.Mp3PlayerException;

public class ControladorReproductor implements ActionListener{

	private Aplicacion aplicacion;
	private GuiAplicacion gui;
	
	public void actionPerformed(ActionEvent e) {
		if (gui == null) gui = GuiAplicacion.getInstance();
		if (aplicacion == null) aplicacion = Aplicacion.getInstance();
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
					aplicacion.getCola().stop();
				}
				r.changeIcon(!play);
			}
		}
	}
}
