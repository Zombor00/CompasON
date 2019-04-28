package test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import aplicacion.Aplicacion;
import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import excepciones.ExcepcionCancionModificable;
import excepciones.ExcepcionCancionNoValidada;
import excepciones.ExcepcionCancionYaNoModificable;
import excepciones.ExcepcionCancionYaValidada;
import excepciones.ExcepcionDuracionLimiteSuperada;
import excepciones.ExcepcionErrorCreandoAlbum;
import excepciones.ExcepcionInsercionInvalida;
import excepciones.ExcepcionLimiteReproducidasAlcanzado;
import excepciones.ExcepcionLoginBloqueado;
import excepciones.ExcepcionLoginErrorCredenciales;
import excepciones.ExcepcionMp3NoValido;
import excepciones.ExcepcionNoAptoParaMenores;
import excepciones.ExcepcionNombreDeUsuarioNoDisponible;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;
import excepciones.ExcepcionReproducibleNoValido;
import excepciones.ExcepcionReproducirProhibido;
import excepciones.ExcepcionUsuarioNoPremium;
import excepciones.ExcepcionUsuarioNoSeguido;
import excepciones.ExcepcionUsuarioSinCuenta;
import excepciones.ExcepcionUsuarioYaSeguido;
//import media.Album;
import media.Buscable;
import media.Cancion;
import media.EstadoValidacion;
//import media.Reproducible;
import pads.musicPlayer.exceptions.Mp3PlayerException;

