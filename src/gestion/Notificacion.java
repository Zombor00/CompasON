package gestion;

import java.io.*;

import media.Cancion;

/**
* Esta clase contiene la informacion comun a las notificaciones
*
* @author Alvaro Zaera alvaro.zaeradela@estudiante.uam.es
* @version 1.0 (07-03-2019)
*
*/
public abstract class Notificacion implements Serializable{
    protected Cancion cancion;

    /**
    * Constructor, con la cancion
    *
    * @param cancion a notificar
    */
    public Notificacion(Cancion cancion){
        this.cancion = cancion;
    }
    
    public Cancion getCancion() {
    	return this.cancion;
    }


}
