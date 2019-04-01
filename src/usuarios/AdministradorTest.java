package usuarios;

import static org.junit.jupiter.api.Assertions.*;

import java.time.*;
import org.junit.jupiter.api.Test;

import excepciones.ExcepcionCancionModificable;
import excepciones.ExcepcionInsercionInvalida;
import excepciones.ExcepcionCancionYaValidada;
import gestion.Denuncia;
import media.*;

public class AdministradorTest {

	private Administrador admin = new Administrador("admin","admin");
	
	@Test /*El administrador no valida una cancion correctamente*/
	void testTramitarValidacion1() {
		Cancion c = new Cancion("tit","file",new UsuarioRegistrado("a","a","a",LocalDate.now()));
		admin.aniadirCancion(c);
		try {
			admin.tramitarValidacion(c, EstadoValidacion.NOVALIDADA);
		} catch (ExcepcionCancionModificable e) {
			fail("Lanzada excepcion no esperada ExcepcionCancionModificable");
		} catch (ExcepcionCancionYaValidada e) {
			fail("Lanzada excepcion no esperada ExcepcionCancionYaValidada");
		}
		assertEquals(LocalDate.now().plusDays(3), c.getModificableHasta());
		assertEquals(EstadoValidacion.NOVALIDADA, c.getEstadoValidacion());
	}
	
	@Test /*El administrador trata de validar una cancion que aun se puede modificar*/
	void testTramitarValidacion2() {
		Cancion c = new Cancion("tit","file",new UsuarioRegistrado("a","a","a",LocalDate.now()));
		admin.aniadirCancion(c);
		try {
			admin.tramitarValidacion(c, EstadoValidacion.NOVALIDADA);
		} catch (ExcepcionCancionModificable e) {
			fail("Lanzada excepcion no esperada ExcepcionCancionModificable");
		} catch (ExcepcionCancionYaValidada e) {
			fail("Lanzada excepcion no esperada ExcepcionCancionYaValidada");
		}
		assertThrows(ExcepcionCancionModificable.class, () -> {
	        admin.tramitarValidacion(c, EstadoValidacion.NOVALIDADA);
	    });
	}
	
	@Test /*El administrador trata de validar una cancion que ya esta validada*/
	void testTramitarValidacion3() {
		Cancion c = new Cancion("tit","file",new UsuarioRegistrado("a","a","a",LocalDate.now()));
		admin.aniadirCancion(c);
		try {
			admin.tramitarValidacion(c, EstadoValidacion.APTOMENORES);
		} catch (ExcepcionCancionModificable e) {
			fail("Lanzada excepcion no esperada ExcepcionCancionModificable");
		} catch (ExcepcionCancionYaValidada e) {
			fail("Lanzada excepcion no esperada ExcepcionCancionYaValidada");
		}
		assertThrows(ExcepcionCancionYaValidada.class, () -> {
	        admin.tramitarValidacion(c, EstadoValidacion.EXPLICITO);
	    });
	}
	

	@Test /*Un usuario denuncia una cancion y no tiene razon*/
	void testTramitarDenuncia1() {
		UsuarioRegistrado denunciante = new UsuarioRegistrado("a","a","a",LocalDate.now());
		UsuarioRegistrado autor = new UsuarioRegistrado("b","b","b",LocalDate.now());
		Cancion denunciada = new Cancion("tit","file",autor);
		Album a = new Album("tit",autor);
		/*Aniadimos la cancion tras validarla a un album para probar todas las funcionalidades*/
		try {
			denunciada.validar(EstadoValidacion.APTOMENORES);
			a.aniadirCancion(denunciada);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		}
		Denuncia d = new Denuncia(denunciante, denunciada, "Es plagio");
		autor.aniadirBuscable(denunciada);
		autor.aniadirBuscable(a);
		/*Simulamos lo que sucede al denunciar, se bloquean tanto el album como la cancion*/
		a.setEstado(Estado.BLOQUEADO);
		denunciada.setEstado(Estado.BLOQUEADO);
		
		admin.aniadirDenuncia(d);
		admin.tramitarDenuncia(d, false);
		assertEquals(LocalDate.now().plusDays(30),denunciante.getBloqueadoHasta());
		assertEquals(Estado.NOBLOQUEADO,denunciada.getEstado());
		assertEquals(Estado.NOBLOQUEADO,a.getEstado());
	}
	
	@Test /*Un usuario denuncia una cancion y tiene razon*/
	void testTramitarDenuncia2() {
		UsuarioRegistrado denunciante = new UsuarioRegistrado("a","a","a",LocalDate.now());
		UsuarioRegistrado autor = new UsuarioRegistrado("b","b","b",LocalDate.now());
		Cancion denunciada = new Cancion("tit","file",autor);
		Album a = new Album("tit",autor);
		/*Aniadimos la cancion a un album para probar todas las funcionalidades*/
		try {
			a.aniadirCancion(denunciada);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		}
		Denuncia d = new Denuncia(denunciante, denunciada, "Es plagio");
		autor.aniadirBuscable(denunciada);
		autor.aniadirBuscable(a);
		/*Simulamos lo que sucede al denunciar, se bloquean tanto el album como la cancion*/
		a.setEstado(Estado.BLOQUEADO);
		denunciada.setEstado(Estado.BLOQUEADO);
		
		admin.aniadirDenuncia(d);
		admin.tramitarDenuncia(d, true);
		assertEquals(LocalDate.MAX,autor.getBloqueadoHasta());
		assertEquals(Estado.BLOQUEADO,denunciada.getEstado());
		assertEquals(Estado.BLOQUEADO,a.getEstado());
	}

}
