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
	 
	 private UsuarioRegistrado autor;

     public Buscable(String titulo, UsuarioRegistrado autor){
         super(titulo);
         this.autor = autor;
     }
   
     /**
      * Desbloquea la cancion c y el album donde este la cancion
      * c si no tiene otras canciones bloqueadas.
      *@param c cancion a desbloquear
      */
     public abstract void desbloquear(Cancion c);
     
     public UsuarioRegistrado getAutor() {
    	 return this.autor;
     }
     
}
