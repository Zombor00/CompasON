package usuarios;

/**
* Esta clase contiene la informacion comun a todos los
* usuarios que tienen cuenta en la aplicacion
*
* @author Alvaro Zaera alvaro.zaeradela@estudiante.uam.es
* @version 1.0 (05-03-2019)
*
*/
public abstract class UsuarioConCuenta{
    private String nombreUsuario;
    private String contrasenia;

    /**
    * Constructor, con el nombre de usuario y contrasenia
    *
    * @param nombreUsuario
    * @param contrasenia del usuario
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

    public void setNombreUsuario(String nombreUsuario){
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasenia(String contrasenia){
        this.contrasenia = contrasenia;
    }

    


}
