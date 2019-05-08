package media;

import java.io.*;

import java.util.*;
import excepciones.ExcepcionCancionNoContenida;
import excepciones.ExcepcionInsercionInvalida;
import excepciones.ExcepcionReproducibleNoValido;
import pads.musicPlayer.Mp3Player;
import pads.musicPlayer.exceptions.Mp3InvalidFileException;
import usuarios.UsuarioRegistrado;

/**
 * Esta clase tiene toda la informacion relevante a las listas.
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class Lista extends Reproducible implements Serializable{

    private ArrayList <Reproducible> reproducibles;
    private Set <Lista> padres;

    /**
     * Constructor de la clase lista que inicializa el array de reproducibles y
     * pone titulo a la lista.
     * @param titulo string que identifica el titulo de la lista
     */
    public Lista(String titulo){
        super(titulo);
        this.reproducibles = new ArrayList<Reproducible>();
        this.padres = new HashSet<>();
    }

    /**
     * Constructor de la clase lista que inicializa el array de reproducibles y
     * pone titulo a la lista.
     * @param titulo string que identifica el titulo de la lista
     * @param reproducibles array de Reproducibles a meter en el album
     * @throws ExcepcionInsercionInvalida
     * @throws ExcepcionReproducibleNoValido
     */
    public Lista(String titulo,ArrayList <Reproducible> reproducibles) throws ExcepcionInsercionInvalida, ExcepcionReproducibleNoValido{
        this(titulo);
        double duracion = 0;
        for(Reproducible r: reproducibles) {
        	this.aniadirReproducible(r);
        	duracion += r.getDuracion();
        	r.aniadirPadre(this);
        }
        this.setDuracion(duracion);
    }

    /**
     * Reproduce una lista en orden entrando recursivamente en las listas
     * contenidas en las listas
     * @param mp3 Cola donde se añade la cancion
     * @throws ExcepcionReproducirProhibido
     * @throws Mp3InvalidFileException
     */
    @Override
    public int reproducir(Mp3Player mp3, UsuarioRegistrado usuarioLogeado) throws Mp3InvalidFileException{
    	int reproducciones = 0;
        for(Reproducible r: reproducibles){
        	if(r.getEstado() != Estado.BORRADO) {
                reproducciones += r.reproducir(mp3,usuarioLogeado);
        	}
        }
        return reproducciones;
    }

    /**
     * Sirve para meter un reproducible pasado como argumento en la lista
     * @param r Cancion a aniadir en el album
     * @throws ExcepcionInsercionInvalida
     * @throws ExcepcionReproducibleNoValido
     */
    public void aniadirReproducible(Reproducible r) throws ExcepcionInsercionInvalida,ExcepcionReproducibleNoValido{
    	
        if(!r.sePuedeMeterEn(this)) { /* this.esViableAniadir(r) */
        	throw new ExcepcionInsercionInvalida(); /* InsercionInvalida */
        }
        
        this.incrementarDuracion(r.getDuracion());
        reproducibles.add(r);
        r.aniadirPadre(this);
    }
    
    public void incrementarDuracion(double incr) {
    	this.setDuracion(this.getDuracion() + incr);
    	for (Lista l : padres) {
    		l.incrementarDuracion(incr);
    	}
    }

    /**
     * Sirve para quitar un reproducible de la lista
     * @param r Reproducible a quitar de la lista
     * @throws ExcepcionCancionNoContenida
     */
    public void quitarReproducible(Reproducible r) throws ExcepcionCancionNoContenida{
        int index;

        index = reproducibles.indexOf(r);
        if(index == -1) {
        	throw new ExcepcionCancionNoContenida();
        }
        
        this.incrementarDuracion(-r.getDuracion());
        reproducibles.remove(index);
        r.quitarPadre(this);
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

    public Set<Lista> getPadres() {
    	return this.padres;
    }

    public void aniadirPadre(Lista lista) {
		this.padres.add(lista);
	}

    public void quitarPadre(Lista lista) {
		this.padres.remove(lista);
	}

    @Override
    public boolean sePuedeMeterEn(Lista l) {
    	for(Reproducible r: this.reproducibles) {
    		if(!r.sePuedeMeterEn(l)) {
    			return false;
    		}
    	}
    	return true;
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

	@Override
    public boolean esValido() {
    	for (Reproducible r : this.reproducibles) {
    		if (r.esValido() == false) {
    			return false;
    		}
    	}
    	return true;
	}

	@Override
	public void setEstado(Estado estado){
		  if (estado == Estado.BORRADO) {
			  return;
		  }
	      super.setEstado(estado);
	  }

	public ArrayList<Reproducible> getReproducibles(){
		return this.reproducibles;
	}


}
