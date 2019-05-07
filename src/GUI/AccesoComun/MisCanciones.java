package GUI.AccesoComun;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import GUI.GuiAplicacion;
import aplicacion.Aplicacion;
import media.Album;
import media.Cancion;
import media.EstadoValidacion;
import usuarios.UsuarioRegistrado;

public class MisCanciones extends JPanel{
	
	/**
	 * Lista con los nombres de las canciones del usuario
	 */
	private ArrayList<String> nombreCanciones = new ArrayList<String>();
	
	/**
	 * Lista con el nombre de los albumes del usuario
	 */
	private ArrayList<String> nombreAlbumes = new ArrayList<String>();
	
	/**
	 * Modelo con los datos de las canciones
	 */
	private DefaultTableModel datosCanciones;
	
	/**
	 * Tabla con el modelo de datos de las canciones
	 */
	private JTable tablaCanciones;
	
	/**
	 * Modelo con los datos de los albumes
	 */
	private DefaultTableModel datosAlbumes;
	
	/**
	 * Tabla con el modelo de datos de los albumes
	 */
	private JTable tablaAlbumes;
	
	/**
	 * Menu de opciones para las canciones y los albumes
	 */
	private JPopupMenu menuCanciones, menuAlbumes;
	
	/**
	 * Opciones para las canciones
	 */
    private JMenuItem reproducirCancion,aniadirCancionACola,aniadirCancionALista,aniadirCancionAAlbum,borrarCancion,modificarCancion;
    
    /**
     * Opciones para los albumes
     */
    private JMenuItem reproducirAlbum,aniadirAlbumACola,aniadirAlbumALista,borrarAlbum,visualizarAlbum;
    
    /**
     * Botones que abren los menus
     */
    private JButton opcionesCanciones,opcionesAlbumes;
    
    /**
     * Formulario para crear un album
     */
    private FormularioAlbum formularioAlbum;
    
    /**
     * Confirmacion previa a crear el album a partir de los datos introducidos en formularioAlbum
     */
    private JButton aceptarAlbum;
    
    /**
     * Formulario para crear una cancion
     */
    private FormularioCancion formularioCancion;
    
    /**
     * Confirmacion previa a crear una cancion a partir de los datos introducidos en formularioCancion
     */
    private JButton aceptarCancion;
    
    /**
     * Permite almacenar los albumes que el usuario ha seleccionado tras pulsar menuCanciones.aniadirCancionAAlbum
     */
    private ArrayList<Integer> albumesSeleccionados = new ArrayList<>();
    
    /**
     * Boton auxiliar que no se muestra por pantalla que sirve para gestionar la adicion de canciones a albumes
     */
    private JButton auxAniadirCancionAAlbum = new JButton();
    
    /**
     * Permite almacenar las listas que el usuario ha seleccionado tras pulsar aniadirCancionALista/aniadirAlbumALista
     */
    private ArrayList<Integer> listasSeleccionadas = new ArrayList<>();
    
    /**
     * Boton auxiliar que no se muestra por pantalla que sirve para gestionar la adicion de canciones a listas
     */
    private JButton auxAniadirCancionALista = new JButton();
    
    /**
     * Boton auxiliar que no se muestra por pantalla que sirve para gestionar la adicion de albumes a listas
     */
    private JButton auxAniadirAlbumALista = new JButton();
    
    /**
     * Formulario para modifcar una cancion
     */
    FormularioModificarCancion formularioModificarCancion = new FormularioModificarCancion();
    
    /**
     * Confirmacion previa a modificar una cancion a partir de los datos introducidos en formularioModificarCancion
     */
    private JButton aceptarModificar = new JButton("Modificar");
	
	public MisCanciones() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		/* Canciones */
		JButton canciones = new JButton("Canciones");		
		String[] titulos = {"Objeto","Cancion", "Duracion"};
		Object[][] filas = {
		{"","Cancion 1", "00:00"},
		};
		datosCanciones = new DefaultTableModelNoEditable(filas, titulos);
		tablaCanciones = new JTable(datosCanciones);
		TableColumnModel tcm = tablaCanciones.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
		tablaCanciones.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tablaCanciones.setPreferredScrollableViewportSize(new Dimension(800, 500));
		JScrollPane scrollTablaCanciones = new JScrollPane(tablaCanciones);
		
