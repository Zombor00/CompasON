package test;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import aplicacion.Aplicacion;
import excepciones.ExcepcionCancionModificable;
import excepciones.ExcepcionCancionYaValidada;
import excepciones.ExcepcionDuracionLimiteSuperada;
import excepciones.ExcepcionLimiteReproducidasAlcanzado;
import excepciones.ExcepcionLoginBloqueado;
import excepciones.ExcepcionLoginErrorCredenciales;
import excepciones.ExcepcionNoAptoParaMenores;
import excepciones.ExcepcionNombreDeUsuarioNoDisponible;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;
import excepciones.ExcepcionReproducirProhibido;
import media.Buscable;
import media.EstadoValidacion;
import pads.musicPlayer.exceptions.Mp3PlayerException;

/**
 * Simulacion
 *
 * @author Antonio Garcia antonio.garcian@estudiante.uam.es
 * @version 1.0 (23-03-2019)
 */
class Simulacion {
	public static void main(String[] args) throws Mp3PlayerException, InterruptedException, ExcepcionLimiteReproducidasAlcanzado, ExcepcionNoAptoParaMenores, ExcepcionLoginErrorCredenciales, ExcepcionLoginBloqueado, ExcepcionDuracionLimiteSuperada, ExcepcionCancionModificable, ExcepcionCancionYaValidada, ExcepcionParametrosDeEntradaIncorrectos, ExcepcionNombreDeUsuarioNoDisponible, IOException, ExcepcionReproducirProhibido {
		List<Buscable> busqueda = new ArrayList<Buscable>();
		System.out.println("INICIO DE SIMULACION");
		//Thread.sleep(3000);
		Aplicacion aplicacion = Aplicacion.getInstance(10,10,10);
		
		System.out.println("Aniadimos dos usuarios: usuario1 y usuario2");
		//Thread.sleep(3000);
		aplicacion.aniadirUsuario("usuario1", "contrasenia1", "Usuario Uno", LocalDate.of(1999, 1, 1));
		aplicacion.aniadirUsuario("usuario2", "contrasenia2", "Usuario Dos", LocalDate.of(1999, 1, 2));
		
		System.out.println("El usuario1 inicia sesion");
		//Thread.sleep(3000);
		aplicacion.login("usuario1", "contrasenia1");
		
		System.out.println("El usuario1 sube Thats What I Like");
		//Thread.sleep(3000);
		aplicacion.subirCancion("Thats What I Like", "Thats What I Like.mp3");
		
		System.out.println("El usuario1 sube This Is Nightlife");
		//Thread.sleep(3000);
		aplicacion.subirCancion("This Is Nightlife", "canciones/This Is Nightlife.mp3");
		
		System.out.println("El usuario1 sube chicle3");
		//Thread.sleep(3000);
		aplicacion.subirCancion("deprueba chicle3", "canciones/deprueba chicle3.mp3");
		
		System.out.println("El usuario1 sube hive");
		//Thread.sleep(3000);
		aplicacion.subirCancion("deprueba hive", "canciones/deprueba hive.mp3");
		
		System.out.println("El usuario1 sube np");
		//Thread.sleep(3000);
		aplicacion.subirCancion("deprueba np", "canciones/deprueba np.mp3");
		
		System.out.println("El usuario1 cierra sesion");
		//Thread.sleep(3000);
		aplicacion.logout();
		
		System.out.println("El administrador inicia sesion");
		//Thread.sleep(3000);
		aplicacion.login("admin", "admin");
		
		System.out.println("El administrador accede a la lista de canciones nuevas");
		//Thread.sleep(3000);
		System.out.println(aplicacion.getAdministrador().getCancionesNuevas());
		
		System.out.println("El administrador valida 4 de las 5 canciones subidas por el usuario1 y una no la valida");
		//Thread.sleep(3000);
		aplicacion.getAdministrador().tramitarValidacion(aplicacion.getAdministrador().getCancionesNuevas().get(0), EstadoValidacion.APTOMENORES);
		aplicacion.getAdministrador().tramitarValidacion(aplicacion.getAdministrador().getCancionesNuevas().get(1), EstadoValidacion.APTOMENORES);
		aplicacion.getAdministrador().tramitarValidacion(aplicacion.getAdministrador().getCancionesNuevas().get(2), EstadoValidacion.NOVALIDADA);
		aplicacion.getAdministrador().tramitarValidacion(aplicacion.getAdministrador().getCancionesNuevas().get(3), EstadoValidacion.APTOMENORES);
		aplicacion.getAdministrador().tramitarValidacion(aplicacion.getAdministrador().getCancionesNuevas().get(4), EstadoValidacion.APTOMENORES);
		
		System.out.println("El administrador cierra sesion");
		//Thread.sleep(3000);
		aplicacion.logout();
		
		System.out.println("El usuario2 inicia sesion");
		//Thread.sleep(3000);
		aplicacion.login("usuario2", "contrasenia2");
		
		System.out.println("El usuario2 realiza una busqueda por titulo: 'Th'");
		//Thread.sleep(3000);
		busqueda = aplicacion.buscarPorTitulo("Th");
		System.out.println(busqueda);
		//Thread.sleep(6000);

		
		/*System.out.println("El usuario2 reproduce la primera cancion que aparece en la busqueda");
		Thread.sleep(3000);
		aplicacion.reproducirReproducible(busqueda.get(0));
		
		System.out.println("El usuario2 espera 10 segundos");
		Thread.sleep(10000);*/
		
		System.out.println("El usuario2 reproduce la segunda cancion que aparece en la busqueda");
		Thread.sleep(3000);
		aplicacion.reproducirReproducible(busqueda.get(1));
		
		System.out.println("El usuario2 espera 5 segundos");
		Thread.sleep(5000);
		
		System.out.println("El usuario2 realiza una busqueda por titulo: 'deprueba'");
		Thread.sleep(3000);
		busqueda = aplicacion.buscarPorTitulo("deprueba");
		System.out.println(busqueda);
		Thread.sleep(3000);
		
		System.out.println("El usuario2 reproduce la primera cancion que aparece en la busqueda y aniade a la cola la segunda");
		Thread.sleep(5000);
		aplicacion.reproducirReproducible(busqueda.get(0));
		aplicacion.aniadirALaCola(busqueda.get(1));

		Thread.sleep(10000);
		
		aplicacion.getCola().stop();
		
		System.out.println("El usuario2 cierra sesion");
		//Thread.sleep(3000);
		aplicacion.logout();
		
		System.out.println("El usuario1 inicia sesion");
		//Thread.sleep(3000);
		aplicacion.login("usuario1", "contrasenia1");
		
		System.out.println("El usuario1 consulta si hay alguna cancion que no ha pasado la validacion");
		//Thread.sleep(3000);
		System.out.println(aplicacion.getUsuarioLogeado().getCancionesNuevas());
		
		System.out.println("El usuario1 modifica la cancion no validada");
		//Thread.sleep(3000);
		aplicacion.getUsuarioLogeado().getCancionesNuevas().get(0).modificar("deprueba chicle3 modificada", null);
		
		System.out.println("El usuario1 cierra sesion");
		//Thread.sleep(3000);
		aplicacion.logout();
		
		System.out.println("El administrador inicia sesion");
		//Thread.sleep(3000);
		aplicacion.login("admin", "admin");
		
		System.out.println("El administrador accede a la lista de canciones nuevas");
		//Thread.sleep(3000);
		System.out.println(aplicacion.getAdministrador().getCancionesNuevas());
		
		System.out.println("El administrador marca como explicita la cancion modificada");
		//Thread.sleep(3000);
		aplicacion.getAdministrador().tramitarValidacion(aplicacion.getAdministrador().getCancionesNuevas().get(0), EstadoValidacion.EXPLICITO);
		
		System.out.println("El administrador cierra sesion");
		//Thread.sleep(3000);
		aplicacion.logout();
		
		System.out.println("Un usuario anonimo realiza una busqueda por titulo: 'deprueba'");
		Thread.sleep(3000);
		busqueda = aplicacion.buscarPorTitulo("deprueba");
		System.out.println(busqueda);
		Thread.sleep(3000);
		
		System.out.println("Trata de reproducir la tercera cancion que aparece en la busqueda");
		Thread.sleep(3000);
		aplicacion.reproducirReproducible(busqueda.get(2));
		
		
	}
}
