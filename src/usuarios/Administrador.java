
import java.util.*;

/**
* Esta clase contiene la informacion necesaria para
* gestionar al administrador
*
* @author Alvaro Zaera alvaro.zaeradela@estudiante.uam.es
* @version 1.0 (07-03-2019)
*
*/
public class Administrador extends UsuarioConCuenta{
    private ArrayList<Cancion> cancionesAValidar = new ArrayList<>();
    private ArrayList<Denuncia> denuncias = new ArrayList<>();

    /**
    * Constructor, con el nombre de usuario y contrasenia
    *
    * @param nombreUsuario
    * @param contrasenia del usuario
    */
    public Administrador(String nombreUsuario, String contrasenia){
        super(nombreUsuario,contrasenia);
    }

    /**
    * Este metodo se usa para aniadir una cancion pendiente
    * de validar
    *
    * @param c cancion a aniadir
    * @return boolean sobre si se hace correctamente
    */
    public boolean aniadirCancion(Cancion c){
        if (c.getValidada()){
            return false;
        }
        return this.cancionesAValidar.add(c);
    }

    /**
    * Este metodo se usa para aniadir una denuncia
    * pendiente de tramitar
    *
    * @param d denuncia a aniadir
    * @return boolean sobre si se hace correctamente
    */
    public boolean aniadirDenuncia(Denuncia d){
        return this.denuncias.add(d);
    }

}
