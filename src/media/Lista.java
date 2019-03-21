package media;

import java.io.*;
import java.util.*;
import pads.musicPlayer.Mp3Player;

/**
 * Esta clase tiene toda la informacion relevante a las listas.
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class Lista extends Reproducible implements Serializable{

    private ArrayList <Reproducible> reproducibles;

    /**
     * Constructor de la clase lista que inicializa el array de reproducibles y
     * pone titulo a la lista.
     * @param titulo string que identifica el titulo de la lista
     */
    public Lista(String titulo){
        super(titulo);
        this.reproducibles = new ArrayList<Reproducible>();
    }

    /**
     * Constructor de la clase lista que inicializa el array de reproducibles y
     * pone titulo a la lista.
     * @param titulo string que identifica el titulo de la lista
     * @param reproducibles array de Reproducibles a meter en el album
     */
    public Lista(String titulo,ArrayList <Reproducible> reproducibles){
        this(titulo);
        this.reproducibles=reproducibles;

    }

    /**
     * Reproduce una lista en orden entrando recursivamente en las listas
     * contenidas en las listas
     * @param mp3 Cola donde se añade la cancion
     */
    @Override
    public void reproducir(Mp3Player mp3){
        for(Reproducible r: reproducibles){
            r.reproducibles(mp3);
        }
    }

    /**
     * Sirve para meter un reproducible pasado como argumento en la lista
     * @param r Cancion a aniadir en el album
     * @return false si el Reproducible ya esta en la lista true en caso contrario
     */
    public boolean aniadirReproducible(Reproducible r){
        if(reproducibles.contieneReproducible(r))return false;
        reproducibles.add(r);
        return true;
    }

    /**
     * Sirve para quitar un reproducible de la lista
     * @param r Reproducible a quitar de la lista
     * @return true si existe el reproducible a quitar false en caso contrario
     */
    public boolean quitarReproducible(Reproducible r){
        int index;

        index = reproducibles.indexOf(r);
        if(index == -1)return false;

        reproducibles.remove(index);
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

}
