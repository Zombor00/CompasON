package usuarios;

import java.io.*;
import java.util.*;
import java.time.*;
import media.*;
import gestion.*;
import excepciones.*;

/**
* Esta clase contiene la informacion comun de los usuarios
* registrados en la aplicacion (sin incluir al administrador)
*
* @author Alvaro Zaera alvaro.zaeradela@estudiante.uam.es
* @version 1.0 (05-03-2019)
*
*/
public class UsuarioRegistrado extends UsuarioConCuenta implements Serializable{

	private String nombre;
    private LocalDate fechaNacimiento;
    private LocalDate premiumHasta;
    private LocalDate bloqueadoHasta;
    /* Atributo que almacena la fecha en la que se ha reproducido una cancion del usuario*/
    private ArrayList<LocalDate> reproducciones = new ArrayList<>();
    private int reproducidas; /* Numero de veces que ha reproducido el usuario*/
    private ArrayList<Buscable> buscables = new ArrayList<>();
    private ArrayList<Lista> listas = new ArrayList<>();
    private ArrayList<Notificacion> notificaciones = new ArrayList<>();
    private ArrayList<UsuarioRegistrado> seguidos = new ArrayList<>();
    private ArrayList<UsuarioRegistrado> seguidores = new ArrayList<>();
    private LocalDate ultimoLogin;

