package GUI.AccesoComun;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import aplicacion.Aplicacion;
import usuarios.UsuarioRegistrado;

/**
 * Esta clase tiene toda la informacion relevante al panel de
 * inicio comun a los usuarios registrados en la aplicacion
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class InicioComun extends JPanel{
	
	/**
	 * Texto de bienvenida al usuario
	 */
	private JLabel bienvenido = new JLabel("Bienvenido _");
	/**
	 * Texto que indica el numero de canciones reproducidas
	 */
	private JLabel reproducidas = new JLabel("Canciones reproducidas este mes: _  (Limite: _ )");
	/**
	 * Texto que indica el numero de reproducciones que han hecho de sus canciones
	 */
	private JLabel reproducciones = new JLabel("Reproducciones obtenidas este mes: _  (_ mas para ser premium)");
	
	/**
	  * Constructor de la parte de la pestania de Inicio comun 
	  *
	  */
	public InicioComun() {
		
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon(new ImageIcon("aux/logo-compason.png").getImage().getScaledInstance(600, 350, Image.SCALE_DEFAULT)));
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, bienvenido, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, logo, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, reproducciones, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.WEST, reproducidas, 0, SpringLayout.WEST, reproducciones);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, bienvenido, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, logo, -100, SpringLayout.NORTH, bienvenido);
		layout.putConstraint(SpringLayout.NORTH, reproducidas, 10, SpringLayout.SOUTH, bienvenido);
		layout.putConstraint(SpringLayout.NORTH, reproducciones, 5, SpringLayout.SOUTH, reproducidas);
		
		this.add(logo);
		this.add(bienvenido);
		this.add(reproducidas);
		this.add(reproducciones);
		
	}
	
	/**
	 * Metodo que actualiza el contenido de los JLabel
	 * 
	 */
	public void actualizarDatos() {
		UsuarioRegistrado u = Aplicacion.getInstance().getUsuarioLogeado();
		if (u == null) return;
		this.bienvenido.setText("Bienvenido " + u.getNombre());
		this.reproducciones.setText("Canciones reproducidas este mes: " + u.getReproducidas() +
				                    " (Limite: "+ Aplicacion.getInstance().getLimiteReproducciones() + ")");
		int rep = u.reproduccionesMesActual();
		this.reproducidas.setText("Reproducciones obtenidas este mes: " + rep +
				                  " (" + (Aplicacion.getInstance().getReproduccionesPremium() - rep) + " mas para ser premium)");
	}

}
