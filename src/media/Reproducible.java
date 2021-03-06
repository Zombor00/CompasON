package media;

import java.io.Serializable;
import java.lang.Math;
import pads.musicPlayer.Mp3Player;
import pads.musicPlayer.exceptions.Mp3InvalidFileException;
import usuarios.UsuarioRegistrado;

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

  public abstract int reproducir(Mp3Player mp3, UsuarioRegistrado usuarioLogeado) throws Mp3InvalidFileException;

  public String getTitulo(){
      return this.titulo;
  }
  
  public String getTituloExplicito() {
	  String aux = "";
	  if(this.esValido() && !this.esAptoParaMenores()) {
		  aux = "E-";
	  }
	  return aux + this.getTitulo();
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
	  if (this.estado == Estado.BORRADO) {
		  return;
	  }
      this.estado = estado;
  }

  public void setDuracion(double duracion){
      this.duracion = Math.floor(duracion);
  }

  /**
   * Si es una lista aniade al array de listas que la contienen una lista.
   * Si no es una lista no hace nada.
   * @param lista lista en la que esta contenida
   */
  public abstract void aniadirPadre(Lista lista);
  
  
  /**
   * Devuelve True si el elemento reproducible es apto para menores y
   * False en caso contrario.
   * @return boolean
   */
  public abstract boolean esAptoParaMenores();
  
  /**
   * Devuelve True si es valido
   * 
   * @return boolean
   */
  public abstract boolean esValido();


  /**
   * Si es una lista del array de listas que la contienen una lista
   * Si no es una lista no hace nada
   * @param lista lista en la que deja de estar contenida
   */
  public abstract void quitarPadre(Lista lista);
  
  /**
   * Devuelve true si en cualquier lista padre(y recursivamente padres e hijos) el elemento reproducible 
   * esta contenido en l. False en caso contrario
   * @param l lista a buscar
   * @return boolean
   */
  public abstract boolean sePuedeMeterEn(Lista l);
  
  public String parseSeconds(Double secs) {
	  int segundos = (int)Math.round(secs);
	  int minutos = (segundos - (segundos%60)) / 60;
	  segundos = segundos %60;
	  String segundosString = String.valueOf(segundos);
	  if(segundosString.length() == 1) {
		  segundosString = "0" + segundosString;
	  }
	  return String.valueOf(minutos) + ":" + segundosString;
}
  
}
