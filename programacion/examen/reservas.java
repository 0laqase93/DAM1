import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Cliente {
    private String dni;
    private String nombre;
    private String apellido;

    public Cliente(String dni, String nombre, String apellido) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getDNI() {
        return this.dni;
    }
}

class Habitacion {
    private int num;
    private int precio;
    private int plazas;
    private boolean[] reservada = new boolean[32];

    public Habitacion() { }

    public Habitacion(int num, int precio, int plazas) {
        this.num = num;
        this.precio = precio;
        this.plazas = plazas;
    }

    public int getNum() {
        return this.num;
    }

    public int getPrecio() {
        return precio;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    public void reservarHabitacion(int dia) {
        this.reservada[dia] = true;
    }

    public boolean diaReservado(int dia) {
        return this.reservada[dia];
    }
}

class HItinerante extends Habitacion {
    public HItinerante(int num) {
        super(num, 20, 1);
    }
}

class HEstandar extends Habitacion {
    public HEstandar(int num) {
        super(num, 80, 2);
    }
}

class Hotel {
    private String codigo;
    private String nombre;
    private String poblacion;
    private HashMap<Integer, Habitacion> habitaciones = new HashMap<>();

    public Hotel() { }

    public Hotel(String codigo, String nombre, String poblacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.poblacion = poblacion;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public Habitacion getHabitacion(int num_habitacion) {
        return this.habitaciones.get(num_habitacion);
    }

    public void addHabitacion(Habitacion habitacion) {
        this.habitaciones.put(habitacion.getNum(), habitacion);
    }

    public ArrayList<Integer> habitacionesDisponibles(int dia, int plazas) {
        ArrayList<Integer> disponibles = new ArrayList<>();
        for (Map.Entry<Integer, Habitacion> valor : habitaciones.entrySet()) {
            if ((!valor.getValue().diaReservado(dia)) && (valor.getValue().getPlazas() == plazas)) {
                disponibles.add(valor.getKey());
            }
        }
        return disponibles;
    }
}

class Reserva {
    private String cod;
    private String dni;
    private int precio;

    public Reserva(String dni, int dia, String codigo, int num_habitacion, int precio) {
        DecimalFormat formato = new DecimalFormat("0000");
        this.cod = codigo + dia + "/" + formato.format(num_habitacion);
        this.dni = dni;
        this.precio = precio;
    }

    public String imprimirReserva() {
        return "-------------------\nNúm Reserva: " + this.cod + "\nCliente: " + this.dni + "\nPrecio: " + this.precio +  " €\n-------------------";
    }
}

public class reservas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Reserva> reservas = new ArrayList<>();
        Hotel hotel = new Hotel("RDH", "Resort DAM Hotel","Paiporta");
        hotel.addHabitacion(new HItinerante(101));
        hotel.addHabitacion(new HItinerante(202));
        hotel.addHabitacion(new HEstandar(108));
        hotel.addHabitacion(new HEstandar(106));
        hotel.addHabitacion(new HEstandar(237));
        Cliente c1 = new Cliente("12345678W", "Juan", "García");
        Cliente c2 = new Cliente("12121212Y", "Luis", "Roig");
        //Comienza el Proceso de Reserva
        System.out.print("Escribe el código del hotel: ");
        String codigo = sc.nextLine();
        //Si existe el hotel pedimos los datos de la reserva: dia y plazas
        if (hotel.getCodigo().equals(codigo)){
            System.out.print("Introduce el dia: ");
            int dia = sc.nextInt();
            System.out.print("Di el numero de plazas: ");
            int plazas = sc.nextInt();
            //El método habitacionesDisponibles() crea una Lista de números de habitaciones disponibles
            ArrayList<Integer> libres = hotel.habitacionesDisponibles(dia,plazas);
            if (libres.isEmpty()){
                //No hay habitaciones libres para ese dia
                System.out.println("No hay habitaciones disponibles");
            }else{
                System.out.println(libres);
                System.out.print("Elige un número de habitación: ");
                int num_habitacion = sc.nextInt();
                if (libres.contains(num_habitacion)){
                    //Se RESERVA la habitación y se guarda la reserva en la Lista
                    hotel.getHabitacion(num_habitacion).reservarHabitacion(dia);
                    Reserva r = new Reserva(c1.getDNI(), dia, codigo, num_habitacion,
                    hotel.getHabitacion(num_habitacion).getPrecio());
                    reservas.add(r);
                    //El método imprimirReserva() da formato al justificante de la reserva
                    System.out.println(r.imprimirReserva());
                }else{
                    System.out.println("Esa habitacion no se puede reservar");
                }
            }
        }else{
            System.out.println("No existe ese Hotel");
        }
    }
}
