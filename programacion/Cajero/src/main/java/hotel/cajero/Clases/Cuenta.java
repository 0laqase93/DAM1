package hotel.cajero.Clases;

public class Cuenta {
    private String numeroCuenta;
    private String NIF;
    private double saldo;

    public Cuenta(String numeroCuenta, String NIF, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.NIF = NIF;
        this.saldo = saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void restarSaldo(double quitar) {
        this.saldo -= quitar;
    }
}
