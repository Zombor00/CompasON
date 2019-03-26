package test;

import org.junit.runner.RunWith;

import org.junit.runners.Suite;

import org.junit.runners.Suite.SuiteClasses;

import aplicacion.AplicacionTest;
import media.ListaTest;
import usuarios.UsuarioRegistradoTest;
import usuarios.AdministradorTest;

@RunWith(Suite.class)
@SuiteClasses({
	AplicacionTest.class,
    ListaTest.class,
    UsuarioRegistradoTest.class,
    AdministradorTest.class
})

class AllTests {

}
