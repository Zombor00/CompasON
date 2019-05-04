package GUI.AccesoComun;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class JCheckBoxList extends JPanel {
	
	private ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
	
	public JCheckBoxList(ArrayList<String> elementos) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		for (String e : elementos) {
			JCheckBox c = new JCheckBox(e);
			this.add(c);
			checkBoxes.add(c);
		}
	}
	public void specialSetBackground(Color bg) {
		super.setBackground(bg);
		for(JCheckBox c : checkBoxes) {
			c.setBackground(bg);
		}
	}
	
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
