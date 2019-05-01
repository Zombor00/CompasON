package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import GUI.GuiAplicacion;
import GUI.UsuarioPremium.MisListas;
import aplicacion.Aplicacion;
import excepciones.ExcepcionLimiteReproducidasAlcanzado;
import excepciones.ExcepcionNoAptoParaMenores;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;
import excepciones.ExcepcionReproducirProhibido;
import media.Lista;
import pads.musicPlayer.exceptions.Mp3PlayerException;

public class ControladorMisListas implements ActionListener {
	
	private Aplicacion aplicacion;
	private MisListas vista;
	private GuiAplicacion gui;
	
	public ControladorMisListas(MisListas misListas) {
		this.vista = misListas;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (aplicacion == null) aplicacion = Aplicacion.getInstance();
		if (gui == null) gui = GuiAplicacion.getInstance();
		
		if (e.getActionCommand().equals("OPCIONES")) {
			JButton opciones = vista.getOpciones();
			JPopupMenu menu = vista.getMenu();
			menu.show(opciones, 0, opciones.getHeight());
		} else if (e.getActionCommand().equals("REPRODUCIR")) {
			JTable tabla = vista.getTabla();
			
	        int fila = tabla.getSelectedRow();
	        if(fila == -1) {
	        	return;
	        }
	        Lista lista = (Lista)tabla.getValueAt(fila, 0);
	        try {
				aplicacion.reproducirReproducible(lista);
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
		} else if(e.getActionCommand().equals("CREAR_LISTA")) {
			System.out.println("CRE");
		} else if(e.getActionCommand().equals("BORRAR")) {
			
		}
		
	}

}
