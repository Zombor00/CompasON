package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
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

/**
 * Esta clase tiene toda la informacion relevante al controlador
 * de validar: todas las acciones que puede haber en esa pestania
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class ControladorValidar implements ActionListener {

	/**
	 * Aplicacion con la informacion
	 */
	private Aplicacion aplicacion;
	/**
	 * Pestania validar donde suceden los eventos
	 */
	private Validar vista;
	/**
	 * Interfaz grafica de la aplicacion
	 */
	private GuiAplicacion gui;

	/**
	 * Constructor del controlador con validar
	 * @param validar panel donde actua el controlador
	 */
	public ControladorValidar(Validar validar) {
		this.vista = validar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (aplicacion == null)
			aplicacion = Aplicacion.getInstance();
		if (gui == null)
			gui = GuiAplicacion.getInstance();
		
		/* Si se pulsa opciones se muestra el menu */
		if (e.getActionCommand().equals("OPCIONES")) {
			JButton opciones = vista.getOpciones();
			JPopupMenu menu = vista.getMenu();
			menu.show(opciones, 0, opciones.getHeight());
		/* Si se pulsa reproducir se reproduce la cancion seleccionada */
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
				GuiAplicacion.showMessage("No se encuentra el archivo");
			} catch (Mp3PlayerException e1) {
				GuiAplicacion.showMessage("Reproductor no funcionando");
			} catch (ExcepcionLimiteReproducidasAlcanzado e1) {
				GuiAplicacion.showMessage("Limite de reproducciones alcanzado");
			} catch (ExcepcionNoAptoParaMenores e1) {
				GuiAplicacion.showMessage("No apto para menores");
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				e1.printStackTrace();
			} catch (ExcepcionReproducirProhibido e1) {
				e1.printStackTrace();
			}
		/* Si se pulsa cualquiera de estas opciones se tramita la validacion con la caracteristica correspondiente */
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
				aplicacion.actualizarCanciones();
				gui.actualizarDatos();
			} catch (ExcepcionCancionModificable e1) {
				GuiAplicacion.showMessage("Cancion en periodo de modificacion");
			} catch (ExcepcionCancionYaValidada e1) {
				GuiAplicacion.showMessage("Cancion ya validada");
			}

		}

	}

}
