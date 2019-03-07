/**
* Esta clase contiene la informacion para gestionar una cola
*
* @author Alvaro Zaera alvaro.zaeradela@estudiante.uam.es
* @version 1.0 (07-03-2019)
*
*/
public class Cola{
    private ArrayList<Elemento> elementos = new ArrayList<>();

    /**
    * Este metodo se usa para aniadir un elemento
    * a la cola
    *
    * @param e
    * @return boolean sobre si se hace correctamente
    */
    public boolean aniadirElemento(Elemento e){
        return this.elementos.add(e);
    }


}
