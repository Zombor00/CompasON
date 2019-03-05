import java.utils.*;

/**
 * Esta clase tiene toda la informacion relevante a los Albumes.
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class Album extends Elemento{

    private ArrayList <Cancion> canciones = new ArrayList <Cancion>;

    public void Album(String titulo){
        super(titulo);
        canciones = new ArrayList <Cancion>;
    }

    public void Album(String titulo, ArrayList <Cancion> canciones){
        super(titulo);
        this.canciones = canciones;
    }

    /*TO DO:*/
    public void reproducir();

    public boolean aniadirCancion(Cancion c){
        if(canciones.contains(c)==true)return false;
        canciones.add(c);
        return true;
    }

    public boolean quitarCancion(Cancion c){
        int index;

        index = canciones.indexOf(c);
        if(index ==-1)return false;

        canciones.remove(index);
        return true
    }

    public boolean contieneCancion(Cancion c){
        return canciones.contains(c);
    }
}
