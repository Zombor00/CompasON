/**
* Esta clase contiene la informacion para gestionar una denuncia
*
* @author Alvaro Zaera alvaro.zaeradela@estudiante.uam.es
* @version 1.0 (07-03-2019)
*
*/
public class Denuncia{
    private UsuarioRegistrado denunciante;
    private Cancion denunciada;
    private String comentario;

    /**
    * Constructor, con el nombre de usuario y contrasenia
    *
    * @param nombreUsuario
    * @param contrasenia del usuario
    */
    public Denuncia(UsuarioRegistrado denunciante, Cancion denunciada, String c){
        this.denunciante = denunciante;
        this.denunciada = denunciada;
        this.comentario = c;
    }

    public Cancion getDenunciada(){
        return this.denunciada;
    }

    public UsuarioRegistrado getDenunciante(){
        return this.denunciante;
    }


}
