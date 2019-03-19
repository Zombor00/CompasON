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
    private Estado validada;
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
        this.id = this.maxId +1;
        this.maxId += 1;
        this.autor = autor;
        this.ficheroAudio = file;
        this.fechaSubida = LocalDate.now();
        this.duracion = Mp3Player.getDuracion(file);
        this.validada = Estado.SINVALIDAR;
    }

    /**
     * Aniade una cancion a la cola
     * @param mp3 Cola donde se añade la cancion
     */
    public void reproducir(Mp3Player mp3){
        mp3.add(ficheroAudio);
    }


    /**
     * Valida la canción y pone si la cancion es explicita o no.
     * @param explicito boolean que identifica si la cancion es explicita o no
     */
    public void validar(Estado val){
        if(val == Estado.NOVALIDAR){
            this.validada = Estado.NOVALIDAR;
            modificableHasta = LocalDateTime.now().plusDays(3);
        }
        else if(val == EXPLICITO){
            this.Estado = Estado.EXPLICITO;
        }else{
            this.Estado = Estado.APTOMENORES;
        }
    }

    /**
     *  Modifica el titulo el fichero de audio a los que han sido pasados
     *  por parametros si son distintos de null
     * @param titulo titulo de la cancion
     * @param file fichero de audio de la cancion
     * @return true si se puede modificar, false en caso contrario
     */
    public boolean modificar(String titulo, File file){
        /*TO DO: file tiene que ser apto*/
        if(this.validada == true || modificableHasta.isBefore(LocalDateTime.now())){
            return false
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
     * Te devuelve true si el elemento pasado es el mismo.
     * @param c Cancion a comparar
     * @return boolean: true si la cancion es la misma false
     * en caso contrario
     */
    @Override
    public boolean contieneElemento(Elemento e){
        if(e.equals(this) == true)return true
        return false;
    }

    public boolean getValidada(){
        return this.validada;
    }

    public UsuarioRegistrado getAutor(){
        return this.autor;
    }

}
