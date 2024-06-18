package com.acalabuig.model;

/**
 * La clase Cultivo representa un cultivo agrícola con su nombre, área y rendimiento.
 * @version 1.0
 */
public class Cultivo {

    private String nombre;
    /** En hectáreas */
    private double area;
    /** En toneladas por área */
    private double rendimiento;

    /**
     * Constructor para crear una nueva instancia de Cultivo.
     *
     * @param nombre el nombre del cultivo, no puede ser nulo ni vacío.
     * @param area el área del cultivo en hectáreas, debe ser un número positivo.
     * @param rendimiento el rendimiento del cultivo en toneladas por hectárea, debe ser un número positivo.
     * @throws IllegalArgumentException si nombre es nulo o vacío, área es no positiva, o rendimiento es no positivo.
     */
    public Cultivo(String nombre, double area, double rendimiento) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del cultivo no puede ser nulo o vacío.");
        }
        if (area <= 0 || rendimiento <= 0) {
            throw new IllegalArgumentException("Área y rendimiento deben ser números positivos.");
        }
        this.nombre = nombre;
        this.area = area;
        this.rendimiento = rendimiento;
    }

    /**
     * Obtiene el nombre del cultivo.
     *
     * @return el nombre del cultivo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el área del cultivo en hectáreas.
     *
     * @return el área del cultivo.
     */
    public double getArea() {
        return area;
    }

    /**
     * Obtiene el rendimiento del cultivo en toneladas por hectárea.
     *
     * @return el rendimiento del cultivo.
     */
    public double getRendimiento() {
        return rendimiento;
    }

    /**
     * Calcula el rendimiento total del cultivo en toneladas.
     *
     * @return el rendimiento total del cultivo.
     */
    public double calcularRendimientoTotal() {
        return area * rendimiento;
    }

    /**
     * Establece el nombre del cultivo.
     *
     * @param nombre el nombre del cultivo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el área del cultivo en hectáreas.
     *
     * @param area el área del cultivo.
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * Establece el rendimiento del cultivo en toneladas por hectárea.
     *
     * @param rendimiento el rendimiento del cultivo.
     */
    public void setRendimiento(double rendimiento) {
        this.rendimiento = rendimiento;
    }

    /**
     * Calcula los ingresos generados por el cultivo dado un precio por tonelada.
     *
     * @param precioPorTonelada el precio por tonelada.
     * @return los ingresos generados por el cultivo.
     */
    public double calcularIngresos(double precioPorTonelada) {
        return calcularRendimientoTotal() * precioPorTonelada;
    }
}
