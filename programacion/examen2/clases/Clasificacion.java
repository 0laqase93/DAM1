package clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import clases.*;

public class Clasificacion {

    List<PuntuacionPiloto> clasificacion;

    public Clasificacion(List<PuntuacionPiloto> clasificacion) {
        this.clasificacion = clasificacion;
    }

    public void addPuntuacionPiloto(PuntuacionPiloto p) {
        clasificacion.add(p);
    }

    public void ordenaClasificación() {
        Collections.sort(clasificacion);
    }

    public void imprimirClasificacion() {
        for (PuntuacionPiloto valor : clasificacion) {
            System.out.println(valor.getPiloto().getNombre() + " - " + valor.getPuntos());
        }
    }
}
