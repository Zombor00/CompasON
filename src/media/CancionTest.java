package media;

import java.time.LocalDate;

import usuarios.UsuarioRegistrado;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CancionTest {
	
	@Test 
	void testCancionValidar() {
		UsuarioRegistrado usuario1 = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
	    Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario1);
	    
	    cancion1.validar(EstadoValidacion.NOVALIDADA);
	    assertSame(cancion1.getEstadoValidacion(),EstadoValidacion.NOVALIDADA);
	    /*(cancion1.getModificableHasta(),LocalDate.now().plusDays(3));*/
	    cancion1.validar(EstadoValidacion.EXPLICITO);
	    assertSame(cancion1.getEstadoValidacion(),EstadoValidacion.EXPLICITO);
	    cancion1.validar(EstadoValidacion.APTOMENORES);
	    assertSame(cancion1.getEstadoValidacion(),EstadoValidacion.APTOMENORES);
	}
	
	@Test 
	void testCancionModificar() {
		UsuarioRegistrado usuario1 = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
	    Cancion cancion1 = new Cancion("cancion1","ruta cancion1",usuario1);
	    Cancion cancion2 = new Cancion("cancion2","ruta cancion2",usuario1);
	    
	    cancion1.validar(EstadoValidacion.NOVALIDADA);
	    assertTrue(cancion1.modificar("Wait for it",null));
	    assertSame(cancion1.getTitulo(),"Wait for it");
	    assertSame(cancion1.getModificableHasta(),null);
;	    
	    cancion1.validar(EstadoValidacion.APTOMENORES);
	    assertFalse(cancion1.modificar("CANTCHANGENAME", null));
	    cancion1.validar(EstadoValidacion.EXPLICITO);
	    assertFalse(cancion1.modificar("My Shot",null));
	    
	    cancion2.validar(EstadoValidacion.NOVALIDADA);
	    assertFalse(cancion2.modificar(null,"DOENSTEXIST.mp3"));
	    
	    cancion2.validar(EstadoValidacion.NOVALIDADA);
	    assertTrue(cancion2.modificar("Thats What I Like","canciones/Thats What I Like.mp3"));
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
