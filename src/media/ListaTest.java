package media;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import usuarios.UsuarioRegistrado;

/**
 * Implementamos un tester para la clase Lista.
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class ListaTest {

	@Test
	void testContieneReproducible() {
		Lista lista = new Lista("nombre lista");
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
		Cancion cancion = new Cancion("nombre cancion","ruta cancion",usuario);
		lista.aniadirReproducible(cancion);
		assertTrue(lista.contieneReproducible(cancion));
		lista.quitarReproducible(cancion);
		assertFalse(lista.contieneReproducible(cancion));
	}

}
