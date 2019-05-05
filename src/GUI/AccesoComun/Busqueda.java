package GUI.AccesoComun;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import aplicacion.Aplicacion;
import media.Buscable;

public class Busqueda extends JPanel {
	
	private DefaultTableModel modeloDatos;
	private JButton buscar;
	private JTextField busqueda;
	private JComboBox<String> modo;
	private JTable tabla;
    private JPopupMenu menu;
    private JMenuItem reproducir,aniadirACola,aniadirALista,seguirAutor,denunciar;
    private JButton opciones;

	public Busqueda() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		busqueda = new JTextField(20);
		buscar = new JButton("Buscar");
		String[] modos = {"Por titulo", "Por autor"};
		modo = new JComboBox<>(modos);
		
		String[] titulos = {"Objeto","Cancion", "Autor", "Duración"};
		Object[][] filas = {
		};
		
	    modeloDatos = new DefaultTableModelNoEditable(filas, titulos);
		tabla = new JTable(modeloDatos);
		tabla.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tabla.setPreferredScrollableViewportSize(new Dimension(800, 500));
		TableColumnModel tcm = tabla.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
		JScrollPane scrollTabla = new JScrollPane(tabla);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollTabla, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, scrollTabla, 0, SpringLayout.VERTICAL_CENTER, this);
		
		layout.putConstraint(SpringLayout.SOUTH, buscar, 0, SpringLayout.NORTH, scrollTabla);
		layout.putConstraint(SpringLayout.EAST, buscar, 0, SpringLayout.EAST, scrollTabla);
		
		layout.putConstraint(SpringLayout.SOUTH, modo, 0, SpringLayout.NORTH, scrollTabla);
		layout.putConstraint(SpringLayout.EAST, modo, -5, SpringLayout.WEST, buscar);
		
		layout.putConstraint(SpringLayout.SOUTH, busqueda, 0, SpringLayout.NORTH, scrollTabla);
		layout.putConstraint(SpringLayout.EAST, busqueda, -5, SpringLayout.WEST, modo);

		
		this.add(buscar);
		this.add(modo);
		this.add(busqueda);
		this.add(scrollTabla);
		
		/* Aniadimos el menu y el boton de opciones */        
        menu=new JPopupMenu();
        reproducir=new JMenuItem("Reproducir");
        menu.add(reproducir);
        aniadirACola=new JMenuItem("Añadir a la cola");
        menu.add(aniadirACola);
        aniadirALista=new JMenuItem("Añadir a una lista");
        menu.add(aniadirALista);
        seguirAutor=new JMenuItem("Seguir al autor");
        menu.add(seguirAutor);
        denunciar=new JMenuItem("Denunciar");
        menu.add(denunciar);
        opciones = new JButton("Opciones"); 
        layout.putConstraint(SpringLayout.NORTH, opciones, 0, SpringLayout.NORTH, scrollTabla);
        layout.putConstraint(SpringLayout.WEST, opciones, 0, SpringLayout.EAST, scrollTabla);
        this.add(opciones);
        actualizarBusqueda((ArrayList<Buscable>) Aplicacion.getInstance().getBuscables());
	}
	
	public void limpiarBusqueda() {
		int numFilas = modeloDatos.getRowCount();
		for(int i=0; i< numFilas; i++) {
			modeloDatos.removeRow(0);
		}
		modo.setSelectedIndex(0);
	}

	public void actualizarBusqueda(ArrayList<Buscable> buscables) {
		
		int modoAnterior = modo.getSelectedIndex();
		this.limpiarBusqueda();
		
		Object[] rowData = {0,0,0,0};
		for (Buscable b : buscables) {
			rowData[0] = b;
			rowData[1] = b.getTitulo();
			rowData[2] = b.getAutor();
			rowData[3] = b.parseSeconds(b.getDuracion());
			modeloDatos.addRow(rowData);
		}
		busqueda.setText("");
		modo.setSelectedIndex(modoAnterior);
	}
	
	public void actualizarDatos() {
		actualizarBusqueda((ArrayList<Buscable>) Aplicacion.getInstance().getBuscables());
	}
	
	public void setControlador(ActionListener controlador) {
		buscar.setActionCommand("BUSCAR");
		buscar.addActionListener(controlador);
		opciones.setActionCommand("OPCIONES");
		opciones.addActionListener(controlador);
		reproducir.setActionCommand("REPRODUCIR");
		reproducir.addActionListener(controlador);
		aniadirACola.setActionCommand("ANIADIRACOLA");
		aniadirACola.addActionListener(controlador);
		seguirAutor.setActionCommand("SEGUIRAUTOR");
		seguirAutor.addActionListener(controlador);
		denunciar.setActionCommand("DENUNCIAR");
		denunciar.addActionListener(controlador);
		aniadirALista.setActionCommand("ANIADIR_A_LISTA");
		aniadirALista.addActionListener(controlador);
	}
	
	public String getBusqueda() {
		return busqueda.getText();
	}
	
	public JTable getTabla() {
		return this.tabla;
	}
	
	public JButton getOpciones() {
		return this.opciones;
	}
	
	public JPopupMenu getMenu() {
		return this.menu;	
	}
	
	public JComboBox<String> getModo() {
		return this.modo;
	}
}
