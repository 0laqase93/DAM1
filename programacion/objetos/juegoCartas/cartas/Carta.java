package cartas;

import cartas.*;

import java.util.HashMap;
import java.util.Map;

public class Carta implements Comparable<Carta> {
    private Integer num;
    private String color;
    private Map<String, Integer> colores = new HashMap<>();

    public Carta(Integer num, String color) {
        this.num = num;
        this.color = color;
        colores.put(Colores.RED, 1);
        colores.put(Colores.GREEN, 2);
        colores.put(Colores.BLUE, 3);
        colores.put(Colores.YELLOW, 4);
        colores.put("", 5); //cartas especiales
    }

    public int getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "[" + this.color + this.num + Colores.RESET + "]";
    }

    @Override
    public int compareTo(Carta carta) {
        return this.colores.get(this.color) - carta.colores.get(carta.getColor());
    }
}
