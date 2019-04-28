package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import GUI.GuiAplicacion;
import GUI.AccesoComun.Busqueda;
import media.Buscable;
import aplicacion.Aplicacion;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;

public class ControladorBuscar implements ActionListener {
	
	private Aplicacion aplicacion;
	private Busqueda busqueda;
	private GuiAplicacion gui;
	
	public ControladorBuscar(Busqueda busqueda) {
		this.busqueda = busqueda;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (aplicacion == null) aplicacion = Aplicacion.getInstance();
		if (gui == null) gui = GuiAplicacion.getInstance();
		ArrayList<Buscable> buscable = null;
		
		if (e.getActionCommand().equals("BUSCAR")) {
			try {
				buscable = aplicacion.buscarPorTitulo(busqueda.getBusqueda());
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				e1.printStackTrace();
			}
			
			gui.actualizarBusqueda(buscable);
		
		}
	}
}
