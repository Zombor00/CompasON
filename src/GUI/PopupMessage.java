package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class PopupMessage extends JDialog implements ActionListener {

	public PopupMessage(String message) {
		
		super(GuiAplicacion.getInstance(),"Mensaje");

		JLabel label = new JLabel(message);
		add(label);
		JButton button = new JButton("close");
		button.addActionListener(this);
		add(button);
		pack();
		setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(400,150,400,150);
		panel.add(button);
		panel.add(label);
		add(panel);
		
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, label, 0, SpringLayout.HORIZONTAL_CENTER, panel);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, label, -10, SpringLayout.VERTICAL_CENTER, panel);
		layout.putConstraint(SpringLayout.EAST, button, 0, SpringLayout.EAST, panel);
		layout.putConstraint(SpringLayout.SOUTH, button, 0, SpringLayout.SOUTH, panel);

		pack();
		setVisible(true);
		setBounds(400, 150, 400, 150);
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		dispose();
	}

}
