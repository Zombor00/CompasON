package media;

import java.io.*;
import java.time.*;

import excepciones.ExcepcionDuracionLimiteSuperada;
import excepciones.ExcepcionReproducirProhibido;
import excepciones.ExcepcionCancionYaValidada;
import excepciones.ExcepcionCancionModificable;
import excepciones.ExcepcionCancionYaNoModificable;
import excepciones.ExcepcionMp3NoValido;
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
     * @throws ExcepcionMp3NoValido 
     * @throws FileNotFoundException 
     */
    public Cancion(String titulo, String file, UsuarioRegistrado autor) throws ExcepcionMp3NoValido, FileNotFoundException {
        super(titulo, autor);
        this.id = Cancion.maxId +1;
        Cancion.maxId += 1;
        this.ficheroAudio = file;
        this.fechaSubida = LocalDate.now();
        if(file != null) {
            if(Mp3Player.isValidMp3File(file)) {
                    this.ficheroAudio = file;
            }else{
            	throw new ExcepcionMp3NoValido();
            }
        }
        this.estadoValidacion = EstadoValidacion.NOVALIDADA;
        try {
            this.setDuracion(Mp3Player.getDuration(file));
        }
        catch(FileNotFoundException e){
        	throw new FileNotFoundException();
        }
            
    }

    /**
     * Aniade una cancion a la cola
     * @param mp3 Cola donde se añade la cancion
     * @return numero de reproducciones realizadas
     * @throws ExcepcionReproducirProhibido 
     * @throws Mp3InvalidFileException 
     */
    public int reproducir(Mp3Player mp3, UsuarioRegistrado usuarioLogeado) throws Mp3InvalidFileException {
    	mp3.add(ficheroAudio);
    	
    	if (this.getAutor() == usuarioLogeado) {
    		return 0;
    	} else {
    		this.getAutor().aniadirReproduccion();
    		return 1;
    	}
    }


    /**
     * Valida la canción y pone si la cancion es explicita o no.
     * @param estado boolean que identifica si la cancion es explicita o no
     * @throws ExcepcionCancionYaValidada 
     * @throws ExcepcionCancionModificable 
     */
    public void validar(EstadoValidacion estado) throws ExcepcionCancionYaValidada, ExcepcionCancionModificable {
    	if (this.estadoValidacion == EstadoValidacion.APTOMENORES || this.estadoValidacion == EstadoValidacion.EXPLICITO) {
    		throw new ExcepcionCancionYaValidada();
    	}
    	if (this.modificableHasta != null && this.modificableHasta.isAfter(LocalDate.now())) {
    		throw new ExcepcionCancionModificable();
    	}
    	
    	
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
     * @throws ExcepcionDuracionLimiteSuperada 
     * @throws FileNotFoundException 
     * @throws ExcepcionCancionYaNoModificable
     * @throws ExcepcionCancionYaValidada
     * @throws ExcepcionMp3NoValido
     */
    public void modificar(String titulo, String file) throws ExcepcionCancionYaValidada,
    FileNotFoundException, ExcepcionDuracionLimiteSuperada,ExcepcionCancionYaNoModificable,
    ExcepcionMp3NoValido {
        if(this.estadoValidacion == EstadoValidacion.APTOMENORES ||
           this.estadoValidacion == EstadoValidacion.EXPLICITO) {
            throw new ExcepcionCancionYaValidada();
        }

        if(modificableHasta != null && modificableHasta.isBefore(LocalDate.now())) {
            throw new ExcepcionCancionYaNoModificable();
        }

        if(file != null) {
            if(Mp3Player.isValidMp3File(file)) {
                    this.ficheroAudio = file;
            }else{
            	throw new ExcepcionMp3NoValido();
            }
        }

        if(titulo != null) {
            setTitulo(titulo);
        }
        
        if (file != null && Mp3Player.getDuration(file) > 30*60) {
    		throw new ExcepcionDuracionLimiteSuperada();
    	}

        this.modificableHasta = null;
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

    public LocalDate getModificableHasta() {
        return modificableHasta;
    }

    public int getId() {
    	return this.id;
    }

    public String getFicheroAudio() {
        return this.ficheroAudio;
    }

    public LocalDate getFechaSubida() {
    	return this.fechaSubida;
    }

    @Override
    public void desbloquear(Cancion c) {
        if(this.equals(c)) {
        	if (this.getEstado() == Estado.BORRADO) {
        		return;
        	}
            this.setEstado(Estado.NOBLOQUEADO);
        }
    }

	@Override
	public String toString() {
		return this.getTitulo();
	}

    @Override
	public void aniadirPadre(Lista lista) {

	}

    @Override
	public void quitarPadre(Lista lista) {

	}

    @Override
	public boolean esAptoParaMenores() {
		if (estadoValidacion == EstadoValidacion.APTOMENORES) {
			return true;
		}
		return false;
	}
    
    @Override
    public boolean esValido() {
    	return this.estadoValidacion != EstadoValidacion.NOVALIDADA;
    }
    
    public boolean equals(Cancion c) {
    	return this.id == c.id;
    }
    
    @Override
    public boolean sePuedeMeterEn(Lista l) {
    	/*Si la lista no tiene padres buscamos recursivamente hacia abajo */
    	if(l.getPadres().isEmpty()) {
    		if(l.contieneReproducible(this)){
    			return false;
    		}
    	/*Si la lista tiene padres seguimos buscando hasta que no tenga ninguno
    	 * para poder hacer la recursión hacia abajo desde los padres mas altos*/
    	} else{
    		for(Lista padre: l.getPadres()) {
    			if(!this.sePuedeMeterEn(padre)) {
    				return false;
    			}
    		}
    	}
    	return true;
    }

}
