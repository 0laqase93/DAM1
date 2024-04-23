import java.io.InputStreamReader;
import java.io.BufferedReader;

public class ejercicio1 {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            Integer n1 = Integer.parseInt(br.readLine());
            for (int i = 0; i < n1; i++) {
                Integer n2 = Integer.parseInt(br.readLine());
                    String res = "CORRECTA";
                    String[] secString = br.readLine().split(" ");
                    Integer c = 0;
                    Integer[] sec = new Integer[secString.length];
                    for (String string : secString) {
                        sec[c] = Integer.parseInt(string);
                        c++;
                    }
                    boolean tipo = true;
                    boolean tipoAnteior = false;
                    for (int k = 1; k < secString.length; k++) {
                        int anterior = sec[k-1];
                        int actual = sec[k];
                        if (actual < 0) {
                            tipo = true;
                        } else {
                            tipo = false;
                        }
                        if (anterior < 0) {
                            tipoAnteior = true;
                        } else {
                            tipoAnteior = false;
                        }
                        if (Math.abs(actual) < Math.abs(anterior)) {
                            if (tipo != tipoAnteior) {
                                res = "INCORRECTA";
                                break;
                            }
                        }
                    }
                    System.out.println(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}