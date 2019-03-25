package media;

import java.time.LocalDate;
import usuarios.UsuarioRegistrado;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import media.EstadoValidacion;
import media.Cancion;

public class CancionTest {
    UsuarioRegistrado usuario1 = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
    Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario1);
    Cancion cancion2 = new Cancion("cancion2","ruta cancion2",usuario1);

    cancion1.validar(EstadoValidacion.NOVALIDADA);
    assertSame(cancion1.getEstadoValidacion(),EstadoValidacion.NOVALIDADA);
    assertTrue(cancion1.modificar("Wait for it",null));
    cancion1.validar(EstadoValidacion.APTOMENORES);
    assertSame(cancion1.getEstadoValidacion(),EstadoValidacion.APTOMENORES);
    assertTrue(cancion1.contieneReproducible(cancion1));
    cancion1.setEstado(Estado.BLOQUEADO);
    cancion1.desbloquear();
    assertSame(cancion1.getEstado(),Estado.NOBLOQUEADO);
    assertFalse(cancion1.modificar("My Shot",null));

    cancion2.validar(EstadoValidacion.NOVALIDADA);
    assertFalse(cancion2.modificar("Thats What I Like","DOENSTEXIST.mp3"));
    assertTrue(cancion2.modificar("Thats What I Like","Thats What I Like.mp3"));
    assertSame("Thats What I Like.mp3",cancion2.getFicheroAudio);


}
