package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import GUI.GuiAplicacion;
import GUI.AccesoComun.JCheckBoxScrollableListSelect;
import GUI.AccesoComun.ListaCanciones;
import GUI.AccesoComun.MisCanciones;
import aplicacion.Aplicacion;
import excepciones.ExcepcionCancionNoValidada;
import excepciones.ExcepcionCancionYaNoModificable;
import excepciones.ExcepcionCancionYaValidada;
import excepciones.ExcepcionDuracionLimiteSuperada;
import excepciones.ExcepcionErrorCreandoAlbum;
import excepciones.ExcepcionInsercionInvalida;
import excepciones.ExcepcionLimiteReproducidasAlcanzado;
import excepciones.ExcepcionMp3NoValido;
import excepciones.ExcepcionNoAptoParaMenores;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;
import excepciones.ExcepcionReproducibleNoValido;
import excepciones.ExcepcionReproducirProhibido;
import excepciones.ExcepcionUsuarioSinCuenta;
import media.Album;
import media.Buscable;
import media.Cancion;
import media.Lista;
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
			
			
			ArrayList<Cancion> canciones = this.getSelectedCanciones();
			if(canciones == null)return;
			
	        try {
				aplicacion.reproducirReproducible(canciones.get(0));
				canciones.remove(0);
				for (Cancion c : canciones) {
					try {
						aplicacion.aniadirALaCola(c);
					} catch (ExcepcionUsuarioSinCuenta e1) {
						
					}
				}
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
				GuiAplicacion.showMessage("Parametros de entrada incorrectos");
			} catch (ExcepcionReproducirProhibido e1) {
				GuiAplicacion.showMessage("Canción no reproducible");
			}
	        
	        
		} else if (e.getActionCommand().equals("REPRODUCIR_ALBUM")) {
			
			
			ArrayList<Album> albumes = this.getSelectedAlbumes();
			if(albumes == null)return;
			
	        try {
	        	aplicacion.reproducirReproducible(albumes.get(0));
				albumes.remove(0);
				for (Album a : albumes) {
					try {
						aplicacion.aniadirALaCola(a);
					} catch (ExcepcionUsuarioSinCuenta e1) {
						
					}
				}
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
				GuiAplicacion.showMessage("Canción no reproducible");
			}
	        
	        
		} else if(e.getActionCommand().equals("ANIADIR_CANCION_A_ALBUM")) {
			
			
			ArrayList<Integer> albumesSeleccionados = vista.getAlbumesSeleccionados();
			albumesSeleccionados.clear();
			JTable tablaCanciones = vista.getTablaCanciones();
			if(tablaCanciones.getSelectedRowCount() == 0) return;
			@SuppressWarnings("unused")
			JCheckBoxScrollableListSelect checkBoxScrollableListSelect = new JCheckBoxScrollableListSelect(
					"Seleccione un album",
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
			for(Integer indice : tablaCanciones.getSelectedRows()) {
				try {
					a.aniadirCancion((Cancion)datosCanciones.getValueAt(indice, 0));
				} catch (ExcepcionInsercionInvalida e1) {
					GuiAplicacion.showMessage("Insercion invalida");
				} catch (ExcepcionCancionNoValidada e1) {
					GuiAplicacion.showMessage("Cancion no validada");
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
				GuiAplicacion.showMessage("Error creando album");
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				GuiAplicacion.showMessage("Parmetros de entrada incorrectos");
			} catch (ExcepcionUsuarioSinCuenta e1) {
				GuiAplicacion.showMessage("Usuario sin cuenta");
			} catch (ExcepcionInsercionInvalida e1) {
				GuiAplicacion.showMessage("Insercion invalida");
			} catch (ExcepcionCancionNoValidada e1) {
				GuiAplicacion.showMessage("Cancion no validada");
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
				GuiAplicacion.showMessage("Fichero invalido");
			} catch (ExcepcionDuracionLimiteSuperada e1) {
				GuiAplicacion.showMessage("Duracion limite superada");
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				GuiAplicacion.showMessage("Parametros de entrada incorrectos");
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ExcepcionUsuarioSinCuenta e1) {
				e1.printStackTrace();
			} catch (ExcepcionMp3NoValido e1) {
				e1.printStackTrace();
			}
			gui.actualizarDatos();
			
			
		} else if (e.getActionCommand().equals("ANIADIR_CANCION_A_LISTA")) {
			
			
			if(aplicacion.getUsuarioLogeado() == null) {
				GuiAplicacion.showMessage("Inicia sesion para acceder a tus listas");
				return;
			}
			if(aplicacion.getUsuarioLogeado().esPremium() == false) {
				GuiAplicacion.showMessage("Hazte premium para acceder a tus listas");
				return;
			}
			ArrayList<Integer> listasSeleccionadas = vista.getListasSeleccionadas();
			listasSeleccionadas.clear();
			JTable tabla = vista.getTablaCanciones();
			if(tabla.getSelectedRowCount() == 0) return;
			new JCheckBoxScrollableListSelect(
					"Seleccione una lista ",
					GuiAplicacion.getInstance().getPanelesUsuarios().getPanelUsuarioPremium().getPestanias().getMisListas().getNombreListas(),
					null,
					listasSeleccionadas,vista.getAuxAniadirCancionALista());
			
			
		} else if (e.getActionCommand().equals("AUX_ANIADIR_CANCION_A_LISTA")) {
			
			
			JTable tabla = vista.getTablaCanciones();
			DefaultTableModel datos = vista.getDatosCanciones();
			if(tabla.getSelectedRowCount() == 0) return;
			Lista lista = (Lista)GuiAplicacion.getInstance().getPanelesUsuarios().getPanelUsuarioPremium()
					.getPestanias().getMisListas().getDatosListas()
					.getValueAt(vista.getListasSeleccionadas().get(0), 0);
			for(int fila : tabla.getSelectedRows()) {
				try {
					lista.aniadirReproducible((Buscable)datos.getValueAt(fila, 0));
				} catch (ExcepcionInsercionInvalida e1) {
					GuiAplicacion.showMessage("Insercion invalida");
				} catch (ExcepcionReproducibleNoValido e1) {
					GuiAplicacion.showMessage("Reproducible no valido");
				}
			}
			gui.actualizarDatos();
			
			
		} else if (e.getActionCommand().equals("ANIADIR_ALBUM_A_LISTA")) {
			
			
			if(aplicacion.getUsuarioLogeado() == null) {
				GuiAplicacion.showMessage("Inicia sesion para acceder a tus listas");
				return;
			}
			if(aplicacion.getUsuarioLogeado().esPremium() == false) {
				GuiAplicacion.showMessage("Hazte premium para acceder a tus listas");
				return;
			}
			ArrayList<Integer> listasSeleccionadas = vista.getListasSeleccionadas();
			listasSeleccionadas.clear();
			JTable tabla = vista.getTablaAlbumes();
			if(tabla.getSelectedRowCount() == 0) return;
			new JCheckBoxScrollableListSelect(
					"Seleccione una lista ",
					GuiAplicacion.getInstance().getPanelesUsuarios().getPanelUsuarioPremium().getPestanias().getMisListas().getNombreListas(),
					null,
					listasSeleccionadas,vista.getAuxAniadirAlbumALista());
			
			
		}  else if (e.getActionCommand().equals("AUX_ANIADIR_ALBUM_A_LISTA")) {
			
			
			JTable tabla = vista.getTablaAlbumes();
			DefaultTableModel datos = vista.getDatosAlbumes();
			if(tabla.getSelectedRowCount() == 0) return;
			Lista lista = (Lista)GuiAplicacion.getInstance().getPanelesUsuarios().getPanelUsuarioPremium()
					.getPestanias().getMisListas().getDatosListas()
					.getValueAt(vista.getListasSeleccionadas().get(0), 0);
			for(int fila : tabla.getSelectedRows()) {
				try {
					lista.aniadirReproducible((Buscable)datos.getValueAt(fila, 0));
				} catch (ExcepcionInsercionInvalida e1) {
					GuiAplicacion.showMessage("Insercion invalida");
				} catch (ExcepcionReproducibleNoValido e1) {
					GuiAplicacion.showMessage("Reproducible no valido");
				}
			}
			gui.actualizarDatos();
			
			
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
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				GuiAplicacion.showMessage("Parametros de entrada incorrectos");
			}
			
			
		} else if(e.getActionCommand().equals("ANIADIR_CANCION_COLA")) {
			
			
			ArrayList<Cancion> canciones = this.getSelectedCanciones();
			if(canciones == null) {
				return;
			}
			try {
				for (Cancion c : canciones) {
					aplicacion.aniadirALaCola(c);
				}
			} catch (Mp3InvalidFileException e1) {
				GuiAplicacion.showMessage("Reproductor no funcionando");
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				GuiAplicacion.showMessage("Parametros de entrada incorrectos");
			} catch (ExcepcionLimiteReproducidasAlcanzado e1) {
				GuiAplicacion.showMessage("Limite de reproducciones alcanzado");
				e1.printStackTrace();
			} catch (ExcepcionNoAptoParaMenores e1) {
				GuiAplicacion.showMessage("No apto para menores");
				e1.printStackTrace();
			} catch (ExcepcionReproducirProhibido e1) {
				GuiAplicacion.showMessage("Reproducir prohibido");
			} catch (ExcepcionUsuarioSinCuenta e1) {
				GuiAplicacion.showMessage("Debe registrarse para usar la cola");
				e1.printStackTrace();
			}
			
			
		} else if(e.getActionCommand().equals("ANIADIR_ALBUM_COLA")) {
			
			
			ArrayList<Album> albumes = this.getSelectedAlbumes();
			if(albumes == null) {
				return;
			}
			try {
				for(Album a : albumes) {
					aplicacion.aniadirALaCola(a);
				}
			} catch (Mp3InvalidFileException e1) {
				GuiAplicacion.showMessage("Reproductor no funcionando");
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				GuiAplicacion.showMessage("Parametros de entrada incorrectos");
			} catch (ExcepcionLimiteReproducidasAlcanzado e1) {
				GuiAplicacion.showMessage("Limite de reproducciones alcanzado");
			} catch (ExcepcionNoAptoParaMenores e1) {
				GuiAplicacion.showMessage("No apto para menores");
			} catch (ExcepcionReproducirProhibido e1) {
				GuiAplicacion.showMessage("Reproducir prohibido");
			} catch (ExcepcionUsuarioSinCuenta e1) {
				GuiAplicacion.showMessage("Debe registrarse para usar la cola");
			}
			
			
		} else if(e.getActionCommand().equals("ACEPTAR_MODIFICAR")) {
			
			
			Cancion c = this.getSelectedCancion();
			try {
				c.modificar(vista.getFormularioModificarCancion().getNombre(), vista.getFormularioModificarCancion().getFichero());
			} catch (FileNotFoundException e1) {
				GuiAplicacion.showMessage("File not found");
			} catch (ExcepcionCancionYaValidada e1) {
				GuiAplicacion.showMessage("Cancion ya validad");
			} catch (ExcepcionDuracionLimiteSuperada e1) {
				GuiAplicacion.showMessage("Duracion limite superada");
			} catch (ExcepcionCancionYaNoModificable e1) {
				GuiAplicacion.showMessage("Cancion ya no modificable");
			} catch (ExcepcionMp3NoValido e1) {
				GuiAplicacion.showMessage("Mp3 no valido");
			}
			gui.actualizarDatos();
			
			
			
		} else if(e.getActionCommand().equals("VISUALIZAR_ALBUM")) {
			
			
			Album a = this.getSelectedAlbum();
			if(a == null) {
				return;
			}
			new ListaCanciones(a);
			
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
	
	private ArrayList<Cancion> getSelectedCanciones(){
		JTable tablaCanciones = vista.getTablaCanciones();
		
		int filas[] = tablaCanciones.getSelectedRows();
        if(filas.length == 0) {
        	return null;
        }
        ArrayList<Cancion> canciones = new ArrayList<>();
        for (int f : filas) {
        	canciones.add((Cancion)tablaCanciones.getModel().getValueAt(f, 0));
        }        
        
        return canciones;
	}
	
	private ArrayList<Album> getSelectedAlbumes(){
		JTable tablaAlbumes = vista.getTablaAlbumes();
		
		int filas[] = tablaAlbumes.getSelectedRows();
        if(filas.length == 0) {
        	return null;
        }
        ArrayList<Album> albumes = new ArrayList<>();
        for (int f : filas) {
        	albumes.add((Album)tablaAlbumes.getModel().getValueAt(f, 0));
        }        
        
        return albumes;
	}

}
