package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import GUI.GuiAplicacion;
import GUI.AccesoComun.JCheckBoxScrollableListSelect;
import GUI.AccesoComun.MisCanciones;
import aplicacion.Aplicacion;
import excepciones.ExcepcionCancionNoValidada;
import excepciones.ExcepcionDuracionLimiteSuperada;
import excepciones.ExcepcionErrorCreandoAlbum;
import excepciones.ExcepcionInsercionInvalida;
import excepciones.ExcepcionLimiteReproducidasAlcanzado;
import excepciones.ExcepcionMp3NoValido;
import excepciones.ExcepcionNoAptoParaMenores;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;
import excepciones.ExcepcionReproducirProhibido;
import excepciones.ExcepcionUsuarioSinCuenta;
import media.Album;
import media.Buscable;
import media.Cancion;
import media.Estado;
import media.EstadoValidacion;
import pads.musicPlayer.exceptions.Mp3InvalidFileException;
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
			Cancion c = this.getSelectedCancion();
			if(c == null)return;
			
			if(c.getEstado() == Estado.BLOQUEADO) {
				GuiAplicacion.showMessage("Cancion bloqueada");
				return;
			}
	     
	        try {
				aplicacion.reproducirReproducible(c);
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
		} else if (e.getActionCommand().equals("REPRODUCIR_ALBUM")) {
			Album a = this.getSelectedAlbum();
			if(a == null)return;
			
			if(a.getEstado() == Estado.BLOQUEADO) {
				GuiAplicacion.showMessage("Album bloqueado");
				return;
			}
			
	        try {
				aplicacion.reproducirReproducible(a);
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
		} else if(e.getActionCommand().equals("ANIADIR_CANCION_A_ALBUM")) {
			ArrayList<Integer> albumesSeleccionados = vista.getAlbumesSeleccionados();
			albumesSeleccionados.clear();
			JTable tablaCanciones = vista.getTablaCanciones();
			
			if(tablaCanciones.getSelectedRowCount() == 0) return;
			
			@SuppressWarnings("unused")
			JCheckBoxScrollableListSelect checkBoxScrollableListSelect = new JCheckBoxScrollableListSelect(
					"Seleccione el album",
					vista.getNombreAlbumes(),
					null,
					albumesSeleccionados,vista.getAuxAniadirCancionAAlbum());			
		} else if (e.getActionCommand().equals("AUX_ANIADIR_CANCION_A_ALBUM")) {
			ArrayList<Integer> albumesSeleccionados = vista.getAlbumesSeleccionados();
			DefaultTableModel datosCanciones = vista.getDatosCanciones();
			DefaultTableModel datosAlbumes = vista.getDatosAlbumes();
			JTable tablaCanciones = vista.getTablaCanciones();
			if(albumesSeleccionados.size() == 0) return;
			Album a = ((Album)(datosAlbumes.getValueAt(albumesSeleccionados.get(0), 0)));
			System.out.println("ContoladorMisCanciones.214: Aniadimos al album " + a);
			
			for(Integer indice : tablaCanciones.getSelectedRows()) {
				try {
					a.aniadirCancion((Cancion)datosCanciones.getValueAt(indice, 0));
				} catch (ExcepcionInsercionInvalida e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ExcepcionCancionNoValidada e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			gui.actualizarDatos();
		} else if (e.getActionCommand().equals("ACEPTAR_ALBUM")) {
			DefaultTableModel datosCanciones = vista.getDatosCanciones();
			ArrayList<Integer> cancionesSeleccionadas = vista.getFormularioAlbum().getCancionesSeleccionadas();
			String nombre = vista.getFormularioAlbum().getNombre();
			
			if(nombre.length()==0) {
				GuiAplicacion.showMessage("Introduce un nombre");
				return;
			}
			if(cancionesSeleccionadas.size()==0) {
				GuiAplicacion.showMessage("Selecciona al menos una cancion");
				return;
			}
			ArrayList<Cancion> canciones = new ArrayList<>();
			for(Integer indice : cancionesSeleccionadas) {
				canciones.add((Cancion)datosCanciones.getValueAt(indice, 0));
			}
			try {
				aplicacion.aniadirAlbum(nombre, canciones);
			} catch (ExcepcionErrorCreandoAlbum e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExcepcionUsuarioSinCuenta e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExcepcionInsercionInvalida e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExcepcionCancionNoValidada e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			gui.actualizarDatos();
		} else if(e.getActionCommand().equals("ACEPTAR_CANCION")) {
			String nombre = vista.getFormularioCancion().getNombre();
			String fichero = vista.getFormularioCancion().getFichero();
			if(nombre.length() == 0) {
				GuiAplicacion.showMessage("Introduce un nombre para la cancion");
				return;
			}
			if(fichero.length() == 0) {
				GuiAplicacion.showMessage("Introduce el nombre del fichero");
				return;
			}
			try {
				aplicacion.subirCancion(nombre, fichero);
			} catch (Mp3InvalidFileException e1) {
				e1.printStackTrace();
			} catch (ExcepcionDuracionLimiteSuperada e1) {
				e1.printStackTrace();
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ExcepcionUsuarioSinCuenta e1) {
				e1.printStackTrace();
			} catch (ExcepcionMp3NoValido e1) {
				e1.printStackTrace();
			}
			gui.actualizarDatos();
			
		} else if (e.getActionCommand().equals("ANIADIR_CANCION_A_LISTA")) {
			GuiAplicacion.showMessage("NO ESTA IMPLEMENTADO");
			
		} else if (e.getActionCommand().equals("ANIADIR_ALBUM_A_LISTA")) {
			GuiAplicacion.showMessage("NO ESTA IMPLEMENTADO");
			
		} else if(e.getActionCommand().equals("BORRAR_CANCION")) {
			Cancion c = this.getSelectedCancion();
			if(c == null) {
				return;
			}
			
			try {
				aplicacion.borrarCancion(c);
				gui.actualizarDatos();
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				e1.printStackTrace();
			}
				
		} else if(e.getActionCommand().equals("BORRAR_ALBUM")) {
			Album a = this.getSelectedAlbum();
			if(a == null) return;
			try {
				aplicacion.borrarAlbum(a);
				gui.actualizarDatos();
				System.out.println("Hola");
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				e1.printStackTrace();
			}
		} else if(e.getActionCommand().equals("ANIADIR_CANCION_COLA")) {
			Cancion c = this.getSelectedCancion();
			if(c == null) {
				return;
			}
			
			try {
				aplicacion.aniadirALaCola(c);
			} catch (Mp3InvalidFileException e1) {
				GuiAplicacion.showMessage("Reproductor no funcionando");
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				e1.printStackTrace();
			} catch (ExcepcionLimiteReproducidasAlcanzado e1) {
				GuiAplicacion.showMessage("Limite de reproducciones alcanzado");
				e1.printStackTrace();
			} catch (ExcepcionNoAptoParaMenores e1) {
				GuiAplicacion.showMessage("No apto para menores");
				e1.printStackTrace();
			} catch (ExcepcionReproducirProhibido e1) {
				e1.printStackTrace();
			} catch (ExcepcionUsuarioSinCuenta e1) {
				GuiAplicacion.showMessage("Debe registrarse para usar la cola");
				e1.printStackTrace();
			}
		} else if(e.getActionCommand().equals("ANIADIR_ALBUM_COLA")) {
			Album a = this.getSelectedAlbum();
			if(a == null) {
				return;
			}
			
			try {
				aplicacion.aniadirALaCola(a);
			} catch (Mp3InvalidFileException e1) {
				GuiAplicacion.showMessage("Reproductor no funcionando");
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				e1.printStackTrace();
			} catch (ExcepcionLimiteReproducidasAlcanzado e1) {
				GuiAplicacion.showMessage("Limite de reproducciones alcanzado");
				e1.printStackTrace();
			} catch (ExcepcionNoAptoParaMenores e1) {
				GuiAplicacion.showMessage("No apto para menores");
				e1.printStackTrace();
			} catch (ExcepcionReproducirProhibido e1) {
				e1.printStackTrace();
			} catch (ExcepcionUsuarioSinCuenta e1) {
				GuiAplicacion.showMessage("Debe registrarse para usar la cola");
				e1.printStackTrace();
			}
		} else if(e.getActionCommand().equals("MODIFICAR_CANCION")) {
			Cancion c = this.getSelectedCancion();
			if (c == null) return;
			GuiAplicacion.showMessage("NO ESTA IMPLEMENTADO");
		}
	}
	
	public Album getSelectedAlbum() {
		JTable tablaAlbum = vista.getTablaAlbumes();
        int fila = tablaAlbum.getSelectedRow();
        if(fila == -1) {
        	return null;
        }
        return (Album)tablaAlbum.getModel().getValueAt(fila, 0);
	}
	
	public Cancion getSelectedCancion() {
		JTable tablaCanciones = vista.getTablaCanciones();
        int fila = tablaCanciones.getSelectedRow();
        if(fila == -1) {
        	return null;
        }
        return (Cancion)tablaCanciones.getModel().getValueAt(fila, 0);
	}

}
