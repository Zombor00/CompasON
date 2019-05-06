package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import GUI.GuiAplicacion;
import GUI.AccesoComun.Busqueda;
import media.Buscable;
import media.Cancion;
import pads.musicPlayer.exceptions.Mp3InvalidFileException;
import pads.musicPlayer.exceptions.Mp3PlayerException;
import aplicacion.Aplicacion;
import excepciones.ExcepcionLimiteReproducidasAlcanzado;
import excepciones.ExcepcionNoAptoParaMenores;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;
import excepciones.ExcepcionReproducirProhibido;
import excepciones.ExcepcionSeguirseASiMismo;
import excepciones.ExcepcionUsuarioSinCuenta;
import excepciones.ExcepcionUsuarioYaSeguido;

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
			String modo = (String)vista.getModo().getSelectedItem();
			try {
				if(modo.equals("Por titulo")) {
					buscable = aplicacion.buscarPorTitulo(vista.getBusqueda());
				}else {
					buscable = aplicacion.buscarPorAutor(vista.getBusqueda());
				}
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				e1.printStackTrace();
			}
			
			gui.actualizarBusqueda(buscable,gui.getPanelesUsuarios().getActual());
		
		} else if (e.getActionCommand().equals("OPCIONES")) {
			JButton opciones = vista.getOpciones();
			JPopupMenu menu = vista.getMenu();
			menu.show(opciones, 0, opciones.getHeight());
		} else if (e.getActionCommand().equals("REPRODUCIR")) {
			Buscable b = this.getBuscable();
			if(b == null) {
				return;
			}
	     
	        try {
				aplicacion.reproducirReproducible(b);
		        gui.getReproductor().changeIcon(false);
		        gui.actualizarDatos();
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
	        
	        
	        //gui.getReproductor().get
	        //Si hay mas de una cancion seleccionada la primera se reproduce y las demas se aniaden a la cola
		}else if(e.getActionCommand().equals("ANIADIRACOLA")){
			Buscable b = this.getBuscable();
			if(b == null) {
				return;
			}
			
			try {
				aplicacion.aniadirALaCola(b);
				gui.actualizarDatos();
			} catch (Mp3InvalidFileException e1) {
				GuiAplicacion.showMessage("Reproductor no funcionando");
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				e1.printStackTrace();
			} catch (ExcepcionLimiteReproducidasAlcanzado e1) {
				GuiAplicacion.showMessage("Limite de reproducciones alcanzado");
			} catch (ExcepcionNoAptoParaMenores e1) {
				GuiAplicacion.showMessage("No apto para menores");
				e1.printStackTrace();
			} catch (ExcepcionReproducirProhibido e1) {
				e1.printStackTrace();
			} catch (ExcepcionUsuarioSinCuenta e1) {
				GuiAplicacion.showMessage("Debe registrarse para usar la cola");
				e1.printStackTrace();
			}
		}else if(e.getActionCommand().equals("SEGUIRAUTOR")) {
			Buscable b = this.getBuscable();
			if(b == null) {
				return;
			}
			
			try {
				aplicacion.getUsuarioLogeado().seguirUsuario(b.getAutor());
				gui.actualizarDatos();
			} catch (ExcepcionUsuarioYaSeguido e1) {
				e1.printStackTrace();
			} catch (ExcepcionSeguirseASiMismo e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if(e.getActionCommand().equals("DENUNCIAR")) {
			vista.comentarioDenunciaVisible(true);
		}else if(e.getActionCommand().equals("ENVIAR_DENUNCIA")) {
			Buscable b = this.getBuscable();
			if(b == null) {
				return;
			}
			if(b instanceof Cancion) {
				try {
					aplicacion.denunciarPlagio((Cancion)b, vista.getComentarioDenuncia());
				} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
					e1.printStackTrace();
				} catch (ExcepcionUsuarioSinCuenta e1) {
					GuiAplicacion.showMessage("Debe registrarse para denunciar");
					e1.printStackTrace();
				}
				gui.actualizarDatos();
			}else {
				GuiAplicacion.showMessage("Solo puede denunciar canciones");
			}
			vista.comentarioDenunciaVisible(false);
		} else if (e.getActionCommand().equals("ANIADIR_A_LISTA")) {
			GuiAplicacion.showMessage("NO ESTA IMPLEMENTADO");
		} 
	}
	
	private Buscable getBuscable() {
		JTable tablaNotificaciones = vista.getTabla();
		
        int fila = tablaNotificaciones.getSelectedRow();
        if(fila == -1) {
        	return null;
        }
        Buscable b = (Buscable)tablaNotificaciones.getModel().getValueAt(fila, 0);
        
        return b;
	}
}
