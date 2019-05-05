package GUI.AccesoComun;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import GUI.GuiAplicacion;

public class JCheckBoxScrollableListSelect extends JDialog implements ActionListener {

	public JCheckBoxScrollableListSelect(String title, ArrayList<String> elementos,
			JTextField textField, ArrayList<Integer> seleccionadas, JButton boton) {
		
		/* Invoco al constructor de la clase superior */
		super(GuiAplicacion.getInstance());
		setTitle(title);
        
		//Botón
		JButton button = new JButton("Close");
	    button.addActionListener(this);
	    add(button);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(500,500,500,500);
		panel.add(button);
		add(panel);
		
		JCheckBoxScrollableList checkBoxScrollableList = 
				new JCheckBoxScrollableList(new JCheckBoxList(elementos));
		panel.add(checkBoxScrollableList);
		
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, checkBoxScrollableList, 0, SpringLayout.HORIZONTAL_CENTER, panel);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, checkBoxScrollableList, -10, SpringLayout.VERTICAL_CENTER, panel);
		layout.putConstraint(SpringLayout.EAST, button, 0, SpringLayout.EAST, panel);
		layout.putConstraint(SpringLayout.SOUTH, button, 0, SpringLayout.SOUTH, panel);
		
		
		
		pack();
		setVisible(true);
	    setBounds(500,375,500,375);
		setLocationRelativeTo(null);
		
		button.addActionListener((
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						seleccionadas.clear();
						seleccionadas.addAll(checkBoxScrollableList.getSelectedIndices());
						if(textField != null) textField.setText(seleccionadas.toString());
						if(boton != null) boton.doClick();
					}
				}
		));
        
		  
		/*this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		JCheckBoxScrollableList checkBoxScrollableList = 
				new JCheckBoxScrollableList(new JCheckBoxList(elementos));
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
		this.pack();
		
		this.setSize(new Dimension(500,375));
		button.addActionListener((
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					System.out.println("HOLA");
						seleccionadas.clear();
						seleccionadas.addAll(checkBoxScrollableList.getSelectedIndices());
						if(textField != null) textField.setText(seleccionadas.toString());
						button.setVisible(false);
					}
				}
		));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		*/
	}
	
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
	
}
