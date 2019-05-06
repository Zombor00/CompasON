package GUI.AccesoComun;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 * Panel que muestra una lista de componentes de tipo JCheckBox
 * @author antonio
 */
public class JCheckBoxList extends JPanel {
	
	/**
	 * Lista de checkboxes que se muestran
	 */
	private ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
	
	/**
	 * COntructor de la clase
	 * @param elementos Lista de nombres que definen a los checkboxes
	 */
	public JCheckBoxList(ArrayList<String> elementos) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		for (String e : elementos) {
			JCheckBox c = new JCheckBox(e);
			this.add(c);
			checkBoxes.add(c);
		}
	}
	
	/**
	 * Permite cambiar el fondo del panel sin sobreescribir el clasico setBackground
	 * @param bg Color del fonfo
	 */
	public void specialSetBackground(Color bg) {
		super.setBackground(bg);
		for(JCheckBox c : checkBoxes) {
			c.setBackground(bg);
		}
	}
	
	/**
	 * Devuelve los indices de los checkboxes seleccionadas
	 * @return Lista con los indices de los checkboxes seleccionados
	 */
	public ArrayList<Integer> getSelectedIndices(){
		ArrayList<Integer> indices =  new ArrayList<Integer>();
		int i = 0;
		
		for (JCheckBox c : this.checkBoxes) {
			if (c.isSelected()) {
				indices.add(i);
			}
			i += 1;
		}
		
		return indices;
		
	}

}
