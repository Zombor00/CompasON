package aplicacion;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import excepciones.*;
import media.Album;
import media.Buscable;
import media.Cancion;
import media.Estado;
import media.EstadoValidacion;
import pads.musicPlayer.exceptions.Mp3InvalidFileException;
import pads.musicPlayer.exceptions.Mp3PlayerException;
import usuarios.UsuarioRegistrado;

/**
 * Implementamos un tester para la clase Aplicacion
 *
 * @author Antonio Garcia antonio.garcian@estudiante.uam.es
 * @version 1.0 (22-03-2019)
 */
public class AplicacionTest {
	
	static Aplicacion aplicacion = null;

	@BeforeAll
	static void before() throws FileNotFoundException, Mp3PlayerException, ExcepcionParametrosDeEntradaIncorrectos, ExcepcionNombreDeUsuarioNoDisponible {
		aplicacion = Aplicacion.getInstance(0,0,0);
	}
	
	@Test
	void testAniadirUsuario() {
		boolean excepcionLanzada = false;
		try {
			aplicacion.aniadirUsuario("nombre usuario repetido", "contrasenia", "nombre", LocalDate.now());
			aplicacion.aniadirUsuario("nombre usuario repetido", "contrasenia", "nombre", LocalDate.now());
		} catch (ExcepcionParametrosDeEntradaIncorrectos e) {
			fail("Lanzada excepcion no esperada ExcepcionParametrosDeEntradaIncorrectos");
		} catch (ExcepcionNombreDeUsuarioNoDisponible e) {
			excepcionLanzada = true;
		} catch (NoSuchAlgorithmException e) {
			fail("Lanzada excepcion no esperada: NoSuchAlgorithmException");
		}
		assertTrue(excepcionLanzada);
	}
	
	
	
	@Test /* Login correcto de un usuario registrado */
	void testLog1() throws ExcepcionParametrosDeEntradaIncorrectos, FileNotFoundException, Mp3PlayerException{
		try {
			aplicacion.aniadirUsuario("nombre usuario", "contrasenia", "nombre", LocalDate.now());
		} catch (ExcepcionNombreDeUsuarioNoDisponible e) {
			fail("Lanzada excepcion no esperada: ExcepcionNombreDeUsuarioNoDisponible");
		} catch (NoSuchAlgorithmException e) {
			fail("Lanzada excepcion no esperada: NoSuchAlgorithmException");
		}

		try {
			aplicacion.login("nombre usuario", "contrasenia");
		}
		catch(ExcepcionLoginBloqueado e) {
			fail("Lanzada excepcion no esperada ExcepcionLoginBloqueado");
		}
		catch(ExcepcionLoginErrorCredenciales e) {
			fail("Lanzada excepcion no esperada ExcepcionLoginErrorCredenciales");
		}
		catch (ExcepcionParametrosDeEntradaIncorrectos e) {
			fail("Lanzada excepcion no esperada ExcepcionParametrosDeEntradaIncorrectos");
		} catch (NoSuchAlgorithmException e) {
			fail("Lanzada excepcion no esperada: NoSuchAlgorithmException");
		}
		assertTrue(aplicacion.getUsuarioLogeado()!=null);
		assertFalse(aplicacion.getAdministradorLogeado());
		aplicacion.logout();
		assertTrue(aplicacion.getUsuarioLogeado()==null);
		assertFalse(aplicacion.getAdministradorLogeado());
	}
	
	@Test /* Login correcto del administrador */
	void testLog2() throws FileNotFoundException, Mp3PlayerException {
		try {
			aplicacion.login(aplicacion.nombreAdministrador, aplicacion.contraseniaAdministrador);
		}
		catch(ExcepcionLoginBloqueado e) {
			fail("Lanzada excepcion no esperada ExcepcionLoginBloqueado");
		}
		catch(ExcepcionLoginErrorCredenciales e) {
			fail("Lanzada excepcion no esperada ExcepcionLoginErrorCredenciales");
		}
		catch (ExcepcionParametrosDeEntradaIncorrectos e) {
			fail("Lanzada excepcion no esperada ExcepcionParametrosDeEntradaIncorrectos");
		} catch (NoSuchAlgorithmException e) {
			fail("Lanzada excepcion no esperada: NoSuchAlgorithmException");
		}
		assertTrue(aplicacion.getUsuarioLogeado()==null);
		assertTrue(aplicacion.getAdministradorLogeado());
		aplicacion.logout();
		assertTrue(aplicacion.getUsuarioLogeado()==null);
		assertFalse(aplicacion.getAdministradorLogeado());
	}
	
