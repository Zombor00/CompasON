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
	    Cancion cancion1 = null;
		try {
			cancion1 = new Cancion("cancion1","canciones/Thats What I Like.mp3",usuario1);
		} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
	    
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
	    Cancion cancion1 = null;
	    Cancion cancion2 = null;
	    boolean lanzadaExcepcion = false;
		try {
			cancion1 = new Cancion("cancion1","canciones/Thats What I Like.mp3",usuario1);
		    cancion2 = new Cancion("cancion2","canciones/Thats What I Like.mp3",usuario1);
		} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
	    
	    cancion1.validar(EstadoValidacion.NOVALIDADA);
	    try {
	    	cancion1.modificar("Wait for it",null);
		} catch (ExcepcionCancionYaValidada | ExcepcionCancionYaNoModificable | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
	    
	    assertSame(cancion1.getTitulo(),"Wait for it");
	    assertSame(cancion1.getModificableHasta(),null);
;	    
	    cancion1.validar(EstadoValidacion.APTOMENORES);
	    try {
	    	cancion1.modificar("CANTCHANGENAME", null);
	    	fail("Esperada excepcion no lanzada");
		} catch (ExcepcionCancionYaValidada | ExcepcionCancionYaNoModificable | ExcepcionMp3NoValido e) {
			lanzadaExcepcion = true;
		}
	   assertTrue(lanzadaExcepcion);
	   lanzadaExcepcion = false;

	    try {
	    	cancion1.modificar("My Shot",null);
	    	fail("Esperada excepcion no lanzada");
		} catch (ExcepcionCancionYaValidada e) {
			lanzadaExcepcion = true;
		} catch(ExcepcionCancionYaNoModificable | ExcepcionMp3NoValido e1){
			fail("Lanzada excepcion no esperada");
		}
	   assertTrue(lanzadaExcepcion);
	   lanzadaExcepcion = false;
	   

	   cancion2.validar(EstadoValidacion.NOVALIDADA);
	   try {
	    	cancion2.modificar(null,"DOENSTEXIST.mp3");
	    	fail("Esperada excepcion no lanzada");
		} catch ( ExcepcionMp3NoValido e) {
			lanzadaExcepcion = true;
		} catch(ExcepcionCancionYaNoModificable | ExcepcionCancionYaValidada e1){
			fail("Lanzada excepcion no esperada");
		}
	    
	    cancion2.validar(EstadoValidacion.NOVALIDADA);
	    try {
			cancion2.modificar("Thats What I Like","canciones/Thats What I Like.mp3");
		} catch (ExcepcionCancionYaValidada | ExcepcionCancionYaNoModificable | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
	}
	
	@Test 
	void testCancionContieneReproducible() {
		UsuarioRegistrado usuario1 = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
	    Cancion cancion1 = null;
	    Cancion cancion2 = null;
		try {
			cancion1 = new Cancion("cancion1","canciones/Thats What I Like.mp3",usuario1);
		    cancion2 = new Cancion("cancion2","canciones/Thats What I Like.mp3",usuario1);
		} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
	    assertTrue(cancion1.contieneReproducible(cancion1));
	    assertFalse(cancion1.contieneReproducible(cancion2));
	}
	
	@Test
	void testCancionDesbloquear() {
		UsuarioRegistrado usuario1 = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
		Cancion cancion1 = null;
	    Cancion cancion2 = null;
		try {
			cancion1 = new Cancion("cancion1","canciones/Thats What I Like.mp3",usuario1);
		    cancion2 = new Cancion("cancion2","canciones/Thats What I Like.mp3",usuario1);
		} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
		
	    
		cancion1.setEstado(Estado.BLOQUEADO);
		cancion1.desbloquear(cancion2);
		assertSame(cancion1.getEstado(),Estado.BLOQUEADO);
	    cancion1.desbloquear(cancion1);
	    assertSame(cancion1.getEstado(),Estado.NOBLOQUEADO);
	    cancion1.setEstado(Estado.BORRADO);
	    cancion1.desbloquear(cancion1);
	    assertSame(cancion1.getEstado(),Estado.BORRADO);
	}
	
	@Test
	void testCancionEsAptoMenores() {
		UsuarioRegistrado usuario1 = new UsuarioRegistrado("nombre usuario","contrasenia","nombre",LocalDate.now());
		Cancion cancion1 = null;
		try {
			cancion1 = new Cancion("cancion1","canciones/Thats What I Like.mp3",usuario1);
		} catch (FileNotFoundException | ExcepcionMp3NoValido e) {
			fail("Lanzada excepcion no esperada");
		}
	    
	    cancion1.validar(EstadoValidacion.EXPLICITO);
	    assertFalse(cancion1.esAptoParaMenores());
	    cancion1.validar(EstadoValidacion.APTOMENORES);
	    assertTrue(cancion1.esAptoParaMenores());
	}
	
    


}
