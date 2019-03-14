/**
 * Esta clase contiene todos los atributos y metodos comunes a los elementos
 * reproducibles.
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */


public enum estado{
 SINVALIDAR,EXPLICITO,APTOMENORES;
}


public abstract class Elemento implements Serializable{

  private String titulo;
  private Time duracion;
  private boolean bloqueado;
  private boolean borrado ;


  /**
       * Constructor de la clase elemento que se encarga de poner titulo al
       * elemento y de inicializar a false el atributo bloqueado y borrado.
       * @param titulo String que identifica el titulo de la cancion
       */
  public Elemento(String titulo){
      this.titulo = titulo;
      this.bloqueado = false;
      this.borrado = false;
  }

  public abstract boolean contieneElemento(Elemento e);

  public abstract reproducir();

  public String getTitulo(){
      return this.titulo;
  }

  public Time getDuracion(){
      return this.duracion;
  }

  public boolean getBloqueado(){
      return this.bloqueado;
  }

  public boolean getBorrado(){
      return this.borrado;
  }

  public void setTitulo(String titulo){
      this.titulo = titulo;
  }

  public void setBloqueado(boolean b){
      this.bloqueado = b;
  }

  public void setBorrado(boolean b){
      this.borrado = b;
  }

}
