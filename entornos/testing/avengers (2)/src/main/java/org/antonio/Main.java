package org.antonio;

import org.antonio.Exception.HeroeNoEncontradoException;
import org.antonio.Model.GestorHeroes;
import org.antonio.Model.Heroe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorHeroes gestorHeroes = new GestorHeroes();

        // Agregamos algunos héroes
        gestorHeroes.agregarHeroe(new Heroe("Iron Man", "Traje de alta tecnología", "Millonario y filántropo"));
        gestorHeroes.agregarHeroe(new Heroe("Spider-Man", "Sentido arácnido, trepador", "Tímido estudiante de secundaria"));
        gestorHeroes.agregarHeroe(new Heroe("Capitán América", "Superfuerza, agilidad, resistencia", "Soldado de la Segunda Guerra Mundial"));

        // Pedimos al usuario que seleccione un héroe
        Scanner scanner = new Scanner(System.in);
        System.out.println("Elige un héroe de la lista:");
        for (Heroe heroe : gestorHeroes.getHeroes()) {
            System.out.println("- " + heroe.getNombre());
        }
        String nombre = scanner.nextLine();

        // Buscamos el héroe y mostramos su información
        try {
            Heroe heroeSeleccionado = gestorHeroes.buscarHeroe(nombre);
            System.out.println("Nombre: " + heroeSeleccionado.getNombre());
            System.out.println("Superpoderes: " + heroeSeleccionado.getSuperpoderes());
            System.out.println("Biografía: " + heroeSeleccionado.getBiografia());
        } catch (HeroeNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
}