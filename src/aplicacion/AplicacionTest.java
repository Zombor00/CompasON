package aplicacion;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.*;
import media.Buscable;
import media.Cancion;
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
		aplicacion = Aplicacion.getInstance(10,10,10);
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
		}
		assertTrue(excepcionLanzada);
	}
	
	@Test /* Login correcto de un usuario registrado */
	void testLog1() throws ExcepcionParametrosDeEntradaIncorrectos{
		try {
			aplicacion.aniadirUsuario("nombre usuario", "contrasenia", "nombre", LocalDate.now());
		} catch (ExcepcionNombreDeUsuarioNoDisponible e) {
			
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
		}
		assertTrue(aplicacion.getUsuarioLogeado()!=null);
		assertFalse(aplicacion.getAdministradorLogeado());
		aplicacion.logout();
		assertTrue(aplicacion.getUsuarioLogeado()==null);
		assertFalse(aplicacion.getAdministradorLogeado());
	}
	
	@Test /* Login correcto del administrador */
	void testLog2() {
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
		}
		assertTrue(aplicacion.getUsuarioLogeado()==null);
		assertTrue(aplicacion.getAdministradorLogeado());
		aplicacion.logout();
		assertTrue(aplicacion.getUsuarioLogeado()==null);
		assertFalse(aplicacion.getAdministradorLogeado());
	}
	
	@Test /* Login fallido. Nombre o contrasenia incorrectos */
	void testLog3() {
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
		}
		assertTrue(excepcionLanzada);
		assertTrue(aplicacion.getUsuarioLogeado()==null);
		assertFalse(aplicacion.getAdministradorLogeado());
		aplicacion.logout();
		assertTrue(aplicacion.getUsuarioLogeado()==null);
		assertFalse(aplicacion.getAdministradorLogeado());
	}
	
	@Test /* Login fallido. Usuario bloqueado */
	void testLog4() throws ExcepcionParametrosDeEntradaIncorrectos {
		UsuarioRegistrado u = null;
		boolean excepcionLanzada = false;
		
		try {
			aplicacion.aniadirUsuario("nombre usuario", "contrasenia", "nombre", LocalDate.now());
		} catch (ExcepcionNombreDeUsuarioNoDisponible e) {
			
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
		}
		assertTrue(aplicacion.getUsuarioLogeado()!=null);
		assertFalse(aplicacion.getAdministradorLogeado());
		aplicacion.logout();
		assertTrue(aplicacion.getUsuarioLogeado()==null);
		assertFalse(aplicacion.getAdministradorLogeado());
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
			aplicacion.aniadirCancion(new Cancion(nombresQueDebeEncontrar.get(i),"Ruta",null));
		}
		for (int i = 0; i < numDebeObviar; i++) {
			aplicacion.aniadirCancion(new Cancion(nombresQueDebeObviar.get(i),"Ruta",null));
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
	void testBuscarAlbumesPorTitulo() throws ExcepcionErrorCreandoAlbum, ExcepcionParametrosDeEntradaIncorrectos {
		int numDebeEncontrar = 10; /* Numero de albumes que la busqueda debe encontrar */
		int numDebeObviar = 5; /* Numero de albumes que la busqueda debe obviar */
		ArrayList<String> nombresQueDebeEncontrar = new ArrayList<>();
		ArrayList<String> nombresQueDebeObviar = new ArrayList<>();
		ArrayList<Buscable> encontrados = new ArrayList<>();
		ArrayList<String> nombresQueHaEncontrado = new ArrayList<>();
		ArrayList<Cancion> listaCancionesAuxiliar = new ArrayList<>();
		
		aplicacion.borrarDatos();	
		
		for (int i=0; i< numDebeEncontrar; i++) {
			nombresQueDebeEncontrar.add("debeEncontrar"+i);
		}
		for (int i=0; i< numDebeObviar; i++) {
			nombresQueDebeObviar.add("debeObviar"+i);
		}
		
		for (int i = 0; i < numDebeEncontrar; i++) {
			aplicacion.aniadirAlbum(nombresQueDebeEncontrar.get(i), LocalDate.now(),listaCancionesAuxiliar);
		}
		for (int i = 0; i < numDebeObviar; i++) {
			aplicacion.aniadirAlbum(nombresQueDebeObviar.get(i), LocalDate.now(),listaCancionesAuxiliar);
		}
		
		encontrados = aplicacion.buscarPorTitulo("debeEncontrar");
		
		for (Buscable buscable : encontrados) {
			nombresQueHaEncontrado.add(buscable.getTitulo());
		}
		
		assertTrue(nombresQueHaEncontrado.size()==numDebeEncontrar);
		
		for (int i=0; i< numDebeEncontrar; i++) {
			assertTrue(nombresQueHaEncontrado.get(i) == nombresQueDebeEncontrar.get(i));
		}
		
		aplicacion.borrarDatos();	
	}
	

}
