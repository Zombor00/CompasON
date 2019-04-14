package GUI.Administrador;

import java.awt.FlowLayout;

import javax.swing.*;

import GUI.AccesoComun.Sesion;

public class InformacionAdmin extends JPanel{
	
	public InformacionAdmin() {
		
		super();
		FlowLayout layout = new FlowLayout();
		this.setLayout(layout);
		Sesion sesion = new Sesion();
		//Opciones opciones = new Opciones();
		
		this.add(sesion);
		//this.add(opciones);
	}

}
