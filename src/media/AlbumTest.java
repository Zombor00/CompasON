package media;

import usuarios.UsuarioRegistrado;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import excepciones.ExcepcionCancionModificable;
import excepciones.ExcepcionCancionNoContenida;
import excepciones.ExcepcionCancionNoValidada;
import excepciones.ExcepcionCancionYaValidada;
import excepciones.ExcepcionInsercionInvalida;
import excepciones.ExcepcionMp3NoValido;

import static org.junit.jupiter.api.Assertions.*;

public class AlbumTest {
	
	@Test
	void testAlbumAniadirCancion() {
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
		boolean lanzadaExcepcion = false;
		Cancion cancion1 = null;
		try {
			cancion1 = new Cancion("cancion1","canciones/Thats What I Like.mp3",usuario);
		} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
		
	    Album album1 = new Album("nombre album",usuario);
	    
	    try {
	    	album1.aniadirCancion(cancion1);
	    }catch(ExcepcionCancionNoValidada e) {
	    	fail("Lanzada excepcion no esperada");
	    } catch (ExcepcionInsercionInvalida e) {
	    	fail("Lanzada excepcion no esperada");
		}
	    
	    try {
			cancion1.validar(EstadoValidacion.EXPLICITO);
		} catch (ExcepcionCancionYaValidada | ExcepcionCancionModificable e1) {
			fail("Lanzada excepcion no esperada");
		}
	    
	    try {
			album1.aniadirCancion(cancion1);
		} catch (ExcepcionInsercionInvalida e) {
			lanzadaExcepcion = true;
		} catch (ExcepcionCancionNoValidada e) {
			fail("Lanzada excepcion no esperada");
		}
	    assertTrue(lanzadaExcepcion);
	    lanzadaExcepcion = false;
	    
	    try {
			album1.aniadirCancion(cancion1);
			fail("Esperada excepcion ExcepcionInsercionInvalida");
		} catch (ExcepcionInsercionInvalida e) {
			lanzadaExcepcion = true;
		} catch (ExcepcionCancionNoValidada e) {
			fail("Lanzada excepcion no esperada");
		}
	    assertTrue(lanzadaExcepcion);
	    
	    album1.toString();
	    
	}
	
	@Test
	void testAlbumContieneReproducible() {
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
		
		Cancion cancion1 = null;
	    Cancion cancion2 = null;
	    Album album1 = new Album("nombre album",usuario);
		try {
			cancion1 = new Cancion("cancion1","canciones/Thats What I Like.mp3",usuario);
			cancion2 = new Cancion("cancion2","canciones/Thats What I Like.mp3",usuario);
		} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}

		try {
			cancion1.validar(EstadoValidacion.EXPLICITO);
		} catch (ExcepcionCancionYaValidada | ExcepcionCancionModificable e1) {
			fail("Lanzada excepcion no esperada");
		}
		
