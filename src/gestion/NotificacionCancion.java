package gestion;

import java.io.Serializable;

import media.Cancion;

/**
 * Esta clase contiene la informacion de las notificaciones default: canciones
 * nuevas
 *
 * @author Alvaro Zaera alvaro.zaeradela@estudiante.uam.es
 * @version 1.0 (07-03-2019)
 *
 */
public class NotificacionCancion extends Notificacion implements Serializable {

	/**
	 * Constructor, con la cancion
	 *
	 * @param cancion a notificar
	 */
	public NotificacionCancion(Cancion cancion) {
		super(cancion);
	}

	@Override
	public String toString() {
		return "Nueva cancion de " + cancion.getAutor().getNombreUsuario() + ": " + cancion.getTitulo();
	}

}
