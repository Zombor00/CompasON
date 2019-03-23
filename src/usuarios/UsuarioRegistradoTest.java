package usuarios;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.*;

class UsuarioRegistradoTest {
	
	UsuarioRegistrado u1 = new UsuarioRegistrado("a","a","a",LocalDate.now());
	
	
	@Test
	void testSeguirUsuario() {
		UsuarioRegistrado u2 = new UsuarioRegistrado("b","b","b",LocalDate.now());
		u1.seguirUsuario(u2);
		assertTrue(u1.getSeguidos().contains(u2));
		assertTrue(u2.getSeguidores().contains(u1));
	}

	@Test
	void testDejarSeguirUsuario() {
		UsuarioRegistrado u2 = new UsuarioRegistrado("b","b","b",LocalDate.now());
		u1.seguirUsuario(u2);
		u1.dejarSeguirUsuario(u2);
		assertFalse(u1.getSeguidos().contains(u2));
		assertFalse(u2.getSeguidores().contains(u1));
	}

}
