package com.acalabuig.model;

/**
 * La clase Empleado representa un empleado con su nombre, cargo y salario.
 * @version 1.0
 */
public class Empleado {
    private String nombre;
    private String cargo;
    /** En euros */
    private double salario; // in Euros

    /**
     * Constructor para crear una nueva instancia de Empleado.
     *
     * @param nombre el nombre del empleado, no puede ser nulo ni vacío.
     * @param cargo el cargo del empleado, no puede ser nulo ni vacío.
     * @param salario el salario del empleado en euros, debe ser un número positivo.
     * @throws IllegalArgumentException si nombre o cargo son nulos o vacíos, o si salario no es positivo.
     */
    public Empleado(String nombre, String cargo, double salario) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del empleado no puede ser nulo o vacío.");
        }
        if (cargo == null || cargo.isEmpty()) {
            throw new IllegalArgumentException("Cargo no puede ser nulo o vacío.");
        }
        if (salario <= 0) {
            throw new IllegalArgumentException("Salario debe ser un número positivo.");
        }
        this.nombre = nombre;
        this.cargo = cargo;
        this.salario = salario;
    }

    /**
     * Obtiene el nombre del empleado.
     *
     * @return el nombre del empleado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el cargo del empleado.
     *
     * @return el cargo del empleado.
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Obtiene el salario del empleado en euros.
     *
     * @return el salario del empleado.
     */
    public double getSalario() {
        return salario;
    }

    /**
     * Establece el nombre del empleado.
     *
     * @param nombre el nombre del empleado.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el cargo del empleado.
     *
     * @param cargo el cargo del empleado.
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * Establece el salario del empleado en euros.
     *
     * @param salario el salario del empleado.
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * Calcula el salario anual del empleado.
     *
     * @return el salario anual del empleado.
     */
    public double calculaSalarioAnual() {
        return salario * 12;
    }

    /**
     * Da un aumento al salario del empleado basado en un porcentaje.
     *
     * @param porcentaje el porcentaje de aumento, no puede ser negativo.
     * @throws IllegalArgumentException si el porcentaje es negativo.
     */
    public void darAumento(double porcentaje) {
        if (porcentaje < 0) {
            throw new IllegalArgumentException("El porcentaje de aumento no puede ser negativo.");
        }
        this.salario += this.salario * porcentaje / 100;
    }
}
