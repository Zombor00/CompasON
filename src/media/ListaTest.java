package media;


import java.time.LocalDate;
import java.util.ArrayList;
import usuarios.UsuarioRegistrado;
import org.junit.jupiter.api.Test;

import excepciones.ExcepcionCancionNoContenida;
import excepciones.ExcepcionCancionNoValidada;
import excepciones.ExcepcionInsercionInvalida;

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
		cancion1.validar(EstadoValidacion.EXPLICITO);
		Cancion cancion2 = new Cancion("cancion2","ruta cancion2",usuario);
		cancion1.validar(EstadoValidacion.EXPLICITO);
		
		try {
			assertTrue(lista1.aniadirReproducible(cancion1));
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		}
		assertThrows(ExcepcionInsercionInvalida.class, () -> {
			lista1.aniadirReproducible(cancion1);
	    });
		
		try {
			lista2.aniadirReproducible(cancion2);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		}
		try {
			lista1.aniadirReproducible(lista2);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		}
		
		assertThrows(ExcepcionInsercionInvalida.class, () -> {
			lista1.aniadirReproducible(cancion2);
	    });
	}
	
	@Test
	void testListaContenidoEnPadres() {
		Lista lista1 = null;
		Lista lista2 = new Lista("nombre lista2");
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
		Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario);
		cancion1.validar(EstadoValidacion.EXPLICITO);
		ArrayList<Reproducible> reproducibles = new ArrayList<>();
		reproducibles.add(cancion1);
		reproducibles.add(lista2);
		try {
			lista1 =  new Lista("nombre lista1",reproducibles);
		} catch (ExcepcionInsercionInvalida e2) {
			fail("Lanzada excepcion no esperada");
		}
		
		assertThrows(ExcepcionInsercionInvalida.class, () -> {
			lista2.aniadirReproducible(cancion1);
	    });
	
		try {
			lista1.quitarReproducible(cancion1);
		} catch (ExcepcionCancionNoContenida e1) {
			fail("Lanzada excepcion no esperada");
		}
		try {
			assertTrue(lista2.aniadirReproducible(cancion1));
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		}
	
	}
	
	@Test
	void testQuitarPadre() {
		Lista lista1 = new Lista("nombre lista1");
		Lista lista2 = new Lista("nombre lista2");
		try {
			lista1.aniadirReproducible(lista2);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		}
		try {
			lista1.quitarReproducible(lista2);
		} catch (ExcepcionCancionNoContenida e) {
			fail("Lanzada excepcion no esperada");
		}
		
		assertTrue(lista2.getPadres().isEmpty());
	}
	
	@Test
	void testListaQuitarReproducible() {
		Lista lista = new Lista("nombre lista");
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
		Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario);
		cancion1.validar(EstadoValidacion.EXPLICITO);
		
		try {
			lista.aniadirReproducible(cancion1);
		} catch (ExcepcionInsercionInvalida e1) {
			fail("Lanzada excepcion no esperada");
		}
		try {
			lista.quitarReproducible(cancion1);
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
		cancion1.validar(EstadoValidacion.EXPLICITO);
		
		try {
			lista.aniadirReproducible(cancion1);
		} catch (ExcepcionInsercionInvalida e1) {
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
		cancion1.validar(EstadoValidacion.EXPLICITO);
		Cancion cancion2 = new Cancion("cancion2","ruta cancion2",usuario);
	    Album album1 = new Album("nombre album", usuario);
		
		try {
			lista.aniadirReproducible(cancion1);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		}
		assertFalse(lista.esAptoParaMenores());
		cancion1.validar(EstadoValidacion.APTOMENORES);
		assertTrue(lista.esAptoParaMenores());
		
		cancion2.validar(EstadoValidacion.EXPLICITO);
	    
	    try {
			album1.aniadirCancion(cancion1);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionCancionNoValidada e) {
			fail("Lanzada excepcion no esperada");
		}
	    
	    try {
			lista.aniadirReproducible(album1);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		}
	    
	    assertTrue(lista.esAptoParaMenores());
	
	}

}
