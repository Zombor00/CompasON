package GUI.UsuarioPremium;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
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

/**
 * Esta clase tiene las listas del usuario premium
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
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
	 * Modelo con los datos de la lista mostrada
	 */
	private DefaultTableModel datosDeLaLista;
	
	/**
	 * Tabla con el modelo de datos
	 */
	private JTable tablaListas;
	
	/**
	 * Tabla con el modelo de datos de la lista mostrada
	 */
	private JTable tablaDeLaLista;
	
	/**
	 * Menu de opciones
	 */
	private JPopupMenu menu;
	
	/**
	 * Menu de opciones de la lista mostrada
	 */
	private JPopupMenu menuDeLaLista;
	
	/**
	 * Opciones
	 */
    private JMenuItem reproducir,borrar,aniadirCola,aniadirALista,visualizarLista;
    
    /**
	 * Opciones de la lista mostrada
	 */
    private JMenuItem reproducirReproducible, quitarReproducible;
    
    /**
     * Muestra las opciones
     */
    private JButton opciones;
    
    /**
     * Muestra las opciones de la lista
     */
    private JButton opcionesDeLaLista;
    
    /**
     * Para abrir el formulario
     */
    private JButton crearLista;
    
    /**
     * Para ver las listas
     */
    private JButton listas;
    
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
     * Nombre de la lista mostrada
     */
    JLabel listaMostrada;
	
    /**
     * Constructor de la clase
     */
	public MisListas() {
		
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		/* Listas */	
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
		listas = new JButton("Listas");
		formularioLista = new FormularioLista();
		formularioLista.setVisible(false);
		
		aceptar = new JButton("Aceptar");
		aceptar.setVisible(true);
		
		/* Lista mostrada */
		String[] titulosDeLaLista = {"Objeto","Reproducible", "Duracion"};
		Object[][] filasDeLaLista = {{"obj","rep", "dur"},
		};
		datosDeLaLista = new DefaultTableModelNoEditable(filasDeLaLista, titulosDeLaLista);
		tablaDeLaLista = new JTable(datosDeLaLista);
		TableColumnModel tcmDeLaLista = tablaDeLaLista.getColumnModel();
		tcmDeLaLista.removeColumn(tcmDeLaLista.getColumn(0));
		tablaDeLaLista.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tablaDeLaLista.setPreferredScrollableViewportSize(new Dimension(800, 500));
		JScrollPane scrollTablaDeLaLista = new JScrollPane(tablaDeLaLista);
		scrollTablaDeLaLista.setVisible(false);
		JButton volver = new JButton("Volver");
		volver.setVisible(false);
		listaMostrada = new JLabel("Lista:");

		
		/*Colocamos los elementos*/
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollTablaListas, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, scrollTablaListas, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollTablaDeLaLista, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, scrollTablaDeLaLista, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.EAST, crearLista, 0, SpringLayout.EAST, scrollTablaListas);
		layout.putConstraint(SpringLayout.SOUTH, crearLista, 0, SpringLayout.NORTH, scrollTablaListas);
		layout.putConstraint(SpringLayout.WEST, listas, 0, SpringLayout.WEST, scrollTablaListas);
		layout.putConstraint(SpringLayout.SOUTH, listas, 0, SpringLayout.NORTH, scrollTablaListas);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, formularioLista, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, formularioLista, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, aceptar, 0, SpringLayout.SOUTH, formularioLista);
		layout.putConstraint(SpringLayout.EAST, aceptar, 0, SpringLayout.EAST, crearLista);
		layout.putConstraint(SpringLayout.NORTH, volver, 0, SpringLayout.NORTH, scrollTablaListas);
        layout.putConstraint(SpringLayout.EAST, volver, 0, SpringLayout.WEST, scrollTablaListas);
        layout.putConstraint(SpringLayout.SOUTH, listaMostrada, 0, SpringLayout.NORTH, scrollTablaListas);
        layout.putConstraint(SpringLayout.WEST, listaMostrada, 5, SpringLayout.WEST, scrollTablaListas);
		
		/* Configuramos el boton "Listas" */
		listas.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						scrollTablaListas.setVisible(true);
						opciones.setVisible(true);
						formularioLista.setVisible(false);
						aceptar.setVisible(false);
						scrollTablaDeLaLista.setVisible(false);
						listas.setVisible(true);
						crearLista.setVisible(true);
						volver.setVisible(false);
						opcionesDeLaLista.setVisible(false);
						listaMostrada.setVisible(false);
					}
				});
		
		/* Configuramos el boton "crearLista" */
		crearLista.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						scrollTablaListas.setVisible(false);
						opciones.setVisible(false);
						formularioLista.setVisible(true);
						aceptar.setVisible(true);
						scrollTablaDeLaLista.setVisible(false);
						listas.setVisible(true);
						crearLista.setVisible(true);
						volver.setVisible(false);
						opcionesDeLaLista.setVisible(false);
						listaMostrada.setVisible(false);
					}
				});
		
		this.add(scrollTablaListas);
		this.add(crearLista);
		this.add(listas);
		this.add(formularioLista);
		this.add(aceptar);
		this.add(scrollTablaDeLaLista);
		this.add(volver);
		this.add(listaMostrada);
		
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
        visualizarLista = new JMenuItem("Visualizar");
        menu.add(visualizarLista);
        opciones = new JButton("Opciones"); 
        layout.putConstraint(SpringLayout.NORTH, opciones, 0, SpringLayout.NORTH, scrollTablaListas);
        layout.putConstraint(SpringLayout.WEST, opciones, 0, SpringLayout.EAST, scrollTablaListas);
        this.add(opciones);
        
        /* Aniadimos el menu y el boton de opciones de la lista mostrada*/        
        menuDeLaLista = new JPopupMenu();
        reproducirReproducible = new JMenuItem("Reproducir");
        menuDeLaLista.add(reproducirReproducible);
        quitarReproducible = new JMenuItem("Quitar");
        menuDeLaLista.add(quitarReproducible);
        opcionesDeLaLista = new JButton("Opciones"); 
        layout.putConstraint(SpringLayout.NORTH, opcionesDeLaLista, 0, SpringLayout.NORTH, scrollTablaListas);
        layout.putConstraint(SpringLayout.WEST, opcionesDeLaLista, 0, SpringLayout.EAST, scrollTablaListas);
        this.add(opcionesDeLaLista);
        opcionesDeLaLista.setVisible(false);
        
        /* Configuramos el boton "visualizarLista" */
		visualizarLista.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Lista lista = MisListas.this.getSelectedLista();
						if(lista == null) return;
						listaMostrada.setText("Lista: " + lista.getTitulo());
						scrollTablaListas.setVisible(false);
						opciones.setVisible(false);
						formularioLista.setVisible(false);
						aceptar.setVisible(false);
						scrollTablaDeLaLista.setVisible(true);
						listas.setVisible(false);
						crearLista.setVisible(false);
						volver.setVisible(true);
						opcionesDeLaLista.setVisible(true);
						listaMostrada.setVisible(true);
					}
				});
		
		/* Configuramos el boton "volver" */
		volver.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						scrollTablaListas.setVisible(true);
						opciones.setVisible(true);
						formularioLista.setVisible(false);
						aceptar.setVisible(false);
						scrollTablaDeLaLista.setVisible(false);
						listas.setVisible(true);
						crearLista.setVisible(true);
						volver.setVisible(false);
						opcionesDeLaLista.setVisible(false);
						listaMostrada.setVisible(false);
					}
				});
		
	}
	
	public JTable getTabla() {
		return this.tablaListas;
	}
	
	public JTable getTablaDeLaLista() {
		return this.tablaDeLaLista;
	}
	
	public JButton getOpcionesDeLaLista() {
		return this.opcionesDeLaLista;
	}
	
	public JButton getOpciones() {
		return this.opciones;
	}
	
	public JPopupMenu GetMenuDeLaLista() {
		return this.menuDeLaLista;
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
	
	public DefaultTableModel getDatosDeLaLista() {
		return this.datosDeLaLista;
	}
	public JMenuItem getVisualizarLista() {
		return this.visualizarLista;
	}
	
	/**
	 * Metodo que devuelve las listas seleccionados
	 * @return lista de listas seleccionados
	 */
	public ArrayList<Lista> getSelectedListas(){
		JTable tablaListas = this.getTabla();
		
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
	
	/**
	 * Metodo que devuelve la primera lista seleccionada
	 * @return lista de listas seleccionados
	 */
	public Lista getSelectedLista() {
		JTable tabla = this.getTabla();
        int fila = tabla.getSelectedRow();
        if(fila == -1) {
        	return null;
        }
        return (Lista)tabla.getModel().getValueAt(fila, 0);
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
		opcionesDeLaLista.setActionCommand("OPCIONES_DE_LA_LISTA");
		opcionesDeLaLista.addActionListener(controlador);
		visualizarLista.setActionCommand("VISUALIZAR_LISTA");
		visualizarLista.addActionListener(controlador);
		reproducirReproducible.setActionCommand("REPRODUCIR_REPRODUCIBLE");
		reproducirReproducible.addActionListener(controlador);
		quitarReproducible.setActionCommand("QUITAR_REPRODUCIBLE");
		quitarReproducible.addActionListener(controlador);
	}
	
	/**
	 * Actualiza los datos de las listas del usuario
	 */
	public void actualizarDatos() {
		listas.doClick();
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
