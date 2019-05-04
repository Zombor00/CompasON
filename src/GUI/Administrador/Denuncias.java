package GUI.Administrador;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import GUI.AccesoComun.DefaultTableModelNoEditable;
import aplicacion.Aplicacion;
import gestion.Denuncia;
import usuarios.Administrador;


public class Denuncias extends JPanel {
	
	private DefaultTableModel modeloDatos;
	private JTable tabla;
	private JPopupMenu menu;
    private JMenuItem reproducir, plagio, noPlagio;
    private JButton opciones;

	public Denuncias() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		
		String[] titulos = {"Cancion", "Autor", "Duracion", "Comentario"};
		Object[][] filas = {
		{"Cancion 1", "Autor 1", "00:00","Comentario 1"},
		{"Cancion 2", "Autor 2", "00:00", "Comentario 2"},
		{"Cancion 3", "Autor 3", "00:00", "Comentario 3"},
		{"Cancion 4", "Autor 4", "00:00", "Comentario 4"},
		{"Cancion 5", "Autor 5", "00:00", "Comentario 5"},
		{"Cancion 6", "Autor 6", "00:00", "Comentario 6"},
		{"Cancion 7", "Autor 7", "00:00", "Comentario 7"},
		{"Cancion 8", "Autor 8", "00:00", "Comentario 8"},
		{"Cancion 9", "Autor 9", "00:00", "Comentario 9"},
		{"Cancion 10", "Autor 10", "00:00", "Comentario 10"},
		};
		modeloDatos = new DefaultTableModelNoEditable(filas, titulos);
		tabla = new JTable(modeloDatos);
		tabla.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tabla.setPreferredScrollableViewportSize(new Dimension(800, 500));
		JScrollPane scrollTabla = new JScrollPane(tabla);
		
		

		
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
