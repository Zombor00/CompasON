package media;

import java.io.*;

import java.util.*;
import excepciones.ExcepcionCancionNoContenida;
import excepciones.ExcepcionInsercionInvalida;
import excepciones.ExcepcionReproducirProhibido;
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
    private ArrayList <Lista> padres;

    /**
     * Constructor de la clase lista que inicializa el array de reproducibles y
     * pone titulo a la lista.
     * @param titulo string que identifica el titulo de la lista
     */
    public Lista(String titulo){
        super(titulo);
        this.reproducibles = new ArrayList<Reproducible>(); 
        this.padres = new ArrayList<>(); /* A esto llamalo padres */
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
    public int reproducir(Mp3Player mp3, UsuarioRegistrado usuarioLogeado) throws ExcepcionReproducirProhibido, Mp3InvalidFileException{
    	int reproducciones = 0;
    	if (this.getEstado() != Estado.NOBLOQUEADO) {
    		throw new ExcepcionReproducirProhibido();
    	}
        for(Reproducible r: reproducibles){
            reproducciones += r.reproducir(mp3,usuarioLogeado);
        }
        return reproducciones;
    }

    /**
     * Sirve para meter un reproducible pasado como argumento en la lista
     * @param r Cancion a aniadir en el album
     * @return false si el Reproducible ya esta en la lista true en caso contrario
     */
    public boolean aniadirReproducible(Reproducible r) throws ExcepcionInsercionInvalida{
    	
    	if(r.esValido()==false) {
    		return false;
    	}

        if(!r.sePuedeMeterEn(this)) { /* this.esViableAniadir(r) */
        	throw new ExcepcionInsercionInvalida(); /* InsercionInvalida */
        }
        reproducibles.add(r);
        r.aniadirPadre(this);
        return true;
    }

    /**
     * Sirve para quitar un reproducible de la lista
     * @param r Reproducible a quitar de la lista
     * @return true si existe el reproducible a quitar false en caso contrario
     */
    public void quitarReproducible(Reproducible r) throws ExcepcionCancionNoContenida{
        int index;

        index = reproducibles.indexOf(r);
        if(index == -1) {
        	throw new ExcepcionCancionNoContenida();
        }

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

    public ArrayList<Lista> getPadres() {
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
		return this.getReproducibles();
	}
   

}
