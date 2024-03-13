package clases;

import clases.*;

public class ResultadoCarrera {
    private Circuito circuito;
    private Piloto piloto;
    private Integer posicion;
    
    public ResultadoCarrera() {}

    public ResultadoCarrera(Circuito circuito, Piloto piloto, Integer posicion) {
        this.circuito = circuito;
        this.piloto = piloto;
        this.posicion = posicion;
    }

    public Circuito getCircuito() {
        return circuito;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }

    public void addPiloto(Piloto piloto, Integer posicion) {
        this.piloto = piloto;
        this.posicion = posicion;
    }
}
