package GUI.AccesoComun;

import javax.swing.table.DefaultTableModel;

public class DefaultTableModelNoEditable extends DefaultTableModel{
	
    public DefaultTableModelNoEditable(Object[][] filas, String[] titulos) {
    	super(filas,titulos);
	}
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
