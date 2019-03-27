package gestion;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import media.Cancion;
import usuarios.UsuarioRegistrado;

class NotificacionDenunciaTest {

	NotificacionDenuncia not;
	
	@Test
	void test() {
		not = new NotificacionDenuncia(
				new Cancion("denunciada","file",new UsuarioRegistrado("autor","a","a",LocalDate.now())));
		assertNotNull(not.mostrarNotificacion());
		not.ocultarNotificacion();
		assertNull(not.mostrarNotificacion());
	}

}
