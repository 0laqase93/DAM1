package cartas;

import cartas.*;

public class Nodo {
    private Jugador valor;
    private Nodo anterior;
    private Nodo siguiente;

    public Nodo() {
        this.valor = new Jugador();
        this.anterior = null;
        this.siguiente = null;
    }

    public Nodo(Jugador valor, Nodo anterior, Nodo siguiente) {
        this.valor = valor;
        this.anterior = anterior;
        this.siguiente = siguiente;
    }

    public Jugador getValor() {
        return valor;
    }

    public void setValor(Jugador valor) {
        this.valor = valor;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
