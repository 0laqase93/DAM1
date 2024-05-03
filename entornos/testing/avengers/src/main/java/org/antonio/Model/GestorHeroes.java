package org.antonio.Model;

import org.antonio.Exception.HeroeNoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class GestorHeroes {
    private List<Heroe> heroes;

    public List<Heroe> getHeroes() {
        return heroes;
    }

    public GestorHeroes() {
        this.heroes = new ArrayList<>();
    }

    public void agregarHeroe(Heroe heroe) {
        this.heroes.add(heroe);
    }

    public Heroe buscarHeroe(String nombre) throws HeroeNoEncontradoException {
        for (Heroe heroe : this.heroes) {
            if (heroe.getNombre().equals(nombre)) {
                return heroe;
            }
        }
        throw new HeroeNoEncontradoException(nombre);
    }

    public void eliminarHeroe(String nombre) throws HeroeNoEncontradoException {
        Heroe h = buscarHeroe(nombre);
        heroes.remove(h);
    }

    public Heroe buscarHeroePorSuperpoder(String superpoder) throws HeroeNoEncontradoException {
        for (Heroe heroe : this.heroes) {
            if (heroe.getSuperpoderes().contains(superpoder)) {
                return heroe;
            }
        }
        throw new HeroeNoEncontradoException(superpoder);
    }

    public void actualizarHeroe(Heroe heroeActualizado) {
        int index = 0;
        for (Heroe heroe : this.heroes) {
            if (heroe.getNombre().equals(heroeActualizado.getNombre())) {
                heroes.set(index, heroeActualizado);
                break;
            }
            index++;
        }
    }

    public String listarHeroes() {
        String nombres = heroes.get(0).getNombre();
        for (int i = 1; i < heroes.size(); i++) {
            nombres += ", " + heroes.get(i).getNombre();
        }
        return nombres;
    }

    public ArrayList<Heroe> buscarHeroesPorSuperpoder(String superpoderes) {
        ArrayList<Heroe> devueltos = new ArrayList<>();
        String[] superpoder = superpoderes.split(" ");
        for (Heroe heroe : this.heroes) {
            for (int i = 0; i < superpoder.length; i++) {
                if (heroe.getSuperpoderes().contains(superpoder[i])) {
                    if (!devueltos.contains(heroe)) {
                        devueltos.add(heroe);
                    }
                }
            }
        }
        return devueltos;
    }
}
