package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import GUI.GuiAplicacion;
import GUI.Administrador.Validar;
import aplicacion.Aplicacion;
import excepciones.ExcepcionCancionModificable;
import excepciones.ExcepcionCancionYaValidada;
import excepciones.ExcepcionLimiteReproducidasAlcanzado;
import excepciones.ExcepcionNoAptoParaMenores;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;
import excepciones.ExcepcionReproducirProhibido;
import media.Cancion;
import media.EstadoValidacion;
import pads.musicPlayer.exceptions.Mp3PlayerException;

public class ControladorValidar implements ActionListener {

	private Aplicacion aplicacion;
	private Validar vista;
	private GuiAplicacion gui;

	public ControladorValidar(Validar validar) {
		this.vista = validar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (aplicacion == null)
			aplicacion = Aplicacion.getInstance();
		if (gui == null)
			gui = GuiAplicacion.getInstance();

		if (e.getActionCommand().equals("OPCIONES")) {
			JButton opciones = vista.getOpciones();
			JPopupMenu menu = vista.getMenu();
			menu.show(opciones, 0, opciones.getHeight());
		} else if (e.getActionCommand().equals("REPRODUCIR")) {
			JTable tabla = vista.getTabla();

			int fila = tabla.getSelectedRow();
			if (fila == -1) {
				return;
			}
			Cancion cancion = (Cancion) tabla.getValueAt(fila, 0);
			try {
				aplicacion.reproducirReproducible(cancion);
				gui.getReproductor().changeIcon(false);
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(gui, "No se encuentra el archivo");
			} catch (Mp3PlayerException e1) {
				JOptionPane.showMessageDialog(gui, "Reproductor no funcionando");
			} catch (ExcepcionLimiteReproducidasAlcanzado e1) {
				JOptionPane.showMessageDialog(gui, "Limite de reproducciones alcanzado");
			} catch (ExcepcionNoAptoParaMenores e1) {
				JOptionPane.showMessageDialog(gui, "No apto para menores");
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				e1.printStackTrace();
			} catch (ExcepcionReproducirProhibido e1) {
				e1.printStackTrace();
			}
		} else if (e.getActionCommand().equals("SIN_LIMITACION") || e.getActionCommand().equals("EXPLICITO")
				|| e.getActionCommand().equals("DENEGAR")) {
			JTable tabla = vista.getTabla();

			int fila = tabla.getSelectedRow();
			if (fila == -1) {
				return;
			}

			Cancion cancion = (Cancion) tabla.getValueAt(fila, 0);
			try {
				if (e.getActionCommand().equals("SIN_LIMITACION")) {
					aplicacion.getAdministrador().tramitarValidacion(cancion, EstadoValidacion.APTOMENORES);
				}
				if (e.getActionCommand().equals("EXPLICITO")) {
					aplicacion.getAdministrador().tramitarValidacion(cancion, EstadoValidacion.EXPLICITO);
				}
				if (e.getActionCommand().equals("DENEGAR")) {
					aplicacion.getAdministrador().tramitarValidacion(cancion, EstadoValidacion.NOVALIDADA);
				}
				gui.actualizarDatos();
			} catch (ExcepcionCancionModificable e1) {
				JOptionPane.showMessageDialog(gui, "Cancion en periodo de modificacion");
			} catch (ExcepcionCancionYaValidada e1) {
				JOptionPane.showMessageDialog(gui, "Cancion ya validada");
			}

		}

	}

}
