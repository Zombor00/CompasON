package media;

import java.io.*;

import usuarios.UsuarioRegistrado;

/**
 * Esta clase contiene todos los atributos y metodos comunes a los elementos
 * buscables.
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */

 public abstract class Buscable extends Reproducible implements Serializable{

     public Buscable(String titulo){
         super(titulo);
     }
   
     /**
      * Desbloquea la cancion c y el album donde este la cancion
      * c si no tiene otras canciones bloqueadas.
      *@param c cancion a desbloquear
      */
     public abstract void desbloquear(Cancion c);
     
     public abstract UsuarioRegistrado getAutor();
     
}
