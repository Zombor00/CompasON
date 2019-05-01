package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import GUI.GuiAplicacion;
import GUI.AccesoComun.Busqueda;
import media.Buscable;
import pads.musicPlayer.exceptions.Mp3PlayerException;
import aplicacion.Aplicacion;
import excepciones.ExcepcionLimiteReproducidasAlcanzado;
import excepciones.ExcepcionNoAptoParaMenores;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;
import excepciones.ExcepcionReproducirProhibido;

public class ControladorBusqueda implements ActionListener {
	
	private Aplicacion aplicacion;
	private Busqueda vista;
	private GuiAplicacion gui;
	
	public ControladorBusqueda(Busqueda busqueda) {
		this.vista = busqueda;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (aplicacion == null) aplicacion = Aplicacion.getInstance();
		if (gui == null) gui = GuiAplicacion.getInstance();
		ArrayList<Buscable> buscable = null;
		
		if (e.getActionCommand().equals("BUSCAR")) {
			try {
				buscable = aplicacion.buscarPorTitulo(vista.getBusqueda());
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				e1.printStackTrace();
			}
			
			gui.actualizarBusqueda(buscable,gui.getPanelesUsuarios().getActual());
		
		} else if (e.getActionCommand().equals("OPCIONES")) {
			JButton opciones = vista.getOpciones();
			JPopupMenu menu = vista.getMenu();
			menu.show(opciones, 0, opciones.getHeight());
		} else if (e.getActionCommand().equals("REPRODUCIR")) {
			JTable tablaNotificaciones = vista.getTabla();
			
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
	        //Si hay mas de una cancion seleccionada la primera se reproduce y las demas se aniaden a la cola
	        //Gestionar las excepciones bien (usar popups para informar al usuario)
			
		}
	}
}
