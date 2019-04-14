package GUI.UsuarioPremium;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import GUI.AccesoComun.Busqueda;
import GUI.AccesoComun.MisCanciones;

public class PestaniasUsuarioPremium extends JTabbedPane{
	
	public PestaniasUsuarioPremium() {
		super();
		JPanel inicio = new InicioPremium();
		JPanel busqueda = new Busqueda();
		JPanel misCanciones = new MisCanciones();
		JPanel misListas = new MisListas();
		
		this.addTab("Inicio", inicio);
		this.addTab("Busqueda", busqueda);
		this.addTab("Mis canciones", misCanciones);
		this.addTab("Mis listas", misListas);
		
		this.setSelectedIndex(0);
		
	}

}
