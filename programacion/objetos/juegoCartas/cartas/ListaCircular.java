package cartas;

import cartas.*;

public class ListaCircular {
    private Nodo inicio;
    private Nodo ultimo;
    private int size;
    
    public ListaCircular() {
        this.inicio = null;
        this.ultimo = null;
        this.size = 0;
    }

    public ListaCircular(Nodo inicio, Nodo ultimo, int size) {
        this.inicio = inicio;
        this.ultimo = ultimo;
        this.size = size;
    }

    public boolean esVacia() {
        return inicio == null;
    }

    public void add(Jugador valor) {
        Nodo nuevo = new Nodo();
        nuevo.setValor(valor);
        if (esVacia()) {
            this.inicio = nuevo;
            this.ultimo = nuevo;
            this.ultimo.setAnterior(nuevo);
            this.ultimo.setSiguiente(nuevo);
        } else {
            this.ultimo.setSiguiente(nuevo);
            nuevo.setSiguiente(this.inicio);
            this.ultimo = nuevo;
        }
        size++;
    }

    public Jugador get(int index) {
        int aux = 0;
        Nodo nodo = this.inicio;
        while (aux != index) {
            nodo = nodo.getSiguiente();
            aux++;
        }
        return nodo.getValor();
    }
}