	    try {
			album1.aniadirCancion(cancion1);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionCancionNoValidada e) {
			fail("Lanzada excepcion no esperada");
		}
	    assertTrue(album1.contieneReproducible(cancion1));
	    assertFalse(album1.contieneReproducible(cancion2));
	}
	
	@Test
	void testAlbumQuitarCancion() {
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
		boolean excepcionLanzada = false;
		
		Cancion cancion1 = null;
	    Cancion cancion2 = null;
	    Album album1 = null;
		try {
			cancion1 = new Cancion("cancion1","canciones/Thats What I Like.mp3",usuario);
			cancion2 = new Cancion("cancion2","canciones/Thats What I Like.mp3",usuario);
		} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
		try {
			cancion1.validar(EstadoValidacion.EXPLICITO);
			cancion2.validar(EstadoValidacion.EXPLICITO);
		} catch (ExcepcionCancionYaValidada | ExcepcionCancionModificable e1) {
			fail("Lanzada excepcion no esperada");
		}
	    ArrayList<Cancion> canciones = new ArrayList<>();
	    canciones.add(cancion1);
	    canciones.add(cancion2);
		try {
			album1 = new Album("nombre album",usuario,canciones);
		} catch (ExcepcionInsercionInvalida e1) {
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionCancionNoValidada e2) {
			fail("Lanzada excepcion no esperada");
		}
	    
	    try {
	    	album1.quitarCancion(cancion2);
		} catch (ExcepcionCancionNoContenida e3) {
			fail("Lanzada excepcion no esperada");
		}
	    assertFalse(album1.contieneReproducible(cancion2));
	    
	    try {
			album1.quitarCancion(cancion2);
			fail("Se esperaba excepcion ExcepcionCancionNoContenida");
		} catch (ExcepcionCancionNoContenida e1) {
			excepcionLanzada = true;
		}
	    assertTrue(excepcionLanzada);
	    try {
			album1.quitarCancion(cancion1);
		} catch (ExcepcionCancionNoContenida e4) {
			fail("Lanzada excepcion no esperada");
		}
	    assertSame(album1.getEstado(),Estado.BORRADO);
	    
	}
	
	@Test
	void testAlbumContBloqueadas() {
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
	    Cancion cancion1 = null;
	    Cancion cancion2 = null;
		try {
			cancion1 = new Cancion("cancion1","canciones/Thats What I Like.mp3",usuario);
			cancion2 = new Cancion("cancion2","canciones/Thats What I Like.mp3",usuario);
		} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}

		try {
			cancion1.validar(EstadoValidacion.EXPLICITO);
			cancion2.validar(EstadoValidacion.EXPLICITO);
		} catch (ExcepcionCancionYaValidada | ExcepcionCancionModificable e1) {
			fail("Lanzada excepcion no esperada");
		}
		
	    Album album1 = new Album("nombre album",usuario);
	    try {
			album1.aniadirCancion(cancion1);
			album1.aniadirCancion(cancion2);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionCancionNoValidada e) {
			fail("Lanzada excepcion no esperada");
		}
	    
	    assertSame(album1.contBloqueadas(),0);
	    cancion1.setEstado(Estado.BLOQUEADO);
	    assertSame(album1.contBloqueadas(),1);
	}
	
	@Test
	void testAlbumDesbloquear() {
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
	    Cancion cancion1 = null;
	    Cancion cancion2 = null;
	    Album album1 = new Album("nombre album",usuario);
	    try {
			cancion1 = new Cancion("cancion1","canciones/Thats What I Like.mp3",usuario);
			cancion2 = new Cancion("cancion2","canciones/Thats What I Like.mp3",usuario);
		} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
	    
	    try {
			cancion1.validar(EstadoValidacion.EXPLICITO);
			cancion2.validar(EstadoValidacion.EXPLICITO);
		} catch (ExcepcionCancionYaValidada | ExcepcionCancionModificable e1) {
			fail("Lanzada excepcion no esperada");
		}
	    
	    try {
			album1.aniadirCancion(cancion1);
			album1.aniadirCancion(cancion2);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionCancionNoValidada e) {
			fail("Lanzada excepcion no esperada");
		}
	    
	    cancion1.setEstado(Estado.BLOQUEADO);
	    cancion2.setEstado(Estado.BLOQUEADO);
	    album1.setEstado(Estado.BLOQUEADO);
	    
	    album1.desbloquear(cancion1);
	    assertSame(cancion1.getEstado(),Estado.NOBLOQUEADO);
	    album1.desbloquear(cancion2);
	    assertSame(album1.getEstado(),Estado.NOBLOQUEADO);
	   
	}
	
	@Test
	void testAlbumAptoParaMenores() {
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
	    Cancion cancion1 = null;
	    try {
			cancion1 = new Cancion("cancion1","canciones/Thats What I Like.mp3",usuario);

		} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
	    try {
			cancion1.validar(EstadoValidacion.APTOMENORES);
		} catch (ExcepcionCancionYaValidada | ExcepcionCancionModificable e1) {
			fail("Lanzada excepcion no esperada");
		}
	    Album album1 = new Album("nombre album",usuario);
	    
	    try {
			album1.aniadirCancion(cancion1);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionCancionNoValidada e) {
			fail("Lanzada excepcion no esperada");
		}
	    assertTrue(album1.esAptoParaMenores());
	}
	
	@Test
	void testAlbumGetAutor() {
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
	    Cancion cancion1 = null;
	    try {
			cancion1 = new Cancion("cancion1","canciones/Thats What I Like.mp3",usuario);
		} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
	    try {
			cancion1.validar(EstadoValidacion.EXPLICITO);
		} catch (ExcepcionCancionYaValidada | ExcepcionCancionModificable e1) {
			fail("Lanzada excepcion no esperada");
		}
	    Album album1 = new Album("nombre album",usuario);
	    
	    try {
			album1.aniadirCancion(cancion1);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionCancionNoValidada e) {
			fail("Lanzada excepcion no esperada");
		}
	    assertSame(album1.getAutor(),usuario);
	}
	
	@Test
	void testAlbumEsValido() {
		UsuarioRegistrado usuario = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
	    Cancion cancion1 = null;
	    Cancion cancion2 = null;
	    Cancion cancion3 = null;
	    try {
			cancion1 = new Cancion("cancion1","canciones/Thats What I Like.mp3",usuario);
			cancion2 = new Cancion("cancion2","canciones/Thats What I Like.mp3",usuario);
			cancion3 = new Cancion("cancion3","canciones/Thats What I Like.mp3",usuario);
		} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
	    
	    try {
			cancion1.validar(EstadoValidacion.EXPLICITO);
			cancion2.validar(EstadoValidacion.EXPLICITO);
		} catch (ExcepcionCancionYaValidada | ExcepcionCancionModificable e1) {
			fail("Lanzada excepcion no esperada");
		}
	    Album album1 = new Album("nombre album",usuario);
	    
	    try {
			album1.aniadirCancion(cancion1);
			album1.aniadirCancion(cancion2);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionCancionNoValidada e) {
			fail("Lanzada excepcion no esperada");
		}
	    assertTrue(album1.esValido());
	    try {
			cancion3.validar(EstadoValidacion.NOVALIDADA);
		} catch (ExcepcionCancionYaValidada | ExcepcionCancionModificable e1) {
			fail("Lanzada excepcion no esperada");
		}
	    try {
			album1.aniadirCancion(cancion3);
		} catch (ExcepcionInsercionInvalida e) {
			fail("Lanzada excepcion no esperada");
		} catch (ExcepcionCancionNoValidada e) {
			fail("Lanzada excepcion no esperada");
		}
	}


}
