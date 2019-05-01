package controladores;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import GUI.GuiAplicacion;
import GUI.AccesoComun.JCheckBoxList;
import GUI.AccesoComun.JCheckBoxScrollableList;
import GUI.AccesoComun.MisCanciones;
import aplicacion.Aplicacion;
import excepciones.ExcepcionLimiteReproducidasAlcanzado;
import excepciones.ExcepcionNoAptoParaMenores;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;
import excepciones.ExcepcionReproducirProhibido;
import media.Buscable;
import pads.musicPlayer.exceptions.Mp3PlayerException;

public class ControladorMisCanciones implements ActionListener {
	
	private Aplicacion aplicacion;
	private MisCanciones  vista;
	private GuiAplicacion gui;
	
	public ControladorMisCanciones(MisCanciones  vista) {
		this.vista = vista;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (aplicacion == null) aplicacion = Aplicacion.getInstance();
		if (gui == null) gui = GuiAplicacion.getInstance();
		
		if (e.getActionCommand().equals("OPCIONES_CANCIONES")) {
			JButton opciones = vista.getOpcionesCanciones();
			JPopupMenu menu = vista.getMenuCanciones();
			menu.show(opciones, 0, opciones.getHeight());
		} else if (e.getActionCommand().equals("OPCIONES_ALBUMES")) {
			JButton opciones = vista.getOpcionesAlbumes();
			JPopupMenu menu = vista.getMenuAlbumes();
			menu.show(opciones, 0, opciones.getHeight());
		} else if (e.getActionCommand().equals("REPRODUCIR_CANCION")) {
			JTable tablaNotificaciones = vista.getTablaCanciones();
			
	        int fila = tablaNotificaciones.getSelectedRow();
	        if(fila == -1) {
	        	return;
	        }
	        Buscable b = (Buscable)tablaNotificaciones.getModel().getValueAt(fila, 0);
	     
	        try {
				aplicacion.reproducirReproducible(b);
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(gui,"No se encuentra el archivo");
			} catch (Mp3PlayerException e1) {
				JOptionPane.showMessageDialog(gui,"Reproductor no funcionando");
			} catch (ExcepcionLimiteReproducidasAlcanzado e1) {
				JOptionPane.showMessageDialog(gui,"Limite de reproducciones alcanzado");
			} catch (ExcepcionNoAptoParaMenores e1) {
				JOptionPane.showMessageDialog(gui,"No apto para menores");
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				e1.printStackTrace();
			} catch (ExcepcionReproducirProhibido e1) {
				e1.printStackTrace();
			}
		} else if (e.getActionCommand().equals("REPRODUCIR_ALBUM")) {
			JTable tablaNotificaciones = vista.getTablaAlbumes();
			
	        int fila = tablaNotificaciones.getSelectedRow();
	        if(fila == -1) {
	        	return;
	        }
	        Buscable b = (Buscable)tablaNotificaciones.getModel().getValueAt(fila, 0);
	     
	        try {
				aplicacion.reproducirReproducible(b);
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(gui,"No se encuentra el archivo");
			} catch (Mp3PlayerException e1) {
				JOptionPane.showMessageDialog(gui,"Reproductor no funcionando");
			} catch (ExcepcionLimiteReproducidasAlcanzado e1) {
				JOptionPane.showMessageDialog(gui,"Limite de reproducciones alcanzado");
			} catch (ExcepcionNoAptoParaMenores e1) {
				JOptionPane.showMessageDialog(gui,"No apto para menores");
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				e1.printStackTrace();
			} catch (ExcepcionReproducirProhibido e1) {
				e1.printStackTrace();
			}
		} else if(e.getActionCommand().equals("ANIADIR_CANCION_A_ALBUM")) {
			JCheckBoxList checkBoxList = new JCheckBoxList(vista.getNombreAlbumes());
			JCheckBoxScrollableList checkBoxScrollableList = new JCheckBoxScrollableList(checkBoxList);
			checkBoxScrollableList.setPreferredSize(new Dimension(500,250));
			JOptionPane.showMessageDialog(null, checkBoxScrollableList, "Seleccione el album", JOptionPane.PLAIN_MESSAGE);
			
			System.out.println("Hay que aniadir la cancion seleccionada al album en la posicion: " + 
			checkBoxScrollableList.getSelectedIndices());
		}
	}

}
