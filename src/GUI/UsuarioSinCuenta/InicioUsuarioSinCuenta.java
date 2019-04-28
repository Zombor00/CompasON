package GUI.UsuarioSinCuenta;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class InicioUsuarioSinCuenta extends JPanel {
	
	private Login login = new Login();
	private Registro registro = new Registro();

	public InicioUsuarioSinCuenta() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon(new ImageIcon("aux/logo-compason.png").getImage().getScaledInstance(200, 75, Image.SCALE_DEFAULT)));
		registro.setVisible(false);
		JButton registrarse   = new JButton("Registrarse en CompasON");
		JButton iniciarSesion   = new JButton("Iniciar sesion en CompasON");
		iniciarSesion.setVisible(false);
		
		/* Colocamos los elementos */
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, login, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, registro, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, registrarse, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, iniciarSesion, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, logo, 0, SpringLayout.HORIZONTAL_CENTER, this);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, login, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, logo, -100, SpringLayout.NORTH, login);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, registro, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, registrarse, 200, SpringLayout.VERTICAL_CENTER, login);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, iniciarSesion, 200, SpringLayout.VERTICAL_CENTER, registro);
		
		/* Configuramos el boton "registrarse" */
		registrarse.setContentAreaFilled(false);
		registrarse.setFocusPainted(false);
		registrarse.setBorderPainted(false);
		registrarse.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	Font font = registrarse.getFont();
		    	Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		    	attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		    	registrarse.setFont(font.deriveFont(attributes));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	Font font = registrarse.getFont();
                Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
                attributes.put(TextAttribute.UNDERLINE, -1);
                registrarse.setFont(font.deriveFont(attributes));
		    }
		});
		registrarse.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						login.setVisible(false);
						registro.setVisible(true);
						registrarse.setVisible(false);
						iniciarSesion.setVisible(true);
						}
					}
				);
		
		/* Configuramos el boton "iniciarSesion" */
		iniciarSesion.setContentAreaFilled(false);
		iniciarSesion.setFocusPainted(false);
		iniciarSesion.setBorderPainted(false);
		iniciarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	Font font = iniciarSesion.getFont();
		    	Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		    	attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		    	iniciarSesion.setFont(font.deriveFont(attributes));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	Font font = iniciarSesion.getFont();
                Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
                attributes.put(TextAttribute.UNDERLINE, -1);
                iniciarSesion.setFont(font.deriveFont(attributes));
		    }
		});
		iniciarSesion.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						login.setVisible(true);
						registro.setVisible(false);
						registrarse.setVisible(true);
						iniciarSesion.setVisible(false);
						}
					}
				);

		
		this.add(registrarse);
		this.add(iniciarSesion);
		this.add(logo);
		this.add(login);
		this.add(registro);
	}
	
	public Login getLogin() {
		return this.login;
	}
	
	public Registro getRegistro() {
		return this.registro;
	}
	
}