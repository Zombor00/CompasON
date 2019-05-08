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

/**
 * Dialogo que muestra una lista con scroll de componentes de tipo JCheckBox
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 */
public class JCheckBoxScrollableListSelect extends JDialog implements ActionListener {

	/**
	 * Constructor de la clase
	 * @param title Titulo del dialogo
	 * @param elementos Lista de nombres que definen los checkboxes
	 * @param textField Campo de texto en el que se imprime "seleccionadas"
	 * @param seleccionadas Lista donde se añaden los indices de los checkboxes seleccionadas
	 * @param externo boton Boton al que se hace click cuando el usuario termina la seleccion.
	 * Esto permite asociar un comportamiento externo al panel.
	 */
	public JCheckBoxScrollableListSelect(String title, ArrayList<String> elementos,
			JTextField textField, ArrayList<Integer> seleccionadas, JButton externo) {
		
		super(GuiAplicacion.getInstance());
		
		/* Actualizamos el titulo del dialogo */
		setTitle(title);
		JButton button = new JButton("OK");
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
		
		/* Al presionar button actualizamos seleccionadas, textField y 
		 * hacemos click en el boton externo que se pasa como parametro */
		button.addActionListener((
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						seleccionadas.clear();
						seleccionadas.addAll(checkBoxScrollableList.getSelectedIndices());
						if(textField != null) textField.setText(seleccionadas.toString());
						if(externo != null) externo.doClick();
					}
				}
		));
        
	}
	
	/**
	 * Al presionar button, el dialogo se cierra
	 */
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
	
}
