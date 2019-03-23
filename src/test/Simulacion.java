package test;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import aplicacion.Aplicacion;
import excepciones.ExcepcionLimiteReproducidasAlcanzado;
import excepciones.ExcepcionLoginBloqueado;
import excepciones.ExcepcionLoginErrorCredenciales;
import excepciones.ExcepcionNoAptoParaMenores;
import media.Buscable;
import pads.musicPlayer.Mp3Player;
import pads.musicPlayer.exceptions.Mp3PlayerException;

/**
 * Simulacion
 *
 * @author Antonio Garcia antonio.garcian@estudiante.uam.es
 * @version 1.0 (23-03-2019)
 */
class Simulacion {
	public static void main(String[] args) throws Mp3PlayerException, InterruptedException, FileNotFoundException, ExcepcionLimiteReproducidasAlcanzado, ExcepcionNoAptoParaMenores, ExcepcionLoginErrorCredenciales, ExcepcionLoginBloqueado {
		List<Buscable> busqueda = new ArrayList<Buscable>();
		System.out.println("INICIO DE SIMULACION");
		Thread.sleep(3000);
		Aplicacion aplicacion = Aplicacion.getInstance();
		
		System.out.println("Aniadimos dos usuarios: usuario1 y usuario2");
		Thread.sleep(3000);
		aplicacion.aniadirUsuario("usuario1", "contrasenia1", "Usuario Uno", LocalDate.of(1999, 1, 1));
		aplicacion.aniadirUsuario("usuario2", "contrasenia2", "Usuario Dos", LocalDate.of(1999, 1, 2));
		
		System.out.println("El usuario1 inicia sesion");
		Thread.sleep(3000);
		try {
			aplicacion.login("usuario1", "contrasenia1");
		} catch (ExcepcionLoginErrorCredenciales e) {
			e.printStackTrace();
		} catch (ExcepcionLoginBloqueado e) {
			e.printStackTrace();
		}
		
		System.out.println("El usuario1 sube Thats What I Like");
		Thread.sleep(3000);
		aplicacion.aniadirCancion("Thats What I Like", "canciones/Thats What I Like.mp3");
		
		System.out.println("El usuario1 sube This Is Nightlife");
		Thread.sleep(3000);
		aplicacion.aniadirCancion("This Is Nightlife", "canciones/This Is Nightlife.mp3");
		
		System.out.println("El usuario1 cierra sesion");
		Thread.sleep(3000);
		aplicacion.logout();
		
		System.out.println("El usuario2 inicia sesion");
		Thread.sleep(3000);
		aplicacion.login("usuario2", "contrasenia2");
		
		System.out.println("El usuario2 realiza una busqueda por titulo: 'Th'");
		Thread.sleep(3000);
		busqueda = aplicacion.buscarPorTitulo("Th");
		System.out.println(busqueda);
		Thread.sleep(6000);

		
		System.out.println("El usuario2 reproduce la primera cancion que aparece en la busqueda");
		Thread.sleep(3000);
		aplicacion.reproducirReproducible(busqueda.get(0));
		
		System.out.println("El usuario2 espera 10 segundos");
		Thread.sleep(10000);
		
		System.out.println("El usuario2 reproduce la segunda cancion que aparece en la busqueda");
		Thread.sleep(3000);
		aplicacion.reproducirReproducible(busqueda.get(1));

		Mp3Player player = aplicacion.getCola();
		Thread.sleep(60000);
		
		player.stop();
		
	}
}
