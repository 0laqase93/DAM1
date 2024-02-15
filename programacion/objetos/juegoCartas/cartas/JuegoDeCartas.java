package cartas;

abstract public class JuegoDeCartas {

    /** Esta función devuelve true si un jugador no tiene cartas e imprime el nombre del jugador
     * 
     * @param jugadores Se le pasa la lista con todos los jugadores
     * @return true si un jugador no tiene cartas, false si todos los jugadores tienen al menos una carta 
     */

    public abstract boolean ganaMano(ListaCircular jugadores);
}
