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

/**
 * Esta clase tiene toda la informacion relevante al controlador
 * de busqueda: todas las acciones que puede haber en esa pestania
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class ControladorBusqueda implements ActionListener {
	
	/**
	 * Aplicacion con la informacion
	 */
	private Aplicacion aplicacion;
	/**
	 * Busqueda donde suceden los eventos
	 */
	private Busqueda vista;
	/**
	 * Interfaz grafica de la aplicacion
	 */
	private GuiAplicacion gui;
	
	/**
	 * Constructor del controlador con busqueda
	 * @param busqueda panel donde actua el controlador
	 */
	public ControladorBusqueda(Busqueda busqueda) {
		this.vista = busqueda;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (aplicacion == null) aplicacion = Aplicacion.getInstance();
		if (gui == null) gui = GuiAplicacion.getInstance();
		ArrayList<Buscable> buscable = null;
		
		/* Si se pulsa a buscar se busca por autor o titulo segun lo indicado */
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
			
		/* Si se pulsa opciones se despliega u oculta el menu */
		} else if (e.getActionCommand().equals("OPCIONES")) {
						
			JButton opciones = vista.getOpciones();
			JPopupMenu menu = vista.getMenu();
			menu.show(opciones, 0, opciones.getHeight());
			
		/* Si se pulsa reproducir, la primera seleccionada se reproduce y las demas se
		 * aniaden a la cola, se actualiza tambien la informacion del reproductor
		 */
		} else if (e.getActionCommand().equals("REPRODUCIR")) {
			
			
			ArrayList<Buscable> buscables = this.getBuscables();
			if(buscables == null) {
				return;
			}
	        try {
				aplicacion.reproducirReproducible(buscables.get(0));
				buscables.remove(0);
		        gui.getReproductor().changeIcon(false);
		        gui.getReproductor().setFirstRep(true);
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
				GuiAplicacion.showMessage("Elemento no reproducible");
			}
	        
	    /* Si se pulsa aniadir a la cola se aniaden todas las seleccionadas */
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
			
		/* Si se pulsa seguir al autor se sigue al autor del elemento seleccionado */
		}else if(e.getActionCommand().equals("SEGUIRAUTOR")) {
			
			
			Buscable b = this.getBuscable();
			if(b == null) {
				GuiAplicacion.showMessage("Selecciona un elemento para seguir a su autor");
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
			
		/* Si se pulsa denunciar se muestra un panel para rellenar un comentario con la denuncia */
		} else if(e.getActionCommand().equals("DENUNCIAR")) {
			
			
			vista.comentarioDenunciaVisible(true);
			
		/* Si se da a enviar denuncia se denuncia la cancion seleccionada con el comentario escrito */
		}else if(e.getActionCommand().equals("ENVIAR_DENUNCIA")) {
			
			
			Buscable b = this.getBuscable();
			if(b == null) {
				GuiAplicacion.showMessage("Selecciona la canción que quieras denunciar");
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
				GuiAplicacion.showMessage("Solo se pueden denunciar canciones");
			}
			vista.comentarioDenunciaVisible(false);
			
		/* Si se pulsa aniadir a lista aparece la opcion de elegir a que listas aniadir el elemento seleccionado */
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
			
		/* Al confirmar se aniade a las listas seleccionadas el elemento seleccionado previamente */
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
	
	/**
	 * Metodo que devuelve el buscable seleccionado en la tabla
	 * @return buscable seleccionado
	 */
	private Buscable getBuscable() {
		JTable tablaBusqueda = vista.getTabla();
		
        int fila = tablaBusqueda.getSelectedRow();
        if(fila == -1) {
        	return null;
        }
        Buscable b = (Buscable)tablaBusqueda.getModel().getValueAt(fila, 0);
        
        return b;
	}
	
	/**
	 * Metodo que devuelve los buscables seleccionado en la tabla
	 * @return lista de buscables seleccionado
	 */
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
