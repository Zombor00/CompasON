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
		not = new NotificacionDenuncia(new Denuncia(new UsuarioRegistrado("denunciante","a","a",LocalDate.now()),
				new Cancion("denunciada","file",new UsuarioRegistrado("autor","a","a",LocalDate.now())),"Es plagio"));
		assertNotNull(not.mostrarNotificacion());
		not.ocultarNotificacion();
		assertNull(not.mostrarNotificacion());
	}

}
