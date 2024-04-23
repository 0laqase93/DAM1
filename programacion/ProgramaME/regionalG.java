import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class regionalG {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            Integer personas = Integer.parseInt(br.readLine());
            ArrayList<String> finaltrabajo = new ArrayList<>();

            for (int i = 0; i < personas; i++) {
                String persona = br.readLine();
                String[] trabajos = persona.split(" ");


                for (int j = 1; j < trabajos.length; j++) {
                    if (!finaltrabajo.contains(trabajos[j])) {
                        finaltrabajo.add(trabajos[j]);
                    }
                }
            }

            Integer trabajos = Integer.parseInt(br.readLine());

            for (int i = 0; i < trabajos; i++) {
                ArrayList<String> finaltrabajos = new ArrayList<>();
                String proy = br.readLine();
                String[] trabajo = proy.split(" ");


                for (int j = 1; j < trabajo.length; j++) {
                    finaltrabajos.add(trabajo[j]);
                }

                String res = "POSIBLE";

                for (String v : finaltrabajos) {
                    if (!finaltrabajo.contains(v)) {
                        res = "IMPOSIBLE";
                        break;
                    }
                }
                System.out.println(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
