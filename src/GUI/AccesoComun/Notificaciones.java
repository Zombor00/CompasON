package GUI.AccesoComun;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import gestion.Notificacion;
import aplicacion.Aplicacion;

/**
 * Esta clase tiene toda la informacion relevante al panel
 * donde aparecen las notificaciones del usuario
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class Notificaciones extends JPanel {
	
	/**
	 * Modelo con los datos de las notificaciones a mostrar
	 */
	private DefaultTableModel usuarioNotificacion;
	/**
	 * Tabla con el modelo de datos de las notificaciones
	 */
	private JTable tablaNotificaciones;
	
	/**
	 * Constructor del panel notificaciones
	 *
	 */
	public Notificaciones() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		String[] titulos = {"Usuarios", "Notificacion"};
		Object[][] filas = {
		{"OJO: sin actualizar", "Nueva canción"}
		
		};
		usuarioNotificacion = new DefaultTableModelNoEditable(filas, titulos);
		tablaNotificaciones = new JTable(usuarioNotificacion);
		tablaNotificaciones.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		tablaNotificaciones.setPreferredScrollableViewportSize(new Dimension(250, 150));
		JScrollPane scrolltablaNotificaciones = new JScrollPane(tablaNotificaciones);
		
		JLabel notificaciones = new JLabel("Notificaciones");
		notificaciones.setFont(new Font(notificaciones.getFont().getFontName(), notificaciones.getFont().getStyle(), 20));
		layout.putConstraint(SpringLayout.NORTH, notificaciones, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, scrolltablaNotificaciones, 25, SpringLayout.NORTH, notificaciones);
		
		layout.putConstraint(SpringLayout.WEST, notificaciones, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, scrolltablaNotificaciones, 25, SpringLayout.WEST, this);
		this.add(notificaciones);
		this.add(scrolltablaNotificaciones);
		
	}
	
	/**
	 * Metodo que actualiza la informacion del panel
	 * 
	 */
	public void actualizarDatos() {
		Aplicacion a = Aplicacion.getInstance();
		if (a.getUsuarioLogeado() == null) {
			return;
		}
		
		ArrayList<Notificacion> notificaciones = a.getUsuarioLogeado().getNotificaciones();
		
		int numFilas = usuarioNotificacion.getRowCount();
		for(int i=0; i< numFilas; i++) {
			usuarioNotificacion.removeRow(0);
		}

		Object[] rowData = {0,0};
		for (Notificacion n : notificaciones) {
			rowData[0] = n.getCancion().getAutor();
			rowData[1] = n;
			usuarioNotificacion.addRow(rowData);
		}
	}
	
	public JTable getTablaNotificaciones() {
		return this.tablaNotificaciones;
	}
		
	
	public DefaultTableModel getUsuarioNotificacion() {
		return usuarioNotificacion;
	}

	/**
	 * Metodo que establece el controlador de las acciones al pulsar
	 * las notificaciobes
	 * 
	 * @param controlador de los botones del panel
	 */
	public void setControlador(ListSelectionListener controlador) {
		tablaNotificaciones.getSelectionModel().addListSelectionListener(controlador);
		tablaNotificaciones.getColumnModel().getSelectionModel().addListSelectionListener(controlador);	
	}
	
}
