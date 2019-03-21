package gestion;

/**
* Esta clase contiene la informacion de las notificaciones default:
* canciones nuevas
*
* @author Alvaro Zaera alvaro.zaeradela@estudiante.uam.es
* @version 1.0 (07-03-2019)
*
*/
public class NotificacionDenuncia extends Notificacion{
    private Denuncia denuncia;

    /**
    * Constructor, con la denuncia
    *
    * @param denuncia a notificar
    */
    public NotificacionDenuncia(Denuncia denuncia){
        this.denuncia = denuncia;
    }

}
