package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import GUI.GuiAplicacion;
import GUI.AccesoComun.Busqueda;
import GUI.AccesoComun.JCheckBoxScrollableListSelect;
import media.Buscable;
import media.Cancion;
import media.Lista;
import pads.musicPlayer.exceptions.Mp3InvalidFileException;
import pads.musicPlayer.exceptions.Mp3PlayerException;
import aplicacion.Aplicacion;
import excepciones.ExcepcionInsercionInvalida;
import excepciones.ExcepcionLimiteReproducidasAlcanzado;
import excepciones.ExcepcionNoAptoParaMenores;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;
import excepciones.ExcepcionReproducibleNoValido;
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
			
			
			ArrayList<Buscable> buscables = this.getBuscables();
			if(buscables == null) {
				return;
			}
	        try {
				aplicacion.reproducirReproducible(buscables.get(0));
				buscables.remove(0);
		        gui.getReproductor().changeIcon(false);
		        for (Buscable b : buscables) {
		        	try {
						aplicacion.aniadirALaCola(b);
					} catch (ExcepcionUsuarioSinCuenta e1) {
		
					}
		        }
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
				GuiAplicacion.showMessage("Parametros de entrada incorrectos");
			} catch (ExcepcionReproducirProhibido e1) {
				GuiAplicacion.showMessage("Reproducir prohibido");
			}
	        
	        
		}else if(e.getActionCommand().equals("ANIADIRACOLA")){
			
			
			ArrayList<Buscable> buscables = this.getBuscables();
			if(buscables == null) {
				return;
			}
			try {
				for (Buscable b : buscables) {
					aplicacion.aniadirALaCola(b);
				}
				gui.actualizarDatos();
			} catch (Mp3InvalidFileException e1) {
				GuiAplicacion.showMessage("Reproductor no funcionando");
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				GuiAplicacion.showMessage("Parametros de entrada incorrectos");
			} catch (ExcepcionLimiteReproducidasAlcanzado e1) {
				GuiAplicacion.showMessage("Limite de reproducciones alcanzado");
			} catch (ExcepcionNoAptoParaMenores e1) {
				GuiAplicacion.showMessage("No apto para menores");
			} catch (ExcepcionReproducirProhibido e1) {
			} catch (ExcepcionUsuarioSinCuenta e1) {
				GuiAplicacion.showMessage("Debe registrarse para usar la cola");
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
				GuiAplicacion.showMessage("Usuario ya seguido");
			} catch (ExcepcionSeguirseASiMismo e1) {
				GuiAplicacion.showMessage("No puedes seguirte a ti mismo");
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
					GuiAplicacion.showMessage("Parametros de entrada incorrectos");
				} catch (ExcepcionUsuarioSinCuenta e1) {
					GuiAplicacion.showMessage("Debe registrarse para denunciar");
				}
				gui.actualizarDatos();
			}else {
				GuiAplicacion.showMessage("Solo puede denunciar canciones");
			}
			vista.comentarioDenunciaVisible(false);
			
			
		} else if (e.getActionCommand().equals("ANIADIR_A_LISTA")) {
			
			
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
			JTable tabla = vista.getTabla();
			if(tabla.getSelectedRowCount() == 0) return;
			new JCheckBoxScrollableListSelect(
					"Seleccione una lista ",
					GuiAplicacion.getInstance().getPanelesUsuarios().getPanelUsuarioPremium().getPestanias().getMisListas().getNombreListas(),
					null,
					listasSeleccionadas,vista.getAuxAniadirAlista());
			
			
		} else if (e.getActionCommand().equals("AUX_ANIADIR_A_LISTA")) {

			JTable tabla = vista.getTabla();
			DefaultTableModel datos = vista.getDatos();
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
			

		}
	}
	
	/*Devuelve el buscable seleccionado*/
	private Buscable getBuscable() {
		JTable tablaBusqueda = vista.getTabla();
		
        int fila = tablaBusqueda.getSelectedRow();
        if(fila == -1) {
        	return null;
        }
        Buscable b = (Buscable)tablaBusqueda.getModel().getValueAt(fila, 0);
        
        return b;
	}
	
	/*Devuelve los buscables seleccionados*/
	private ArrayList<Buscable> getBuscables(){
		JTable tablaBusqueda = vista.getTabla();
		
		int filas[] = tablaBusqueda.getSelectedRows();
        if(filas.length == 0) {
        	return null;
        }
        ArrayList<Buscable> buscables = new ArrayList<>();
        for (int f : filas) {
        	buscables.add((Buscable)tablaBusqueda.getModel().getValueAt(f, 0));
        }
        
        
        return buscables;
	}
}