    /**
    * Constructor, con el nombre de usuario, contrasenia, nombre y
    * fecha de nacimiento
    *
    * @param nombreUsuario
    * @param contrasenia del usuario
    * @param nombre real del usuario
    * @param fechaNacimiento
    */
    public UsuarioRegistrado(String nombreUsuario, String contrasenia,
                             String nombre, LocalDate fechaNacimiento){
        super(nombreUsuario,contrasenia);
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public String getNombre(){
        return this.nombre;
    }

    public LocalDate geFechaNacimiento(){
        return this.fechaNacimiento;
    }
    
    public ArrayList<UsuarioRegistrado> getSeguidores(){
        return this.seguidores;
    }

    public ArrayList<UsuarioRegistrado> getSeguidos() {
		return seguidos;
	}

	public ArrayList<Buscable> getBuscables(){
        return this.buscables;
    }

    public ArrayList<Lista> getListas(){
        return this.listas;
    }

    public int getReproducidas(){
        return this.reproducidas;
    }

    public ArrayList<LocalDate> getReproducciones(){
        return this.reproducciones;
    }
    
    public LocalDate getUltimoLogin() {
    	return this.ultimoLogin;
    }
    
    public LocalDate getPremiumHasta() {
		return premiumHasta;
	}
    
    public LocalDate getBloqueadoHasta() {
		return this.bloqueadoHasta;
	}

	public ArrayList<Notificacion> getNotificaciones() {
		return this.notificaciones;
	}

	public void setReproducidas(int reproducidas) {
		this.reproducidas = reproducidas;		
	}
	
	public void setReproducciones(ArrayList<LocalDate> reproducciones) {
		this.reproducciones = reproducciones;		
	}

	public void setPremiumHasta(LocalDate premiumHasta) {
		this.premiumHasta = premiumHasta;
	}


    public void setUltimoLogin(LocalDate l){
        this.ultimoLogin = l;
    }

    public void setBloqueadoHasta(LocalDate b){
        this.bloqueadoHasta = b;
    }



    /**
    * Este metodo se usa para aniadir una cancion o album al usuario
    *
    * @param e elemento a aniadir
    * @return boolean sobre si se hace correctamente
    */
    public boolean aniadirBuscable(Buscable e){
        return this.buscables.add(e);
    }

    /**
    * Este metodo se usa para crear una lista 
    *
    * @param titulo nombre de la lista
    * @param elementos iniciales de la lista
    */
    public void crearLista(String titulo, ArrayList <Reproducible> elementos) throws ExcepcionUsuarioNoPremium{
        if(premiumHasta!=null && premiumHasta.isAfter(LocalDate.now())){
            Lista lista = new Lista(titulo,elementos);
            this.listas.add(lista);
        }
        else {
        	throw new ExcepcionUsuarioNoPremium();	
        }
    }
    
    /**
     * Este metodo se usa para crear una lista 
     *
     * @param titulo nombre de la lista
     * @param elementos iniciales de la lista
     */
     public void crearLista(String titulo) throws ExcepcionUsuarioNoPremium{
         if(premiumHasta!=null && premiumHasta.isAfter(LocalDate.now())){
             Lista lista = new Lista(titulo);
             this.listas.add(lista);
         }
         else {
         	throw new ExcepcionUsuarioNoPremium();	
         }
     }



    /**
    * Este metodo almacena el usuario que se recibe en lista de seguidos y guarda
    * este objeto en la lista de seguidores del que se recibe como argumento
    *
    * @param u usuario a seguir
    */
    public void seguirUsuario(UsuarioRegistrado u) throws ExcepcionUsuarioYaSeguido{
        if (this.seguidos.contains(u) && u.getSeguidores().contains(this)){
            throw new ExcepcionUsuarioYaSeguido();
        }
        this.seguidos.add(u);
        u.getSeguidores().add(this);
    }

    /**
    * Este metodo quita al usuario que se recibe de la lista de seguidos y quita
    * este objeto de la lista de seguidores del que se recibe como argumento
    *
    * @param u usuario a dejar de seguir
    */
    public void dejarSeguirUsuario(UsuarioRegistrado u) throws ExcepcionUsuarioNoSeguido{
        if (this.seguidos.remove(u) == false || u.getSeguidores().remove(this) == false) {
        	throw new ExcepcionUsuarioNoSeguido();
        }
    }


    /**
    * Este metodo se usa para aniadir una notificacion al usuario
    *
    * @param n notificacion a aniadir
    * @return boolean sobre si se hace correctamente
    */
    public boolean aniadirNotificacion(Notificacion n){
        return this.notificaciones.add(n);
    }
    
    /**
    * Este metodo se usa para borrar una notificacion del usuario
    *
    * @param n notificacion a borrar
    * @return boolean sobre si se hace correctamente
    */
    public boolean borrarNotificacion(Notificacion n){
        return this.notificaciones.remove(n);
    }    
   

    /**
    * Este metodo se usa para aumentar las reproducciones del usuario
    *
    */
    public void aniadirReproducida(){
        this.reproducidas++;
    }

    /**
    * Este metodo se usa para aumentar las reproducciones de otros usuarios
    * a canciones del usuario
    *
    */
    public void aniadirReproduccion(){
        this.reproducciones.add(LocalDate.now());
    }
    
    /**
    * Devuelve el numero de reproducciones que han hecho otros usuarios
    * de canciones suyas el ultimo mes
    *
    * @return numero de reproducciones
    */
    public int reproduccionesUltimoMes() {
    	LocalDate hoy = LocalDate.now();
    	int cont = 0, mesBuscado, anioBuscado;      	
    	int mes = hoy.getMonthValue();
    	int anio = hoy.getYear();
    	ArrayList<LocalDate> posteriores = new ArrayList<>();
    	
    	if (mes == 1) {
    		mesBuscado = 12;
    		anioBuscado = anio - 1;
    	}
    	else {
    		mesBuscado = mes - 1;
    		anioBuscado = anio;
    	}
    	
    	LocalDate fechaAntigua = LocalDate.of(anioBuscado, mesBuscado, 1);
    	
    	for (int i = this.reproducciones.size() - 1; this.reproducciones.get(i).isBefore(fechaAntigua)==false ;i--) {
    		if (this.reproducciones.get(i).getYear() == anioBuscado &&
    				this.reproducciones.get(i).getMonthValue() == mesBuscado) {
    			cont++;
    		}
    		else {
    			posteriores.add(this.reproducciones.get(i));
    		}
    	}
    	
    	this.reproducciones.retainAll(posteriores);
    	return cont;
    	 
    }

    /**
     * Devuelve si el usuario es menor de edad
     *
     * @return true si el usuario es menor
     */

	public boolean esMenor() {
		return this.fechaNacimiento.isAfter(LocalDate.now().minusYears(18));
	}
	
	public String toString() {
    	return nombre+" ("+super.toString()+")";
    }




}
