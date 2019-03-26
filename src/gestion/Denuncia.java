package gestion;

import java.io.*;
import media.Cancion;
import usuarios.UsuarioRegistrado;

/**
* Esta clase contiene la informacion para gestionar una denuncia
*
* @author Alvaro Zaera alvaro.zaeradela@estudiante.uam.es
* @version 1.0 (07-03-2019)
*
*/
public class Denuncia implements Serializable{
    private UsuarioRegistrado denunciante;
    private Cancion denunciada;
    private String comentario;

    /**
    * Constructor, con el denunciante, cancion denunciada y un comentario 
    * 
    * @param nombreUsuario
    * @param contrasenia clave del usuario
    * @param c comentario de la denuncia
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
    
    public String getComentario(){
        return this.comentario;
    }

	@Override
	public String toString() {
		return "Denuncia [denunciante=" + denunciante + ", denunciada=" + denunciada + ", comentario=" + comentario
				+ "]";
	}


}
