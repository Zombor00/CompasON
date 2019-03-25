package media;

import java.io.*;
import pads.musicPlayer.Mp3Player;


/**
 * Esta clase contiene todos los atributos y metodos comunes a los elementos
 * reproducibles.
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */




public abstract class Reproducible implements Serializable{

  private String titulo;
  private double duracion;
  private Estado estado;

  /**
       * Constructor de la clase Reproducible que se encarga de poner titulo al
       * elemento y de inicializar a false el atributo bloqueado y borrado.
       * @param titulo String que identifica el titulo de la cancion
       */
  public Reproducible(String titulo){
      this.titulo = titulo;
      this.estado = Estado.NOBLOQUEADO;
      this.duracion = 0;
  }

  public abstract boolean contieneReproducible(Reproducible e);

  public abstract void reproducir(Mp3Player mp3);

  public String getTitulo(){
      return this.titulo;
  }

  public double getDuracion(){
      return this.duracion;
  }

  public Estado getEstado(){
      return this.estado;
  }

  public void setTitulo(String titulo){
      this.titulo = titulo;
  }

  public void setEstado(Estado estado){
      this.estado = estado;
  }
  
  public void setDuracion(double duracion){
      this.duracion = duracion;
  }
  
  public boolean esAptoParaMenores() {
	  /* Esto hay que sobreescribirlo en las subclases 
	   * Una cancion es apta si su estadoValidacion es APTOMENORES
	   * Un album o lista es apto si todos los reproducibles que contienen son aptos*/
	  return true;
  }

}
