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

import aplicacion.Aplicacion;
import media.Lista;
import usuarios.UsuarioRegistrado;

public class MisListas extends JPanel{
	
	private ArrayList<String> nombreListas = new ArrayList<String>();
	private DefaultTableModel datosListas;
	private JTable tablaListas;
	private JPopupMenu menu;
    private JMenuItem reproducir,borrar;
    private JButton opciones;
    private JButton crearLista;
	
	public MisListas() {
		
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		/* Canciones */	
		String[] titulos = {"Lista", "Duracion"};
		Object[][] filas = {
		{"Lista 1", "00:00"},
		{"Lista 2", "00:00"},
		{"Lista 3", "00:00"},
		{"Lista 4", "00:00"},
		{"Lista 5", "00:00"},
		{"Lista 6", "00:00"},
		{"Lista 7", "00:00"},
		{"Lista 8", "00:00"},
		{"Lista 9", "00:00"},
		{"Lista 10", "00:00"},
		};
		datosListas = new DefaultTableModel(filas, titulos);
		tablaListas = new JTable(datosListas);
		tablaListas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tablaListas.setPreferredScrollableViewportSize(new Dimension(800, 500));
		JScrollPane scrollTablaListas = new JScrollPane(tablaListas);
		crearLista = new JButton("Crear lista");
		JButton listas = new JButton("Listas");
		FormularioLista formularioLista = new FormularioLista();
		formularioLista.setVisible(false);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollTablaListas, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, scrollTablaListas, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.EAST, crearLista, 0, SpringLayout.EAST, scrollTablaListas);
		layout.putConstraint(SpringLayout.SOUTH, crearLista, -50, SpringLayout.NORTH, scrollTablaListas);
		layout.putConstraint(SpringLayout.WEST, listas, 0, SpringLayout.WEST, scrollTablaListas);
		layout.putConstraint(SpringLayout.SOUTH, listas, -50, SpringLayout.NORTH, scrollTablaListas);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, formularioLista, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, formularioLista, 0, SpringLayout.VERTICAL_CENTER, this);
		
		/* COnfiguramos el boton "Listas" */
		listas.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						scrollTablaListas.setVisible(true);
						opciones.setVisible(true);
						formularioLista.setVisible(false);
					}
				});
		
		/* COnfiguramos el boton "Listas" */
		crearLista.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						scrollTablaListas.setVisible(false);
						opciones.setVisible(false);
						formularioLista.setVisible(true);
					}
				});
		
		this.add(scrollTablaListas);
		this.add(crearLista);
		this.add(listas);
		this.add(formularioLista);
		
		/* Aniadimos el menu y el boton de opciones */        
        menu=new JPopupMenu();
        reproducir=new JMenuItem("Reproducir");
        menu.add(reproducir);
        borrar=new JMenuItem("Borrar");
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
	
	public void setControlador(ActionListener controlador) {
		opciones.setActionCommand("OPCIONES");
		opciones.addActionListener(controlador);
		reproducir.setActionCommand("REPRODUCIR");
		reproducir.addActionListener(controlador);
		borrar.setActionCommand("BORRAR");
		borrar.addActionListener(controlador);
		crearLista.setActionCommand("CREAR_LISTA");
		crearLista.addActionListener(controlador);
	}
	
	public void actualizarDatos() {
		int numFilas = datosListas.getRowCount();
		for(int i=0; i< numFilas; i++) {
			datosListas.removeRow(0);
		}
		UsuarioRegistrado u = Aplicacion.getInstance().getUsuarioLogeado();
		Object[] rowData = {0,0};
		if (u==null) return;
		for (Lista c : u.getListas()) {
			rowData[0] = c;
			rowData[1] = c.getDuracion();
			datosListas.addRow(rowData);
			nombreListas.add(c.getTitulo());
		}
	}

}
