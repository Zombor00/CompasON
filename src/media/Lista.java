import java.utils.*;
/**
 * Esta clase tiene toda la informacion relevante a las listas.
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class Lista extends Elemento implements Serializable{

    private ArrayList <Elemento> elementos;

    /**
     * Constructor de la clase lista que inicializa el array de elementos y
     * pone titulo a la lista.
     * @param titulo string que identifica el titulo de la lista
     */
    public Lista(String titulo){
        super(titulo);
        elementos = new ArrayList <Elemento>;
    }

    /**
     * Constructor de la clase lista que inicializa el array de elementos y
     * pone titulo a la lista.
     * @param titulo string que identifica el titulo de la lista
     * @param elementos array de elementos a meter en el album
     */
    public Lista(String titulo,ArrayList <Elemento> elementos){
        this(titulo);
        this.elementos

    }

    /*TO DO:*/
    public void reproducir();

    /**
     * Sirve para meter un elemento pasado como argumento en la lista
     * @param e Cancion a aniadir en el album
     * @return false si el elemento ya esta en la lista true en caso contrario
     */
    public boolean aniadirElemento(Elemento e){
        if(elementos.contieneElemento(e) == true)return false;
        elementos.add(e);
        return true;
    }

    /**
     * Sirve para quitar un elemento de la lista
     * @param e Elemento a quitar de la lista
     * @return true si existe el elemento a quitar false en caso contrario
     */
    public boolean quitarElemento(Elemento e){
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
    public boolean contieneElemento(Elemento e){
        for(Elemento eLista: elementos){
            if(eLista.contieneElemento(e)){
                return true;
            }
        }
        return false;
    }

}
