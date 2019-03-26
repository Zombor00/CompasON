package media;


import java.time.LocalDate;

import usuarios.UsuarioRegistrado;
import org.junit.jupiter.api.Test;
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
		
		assertTrue(lista1.aniadirReproducible(cancion1));
		assertFalse(lista1.aniadirReproducible(cancion1));
		
		lista2.aniadirReproducible(cancion2);
		lista1.aniadirReproducible(lista2);
		
		assertFalse(lista1.aniadirReproducible(cancion2));
	}
	
	@Test
	void testListaContenidoEnPadres() {
		Lista lista1 = new Lista("nombre lista1");
		Lista lista2 = new Lista("nombre lista2");
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
		Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario);
		
		lista1.aniadirReproducible(cancion1);
		lista1.aniadirReproducible(lista2);
		
		assertFalse(lista2.aniadirReproducible(cancion1));
		lista1.quitarReproducible(cancion1);
		assertTrue(lista2.aniadirReproducible(cancion1));
	
	}
	
	@Test
	void testQuitarContenidoEn() {
		Lista lista1 = new Lista("nombre lista1");
		Lista lista2 = new Lista("nombre lista2");
		lista1.aniadirReproducible(lista2);
		lista1.quitarReproducible(lista2);
		
		assertTrue(lista2.getContenidoEn().isEmpty());
	}
	
	@Test
	void testListaQuitarReproducible() {
		Lista lista = new Lista("nombre lista");
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
		Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario);
		
		lista.aniadirReproducible(cancion1);
		assertTrue(lista.quitarReproducible(cancion1));
		assertFalse(lista.contieneReproducible(cancion1));
		assertFalse(lista.quitarReproducible(cancion1));
		
	}

	@Test
	void testListaContieneReproducible() {
		Lista lista = new Lista("nombre lista");
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
		Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario);
		
		lista.aniadirReproducible(cancion1);
		assertTrue(lista.contieneReproducible(cancion1));
		lista.quitarReproducible(cancion1);
		assertFalse(lista.contieneReproducible(cancion1));
	}

}
