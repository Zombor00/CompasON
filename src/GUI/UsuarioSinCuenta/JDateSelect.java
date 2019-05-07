package GUI.UsuarioSinCuenta;

import java.time.LocalDate;

import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * Esta clase tiene una estructura para elegir una fecha
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class JDateSelect extends JPanel{
	
	/**
	 * ComboBox con los dias a elegir 
	 */
	private JComboBox<Integer> comboDias;
	/**
	 * ComboBox con los meses a elegir 
	 */
	private JComboBox<String> comboMeses;
	/**
	 * ComboBox con los anios a elegir 
	 */
	private JComboBox<Integer> comboAnios;
	
	/**
	 * Constructor de los elementos para elegir la fecha
	 *
	 */
	public JDateSelect() {
		Integer[] dias = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
		comboDias = new JComboBox<>(dias);		
		this.add(comboDias);
		
		String[] meses = {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
		comboMeses = new JComboBox<>(meses);		
		this.add(comboMeses);
		
		Integer[] anios = {2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009,2008,2007,2006,2005,2004,2003,2002,2001,2000,1999,1998,1997,1996,1995,1994,1993,1992,1991,1990,1989,1988,1987,1986,1985,1984,1983,1982,1981,1980,1979,1978,1977,1976,1975,1974,1973,1972,1971,1970,1969,1968,1967,1966,1965,1964,1963,1962,1961,1960,1959,1958,1957,1956,1955,1954,1953,1952,1951,1950};
		comboAnios = new JComboBox<>(anios);		
		this.add(comboAnios);
		
	}
	
	public LocalDate getDate() {		
		return LocalDate.of( (Integer) comboAnios.getSelectedItem(), comboMeses.getSelectedIndex() + 1, (Integer) comboDias.getSelectedItem());
	}
	
	public JComboBox<Integer> getAnios(){
		return comboAnios;
	}

}
