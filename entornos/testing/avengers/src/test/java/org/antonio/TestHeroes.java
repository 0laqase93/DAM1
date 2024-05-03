package org.antonio;

import org.antonio.Exception.HeroeNoEncontradoException;
import org.antonio.Model.GestorHeroes;
import org.antonio.Model.Heroe;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestHeroes {

    GestorHeroes gestorHeroes = null;
    Heroe ironMan = null;
    Heroe spiderMan = null;
    Heroe capitanAmerica = null;

    @Before
    public void setUp() { //EXAMEN
        gestorHeroes = new GestorHeroes();
        ironMan = new Heroe("Iron Man", "Traje de alta tecnología", "Millonario y filántropo", "Sugar daddy");
        spiderMan = new Heroe("Spider-Man", "Sentido arácnido, trepador", "Tímido estudiante de secundaria", "Cara de araña");
        capitanAmerica = new Heroe("Capitán América", "Superfuerza, agilidad, resistencia", "Soldado de la Segunda Guerra Mundial", "Traje americano con un escudo super chulo");

        gestorHeroes.agregarHeroe(ironMan);
        gestorHeroes.agregarHeroe(spiderMan);
        gestorHeroes.agregarHeroe(capitanAmerica);
    }

    @Test
    public void testGetNombre() {
        assertTrue(ironMan.getNombre().equals("Iron Man"));
        assertNotSame(ironMan.getNombre(), "Mauricio");
    }

    @Test
    public void testGetSuperpoderes() {
        assertTrue(ironMan.getSuperpoderes().equals("Traje de alta tecnología"));
        assertNotSame(ironMan.getSuperpoderes(), "Tío que sirve para todo");
    }

    @Test
    public void testGetBiografia() {
        assertTrue(ironMan.getBiografia().equals("Millonario y filántropo"));
        assertNotSame(ironMan.getBiografia(), "Un buen fontanero de toda la vida");
    }

    @Test
    public void testSetNombre() {
        ironMan.setNombre("Mauricio");
        assertFalse(ironMan.getNombre().equals("Iron Man"));
        assertSame(ironMan.getNombre(), "Mauricio");
    }

    @Test
    public void testSetSuperpoderes() {
        ironMan.setSuperpoderes("Tío que sirve para todo");
        assertFalse(ironMan.getSuperpoderes().equals("Traje de alta tecnología"));
        assertSame(ironMan.getSuperpoderes(), "Tío que sirve para todo");
    }

    @Test
    public void testSetBiografia() {
        ironMan.setBiografia("Un buen fontanero de toda la vida");
        assertFalse(ironMan.getBiografia().equals("Millonario y filántropo"));
        assertSame(ironMan.getBiografia(), "Un buen fontanero de toda la vida");
    }

    @Test
    public void testAgregarHeroe() {
        gestorHeroes.agregarHeroe(ironMan);
        Heroe heroeFinal = gestorHeroes.getHeroes().get(gestorHeroes.getHeroes().size()-1);

        assertSame(ironMan, heroeFinal);
        assertTrue(heroeFinal.getSuperpoderes().equals("Traje de alta tecnología"));
    }

    @Test
    public void testBuscarHeroe() throws HeroeNoEncontradoException {
        Heroe h = gestorHeroes.buscarHeroe("Spider-Man");

        assertSame(spiderMan, h);
        assertTrue(h.getNombre().equals("Spider-Man"));
    }

    @Test(expected = HeroeNoEncontradoException.class)
    public void testHeroeNoEncontradoException() throws HeroeNoEncontradoException {
        gestorHeroes.buscarHeroe("Mauricio");
        gestorHeroes.buscarHeroe("Antonio");
    }

    @Test
    public void testGetDescripcion() throws HeroeNoEncontradoException {
        assertEquals("Sugar daddy", ironMan.getDescripcion());
        assertNotEquals("Superfuerza, agilidad, resistencia", capitanAmerica.getDescripcion());
        assertEquals("Traje americano con un escudo super chulo", capitanAmerica.getDescripcion());
    }

    @Test
    public void testSetDescripcion() {
        ironMan.setDescripcion("Robot grande");
        spiderMan.setDescripcion("FurroInsecto");

        assertNotEquals("Sugar daddy", ironMan.getDescripcion());
        assertNotSame("Cara de araña", spiderMan.getDescripcion());
    }

    @Test
    public void testEliminarHeroe() throws HeroeNoEncontradoException {
        int longitudInicial = gestorHeroes.getHeroes().size();
        gestorHeroes.eliminarHeroe("Spider-Man");
        int longitudfinal = gestorHeroes.getHeroes().size();

        assertNotEquals(longitudInicial, longitudfinal);
        assertTrue(longitudInicial > longitudfinal);
    }

    @Test
    public void testAgregarVariosHeroes() throws HeroeNoEncontradoException {
        Heroe ironMan2 = gestorHeroes.buscarHeroe("Iron Man");
        Heroe spiderMan2 = gestorHeroes.buscarHeroe("Spider-Man");
        Heroe capitanAmerica2 = gestorHeroes.buscarHeroe("Capitán América");

        assertEquals("Iron Man", ironMan2.getNombre());
        assertTrue(spiderMan2.getSuperpoderes().equals("Sentido arácnido, trepador"));
        assertSame("Soldado de la Segunda Guerra Mundial", capitanAmerica2.getBiografia());
    }

    @Test
    public void testBuscarHeroePorSuperpoder() throws HeroeNoEncontradoException {
        Heroe ironMan2 = gestorHeroes.buscarHeroePorSuperpoder("alta tecnología");
        Heroe spiderMan2 = gestorHeroes.buscarHeroePorSuperpoder("trepador");
        Heroe capitanAmerica2 = gestorHeroes.buscarHeroePorSuperpoder("Superfuerza");

        assertNotNull(ironMan2);
        assertNotNull(spiderMan2);
        assertNotNull(capitanAmerica2);

        assertEquals("Iron Man", ironMan2.getNombre());
        assertSame("Cara de araña", spiderMan2.getDescripcion());
        assertTrue(capitanAmerica2.getBiografia().equals("Soldado de la Segunda Guerra Mundial"));
    }

    @Test
    public void testActualizarHeroe() throws HeroeNoEncontradoException {
        Heroe ironMan2 = new Heroe("Iron Man", "Traje de baja tecnología", "Pobre y aburrido", "Nada especial");
        gestorHeroes.actualizarHeroe(ironMan2);

        assertNotNull(gestorHeroes.buscarHeroe("Iron Man"));
        assertEquals("Traje de baja tecnología", gestorHeroes.buscarHeroe("Iron Man").getSuperpoderes());
        assertNotEquals("Millonario y filántropo", gestorHeroes.buscarHeroe("Iron Man").getBiografia());
    }

    @Test
    public void testListarHeroes() {
        String nombres = gestorHeroes.listarHeroes();

        assertNotNull(nombres);
        assertEquals("Iron Man, Spider-Man, Capitán América", nombres);
    }

    @Test
    public void testBuscarHeroesPorSuperpoder() {
        ArrayList<Heroe> esperados = new ArrayList<>();
        esperados.add(ironMan);
        esperados.add(spiderMan);
        ArrayList<Heroe> encontrados = gestorHeroes.buscarHeroesPorSuperpoder("alta tecnología trepador");

        assertNotNull(encontrados);
        assertEquals(esperados, encontrados);
    }
}
