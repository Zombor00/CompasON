/**
* Esta clase contiene la informacion comun a las notificaciones
*
* @author Alvaro Zaera alvaro.zaeradela@estudiante.uam.es
* @version 1.0 (07-03-2019)
*
*/
public abstract class Notificacion{
    private boolean visible = true;

    public String mostrarNotificacion(){
        if(visible == true){
            return this.toString();
        }
        return null;
    }

    public void ocultarNotificacion(){
        visible=false;
    }


}
