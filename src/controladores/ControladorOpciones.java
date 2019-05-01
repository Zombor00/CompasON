package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GuiAplicacion;
import GUI.Administrador.Opciones;
import aplicacion.Aplicacion;

public class ControladorOpciones implements ActionListener {

	private Opciones opciones;
	private Aplicacion aplicacion;
	private GuiAplicacion gui;
	
	public ControladorOpciones(Opciones opciones) {
		this.opciones = opciones;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (aplicacion == null) aplicacion = Aplicacion.getInstance();
		if (gui == null) gui = GuiAplicacion.getInstance();
		
		if (e.getActionCommand().equals("ACTUALIZAR")) {
			aplicacion.setPrecioPremium((Integer) opciones.getPrecio().getValue());
			aplicacion.setLimiteReproducciones((Integer) opciones.getLimite().getValue());
			aplicacion.setReproduccionesPremium((Integer) opciones.getReproducciones().getValue());
		}
	}

}
