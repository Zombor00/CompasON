package GUI.AccesoComun;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class JCheckBoxScrollableListSelect extends JFrame {
	
	public JCheckBoxScrollableListSelect(String tittle, JCheckBoxScrollableList checkBoxScrollableList) {
		
		super(tittle);
		Container contenedor = this.getContentPane();

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contenedor.add(panel,BorderLayout.CENTER);
		
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JButton button = new JButton("OK");
		panel.add(button);
		panel.add(checkBoxScrollableList);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, checkBoxScrollableList, 0, SpringLayout.HORIZONTAL_CENTER, panel);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, checkBoxScrollableList, -10, SpringLayout.VERTICAL_CENTER, panel);
		layout.putConstraint(SpringLayout.EAST, button, 0, SpringLayout.EAST, panel);
		layout.putConstraint(SpringLayout.SOUTH, button, 0, SpringLayout.SOUTH, panel);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(500,375));
		button.addActionListener((
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JCheckBoxScrollableListSelect.super.dispose();
						panel.setBackground(null);
					}
				}
		));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
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
		
		
		@SuppressWarnings("unused")
		JCheckBoxScrollableListSelect checkBoxScrollableListSelect = new JCheckBoxScrollableListSelect("Prueba",c);
	}
}
