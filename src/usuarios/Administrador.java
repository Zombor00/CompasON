
package usuarios;
import java.io.*;
import java.time.*;
import java.util.*;
import media.*;
import gestion.*;
import excepciones.*;

/**
* Esta clase contiene la informacion necesaria para
* gestionar al administrador
*
* @author Alvaro Zaera alvaro.zaeradela@estudiante.uam.es
* @version 1.0 (07-03-2019)
*
*/
public class Administrador extends UsuarioConCuenta implements Serializable{
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
    public void tramitarValidacion(Cancion c, EstadoValidacion estadoValidacion) throws ExcepcionCancionModificable, ExcepcionCancionYaValidada{
    	if (c.getEstadoValidacion()==EstadoValidacion.APTOMENORES || c.getEstadoValidacion()==EstadoValidacion.EXPLICITO) {
    		throw new ExcepcionCancionYaValidada();
    	}
    	if (c.getModificableHasta() != null && c.getModificableHasta().isAfter(LocalDate.now())) {
    		throw new ExcepcionCancionModificable();
    	}
    	
        c.validar(estadoValidacion);
    }

    /**
    * Este metodo se usa para tramitar una denuncia
    *
    * @param d denuncia a tramitar
    * @param plagio indica si la denuncia tiene fundamento
    *
    */
    public void tramitarDenuncia(Denuncia d, boolean plagio){
        Cancion c = d.getDenunciada();
        UsuarioRegistrado autor = c.getAutor();
        UsuarioRegistrado denunciante = d.getDenunciante();
        if(plagio){
            autor.setBloqueadoHasta(LocalDate.MAX);
        }
        else{
            denunciante.setBloqueadoHasta(LocalDate.now().plusDays(30));
            ArrayList<Buscable> buscables = autor.getBuscables();
            for (Buscable b : buscables) {
                if (b.contieneReproducible(c)) {
                    b.desbloquear(c);
                }
            }
        }
        denuncias.remove(d);
    }
    

}
