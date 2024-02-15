package cartas;

public class JuegoUNO extends JuegoDeCartas {
    public boolean ganaMano(ListaCircular jugadores) {
        String ganador = "";
        int contador = 0;
        while ((contador < jugadores.getSize()) && (ganador.equals(""))) {
            Jugador jugador = jugadores.get(contador).getValor();
            if (jugador.mano.size() == 0) {
                return true;
            }
            contador++;
        }
        return false;
    }
}
