package GUI.AccesoComun;

import javax.swing.table.DefaultTableModel;

/**
 * Esta clase crea una tabla que no es editable
 * 
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class DefaultTableModelNoEditable extends DefaultTableModel{
	
    public DefaultTableModelNoEditable(Object[][] filas, String[] titulos) {
    	super(filas,titulos);
	}
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
