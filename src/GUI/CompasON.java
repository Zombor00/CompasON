package GUI;

import java.awt.*;
import javax.swing.*;


public class CompasON extends JFrame {

	public CompasON() {
		super("CompasON");
		Container contenedor = this.getContentPane();
		contenedor.setLayout(new BorderLayout());
		
		contenedor.add(new Pestanias(),BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		CompasON a = new CompasON();
	}
}
