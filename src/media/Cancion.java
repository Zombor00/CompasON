/**
 * Esta clase tiene toda la informacion relevante a las canciones.
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */

public class Cancion extends Elemento implements Serializable{

    private boolean explicit;
    private UsuarioRegistrado autor;
    private File ficheroAudio;
    private LocalDate fechaSubida;
    private LocalDateTime modificableHasta;
    private boolean validada;

    /**
     * Constructor de la clase cancion que llama al constructor de elemento
     * con titulo, inicializa validada a false y pone el atributo fechaDeSubida
     * a la fecha actual.
     * @param titulo string que identifica el titulo de la cancion
     * @param file fichero con el audio de la cancion
     * @param autor autor de la cancion
     */
    public Cancion(String titulo, File file, UsuarioRegistrado autor){
        Super(titulo);
        this.autor = autor;
        this.ficheroAudio = file;
        this.fechaSubida = LocalDate.now();
        /*TO DO: Meter duración*/
        this.validada = false;
    }

    /*TO DO:*/
    public void reproducir();


    /**
     * Valida la canción y pone si la cancion es explicita o no.
     * @param explicito boolean que identifica si la cancion es explicita o no
     */
    public void validar(boolean explicito){
        this.validada = true;
        this.explicit = explicito;
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

}
