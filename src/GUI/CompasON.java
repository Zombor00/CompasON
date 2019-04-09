package GUI;

import java.awt.*;
import javax.swing.*;


public class CompasON extends JFrame {

	public CompasON() {
		super("CompasON");
		this.setIconImage(new ImageIcon("aux/icono-compason.png").getImage());
		Container contenedor = this.getContentPane();
		BorderLayout layout = new BorderLayout();
		contenedor.setLayout(layout);
		
		contenedor.add(new ConjuntoPestanias(),BorderLayout.CENTER);
		contenedor.add(new Reproductor(),BorderLayout.SOUTH);
		
        this.setSize(this.getToolkit().getScreenSize());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		CompasON a = new CompasON();
	}
}
