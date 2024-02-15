package cartas;

import cartas.*;

import java.util.HashMap;
import java.util.Map;

public class Carta implements Comparable<Carta> {
    private Integer num;
    private char color;

    public Carta(Integer num, char color) {
        this.num = num;
        this.color = color;
    }

    public int getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "[" + this.color + this.num + Colores.RESET + "]";
    }

    @Override
    public int compareTo(Carta carta) {
        if (this.color == carta.getColor()) {
            return this.num - carta.getNum();
        }
        return this.color - carta.getColor();
    }
}
