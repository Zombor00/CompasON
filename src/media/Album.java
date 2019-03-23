package media;

import java.io.*;
import java.util.*;
import java.time.*;
import pads.musicPlayer.Mp3Player;

/**
 * Esta clase tiene toda la informacion relevante a los Albumes.
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class Album extends Buscable implements Serializable{

    private ArrayList <Cancion> canciones;
    private LocalDate anio;

    /**
     * Constructor de la clase album que inicializa el array de canciones y
     * pone titulo al album.
     * @param titulo string que identifica el titulo del album
     */
    public Album(String titulo,LocalDate anio){
        this(titulo, anio, new ArrayList<Cancion>() );
    }

    /**
    * Constructor de la clase album que pone titulo al album y al que se le pasa
     las canciones que tendrá el album inicialmente.
     * @param titulo string que identifica el titulo del album
     * @param canciones array de canciones a meter en el album
     */
    public Album(String titulo, LocalDate anio, ArrayList <Cancion> canciones){
        super(titulo);
        this.anio = anio;
        this.canciones = canciones;
    }

    /**
     * Reproduce un album en orden
     * @param mp3 Cola donde se añade la cancion
     */

    public void reproducir(Mp3Player mp3){
        for(Cancion c: canciones){
            c.reproducir(mp3);
        }
    }

    /**
     * Sirve para meter la cancion pasada como argumento en el album
     * @param c Cancion a aniadir en el album
     * @return false si la cancion ya esta en el album true en caso contrario
     */
    public boolean aniadirCancion(Cancion c){
        if(canciones.contains(c))return false;
        canciones.add(c);
        return true;
    }

    /**
     * Sirve para quitar una cancion del album
     * @param c Cancion a quitar del album
     * @return true si existe la cancion a quitar false en caso contrario
     */
    public boolean quitarCancion(Cancion c){
        int index;

        index = canciones.indexOf(c);
        if(index == -1)return false;

        canciones.remove(index);

        if(this.canciones.size() == 0){
            this.estado = Estado.BORRADO;
        }

        return true;
    }

    /**
     * Te devuelve true si la cancion existe en el album y false si no
     * existe la cancion
     * @param c Cancion a buscar en el album
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
		return "Album [titulo=" + this.getTitulo() + "canciones=" + canciones + ", anio=" + anio + "]";
	}



}
