package GUI;

import java.awt.*;
import java.io.FileNotFoundException;

import javax.swing.*;

import aplicacion.Aplicacion;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;
import pads.musicPlayer.exceptions.Mp3PlayerException;


public class GuiAplicacion extends JFrame {
	
	public static GuiAplicacion INSTANCE = null;
	private PanelesUsuarios panelesUsuarios = new PanelesUsuarios();

	private GuiAplicacion() {
		super("CompasON");
		this.setIconImage(new ImageIcon("aux/icono-compason.png").getImage());
		Container contenedor = this.getContentPane();
		BorderLayout layout = new BorderLayout();
		contenedor.setLayout(layout);
		
		contenedor.add(panelesUsuarios,BorderLayout.CENTER);
		contenedor.add(new Reproductor(),BorderLayout.SOUTH);
		
        this.setSize(this.getToolkit().getScreenSize());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);	
		
	}
	
	public static GuiAplicacion getInstance() {
		if (INSTANCE == null) INSTANCE = new GuiAplicacion();
		return INSTANCE;
	}
	
	public PanelesUsuarios getPanelesUsuarios() {
		return this.panelesUsuarios;
	}
	
	public static void main(String[] args) throws FileNotFoundException, Mp3PlayerException, ExcepcionParametrosDeEntradaIncorrectos {
		Aplicacion a = Aplicacion.getInstance(10, 10, 10);
		GuiAplicacion g = GuiAplicacion.getInstance();
		System.out.println("Ejecutando main de Gui.Aplicaion. "+ a + " " + g);
	}
}
