package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import GUI.GuiAplicacion;
import GUI.Administrador.Denuncias;
import aplicacion.Aplicacion;
import excepciones.ExcepcionLimiteReproducidasAlcanzado;
import excepciones.ExcepcionNoAptoParaMenores;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;
import excepciones.ExcepcionReproducirProhibido;
import gestion.Denuncia;
import pads.musicPlayer.exceptions.Mp3PlayerException;

public class ControladorDenuncias implements ActionListener {

	private Aplicacion aplicacion;
	private Denuncias vista;
	private GuiAplicacion gui;

	public ControladorDenuncias(Denuncias denuncias) {
		this.vista = denuncias;
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
			Denuncia denuncia = (Denuncia) tabla.getValueAt(fila, 0);
			try {
				aplicacion.reproducirReproducible(denuncia.getDenunciada());
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
		} else if (e.getActionCommand().equals("PLAGIO") || e.getActionCommand().equals("SIN_PLAGIO")) {
			JTable tabla = vista.getTabla();

			int fila = tabla.getSelectedRow();
			if (fila == -1) {
				return;
			}

			Denuncia denuncia = (Denuncia) tabla.getValueAt(fila, 0);

			if (e.getActionCommand().equals("PLAGIO")) {
				aplicacion.getAdministrador().tramitarDenuncia(denuncia, true);
			}
			if (e.getActionCommand().equals("SIN_PLAGIO")) {
				aplicacion.getAdministrador().tramitarDenuncia(denuncia, false);
			}
			vista.actualizarDatos();

		}

	}

}
