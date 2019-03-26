package gestion;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import media.Cancion;
import usuarios.UsuarioRegistrado;

public class NotificacionCancionTest {

	NotificacionCancion not;
	
	@Test
	void test() {
		not = new NotificacionCancion(new Cancion("Titulo","file",new UsuarioRegistrado("a","a","a",LocalDate.now())));
		assertNotNull(not.mostrarNotificacion());
		not.ocultarNotificacion();
		assertNull(not.mostrarNotificacion());
	}

}
