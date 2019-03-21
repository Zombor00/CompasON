package gestion;

import java.io.Serializable;

import media.Cancion;

/**
* Esta clase contiene la informacion de las notificaciones default:
* canciones nuevas
*
* @author Alvaro Zaera alvaro.zaeradela@estudiante.uam.es
* @version 1.0 (07-03-2019)
*
*/
public class NotificacionCancion extends Notificacion implements Serializable{
    private Cancion cancion;

    /**
    * Constructor, con la cancion
    *
    * @param cancion a notificar
    */
    public NotificacionCancion(Cancion cancion){
        this.cancion = cancion;
    }
}
