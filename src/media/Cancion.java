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
    private LocalDate modificableHasta;
    private EstadoValidacion estadoValidacion;

    /**
     * Constructor de la clase cancion que llama al constructor de elemento
     * con titulo, inicializa validada a false y pone el atributo fechaDeSubida
     * a la fecha actual.
     * @param titulo string que identifica el titulo de la cancion
     * @param file fichero con el audio de la cancion
     * @param autor autor de la cancion
     */
    public Cancion(String titulo, String file, UsuarioRegistrado autor) {
        super(titulo);
        this.id = Cancion.maxId +1;
        Cancion.maxId += 1;
        this.autor = autor;
        this.ficheroAudio = file;
        this.fechaSubida = LocalDate.now();
        try {
            this.setDuracion(Mp3Player.getDuration(file));
            }
        catch(FileNotFoundException e) {
            /* Gestion de excepxion */
        }
    }

    /**
     * Aniade una cancion a la cola
     * @param mp3 Cola donde se añade la cancion
     */
    public void reproducir(Mp3Player mp3) {
    	this.autor.aniadirReproduccion();
        try {
            mp3.add(ficheroAudio);
        }
        catch(Mp3InvalidFileException e) {
        	System.out.println(e);
        }
    }


    /**
     * Valida la canción y pone si la cancion es explicita o no.
     * @param estado boolean que identifica si la cancion es explicita o no
     */
    public void validar(EstadoValidacion estado) {
        if(estado == EstadoValidacion.NOVALIDADA) {
            this.estadoValidacion = EstadoValidacion.NOVALIDADA;
            modificableHasta = LocalDate.now().plusDays(3);
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
    public boolean modificar(String titulo, String file) {
        /*TO DO: file tiene que ser apto*/
        if(this.estadoValidacion == EstadoValidacion.APTOMENORES ||
           this.estadoValidacion == EstadoValidacion.EXPLICITO) {
            return false;
        }

        if(modificableHasta != null && modificableHasta.isBefore(LocalDate.now())) {
            return false;
        }

        if(file != null) {
            if(Mp3Player.isValidMp3File(file)) {
                    this.ficheroAudio = file;
            }else{
                return false;
            }
        }

        if(titulo != null) {
            setTitulo(titulo);
        }

        this.modificableHasta = null;
        return true;
    }

    /**
     * Te devuelve true si el Reproducible pasado es el mismo.
     * @param e Cancion a comparar
     * @return boolean: true si la cancion es la misma false
     * en caso contrario
     */
    @Override
    public boolean contieneReproducible(Reproducible e) {
        if(e.equals(this)) return true;
        return false;
    }

    public EstadoValidacion getEstadoValidacion() {
        return this.estadoValidacion;
    }

    public UsuarioRegistrado getAutor() {
        return this.autor;
    }

    public LocalDate getModificableHasta() {
        return modificableHasta;
    }

    public int getId() {
    	return this.id;
    }

    public String getFicheroAudio() {
        return this.ficheroAudio
    }

    public LocalDate getFechaSubida() {
    	return this.fechaSubida;
    }

    @Override
    public void desbloquear(Cancion c) {
        if(this.equals(c)) {
            this.setEstado(Estado.NOBLOQUEADO);
        }
    }

	@Override
	public String toString() {
		return "Cancion [titulo=" + this.getTitulo() + ", autor=" + autor + ", duracion=" + this.getDuracion() + "]";
	}

}
