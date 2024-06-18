package com.acalabuig.model;

/**
 * La clase Producto representa un producto con su nombre y precio.
 * @version 1.0
 */
public class Producto {
    private String nombre;
    /** En euros */
    private double precio;

    /**
     * Constructor para crear una nueva instancia de Producto.
     *
     * @param nombre el nombre del producto, no puede ser nulo ni vacío.
     * @param precio el precio del producto en euros, debe ser un número positivo.
     * @throws IllegalArgumentException si nombre es nulo o vacío, o si precio no es positivo.
     */
    public Producto(String nombre, double precio) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre de producto no puede ser nulo o vacío.");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser positivo.");
        }
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return el nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio del producto en euros.
     *
     * @return el precio del producto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nombre el nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el precio del producto en euros.
     *
     * @param precio el precio del producto.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Calcula el precio del producto después de aplicar un descuento.
     *
     * @param porcentajeDescuento el porcentaje de descuento, debe estar entre 0 y 100.
     * @return el precio del producto después del descuento.
     * @throws IllegalArgumentException si el porcentaje de descuento es negativo o mayor que 100.
     */
    public double calcularPrecioConDescuento(double porcentajeDescuento) {
        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 100.");
        }
        return precio * (1 - porcentajeDescuento / 100);
    }

    /**
     * Calcula el precio del producto después de aplicar un descuento.
     *
     * @param porcentajeDescuento el porcentaje de descuento, debe estar entre 0 y 100.
     * @return el precio del producto después del descuento.
     * @throws IllegalArgumentException si el porcentaje de descuento es negativo o mayor que 100.
     * @deprecated Reemplazado por {@link #calcularPrecioConDescuento(double)} ()}.
     */
    @Deprecated
    public double calcularPrecioConDescuentoAntigua(double porcentajeDescuento) {
        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 100.");
        }
        return precio * (1 - porcentajeDescuento / 100);
    }
}