public class GeneraDatos {
	public static void main(String[] args) throws Mp3PlayerException, InterruptedException, ExcepcionLimiteReproducidasAlcanzado, ExcepcionNoAptoParaMenores, ExcepcionLoginErrorCredenciales, ExcepcionLoginBloqueado, ExcepcionDuracionLimiteSuperada, ExcepcionCancionModificable, ExcepcionCancionYaValidada, ExcepcionParametrosDeEntradaIncorrectos, ExcepcionNombreDeUsuarioNoDisponible, IOException, ExcepcionReproducirProhibido, ExcepcionUsuarioYaSeguido, ExcepcionUsuarioNoSeguido, ExcepcionErrorCreandoAlbum, InvalidCardNumberException, FailedInternetConnectionException, OrderRejectedException, ExcepcionUsuarioNoPremium, NoSuchAlgorithmException, ExcepcionCancionYaNoModificable, ExcepcionMp3NoValido, ExcepcionUsuarioSinCuenta, ClassNotFoundException, ExcepcionInsercionInvalida, ExcepcionCancionNoValidada, ExcepcionReproducibleNoValido {
		List<Buscable> busqueda = new ArrayList<Buscable>();
		int sleep = 0;
		System.out.println("GENERANDO DATOS");
		Thread.sleep(sleep);
		Aplicacion aplicacion = Aplicacion.getInstance(10,10,10);
		
		System.out.println("Aniadimos dos usuarios: usuario1 y usuario2");
		Thread.sleep(sleep);
		aplicacion.aniadirUsuario("usuario1", "contrasenia1", "Usuario Uno", LocalDate.of(1999, 1, 1));
		aplicacion.aniadirUsuario("usuario2", "contrasenia2", "Usuario Dos", LocalDate.of(1999, 1, 2));
		
		
		System.out.println("El usuario1 inicia sesion");
		Thread.sleep(sleep);
		aplicacion.login("usuario1", "contrasenia1");
		
		System.out.println("El usuario1 sube Thats What I Like");
		Thread.sleep(sleep);
		aplicacion.subirCancion("Thats What I Like", "Thats What I Like.mp3");
		
		System.out.println("El usuario1 sube This Is Nightlife");
		Thread.sleep(sleep);
		aplicacion.subirCancion("This Is Nightlife", "canciones/This Is Nightlife.mp3");
		
		System.out.println("El usuario1 sube chicle3");
		Thread.sleep(sleep);
		aplicacion.subirCancion("deprueba chicle3", "canciones/deprueba chicle3.mp3");
		
		System.out.println("El usuario1 sube hive");
		Thread.sleep(sleep);
		aplicacion.subirCancion("deprueba hive", "canciones/deprueba hive.mp3");
		
		System.out.println("El usuario1 sube np");
		Thread.sleep(sleep);
		aplicacion.subirCancion("deprueba np", "canciones/deprueba np.mp3");
		
		System.out.println("El usuario1 cierra sesion");
		Thread.sleep(sleep);
		aplicacion.logout();
		
		System.out.println("El administrador inicia sesion");
		Thread.sleep(sleep);
		aplicacion.login("admin", "admin");
		
		System.out.println("El administrador accede a la lista de canciones nuevas");
		Thread.sleep(sleep);
		System.out.println(aplicacion.getAdministrador().getCancionesNuevas());
		
		System.out.println("El administrador valida 4 de las 5 canciones subidas por el usuario1 y una no la valida");
		Thread.sleep(sleep);
		aplicacion.getAdministrador().tramitarValidacion(aplicacion.getAdministrador().getCancionesNuevas().get(0), EstadoValidacion.APTOMENORES);
		aplicacion.getAdministrador().tramitarValidacion(aplicacion.getAdministrador().getCancionesNuevas().get(1), EstadoValidacion.APTOMENORES);
		aplicacion.getAdministrador().tramitarValidacion(aplicacion.getAdministrador().getCancionesNuevas().get(2), EstadoValidacion.NOVALIDADA);
		aplicacion.getAdministrador().tramitarValidacion(aplicacion.getAdministrador().getCancionesNuevas().get(3), EstadoValidacion.APTOMENORES);
		aplicacion.getAdministrador().tramitarValidacion(aplicacion.getAdministrador().getCancionesNuevas().get(4), EstadoValidacion.APTOMENORES);
		
		System.out.println("El administrador cierra sesion");
		Thread.sleep(sleep);
		aplicacion.logout();
		
		System.out.println("El usuario2 inicia sesion");
		Thread.sleep(sleep);
		aplicacion.login("usuario2", "contrasenia2");
		
		System.out.println("El usuario2 sigue al usuario1");
		Thread.sleep(sleep);
		busqueda = aplicacion.buscarPorTitulo("Th");
		aplicacion.getUsuarioLogeado().seguirUsuario(busqueda.get(1).getAutor());
		
		System.out.println("El usuario2 cierra sesion");
		Thread.sleep(sleep);
		aplicacion.logout();
		
		System.out.println("El usuario1 inicia sesion");
		Thread.sleep(sleep);
		aplicacion.login("usuario1", "contrasenia1");
		
		System.out.println("El usuario1 consulta si hay alguna cancion que no ha pasado la validacion");
		Thread.sleep(sleep);
		System.out.println(aplicacion.getUsuarioLogeado().getCancionesNuevas());
		
		System.out.println("El usuario1 modifica la cancion no validada");
		Thread.sleep(sleep);
		aplicacion.getUsuarioLogeado().getCancionesNuevas().get(0).modificar("deprueba chicle3 modificada", null);
		
		System.out.println("El usuario1 aprovecha, consulta sus canciones validadas y crea un album con las dos ultimas");
		Thread.sleep(sleep);
		System.out.println(aplicacion.getUsuarioLogeado().getBuscables());
		ArrayList<Cancion> cancionesAlbum = new ArrayList<>();
		cancionesAlbum.add((Cancion) aplicacion.getUsuarioLogeado().getBuscables().get(2));
		cancionesAlbum.add((Cancion) aplicacion.getUsuarioLogeado().getBuscables().get(3));
		aplicacion.aniadirAlbum("depruebazz", cancionesAlbum);
		
		System.out.println("El usuario1 cierra sesion");
		Thread.sleep(sleep);
		aplicacion.logout();
		
		System.out.println("El administrador inicia sesion");
		Thread.sleep(sleep);
		aplicacion.login("admin", "admin");
		
		System.out.println("El administrador accede a la lista de canciones nuevas");
		Thread.sleep(sleep);
		System.out.println(aplicacion.getAdministrador().getCancionesNuevas());
		
		System.out.println("El administrador marca como explicita la cancion modificada");
		Thread.sleep(sleep);
		aplicacion.getAdministrador().tramitarValidacion(aplicacion.getAdministrador().getCancionesNuevas().get(0), EstadoValidacion.EXPLICITO);
		
		System.out.println("El administrador cierra sesion");
		Thread.sleep(sleep);
		aplicacion.logout();
		
		/*System.out.println("El usuario2 inicia sesion");
		Thread.sleep(sleep);
		aplicacion.login("usuario2", "contrasenia2");
		
		System.out.println("El usuario2 consulta sus notificaciones");
		Thread.sleep(sleep);
		System.out.println(aplicacion.getUsuarioLogeado().getNotificaciones());
		
		System.out.println("El usuario2 busca la cancion nueva y la reproduce");
		Thread.sleep(sleep);
		busqueda = aplicacion.buscarPorTitulo("deprueba chicle3 modificada");
		System.out.println(busqueda);
		aplicacion.reproducirReproducible(busqueda.get(0));
		
		Thread.sleep(5000);
		aplicacion.getCola().stop();
		
		System.out.println("El usuario2 denuncia que la cancion es plagio y deja de seguir al usuario1");
		Thread.sleep(sleep);
		aplicacion.denunciarPlagio((Cancion) busqueda.get(0), "Esto lo vi por Internet hace unos dias");
		aplicacion.getUsuarioLogeado().dejarSeguirUsuario(busqueda.get(0).getAutor());
		
		System.out.println("El usuario2 cierra sesion");
		Thread.sleep(sleep);
		aplicacion.logout();
		
		System.out.println("El usuario1 inicia sesion");
		Thread.sleep(sleep);
		aplicacion.login("usuario1", "contrasenia1");
		
		System.out.println("El usuario1 consulta sus notificaciones y se da cuenta de que le han denunciado y puede ser bloqueado proximamente");
		Thread.sleep(sleep);
		System.out.println(aplicacion.getUsuarioLogeado().getNotificaciones());
		
		System.out.println("El usuario1 decide borrar su album");
		Thread.sleep(sleep);
		aplicacion.borrarAlbum((Album)aplicacion.getUsuarioLogeado().getBuscables().get(3));
		
		
		System.out.println("El usuario1 cierra sesion");
		Thread.sleep(sleep);
		aplicacion.logout();		
		
		
		System.out.println("El administrador inicia sesion");
		Thread.sleep(sleep);
		aplicacion.login("admin", "admin");
		
		System.out.println("El administrador consulta las denuncias a tramitar");
		Thread.sleep(sleep);
		System.out.println(aplicacion.getAdministrador().getDenuncias());
		
		System.out.println("El administrador concluye que la denuncia es falsa");
		Thread.sleep(sleep);
		aplicacion.getAdministrador().tramitarDenuncia(aplicacion.getAdministrador().getDenuncias().get(0), false);
		
		System.out.println("El administrador cierra sesion");
		Thread.sleep(sleep);
		aplicacion.logout();
		
		System.out.println("El usuario2 trata de iniciar sesion");
		Thread.sleep(sleep);
		try{
			aplicacion.login("usuario2", "contrasenia2");
		} catch (ExcepcionLoginBloqueado e) {
			System.out.println("No puede iniciar sesion, su cuenta esta bloqueada");
		}
		
		System.out.println("Guardamos los datos, los borramos (simulando que cerramos la app) y los volvemos a cargar");

		aplicacion.guardarDatos();
		aplicacion.borrarDatos();
		aplicacion = null;
		aplicacion = Aplicacion.cargarDatos();

		
		System.out.println("Se intenta registrar un usuario con el nombre de usuario2");
		Thread.sleep(sleep);
		try {
			aplicacion.aniadirUsuario("usuario2", "contrasenia2", "Uss", LocalDate.of(1998, 11, 2));
		} catch (ExcepcionNombreDeUsuarioNoDisponible e) {
			System.out.println("Error: Nombre de usuario no disponible");
		}
		
		System.out.println("Se registra, esta vez como usuario3 e inicia sesion");
		Thread.sleep(sleep);
		aplicacion.aniadirUsuario("usuario3", "contrasenia3", "Usuario Tres", LocalDate.of(1998, 11, 2));
		aplicacion.login("usuario3", "contrasenia3");
		
		System.out.println("El usuario3 realiza una busqueda por autor: 'usuario1'");
		Thread.sleep(sleep);
		busqueda = aplicacion.buscarPorAutor("usuario1");
		System.out.println(busqueda);
		Thread.sleep(sleep);
		
		System.out.println("El usuario3 reproduce el album y le gusta tanto que lo aniade a la cola 5 veces");
		try {
			aplicacion.reproducirReproducible(busqueda.get(3));
			for(int i = 0; i < 5; i++) {
				aplicacion.aniadirALaCola(busqueda.get(3));
			}
		} catch (ExcepcionLimiteReproducidasAlcanzado e) {
			System.out.println("No podras reproducir mas veces este mes, limite alcanzado");
		}
		Thread.sleep(10000);
		aplicacion.getCola().stop();
		
		System.out.println("El usuario3 decide, por tanto, pagar el servicio Premium");
		Thread.sleep(sleep);
		aplicacion.pagarPremium("5555555555555555", "Pago Premium CompasON");
		
		System.out.println("El usuario3 reproduce otra cancion ahora que no tiene limite");
		aplicacion.reproducirReproducible(busqueda.get(0));
		
		Thread.sleep(5000);
		aplicacion.getCola().stop();
		
		System.out.println("El usuario3 se crea una lista de reproduccion con el album y esta cancion:");
		ArrayList<Reproducible> cancionesLista = new ArrayList<>();
		cancionesLista.add(busqueda.get(4));
		cancionesLista.add(busqueda.get(0));
		aplicacion.getUsuarioLogeado().crearLista("Favoritas", cancionesLista);
		System.out.println(aplicacion.getUsuarioLogeado().getListas());
		
		System.out.println("A el usuario3 no le convence la lista y decide borrarla");
		aplicacion.getUsuarioLogeado().borrarLista(aplicacion.getUsuarioLogeado().getListas().get(0));
		
		System.out.println("El usuario3 cierra sesion");
		aplicacion.logout();*/
		
		System.out.println("Guardamos los datos");
		aplicacion.guardarDatos();
		
		
	}
}