		/* Albumes */
		JButton albumes = new JButton("Albumes");
		String[] titulos2 = {"Objeto","Album", "Duracion"};
		Object[][] filas2 = {
		{"","Album 1", "00:00"},
		};
		datosAlbumes = new DefaultTableModel(filas2, titulos2);
		tablaAlbumes = new JTable(datosAlbumes);
		tcm = tablaAlbumes.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
		tablaAlbumes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tablaAlbumes.setPreferredScrollableViewportSize(new Dimension(800, 500));
		JScrollPane scrollTablaAlbumes = new JScrollPane(tablaAlbumes);
		
		/* Subir cancion */
		JButton subirCancion = new JButton("Subir cancion");
		formularioCancion = new FormularioCancion();
		aceptarCancion = new JButton("Aceptar");
		
		/* Crear album */
		JButton subirAlbum = new JButton("Crear album");
		formularioAlbum = new FormularioAlbum(this.nombreCanciones);
		aceptarAlbum = new JButton("Aceptar");
		
		

		/* Colocamos los elementos */
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollTablaCanciones, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollTablaAlbumes, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, formularioCancion, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, formularioModificarCancion, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, formularioAlbum, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, albumes, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.WEST, canciones, 0, SpringLayout.WEST, scrollTablaCanciones);
		layout.putConstraint(SpringLayout.EAST, subirCancion, 0, SpringLayout.EAST, scrollTablaCanciones);
		layout.putConstraint(SpringLayout.EAST, subirAlbum, 0, SpringLayout.EAST, scrollTablaCanciones);
		layout.putConstraint(SpringLayout.EAST, aceptarAlbum, 0, SpringLayout.EAST, subirAlbum);
		layout.putConstraint(SpringLayout.EAST, aceptarCancion, 0, SpringLayout.EAST, subirAlbum);
		layout.putConstraint(SpringLayout.EAST, aceptarModificar, 0, SpringLayout.EAST, subirAlbum);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, scrollTablaCanciones, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, scrollTablaAlbumes, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, formularioCancion, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, formularioModificarCancion, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, formularioAlbum, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, canciones, 0, SpringLayout.NORTH, scrollTablaCanciones);
		layout.putConstraint(SpringLayout.SOUTH, albumes, 0, SpringLayout.NORTH, scrollTablaCanciones);
		layout.putConstraint(SpringLayout.SOUTH, subirCancion, 0, SpringLayout.NORTH, scrollTablaCanciones);
		layout.putConstraint(SpringLayout.SOUTH, subirAlbum, 0, SpringLayout.NORTH, scrollTablaCanciones);
		layout.putConstraint(SpringLayout.NORTH, aceptarAlbum, 0, SpringLayout.SOUTH, formularioAlbum);
		layout.putConstraint(SpringLayout.NORTH, aceptarCancion, 0, SpringLayout.SOUTH, formularioAlbum);
		layout.putConstraint(SpringLayout.NORTH, aceptarModificar, 0, SpringLayout.SOUTH, formularioAlbum);
		
		
		this.add(aceptarCancion);
		aceptarCancion.setVisible(false);
		this.add(aceptarAlbum);
		aceptarAlbum.setVisible(false);
		this.add(canciones);
		this.add(scrollTablaCanciones);
		this.add(albumes);
		this.add(scrollTablaAlbumes);
		scrollTablaAlbumes.setVisible(false);
		this.add(subirCancion);
		this.add(subirAlbum);
		this.add(formularioCancion);
		formularioCancion.setVisible(false);
		this.add(formularioAlbum);
		formularioAlbum.setVisible(false);
		this.add(formularioModificarCancion);
		formularioModificarCancion.setVisible(false);
		this.add(aceptarModificar);
		aceptarModificar.setVisible(false);
		
		/* Configuramos el boton "albumes" */
		albumes.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						scrollTablaCanciones.setVisible(false);
						formularioCancion.setVisible(false);
						scrollTablaAlbumes.setVisible(true);
						subirCancion.setVisible(false);
						subirAlbum.setVisible(true);
						opcionesCanciones.setVisible(false);
						opcionesAlbumes.setVisible(true);
						aceptarAlbum.setVisible(false);
						aceptarCancion.setVisible(false);
						aceptarModificar.setVisible(false);
						formularioModificarCancion.setVisible(false);
					}
				});
		
		/* Configuramos el boton "canciones" */
		canciones.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						scrollTablaCanciones.setVisible(true);
						formularioCancion.setVisible(false);
						scrollTablaAlbumes.setVisible(false);
						subirCancion.setVisible(true);
						subirAlbum.setVisible(false);
						opcionesCanciones.setVisible(true);
						opcionesAlbumes.setVisible(false);
						aceptarAlbum.setVisible(false);
						aceptarCancion.setVisible(false);
						aceptarModificar.setVisible(false);
						formularioModificarCancion.setVisible(false);
					}
				});
		
		/* Configuramos el boton "subirCancion" */
		subirCancion.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						scrollTablaCanciones.setVisible(false);
						formularioCancion.setVisible(true);
						scrollTablaAlbumes.setVisible(false);
						opcionesCanciones.setVisible(false);
						opcionesAlbumes.setVisible(false);
						aceptarAlbum.setVisible(false);
						aceptarCancion.setVisible(true);
						aceptarModificar.setVisible(false);
						formularioModificarCancion.setVisible(false);
					}
				});
		
		/* Configuramos el boton "subirAlbum" */
		subirAlbum.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						scrollTablaCanciones.setVisible(false);
						formularioAlbum.setVisible(true);
						scrollTablaAlbumes.setVisible(false);
						opcionesCanciones.setVisible(false);
						opcionesAlbumes.setVisible(false);
						aceptarAlbum.setVisible(true);
						aceptarCancion.setVisible(false);
						aceptarModificar.setVisible(false);
						formularioModificarCancion.setVisible(false);
					}
				});
		
		/* Aniadimos el menu y el boton de opciones para las canciones */        
		menuCanciones = new JPopupMenu();
		reproducirCancion = new JMenuItem("Reproducir");
        menuCanciones.add(reproducirCancion);
        aniadirCancionACola = new JMenuItem("Añadir a la cola");
        menuCanciones.add(aniadirCancionACola);
        aniadirCancionALista = new JMenuItem("Añadir a una lista");
        menuCanciones.add(aniadirCancionALista);
        aniadirCancionAAlbum = new JMenuItem("Añadir a un album");
        menuCanciones.add(aniadirCancionAAlbum);
        modificarCancion = new JMenuItem("Modificar cancion");
        menuCanciones.add(modificarCancion);
        borrarCancion = new JMenuItem("Borrar cancion");
        menuCanciones.add(borrarCancion);
        opcionesCanciones = new JButton("Opciones"); 
        layout.putConstraint(SpringLayout.NORTH, opcionesCanciones, 0, SpringLayout.NORTH, scrollTablaCanciones);
        layout.putConstraint(SpringLayout.WEST, opcionesCanciones, 0, SpringLayout.EAST, scrollTablaCanciones);
        this.add(opcionesCanciones);
        
        /* Aniadimos el menu y el boton de opciones para los albumes */
        menuAlbumes=new JPopupMenu();
        reproducirAlbum=new JMenuItem("Reproducir");
        menuAlbumes.add(reproducirAlbum);
        aniadirAlbumACola=new JMenuItem("Añadir a la cola");
        menuAlbumes.add(aniadirAlbumACola);
        aniadirAlbumALista = new JMenuItem("Añadir a una lista");
        menuAlbumes.add(aniadirAlbumALista);
        borrarAlbum = new JMenuItem("Borrar album");
        menuAlbumes.add(borrarAlbum);
        visualizarAlbum = new JMenuItem("Visualizar");
        menuAlbumes.add(visualizarAlbum);
        opcionesAlbumes = new JButton("Opciones"); 
        layout.putConstraint(SpringLayout.NORTH, opcionesAlbumes, 0, SpringLayout.NORTH, scrollTablaAlbumes);
        layout.putConstraint(SpringLayout.WEST, opcionesAlbumes, 0, SpringLayout.EAST, scrollTablaAlbumes);
        this.add(opcionesAlbumes);
        
        /* Configuramos la opcion modificarCancion */
        modificarCancion.addActionListener((
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Cancion c = MisCanciones.this.getSelectedCancion();
						if (c == null) return;
						if(c.getModificableHasta().isBefore(LocalDate.now())) {
							GuiAplicacion.showMessage("La cancion ya no se puede modificar");
							return;
						}
						scrollTablaCanciones.setVisible(false);
						formularioAlbum.setVisible(false);
						scrollTablaAlbumes.setVisible(false);
						opcionesCanciones.setVisible(false);
						opcionesAlbumes.setVisible(false);
						aceptarAlbum.setVisible(false);
						aceptarCancion.setVisible(false);
						formularioModificarCancion.setNombre(c.getTitulo());
						formularioModificarCancion.setFichero(c.getFicheroAudio());
						formularioModificarCancion.setVisible(true);
						aceptarModificar.setVisible(true);
					}
				}));
	}
	
	public JTable getTablaCanciones() {
		return this.tablaCanciones;
	}
	
	public JTable getTablaAlbumes() {
		return this.tablaAlbumes;
	}
	
	public JButton getOpcionesCanciones() {
		return this.opcionesCanciones;
	}
	
	public JPopupMenu getMenuCanciones() {
		return this.menuCanciones;	
	}
	
	public JButton getOpcionesAlbumes() {
		return this.opcionesAlbumes;
	}
	
	public JPopupMenu getMenuAlbumes() {
		return this.menuAlbumes;	
	}
	
	public ArrayList<String> getNombreAlbumes() {
		return this.nombreAlbumes;
	}
	
	public ArrayList<String> getNombreCanciones() {
		return this.nombreCanciones;
	}
	
	public FormularioAlbum getFormularioAlbum() {
		return this.formularioAlbum;
	}
	
	public FormularioCancion getFormularioCancion() {
		return this.formularioCancion;
	}
	
	public DefaultTableModel getDatosAlbumes() {
		return this.datosAlbumes;
	}
	
	public DefaultTableModel getDatosCanciones() {
		return this.datosCanciones;
	}
	
	public JButton getAuxAniadirCancionAAlbum() {
		return this.auxAniadirCancionAAlbum;
	}
	
	public ArrayList<Integer> getAlbumesSeleccionados(){
		return this.albumesSeleccionados;
	}
	
	public ArrayList<Integer> getListasSeleccionadas() {
		return this.listasSeleccionadas;
	}
	
	public JButton getAuxAniadirCancionALista() {
		return this.auxAniadirCancionALista;
	}
	
	public JButton getAuxAniadirAlbumALista() {
		return this.auxAniadirAlbumALista;
	}
	
	public FormularioModificarCancion getFormularioModificarCancion() {
		return this.formularioModificarCancion;
	}
	
	public Cancion getSelectedCancion() {
		JTable tablaCanciones = this.getTablaCanciones();
        int fila = tablaCanciones.getSelectedRow();
        if(fila == -1) {
        	return null;
        }
        return (Cancion)tablaCanciones.getModel().getValueAt(fila, 0);
	}
	
	public void actualizarDatos() {
		int numFilas = datosCanciones.getRowCount();
		for(int i=0; i< numFilas; i++) {
			datosCanciones.removeRow(0);
		}
		
		numFilas = datosAlbumes.getRowCount();
		for(int i=0; i< numFilas; i++) {
			datosAlbumes.removeRow(0);
		}
		
		nombreCanciones.clear();
		nombreAlbumes.clear();
		albumesSeleccionados.clear();
		
		UsuarioRegistrado u = Aplicacion.getInstance().getUsuarioLogeado();
		if (u==null) return;
		Object[] rowData = {0,0,0};
		String aux = "";
		for (Cancion c : u.getCanciones()) {
			rowData[0] = c;
			rowData[1] = c.getTituloExplicito();
			rowData[2] = c.parseSeconds(c.getDuracion());
			datosCanciones.addRow(rowData);
			nombreCanciones.add(c.getTitulo());
		}
		for (Cancion c : u.getCancionesNuevas()) {
			rowData[0] = c;
			if (c.getEstadoValidacion() == EstadoValidacion.NOVALIDADA && c.getModificableHasta() != null) {
				rowData[1] = c.getTitulo() + " (Sin validar. Modificable hasta: " + c.getModificableHasta() + ")" ;
			}
			else {
				rowData[1] = c.getTitulo() + " (En proceso de validacion)";
			}
			rowData[2] = c.parseSeconds(c.getDuracion());
			datosCanciones.addRow(rowData);
			nombreCanciones.add(c.getTitulo());
		}
		for (Album a : u.getAlbumes()) {
			rowData[0] = a;
			rowData[1] = a.getTituloExplicito();
			rowData[2] = a.parseSeconds(a.getDuracion());
			datosAlbumes.addRow(rowData);
			nombreAlbumes.add(a.getTitulo());
		}
		this.formularioAlbum.actualizarDatos();
		this.formularioCancion.actualizarDatos();
		this.formularioModificarCancion.actualizarDatos();
	}
	
	public void setControlador(ActionListener controlador) {
		opcionesCanciones.setActionCommand("OPCIONES_CANCIONES");
		opcionesCanciones.addActionListener(controlador);
		opcionesAlbumes.setActionCommand("OPCIONES_ALBUMES");
		opcionesAlbumes.addActionListener(controlador);
		reproducirCancion.setActionCommand("REPRODUCIR_CANCION");
		reproducirCancion.addActionListener(controlador);
		reproducirAlbum.setActionCommand("REPRODUCIR_ALBUM");
		reproducirAlbum.addActionListener(controlador);
		aniadirCancionAAlbum.setActionCommand("ANIADIR_CANCION_A_ALBUM");
		aniadirCancionAAlbum.addActionListener(controlador);
		aceptarAlbum.setActionCommand("ACEPTAR_ALBUM");
		aceptarAlbum.addActionListener(controlador);
		aceptarCancion.setActionCommand("ACEPTAR_CANCION");
		aceptarCancion.addActionListener(controlador);
		auxAniadirCancionAAlbum.setActionCommand("AUX_ANIADIR_CANCION_A_ALBUM");
		auxAniadirCancionAAlbum.addActionListener(controlador);
		aniadirCancionALista.setActionCommand("ANIADIR_CANCION_A_LISTA");
		aniadirCancionALista.addActionListener(controlador);
		aniadirAlbumALista.setActionCommand("ANIADIR_ALBUM_A_LISTA");
		aniadirAlbumALista.addActionListener(controlador);
		borrarCancion.setActionCommand("BORRAR_CANCION");
		borrarCancion.addActionListener(controlador);
		borrarAlbum.setActionCommand("BORRAR_ALBUM");
		borrarAlbum.addActionListener(controlador);
		aniadirCancionACola.setActionCommand("ANIADIR_CANCION_COLA");
		aniadirCancionACola.addActionListener(controlador);
		aniadirAlbumACola.setActionCommand("ANIADIR_ALBUM_COLA");
		aniadirAlbumACola.addActionListener(controlador);
		auxAniadirCancionALista.setActionCommand("AUX_ANIADIR_CANCION_A_LISTA");
		auxAniadirCancionALista.addActionListener(controlador);
		auxAniadirAlbumALista.setActionCommand("AUX_ANIADIR_ALBUM_A_LISTA");
		auxAniadirAlbumALista.addActionListener(controlador);
		aceptarModificar.setActionCommand("ACEPTAR_MODIFICAR");
		aceptarModificar.addActionListener(controlador);
		visualizarAlbum.setActionCommand("VISUALIZAR_ALBUM");
		visualizarAlbum.addActionListener(controlador);
	}

}
