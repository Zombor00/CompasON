package media;

import usuarios.UsuarioRegistrado;
import java.time.LocalDate;

public class AlbumTest {
    UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
    Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario);
    Cancion cancion2 = new Cancion("cancion2","ruta cancion2",usuario);
    Album album1 = new Album("nombre album",LocalDate.now());
    album1.aniadirCancion(cancion1);
    album1.aniadirCancion(cancion2);

    assertTrue(album1.contieneReproducible(cancion1));
    assertFalse(album1.aniadirCancion(cancion1));

    assertTrue(album1.quitarCancion(cancion2));
    assertFalse(album1.contieneReproducible(cancion2));
    assertFalse(album1.quitarCancion(cancion2));
    album1.quitarCancion(cancion1);
    assertSame(album1.getEstado(),Estado.BORRADO);

}
