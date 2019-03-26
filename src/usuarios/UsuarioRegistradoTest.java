package usuarios;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.*;

import media.*;
import excepciones.ExcepcionUsuarioNoPremium;
import excepciones.ExcepcionUsuarioNoSeguido;
import excepciones.ExcepcionUsuarioYaSeguido;

public class UsuarioRegistradoTest {
	
	UsuarioRegistrado u1 = new UsuarioRegistrado("a","a","a",LocalDate.now());
	
	
	@Test /* Un usuario sigue a otro correctamente. */
	void testSeguirUsuario1() {
		UsuarioRegistrado u2 = new UsuarioRegistrado("b","b","b",LocalDate.now());
		try {
			u1.seguirUsuario(u2);
		} catch (ExcepcionUsuarioYaSeguido e) {
			fail("Lanzada excepcion no esperada ExcepcionUsuarioYaSeguido");
		}
		assertTrue(u1.getSeguidos().contains(u2));
		assertTrue(u2.getSeguidores().contains(u1));
	}
	
	@Test /* Un usuario no puede seguir a uno ya seguido */
	void testSeguirUsuario2() {
		UsuarioRegistrado u2 = new UsuarioRegistrado("b","b","b",LocalDate.now());
		try {
			u1.seguirUsuario(u2);
		} catch (ExcepcionUsuarioYaSeguido e) {
			fail("Lanzada excepcion no esperada ExcepcionUsuarioYaSeguido");
		}
		assertThrows(ExcepcionUsuarioYaSeguido.class, () -> {
	        u1.seguirUsuario(u2);
	    });
	}

	@Test /*Un usuario deja de seguir a otro correctamente*/
	void testDejarSeguirUsuario1() {
		UsuarioRegistrado u2 = new UsuarioRegistrado("b","b","b",LocalDate.now());
		try {
			u1.seguirUsuario(u2);
		} catch (ExcepcionUsuarioYaSeguido e) {
			fail("Lanzada excepcion no esperada ExcepcionUsuarioYaSeguido");
		}
		try {
			u1.dejarSeguirUsuario(u2);
		} catch (ExcepcionUsuarioNoSeguido e) {
			fail("Lanzada excepcion no esperada ExcepcionUsuarioNoSeguido");
		}
		assertFalse(u1.getSeguidos().contains(u2));
		assertFalse(u2.getSeguidores().contains(u1));
	}
	
	@Test /*Un usuario intenta dejar de seguir a uno que no sigue*/
	void testDejarSeguirUsuario2() {
		UsuarioRegistrado u2 = new UsuarioRegistrado("b","b","b",LocalDate.now());
		assertThrows(ExcepcionUsuarioNoSeguido.class, () -> {
	        u1.dejarSeguirUsuario(u2);
	    });
	}
	
	@Test /*Un usuario Premium crea una lista*/
	void testCrearLista1() {
		u1.setPremiumHasta(LocalDate.now().plusDays(1));
		try {
			u1.crearLista("a", new ArrayList<Reproducible>());
		} catch (ExcepcionUsuarioNoPremium e) {
			fail("Lanzada excepcion no esperada ExcepcionUsuarioNoPremium");
		}
		assertEquals(1,u1.getListas().size());
	}
	
	@Test /*Un usuario no Premium intenta crear una lista*/
	void testCrearLista2() {
		assertThrows(ExcepcionUsuarioNoPremium.class, () -> {
	        u1.crearLista("a",new ArrayList<Reproducible>());
	    });
	}
	
	@Test /*Un usuario al que se le acaba de terminar el Premium intenta crear una lista*/
	void testCrearLista3() {
		u1.setPremiumHasta(LocalDate.now());
		assertThrows(ExcepcionUsuarioNoPremium.class, () -> {
	        u1.crearLista("a",new ArrayList<Reproducible>());
	    });
	}
	
	@Test /*Prueba si un usuario es menor o no*/
	void testEsMenor() {
		UsuarioRegistrado u2 = new UsuarioRegistrado("b","b","b",LocalDate.now().withYear(2001));
		assertTrue(u1.esMenor());
		assertFalse(u2.esMenor());
	}
	
	@Test /*Prueba si cuenta bien las reproducciones del ultimo mes y si vacia del ArrayList las mas antiguas*/
	void testReproduccionesUltimoMes() {
		ArrayList<LocalDate> reproducciones = new ArrayList<>();
		int numReproducciones = 7;
		int reproduccionesPosteriores = 3;
		
		reproducciones.add(LocalDate.now().minusMonths(2));
		for (int i = 0; i < numReproducciones; i++) {
			reproducciones.add(LocalDate.now().minusMonths(1));
		}
		for (int i = 0; i < reproduccionesPosteriores; i++) {
			reproducciones.add(LocalDate.now());	
		}	
		u1.setReproducciones(reproducciones);
		assertEquals(numReproducciones, u1.reproduccionesUltimoMes());
		assertEquals(reproduccionesPosteriores, u1.getReproducciones().size());		
	}
	
	

}
