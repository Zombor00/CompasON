package media;

import usuarios.UsuarioRegistrado;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlbumTest {
	
	@Test
	void testAlbumAniadirCancion() {
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
	    Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario);
	    Album album1 = new Album("nombre album",LocalDate.now());
	    
	    assertTrue(album1.aniadirCancion(cancion1));
	    assertFalse(album1.aniadirCancion(cancion1));
	    
	}
	
	@Test
	void testAlbumContieneReproducible() {
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
	    Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario);
	    Cancion cancion2 = new Cancion("cancion2","ruta cancion2",usuario);
	    Album album1 = new Album("nombre album",LocalDate.now());
	    
	    album1.aniadirCancion(cancion1);
	    assertTrue(album1.contieneReproducible(cancion1));
	    assertFalse(album1.contieneReproducible(cancion2));
	}
	
	@Test
	void testAlbumQuitarCancion() {
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
	    Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario);
	    Cancion cancion2 = new Cancion("cancion2","ruta cancion2",usuario);
	    Album album1 = new Album("nombre album",LocalDate.now());
	    
	    album1.aniadirCancion(cancion1);
	    album1.aniadirCancion(cancion2);
	    
	    assertTrue(album1.quitarCancion(cancion2));
	    assertFalse(album1.contieneReproducible(cancion2));
	    assertFalse(album1.quitarCancion(cancion2));
	    album1.quitarCancion(cancion1);
	    assertSame(album1.getEstado(),Estado.BORRADO);
	    
	}
	
	@Test
	void testAlbumContBloqueadas() {
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
	    Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario);
	    Cancion cancion2 = new Cancion("cancion2","ruta cancion2",usuario);
	    Album album1 = new Album("nombre album",LocalDate.now());
	    album1.aniadirCancion(cancion1);
	    album1.aniadirCancion(cancion2);
	    
	    assertSame(album1.contBloqueadas(),0);
	    cancion1.setEstado(Estado.BLOQUEADO);
	    assertSame(album1.contBloqueadas(),1);
	}
	
	@Test
	void testAlbumDesbloquear() {
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
	    Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario);
	    Cancion cancion2 = new Cancion("cancion2","ruta cancion2",usuario);
	    Album album1 = new Album("nombre album",LocalDate.now());
	    album1.aniadirCancion(cancion1);
	    album1.aniadirCancion(cancion2);
	    
	    cancion1.setEstado(Estado.BLOQUEADO);
	    cancion2.setEstado(Estado.BLOQUEADO);
	    album1.setEstado(Estado.BLOQUEADO);
	    
	    album1.desbloquear(cancion1);
	    assertSame(cancion1.getEstado(),Estado.NOBLOQUEADO);
	    album1.desbloquear(cancion2);
	    assertSame(album1.getEstado(),Estado.NOBLOQUEADO);
	   
	}
	

}
