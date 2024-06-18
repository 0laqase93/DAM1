package com.acalabuig;

import com.acalabuig.model.Cultivo;
import com.acalabuig.model.Empleado;
import com.acalabuig.model.Maquinaria;
import com.acalabuig.model.Producto;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        Cultivo trigo = new Cultivo("Trigo", 100, 3);
        Empleado vicentCanyaules = new Empleado("Vicent Canyaules", "L'amo de tot açò", 3500);
        Producto harinaTrigo = new Producto("Harina de trigo", 0.50);
        Maquinaria tractor = new Maquinaria("Tractor", 150, 5);

        System.out.println("Cultivo: " + trigo.getNombre() + ", Rendimiento total: " + trigo.calcularRendimientoTotal() + " toneladas");
        System.out.println("Empleado: " + vicentCanyaules.getNombre() + ", Cargo: " + vicentCanyaules.getCargo() + ", Salario Anual: " + vicentCanyaules.calculaSalarioAnual() + " Euros");
        System.out.println("Producto: " + harinaTrigo.getNombre() + ", Precio con descuento: " + harinaTrigo.calcularPrecioConDescuento(10) + " €/kg");
        System.out.println("Maquinaria: " + tractor.getTipo() + ", Tiempo de harado para 50 hectáreas: " + tractor.calcularTiempoArado(50) + " horas");
    }
}
