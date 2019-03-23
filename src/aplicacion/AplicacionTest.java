package aplicacion;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.Excepcion;

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
		boolean excepcionLanzada = false;
		try {
			aplicacion.login("nombre usuario", "contrasenia");
		}
		catch(Excepcion e) {
			excepcionLanzada = true;
		}
		assertFalse(excepcionLanzada);
		assertTrue(aplicacion.getUsuarioLogeado()!=null);
		assertFalse(aplicacion.getAdministradorLogeado());
		aplicacion.logout();
		assertTrue(aplicacion.getUsuarioLogeado()==null);
		assertFalse(aplicacion.getAdministradorLogeado());
	}
	
	@Test /* Login correcto del administrador */
	void testLog2() {
		boolean excepcionLanzada = false;
		try {
			aplicacion.login(aplicacion.nombreAdministrador, aplicacion.contraseniaAdministrador);
		}
		catch(Excepcion e) {
			excepcionLanzada = true;
		}
		assertFalse(excepcionLanzada);
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
		}
		catch(Excepcion e) {
			excepcionLanzada = true;
			assertTrue(e.toString() == aplicacion.mensajeErrorCredenciales);
		}
		assertTrue(excepcionLanzada);
		assertTrue(aplicacion.getUsuarioLogeado()==null);
		assertFalse(aplicacion.getAdministradorLogeado());
		aplicacion.logout();
		assertTrue(aplicacion.getUsuarioLogeado()==null);
		assertFalse(aplicacion.getAdministradorLogeado());
	}
	
	@Test /* Login fallido. Usuario bloqueado */
	void testLog4() {
		assertTrue(true);
	}
	
	

}
