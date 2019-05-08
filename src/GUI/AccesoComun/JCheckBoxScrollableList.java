package GUI.AccesoComun;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 * Panel que muestra una lista con scroll de componentes de tipo JCheckBox
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 */
public class JCheckBoxScrollableList extends JScrollPane{
	
	/**
	 * Lista de checkboxes asociada
	 */
	private JCheckBoxList checkBoxList;

	/**
	 * Construtor de la clase
	 * @param checkBoxList lista de checkboxes asociada
	 */
	public JCheckBoxScrollableList(JCheckBoxList checkBoxList) {
		super(checkBoxList);
		this.checkBoxList = checkBoxList;
		this.setPreferredSize(new Dimension(250,250));
		this.specialSetBackground(Color.DARK_GRAY);
		JScrollBar s = this.getVerticalScrollBar();
		s.setBackground(Color.red);
		this.setVerticalScrollBar(s);
	}
	
	/**
	 * Devuelve los indices de los checkboxes seleccionadas
	 * @return Lista con los indices de los checkboxes seleccionados
	 */
	public ArrayList<Integer> getSelectedIndices(){
		return this.checkBoxList.getSelectedIndices();
	}
	
	/**
	 * Permite cambiar el fondo del panel sin sobreescribir el clasico setBackground
	 * @param bg Color del fonfo
	 */
	public void specialSetBackground(Color bg) {
		super.setBackground(bg);
		checkBoxList.specialSetBackground(bg);
		this.setBorder(null);
	}

	/**
	 * Metodo para probar la clase
	 */
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
