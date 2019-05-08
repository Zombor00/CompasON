package GUI.AccesoComun;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import GUI.GuiAplicacion;
import media.Album;
import media.Cancion;

public class ListaCanciones extends JDialog implements ActionListener {

	public ListaCanciones(Album album) {
		super(GuiAplicacion.getInstance());

		/* Actualizamos el titulo del dialogo */
		setTitle(album.getTitulo());
		JButton button = new JButton("Cerrar");
		button.addActionListener(this);
		add(button);

		JPanel lista = new JPanel();
		lista.setLayout(new BoxLayout(lista, BoxLayout.Y_AXIS));
		for (Cancion c : album.getCanciones()) {
			lista.add(new JLabel(c.getTitulo()));
		}

		JScrollPane scroll = new JScrollPane(lista);
		scroll.setPreferredSize(new Dimension(400, 200));
		lista.setBackground(Color.DARK_GRAY);
		lista.setBorder(null);
		scroll.setBorder(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(500, 500, 500, 500);
		panel.add(button);
		panel.add(scroll);
		add(panel);

		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scroll, 0, SpringLayout.HORIZONTAL_CENTER, panel);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, scroll, -10, SpringLayout.VERTICAL_CENTER, panel);
		layout.putConstraint(SpringLayout.EAST, button, 0, SpringLayout.EAST, panel);
		layout.putConstraint(SpringLayout.SOUTH, button, 0, SpringLayout.SOUTH, panel);

		pack();
		setVisible(true);
		setBounds(500, 375, 500, 375);
		setLocationRelativeTo(null);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		dispose();
	}

}
