package GUI.AccesoComun;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import aplicacion.Aplicacion;
import usuarios.UsuarioRegistrado;

public class Sesion extends JPanel {

	private JLabel nombre;
	private JButton cerrar;
	private JButton dejarSeguir;
	private JTable tablaSeguidos;
	private DefaultTableModel datosSeguidos;
	private JScrollPane scrolltablaSeguidos;

	public Sesion() {
		super();
		nombre = new JLabel("Nombre");
		cerrar = new JButton("Cerrar Sesión");
		dejarSeguir = new JButton("Dejar de seguir usuario");
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		nombre.setFont(new Font(nombre.getFont().getFontName(), nombre.getFont().getStyle(), 20));

		String[] titulos = { "Usuarios seguidos" };
		Object[][] filas = { { "UsuarioA" }, { "UsuarioB" }, };

		datosSeguidos = new DefaultTableModelNoEditable(filas, titulos);
		tablaSeguidos = new JTable(datosSeguidos);
		tablaSeguidos.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		tablaSeguidos.setPreferredScrollableViewportSize(new Dimension(200, 300));
		scrolltablaSeguidos = new JScrollPane(tablaSeguidos);

		layout.putConstraint(SpringLayout.NORTH, nombre, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, cerrar, 25, SpringLayout.NORTH, nombre);
		layout.putConstraint(SpringLayout.WEST, nombre, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, cerrar, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, scrolltablaSeguidos, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, dejarSeguir, 25, SpringLayout.SOUTH, cerrar);
		layout.putConstraint(SpringLayout.NORTH, scrolltablaSeguidos, 5, SpringLayout.SOUTH, dejarSeguir);
		layout.putConstraint(SpringLayout.WEST, dejarSeguir, 0, SpringLayout.WEST, scrolltablaSeguidos);

		this.add(nombre);
		this.add(cerrar);
		this.add(scrolltablaSeguidos);
		this.add(dejarSeguir);
	}

	public void actualizarDatos() {
		if (Aplicacion.getInstance().getAdministradorLogeado()) {
			nombre.setText("Admin");
			dejarSeguir.setVisible(false);
			scrolltablaSeguidos.setVisible(false);
		} else if (Aplicacion.getInstance().getUsuarioLogeado() != null) {
			nombre.setText(Aplicacion.getInstance().getUsuarioLogeado().getNombre());
			dejarSeguir.setVisible(true);
			scrolltablaSeguidos.setVisible(true);

			int numFilas = datosSeguidos.getRowCount();
			for (int i = 0; i < numFilas; i++) {
				datosSeguidos.removeRow(0);
			}
			UsuarioRegistrado usuario = Aplicacion.getInstance().getUsuarioLogeado();
			Object[] rowData = { 0 };

			for (UsuarioRegistrado u : usuario.getSeguidos()) {
				rowData[0] = u;
				datosSeguidos.addRow(rowData);
			}
		}
	}

	public void setControlador(ActionListener controlador) {
		cerrar.setActionCommand("Cerrar_Sesion");
		cerrar.addActionListener(controlador);
	}
}
