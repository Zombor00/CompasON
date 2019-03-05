import java.utils.*;
/**
 * Esta clase tiene toda la informacion relevante a las listas.
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class Lista extends Elemento{

    private ArrayList <Elemento> elementos;

    public void Lista(String titulo){
        super(titulo);
        elementos = new ArrayList <Elemento>;
    }

    public void Lista(String titulo,ArrayList <Elemento> elementos){
        this(titulo);
        this.elementos

    }

    /*TO DO:*/
    public void reproducir();

    public boolean aniadirElemento(Elemento e){
        if(elementos.contains(e)==true)return false;
        elementos.add(e);
        return true;
    }

    public boolean quitarElemento(Elemento e){
        int index;

        index = elementos.indexOf(e);
        if(index ==-1)return false;

        elementos.remove(index);
        return true
    }

    public boolean contieneElemento(Elemento e){
        return canciones.contains(e);
    }

}
