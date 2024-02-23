class Pieza {
    private String forma;
    private String color;

    public Pieza(String forma, String color) {
        this.forma = forma;
        this.color = color;
    }

    public double area() {
        return 1.0;
    }

    public boolean equals(Object o) {
        try {
            Pieza p = (Pieza) o;
            return this.color.equals(p.color) && this.forma.equals(p.forma) && this.area() == p.area();
        } catch (java.lang.ClassCastException e) {
            return false;
        }
    }
}

public class ej5 {
    public static void main(String[] args) {
        Pieza p1 = new Pieza("cuadrado","rojo");
        Pieza p2 = new Pieza("cuadrado","rojo");
        Double d = new Double(1.0);
        String k = "Hola";
        boolean b1 = p1.equals(p2);
        boolean b2 = d.equals(k);
        boolean b3 = k.equals(p2);
        boolean b4 = p1.equals(d);
        System.out.println(b1 + "-" + b2 + "-" + b3 + "-" + b4);
    }
}