	@Test /* Login fallido. Nombre o contrasenia incorrectos */
	void testLog3() throws FileNotFoundException, Mp3PlayerException {
		boolean excepcionLanzada = false;
		try {
			aplicacion.login("nombre invalido","contrasenia");
			fail("Se esperaba excepcion ExcepcionLoginErrorCredenciales");
		}
		catch(ExcepcionLoginBloqueado e) {
			fail("Lanzada excepcion no esperada ExcepcionLoginBloqueado");
		}
		catch(ExcepcionLoginErrorCredenciales e) {
			excepcionLanzada = true;
		}
		catch (ExcepcionParametrosDeEntradaIncorrectos e) {
			fail("Lanzada excepcion no esperada ExcepcionParametrosDeEntradaIncorrectos");
		} catch (NoSuchAlgorithmException e) {
			fail("Lanzada excepcion no esperada: NoSuchAlgorithmException");
		}
		assertTrue(excepcionLanzada);
		assertTrue(aplicacion.getUsuarioLogeado()==null);
		assertFalse(aplicacion.getAdministradorLogeado());
		aplicacion.logout();
		assertTrue(aplicacion.getUsuarioLogeado()==null);
		assertFalse(aplicacion.getAdministradorLogeado());
	}
	
	@Test /* Login fallido. Usuario bloqueado */
	void testLog4() throws ExcepcionParametrosDeEntradaIncorrectos, FileNotFoundException, Mp3PlayerException {
		UsuarioRegistrado u = null;
		boolean excepcionLanzada = false;
		
		try {
			aplicacion.aniadirUsuario("nombre usuario", "contrasenia", "nombre", LocalDate.now());
		} catch (ExcepcionNombreDeUsuarioNoDisponible e) {
			
		} catch (NoSuchAlgorithmException e) {
			fail("Lanzada excepcion no esperada: NoSuchAlgorithmException");
		}
		
		/* Inicio normal de sesion */
		try {
			aplicacion.login("nombre usuario", "contrasenia");
		}
		catch(ExcepcionLoginBloqueado e) {
			fail("Lanzada excepcion no esperada ExcepcionLoginBloqueado");
		}
		catch(ExcepcionLoginErrorCredenciales e) {
			fail("Lanzada excepcion no esperada ExcepcionLoginErrorCredenciales");
		}
		catch (ExcepcionParametrosDeEntradaIncorrectos e) {
			fail("Lanzada excepcion no esperada ExcepcionParametrosDeEntradaIncorrectos");
		} catch (NoSuchAlgorithmException e) {
			fail("Lanzada excepcion no esperada: NoSuchAlgorithmException");
		}
		u = aplicacion.getUsuarioLogeado();
		aplicacion.logout();
		
		/* Bloquemos al usuario durante cinco dias */
		u.setBloqueadoHasta(LocalDate.now().plusDays(5));
		
		/* El usuario bloqueado intenta iniciar sesion */
		try {
			aplicacion.login("nombre usuario","contrasenia");
			fail("Se esperaba excepcion ExcepcionLoginBloqueado");
		}
		catch(ExcepcionLoginBloqueado e) {
			excepcionLanzada = true;
		}
		catch(ExcepcionLoginErrorCredenciales e) {
			fail("Lanzada excepcion no esperada ExcepcionLoginErrorCredenciales");
		}
		catch (ExcepcionParametrosDeEntradaIncorrectos e) {
			fail("Lanzada excepcion no esperada ExcepcionParametrosDeEntradaIncorrectos");
		} catch (NoSuchAlgorithmException e) {
			fail("Lanzada excepcion no esperada: NoSuchAlgorithmException");
		}
		assertTrue(excepcionLanzada);
		assertTrue(aplicacion.getUsuarioLogeado()==null);
		assertFalse(aplicacion.getAdministradorLogeado());
		
		/* Simulamos que pasan 5 dias */
		u.setBloqueadoHasta(u.getBloqueadoHasta().minusDays(5));
		
		/* El usuario ya no esta bloqueado e inicia sesion correctamente */
		try {
			aplicacion.login("nombre usuario","contrasenia");
		}
		catch(ExcepcionLoginBloqueado e) {
			fail("Lanzada excepcion no esperada ExcepcionLoginBloqueado");
		}
		catch(ExcepcionLoginErrorCredenciales e) {
			fail("Lanzada excepcion no esperada ExcepcionLoginErrorCredenciales");
		}
		catch (ExcepcionParametrosDeEntradaIncorrectos e) {
			fail("Lanzada excepcion no esperada ExcepcionParametrosDeEntradaIncorrectos");
		} catch (NoSuchAlgorithmException e) {
			fail("Lanzada excepcion no esperada: NoSuchAlgorithmException");
		}
		assertTrue(aplicacion.getUsuarioLogeado()!=null);
		assertFalse(aplicacion.getAdministradorLogeado());
		aplicacion.logout();
		assertTrue(aplicacion.getUsuarioLogeado()==null);
		assertFalse(aplicacion.getAdministradorLogeado());
	}
	
