package media;


import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import usuarios.UsuarioRegistrado;
import org.junit.jupiter.api.Test;

import excepciones.ExcepcionCancionNoContenida;
import excepciones.ExcepcionCancionNoValidada;
import excepciones.ExcepcionInsercionInvalida;
import excepciones.ExcepcionMp3NoValido;
import excepciones.ExcepcionReproducibleNoValido;

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
		Cancion cancion1 = null;
		Cancion cancion2 = null;
		boolean lanzadaExcepcion = false;
		
		try {
			cancion1 = new Cancion("cancion1","canciones/Thats What I Like.mp3",usuario);
			cancion2 = new Cancion("cancion2","canciones/Thats What I Like.mp3",usuario);
		} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}

		cancion1.validar(EstadoValidacion.EXPLICITO);
		cancion2.validar(EstadoValidacion.EXPLICITO);
		
		try {
			lista1.aniadirReproducible(cancion1);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionReproducibleNoValido e) {
			fail("Lanzada excepcion no esperada");
		}
		
		try {
			lista1.aniadirReproducible(cancion1);
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionInsercionInvalida e) {
			lanzadaExcepcion = true;
		} catch (ExcepcionReproducibleNoValido e) {
			fail("Lanzada excepcion no esperada");
		}
		
		assertTrue(lanzadaExcepcion);
		lanzadaExcepcion = false;
		
		try {
			lista2.aniadirReproducible(cancion2);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionReproducibleNoValido e) {
			fail("Lanzada excepcion no esperada");
		}
		
		try {
			lista1.aniadirReproducible(lista2);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionReproducibleNoValido e) {
			fail("Lanzada excepcion no esperada");
		}
		
		try {
			lista1.aniadirReproducible(cancion1);
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionInsercionInvalida e) {
			lanzadaExcepcion = true;
		} catch (ExcepcionReproducibleNoValido e) {
			fail("Lanzada excepcion no esperada");
		}
		assertTrue(lanzadaExcepcion);
	}
	
	@Test
	void testListaContenidoEnPadres() {
		Lista lista1 = null;
		Lista lista2 = new Lista("nombre lista2");
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
		Cancion cancion1 = null;
		boolean lanzadaExcepcion = false;
		try {
			cancion1 = new Cancion("cancion1","canciones/Thats What I Like.mp3",usuario);
		} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
		cancion1.validar(EstadoValidacion.EXPLICITO);
		ArrayList<Reproducible> reproducibles = new ArrayList<>();
		reproducibles.add(cancion1);
		reproducibles.add(lista2);
		try {
			lista1 =  new Lista("nombre lista1",reproducibles);
		} catch (ExcepcionInsercionInvalida e2) {
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionReproducibleNoValido e) {
			fail("Lanzada excepcion no esperada");
		}
		
		try {
			lista2.aniadirReproducible(cancion1);
			fail("Esperada excepcion no lanzada");
		} catch (ExcepcionInsercionInvalida e2) {
			lanzadaExcepcion = true;
		} catch (ExcepcionReproducibleNoValido e) {
			fail("Lanzada excepcion no esperada");
		}
		
		assertTrue(lanzadaExcepcion);
	
		try {
			lista1.quitarReproducible(cancion1);
		} catch (ExcepcionCancionNoContenida e1) {
			fail("Lanzada excepcion no esperada");
		}
		try {
			lista2.aniadirReproducible(cancion1);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionReproducibleNoValido e) {
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
		} catch (ExcepcionReproducibleNoValido e) {
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
		Cancion cancion1 = null;
		boolean lanzadaExcepcion = false;
		try {
			cancion1 = new Cancion("cancion1","canciones/Thats What I Like.mp3",usuario);
		} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
		cancion1.validar(EstadoValidacion.EXPLICITO);
		
		try {
			lista.aniadirReproducible(cancion1);
		} catch (ExcepcionInsercionInvalida e1) {
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionReproducibleNoValido e) {
			fail("Lanzada excepcion no esperada");
		}
		
		try {
			lista.quitarReproducible(cancion1);
		} catch (ExcepcionCancionNoContenida e) {
			fail("Lanzada excepcion no esperada");
		}
		assertFalse(lista.contieneReproducible(cancion1));
		
		try {
			lista.quitarReproducible(cancion1);
			fail("Espera excepcion no lanzada");
		} catch (ExcepcionCancionNoContenida e) {
			lanzadaExcepcion = true;
		}
		assertTrue(lanzadaExcepcion);
	}

	@Test
	void testListaContieneReproducible() {
		Lista lista = new Lista("nombre lista");
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
		Cancion cancion1 = null;
		try {
			cancion1 = new Cancion("cancion1","canciones/Thats What I Like.mp3",usuario);
		} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
		cancion1.validar(EstadoValidacion.EXPLICITO);
		
		try {
			lista.aniadirReproducible(cancion1);
		} catch (ExcepcionInsercionInvalida e1) {
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionReproducibleNoValido e) {
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
		Cancion cancion1 = null;
		Cancion cancion2 = null;
		try {
			cancion1 = new Cancion("cancion1","canciones/Thats What I Like.mp3",usuario);
			cancion2 = new Cancion("cancion2","canciones/Thats What I Like.mp3",usuario);
		} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
		cancion1.validar(EstadoValidacion.EXPLICITO);
	    Album album1 = new Album("nombre album", usuario);
		
		try {
			lista.aniadirReproducible(cancion1);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionReproducibleNoValido e) {
			fail("Lanzada excepcion no esperada");
		}
		assertFalse(lista.esAptoParaMenores());
		cancion1.validar(EstadoValidacion.APTOMENORES);
		assertTrue(lista.esAptoParaMenores());
		
		cancion2.validar(EstadoValidacion.EXPLICITO);
	    
	    try {
			album1.aniadirCancion(cancion2);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionCancionNoValidada e) {
			fail("Lanzada excepcion no esperada");
		}
	    
	    try {
			lista.aniadirReproducible(album1);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionReproducibleNoValido e) {
			fail("Lanzada excepcion no esperada");
		}
	    
	    assertFalse(lista.esAptoParaMenores());
	
	}

}
