package usuarios;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.*;

import excepciones.ExcepcionUsuarioNoSeguido;
import excepciones.ExcepcionUsuarioYaSeguido;

class UsuarioRegistradoTest {
	
	UsuarioRegistrado u1 = new UsuarioRegistrado("a","a","a",LocalDate.now());
	
	
	@Test
	void testSeguirUsuario() {
		UsuarioRegistrado u2 = new UsuarioRegistrado("b","b","b",LocalDate.now());
		try {
			u1.seguirUsuario(u2);
		} catch (ExcepcionUsuarioYaSeguido e) {
			fail("Lanzada excepcion no esperada ExcepcionUsuarioYaSeguido");
		}
		assertTrue(u1.getSeguidos().contains(u2));
		assertTrue(u2.getSeguidores().contains(u1));
	}

	@Test
	void testDejarSeguirUsuario() {
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

}
