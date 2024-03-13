package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import clases.*;

public class PuntuacionPiloto implements Comparable<PuntuacionPiloto> {
    private Piloto piloto;
    private Integer puntos = 0;

    public PuntuacionPiloto(Piloto piloto) {
        this.piloto = piloto;
        this.puntos = calculaPuntos(piloto.getNombre());
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Integer calculaPuntos(String nombrePiloto){
        Integer puntos = this.getPuntos();
        String linea;
        try {
            FileReader fr = new FileReader("clases/resultados.txt");
            BufferedReader br = new BufferedReader(fr);
            linea = br.readLine();
            while (linea != null) {
                if (linea.split(":")[1].equals(nombrePiloto)) {
                    int posicion = Integer.parseInt(linea.split(":")[2]);
                    switch (posicion) {
                        case 1: puntos += 25; break;
                        case 2: puntos += 18; break;
                        case 3: puntos += 15; break;
                        case 4: puntos += 12; break;
                        case 5: puntos += 10; break;
                        case 6: puntos += 8; break;
                        case 7: puntos += 6; break;
                        case 8: puntos += 4; break;
                        case 9: puntos += 2; break;
                        case 10: puntos += 1; break;
                    }
                }
                linea = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e){
            System.out.println("Error: No se encuentra el archivo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return puntos;
    }

    public int compareTo(PuntuacionPiloto rc) {
        return rc.getPuntos() - this.getPuntos();
    }
}
