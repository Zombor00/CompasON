package GUI.AccesoComun;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import aplicacion.Aplicacion;
import media.Album;
import media.Cancion;
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
    private JMenuItem reproducirCancion,aniadirCancionACola,aniadirCancionALista,aniadirCancionAAlbum;
    
    /**
     * Opciones para los albumes
     */
    private JMenuItem reproducirAlbum,aniadirAlbumACola,aniadirAlbumALista;
    
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
     * Confirmacion previa s crear una cancion a partir de los datos introducidos en formularioCancion
     */
    private JButton aceptarCancion;
    
    /**
     * Permite almacenar los albumes que el usuario ha seleccionado tras pulsar menuCanciones.aniadirCancionAAlbum
     */
    ArrayList<Integer> albumesSeleccionados = new ArrayList<>();
    
    /**
     * Boton auxiliar que no se muestra por pantalla que sirve para gestionar la adicion de canciones a albumes
     */
    private JButton auxAniadirCancionAAlbum = new JButton();
	
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
		
		/* Subir album */
		JButton subirAlbum = new JButton("Subir album");
		formularioAlbum = new FormularioAlbum(this.nombreCanciones);
		aceptarAlbum = new JButton("Aceptar");
		
		

		/* Colocamos los elementos */
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollTablaCanciones, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollTablaAlbumes, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, formularioCancion, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, formularioAlbum, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, albumes, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.WEST, canciones, 0, SpringLayout.WEST, scrollTablaCanciones);
		layout.putConstraint(SpringLayout.EAST, subirCancion, 0, SpringLayout.EAST, scrollTablaCanciones);
		layout.putConstraint(SpringLayout.EAST, subirAlbum, 0, SpringLayout.EAST, scrollTablaCanciones);
		layout.putConstraint(SpringLayout.EAST, aceptarAlbum, 0, SpringLayout.EAST, subirAlbum);
		layout.putConstraint(SpringLayout.EAST, aceptarCancion, 0, SpringLayout.EAST, subirAlbum);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, scrollTablaCanciones, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, scrollTablaAlbumes, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, formularioCancion, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, formularioAlbum, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, canciones, 0, SpringLayout.NORTH, scrollTablaCanciones);
		layout.putConstraint(SpringLayout.SOUTH, albumes, 0, SpringLayout.NORTH, scrollTablaCanciones);
		layout.putConstraint(SpringLayout.SOUTH, subirCancion, 0, SpringLayout.NORTH, scrollTablaCanciones);
		layout.putConstraint(SpringLayout.SOUTH, subirAlbum, 0, SpringLayout.NORTH, scrollTablaCanciones);
		layout.putConstraint(SpringLayout.NORTH, aceptarAlbum, 0, SpringLayout.SOUTH, formularioAlbum);
		layout.putConstraint(SpringLayout.NORTH, aceptarCancion, 0, SpringLayout.SOUTH, formularioAlbum);
		
		
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
						aceptarAlbum.setVisible(false);
						aceptarCancion.setVisible(true);
					}
				});
		
		/* Configuramos el boton "subirAlbum" */
		subirAlbum.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						scrollTablaCanciones.setVisible(false);
						formularioAlbum.setVisible(true);
						scrollTablaAlbumes.setVisible(false);
						opcionesAlbumes.setVisible(false);
						aceptarAlbum.setVisible(true);
						aceptarCancion.setVisible(false);
					}
				});
		
		/* Aniadimos el menu y el boton de opciones para las canciones*/        
		menuCanciones = new JPopupMenu();
		reproducirCancion = new JMenuItem("Reproducir");
        menuCanciones.add(reproducirCancion);
        aniadirCancionACola = new JMenuItem("Aniaidr a la cola");
        menuCanciones.add(aniadirCancionACola);
        aniadirCancionALista = new JMenuItem("Aniaidr a una lista");
        menuCanciones.add(aniadirCancionALista);
        aniadirCancionAAlbum = new JMenuItem("Aniadir a un album");
        menuCanciones.add(aniadirCancionAAlbum);
        opcionesCanciones = new JButton("Opciones"); 
        layout.putConstraint(SpringLayout.NORTH, opcionesCanciones, 0, SpringLayout.NORTH, scrollTablaCanciones);
        layout.putConstraint(SpringLayout.WEST, opcionesCanciones, 0, SpringLayout.EAST, scrollTablaCanciones);
        this.add(opcionesCanciones);
        
        /* Aniadimos el menu y el boton de opciones para los albumes */
        menuAlbumes=new JPopupMenu();
        reproducirAlbum=new JMenuItem("Reproducir");
        menuAlbumes.add(reproducirAlbum);
        aniadirAlbumACola=new JMenuItem("Aniadir a la cola");
        menuAlbumes.add(aniadirAlbumACola);
        aniadirAlbumALista = new JMenuItem("Aniadir a una lista");
        menuAlbumes.add(aniadirAlbumALista);
        opcionesAlbumes = new JButton("Opciones"); 
        layout.putConstraint(SpringLayout.NORTH, opcionesAlbumes, 0, SpringLayout.NORTH, scrollTablaAlbumes);
        layout.putConstraint(SpringLayout.WEST, opcionesAlbumes, 0, SpringLayout.EAST, scrollTablaAlbumes);
        this.add(opcionesAlbumes);
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
		for (Cancion c : u.getCanciones()) {
			rowData[0] = c;
			rowData[1] = c.getTitulo();
			rowData[2] = c.parseSeconds(c.getDuracion());
			datosCanciones.addRow(rowData);
			nombreCanciones.add(c.getTitulo());
		}
		for (Album a : u.getAlbumes()) {
			rowData[0] = a;
			rowData[1] = a.getTitulo();
			rowData[2] = a.parseSeconds(a.getDuracion());
			datosAlbumes.addRow(rowData);
			nombreAlbumes.add(a.getTitulo());
		}
		this.formularioAlbum.actualizarDatos();
		this.formularioCancion.actualizarDatos();
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
	}

}
