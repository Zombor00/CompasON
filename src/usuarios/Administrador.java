
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

    /**
    * Este metodo se usa para tramitar la validacion de una cancion
    *
    * @param c cancion a tramitar
    * @param estado indica si es explicito, no explicito o no validado
    *
    */
    public void tramitarValidacion(Cancion c, Estado estado){
        c.validar(estado);
        cancionesAValidar.remove(cancionesAValidar.index(c));
    }

    /**
    * Este metodo se usa para tramitar una denuncia
    *
    * @param d denuncia a tramitar
    * @param ganada indica si la denuncia tiene fundamento
    *
    */
    public void tramitarDenuncia(Denuncia d, boolean plagio){
        UsuarioRegistrado autor = d.getDenunciada.getAutor();
        UsuarioRegistrado denunciante = d.getDenunciante();
        if(plagio){
            autor.setBloqueadoHasta(LocalDate.MAX);
        }
        else{
            autor.setBloqueadoHasta(LocalDate.now().plusDays(30));
            ArrayList<Buscable> buscables = autor.getBuscables();
            /*To do: bucle hiper tocho*/
        }
        denuncias.remove(denuncias.index(d));
    }

}
