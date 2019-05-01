package GUI.AccesoComun;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class JCheckBoxScrollableList extends JScrollPane{
	
	private JCheckBoxList checkBoxList;

	public JCheckBoxScrollableList(JCheckBoxList checkBoxList) {
		super(checkBoxList);
		this.checkBoxList = checkBoxList;
	}
	
	public ArrayList<Integer> getSelectedIndices(){
		return this.checkBoxList.getSelectedIndices();
	}
	
	
	
	

	public static void main(String[] args) {
		
		ArrayList<String> l = new ArrayList<String>();
		l.add("Hola");
		l.add("Que");
		l.add("Tal");
		l.add("Hola");
		l.add("Que");
		l.add("Tal");
		l.add("Hola");
		l.add("Que");
		l.add("Tal");
		l.add("Hola");
		l.add("Que");
		l.add("Tal");
		l.add("Hola");
		l.add("Que");
		l.add("Tal");
		JCheckBoxScrollableList c = new JCheckBoxScrollableList(new JCheckBoxList(l));
		JFrame pantalla = new JFrame();
		pantalla.add(c);
		
		pantalla.setSize(new Dimension(250,250));
		pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pantalla.setVisible(true);
		
		System.out.println(c.checkBoxList);
		
	}
	
}
