package GUI.Administrador;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import GUI.AccesoComun.DefaultTableModelNoEditable;
import aplicacion.Aplicacion;
import gestion.Denuncia;
import usuarios.Administrador;

/**
 * Esta clase tiene toda la informacion relevante a la pestania
 * de validar del administrador
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class Denuncias extends JPanel {
	
	/**
	 * Modelo con los datos de las denuncias a tramitar
	 */
	private DefaultTableModel modeloDatos;
	/**
	 * Tabla con el modelo de datos de las denuncias a tramitar
	 */
	private JTable tabla;
	/**
	 * Menu de opciones
	 */
	private JPopupMenu menu;
	/**
	 * Opciones a realizar con las denuncias
	 */
    private JMenuItem reproducir, plagio, noPlagio;
	/**
	 * Boton que despliega las opciones del menu
	 */
    private JButton opciones;

    /**
	 * Constructor de la pestania denuncias
	 *
	 */
	public Denuncias() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		
		String[] titulos = {"Cancion", "Autor", "Duracion", "Comentario"};
		Object[][] filas = {
		{"Cancion 1", "Autor 1", "00:00","Comentario 1"}
		};
		modeloDatos = new DefaultTableModelNoEditable(filas, titulos);
		tabla = new JTable(modeloDatos);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		tabla.setPreferredScrollableViewportSize(new Dimension(800, 500));
		JScrollPane scrollTabla = new JScrollPane(tabla);
		TableColumnModel modeloColumnas = tabla.getColumnModel();
		modeloColumnas.getColumn(0).setPreferredWidth(250);
		modeloColumnas.getColumn(1).setPreferredWidth(220);
		modeloColumnas.getColumn(2).setPreferredWidth(80);
		modeloColumnas.getColumn(3).setPreferredWidth(250);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollTabla, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, scrollTabla, 0, SpringLayout.VERTICAL_CENTER, this);

		/* Aniadimos el menu y el boton de opciones */        
        menu = new JPopupMenu();
        reproducir = new JMenuItem("Reproducir");
        menu.add(reproducir);
        plagio = new JMenuItem("Plagio confirmado");
        menu.add(plagio);
        noPlagio = new JMenuItem("No hay plagio");
        menu.add(noPlagio);
        opciones = new JButton("Opciones"); 
        layout.putConstraint(SpringLayout.NORTH, opciones, 0, SpringLayout.NORTH, scrollTabla);
        layout.putConstraint(SpringLayout.WEST, opciones, 0, SpringLayout.EAST, scrollTabla);
        this.add(opciones);
	
		this.add(scrollTabla);
	}
	
	/**
	 * Metodo que establece el controlador de las acciones de los botones
	 * 
	 * @param controlador de los botones de la pestania
	 */
	public void setControlador(ActionListener controlador) {
		opciones.setActionCommand("OPCIONES");
		opciones.addActionListener(controlador);
		reproducir.setActionCommand("REPRODUCIR");
		reproducir.addActionListener(controlador);
		plagio.setActionCommand("PLAGIO");
		plagio.addActionListener(controlador);
		noPlagio.setActionCommand("SIN_PLAGIO");
		noPlagio.addActionListener(controlador);
	}

	/**
	 * Metodo que actualiza la informacion de la pestania denuncias
	 * 
	 */
	public void actualizarDatos() {
		int numFilas = modeloDatos.getRowCount();
		for(int i=0; i< numFilas; i++) {
			modeloDatos.removeRow(0);
		}
		Administrador a = Aplicacion.getInstance().getAdministrador();
		Object[] rowData = {0,0,0,0};
		
		for (Denuncia d : a.getDenuncias()) {
			rowData[0] = d;
			rowData[1] = d.getDenunciada().getAutor();
			rowData[2] = d.getDenunciada().parseSeconds(d.getDenunciada().getDuracion());
			rowData[3] = d.getComentario();
			modeloDatos.addRow(rowData);
		}		
	}

	public JTable getTabla() {
		return tabla;
	}

	public JPopupMenu getMenu() {
		return menu;
	}

	public JMenuItem getReproducir() {
		return reproducir;
	}

	public JMenuItem getPlagio() {
		return plagio;
	}

	public JMenuItem getNoPlagio() {
		return noPlagio;
	}

	public JButton getOpciones() {
		return opciones;
	}
	
	

	
}
