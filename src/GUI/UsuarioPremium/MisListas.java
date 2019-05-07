package GUI.UsuarioPremium;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import GUI.AccesoComun.DefaultTableModelNoEditable;
import aplicacion.Aplicacion;
import media.Lista;
import usuarios.UsuarioRegistrado;

public class MisListas extends JPanel{
	
	/**
	 * Lista con los nombres de las listas del usuario
	 */
	private ArrayList<String> nombreListas = new ArrayList<String>();
	
	/**
	 * Modelo con los datos de las listas
	 */
	private DefaultTableModel datosListas;
	
	/**
	 * Tabla con el modelo de datos
	 */
	private JTable tablaListas;
	
	/**
	 * Menu de opciones
	 */
	private JPopupMenu menu;
	
	/**
	 * Opciones
	 */
    private JMenuItem reproducir,borrar,aniadirCola,aniadirALista;
    
    /**
     * Muestra las opciones
     */
    private JButton opciones;
    
    /**
     * Para abrir el formulario
     */
    private JButton crearLista;
    
    /**
     * Para crear una nueva lista
     */
    private FormularioLista formularioLista;
    
    /**
     * Confirmacion previa a la creacion de una nueva lista
     */
    private JButton aceptar;
    
    /**
     * Permite almacenar las listas que el usuario ha seleccionado tras pulsar aniadirALista
     */
    private ArrayList<Integer> listasSeleccionadas = new ArrayList<>();
    
    /**
     * Boton auxiliar que no se muestra por pantalla que sirve para gestionar la adicion de listas a listas
     */
    private JButton auxAniadirALista = new JButton();
	
    /**
     * Constructor de la clase
     */
	public MisListas() {
		
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		/* Canciones */	
		String[] titulos = {"Objeto","Lista", "Duracion"};
		Object[][] filas = {
		};
		datosListas = new DefaultTableModelNoEditable(filas, titulos);
		tablaListas = new JTable(datosListas);
		TableColumnModel tcm = tablaListas.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
		tablaListas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tablaListas.setPreferredScrollableViewportSize(new Dimension(800, 500));
		JScrollPane scrollTablaListas = new JScrollPane(tablaListas);
		crearLista = new JButton("Crear lista");
		JButton listas = new JButton("Listas");
		formularioLista = new FormularioLista();
		formularioLista.setVisible(false);
		
		aceptar = new JButton("Aceptar");
		aceptar.setVisible(true);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollTablaListas, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, scrollTablaListas, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.EAST, crearLista, 0, SpringLayout.EAST, scrollTablaListas);
		layout.putConstraint(SpringLayout.SOUTH, crearLista, 0, SpringLayout.NORTH, scrollTablaListas);
		layout.putConstraint(SpringLayout.WEST, listas, 0, SpringLayout.WEST, scrollTablaListas);
		layout.putConstraint(SpringLayout.SOUTH, listas, 0, SpringLayout.NORTH, scrollTablaListas);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, formularioLista, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, formularioLista, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, aceptar, 0, SpringLayout.SOUTH, formularioLista);
		layout.putConstraint(SpringLayout.EAST, aceptar, 0, SpringLayout.EAST, crearLista);
		
		/* Configuramos el boton "Listas" */
		listas.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						scrollTablaListas.setVisible(true);
						opciones.setVisible(true);
						formularioLista.setVisible(false);
						aceptar.setVisible(false);
					}
				});
		
		/* COonfiguramos el boton "crearLista" */
		crearLista.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						scrollTablaListas.setVisible(false);
						opciones.setVisible(false);
						formularioLista.setVisible(true);
						aceptar.setVisible(true);
					}
				});
		
		this.add(scrollTablaListas);
		this.add(crearLista);
		this.add(listas);
		this.add(formularioLista);
		this.add(aceptar);
		
		/* Aniadimos el menu y el boton de opciones */        
        menu = new JPopupMenu();
        reproducir = new JMenuItem("Reproducir");
        menu.add(reproducir);
        aniadirCola = new JMenuItem("Añadir a la cola");
        menu.add(aniadirCola);
        aniadirALista = new JMenuItem("Añadir a lista");
        menu.add(aniadirALista);
        borrar = new JMenuItem("Borrar");
        menu.add(borrar);
        opciones = new JButton("Opciones"); 
        layout.putConstraint(SpringLayout.NORTH, opciones, 0, SpringLayout.NORTH, scrollTablaListas);
        layout.putConstraint(SpringLayout.WEST, opciones, 0, SpringLayout.EAST, scrollTablaListas);
        this.add(opciones);
		
	}
	
	public JTable getTabla() {
		return this.tablaListas;
	}
	
	public JButton getOpciones() {
		return this.opciones;
	}
	
	public JPopupMenu getMenu() {
		return this.menu;	
	}
	
	public ArrayList<String> getNombreListas() {
		return this.nombreListas;
	}
	
	public DefaultTableModel getDatosListas() {
		return this.datosListas;
	}
	
	public FormularioLista getFormularioLista() {
		return this.formularioLista;
	}
	
	public ArrayList<Integer> getListasSeleccionadas() {
		return this.listasSeleccionadas;
	}
	
	public JButton getAuxAniadirALista() {
		return this.auxAniadirALista;
	}
	
	public void setControlador(ActionListener controlador) {
		opciones.setActionCommand("OPCIONES");
		opciones.addActionListener(controlador);
		reproducir.setActionCommand("REPRODUCIR");
		reproducir.addActionListener(controlador);
		borrar.setActionCommand("BORRAR");
		borrar.addActionListener(controlador);
		aniadirCola.setActionCommand("ANIADIR_COLA");
		aniadirCola.addActionListener(controlador);
		aceptar.setActionCommand("ACEPTAR");
		aceptar.addActionListener(controlador);
		aniadirALista.setActionCommand("ANIADIR_A_LISTA");
		aniadirALista.addActionListener(controlador);
		auxAniadirALista.setActionCommand("AUX_ANIADIR_A_LISTA");
		auxAniadirALista.addActionListener(controlador);
	}
	
	/**
	 * Actualiza los datos del panel
	 */
	public void actualizarDatos() {
		int numFilas = datosListas.getRowCount();
		for(int i=0; i< numFilas; i++) {
			datosListas.removeRow(0);
		}
		nombreListas.clear();
		UsuarioRegistrado u = Aplicacion.getInstance().getUsuarioLogeado();
		Object[] rowData = {0,0,0};
		if (u==null) return;
		for (Lista l : u.getListas()) {
			rowData[0] = l;
			rowData[1] = l.getTituloExplicito();
			rowData[2] = l.parseSeconds(l.getDuracion());
			datosListas.addRow(rowData);
			nombreListas.add(l.getTitulo());
		}
		this.formularioLista.actualizarDatos();
	}

}
