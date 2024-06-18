package com.acalabuig.model;

/**
 * La clase Maquinaria representa una maquinaria agrícola con su tipo, horas de uso y eficiencia.
 * @version 1.0
 */
public class Maquinaria {
    private String tipo;
    /** En horas */
    private double horaUso; // in hours
    /** Hectáreas por hora */
    private double eficiencia; // hectares per hour

    /**
     * Constructor para crear una nueva instancia de Maquinaria.
     *
     * @param tipo el tipo de maquinaria, no puede ser nulo ni vacío.
     * @param horasUso las horas de uso de la maquinaria, no puede ser negativo.
     * @param eficiencia la eficiencia de la maquinaria en hectáreas por hora, debe ser positiva.
     * @throws IllegalArgumentException si tipo es nulo o vacío, horasUso es negativo, o eficiencia es no positiva.
     */
    public Maquinaria(String tipo, double horasUso, double eficiencia) {
        if (tipo == null || tipo.isEmpty()) {
            throw new IllegalArgumentException("Tipo no puede ser nulo o vacío.");
        }
        if (horasUso < 0 || eficiencia <= 0) {
            throw new IllegalArgumentException("Horas de uso no puede ser negativo y eficiencia debe ser positiva.");
        }
        this.tipo = tipo;
        this.horaUso = horasUso;
        this.eficiencia = eficiencia;
    }

    /**
     * Obtiene el tipo de maquinaria.
     *
     * @return el tipo de maquinaria.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Obtiene las horas de uso de la maquinaria.
     *
     * @return las horas de uso.
     */
    public double getHoraUso() {
        return horaUso;
    }

    /**
     * Obtiene la eficiencia de la maquinaria en hectáreas por hora.
     *
     * @return la eficiencia de la maquinaria.
     */
    public double getEficiencia() {
        return eficiencia;
    }

    /**
     * Establece el tipo de maquinaria.
     *
     * @param tipo el tipo de maquinaria.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Establece las horas de uso de la maquinaria.
     *
     * @param horaUso las horas de uso.
     */
    public void setHoraUso(double horaUso) {
        this.horaUso = horaUso;
    }

    /**
     * Establece la eficiencia de la maquinaria en hectáreas por hora.
     *
     * @param eficiencia la eficiencia de la maquinaria.
     */
    public void setEficiencia(double eficiencia) {
        this.eficiencia = eficiencia;
    }

    /**
     * Calcula el tiempo necesario para arar una cierta cantidad de hectáreas.
     *
     * @param hectareas la cantidad de hectáreas a arar, debe ser un número positivo.
     * @return el tiempo en horas necesario para arar las hectáreas especificadas.
     * @throws IllegalArgumentException si hectáreas no es un número positivo.
     */
    public double calcularTiempoArado(double hectareas) {
        if (hectareas <= 0) {
            throw new IllegalArgumentException("Hectáreas debe ser un número positivo.");
        }
        return hectareas / eficiencia;
    }

    /**
     * Añade horas adicionales de trabajo a las horas de uso de la maquinaria.
     *
     * @param horasAdicionales las horas adicionales a añadir, no pueden ser negativas.
     * @throws IllegalArgumentException si horasAdicionales es negativo.
     */
    public void anyadirHorasTrabajo(double horasAdicionales) {
        if (horasAdicionales < 0) {
            throw new IllegalArgumentException("Horas adicionales no pueden ser negativas.");
        }
        this.horaUso += horasAdicionales;
    }
}