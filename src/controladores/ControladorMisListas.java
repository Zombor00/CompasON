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
import GUI.AccesoComun.JCheckBoxScrollableListSelect;
import GUI.UsuarioPremium.MisListas;
import aplicacion.Aplicacion;
import excepciones.ExcepcionInsercionInvalida;
import excepciones.ExcepcionLimiteReproducidasAlcanzado;
import excepciones.ExcepcionNoAptoParaMenores;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;
import excepciones.ExcepcionReproducibleNoValido;
import excepciones.ExcepcionReproducirProhibido;
import excepciones.ExcepcionUsuarioNoPremium;
import excepciones.ExcepcionUsuarioSinCuenta;
import media.Lista;
import media.Reproducible;
import pads.musicPlayer.exceptions.Mp3InvalidFileException;
import pads.musicPlayer.exceptions.Mp3PlayerException;

/**
 * Esta clase tiene toda la informacion relevante al controlador
 * de mis listas: todas las acciones que puede haber en esa pestania
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class ControladorMisListas implements ActionListener {
	
	/**
	 * Aplicacion con la informacion
	 */
	private Aplicacion aplicacion;
	/**
	 * Pestania de mis canciones donde suceden los eventos
	 */
	private MisListas vista;
	/**
	 * Interfaz grafica de la aplicacion
	 */
	private GuiAplicacion gui;
	
	/**
	 * Constructor del controlador con mis listas
	 * @param misListas panel donde actua el controlador
	 */
	public ControladorMisListas(MisListas misListas) {
		this.vista = misListas;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (aplicacion == null) aplicacion = Aplicacion.getInstance();
		if (gui == null) gui = GuiAplicacion.getInstance();
		
		/* Si se pulsa el opciones de canciones se muestra u oculta su menu */
		if (e.getActionCommand().equals("OPCIONES")) {			
			
			JButton opciones = vista.getOpciones();
			JPopupMenu menu = vista.getMenu();
			menu.show(opciones, 0, opciones.getHeight());
			
		/* Si se pulsa reproducir se reproduce la primera lista seleccionada y las demas se aniaden a la cola */	
		} else if (e.getActionCommand().equals("REPRODUCIR")) {
			
			ArrayList<Lista> listas= this.getSelectedListas();
			if(listas == null)return;
			
	        try {
	        	aplicacion.reproducirReproducible(listas.get(0));
				listas.remove(0);
				gui.getReproductor().setFirstRep(true);
	        	for (Lista l : listas) {
	        		try {
						aplicacion.aniadirALaCola(l);
					} catch (ExcepcionUsuarioSinCuenta e1) {
						
					}
	        	}
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
				GuiAplicacion.showMessage("Lista no reproducible");
			}
	        
	    /* Si se pulsa aceptar se crea la lista con la informacion indicada */
		} else if(e.getActionCommand().equals("ACEPTAR")) {
			
			
			String nombre = vista.getFormularioLista().getNombre();
			ArrayList<Integer> reproduciblesSeleccionados = vista.getFormularioLista().getReproduciblesSeleccionados();
			ArrayList<Integer> aux = null;
			if(nombre.length() == 0) {
				GuiAplicacion.showMessage("Introduce el nombre de la lista");
				return;
			}
			ArrayList<Reproducible> reproducibles = new ArrayList<>();
			int numCanciones = GuiAplicacion.getInstance().getPanelesUsuarios().getPanelUsuarioPremium().getPestanias().
					getMisCanciones().getNombreCanciones().size();
			DefaultTableModel datosCanciones = GuiAplicacion.getInstance().getPanelesUsuarios().
					getPanelUsuarioPremium().getPestanias().getMisCanciones().getDatosCanciones();
			int numAlbumes = GuiAplicacion.getInstance().getPanelesUsuarios().getPanelUsuarioPremium().getPestanias().
					getMisCanciones().getNombreAlbumes().size();
			DefaultTableModel datosAlbumes = GuiAplicacion.getInstance().getPanelesUsuarios().
					getPanelUsuarioPremium().getPestanias().getMisCanciones().getDatosAlbumes();
			int numListas = vista.getNombreListas().size();
			DefaultTableModel datosListas = vista.getDatosListas();
			for(Integer indice : reproduciblesSeleccionados) {
				if (indice >= numCanciones) {
					aux = new ArrayList<Integer>(reproduciblesSeleccionados.subList(
							reproduciblesSeleccionados.indexOf(indice), 
							reproduciblesSeleccionados.size()));
					break;
				}
				reproducibles.add((Reproducible)datosCanciones.getValueAt(indice, 0));
			}
			if(aux != null) {
				for(Integer indice : aux) {
					if (indice >= numCanciones + numAlbumes) {
						aux = new ArrayList<Integer>(aux.subList(aux.indexOf(indice), aux.size()));
						break;
					}
					reproducibles.add((Reproducible)datosAlbumes.getValueAt(indice - numCanciones, 0));
				}
			}
			if(aux != null) {
				for(Integer indice : aux) {
					if (indice >= numCanciones + numAlbumes + numListas) {
						break;
					}
					reproducibles.add((Reproducible)datosListas.getValueAt(indice - numCanciones - numAlbumes, 0));
				}
			}
			try {
				aplicacion.getUsuarioLogeado().crearLista(nombre, reproducibles);
			} catch (ExcepcionUsuarioNoPremium e1) {
				e1.printStackTrace();
			} catch (ExcepcionInsercionInvalida e1) {
				GuiAplicacion.showMessage("Insercion invalida");
			} catch (ExcepcionReproducibleNoValido e1) {
				e1.printStackTrace();
			}
			gui.actualizarDatos();
			
		/* Si se pulsa borrar se borra la lista de la aplicacion */	
		} else if(e.getActionCommand().equals("BORRAR")) {
			
			
			JTable tablaListas = vista.getTabla();
	        int fila = tablaListas.getSelectedRow();
	        if(fila == -1) {
	        	return;
	        }
	        Lista l = (Lista)tablaListas.getModel().getValueAt(fila, 0);
	        aplicacion.getUsuarioLogeado().borrarLista(l);
	        gui.actualizarDatos();
	        
	    /* Si se pulsa aniadir a la cola se aniaden todas las listas seleccionadas */
		} else if(e.getActionCommand().equals("ANIADIR_COLA")) {
			
			ArrayList<Lista> listas = this.getSelectedListas();
			if(listas == null) {
				return;
			}
			try {
				for (Lista l : listas) {
					aplicacion.aniadirALaCola(l);
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
			
		/* Si se pulsa aniadir a lista aparecen las listas en las que se puede aniadir la seleccionada */
		} else if(e.getActionCommand().equals("ANIADIR_A_LISTA")) {
			
			
			ArrayList<Integer> listasSeleccionadas = vista.getListasSeleccionadas();
			listasSeleccionadas.clear();
			JTable tablaCanciones = vista.getTabla();
			if(tablaCanciones.getSelectedRowCount() == 0) return;
			@SuppressWarnings("unused")
			JCheckBoxScrollableListSelect checkBoxScrollableListSelect = new JCheckBoxScrollableListSelect(
					"Seleccione una lista",
					vista.getNombreListas(),
					null,
					listasSeleccionadas,vista.getAuxAniadirALista());
			
		/* Si se confirma, se aniade la lista seleccionada inicialmente a todas las seleccionadas despues */
		} else if(e.getActionCommand().equals("AUX_ANIADIR_A_LISTA")) {
			
			
			ArrayList<Integer> listasSeleccionadas = vista.getListasSeleccionadas();
			DefaultTableModel datos = vista.getDatosListas();
			JTable tabla = vista.getTabla();
			if(listasSeleccionadas.size() == 0) return;
			Lista lista = ((Lista)(datos.getValueAt(listasSeleccionadas.get(0), 0)));
			for(Integer indice : tabla.getSelectedRows()) {
				try {
					lista.aniadirReproducible((Reproducible)datos.getValueAt(indice, 0));
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
	 * Metodo que devuelve las listas seleccionados
	 * @return lista de listas seleccionados
	 */
	private ArrayList<Lista> getSelectedListas(){
		JTable tablaListas = vista.getTabla();
		
		int filas[] = tablaListas.getSelectedRows();
        if(filas.length == 0) {
        	return null;
        }
        ArrayList<Lista> listas = new ArrayList<>();
        for (int f : filas) {
        	listas.add((Lista)tablaListas.getModel().getValueAt(f, 0));
        }        
        
        return listas;
	}

}
