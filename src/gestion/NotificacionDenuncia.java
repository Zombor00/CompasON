package gestion;

import java.io.Serializable;

import media.Cancion;

/**
* Esta clase contiene la informacion de las notificaciones
* de notificaciones de canciones denunciadas
*
* @author Alvaro Zaera alvaro.zaeradela@estudiante.uam.es
* @version 1.0 (07-03-2019)
*
*/
public class NotificacionDenuncia extends Notificacion implements Serializable{

    /**
    * Constructor, con la cancion denunciada
    *
    * @param cancion
    */
    public NotificacionDenuncia(Cancion cancion){
        super(cancion);
    }

	@Override
	public String toString() {
		return "Tu cancion titulada " + cancion.getTitulo() + " ha sido denunciada por plagio.";
	}

}
