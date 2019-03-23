package aplicacion;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.*;
import media.Buscable;
import usuarios.UsuarioRegistrado;

/**
 * Implementamos un tester para la clase Aplicacion
 *
 * @author Antonio Garcia antonio.garcian@estudiante.uam.es
 * @version 1.0 (22-03-2019)
 */
public class AplicacionTest {
	
	Aplicacion aplicacion = Aplicacion.getInstance();

	@BeforeAll
	static void before() {
		Aplicacion.getInstance().aniadirUsuario("nombre usuario", "contrasenia", "nombre", LocalDate.now());
	}
	
	@Test /* Login correcto de un usuario registrado */
	void testLog1() {
		try {
			aplicacion.login("nombre usuario", "contrasenia");
		}
		catch(ExcepcionLoginBloqueado e) {
			fail("Lanzada excepcion no esperada ExcepcionLoginBloqueado");
		}
		catch(ExcepcionLoginErrorCredenciales e) {
			fail("Lanzada excepcion no esperada ExcepcionLoginErrorCredenciales");
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
		assertTrue(aplicacion.getUsuarioLogeado()==null);
		assertTrue(aplicacion.getAdministradorLogeado());
		aplicacion.logout();
		assertTrue(aplicacion.getUsuarioLogeado()==null);
		assertFalse(aplicacion.getAdministradorLogeado());
	}
	
	@Test /* Login fallido. Nombre o contrasenia incorrectos */
	void testLog3() {
		try {
			aplicacion.login("nombre invalido","contrasenia");
			fail("Se esperaba excepcion ExcepcionLoginErrorCredenciales");
		}
		catch(ExcepcionLoginBloqueado e) {
			fail("Lanzada excepcion no esperada ExcepcionLoginBloqueado");
		}
		catch(ExcepcionLoginErrorCredenciales e) {
			
		}
		assertTrue(aplicacion.getUsuarioLogeado()==null);
		assertFalse(aplicacion.getAdministradorLogeado());
		aplicacion.logout();
		assertTrue(aplicacion.getUsuarioLogeado()==null);
		assertFalse(aplicacion.getAdministradorLogeado());
	}
	
	@Test /* Login fallido. Usuario bloqueado */
	void testLog4() {
		UsuarioRegistrado u = null;
		
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
			
		}
		catch(ExcepcionLoginErrorCredenciales e) {
			fail("Lanzada excepcion no esperada ExcepcionLoginErrorCredenciales");
		}
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
		assertTrue(aplicacion.getUsuarioLogeado()!=null);
		assertFalse(aplicacion.getAdministradorLogeado());
		aplicacion.logout();
		assertTrue(aplicacion.getUsuarioLogeado()==null);
		assertFalse(aplicacion.getAdministradorLogeado());
	}
	
	@Test 
	void testBuscarCancionesPorTitulo() {
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
			aplicacion.aniadirCancion(nombresQueDebeEncontrar.get(i),"Ruta");
		}
		for (int i = 0; i < numDebeObviar; i++) {
			aplicacion.aniadirCancion(nombresQueDebeObviar.get(i),"Ruta");
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
	void testBuscarAlbumesPorTitulo() {
		int numDebeEncontrar = 10; /* Numero de albumes que la busqueda debe encontrar */
		int numDebeObviar = 5; /* Numero de albumes que la busqueda debe obviar */
		ArrayList<String> nombresQueDebeEncontrar = new ArrayList<>();
		ArrayList<String> nombresQueDebeObviar = new ArrayList<>();
		ArrayList<Buscable> encontrados = new ArrayList<>();
		ArrayList<String> nombresQueHaEncontrado = new ArrayList<>();
		
		aplicacion.borrarDatos();	
		
		for (int i=0; i< numDebeEncontrar; i++) {
			nombresQueDebeEncontrar.add("debeEncontrar"+i);
		}
		for (int i=0; i< numDebeObviar; i++) {
			nombresQueDebeObviar.add("debeObviar"+i);
		}
		
		for (int i = 0; i < numDebeEncontrar; i++) {
			aplicacion.aniadirAlbum(nombresQueDebeEncontrar.get(i), LocalDate.now(), null);
		}
		for (int i = 0; i < numDebeObviar; i++) {
			aplicacion.aniadirAlbum(nombresQueDebeObviar.get(i), LocalDate.now(), null);
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
