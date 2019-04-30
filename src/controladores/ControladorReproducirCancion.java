package controladores;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import GUI.GuiAplicacion;
import GUI.AccesoComun.Busqueda;
import GUI.AccesoComun.Notificaciones;
import aplicacion.Aplicacion;
import excepciones.ExcepcionLimiteReproducidasAlcanzado;
import excepciones.ExcepcionNoAptoParaMenores;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;
import excepciones.ExcepcionReproducirProhibido;
import gestion.Notificacion;
import media.Buscable;
import pads.musicPlayer.exceptions.Mp3PlayerException;

public class ControladorReproducirCancion implements ListSelectionListener{

	private Aplicacion aplicacion;
	private Busqueda busqueda;
	private GuiAplicacion gui;
	
	public ControladorReproducirCancion(Busqueda busqueda) {
		this.busqueda = busqueda;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (aplicacion == null) aplicacion = Aplicacion.getInstance();
		if (gui == null) gui = GuiAplicacion.getInstance();
		ArrayList<Buscable> buscables = null;
		JTable tablaNotificaciones = busqueda.getTabla();
		
        int fila = tablaNotificaciones.getSelectedRow();
        if(fila == -1) {
        	return;
        }
        Buscable b = (Buscable)tablaNotificaciones.getValueAt(fila, 0);
     
        try {
			aplicacion.reproducirReproducible(b);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (Mp3PlayerException e1) {
			e1.printStackTrace();
		} catch (ExcepcionLimiteReproducidasAlcanzado e1) {
			e1.printStackTrace();
		} catch (ExcepcionNoAptoParaMenores e1) {
			e1.printStackTrace();
		} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
			e1.printStackTrace();
		} catch (ExcepcionReproducirProhibido e1) {
			e1.printStackTrace();
		}
        
        //Cambiar el boton del reproductor
        //gui.getReproductor().get
		
	}
}
