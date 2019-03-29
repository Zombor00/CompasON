package media;

import java.io.*;
import java.util.*;
import excepciones.ExcepcionCancionNoContenida;
import excepciones.ExcepcionCancionYaContenida;
import excepciones.ExcepcionReproducirProhibido;
import pads.musicPlayer.Mp3Player;

/**
 * Esta clase tiene toda la informacion relevante a las listas.
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class Lista extends Reproducible implements Serializable{

    private ArrayList <Reproducible> reproducibles;
    private ArrayList <Lista> contenidaEn;

    /**
     * Constructor de la clase lista que inicializa el array de reproducibles y
     * pone titulo a la lista.
     * @param titulo string que identifica el titulo de la lista
     */
    public Lista(String titulo){
        super(titulo);
        this.reproducibles = new ArrayList<Reproducible>();
        this.contenidaEn = new ArrayList<>();
    }

    /**
     * Constructor de la clase lista que inicializa el array de reproducibles y
     * pone titulo a la lista.
     * @param titulo string que identifica el titulo de la lista
     * @param reproducibles array de Reproducibles a meter en el album
     */
    public Lista(String titulo,ArrayList <Reproducible> reproducibles){
        this(titulo);
        double duracion = 0;
        this.reproducibles=reproducibles;
        for(Reproducible r: reproducibles) {
        	duracion += r.getDuracion();
        	r.aniadirContenidoEn(this);
        }
        this.setDuracion(duracion);
    }

    /**
     * Reproduce una lista en orden entrando recursivamente en las listas
     * contenidas en las listas
     * @param mp3 Cola donde se añade la cancion
     * @throws ExcepcionReproducirProhibido 
     */
    @Override
    public int reproducir(Mp3Player mp3) throws ExcepcionReproducirProhibido{
    	int reproducciones = 0;
    	if (this.getEstado() != Estado.NOBLOQUEADO) {
    		throw new ExcepcionReproducirProhibido();
    	}
        for(Reproducible r: reproducibles){
            reproducciones += r.reproducir(mp3);
        }
        return reproducciones;
    }

    /**
     * Sirve para meter un reproducible pasado como argumento en la lista
     * @param r Cancion a aniadir en el album
     * @return false si el Reproducible ya esta en la lista true en caso contrario
     */
    public boolean aniadirReproducible(Reproducible r) throws ExcepcionCancionYaContenida{


        if(this.contenidoEn(r)) {
        	throw new ExcepcionCancionYaContenida();
        }
        reproducibles.add(r);
        r.aniadirContenidoEn(this);
        return true;
    }

    /**
     * Sirve para quitar un reproducible de la lista
     * @param r Reproducible a quitar de la lista
     * @return true si existe el reproducible a quitar false en caso contrario
     */
    public boolean quitarReproducible(Reproducible r) throws ExcepcionCancionNoContenida{
        int index;

        index = reproducibles.indexOf(r);
        if(index == -1) {
        	throw new ExcepcionCancionNoContenida();
        }

        reproducibles.remove(index);
        r.quitarContenidoEn(this);
        return true;
    }

    /**
     * Te devuelve true si el reproducible existe en el album y false si no
     * existe el reproducible
     * @param r reproducible a buscar en la lista
     * @return boolean: true si existe el reproducible en el album false
     * en caso contrario
     */

    @Override
    public boolean contieneReproducible(Reproducible r){

        for(Reproducible eLista: reproducibles){
            if(eLista.contieneReproducible(r)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void setEstado(Estado estado){
      if (estado == Estado.BLOQUEADO) return;
      this.setEstado(estado);
    }

    public ArrayList<Lista> getContenidoEn() {
    	return this.contenidaEn;
    }

    public void aniadirContenidoEn(Lista lista) {
		this.contenidaEn.add(lista);
	}

    public void quitarContenidoEn(Lista lista) {
		this.contenidaEn.remove(lista);
	}

    /**
     * Devuelve true si en cualquier lista padre(y recursivamente padres e hijos) el elemento reproducible r
     * esta contenido. False en caso contrario
     * @param r: reproducible a buscar en las listas padre
     */
    public boolean contenidoEn(Reproducible r) {
    	boolean aux = false;
    	if(this.getContenidoEn().isEmpty()) {
    		if(this.contieneReproducible(r)) {
    			aux = true;
    		}
    	}else {
    		for(Lista l: this.getContenidoEn()) {
    			if(l.contenidoEn(r)) {
    				aux = true;
    			}
    		}
    	}
    	return aux;
    }

    @Override
	public boolean esAptoParaMenores() {
        for(Reproducible r: reproducibles){
            if(!r.esAptoParaMenores()){
                return false;
            }
        }
        return true;
	}
    
	@Override
	public String toString() {
		return "Lista [titulo=" + this.getTitulo() + " elementos=" + reproducibles + "]";
	}
   

}
