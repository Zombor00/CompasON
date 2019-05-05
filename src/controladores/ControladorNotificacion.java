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

public class ControladorNotificacion implements ListSelectionListener{

	private Aplicacion aplicacion;
	private Notificaciones notificaciones;
	private GuiAplicacion gui;
	
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
        Notificacion n = (Notificacion)tablaNotificaciones.getValueAt(fila, 1);
        aplicacion.getUsuarioLogeado().borrarNotificacion(n);
        notificaciones.getUsuarioNotificacion().removeRow(fila);
        if(n instanceof NotificacionDenuncia) {
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
