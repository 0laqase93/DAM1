package org.antonio.Exception;

public class HeroeNoEncontradoException extends Exception {
    public HeroeNoEncontradoException(String nombre) {
        super("No se encontró el héroe con nombre " + nombre);
    }
}
