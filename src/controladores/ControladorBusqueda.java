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
	        
	        
	        //gui.getReproductor().get
	        //Si hay mas de una cancion seleccionada la primera se reproduce y las demas se aniaden a la cola
		}else if(e.getActionCommand().equals("ANIADIRACOLA")){
			Buscable b = this.getBuscable();
			if(b == null) {
				return;
			}
			
			try {
				aplicacion.aniadirALaCola(b);
			} catch (Mp3InvalidFileException e1) {
				JOptionPane.showMessageDialog(gui,"Reproductor no funcionando");
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				e1.printStackTrace();
			} catch (ExcepcionLimiteReproducidasAlcanzado e1) {
				JOptionPane.showMessageDialog(gui,"Limite de reproducciones alcanzado");
				e1.printStackTrace();
			} catch (ExcepcionNoAptoParaMenores e1) {
				JOptionPane.showMessageDialog(gui,"No apto para menores");
				e1.printStackTrace();
			} catch (ExcepcionReproducirProhibido e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExcepcionUsuarioSinCuenta e1) {
				JOptionPane.showMessageDialog(gui,"Debe registrarse para usar la cola");
				e1.printStackTrace();
			}
		}else if(e.getActionCommand().equals("SEGUIRAUTOR")) {
			Buscable b = this.getBuscable();
			if(b == null) {
				return;
			}
			
			try {
				aplicacion.getUsuarioLogeado().seguirUsuario(b.getAutor());
			} catch (ExcepcionUsuarioYaSeguido e1) {
				e1.printStackTrace();
			}
		} else if(e.getActionCommand().equals("DENUNCIAR")) {
			Buscable b = this.getBuscable();
			if(b == null) {
				return;
			}
			
			if(b instanceof Cancion) {
				try {
					aplicacion.denunciarPlagio((Cancion)b, "RELLENAR");
				} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
					e1.printStackTrace();
				} catch (ExcepcionUsuarioSinCuenta e1) {
					JOptionPane.showMessageDialog(gui,"Debe registrarse para denunciar");
					e1.printStackTrace();
				}
				gui.actualizarDatos();
			}else {
				JOptionPane.showMessageDialog(gui,"Solo puede denunciar canciones");
			}
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
