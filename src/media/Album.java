package media;

import java.io.*;
import java.util.*;
import excepciones.ExcepcionReproducirProhibido;
import excepciones.ExcepcionInsercionInvalida;
import excepciones.ExcepcionCancionNoContenida;
import excepciones.ExcepcionCancionNoValidada;

import java.time.*;
import pads.musicPlayer.Mp3Player;
import pads.musicPlayer.exceptions.Mp3InvalidFileException;
import usuarios.UsuarioRegistrado;

/**
 * Esta clase tiene toda la informacion relevante a los Albumes.
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class Album extends Buscable implements Serializable{

    private ArrayList <Cancion> canciones;
    private int anio;

    /**
     * Constructor de la clase album que inicializa el array de canciones y
     * pone titulo al album.
     * @param titulo string que identifica el titulo del album
     * @param autor usuario que ha hecho el album
     */
    public Album(String titulo, UsuarioRegistrado autor) {
        super(titulo, autor);
        this.setDuracion(0);
        this.anio = LocalDate.now().getYear();
        this.canciones = new ArrayList<>();
    }

    /**
    * Constructor de la clase album que pone titulo al album y al que se le pasa
     las canciones que tendrá el album inicialmente.
     * @param titulo string que identifica el titulo del album
     * @param autor usuario que ha hecho el album
     * @param canciones array de canciones a meter en el album
     * @throws ExcepcionInsercionInvalida
     * @throws ExcepcionCancionNoValidada
     */
    public Album(String titulo, UsuarioRegistrado autor, ArrayList <Cancion> canciones) throws ExcepcionInsercionInvalida, ExcepcionCancionNoValidada{
        this(titulo, autor);
        double duracion = 0;
        if (canciones != null) {
        	for(Cancion c: canciones) {
        		this.aniadirCancion(c);
            	duracion += c.getDuracion();
            }
        }
        this.setDuracion(duracion);
    }

    /**
     * Reproduce un album en orden
     * @param mp3 Cola donde se añade la cancion
     * @throws ExcepcionReproducirProhibido
     * @throws Mp3InvalidFileException
     */

    public int reproducir(Mp3Player mp3, UsuarioRegistrado usuarioLogeado) throws Mp3InvalidFileException{
    	int reproducciones = 0;
    	
        for(Cancion c: canciones){
        	if(c.getEstado() != Estado.BORRADO) {
                reproducciones += c.reproducir(mp3,usuarioLogeado);
        	}
        }
        return reproducciones;
    }

    /**
     * Sirve para meter la cancion pasada como argumento en el album
     * @param c Cancion a aniadir en el album
     * @throws ExcepcionInsercionInvalida
     * @throws ExcepcionCancionNoValidada
     */
    public void aniadirCancion(Cancion c) throws ExcepcionInsercionInvalida, ExcepcionCancionNoValidada{
        if(canciones.contains(c)) {
        	throw new ExcepcionInsercionInvalida();
        }
        if (c.getEstadoValidacion() == EstadoValidacion.NOVALIDADA) {
        	throw new ExcepcionCancionNoValidada();
        }
        
        canciones.add(c);
        this.setDuracion(this.getDuracion() + c.getDuracion());
    }

    /**
     * Sirve para quitar una cancion del album
     * @param c Cancion a quitar del album
     * @throws ExcepcionCancionNoContenida
     */
    public void quitarCancion(Cancion c) throws ExcepcionCancionNoContenida {
        int index;

        index = canciones.indexOf(c);
        if(index == -1) {
        	throw new ExcepcionCancionNoContenida();
        }
        
        this.setDuracion(this.getDuracion() - c.getDuracion());
        canciones.remove(index);

        if(this.canciones.stream().filter(cancion -> cancion.getEstado() == Estado.NOBLOQUEADO).count() == 0){
            this.setEstado(Estado.BORRADO);
        }
    }

    /**
     * Te devuelve true si la cancion existe en el album y false si no
     * existe la cancion
     * @param e Cancion a buscar en el album
     * @return boolean: true si existe la cancion en el album false
     * en caso contrario
     */
    @Override
    public boolean contieneReproducible(Reproducible e){
        return canciones.contains(e);
    }

    @Override
    public void desbloquear(Cancion c){
        for(Cancion can: canciones){
            can.desbloquear(c);
        }
        if(this.contBloqueadas() == 0){
            this.setEstado(Estado.NOBLOQUEADO);
        }
    }

    /**
     * Cuenta el numero de canciones bloqueadas en un album
     * @return int count: numero de canciones bloqueadas
     */
    public int contBloqueadas(){
        int count = 0;

        for(Cancion can: canciones){
            if(can.getEstado() == Estado.BLOQUEADO){
                count += 1;
            }
        }
        return count;
    }

	@Override
	public String toString() {
		return "Album [titulo=" + this.getTitulo() + " canciones=" + canciones + ", anio=" + anio + "]";
	}

	@Override
	public void aniadirPadre(Lista lista) {

	}

	@Override
	public void quitarPadre(Lista lista) {

	}

    @Override
	public boolean esAptoParaMenores() {
        for(Cancion can: canciones){
            if(can.getEstadoValidacion() != EstadoValidacion.APTOMENORES){
                return false;
            }
        }
        return true;
	}

    @Override
    public boolean esValido() {
    	for (Cancion c : this.canciones) {
    		if (!c.esValido()) {
    			return false;
    		}
    	}
    	return true;
    }

    public ArrayList<Cancion> getCanciones(){
    	return this.canciones;
    }

    @Override
    public boolean sePuedeMeterEn(Lista l) {
    	for(Cancion c: this.canciones) {
    		if(!c.sePuedeMeterEn(l)) {
    			return false;
    		}
    	}
    	return true;
    }

}
