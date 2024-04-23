package agenda;

import java.time.LocalDate;

public class Empleado {
    private int idEmpleado;
    private String nombre;
    private String apellidos;
    private String telefono; 
    private LocalDate fecha;
    private String cargo;

    public Empleado(int idEmpleado, String nombre, String apellidos, String telefono, LocalDate fecha, String cargo) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fecha = fecha;
        this.cargo = cargo;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getCargo() {
        return cargo;
    }

    public void actualizarEmpleado (String nombre, String apellidos, String telefono, LocalDate fecha, String cargo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fecha = fecha;
        this.cargo = cargo;
    }

    public void actualizarIdEmpleado (int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}
