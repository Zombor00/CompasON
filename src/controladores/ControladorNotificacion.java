package controladores;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import GUI.GuiAplicacion;
import GUI.AccesoComun.Notificaciones;
import aplicacion.Aplicacion;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;
import gestion.Notificacion;
import gestion.NotificacionDenuncia;
import media.Buscable;

/**
 * Esta clase tiene toda la informacion relevante al controlador
 * de notificacion: todas las acciones que puede haber en ese panel
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class ControladorNotificacion implements ListSelectionListener{

	/**
	 * Aplicacion con la informacion
	 */
	private Aplicacion aplicacion;
	/**
	 * Panel de notificaciones suceden los eventos
	 */
	private Notificaciones notificaciones;
	/**
	 * Interfaz grafica de la aplicacion
	 */
	private GuiAplicacion gui;
	
	/**
	 * Constructor del controlador con notificaciones
	 * @param notificaciones panel donde actua el controlador
	 */
	public ControladorNotificacion(Notificaciones notificaciones) {
		this.notificaciones = notificaciones;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (aplicacion == null) aplicacion = Aplicacion.getInstance();
		if (gui == null) gui = GuiAplicacion.getInstance();
		ArrayList<Buscable> buscables = null;
		JTable tablaNotificaciones = notificaciones.getTablaNotificaciones();
		
        int fila = tablaNotificaciones.getSelectedRow();
        if(fila == -1) {
        	return;
        }
        
        /* Al tocar una notificacion, si es de cancion nueva de un usuario al que sigues,
         * te lleva a la busqueda con esa cancion buscada y si es de denuncia te lleva a tus
         * canciones señalando que cancion ha sido bloqueada por la denuncia.
         * Posteriormente se borra la notificacion.
         */
        
        Notificacion n = (Notificacion)tablaNotificaciones.getValueAt(fila, 1);
        aplicacion.getUsuarioLogeado().borrarNotificacion(n);
        notificaciones.getUsuarioNotificacion().removeRow(fila);
        if(n instanceof NotificacionDenuncia) {
        	gui.mostrarDenunciada(n.getCancion() ,gui.getPanelesUsuarios().getActual());
        	return;
        }else {
        	try {
    			buscables = aplicacion.buscarPorTitulo(n.getCancion().getTitulo());
    		} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
    			e1.printStackTrace();
    		}
        }
        
		gui.actualizarBusqueda(buscables, gui.getPanelesUsuarios().getActual());
		
	}
}
