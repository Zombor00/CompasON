package GUI.AccesoComun;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Notificaciones extends JPanel {
	
	public Notificaciones() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		String[] titulos = {"Usuarios", "Notificacion"};
		Object[][] filas = {
		{"Usuario 1", "Nueva canción: MathFun!"},
		{"Usuario 2", "Nuevo disco: a"},
		
		};
		DefaultTableModel usuarioNotificacion = new DefaultTableModel(filas, titulos);
		JTable tablaNotificaciones= new JTable(usuarioNotificacion);
		tablaNotificaciones.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tablaNotificaciones.setPreferredScrollableViewportSize(new Dimension(150, 150));
		JScrollPane scrolltablaNotificaciones = new JScrollPane(tablaNotificaciones);
		
		JLabel notificaciones = new JLabel("Notificaciones");
		notificaciones.setFont(new Font(notificaciones.getFont().getFontName(), notificaciones.getFont().getStyle(), 20));
		layout.putConstraint(SpringLayout.NORTH, notificaciones, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, scrolltablaNotificaciones, 45, SpringLayout.NORTH, notificaciones);
		
		layout.putConstraint(SpringLayout.WEST, notificaciones, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, scrolltablaNotificaciones, 25, SpringLayout.WEST, this);
		this.add(notificaciones);
		this.add(scrolltablaNotificaciones);
	}

}
