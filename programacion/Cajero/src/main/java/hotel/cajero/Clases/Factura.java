package hotel.cajero.Clases;

public class Factura {
    private Integer numeroFactura;
    private String NIF;
    private Integer numeroHabitacion;
    private String importe;

    public Factura(Integer numeroFactura, String NIF, Integer numeroHabitacion, String importe) {
        this.numeroFactura = numeroFactura;
        this.NIF = NIF;
        this.numeroHabitacion = numeroHabitacion;
        this.importe = importe;
    }

    public Integer getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public Integer getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(Integer numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }
}
