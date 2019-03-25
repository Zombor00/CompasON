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
	void testContieneReproducible() {
		Lista lista = new Lista("nombre lista");
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
		Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario);
		Cancion cancion2 = new Cancion("cancion2","ruta cancion2",usuario);
		lista.aniadirReproducible(cancion1);
		assertTrue(lista.contieneReproducible(cancion1));
		lista.quitarReproducible(cancion1);
		assertFalse(lista.contieneReproducible(cancion1));
	}

}
