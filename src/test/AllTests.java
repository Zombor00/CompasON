package test;

import org.junit.runner.RunWith;

import org.junit.runners.Suite;

import org.junit.runners.Suite.SuiteClasses;

import aplicacion.AplicacionTest;
import media.ListaTest;

@RunWith(Suite.class)
@SuiteClasses({
	AplicacionTest.class,
    ListaTest.class
})

class AllTests {

}