	@Test 
	void testLog5() throws NoSuchAlgorithmException, ExcepcionParametrosDeEntradaIncorrectos, ExcepcionNombreDeUsuarioNoDisponible, ExcepcionLoginErrorCredenciales, ExcepcionLoginBloqueado {
		try {
			aplicacion.aniadirUsuario("nombre usuario", "contrasenia", "nombre", LocalDate.now());
		} catch (ExcepcionNombreDeUsuarioNoDisponible e) {
			
		}
		aplicacion.login("nombre usuario", "contrasenia");
		aplicacion.getUsuarioLogeado().setUltimoLogin(LocalDate.now().minusMonths(1));
		aplicacion.getUsuarioLogeado().aniadirReproducida();
		aplicacion.getUsuarioLogeado().aniadirReproduccion();
		aplicacion.setUsuarioLogeado(null);
		aplicacion.login("nombre usuario", "contrasenia");
		assertTrue(aplicacion.getUsuarioLogeado().getReproducidas() == 0);
	}
	
	@Test 
	void testBuscarCancionesPorTitulo() throws ExcepcionParametrosDeEntradaIncorrectos {
		int numDebeEncontrar = 10; /* Numero de canciones que la busqueda debe encontrar */
		int numDebeObviar = 5; /* Numero de canciones que la busqueda debe obviar */
		ArrayList<String> nombresQueDebeEncontrar = new ArrayList<>();
		ArrayList<String> nombresQueDebeObviar = new ArrayList<>();
		ArrayList<Buscable> encontradas = new ArrayList<>();
		ArrayList<String> nombresQueHaEncontrado = new ArrayList<>();
		
		aplicacion.borrarDatos();		
		
		for (int i=0; i< numDebeEncontrar; i++) {
			nombresQueDebeEncontrar.add("debeEncontrar"+i);
		}
		for (int i=0; i< numDebeObviar; i++) {
			nombresQueDebeObviar.add("debeObviar"+i);
		}
		
		for (int i = 0; i < numDebeEncontrar; i++) {
			try {
				aplicacion.aniadirCancion(new Cancion(nombresQueDebeEncontrar.get(i),"canciones/Thats What I Like.mp3",null));
			} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
				fail("Lanzada excepcion no esperada");
			}
		}
		for (int i = 0; i < numDebeObviar; i++) {
			try {
				aplicacion.aniadirCancion(new Cancion(nombresQueDebeObviar.get(i),"canciones/Thats What I Like.mp3",null));
			} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
				fail("Lanzada excepcion no esperada");
			}
		}
		
		encontradas = aplicacion.buscarPorTitulo("debeEncontrar");
		
		for (Buscable buscable : encontradas) {
			nombresQueHaEncontrado.add(buscable.getTitulo());
		}
		
		assertTrue(nombresQueHaEncontrado.size()==numDebeEncontrar);
		
		for (int i=0; i< numDebeEncontrar; i++) {
			assertTrue(nombresQueHaEncontrado.get(i) == nombresQueDebeEncontrar.get(i));
		}
		
		aplicacion.borrarDatos();	
		
		
	}
	
	@Test
	void testSubirCancionYBorrarCancion() {
		boolean lanzadaExcepcion = false;
		Cancion cancion1 = null;
		try {
			aplicacion.logout();
		} catch (FileNotFoundException | Mp3PlayerException e2) {
		}
		
		try {
			aplicacion.aniadirUsuario("nombre usuario3", "contrasenia", "nombre", LocalDate.now());
		} catch (ExcepcionParametrosDeEntradaIncorrectos e) {
			fail("Lanzada excepcion no esperada ExcepcionParametrosDeEntradaIncorrectos");
		} catch (ExcepcionNombreDeUsuarioNoDisponible e) {
			fail("Lanzada excepcion no esperada ExcepcionNombreDeUsuarioNoDisponible");
		} catch (NoSuchAlgorithmException e) {
			fail("Lanzada excepcion no esperada: NoSuchAlgorithmException");
		}
		
		try {
			aplicacion.subirCancion("What I like", "canciones/Thats Like.mp3");
			fail("Excepcion esperada no lanzada");
		} catch (Mp3InvalidFileException | ExcepcionDuracionLimiteSuperada | ExcepcionParametrosDeEntradaIncorrectos
				| IOException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionUsuarioSinCuenta e) {
			lanzadaExcepcion = true;
		}
		
		assertTrue(lanzadaExcepcion);
		lanzadaExcepcion = false;
		
		try {
			aplicacion.login("nombre usuario3", "contrasenia");
		}
		catch(ExcepcionLoginBloqueado e) {
			fail("Lanzada excepcion no esperada ExcepcionLoginBloqueado");
		}
		catch(ExcepcionLoginErrorCredenciales e) {
			fail("Lanzada excepcion no esperada ExcepcionLoginErrorCredenciales");
		}
		catch (ExcepcionParametrosDeEntradaIncorrectos e) {
			fail("Lanzada excepcion no esperada ExcepcionParametrosDeEntradaIncorrectos");
		} catch (NoSuchAlgorithmException e) {
			fail("Lanzada excepcion no esperada: NoSuchAlgorithmException");
		}
		
		try {
			aplicacion.subirCancion("What I like", "canciones/Thats Like.mp3");
			fail("Excepcion esperada no lanzada");
		} catch (ExcepcionDuracionLimiteSuperada | ExcepcionParametrosDeEntradaIncorrectos
				| IOException | ExcepcionUsuarioSinCuenta | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		} catch (Mp3InvalidFileException e) {
			lanzadaExcepcion = true;
		}
		
		assertTrue(lanzadaExcepcion);
		lanzadaExcepcion = false;
		
		try {
			aplicacion.subirCancion(null, "canciones/Thats What I Like.mp3");
			fail("Excepcion esperada no lanzada");
		} catch (Mp3InvalidFileException | ExcepcionDuracionLimiteSuperada
				| IOException | ExcepcionUsuarioSinCuenta | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		} catch(ExcepcionParametrosDeEntradaIncorrectos e1) {
			lanzadaExcepcion = true;
		}
		assertTrue(lanzadaExcepcion);
		
		try {
			aplicacion.subirCancion("What I like", "canciones/Thats What I Like.mp3");
		} catch (Mp3InvalidFileException | ExcepcionDuracionLimiteSuperada | ExcepcionParametrosDeEntradaIncorrectos
				| IOException | ExcepcionUsuarioSinCuenta | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
		
		try {
			aplicacion.borrarCancion(null);
			fail("Excepcion esperada no lanzada");
		} catch (ExcepcionParametrosDeEntradaIncorrectos e) {
			lanzadaExcepcion = true;
		}
		
		assertTrue(lanzadaExcepcion);
		
		try {
			cancion1 = new Cancion("cancion1","canciones/Thats What I Like.mp3",aplicacion.getUsuarioLogeado());
		} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
	    cancion1.validar(EstadoValidacion.EXPLICITO);
		
		try {
			aplicacion.aniadirCancion(cancion1);
		} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
			fail("Lanzada excepcion no esperada");
		}
		
		try {
			aplicacion.borrarCancion(cancion1);
		} catch (ExcepcionParametrosDeEntradaIncorrectos e) {
			fail("Lanzada excepcion no esperada");
		}
		
		try {
			aplicacion.logout();
		} catch (FileNotFoundException | Mp3PlayerException e) {
			fail("Lanzada excepcion no esperada");
		}
		
	}

	@Test
	void testAniadirYBorrarAlbum() {
		boolean lanzadaExcepcion = false;
		ArrayList<Cancion> canciones = new ArrayList<>();
		Cancion cancion1 = null;
		
		try {
			aplicacion.logout();
		} catch (FileNotFoundException | Mp3PlayerException e2) {
		}
		
		try {
			aplicacion.aniadirUsuario("nombre usuario2", "contrasenia", "nombre", LocalDate.now());
		} catch (NoSuchAlgorithmException | ExcepcionParametrosDeEntradaIncorrectos
				| ExcepcionNombreDeUsuarioNoDisponible e3) {
			fail("Lanzada excepcion no esperada");
		}
		
		try {
			aplicacion.login("nombre usuario2", "contrasenia");
		}
		catch(ExcepcionLoginBloqueado e) {
			fail("Lanzada excepcion no esperada ExcepcionLoginBloqueado");
		}
		catch(ExcepcionLoginErrorCredenciales e) {
			fail("Lanzada excepcion no esperada ExcepcionLoginErrorCredenciales");
		}
		catch (ExcepcionParametrosDeEntradaIncorrectos e) {
			fail("Lanzada excepcion no esperada ExcepcionParametrosDeEntradaIncorrectos");
		} catch (NoSuchAlgorithmException e) {
			fail("Lanzada excepcion no esperada: NoSuchAlgorithmException");
		}
		
		try {
			aplicacion.aniadirAlbum("Hola", null);
			fail("Excepcion esperada no lanzada");
		} catch (ExcepcionErrorCreandoAlbum | ExcepcionUsuarioSinCuenta
				| ExcepcionInsercionInvalida | ExcepcionCancionNoValidada e) {
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionParametrosDeEntradaIncorrectos e) {
			lanzadaExcepcion = true;
		}
		assertTrue(lanzadaExcepcion);
		lanzadaExcepcion = false;
		
		try {
			cancion1 = new Cancion("cancion1","canciones/Thats What I Like.mp3",aplicacion.getUsuarioLogeado());
		} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
		
		cancion1.validar(EstadoValidacion.APTOMENORES);
		
		canciones.add(cancion1);
		
		try {
			aplicacion.aniadirAlbum("Increible", canciones);
		} catch (ExcepcionErrorCreandoAlbum | ExcepcionParametrosDeEntradaIncorrectos | ExcepcionUsuarioSinCuenta
				| ExcepcionInsercionInvalida | ExcepcionCancionNoValidada e2) {
			fail("Lanzada excepcion no esperada");
		}
		
		try {
			aplicacion.logout();
		} catch (FileNotFoundException | Mp3PlayerException e) {
			fail("Lanzada excepcion no esperada");
		}
		
		try {
			aplicacion.aniadirAlbum("Nada", canciones);
			fail("Excepcion esperada no lanzada");
		} catch (ExcepcionErrorCreandoAlbum | ExcepcionParametrosDeEntradaIncorrectos
				| ExcepcionInsercionInvalida | ExcepcionCancionNoValidada e) {
			fail("Lanzada excepcion no esperada");
		} catch(ExcepcionUsuarioSinCuenta e1) {
			lanzadaExcepcion = true;
		}
		assertTrue(lanzadaExcepcion);
		lanzadaExcepcion = false;
		
		try {
			aplicacion.borrarAlbum(null);
			fail("Excepcion esperada no lanzada");
		} catch (ExcepcionParametrosDeEntradaIncorrectos e) {
			lanzadaExcepcion = true;
		}
		assertTrue(lanzadaExcepcion);
		
		try {
			aplicacion.login("nombre usuario2", "contrasenia");
		} catch (NoSuchAlgorithmException | ExcepcionLoginErrorCredenciales | ExcepcionLoginBloqueado
				| ExcepcionParametrosDeEntradaIncorrectos e1) {
			fail("Excepcion lanzada no esperada");
		}
		
		Album album = null;
		try {
			album = new Album("Increible",aplicacion.getUsuarioLogeado(),canciones);
		} catch (ExcepcionInsercionInvalida | ExcepcionCancionNoValidada e1) {
			fail("Lanzada excepcion no esperada");
		}
		try {
			aplicacion.borrarAlbum(album);
		} catch (ExcepcionParametrosDeEntradaIncorrectos e) {

			fail("Excepcion lanzada no esperada");
		}
		
		try {
			aplicacion.logout();
		} catch (FileNotFoundException | Mp3PlayerException e) {
			fail("Excepcion lanzada no esperada");
		}
	}
	
	@Test 
	void testBuscarAlbumesPorTitulo() throws ExcepcionErrorCreandoAlbum, ExcepcionParametrosDeEntradaIncorrectos, NoSuchAlgorithmException, ExcepcionNombreDeUsuarioNoDisponible, ExcepcionLoginErrorCredenciales, ExcepcionLoginBloqueado, FileNotFoundException, Mp3PlayerException, ExcepcionUsuarioSinCuenta {
		int numDebeEncontrar = 10; /* Numero de albumes que la busqueda debe encontrar */
		int numDebeObviar = 5; /* Numero de albumes que la busqueda debe obviar */
		ArrayList<String> nombresQueDebeEncontrar = new ArrayList<>();
		ArrayList<String> nombresQueDebeObviar = new ArrayList<>();
		ArrayList<Buscable> encontrados = new ArrayList<>();
		ArrayList<String> nombresQueHaEncontrado = new ArrayList<>();
		ArrayList<Cancion> listaCancionesAuxiliar = new ArrayList<>();
		
		aplicacion.borrarDatos();
		
		/* Es ecesario que haya algun usuario logeado para poder aniadir un album */
		aplicacion.aniadirUsuario("nombre usuario", "contrasenia", "nombre", LocalDate.now());
		aplicacion.login("nombre usuario", "contrasenia");	
		
		for (int i=0; i< numDebeEncontrar; i++) {
			nombresQueDebeEncontrar.add("debeEncontrar"+i);
		}
		for (int i=0; i< numDebeObviar; i++) {
			nombresQueDebeObviar.add("debeObviar"+i);
		}
		
		for (int i = 0; i < numDebeEncontrar; i++) {
			try {
				aplicacion.aniadirAlbum(nombresQueDebeEncontrar.get(i), listaCancionesAuxiliar);
			} catch (ExcepcionInsercionInvalida | ExcepcionCancionNoValidada e) {
				fail("Lanzada excepcion no esperada");
			}
		}
		for (int i = 0; i < numDebeObviar; i++) {
			try {
				aplicacion.aniadirAlbum(nombresQueDebeObviar.get(i), listaCancionesAuxiliar);
			} catch (ExcepcionInsercionInvalida | ExcepcionCancionNoValidada e) {
				fail("Lanzada excepcion no esperada");
			}
		}
		
		encontrados = aplicacion.buscarPorTitulo("debeEncontrar");
		
		for (Buscable buscable : encontrados) {
			nombresQueHaEncontrado.add(buscable.getTitulo());
		}
		
		assertTrue(nombresQueHaEncontrado.size()==numDebeEncontrar);
		
		for (int i=0; i< numDebeEncontrar; i++) {
			assertTrue(nombresQueHaEncontrado.get(i) == nombresQueDebeEncontrar.get(i));
		}
		
		aplicacion.logout();
		
		aplicacion.borrarDatos();	
	}
	
	@Test
	void testBuscarPorAutor() throws NoSuchAlgorithmException, ExcepcionParametrosDeEntradaIncorrectos, ExcepcionNombreDeUsuarioNoDisponible, ExcepcionLoginErrorCredenciales, ExcepcionLoginBloqueado, FileNotFoundException, Mp3PlayerException {
		
		int numDebeEncontrar = 10; /* Numero de albumes que la busqueda debe encontrar */
		int numDebeObviar = 5; /* Numero de albumes que la busqueda debe obviar */
		ArrayList<String> nombresQueDebeEncontrar = new ArrayList<>();
		ArrayList<String> nombresQueDebeObviar = new ArrayList<>();
		ArrayList<Buscable> encontrados = new ArrayList<>();
		ArrayList<String> nombresQueHaEncontrado = new ArrayList<>();
		Cancion c = null;
		
		aplicacion.borrarDatos();
		
		/* Es ecesario que haya algun usuario logeado para que haya autor */
		aplicacion.aniadirUsuario("autor", "contrasenia", "autor", LocalDate.now());
		aplicacion.login("autor", "contrasenia");
		
		for (int i=0; i< numDebeEncontrar; i++) {
			nombresQueDebeEncontrar.add("debeEncontrar"+i);
		}
		for (int i=0; i< numDebeObviar; i++) {
			nombresQueDebeObviar.add("debeObviar"+i);
		}
		
		for (int i = 0; i < numDebeEncontrar; i++) {
			try {
				c = new Cancion(nombresQueDebeEncontrar.get(i),"canciones/Thats What I Like.mp3",aplicacion.getUsuarioLogeado());
			} catch (ExcepcionMp3NoValido e) {
				fail("Lanzada excepcion no esperada");
			}
			aplicacion.aniadirCancion(c);
			aplicacion.getUsuarioLogeado().aniadirBuscable(c);
		}
		for (int i = 0; i < numDebeObviar; i++) {
			try {
				aplicacion.aniadirCancion(new Cancion(nombresQueDebeObviar.get(i),"canciones/Thats What I Like.mp3",null));
			} catch (ExcepcionMp3NoValido e) {
				fail("Lanzada excepcion no esperada");
			}
		}
		
		encontrados = aplicacion.buscarPorAutor("auto");
		
		for (Buscable b : encontrados) {
			nombresQueHaEncontrado.add(b.getTitulo());
		}
		
		assertTrue(nombresQueHaEncontrado.size()==numDebeEncontrar);
		
		for (int i=0; i< numDebeEncontrar; i++) {
			assertTrue(nombresQueHaEncontrado.get(i) == nombresQueDebeEncontrar.get(i));
		}
		
		aplicacion.logout();
		
		aplicacion.borrarDatos();	

	}
	
	@Test
	void testPagarPremium() throws NoSuchAlgorithmException, ExcepcionParametrosDeEntradaIncorrectos, ExcepcionNombreDeUsuarioNoDisponible, ExcepcionLoginErrorCredenciales, ExcepcionLoginBloqueado, InvalidCardNumberException, FailedInternetConnectionException, OrderRejectedException {
		aplicacion.aniadirUsuario("usuario que va ser premium", "contrasenia", "nombreCompleto", LocalDate.now());
		aplicacion.login("usuario que va ser premium", "contrasenia");
		aplicacion.pagarPremium("5390700823285988", "test");
		assertTrue(aplicacion.getUsuarioLogeado().getPremiumHasta().equals(LocalDate.now().plusDays(30)));
	}
	
	@Test
	void testGuardarCargar() throws ExcepcionParametrosDeEntradaIncorrectos, NoSuchAlgorithmException, ExcepcionNombreDeUsuarioNoDisponible, IOException, ClassNotFoundException, Mp3PlayerException {
		List<Buscable> canciones = null;
		List<UsuarioRegistrado> usuarios = null;
		aplicacion.borrarDatos();
		
		try {
			aplicacion.aniadirCancion(new Cancion("nombre cancion","canciones/Thats What I Like.mp3",null));
		} catch (ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
		aplicacion.aniadirUsuario("nombreUsuario", "contrasenia", "nombreCompleto", LocalDate.now());
		canciones = aplicacion.getBuscables();
		usuarios = aplicacion.getUsuarios();
		aplicacion.guardarDatos();
		
		aplicacion.borrarDatos();
		
		aplicacion = Aplicacion.cargarDatos();
		
		assertTrue(usuarios.get(0).equals(aplicacion.getUsuarios().get(0)));
		assertTrue(((Cancion)canciones.get(0)).equals((Cancion)aplicacion.getBuscables().get(0)));
		
	}
	
	@Test
	void testBorrarEfectivamente() throws ExcepcionParametrosDeEntradaIncorrectos, NoSuchAlgorithmException, ExcepcionNombreDeUsuarioNoDisponible, ExcepcionLoginErrorCredenciales, ExcepcionLoginBloqueado {
		aplicacion.borrarDatos();
		aplicacion.aniadirUsuario("autor", "contrasenia", "nombre completo", LocalDate.now());
		aplicacion.login("autor", "contrasenia");
		Cancion cancion = null;
		try {
			cancion = new Cancion("titulo","canciones/Thats What I Like.mp3",aplicacion.getUsuarioLogeado());
		} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
		aplicacion.getUsuarioLogeado().aniadirBuscable(cancion);
		aplicacion.aniadirCancion(cancion);
		
		assertTrue(aplicacion.getBuscables().size() == 1);
		cancion.setEstado(Estado.BORRADO);
		assertTrue(aplicacion.getBuscables().size() == 1);
		aplicacion.borrarEfectivamente();
		assertTrue(aplicacion.getBuscables().size() == 0);
	}

}
