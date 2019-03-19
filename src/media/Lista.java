import java.utils.*;
/**
 * Esta clase tiene toda la informacion relevante a las listas.
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class Lista extends Reproducible implements Serializable{

    private ArrayList <Reproducible> elementos;

    /**
     * Constructor de la clase lista que inicializa el array de elementos y
     * pone titulo a la lista.
     * @param titulo string que identifica el titulo de la lista
     */
    public Lista(String titulo){
        super(titulo);
        elementos = new ArrayList<Reproducible>();
    }

    /**
     * Constructor de la clase lista que inicializa el array de elementos y
     * pone titulo a la lista.
     * @param titulo string que identifica el titulo de la lista
     * @param elementos array de elementos a meter en el album
     */
    public Lista(String titulo,ArrayList <Reproducible> elementos){
        this(titulo);
        this.elementos=elementos;

    }

    /**
     * Reproduce una lista en orden entrando recursivamente en las listas
     * contenidas en las listas
     * @param mp3 Cola donde se añade la cancion
     */
    public void reproducir(Mp3Player mp3){
        for(Reproducible r: elementos){
            r.reproducibles(mp3);
        }
    }

    /**
     * Sirve para meter un elemento pasado como argumento en la lista
     * @param e Cancion a aniadir en el album
     * @return false si el elemento ya esta en la lista true en caso contrario
     */
    public boolean aniadirElemento(Reproducible e){
        if(elementos.contieneElemento(e) == true)return false;
        elementos.add(e);
        return true;
    }

    /**
     * Sirve para quitar un elemento de la lista
     * @param e Elemento a quitar de la lista
     * @return true si existe el elemento a quitar false en caso contrario
     */
    public boolean quitarElemento(Reproducible e){
        int index;

        index = elementos.indexOf(e);
        if(index == -1)return false;

        elementos.remove(index);
        return true;
    }

    /**
     * Te devuelve true si el elemento existe en el album y false si no
     * existe el elemento
     * @param e elemento a buscar en la lista
     * @return boolean: true si existe el elemento en el album false
     * en caso contrario
     */

    @Override
    public boolean contieneElemento(Reproducible e){
        for(Reproducible eLista: elementos){
            if(eLista.contieneElemento(e)){
                return true;
            }
        }
        return false;
    }
    @Override
    public setBloqueado(boolean b){

    }

}
