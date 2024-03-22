package reservaVuelos.Clases;

public class Reserva {
    private int idVuelo;
    private int idPasajero;
    private int numAsiento;
    
    public Reserva(Vuelo vuelo, Pasajero pasajero, int numAsiento) {
        this.idVuelo = vuelo.getIdVuelo();
        this.idPasajero = pasajero.getIdPasajero();
        this.numAsiento = numAsiento;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public int getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public int getNumAsiento() {
        return numAsiento;
    }

    public void setNumAsiento(int numAsiento) {
        this.numAsiento = numAsiento;
    }
}
