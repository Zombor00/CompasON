import java.utils.*;

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
        super(titulo);
        canciones = new ArrayList<Cancion>();
        this.anio = anio;
    }

    /**
    * Constructor de la clase album que pone titulo al album y al que se le pasa
     las canciones que tendrá el album inicialmente.
     * @param titulo string que identifica el titulo del album
     * @param canciones array de canciones a meter en el album
     */
    public Album(String titulo, Date anio, ArrayList <Cancion> canciones){
        super(titulo,anio);
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
        if(canciones.contains(c) == true)return false;
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
        return true
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
        return canciones.contains(c);
    }
}
