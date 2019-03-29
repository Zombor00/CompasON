package media;


import java.time.LocalDate;
import java.util.ArrayList;
import usuarios.UsuarioRegistrado;
import org.junit.jupiter.api.Test;

import excepciones.ExcepcionCancionNoContenida;
import excepciones.ExcepcionCancionYaContenida;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Implementamos un tester para la clase Lista.
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class ListaTest {
	
	@Test
	void testListaAniadirReproducible() {
		Lista lista1 = new Lista("nombre lista1");
		Lista lista2 = new Lista("nombre lista2");
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
		Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario);
		Cancion cancion2 = new Cancion("cancion2","ruta cancion2",usuario);
		
		try {
			assertTrue(lista1.aniadirReproducible(cancion1));
		} catch (ExcepcionCancionYaContenida e) {
			fail("Lanzada excepcion no esperada");
		}
		assertThrows(ExcepcionCancionYaContenida.class, () -> {
			lista1.aniadirReproducible(cancion1);
	    });
		
		try {
			lista2.aniadirReproducible(cancion2);
		} catch (ExcepcionCancionYaContenida e) {
			fail("Lanzada excepcion no esperada");
		}
		try {
			lista1.aniadirReproducible(lista2);
		} catch (ExcepcionCancionYaContenida e) {
			fail("Lanzada excepcion no esperada");
		}
		
		assertThrows(ExcepcionCancionYaContenida.class, () -> {
			lista1.aniadirReproducible(cancion2);
	    });
	}
	
	@Test
	void testListaContenidoEnPadres() {
		Lista lista1;
		Lista lista2 = new Lista("nombre lista2");
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
		Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario);
		ArrayList<Reproducible> reproducibles = new ArrayList<>();
		reproducibles.add(cancion1);
		reproducibles.add(lista2);
		lista1 =  new Lista("nombre lista1",reproducibles);
		
		assertThrows(ExcepcionCancionYaContenida.class, () -> {
			lista2.aniadirReproducible(cancion1);
	    });
	
		try {
			lista1.quitarReproducible(cancion1);
		} catch (ExcepcionCancionNoContenida e1) {
			fail("Lanzada excepcion no esperada");
		}
		try {
			assertTrue(lista2.aniadirReproducible(cancion1));
		} catch (ExcepcionCancionYaContenida e) {
			fail("Lanzada excepcion no esperada");
		}
	
	}
	
	@Test
	void testQuitarContenidoEn() {
		Lista lista1 = new Lista("nombre lista1");
		Lista lista2 = new Lista("nombre lista2");
		try {
			lista1.aniadirReproducible(lista2);
		} catch (ExcepcionCancionYaContenida e) {
			fail("Lanzada excepcion no esperada");
		}
		try {
			lista1.quitarReproducible(lista2);
		} catch (ExcepcionCancionNoContenida e) {
			fail("Lanzada excepcion no esperada");
		}
		
		assertTrue(lista2.getContenidoEn().isEmpty());
	}
	
	@Test
	void testListaQuitarReproducible() {
		Lista lista = new Lista("nombre lista");
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
		Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario);
		
		try {
			lista.aniadirReproducible(cancion1);
		} catch (ExcepcionCancionYaContenida e1) {
			fail("Lanzada excepcion no esperada");
		}
		try {
			assertTrue(lista.quitarReproducible(cancion1));
		} catch (ExcepcionCancionNoContenida e) {
			fail("Lanzada excepcion no esperada");
		}
		assertFalse(lista.contieneReproducible(cancion1));
		assertThrows(ExcepcionCancionNoContenida.class, () -> {
			lista.quitarReproducible(cancion1);
	    });
		
	}

	@Test
	void testListaContieneReproducible() {
		Lista lista = new Lista("nombre lista");
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
		Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario);
		
		try {
			lista.aniadirReproducible(cancion1);
		} catch (ExcepcionCancionYaContenida e1) {
			fail("Lanzada excepcion no esperada");
		}
		assertTrue(lista.contieneReproducible(cancion1));
		try {
			lista.quitarReproducible(cancion1);
		} catch (ExcepcionCancionNoContenida e) {
			fail("Lanzada excepcion no esperada");		}
		assertFalse(lista.contieneReproducible(cancion1));
	}
	
	@Test
	void testListAptoMenores() {
		Lista lista = new Lista("nombre lista");
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
		Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario);
		
		try {
			lista.aniadirReproducible(cancion1);
		} catch (ExcepcionCancionYaContenida e) {
			fail("Lanzada excepcion no esperada");
		}
		assertFalse(lista.esAptoParaMenores());
		cancion1.validar(EstadoValidacion.APTOMENORES);
		assertTrue(lista.esAptoParaMenores());
	
	}

}
