package usuarios;

import java.io.*;
import java.util.ArrayList;
import media.Cancion;

/**
* Esta clase contiene la informacion comun a todos los
* usuarios que tienen cuenta en la aplicacion
*
* @author Alvaro Zaera alvaro.zaeradela@estudiante.uam.es
* @version 1.0 (05-03-2019)
*
*/
public abstract class UsuarioConCuenta implements Serializable{
    private String nombreUsuario;
    private String contrasenia;
    protected ArrayList<Cancion> cancionesNuevas = new ArrayList<>();

    /**
    * Constructor, con el nombre de usuario y contrasenia
    *
    * @param nombreUsuario nombre de usuario
    * @param contrasenia clave del usuario
    */
    public UsuarioConCuenta(String nombreUsuario, String contrasenia){
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

    public String getNombreUsuario(){
        return this.nombreUsuario;
    }

    public String getContrasenia(){
        return this.contrasenia;
    }

    public ArrayList<Cancion> getCancionesNuevas() {
		return cancionesNuevas;
	}
    

	public void setNombreUsuario(String nombreUsuario){
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasenia(String contrasenia){
        this.contrasenia = contrasenia;
    }
    
    /**
    * Este metodo se usa para aniadir una cancion pendiente
    * de validar
    *
    * @param c cancion a aniadir
    * @return boolean sobre si se hace correctamente
    */
    public boolean aniadirCancion(Cancion c){
        return this.cancionesNuevas.add(c);
    }
    
    public String toString() {
    	return this.nombreUsuario;
    }


}
