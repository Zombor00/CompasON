package media;

import java.io.*;
import java.time.*;
import pads.musicPlayer.Mp3Player;
import pads.musicPlayer.exceptions.Mp3InvalidFileException;
import usuarios.*;

/**
 * Esta clase tiene toda la informacion relevante a las canciones.
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */


public class Cancion extends Buscable implements Serializable{

    private static int maxId = 0;
    private int id;
    private UsuarioRegistrado autor;
    private String ficheroAudio;
    private LocalDate fechaSubida;
    private LocalDateTime modificableHasta;
    private EstadoValidacion estadoValidacion;
    private double duracion;

    /**
     * Constructor de la clase cancion que llama al constructor de elemento
     * con titulo, inicializa validada a false y pone el atributo fechaDeSubida
     * a la fecha actual.
     * @param titulo string que identifica el titulo de la cancion
     * @param file fichero con el audio de la cancion
     * @param autor autor de la cancion
     */
    public Cancion(String titulo, String file, UsuarioRegistrado autor){
        super(titulo);
        this.id = Cancion.maxId +1;
        Cancion.maxId += 1;
        this.autor = autor;
        this.ficheroAudio = file;
        this.fechaSubida = LocalDate.now();
        try {
            this.duracion = Mp3Player.getDuration(file);
        }
        catch(FileNotFoundException e) {
            /* Gestion de excepxion */
        }
    }

    /**
     * Aniade una cancion a la cola
     * @param mp3 Cola donde se añade la cancion
     */
    public void reproducir(Mp3Player mp3){
        try {
            mp3.add(ficheroAudio);
        }
        catch(Mp3InvalidFileException e) {
            /* Gestion de excepxion */
        }
    }


    /**
     * Valida la canción y pone si la cancion es explicita o no.
     * @param explicito boolean que identifica si la cancion es explicita o no
     */
    public void validar(EstadoValidacion estado){
        if(estado == EstadoValidacion.NOVALIDADA){
            this.estadoValidacion = EstadoValidacion.NOVALIDADA;
            modificableHasta = LocalDateTime.now().plusDays(3);
        }
        else if(estado == EstadoValidacion.EXPLICITO){
            this.estadoValidacion = EstadoValidacion.EXPLICITO;
        }else{
            this.estadoValidacion = EstadoValidacion.APTOMENORES;
        }
    }

    /**
     *  Modifica el titulo el fichero de audio a los que han sido pasados
     *  por parametros si son distintos de null
     * @param titulo titulo de la cancion
     * @param file fichero de audio de la cancion
     * @return true si se puede modificar, false en caso contrario
     */
    public boolean modificar(String titulo, String file){
        /*TO DO: file tiene que ser apto*/
        if(this.estadoValidacion == EstadoValidacion.APTOMENORES ||
           this.estadoValidacion == EstadoValidacion.EXPLICITO ||
           modificableHasta.isBefore(LocalDateTime.now())){
            return false;
        }
        if(file != null){
            this.ficheroAudio = file;
        }
        if(titulo != null){
            setTitulo(titulo);
        }
        return true;
    }

    /**
     * Te devuelve true si el Reproducible pasado es el mismo.
     * @param c Cancion a comparar
     * @return boolean: true si la cancion es la misma false
     * en caso contrario
     */
    @Override
    public boolean contieneReproducible(Reproducible e){
        if(e.equals(this)) return true;
        return false;
    }

    public EstadoValidacion getEstadoValidacion(){
        return this.estadoValidacion;
    }

    public UsuarioRegistrado getAutor(){
        return this.autor;
    }

    public LocalDateTime getModificableHasta(){
        return modificableHasta;
    }

    @Override
    public void desbloquear(Cancion c){
        if(this.equals(c)){
            this.setEstado(Estado.NOBLOQUEADO);
        }
    }
}
