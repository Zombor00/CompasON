package media;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import usuarios.UsuarioRegistrado;
import org.junit.jupiter.api.Test;

import excepciones.ExcepcionCancionYaNoModificable;
import excepciones.ExcepcionCancionYaValidada;
import excepciones.ExcepcionDuracionLimiteSuperada;
import excepciones.ExcepcionMp3NoValido;

import static org.junit.jupiter.api.Assertions.*;

public class CancionTest {
	
	@Test 
	void testCancionValidar() {
		UsuarioRegistrado usuario1 = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
	    Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario1);
	    
	    cancion1.toString();
	    cancion1.validar(EstadoValidacion.NOVALIDADA);
	    assertSame(cancion1.getEstadoValidacion(),EstadoValidacion.NOVALIDADA);
	    /*(cancion1.getModificableHasta(),LocalDate.now().plusDays(3));*/
	    cancion1.validar(EstadoValidacion.EXPLICITO);
	    assertSame(cancion1.getEstadoValidacion(),EstadoValidacion.EXPLICITO);
	    cancion1.validar(EstadoValidacion.APTOMENORES);
	    assertSame(cancion1.getEstadoValidacion(),EstadoValidacion.APTOMENORES);
	}
	
	@Test 
	void testCancionModificar() throws FileNotFoundException, ExcepcionDuracionLimiteSuperada {
		UsuarioRegistrado usuario1 = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
	    Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario1);
	    Cancion cancion2 = new Cancion("cancion2","ruta cancion2",usuario1);
	    
	    cancion1.validar(EstadoValidacion.NOVALIDADA);
	    try {
			assertTrue(cancion1.modificar("Wait for it",null));
		} catch (ExcepcionCancionYaValidada | ExcepcionCancionYaNoModificable | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
	    
	    assertSame(cancion1.getTitulo(),"Wait for it");
	    assertSame(cancion1.getModificableHasta(),null);
;	    
	    cancion1.validar(EstadoValidacion.APTOMENORES);
	    assertThrows(ExcepcionCancionYaValidada.class, () -> {
	    	cancion1.modificar("CANTCHANGENAME", null);
	    });
	    
	    cancion1.validar(EstadoValidacion.EXPLICITO);
	    assertThrows(ExcepcionCancionYaValidada.class, () -> {
	    	cancion1.modificar("My Shot",null);
	    });
	    
	    cancion2.validar(EstadoValidacion.NOVALIDADA);
	    assertThrows(ExcepcionMp3NoValido.class, () -> {
	    	cancion2.modificar(null,"DOENSTEXIST.mp3");
	    });
	    
	    cancion2.validar(EstadoValidacion.NOVALIDADA);
	    try {
			assertTrue(cancion2.modificar("Thats What I Like","canciones/Thats What I Like.mp3"));
		} catch (ExcepcionCancionYaValidada | ExcepcionCancionYaNoModificable | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
	}
	
	@Test 
	void testCancionContieneReproducible() {
		UsuarioRegistrado usuario1 = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
	    Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario1);
	    Cancion cancion2 = new Cancion("cancion2","ruta cancion2",usuario1);
	    
	    assertTrue(cancion1.contieneReproducible(cancion1));
	    assertFalse(cancion1.contieneReproducible(cancion2));
	}
	
	@Test
	
	void testCancionDesbloquear() {
		UsuarioRegistrado usuario1 = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
	    Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario1);
	    Cancion cancion2 = new Cancion("cancion2","ruta cancion2",usuario1);
	    
		cancion1.setEstado(Estado.BLOQUEADO);
		cancion1.desbloquear(cancion2);
		assertSame(cancion1.getEstado(),Estado.BLOQUEADO);
	    cancion1.desbloquear(cancion1);
	    assertSame(cancion1.getEstado(),Estado.NOBLOQUEADO);
	}
	
    


}
