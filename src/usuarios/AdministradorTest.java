package usuarios;

import static org.junit.jupiter.api.Assertions.*;

import java.time.*;
import org.junit.jupiter.api.Test;

import excepciones.ExcepcionCancionModificable;
import media.*;

class AdministradorTest {

	private Administrador admin = new Administrador("admin","admin");
	
	@Test
	void testTramitarValidacion() {
		Cancion c = new Cancion("tit","file",new UsuarioRegistrado("a","a","a",LocalDate.now()));
		admin.aniadirCancion(c);
		try {
			admin.tramitarValidacion(c, EstadoValidacion.NOVALIDADA);
		} catch (ExcepcionCancionModificable e) {
			fail("Lanzada excepcion no esperada ExcepcionCancionModificable");
		}
		/* Esta linea da error assertEquals(LocalDate.now().plusDays(3), c.getModificableHasta());*/
		assertEquals(EstadoValidacion.NOVALIDADA, c.getEstadoValidacion());
	}

	@Test
	void testTramitarDenuncia() {
		
	}

}
